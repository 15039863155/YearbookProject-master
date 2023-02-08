package com.whackon.yearbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>个人通讯录信息平台 - 通讯录功能 Consumer 启动类</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class YearbookConsumerStarter {
	public static void main(String[] args) {
		SpringApplication.run(YearbookConsumerStarter.class, args);
	}
}
