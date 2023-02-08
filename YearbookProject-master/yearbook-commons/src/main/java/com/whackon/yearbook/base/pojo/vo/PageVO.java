package com.whackon.yearbook.base.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <b>基础分页视图信息</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Data
public class PageVO<E extends BaseVO> implements Serializable {
	private static final long serialVersionUID = -1679860709491765627L;
	private static final Integer PAGE_NUM = 1;                      // 默认当前页码
	private static final Integer PAGE_SIZE = 10;                    // 默认每页显示数量

	private Integer pageNum;                        // 当前页码
	private Integer pageSize;                       // 当前页码
	private List<E> list;                           // 分页列表
	private Long totalCount;                        // 总数量
	private Integer totalPage;                      // 总页数

	public PageVO() {}
	public PageVO(Integer pageNum, Integer pageSize) {
		if (pageNum != null && pageNum > 0) {
			this.pageNum = pageNum;
		} else {
			this.pageNum = PAGE_NUM;
		}

		if (pageSize != null && pageSize > 0) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = PAGE_SIZE;
		}
	}
}
