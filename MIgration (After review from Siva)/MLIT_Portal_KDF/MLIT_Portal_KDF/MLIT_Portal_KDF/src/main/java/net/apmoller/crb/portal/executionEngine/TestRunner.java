package net.apmoller.crb.portal.executionEngine;

import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import net.apmoller.crb.portal.config.ExtentManager;
import net.apmoller.crb.portal.config.TestActions;
import net.apmoller.crb.portal.config.Constants;
import net.apmoller.crb.portal.utils.ExcelReader;
import net.apmoller.crb.portal.utils.Log;

public class TestRunner extends ExtentManager {
	
	public WebDriver driver;

	public static Properties OR;
	public static TestActions testActions;
	public static String sTestActions;
	public static String sPageObject;
	public static Method method[];
 
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestStepName;
	public static String sTestCaseID;
	public static String testDesc;
	public static String sRunMode;
	public static String sData;
	
	public static boolean bResult;
	public static boolean testCaseStatus;
	public ExtentReports htmlReport ;
	public ExtentTest reportLogger;
	public static String imagePath = null;

	
	public TestRunner() throws NoSuchMethodException, SecurityException{
//        super();
		testActions = new TestActions();
		method = testActions.getClass().getMethods();
    	htmlReport = ExtentManager.getReporter(System.getProperty("user.dir")+"\\Reports\\Report.html");
    }
	
/* These annotations can be used if the application has a login functionality implemented, before executing the Test Cases
	  @BeforeSuite
	
	@Parameters({"Browser"})
	public void beforeSuite(String browser) throws Exception
	{
		TestActions.openBrowser(browser);
	}
	
	@BeforeClass
	@Parameters({"ENV","userName","Password","customerCode"})
	public void beforeClass(String env , String username, String password, String customerCode) throws Exception
	{
		if(browserResult==true)
		TestActions.login(env,  username,  password,  customerCode);
		else
		log_TestRunner.error("Error in opening browser  ");
	}
	@AfterSuite
	@Parameters({"Browser"})
	public void beforeSuite() throws Exception
	{
		TestActions.close_Browser();
	}
	
	@AfterClass
	@Parameters({"ENV","userName","Password","customerCode"})
	public void beforeClass() throws Exception
	{
		TestActions.logout();
	}
	*/
	@Test
	public void regressionSuite() throws Exception {
		try {
		ExcelReader.setExcelFile(Constants.Path_TestData); 
    	DOMConfigurator.configure("Log4j.xml");
    	String Path_OR = Constants.Path_ObjectRepo;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR= new Properties(System.getProperties());
		OR.load(fs);
 		TestRunner startEngine = new TestRunner();
		startEngine.execute_TestCase();
		}
		catch(Exception e)
    	{
    		Log.error("Error in invoking Test Runner:" +e.getMessage());
    	}
     }
    private void execute_TestCase() throws Exception {
	   	int iTotalTestCases = ExcelReader.getRowCount(Constants.Sheet_TestCases);
	   	TestActions.open_MozillaFFBrowser();
//	   	Iterate Test Cases from the "Test Cases" sheet of Data Engine
	       	for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
			testCaseStatus=true;
			
			sTestCaseID = ExcelReader.getCellData(iTestcase,Constants.Col_TestCase_ID, Constants.Sheet_TestCases);
			testDesc = ExcelReader.getCellData(iTestcase,Constants.Col_Description,Constants.Sheet_TestCases);
			sRunMode = ExcelReader.getCellData(iTestcase,Constants.Col_RunMode,Constants.Sheet_TestCases);
			Log.info("RunMode for test case "+sTestCaseID+ " is "+sRunMode);	

			reportLogger= htmlReport.startTest(sTestCaseID, testDesc);

//			Check on the RunMode value for true
			if (sRunMode.trim().equalsIgnoreCase("Yes")){
				Log.startTestCase(sTestCaseID);
			
/*				Use the below "for-loop" to Iterate the Test Data columns from TestData1 till TestData5
   			      for(int iTestData= Constants.Col_TestData1; iTestData<= Constants.Col_TestData5; iTestData++){
 */
 				  iTestStep = ExcelReader.getRowContains(sTestCaseID,Constants.Col_TestCase_ID, Constants.Sheet_TestSteps);
				  iTestLastStep = ExcelReader.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
                  
//	        Iterating the Test Steps for each Test Case
				for (int stepRepeater=iTestStep;stepRepeater<iTestStep+iTestLastStep;stepRepeater++)
				{
					bResult = true;
					Log.info("Executing " +sTestStepName+ " which is ("+stepRepeater+ ") line of Test Steps sheet" );
				
					sTestStepName = ExcelReader.getCellData(stepRepeater, Constants.Col_TestStepName,Constants.Sheet_TestSteps);
					sTestActions = ExcelReader.getCellData(stepRepeater, Constants.Col_Keyword,Constants.Sheet_TestSteps);
		    		sPageObject = ExcelReader.getCellData(stepRepeater, Constants.Col_PageObject, Constants.Sheet_TestSteps);
		    		sData = ExcelReader.getCellData(stepRepeater, Constants.Col_TestData1, Constants.Sheet_TestSteps);
/*                  Comment out the above  and Un-comment the below,if multi-test data is in use		    		
		    		sData = ExcelReader.getCellData(stepRepeater, iTestData, Constants.Sheet_TestSteps);
*/		    		    		
//				    Calls the TestActions class to execute the keyword-functions
		    		execute_Actions();
		    		
//		    	    Check on each Test Step and posting the result back to the Excel
		    		if(bResult==true)
		    		{	    		
		    			testCaseStatus = true;
		    			 Log.info(sTestStepName +" Passed");
		    			 reportLogger.log(LogStatus.PASS, sTestStepName);
		    			 ExcelReader.setCellData(Constants.KEYWORD_PASS,stepRepeater,Constants.Col_TestStepResult,Constants.Sheet_TestSteps); 					 
		    		}   		
		    		
		    		else
		    			if(bResult==false)	    		
		    		{	    		
		    			 testCaseStatus = false;
		    			 Log.error(sTestStepName +" Failed");
		    			 //reportLogger.log(LogStatus.FAIL, sTestStepName, reportLogger.addScreenCapture(imagePath));
		    			 ExcelReader.setCellData(Constants.KEYWORD_FAIL,stepRepeater,Constants.Col_TestStepResult,Constants.Sheet_TestSteps);
		    			 break;   		
		    		}	
		    	}
//	    	    Check on each Test Case and post the result back to the Excel
				if(testCaseStatus==false)
				{
                    Log.info(sTestCaseID +" Failed");
                 reportLogger.log(LogStatus.FAIL, sTestCaseID);
					ExcelReader.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_TestCaseResult,Constants.Sheet_TestCases);				

			   }  	
//				This will be executed only after the last step of the test case and if the value of bResult is not 'false' at any of the steps of a given Test Case	
			    if(testCaseStatus==true)
			   {				
			       Log.info(sTestCaseID +" passed");
			  	reportLogger.log(LogStatus.PASS, sTestCaseID);
			       ExcelReader.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_TestCaseResult,Constants.Sheet_TestCases);            
			      
			   }
        		Log.endTestCase(sTestCaseID);
        		ExtentManager.flushReport();
				
/* "for-loop" for the Multi-test data ends here				
				 } 
*/
 	          }
		  }
	TestActions.closeBrowser();
    }
    
//      Call the "TestActions" class to execute the keyword-functions
        private static void execute_Actions() throws Exception {
		for(int i=0;i<method.length;i++){
			if(method[i].getName().equals(sTestActions)){
				method[i].invoke(testActions,sPageObject, sData);
				break;
            }
		}
    }
}
