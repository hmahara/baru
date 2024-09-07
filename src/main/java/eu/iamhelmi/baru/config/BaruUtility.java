package eu.iamhelmi.baru.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaruUtility {
	private static String URI_BASE;
	
	@Value("${app.uri.base}")
	public void setStaticName(String name) {
		URI_BASE = name;
	}
	
	public static String getUriBase() {
		return URI_BASE;
	}

}
