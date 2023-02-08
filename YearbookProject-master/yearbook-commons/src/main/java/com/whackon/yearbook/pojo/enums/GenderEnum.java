package com.whackon.yearbook.pojo.enums;

/**
 * <b>个人通讯录信息平台 - 性别枚举信息</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
public enum GenderEnum {
	GENDER_MALE("M", "男性"),
	GENDER_FEMALE("F", "女性");

	private String code;                        // 性别编码
	private String remark;                      // 性别说明

	private GenderEnum(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
