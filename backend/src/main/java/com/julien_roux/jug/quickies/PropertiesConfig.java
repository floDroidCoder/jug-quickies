package com.julien_roux.jug.quickies;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;

@Configuration
public class PropertiesConfig {
	
	@Bean
	PropertyPlaceholderConfigurer PropertyConfigurer() {
		PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
		configurer.setLocation(new ClassPathResource("META-INF/mongodb.properties"));
		return configurer;
	}
}
