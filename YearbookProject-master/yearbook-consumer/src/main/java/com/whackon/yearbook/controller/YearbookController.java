package com.whackon.yearbook.controller;

import cn.hutool.crypto.digest.MD5;
import com.whackon.yearbook.base.controller.BaseController;
import com.whackon.yearbook.base.pojo.vo.PageVO;
import com.whackon.yearbook.base.pojo.vo.ResponseVO;
import com.whackon.yearbook.base.util.RedisUtil;
import com.whackon.yearbook.base.util.TokenUtil;
import com.whackon.yearbook.pojo.vo.LoginVO;
import com.whackon.yearbook.pojo.vo.RegisterVO;
import com.whackon.yearbook.pojo.vo.YearbookVO;
import com.whackon.yearbook.transport.YearbookTransport;
import com.whackon.yearbook.util.CodeUtil;
import com.whackon.yearbook.util.SmsUtil;
import com.whackon.yearbook.util.SystemConstants;
import com.whackon.yearbook.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <b>个人通讯录信息平台 - 通讯录功能控制层类</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@RestController("yearbookController")
@RequestMapping("/yearbook")
public class YearbookController extends BaseController {
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private YearbookTransport yearbookTransport;

	/**
	 * <b>发送手机短信验证码</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/sms/{cellphone}")
	public Object sendSms(@PathVariable("cellphone") String cellphone) throws Exception {
		// 校验手机号码是否可用
		if (ValidationUtil.validateCellphone(cellphone)) {
			// 生成验证码
			String code = CodeUtil.generateValidationCode();
			// 将 code 存储到 Redis 中进行存储
			redisUtil.saveToRedis(cellphone, code, SystemConstants.SMS_EXPIRE_SEC);
			// 发送短信到手机
			if (SmsUtil.sendValidationCode(cellphone, code, SystemConstants.SMS_EXPIRE_SEC)) {
				return ResponseVO.createSuccessResponseVO("短信验证码发送成功");
			} else {
				return ResponseVO.createFailureResponseVO("短信验证码发送失败");
			}
		}
		return ResponseVO.createFailureResponseVO("手机号码格式错误");
	}

	/**
	 * <b>通讯录信息注册</b>
	 * @param registerVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/register")
	public ResponseVO register(@Validated @RequestBody RegisterVO registerVO, BindingResult bindingResult)
			throws Exception {
		// 判断是否能够得到错误信息
		if (bindingResult.hasErrors()) {
			// 提交注册信息有误，将错误信息提取后返回前端
			return ResponseVO.createFailureResponseVO(bindingResult.getFieldErrors());
		}

		// 注册信息通过有效性校验后，进行手机号码唯一性校验
		// 根据手机号码查询通讯录信息
		if (yearbookTransport.getVOByCellphone(registerVO.getCellphone()) != null) {
			// 根据手机号码查询到通讯录信息，则说明该手机号码已被占用
			return ResponseVO.createFailureResponseVO("该手机号码已经注册");
		}

		// 获得校验码信息，和 Redis 中的进行比较
		if (!registerVO.getSms().equals((String) redisUtil.getFromRedis(registerVO.getCellphone(), String.class))) {
			// 验证码错误
			return ResponseVO.createFailureResponseVO("验证码错误");
		}

		// 对登录密码进行加密后进行新通讯录信息添加
		registerVO.setPassword(MD5.create().digestHex(registerVO.getPassword()));
		if (yearbookTransport.registerInfo(registerVO)) {
			return ResponseVO.createSuccessResponseVO("新通讯录信息注册成功");
		}
		return ResponseVO.createFailureResponseVO("新通讯录信息注册失败");
	}

	/**
	 * <b>通讯录用户登录</b>
	 * @param loginVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	public ResponseVO login(@Validated @RequestBody LoginVO loginVO, BindingResult bindingResult)
			throws Exception {
		// 判断校验结构
		if (bindingResult.hasErrors()) {
			// 登录信息有误
			return ResponseVO.createFailureResponseVO(bindingResult.getFieldErrors());
		}

		// 根据手机号码查询用户信息
		YearbookVO yearbookVO = yearbookTransport.getVOByCellphone(loginVO.getCellphone());
		if (yearbookVO == null) {
			// 根据手机号码未查询到用户信息
			return ResponseVO.createFailureResponseVO("手机号码或登录密码错误");
		}

		// 对用户提交登录密码加密后进行判断
		if (!yearbookVO.getPassword().equals(MD5.create().digestHex(loginVO.getPassword()))) {
			// 登录密码错误
			return ResponseVO.createFailureResponseVO("手机号码或登录密码错误");
		}

		// 登录成功，根据当前用户信息生成 Token
		String token = TokenUtil.createToken(yearbookVO, SystemConstants.TOKEN_EXPIRE_SEC);
		// 以生成的 Token 为 key，当前登录用户信息为 value，存储到 Redis
		redisUtil.saveToRedis(token, yearbookVO, SystemConstants.TOKEN_EXPIRE_SEC);

		// 返回生成的 Token
		return ResponseVO.createSuccessResponseVO("登录成功", token);
	}

	/**
	 * <b>根据 Token 获得当前登录用户信息</b>
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/info/{token}")
	public ResponseVO queryInfoByToken(@PathVariable String token) throws Exception {
		// 判断 Token 是否有效
		if (TokenUtil.verifyToken(token, YearbookVO.class) != null) {
			// 根据 Token 从 Redis 中获得用户信息
			YearbookVO yearbookVO = (YearbookVO) redisUtil.getFromRedis(token, YearbookVO.class);
			if (yearbookVO != null) {
				return ResponseVO.createSuccessResponseVO("查询成功", yearbookVO);
			}
		}
		return ResponseVO.createUnAuthResponseVO();
	}

	/**
	 * <b>根据分页信息进行分页查询</b>
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/page/{pageNum}/{pageSize}")
	public ResponseVO queryByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) throws Exception {
		// 根据 pageNum 和 pageSize 创建 PageVO 对象
		PageVO<YearbookVO> pageVO = new PageVO<YearbookVO>(pageNum, pageSize);
		// 进行分页查询
		pageVO = yearbookTransport.getByPage(pageVO);
		return ResponseVO.createSuccessResponseVO("分页查询成功", pageVO);
	}

	/**
	 * <b>根据主键查询用户信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/id/{id}")
	public ResponseVO queryById(@PathVariable("id") String id) throws Exception {
		YearbookVO yearbookVO = yearbookTransport.getVOById(id);
		return ResponseVO.createSuccessResponseVO("查询成功", yearbookVO);
	}
}
