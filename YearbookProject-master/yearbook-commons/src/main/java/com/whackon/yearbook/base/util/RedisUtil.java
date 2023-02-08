package com.whackon.yearbook.base.util;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <b>Redis 工具类</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Component("redisUtil")
public class RedisUtil {
	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * <b>根据 key，将对应的 value 存储到 Redis 中</b>
	 * @param key
	 * @param value
	 * @param expireSec
	 * @return
	 * @throws Exception
	 */
	public boolean saveToRedis(String key, Object value, Integer expireSec) throws Exception {
		// 由于 StringRedisTemplate 在存储 value 时，只能是 String 类型，因此需要将 Object 变为 String，
		// 可以将 Object 对应的对象变为 String 类型的 JSON 进行存储
		// 要想变为 JSON，则需要使用 JsonMapper 对象实现
		// 创建 JsonMapper 对象
		JsonMapper jsonMapper = new JsonMapper();
		// 通过 JsonMapper 将 Object 类型的 value 变为 JSON 格式的 String
		String valueJSON = jsonMapper.writeValueAsString(value);
		// 将该数据存储到 Redis 中
		redisTemplate.opsForValue().set(key, valueJSON);
		// 判断是否给定过期时间
		if (expireSec != null && expireSec > 0) {
			// 设置了过期时间，那么进行设置存储 Redis 的时长
			redisTemplate.expire(key, expireSec, TimeUnit.SECONDS);
		}
		return true;
	}

	/**
	 * <b>根据 key 获得 Redis 中所存储的数据</b>
	 * @param key
	 * @param valueType
	 * @return
	 * @throws Exception
	 */
	public Object getFromRedis(String key, Class valueType) throws Exception {
		// 根据 key 从 Redis 中获得相应的数据
		String valueJSON = redisTemplate.opsForValue().get(key);
		// 判断是否能够获取到相应的数据
		if (valueJSON != null && !"".equals(valueJSON.trim())) {
			// 需要将 JSON 格式的 value 根据所给定的类型，变为对应的对象
			// 创建 JsonMapper 对象
			JsonMapper jsonMapper = new JsonMapper();
			// 根据类型将对应的数据变为其对象格式
			return jsonMapper.readValue(valueJSON, valueType);
		} else {
			// 未获取到相应的数据
			return null;
		}
	}

	/**
	 * <b>将对应的数据从 Redis 中删除</b>
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public boolean deleteFromRedis(String key) throws Exception {
		redisTemplate.delete(key);
		return true;
	}
}
