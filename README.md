# automation-framework
Automation Framework for Web and Mobile (Appium &amp; Selenium)

This repository contains the test automation framework designed for OWASP Web and Wiki App. The framework is built using Selenium, Appium, TestNG, Java, and other necessary utilities for performing automated tests.

**Prerequisites**
Before running the tests, ensure you have the following installed:

Java 8+
Maven
TestNG (Will be automatically downloaded via Maven)
Selenium (Handled by Maven dependencies)
WebDriver (ChromeDriver, GeckoDriver, etc.)

**Getting Started**
Clone the Repository
To clone this repository to your local machine, use the following command:

git clone <repository_url>
cd <repository_directory>

**Set Up Environment Variables**
Before running the tests, set up the following environment variables:

**platform:** Defines the platform for testing. It can be set to one of the following:

web (default)
android
ios
**browser:** Define which browser you want to run tests on. Options:

Chrome (default)
Firefox
Edge
**env:** Set the environment for testing. Options:

prod (default)
test

You can set these environment variables in your terminal as follows:

export platform=web
export browser=Chrome
export env=prod

Alternatively, you can specify these in testng.xml or pass them directly when running tests with Maven.

**Configuration File**
The test configuration is fetched from the src/main/resources/env directory based on the selected environment (e.g., prod.properties, test.properties, etc.).

**Running Tests**
Option 1: Run via Maven
To run the test cases via Maven, use the following command:

mvn clean test
This will execute the tests with the default configurations set in your WebSuite.xml and environment variables.

Option 2: Running Tests with Specific Parameters
If you want to specify parameters such as platform or browser, you can pass them via the command line as follows:

mvn test -Dplatform=android -Dbrowser=Firefox -Denvironment=staging
This will override the default environment variables and use the values provided for that test run.


The platform and browser parameters can be passed as needed.
Specifying Platform in Test Configuration
In your ConfigReader class, platform configuration is fetched from system properties or environment variables. You can set the platform via:

System.setProperty("platform", "android")
System.getenv("platform")
You can also specify the platform directly in testng.xml as shown in the example.

Running Tests on Mobile
To run tests on mobile platforms (Android/iOS), ensure that you have the necessary APK/IPA paths set in the prod.properties or relevant environment configuration file. The platform-specific paths for the mobile apps are:

Android: android_app.apkPath
iOS: ios_app.ipaPath

**Directory Structure**
src/
main/: Contains source code files for the framework (including utilities like ConfigReader.java).
resources/: Contains environment-specific property files (e.g., prod.properties).
src/test/java/: Contains your test cases (e.g., ChangeSetting.java, SearchFlow.java, etc.).
src/test/resources/: Contains TestNG configuration files and other necessary test-related resources.
pom.xml: The Maven build configuration file.
testng.xml: TestNG configuration file to define parallel execution and test suites.

**Useful Commands**
Run Tests: mvn clean test
Run Tests with Parameters: mvn test -Dplatform=android -Dbrowser=Chrome
Run Tests in Parallel: Modify testng.xml to enable parallel execution.

**Troubleshooting**
Test not running: Ensure that your platform and browser are properly set through environment variables or TestNG parameters.
Environment variables not found: Verify that you have correctly set the environment variables for platform, browser, and environment before running the tests.
