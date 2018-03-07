package net.apmoller.crb.portal.config;

import java.io.File;

//import java.io.File;

public class Constants {
	
//  System Variables
	public static final String URL = "http://uicmd.int.ml.apmoller.net:16210/md/customer/";
	public static final String Env = "CMD-INT";
//  	public static final String URL = "http://maerskline-int.apmoller.net/ssib/html/booking/booking.html";
//  	public static final String Env = "SSIB-INT";
  	
/* Data Sheet location and file-name
  Select the appropriate Data Sheet based on the the Portal Project 
 */
	
  public static final String Path_TestData = System.getProperty("user.dir") + "//src//main//resources//dataEngine//CMD_TestData.xlsx";
  public static final String File_TestData = "CMD_TestData.xlsx";
//  	public static final String Path_TestData = System.getProperty("user.dir") + "//src//main//resources//dataEngine//SSIB_TestData.xlsx";
//  	public static final String File_TestData = "SSIB_TestData.xlsx";
//  	public static final String Path_TestData = System.getProperty("user.dir") + "//src//main//resources//dataEngine//Testing - Copy.xlsx";
//	public static final String File_TestData = "Testing - Copy.xlsx";
  	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	
/* Object Repo
   Select the appropriate Data Sheet based on the the Portal Project 
 */
 	public static final String Path_ObjectRepo = new File("src/main/resources/objRepo/CMD_OR.properties").getAbsolutePath();
//  public static final String Path_ObjectRepo = new File("src/main/resources/objRepo/SSIB_OR.properties").getAbsolutePath();
//	public static final String Path_ObjectRepo = new File("src/main/resources/objRepo/or.properties").getAbsolutePath();

/*  Data Sheet Column Numbers
    Columns of the "Test Steps" sheet 
*/
	public static final int Col_TestCase_ID = 0;
	public static final int Col_TestStepName = 1;
	public static final int Col_PageObject = 2;
	public static final int Col_Keyword = 4;
	public static final int Col_TestData1 = 5;
	public static final int Col_TestData5 = 9;
	public static final int Col_TestStepResult = 6;
	
//  Columns of the "Test Case" Sheet
	public static final int Col_Reference = 1;
	public static final int Col_Description = 2;
	public static final int Col_RunMode = 3;
	public static final int Col_TestCaseResult = 4;
	
//	List of sheets of the DataEngine spreadsheet
	public static final String Sheet_TestSteps = "Test Steps";
	public static final String Sheet_TestCases = "Test Cases";

}