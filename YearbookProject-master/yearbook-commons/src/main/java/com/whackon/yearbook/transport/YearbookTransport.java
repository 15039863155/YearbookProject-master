package com.whackon.yearbook.transport;

import com.whackon.yearbook.base.pojo.vo.PageVO;
import com.whackon.yearbook.pojo.vo.RegisterVO;
import com.whackon.yearbook.pojo.vo.YearbookVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <b>个人通讯录信息平台 - 通讯录传输层接口</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@FeignClient(name = "yearbook-provider")
public interface YearbookTransport {
	/**
	 * <b>根据手机号码查询通讯录视图信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/trans/yearbook/cellphone")
	YearbookVO getVOByCellphone(@RequestParam String cellphone) throws Exception;

	/**
	 * <b>注册新通讯录信息</b>
	 * @param registerVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/trans/yearbook/register")
	boolean registerInfo(@RequestBody RegisterVO registerVO) throws Exception;

	/**
	 * <b>进行分页查询</b>
	 * @param pageVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/trans/yearbook/page")
	PageVO<YearbookVO> getByPage(@RequestBody PageVO<YearbookVO> pageVO) throws Exception;

	/**
	 * <b>根据主键查询用户信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/trans/yearbook/id")
	YearbookVO getVOById(@RequestParam String id) throws Exception;
}
