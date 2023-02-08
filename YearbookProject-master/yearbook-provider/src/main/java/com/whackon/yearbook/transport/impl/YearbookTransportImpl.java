package com.whackon.yearbook.transport.impl;

import com.whackon.yearbook.base.pojo.vo.PageVO;
import com.whackon.yearbook.pojo.vo.RegisterVO;
import com.whackon.yearbook.pojo.vo.YearbookVO;
import com.whackon.yearbook.service.YearbookService;
import com.whackon.yearbook.transport.YearbookTransport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <b>个人通讯录信息平台 - 通讯录功能传输层接口实现类</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@RestController("yearbookTransport")
@RequestMapping("/trans/yearbook")
public class YearbookTransportImpl implements YearbookTransport {
	@Resource(name = "yearbookService")
	private YearbookService yearbookService;

	/**
	 * <b>根据手机号码查询通讯录视图信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/cellphone")
	@Override
	public YearbookVO getVOByCellphone(@RequestParam String cellphone) throws Exception {
		return yearbookService.getVOByCellphone(cellphone);
	}

	/**
	 * <b>注册新通讯录信息</b>
	 * @param registerVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/register")
	@Override
	public boolean registerInfo(@RequestBody RegisterVO registerVO) throws Exception {
		return yearbookService.registerInfo(registerVO);
	}

	/**
	 * <b>进行分页查询</b>
	 * @param pageVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/page")
	@Override
	public PageVO<YearbookVO> getByPage(PageVO<YearbookVO> pageVO) throws Exception {
		return yearbookService.getByPage(pageVO);
	}

	/**
	 * <b>根据主键查询用户信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/id")
	@Override
	public YearbookVO getVOById(String id) throws Exception {
		return yearbookService.getVOById(id);
	}
}
