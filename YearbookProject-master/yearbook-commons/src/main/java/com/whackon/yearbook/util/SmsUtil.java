package com.whackon.yearbook.util;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;

/**
 * <b>短信发送工具类</b>
 *
 * @author Arthur
 * @date 2022/9/24
 */
public class SmsUtil {
	/**
	 * <b>向对应手机号码发送验证码</b>
	 * @param cellphone
	 * @param code
	 * @param expireSec
	 * @return
	 */
	public static boolean sendValidationCode(String cellphone, String code, Integer expireSec) {
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		restAPI.init(SystemConstants.SMS_SERVER_IP, SystemConstants.SMS_SERVER_PORT);
		// 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
		restAPI.setAccount(SystemConstants.SMS_ACCOUNT_SID, SystemConstants.SMS_ACCOUNT_TOKEN);
		// 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
		restAPI.setAppId(SystemConstants.SMS_APP_ID);
		// 请使用管理控制台中已创建应用的APPID。
		HashMap<String, Object> result = restAPI.sendTemplateSMS(
				cellphone, SystemConstants.SMS_TEMPLATE_ID, new String[]{code, String.valueOf(expireSec / 60)});
		if("000000".equals(result.get("statusCode"))){
			return true;
		}
		return false;
	}
}
