# Web & App Automation Framework

This project is a **Web & App Automation Framework** using **Selenium**, **Java**, **TestNG**, and **Appium** for App. It is designed to automate web and app application testing and generate detailed reports with each test execution. The framework is structured to work with multiple browsers and platforms and is easy to extend for future test cases.

## Project Overview

- **Selenium**: WebDriver for automating browser interactions.
- **Appium**: Appium for automating App interactions.
- **TestNG**: A testing framework for running automated tests.
- **Page Object Model (POM)**: A design pattern used to create an object-oriented class that serves as an interface to a web page.

## Features

- **Automated Test Execution**: Automates various web tests with detailed results.
- **Parallel Test Execution**: Tests can run in parallel to save time.
- **Platform and Browser Flexibility**: Supports multiple platforms and browsers through TestNG configuration.
- **ExtentReports Integration**: Generates a comprehensive HTML report after test execution.
- **Reusable Test Functions**: Common functions are centralized for reusability and better maintenance.

## Requirements

To run this project, you need:

- Java 8 or higher
- Apache Maven
- WebDriver (ChromeDriver, GeckoDriver, etc.)
- Selenium WebDriver
- TestNG

## Setup Instructions

1. **Clone the Repository**

   Clone this repository to your local machine using the following command:
   
   ```bash
   git clone https://github.com/mohdosama99/automation-framework


**Set Up Environment Variables Before running the tests, set up the following environment variables:**

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

**You can set these environment variables:**

**Directly in the environment variable settings of your run configuration.
Or, while executing the entire test suite, use the appropriate XML file, such as AppSuite or WebSuite.**

**Troubleshooting Test not running:** Ensure that your platform and browser are properly set through environment variables or TestNG parameters. Environment variables not found: Verify that you have correctly set the environment variables for platform, browser, and environment before running the tests.
