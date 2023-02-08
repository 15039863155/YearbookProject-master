package com.whackon.yearbook.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.whackon.yearbook.base.pojo.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * <b>个人通讯录信息平台 - 通讯录实体信息</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Data
@TableName("sys_yearbook")
public class Yearbook extends BaseEntity {
	private static final long serialVersionUID = -498845685676973623L;
	@TableId(type = IdType.INPUT)
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
