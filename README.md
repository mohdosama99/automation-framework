# Web & App Automation Framework

This project is a **Web & App Automation Framework** using **Selenium**, **Java**, **TestNG**, and **Appium** for App. It is designed to automate web and app application testing and generate detailed reports with each test execution. The framework is structured to work with multiple browsers and platforms and is easy to extend for future test cases.

## Project Overview

- **Selenium**: WebDriver for automating browser interactions.
- **Appium**: Appium for automating App interactions.
- **TestNG**: A testing framework for running automated tests.
- **Page Object Model (POM)**: A design pattern used to create an object-oriented class that serves as an interface to a web page.
- **ExtentReports Integration:** Provides detailed HTML reports of test execution with various logging capabilities.
- **Logging with Log4j:** Logs are generated for every test action, providing detailed insights into the execution flow.

## Features

- **Automated Test Execution**: Automates various web tests with detailed results.
- **Parallel Test Execution**: Tests can run in parallel to save time.
- **Platform and Browser Flexibility**: Supports multiple platforms and browsers through TestNG configuration.
- **ExtentReports Integration**: Generates a comprehensive HTML report after test execution.
- **Reusable Test Functions**: Common functions are centralized for reusability and better maintenance.
- **Logging Integration:** Logs are captured for each test step with detailed information about actions and method executions.
- **Reusable Test Functions:** Common functions are centralized for reusability and better maintenance.

## Requirements

To run this project, you need:

- Java 8 or higher
- Apache Maven
- WebDriver (ChromeDriver, GeckoDriver, etc.)
- Selenium WebDriver
- TestNG
- Log4j for logging
- ExtentReports for test reporting

## Setup Instructions

1. **Clone the Repository**

   Clone this repository to your local machine using the following command:
   
   ```bash
   git clone https://github.com/mohdosama99/automation-framework


2. **Set Up Environment Variables Before running the tests, set up the following environment variables**

**platform** Specifies the testing platform.
Available options:
web (default)
android
ios

**browser** Specifies the browser to run tests on.
Available options:
Chrome (default)
Firefox
Edge

**env** Defines the testing environment.
Available options:
prod (default)
test

You can set these environment variables:
Directly in the environment variable settings of your run configuration.
Or, while executing the entire test suite, use the appropriate XML file, such as AppSuite or WebSuite.

3. **Troubleshooting Test not running**
Ensure that your platform and browser are properly set through environment variables or TestNG parameters.
**Environment variables not found:** Verify that you have correctly set the environment variables for platform, browser, and environment before running the tests.

4. **Logging and Report Generation**
**Logs:** The framework uses Log4j for logging test execution steps. Logs are saved at the debug level in the logs folder. You can customize the log level and output directory as per your requirements.

**ExtentReports:** The framework integrates ExtentReports to generate detailed HTML reports for each test execution. Logs from each test step are captured and displayed in the reports.
These reports are stored in the /reports folder and provide an overall summary, test results (Pass/Fail), and logs for each test method.

**Example ExtentReport log output:**
- Test Passed: Indicates that the test executed successfully.
- Test Failed: Indicates that the test failed along with the failure message.
- Test Skipped: Indicates that the test was skipped and the reason for skipping.

**Example of Test Execution**
To run the tests, use the following commands:

a. **For Web Tests:**
```bash
mvn clean test -DsuiteXmlFile=WebSuite.xml

b. **For App Tests:**
```bash
mvn clean test -DsuiteXmlFile=AppSuite.xml

After test execution, the logs and the ExtentReports HTML report will be generated in the logs and reports directories, respectively.
