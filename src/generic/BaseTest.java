package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest implements IAutoConst{
   public WebDriver driver;
   static
   {
	   System.setProperty(CHROME_KEY,CHROME_VALUE);
	   System.setProperty(GECKO_KEY, GECKO_VALUE);
   }
   @Parameters({"browser"})
   @BeforeMethod(alwaysRun=true)
   public void openApp(String browser)
   {   if(browser.equals("chrome"))
	   {
	   driver=new ChromeDriver();
	   }
       else
       {
       driver=new FirefoxDriver();	    
       }
       String url=AutomationLibrary.getProperty(SETTINGS_PATH, "URL");
	   driver.get(url);
	   String strITO = AutomationLibrary.getProperty(SETTINGS_PATH, "ITO");
	   Long ITO=Long.parseLong(strITO);
	   driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
   }
   @AfterMethod(alwaysRun=true)
   public void closeApp(ITestResult res)
   {   
	   String name=res.getName();
	   int status=res.getStatus();
	   if(status==2)
	   {
		   AutomationLibrary.getPhoto(driver, PHOTO_PATH, name);
		   Reporter.log("imagepath:"+PHOTO_PATH,true);
	   }
	   driver.quit();
   }
}
