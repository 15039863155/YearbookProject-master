package com.whackon.yearbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whackon.yearbook.base.pojo.vo.PageVO;
import com.whackon.yearbook.base.util.IdGenerator;
import com.whackon.yearbook.mapper.YearbookMapper;
import com.whackon.yearbook.pojo.entity.Yearbook;
import com.whackon.yearbook.pojo.util.YearbookPojoMapper;
import com.whackon.yearbook.pojo.vo.RegisterVO;
import com.whackon.yearbook.pojo.vo.YearbookVO;
import com.whackon.yearbook.service.YearbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <b>个人通讯录信息平台 - 通讯录功能业务层接口实现类</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Service("yearbookService")
@Transactional
public class YearbookServiceImpl implements YearbookService {
	@Autowired
	private YearbookMapper yearbookMapper;
	@Autowired
	private IdGenerator idGenerator;

	/**
	 * <b>根据手机号码查询通讯录视图信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	@Override
	public YearbookVO getVOByCellphone(String cellphone) throws Exception {
		// 创建 QueryWrapper 对象，并且设置查询条件
		QueryWrapper<Yearbook> query = new QueryWrapper<Yearbook>().eq("cellphone", cellphone);
		// 进行列表查询
		List<Yearbook> entityList = yearbookMapper.selectList(query);
		// 判断是否能够查询到数据
		if (entityList != null && !entityList.isEmpty()) {
			// 如果能够查询到信息，那么有且也只能有一个
			return YearbookPojoMapper.INSTANCE.parseToVO(entityList.get(0));
		}
		return null;
	}

	/**
	 * <b>注册新通讯录信息</b>
	 * @param registerVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean registerInfo(RegisterVO registerVO) throws Exception {
		// 将注册视图转换为实体信息
		Yearbook entity = YearbookPojoMapper.INSTANCE.parseToEntityByRegisterVO(registerVO);
		// 使用雪花算法生成主键
		entity.setId(idGenerator.createId());
		// 进行保存
		if (yearbookMapper.insert(entity) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>进行分页查询</b>
	 * @param pageVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageVO<YearbookVO> getByPage(PageVO<YearbookVO> pageVO) throws Exception {
		// 开启 PageHelper 分页查询过滤器，并且设置当前页码和每页显示数量
		PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
		// 紧跟着 PageHelper 的列表查询将会自动进行分页查询
		List<Yearbook> list = yearbookMapper.selectList(new QueryWrapper<Yearbook>());
		// 为了能够获得分页信息，因此需要使用分页查询获得的列表创建 PageInfo 对象
		PageInfo<Yearbook> pageInfo = new PageInfo<Yearbook>(list);
		// 此时 PageInfo 对象中已经存储号对应的分页信息，通过 PageInfo 对象提取分页信息即可
		pageVO.setList(YearbookPojoMapper.INSTANCE.parseToVOList(pageInfo.getList()));
		pageVO.setTotalCount(pageInfo.getTotal());
		pageVO.setTotalPage(pageInfo.getPages());
		return pageVO;
	}

	/**
	 * <b>根据主键查询视图信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public YearbookVO getVOById(String id) throws Exception {
		// 根据主键查询实体信息
		Yearbook entity = yearbookMapper.selectById(id);
		// 判断能够查询到结果
		if (entity != null) {
			return YearbookPojoMapper.INSTANCE.parseToVO(entity);
		}
		return null;
	}
}
