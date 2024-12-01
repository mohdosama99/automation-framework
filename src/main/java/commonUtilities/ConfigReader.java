package commonUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties properties = new Properties();
	private static String browser;
	private static String apkPath;
	private static String platform;
	private static String url;
	private static String env;

	static {
		
		platform = System.getenv("platform");
		if (platform == null || platform.isEmpty()) {
			throw new IllegalArgumentException(
					"Platform not specified. Please set the 'platform' environment variable.");
		} else {
			
		}

		env = System.getenv("env");

		if (env == null || env.isEmpty()) {
			env = "prod"; // Default to "prod" if not set
			System.out.println("Environment variable 'env' not set. Defaulting to: " + env);
		} else {
			System.out.println("Environment variable 'env' set to: " + env);
		}

		// Load the environment-specific properties file
		String configFilePath = String.format("src/main/resources/env/%s.properties", env);
		try (FileInputStream file = new FileInputStream(configFilePath)) {
			properties.load(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to load config file: " + configFilePath);
		}

		// Fetch other properties
		
		if(System.getenv("browser") != null) {
			browser = System.getenv("browser");
		} else {
			browser = properties.getProperty("browser");
		}
		//browser = System.getenv("browser") != null ? System.getenv("browser") : properties.getProperty("browser");

		// Fetch APK/IPA path based on platform
		if (platform.equalsIgnoreCase("android")) {
			apkPath = properties.getProperty("android_app.apkPath"); // Fetch Android APK path
		} else if (platform.equalsIgnoreCase("ios")) {
			apkPath = properties.getProperty("ios_app.ipaPath"); // Fetch iOS IPA path
		} else if (platform.equalsIgnoreCase("web")) {
			url = properties.getProperty("base_url");
		} else {
			throw new IllegalArgumentException(
					"Invalid platform: " + platform + ". Please set the 'platform' variable to 'android' or 'ios'.");
		}

	}
	
	public static String getProperties(String key) {
		return properties.getProperty(key);
	}

	public static String getEnv() {
		return env;
	}

	public static String getBrowser() {
		return browser;
	}

	public static String getApkPath() {
		return apkPath;
	}

	public static String getPlatform() {
		return platform;
	}

	public static String getUrl() {
		return url;
	}
}
