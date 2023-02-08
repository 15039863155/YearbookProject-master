package com.whackon.yearbook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whackon.yearbook.pojo.entity.Yearbook;
import org.springframework.stereotype.Repository;

/**
 * <b>个人通讯录信息平台 - 通讯录功能数据持久层接口</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Repository("yearbookMapper")
public interface YearbookMapper extends BaseMapper<Yearbook> {
}
