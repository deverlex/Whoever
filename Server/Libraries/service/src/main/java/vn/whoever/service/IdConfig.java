package vn.whoever.service;

import java.io.IOException;
import java.util.Properties;

public class IdConfig {

 	private Properties properties = new Properties();
	private static IdConfig idConfig = new IdConfig();
	
	private IdConfig() {}
	
	public static IdConfig getInstance() {
		return idConfig;
	}
	
	public Properties getProperties() {
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("id.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
