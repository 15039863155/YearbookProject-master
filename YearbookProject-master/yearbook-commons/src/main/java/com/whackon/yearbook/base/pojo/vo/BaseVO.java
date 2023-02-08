package com.whackon.yearbook.base.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>基础视图信息</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Data
public class BaseVO implements Serializable {
	private static final long serialVersionUID = -7252651973164129425L;
	private Date createTime;                        // 创建时间
	private Date modifiedTime;                      // 修改时间
}
