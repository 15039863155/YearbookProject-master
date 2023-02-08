package com.whackon.yearbook.util;

import java.util.Random;

/**
 * <b>系统编码生成工具类</b>
 *
 * @author Arthur
 * @date 2022/9/24
 */
public class CodeUtil {

	/**
	 * <b>生成六位验证码</b>
	 * @return
	 */
	public static String generateValidationCode() {
		// 使用随机数生成 6 位验证码
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();

		while (buffer.length() < 6) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}
}
