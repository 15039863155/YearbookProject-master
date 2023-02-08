package com.whackon.yearbook.util;

import java.util.regex.Pattern;

/**
 * <b>验证工具类</b>
 *
 * @author Arthur
 * @date 2022/9/24
 */
public class ValidationUtil {
	private static final Pattern CELLPHONE_REGEX = Pattern.compile("1[0-9]{10}");

	/**
	 * <b>通过正则表达式校验手机号码</b>
	 * @param cellphone
	 * @return
	 */
	public static boolean validateCellphone(String cellphone) {
		return cellphone != null && CELLPHONE_REGEX.matcher(cellphone).find();
	}
}
