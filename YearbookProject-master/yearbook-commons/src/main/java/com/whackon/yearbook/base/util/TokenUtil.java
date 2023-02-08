package com.whackon.yearbook.base.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.whackon.yearbook.util.SystemConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Token 工具类</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
public class TokenUtil {
	// 设置生成 Token 所使用的加密算法为 HS256
	private static final Algorithm ALGORITHM = Algorithm.HMAC256(SystemConstants.TOKEN_SECRET_KEY);
	// 设置有效载荷存储的 KEY
	private static final String CLAIM_KEY = "info";

	/**
	 * <b>根据数据以及有效时长生成 Token</b>
	 * @param value
	 * @param expireSec
	 * @return
	 */
	public static String createToken(Object value, Integer expireSec) throws Exception {
		// 创建 JWT 生成器对象
		JWTCreator.Builder builder = JWT.create();

		// 设置 JWT Header 信息
		Map<String, Object> header = new HashMap<String, Object>();
		// 设置加密算法属性
		header.put("alg", "HS256");
		header.put("typ", "jwt");
		builder.withHeader(header);

		// 设置有效载荷信息
		// 为了能够将 Object 类型的有效载荷存储到 JWT 中，因此需要 Object 类型数据变为 JSON 格式的 String
		// 创建 JsonMapper
		JsonMapper jsonMapper = new JsonMapper();
		// 使用 JsonMapper 将 Object 的 value 变为 String 的 JSON
		String jsonValue = jsonMapper.writeValueAsString(value);
		// 将对应的数据存入到 JWT 中
		builder.withClaim(CLAIM_KEY, jsonValue);

		// 设置该 Token 所存在的时长
		// 判断是否给定了有效时长
		if (expireSec != null && expireSec > 0) {
			// 给定了有效时长，那么得到过期时间
			Date expireDate = new Date(System.currentTimeMillis() + expireSec * 1000);
			builder.withExpiresAt(expireDate);
		}

		// 使用加密算法对信息进行加密签名，生成最终的 Token
		return builder.sign(ALGORITHM);
	}

	/**
	 * <b>根据所给定的 Token 进行有效性校验</b>
	 * @param token
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static Object verifyToken(String token, Class type) throws Exception {
		// 判断 token 是否有效
		if (token != null && !"".equals(token.trim())) {
			// 给定有效地 Token 之后，创建 JWT Token 校验对象
			JWTVerifier verifier = JWT.require(ALGORITHM).build();
			// 校验 Token 有效性，获得 DecodedJWT 对象
			DecodedJWT decodedJWT = verifier.verify(token);
			// 通过 DecodedJWT 获得有效载荷信息
			String jsonValue = decodedJWT.getClaim(CLAIM_KEY).asString();
			// 判断该信息是否存在
			if (jsonValue != null && !"".equals(jsonValue.trim())) {
				// 将 JSON 格式的 value 数据变为对应类型的对象
				JsonMapper jsonMapper = new JsonMapper();
				return jsonMapper.readValue(jsonValue, type);
			} else {
				throw new Exception("未查找到 Token 信息");
			}
		} else {
			throw new Exception("未给定有效的 Token 信息");
		}
	}
}
