package com.util;

import java.util.Map;

public class Properties {

	private final static String LOCAL_SERVICE_URL = "http://localhost:8888/subscriber";
	private final static String ENV_SERVICE_URL = "SERVICES_URL";
	
	private static String serviceURL = null;
	
	public static void Init() {
		Map<String, String> env = System.getenv();
		if (env.get(ENV_SERVICE_URL) !=null) {
			setServiceURL(env.get(ENV_SERVICE_URL));
		}
		else {
			setServiceURL(LOCAL_SERVICE_URL);
		}
	}

	public static String getServiceURL() {
		if (serviceURL == null) {
			Init();
		}
		return serviceURL;
	}

	private static void setServiceURL(String serviceURL) {
		Properties.serviceURL = serviceURL;
	}
	
	
}
