package com.whackon.yearbook.base.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>基础实体信息</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Data
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 3330861048219084354L;
	@TableField("createTime")
	private Date createTime;                        // 创建时间
	@TableField("modifiedTime")
	private Date modifiedTime;                      // 修改时间
}
