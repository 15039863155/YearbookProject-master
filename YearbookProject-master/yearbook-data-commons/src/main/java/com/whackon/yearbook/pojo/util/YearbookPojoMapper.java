package com.whackon.yearbook.pojo.util;

import com.whackon.yearbook.pojo.entity.Yearbook;
import com.whackon.yearbook.pojo.vo.RegisterVO;
import com.whackon.yearbook.pojo.vo.YearbookVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <b>个人通讯录信息平台 - 通讯录 POJO 转换工具类</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Mapper
public interface YearbookPojoMapper {
	YearbookPojoMapper INSTANCE = Mappers.getMapper(YearbookPojoMapper.class);

	/**
	 * <b>将实体转换为视图</b>
	 * @param entity
	 * @return
	 */
	YearbookVO parseToVO(Yearbook entity);

	/**
	 * <b>将视图转换为实体</b>
	 * @param vo
	 * @return
	 */
	Yearbook parseToEntity(YearbookVO vo);

	/**
	 * <b>将实体列表转换为视图列表</b>
	 * @param entityList
	 * @return
	 */
	List<YearbookVO> parseToVOList(List<Yearbook> entityList);

	/**
	 * <b>将注册视图转换为实体信息</b>
	 * @param registerVO
	 * @return
	 */
	Yearbook parseToEntityByRegisterVO(RegisterVO registerVO);
}
