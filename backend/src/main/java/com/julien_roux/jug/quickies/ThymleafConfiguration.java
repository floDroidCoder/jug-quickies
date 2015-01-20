package com.julien_roux.jug.quickies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;

@Configuration
public class ThymleafConfiguration {
	@Bean
	public TemplateEngine templateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addDialect(new SpringSecurityDialect());
		
		return templateEngine;
	}
}
