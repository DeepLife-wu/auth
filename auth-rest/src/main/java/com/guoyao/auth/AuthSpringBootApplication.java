/**
 * 
 */
package com.guoyao.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wuchao
 * @Date 【2019年1月16日:上午9:33:02】
 */
@SpringBootApplication
@RestController
@EnableScheduling
//@EnableSwagger2//swagger-ui.html
public class AuthSpringBootApplication {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello spring boot";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthSpringBootApplication.class, args);
	}
}
