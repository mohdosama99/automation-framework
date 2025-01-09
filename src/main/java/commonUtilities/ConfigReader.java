package commonUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.nio.file.Paths;
import org.testng.Reporter;

public class ConfigReader {
	private static Properties properties = new Properties();
	private static String browser;
	private static String apkPath;
	private static String platform;
	private static String url;
	private static String env;

	static {
		// Fetch platform from environment variables or system properties
		platform = System.getenv("platform"); // Try to get the platform from environment variables
		if (platform == null || platform.isEmpty() || platform.isBlank()) {
			platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platform");
		}

		// Throw an error if platform is not provided
		if (platform == null || platform.isEmpty() || platform.isBlank()) {
			throw new IllegalArgumentException(
					"Platform is mandatory. Please set the 'platform' environment variable or define it in the TestNG XML file.");
		}
		// Handle environment variable
		env = System.getenv("env");
		if (env == null || env.isEmpty() || env.isBlank()) {
			env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
		}
		if (env == null || env.isEmpty() || env.isBlank()) {
			env = "prod"; // Default to "prod" if not set
			System.out.println("Environment variable 'env' not set. Defaulting to: " + env);
		} else if (!env.equals("prod") && !env.equals("test")) {
			throw new IllegalArgumentException(
					"Invalid env: " + env + ". Please set the 'env' variable to 'prod' or 'test'.");
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

		if (System.getenv("browser") != null) {
			browser = System.getenv("browser");
		} else {
			browser = properties.getProperty("browser");
		}

		String systemPath = System.getProperty("user.dir");
		// Fetch APK/IPA path based on platform
		if (platform.equalsIgnoreCase("android")) {
//			String relativePath = properties.getProperty("android_app.apkPath");
//			apkPath = Paths.get("src", "main", "resources", relativePath).toString();
//			apkPath = properties.getProperty("android_app.apkPath"); // Fetch Android APK path
			apkPath = systemPath+"/src/main/resources/apps/WikipediaSample.apk";
		} else if (platform.equalsIgnoreCase("ios")) {
			String relativePathiOS = properties.getProperty("ios_app.ipaPath");
			apkPath = Paths.get("src", "main", "resources", relativePathiOS).toString();
			//apkPath = properties.getProperty("ios_app.ipaPath"); // Fetch iOS IPA path
		} else if (platform.equalsIgnoreCase("web")) {
			url = properties.getProperty("base_url");
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
