package com.whackon.yearbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <b>个人通讯录信息平台 - 通讯录功能 Provider 启动类</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@MapperScan("com.whackon.yearbook.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class YearbookProviderStarter {
	public static void main(String[] args) {
		SpringApplication.run(YearbookProviderStarter.class, args);
	}
}
