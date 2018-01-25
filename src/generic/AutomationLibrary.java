package generic;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AutomationLibrary {
	public static String getPhoto(WebDriver driver,String folder,String name)
	{   TakesScreenshot t=(TakesScreenshot)driver;
	     File srcFile = t.getScreenshotAs(OutputType.FILE);
	     String dt=new Date().toString().replaceAll(":","-");
	     String path=folder+name+dt+".png";
	     File destFile=new File(path);
	     try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		 return path;
}
	public static String getProperty(String path,String key)
    {   String v=" ";
       try {
       
        Properties p=new Properties();
        p.load(new FileInputStream(path));
        v=p.getProperty(key);
       }
       catch(Exception e)
       {
      	 
       }
  	  return v;
    }
}
