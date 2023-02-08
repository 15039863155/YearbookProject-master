package com.whackon.yearbook.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * <b>个人通讯录信息平台 - 系统常量工具类</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
public class SystemConstants {
	private static Logger logger = LoggerFactory.getLogger(SystemConstants.class);
	private static Properties props = new Properties();

	static {
		try {
			props.load(SystemConstants.class.getClassLoader().getResourceAsStream("props/system.properties"));
		} catch (IOException e) {
			// 将产生的异常记录到日志中
			logger.error(e.getMessage() + " : " + new Date(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * <b>Token 信息：Token 有效存储时长（秒）</b>
	 */
	public static final Integer TOKEN_EXPIRE_SEC = Integer.parseInt(props.getProperty("token.expire.sec"));

	/**
	 * <b>Token 信息：Token 加密秘钥</b>
	 */
	public static final String TOKEN_SECRET_KEY = props.getProperty("token.secret.key");

	/**
	 * <b>短信功能接口信息：serverIP</b>
	 */
	public static final String SMS_SERVER_IP = props.getProperty("sms.server.ip");

	/**
	 * <b>短信功能接口信息：serverPort</b>
	 */
	public static final String SMS_SERVER_PORT = props.getProperty("sms.server.port");

	/**
	 * <b>短信功能接口信息：账户 SID</b>
	 */
	public static final String SMS_ACCOUNT_SID = props.getProperty("sms.account.sid");

	/**
	 * <b>短信功能接口信息：账户 TOKEN</b>
	 */
	public static final String SMS_ACCOUNT_TOKEN = props.getProperty("sms.account.token");

	/**
	 * <b>短信功能接口信息：APP ID</b>
	 */
	public static final String SMS_APP_ID = props.getProperty("sms.app.id");

	/**
	 * <b>短信功能接口信息：模板 ID</b>
	 */
	public static final String SMS_TEMPLATE_ID = props.getProperty("sms.template.id");

	/**
	 * <b>短信验证码有效时长</b>
	 */
	public static final Integer SMS_EXPIRE_SEC = Integer.parseInt(props.getProperty("sms.expire.sec"));
}
