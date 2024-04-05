package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class PropertyFile {

	@Autowired
	private static Environment env;

	public static String getValueByKey(String key) {
		return env.getProperty(key);
	}

}
