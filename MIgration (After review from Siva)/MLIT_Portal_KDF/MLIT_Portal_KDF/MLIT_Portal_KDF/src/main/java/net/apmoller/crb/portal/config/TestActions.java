
package net.apmoller.crb.portal.config;

import static net.apmoller.crb.portal.executionEngine.TestRunner.OR;




//import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.apmoller.crb.portal.executionEngine.TestRunner;
import net.apmoller.crb.portal.utils.Log;

public class TestActions {
	public static WebDriver driver;
	public static Logger log_testActions;
  	
	public static void openChromeBrowser() throws InterruptedException{		
		Log.info("Opening Browser");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromeDriver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static void open_IEBrowser() throws InterruptedException{		
		Log.info("Opening Browser");
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\ieDriver\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		driver.manage().window().maximize();
		
	}
	public static void open_MozillaFFBrowser() throws InterruptedException{		
		Log.info("Opening Browser");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
	}
/* 	The below login() and logout() functions can be used as appropriate w.r.t the AUT
     public static void login(String env , String username, String password, String customerCode) throws Exception
	{		
		if(env.equals("DEV3") || env.equals("INT") )
		{   
			driver.get("http://maerskline-dev3.apmoller.net");
			waitFor("Home_url", "5000", "Login");		
			TestActions.click("link_HomeLogin", " ", "Login","Login");
			waitFor("txtbx_Username_DEV3 ", "5000", "Login");		
			TestActions.input("txtbx_Username_DEV3", username,"Login ","Login");
			TestActions.input("txtbx_Password_DEV3", password,"Login ","Login ");
			TestActions.click("link_Login_DEV3", " ", "Login","Login");
			waitFor("link_Login_DEV3 ", "5000", "Login");		
			if(TestActions.verifyPresence("label_Login_success_DEV3","","Login ","Login"))
			{
				driver.navigate().to("http://maerskline-dev3.apmoller.net/workas?attemptedUrl=%2F");
					waitFor("txtbx_Code_DEV3 ", "3000", "Login ");
				TestActions.input("txtbx_Code_DEV3", customerCode, "Login","Login");
				TestActions.click("btn_Continue_DEV3", " ", "Login","Login");
				waitFor("btn_Continue_DEV3 ", "5000", "Login");
				//logger.log(LogStatus.PASS, stepName, "Login success");
				
				log_testActions.info("logged in to " + env + "successfully ");
				 TestRunner.loginResult = true;
			}
			else
			{
				log_testActions.error("Not able to login to " + env + " successfully ");
				 TestRunner.imagePath =  ExtentManager.CaptureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
				 TestRunner.loginResult = false;
			}
		}
		else
		if(env.equals("SIT") ||env.equals("PP") )
		{
			driver.get("https://myt.apmoller.net");
			waitFor("Home_url ", "5000","Login Page");	
			TestActions.click("link_HomeLogin", " ","Login ","Login");
			waitFor("link_HomeLogin ", "5000","Login Page");
			TestActions.input("txtbx_Username_SIT", username,"Login ","Login");
			TestActions.input("txtbx_Password_SIT", password, "Login","Login");
			waitFor("link_Login_SIT", "2000", "Login");
			TestActions.click("link_Login_SIT", " ","Login","Login");
			waitFor("txtbx_Code_SIT ", "5000", "Login Page");
			if(TestActions.verifyPresence("label_Login_success_SIT","","Login","Login"))
			{
				TestActions.input("txtbx_Code_SIT", customerCode, "Login","Login");
				waitFor(" ", "2000", "Login Page");
				TestActions.click("btn_Continue_SIT", " ", "Login","Login");
				log_testActions.info("logged in to " + env + "successfully");
			
				 TestRunner.loginResult = true;
			}
			else
			{
				log_testActions.error("Not able to login to " + env + " successfully ");
				 TestRunner.imagePath =  ExtentManager.CaptureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
				 TestRunner.loginResult = false;
				 
			}
		}
			
	}
	
	public static void logout()
	{

		try{

			log_testActions.info("Logging out of the application");
			driver.get("http://maerskline-dev3.apmoller.net/logout");
		 }
		catch(Exception e)
		{
			 log_testActions.error("Not able to Logging out of the application" + e.getMessage());
			 
       	}
	}
*/
	public static void navigate(String object, String data)
	{
		try{
			TestRunner.bResult=true;
			Log.info("Navigating to the site");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(data);
		   }
		catch(Exception e)
		{
			System.out.println(e);
			   TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
			Log.info("Not able to navigate --- " + e.getMessage());
			TestRunner.bResult = false;
			}
		}
	public static void input(String object, String data){
		try{
			Log.info("Entering the text in " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		   }catch(Exception e){
			TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
			 Log.error("Not able to enter the text --- " + e.getMessage());
			 TestRunner.bResult = false;
		 	}
		}
	
	
	public static void click(String object, String data){
		try{
			Log.info("Clicking on Web Element "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		   }catch(Exception e){
			TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to click --- " + e.getMessage());
 			TestRunner.bResult = false;
         	}
		}
	public static void wait(String object, String data){
		try{
			Log.info("Waiting for the page to load...");
			Thread.sleep(3500);
		   }
            catch(Exception e){
            TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
			  Log.info("Not able to load the page --- " + e);
			  TestRunner.bResult = false;
			}
		}
	public static void conditionalWait(String object, String data){
		try{
			Log.info("Waiting for the page to load after invisibility of the grey screen...");
			WebDriverWait wait = new WebDriverWait(driver,15);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		   }
	    catch(Exception e){
	    TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
		Log.info("Unable to load the page ( greying out screen takes time ) --- " + e);
		TestRunner.bResult = false;
		}
	}
	//the code written by me
	public static void alertaccept(String object, String data){
		try{
			Log.info("accepting alert "+ object);
			 Alert alert=driver.switchTo().alert();
			  alert.accept();
			
		   }catch(Exception e){
			TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to click --- " + e.getMessage());
 			TestRunner.bResult = false;
         	}
		}
	public static void alertreject(String object, String data){
		try{
			Log.info("rejecting alert "+ object);
			 Alert alert=driver.switchTo().alert();
			  alert.dismiss();
			
		   }catch(Exception e){
			TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to click --- " + e.getMessage());
 			TestRunner.bResult = false;
         	}
		}
	public static void goback(String object, String data){
		try{
			Log.info("going back from current window "+ object);
			driver.navigate().back();
			
		   }catch(Exception e){
			TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to click --- " + e.getMessage());
 			TestRunner.bResult = false;
         	}
	}
	
	public static void suggestion_select(String object, String data){
		try{
			Log.info("Clciking on the suggested element "+ object);
			//driver.navigate().back();
			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			driver.findElement(By.xpath(object)).click();
			
		   }catch(Exception e){
			TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to click --- " + e.getMessage());
 			TestRunner.bResult = false;
         	}
	}
	
	//the code written by me ends here
	/*public static void impliciteWait(String object, String data){
		 
		  try{
			  Log.info("Waiting for the page to load ");
		  driver.manage().timeouts().implicitlyWait(data, TimeUnit.SECONDS);
		 
		  }catch(Exception e){
				 
			  TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
				Log.info("Unable to load the page ( greying out screen takes time ) --- " + e);
				TestRunner.bResult = false;
			}
		 }*/
	public static void select(String object, String data){
		try{
			Log.info("Selecting the option from "+ object);
			Thread.sleep(3000);
			List <WebElement> droplist = driver.findElements(By.xpath(OR.getProperty(object)));
		         for(WebElement option:droplist)
			     	{
					if(option.getText().trim().equalsIgnoreCase(data))
					{
						option.click();
					}
				   }
	       }catch(Exception e){
	    	TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to select --- " + e.getMessage());
 			TestRunner.bResult = false;
         	}
		}
	
	
	public void seleteDropDownValue(String object, String data){
		try{
			Log.info("Selecting the option from "+ object);
			Thread.sleep(3000);
		WebElement mySelectElement = driver.findElement(By.xpath(OR.getProperty(object)));
		Select dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText(data);
		 }
		catch(Exception e){
	    	TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to select --- " + e.getMessage());
 			TestRunner.bResult = false;
         	}
	}
	public static void selectCustomertype(String object, String data){
		try{
			Log.info("Selecting the Customer Type option"+ object);
			List <WebElement> radiolist = driver.findElements(By.xpath(OR.getProperty(object)));
					for(WebElement option:radiolist)
			     	{
					if(option.getText().equals(data))
					{
						option.click();
					}
				   }
		 }catch(Exception e){
			 TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to select --- " + e.getMessage());
 			TestRunner.bResult = false;
         	}
		}
	public static void selectStatus(String object, String data){
		try{
			Log.info("Selecting the Status of record"+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		 }catch(Exception e){
			 TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to select --- " + e.getMessage());
 			System.out.println(e.getMessage());
 			TestRunner.bResult = false;
         	}
		}

public static void selectPayer(String object, String data){
	try{
		Log.info("Selecting the dropdown option"+ object);
		Select option = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
		option.selectByVisibleText(data);
	   }catch(Exception e){
		   TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
			Log.error("Not able to select --- " + e.getMessage());
			TestRunner.bResult = false;
	   }
     }
	
	public static void dropdownClickSelect(String object, String data){
		try{
			Log.info("Selecting the dropdown option"+ object);
			Select option = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
			option.selectByVisibleText(data);
		   }catch(Exception e){
			   TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
 			Log.error("Not able to select --- " + e.getMessage());
 			TestRunner.bResult = false;
         	}
		}
	public static void closeBrowser(){
		System.out.println("Closing browser");
		try{
//			Thread.sleep(10000);
			Log.info("Closing the browser");
			driver.quit();
		 }catch(Exception e){
			 TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
			 Log.error("Not able to Close the Browser" + e.getMessage());
			 TestRunner.bResult = false;
       	}
	}
	public static void validate(String object, String data)
	{
		try{
			if(driver.findElement(By.xpath(OR.getProperty(object))).isDisplayed())
			{
				TestRunner.bResult = true;
				return;
			}
			else
			{
				TestRunner.bResult = false;
				Log.error("Unable to validate");

				return;
			}
		}
		catch(Exception D)
		{
			
			TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
			Log.error("Unable to validate");
			TestRunner.bResult = false;
		}
	}
	public static void verifyPresence(String object, String data)
	{
		try{
			if(driver.findElement(By.xpath(OR.getProperty(object))).isDisplayed())
			{
				TestRunner.bResult = true;
				return;
			}
			else
			{
				TestRunner.bResult = false;
				return;
			}
		}
		catch(Exception D)
		{
			TestRunner.imagePath =  ExtentManager.captureScreen(driver, System.getProperty("user.dir")+"\\Screenshots\\Failed\\");
			Log.error("Not able to verify the presence of the element");
			 TestRunner.bResult = false;
		}
	}
}



