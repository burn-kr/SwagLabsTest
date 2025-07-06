package com.allwyn.swaglabstaf.config;

import com.allwyn.swaglabstaf.config.env.Timeout;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * Configuration class for defining application-wide beans, such as
 * {@link Timeout} durations and a {@link Faker} instance for generating test data.
 * This class uses Spring's Java-based configuration to set up various components
 * required by the application.
 */
@Configuration
public class AppConfig {

    /**
     * Creates a {@link Timeout} bean with values injected from application properties.
     * The implicit and explicit wait durations are sourced from {@code app.timeout.implicit}
     * and {@code app.timeout.explicit} properties respectively.
     *
     * @param implicitWait The duration for implicit waits, typically loaded from a property.
     * @param explicitWait The duration for explicit waits, typically loaded from a property.
     * @return A new {@link Timeout} object containing the configured wait durations.
     */
    @Bean
    public Timeout timeout(@Value("${app.timeout.implicit}") long implicitWait,
                           @Value("${app.timeout.explicit}") long explicitWait) {
        return new Timeout(implicitWait, explicitWait);
    }

    /**
     * Creates a {@link Faker} bean configured for the "en-US" locale.
     * This bean can be used throughout the application to generate realistic-looking
     * fake data for various purposes, such as testing.
     *
     * @return A new {@link Faker} instance initialized with the "en-US" locale.
     */
    @Bean
    public Faker faker() {
        return new Faker(new Locale("en-US"));
    }
}
