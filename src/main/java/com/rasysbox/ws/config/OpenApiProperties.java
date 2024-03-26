package com.rasysbox.ws.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "springdoc.properties")
public class OpenApiProperties {
	private String projectName;
	private String projectShortDescription;
	private String developerName;
	private String projectTosMsg;
	private String projectTosLink;
	private String projectLicenceMsg;
	private String projectLicenceLink;
	private String organizationUrl;
}
