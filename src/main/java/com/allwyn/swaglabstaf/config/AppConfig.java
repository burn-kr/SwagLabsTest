package com.allwyn.swaglabstaf.config;

import com.allwyn.swaglabstaf.config.env.Timeout;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class AppConfig {

    @Bean
    public Timeout timeout(@Value("${app.timeout.implicit}") long implicitWait,
                           @Value("${app.timeout.explicit}") long explicitWait) {
        return new Timeout(implicitWait, explicitWait);
    }

    @Bean
    public Faker faker() {
        return new Faker(new Locale("en-US"));
    }
}
