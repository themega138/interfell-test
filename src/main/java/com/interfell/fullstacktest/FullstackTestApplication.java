package com.interfell.fullstacktest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FullstackTestApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		new FullstackTestApplication().configure(new SpringApplicationBuilder(FullstackTestApplication.class)).run(args);
	}
}
