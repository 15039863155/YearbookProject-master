package com.whackon.yearbook.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <b>个人通讯录信息平台 - 通讯录注册视图信息</b>
 *
 * @author Arthur
 * @date 2022/9/24
 */
@Data
public class RegisterVO implements Serializable {
	private static final long serialVersionUID = -1502364628646284579L;
	@NotNull(message = "未填写姓名")
	@Pattern(regexp = "[\u4e00-\u9fa5A-Za-z]{2,}", message = "姓名格式错误")
	private String name;                        // 姓名
	@NotNull(message = "未填写手机号码")
	@Pattern(regexp = "1[0-9]{10}", message = "手机号码格式错误")
	private String cellphone;                   // 手机号码
	@NotNull(message = "未填写登录密码")
	@Pattern(regexp = "[A-Za-z0-9]{6,20}", message = "登录密码格式错误")
	private String password;                    // 登录密码
	@NotNull(message = "未填写验证码")
	private String sms;                         // 验证码
}
