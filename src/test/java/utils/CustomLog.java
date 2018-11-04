package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CustomLog {
	
	private static final Logger log= LogManager.getLogger(CustomLog.class.getName());
	
	
	public static void startTest(String tcName)
	{
		log.info("***********************************************");
		log.info("TestCase "+tcName+" starts");
		log.info("***********************************************");
	}
	
	public static void endTest(String tcName)
	{
		log.info("***********************************************");
		log.info("TestCase "+tcName+" ends");
		log.info("***********************************************");
	}
	
	public static void warn(String logmessage)
	{
		log.warn(logmessage);
	}
	
	public static void info(String logmessage)
	{
		log.info(logmessage);
	}
	
	public static void error(String logmessage)
	{
		log.error(logmessage);
	}
	
	public static void fatal(String logmessage)
	{
		log.fatal(logmessage);
	}

}
