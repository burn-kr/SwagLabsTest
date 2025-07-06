package com.allwyn.swaglabstaf.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A custom TestNG listener that provides detailed logging and visual feedback
 * (using ANSI escape codes for colored output) for test suite and individual test execution events.
 * It extends {@link TestListenerAdapter} to hook into TestNG's lifecycle.
 */
@Slf4j
public class TestNGExecutionListener extends TestListenerAdapter {

	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_RESET = "\u001B[0m";

	/**
	 * Called when the test suite starts.
	 * Logs the name of the suite and the total number of test methods found within it.
	 *
	 * @param testContext The {@link ITestContext} object providing information about the current test run.
	 */
	@Override
	public void onStart(ITestContext testContext) {
		log.info("Running suite: \"{}\" containing {} tests", testContext.getName(),
				testContext.getAllTestMethods().length);
	}

	/**
	 * Called when an individual test method starts its execution.
	 * Logs an ASCII art "START" banner in green and prints details about the test being executed,
	 * including its class name, method name, and any parameters.
	 *
	 * @param result The {@link ITestResult} object containing information about the test method.
	 */
	@Override
	public void onTestStart(ITestResult result) {
		log.debug("\n" + ANSI_GREEN + """
				 _____                _       ____    _                    _                _
				|_   _|   ___   ___  | |_    / ___|  | |_    __ _   _ __  | |_    ___    __| |
				  | |    / _ \\ / __| | __|   \\___ \\  | __|  / _` | | '__| | __|  / _ \\  / _` |
				  | |   |  __/ \\__ \\ | |_     ___) | | |_  | (_| | | |    | |_  |  __/ | (_| |
				  |_|    \\___| |___/  \\__|   |____/   \\__|  \\__,_| |_|     \\__|  \\___|  \\__,_|
				""" + ANSI_RESET);

		log.info("Starting test: {}#{} {} {}", result.getTestClass().getRealClass().getName(),
				result.getMethod().getMethodName(), join(result.getParameters()), join(result.getFactoryParameters()));
	}

	/**
	 * Called when an individual test method successfully passes.
	 * Logs a "PASSED" message in green along with the test class and method name.
	 * Also prints an ASCII art "SUCCESS" banner.
	 *
	 * @param result The {@link ITestResult} object containing information about the passed test method.
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("{}PASSED{}: {}#{}", ANSI_GREEN, ANSI_RESET, result.getTestClass().getRealClass().getName(),
				result.getMethod().getMethodName());

		log.debug("\n" + ANSI_GREEN + """ 
				  _____                _       ____                                   _
				 |_   _|   ___   ___  | |_    |  _ \\    __ _   ___   ___    ___    __| |
				   | |    / _ \\ / __| | __|   | |_) |  / _` | / __| / __|  / _ \\  / _` |
				   | |   |  __/ \\__ \\ | |_    |  __/  | (_| | \\__ \\ \\__ \\ |  __/ | (_| |
				   |_|    \\___| |___/  \\__|   |_|      \\__,_| |___/ |___/  \\___|  \\__,_|
				""" + ANSI_RESET);
	}

	/**
	 * Called when an individual test method fails.
	 * Logs the error message from the thrown {@link Throwable} in red.
	 * Prints an ASCII art "FAILED" banner and then logs a "FAILED" message in red
	 * along with the test class and method name.
	 *
	 * @param result The {@link ITestResult} object containing information about the failed test method,
	 * including the exception that caused the failure.
	 */
	@Override
	public void onTestFailure(ITestResult result) {

		log.error(result.getThrowable().getMessage());

		log.debug("\n" + ANSI_RED + """ 
				  _____                _       _____           _   _              _
				 |_   _|   ___   ___  | |_    |  ___|   __ _  (_) | |   ___    __| |
				   | |    / _ \\ / __| | __|   | |_     / _` | | | | |  / _ \\  / _` |
				   | |   |  __/ \\__ \\ | |_    |  _|   | (_| | | | | | |  __/ | (_| |
				   |_|    \\___| |___/  \\__|   |_|      \\__,_| |_| |_|  \\___|  \\__,_|
				""" + ANSI_RESET);
		log.error("{}FAILED{}: {}#{}", ANSI_RED, ANSI_RESET, result.getTestClass().getRealClass().getName(),
				result.getMethod().getMethodName());
	}

	/**
	 * Converts an array of objects into a comma-separated string.
	 * This helper method is used to format parameters for logging.
	 *
	 * @param params An array of {@link Object}s, typically method parameters.
	 * @return A {@link String} where each object's {@code toString()} representation is
	 * joined by a comma.
	 */
	private String join(Object[] params) {
		return Arrays.stream(params).map(Object::toString).collect(Collectors.joining(","));
	}
}
