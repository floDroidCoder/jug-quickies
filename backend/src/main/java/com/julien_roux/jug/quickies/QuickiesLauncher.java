package com.julien_roux.jug.quickies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class QuickiesLauncher {

	public static void main(String[] args) {
		System.out.println("Starting Quickies application...");
		SpringApplication.run(new Object[] {QuickiesLauncher.class, ScheduledTasks.class}, args);
		System.out.println("end.");
	}
}
