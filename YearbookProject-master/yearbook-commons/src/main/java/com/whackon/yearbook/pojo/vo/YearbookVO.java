package com.whackon.yearbook.pojo.vo;

import com.whackon.yearbook.base.pojo.vo.BaseVO;
import lombok.Data;

import java.util.Date;

/**
 * <b>个人通讯录信息平台 - 通讯录视图信息</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Data
public class YearbookVO extends BaseVO {
	private static final long serialVersionUID = -3870109797462225188L;
	private String id;                      // 主键
	private String name;                    // 姓名
	private String cellphone;               // 手机号码
	private String password;                // 登录密码，采用 MD5 加密
	private String gender;                  // 性别，M-男性，F-女性
	private Date birthday;                  // 出生日期
	private String email;                   // 电子邮件
	private String hobby;                   // 兴趣爱好
	private String address;                 // 联系地址
	private String avatar;                  // 头像
}
