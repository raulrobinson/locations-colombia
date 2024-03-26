package com.rasysbox.ws.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Profile check config.
 */
@Configuration
public class ProfileCheckConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileCheckConfig.class);

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    /**
     * Profile check application runner.
     *
     * @return the application runner
     */
    @Bean
    public ApplicationRunner profileCheck() {
        return args -> LOGGER.info("Active Profiles: {}", activeProfiles);
    }
}
