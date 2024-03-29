package com.SohamsOnlineWebPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.SohamsOnlineWebPortal.config.constants.GithubConstants;
import com.SohamsOnlineWebPortal.config.constants.GoogleConstants;
import com.SohamsOnlineWebPortal.config.constants.LeetCodeConstants;
import com.SohamsOnlineWebPortal.config.constants.MongoDBConstants;

@SpringBootApplication(scanBasePackages = "com.SohamsOnlineWebPortal")
@EnableConfigurationProperties({
	LeetCodeConstants.class,
	GithubConstants.class,
	MongoDBConstants.class,
	GoogleConstants.class
})
public class SohamsOnlineWebPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SohamsOnlineWebPortalApplication.class, args);
	}

}
