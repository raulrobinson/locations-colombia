package com.rasysbox.ws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration implements WebMvcConfigurer {

    @Value("${controller.properties.base-path}")
    private String urlBase;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests

                        // SWAGGER
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/api-docs/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()

                        // REGISTER
                        .requestMatchers(HttpMethod.GET, urlBase + "/allLocations").permitAll()
                        .requestMatchers(HttpMethod.GET, urlBase + "/locationsById").permitAll()
                        .requestMatchers(HttpMethod.GET, urlBase + "/locationsByDepartment").permitAll()
                        .requestMatchers(HttpMethod.GET, urlBase + "/locationsByMunicipality").permitAll()
                        .requestMatchers(HttpMethod.GET, urlBase + "/locationsByRegion").permitAll()

                        .anyRequest().authenticated())
                .build();
    }

}
