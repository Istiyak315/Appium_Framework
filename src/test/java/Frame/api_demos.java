package Frame;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.aspectj.weaver.loadtime.definition.Definition.ConcreteAspect;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class api_demos extends capabilities{
	
	
AndroidDriver<AndroidElement> driver;


ExtentHtmlReporter htmlreport;
ExtentReports extnt;
ExtentTest test;
    
	
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		service=startServer();
		driver= Capabalities(deviceName, platformName, appActivity, appPackage);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		 htmlreport=new ExtentHtmlReporter("extent.html");
		 extnt=new ExtentReports();
		extnt.attachReporter(htmlreport);
		
		
		
		
	}
	
	@Test
	public void test1() {
		
		 test=extnt.createTest("html Reports");
		System.out.println("API demos Open");
		test.info("printing in console");
		
		service.stop();
		test.info("server stop");
		
		extnt.flush();
	}

	

}
