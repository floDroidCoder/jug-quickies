package com.julien_roux.jug.quickies;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		
		registry.addViewController("/users/*").setViewName("/users/user-detail");
		
		registry.addViewController("/profile").setViewName("/profile/profile");
		registry.addViewController("/profile/edit").setViewName("/profile/profile-edit");
		
		registry.addViewController("/error").setViewName("/errors/error");
	}
}
