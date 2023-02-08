package com.whackon.yearbook.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <b>个人通讯录信息平台 - 登录视图信息</b>
 *
 * @author Arthur
 * @date 2022/9/26
 */
@Data
public class LoginVO implements Serializable {
	private static final long serialVersionUID = 7760952366430715363L;
	@NotNull(message = "未填写手机号码")
	@Pattern(regexp = "1[0-9]{10}", message = "手机号码格式错误")
	private String cellphone;
	@NotNull(message = "未填写登录密码")
	@Pattern(regexp = "[A-Za-z0-9]{6,20}", message = "登录密码格式错误")
	private String password;
}
