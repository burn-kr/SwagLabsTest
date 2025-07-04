package com.allwyn.swaglabstaf;

import com.allwyn.swaglabstaf.config.env.Credentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Credentials.class)
public class SwagLabsTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwagLabsTestApplication.class, args);
	}

}
