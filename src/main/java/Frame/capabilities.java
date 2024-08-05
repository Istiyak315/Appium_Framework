package Frame;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class capabilities {
	public static String deviceName;
	public static String platformName;
	public static String appActivity;
	public static String appPackage;
	
	public AppiumDriverLocalService service;
	 //for starting the server
	 public AppiumDriverLocalService startServer()
	 {
	 boolean flag = checkifserverisRunning(4723);
	 if(!flag)
	 {
	 service = AppiumDriverLocalService.buildDefaultService();
	 service.start();
	 }
	 return service;
	 }
	 public static boolean checkifserverisRunning(int port)
	 {
	 boolean isServerRunning=false;
	 ServerSocket serversocket;
	 try{
	 serversocket = new ServerSocket(port);
	 serversocket.close();
	 }
	 catch(IOException e)
	 {
	 isServerRunning = true;
	 }
	 finally {
	 serversocket=null;
	 }
	 return isServerRunning;
	 }

	
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"/src/main/resources/Extension.bat");
		
		
	}
	public static AndroidDriver<AndroidElement> Capabalities(String deviceName, String platformName, String appActivity, String appPackage) throws IOException, InterruptedException {
		
		//there are two ways in which i can fectch my global.properties file 
		//1.file i/o
		//2.file reader
		
		FileReader gp=new FileReader(System.getProperty("user.dir")+"/src/main/java/global.properties");
		
		Properties gm=new Properties();
		gm.load(gp);
		
		
	
		 deviceName=gm.getProperty("deviceName");
		 platformName=gm.getProperty("platformName");
		 appActivity=gm.getProperty("appActivity");
		 appPackage=gm.getProperty("appPackage");
		
		 
		 if(deviceName.equals("My_emulator")) {
				startEmulator();
			}
		 
		DesiredCapabilities dc= new DesiredCapabilities();
		
		//i am using this dc object to give information about the emulator and the app that i want to open
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		// app activity  io.appium.android.apis.ApiDemos
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		dc.setCapability(MobileCapabilityType.NO_RESET, false);
		dc.setCapability("appium:chromeOptions", ImmutableMap.of("w3c",false));

		
		
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
		return driver;		
	}
}
