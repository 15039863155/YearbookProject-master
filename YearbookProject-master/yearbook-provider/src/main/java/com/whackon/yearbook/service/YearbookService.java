package com.whackon.yearbook.service;

import com.whackon.yearbook.base.pojo.vo.PageVO;
import com.whackon.yearbook.pojo.vo.RegisterVO;
import com.whackon.yearbook.pojo.vo.YearbookVO;

/**
 * <b>个人通讯录信息平台 - 通讯录功能业务层接口</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
public interface YearbookService {
	/**
	 * <b>根据手机号码查询通讯录视图信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	YearbookVO getVOByCellphone(String cellphone) throws Exception;

	/**
	 * <b>注册新通讯录信息</b>
	 * @param registerVO
	 * @return
	 * @throws Exception
	 */
	boolean registerInfo(RegisterVO registerVO) throws Exception;

	/**
	 * <b>进行分页查询</b>
	 * @param pageVO
	 * @return
	 * @throws Exception
	 */
	PageVO<YearbookVO> getByPage(PageVO<YearbookVO> pageVO) throws Exception;

	/**
	 * <b>根据主键查询视图信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	YearbookVO getVOById(String id) throws Exception;
}
