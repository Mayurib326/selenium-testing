package net.apmoller.crb.portal.config;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import net.apmoller.crb.portal.utils.Log;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

    public class ExtentManager {
	public static String appURL ;
	public static String env ;
	private static ExtentReports reports ;	
	private static ExtentTest logger;
	
    public synchronized static ExtentReports getReporter(String filePath) {
        if (reports == null) {
        	reports = new ExtentReports(filePath, true);
          	reports
                .addSystemInfo("Host URL", Constants.URL)
                .addSystemInfo("Environment", Constants.Env);
        }
         return reports;
    }
    
    public  static void flushReport()
	{
    	reports.endTest(logger);
    	reports.flush();
	}
    
    public  static void closeReport()
	{
    	reports.close();
	 
	}
	public static String captureScreen(WebDriver driver, String imagesPath) 
	{
		String timestamp = new SimpleDateFormat("HHmmss_ddMMyyyy").format(new Date());
		String imageFilePath = imagesPath +  timestamp +".png" ;
		Log.info(imageFilePath);
		TakesScreenshot oScn = (TakesScreenshot) driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(imageFilePath);
		try 
		{
			FileUtils.copyFile(oScnShot, oDest);
		} 
		catch (IOException e) 
		{
			Log.error("Error while capturing screenshot"+e.getMessage());
		}
		return imageFilePath;
	}
}
