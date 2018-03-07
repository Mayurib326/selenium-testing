package net.apmoller.crb.portal.utils;

import org.apache.log4j.Logger;

public class Log {

// Initialise Log4j logs
	private static Logger Log = Logger.getLogger(Log.class.getName());//

// Marks the beginning of the Test Case
	public static void startTestCase(String sTestCaseName) {

		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");
		Log.info("-------------------------- " + sTestCaseName+ "---------------------------");
		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");

	}
	public static Logger getLog(String className)
	{	
		return Log = Logger.getLogger(className);
	}


// End of Test Case
	public static void endTestCase(String sTestCaseName) {
		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-"	+ "             XXXXXXXXXXXXXXXXXXXXXX");
		Log.info("X");

	}

	// Need to create these methods, so that they can be called
	public static void info(String message) {
		Log.info(message);
	}

	public static void warn(String message) {
		Log.warn(message);
	}

	public static void error(String message) {
		Log.error(message);
	}

	public static void error(String message, Exception er) {
		Log.error(message, er);
	}
	
	public static void fatal(String message) {
		Log.fatal(message);
	}

	public static void debug(String message) {
		Log.debug(message);
	}

}