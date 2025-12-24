package testcases;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IciciPru {
  WebDriver driver;	
  ExtentReports extent;
  ExtentSparkReporter reporter;
  ExtentTest test;
  
  
  @BeforeClass
  @Parameters("browser")
  public void setup(String browser)
  {
	  String reportPath = "F:\\YouTube\\Videos\\10_SecurityTesting\\MyTestRepo\\test-output\\extentReport\\"+ "ExtentReport" + ".html";
      ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
      extent = new ExtentReports();
      extent.attachReporter(sparkReporter);
	  if(browser.equalsIgnoreCase("chrome"))
	  {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();  
		  
	  }
	  else if(browser.equalsIgnoreCase("edge"))
	  {
		  WebDriverManager.edgedriver().setup();
		  driver = new EdgeDriver();
	  }
	  
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
	  driver.get("https://www.iciciprulife.com/");
	  
  }
  
  @BeforeMethod
  public void createTest(Method method) 
	{
		test = extent.createTest(method.getName());
	}
  @Test(priority =1)
  public void validateHomePageTitle() {
	  
	  String actualPageTitle = driver.getTitle();
	  String expectedPageTitle = "QALife Insurance - ICICI Prudential Life Insurance India 2025";
	  Assert.assertEquals(actualPageTitle, expectedPageTitle);
	  	    
  }
  
  @Test(priority =2)
  public void validaeTermInsuranceMenu() {
	  
	driver.findElement(By.xpath("//a//span[text()='Term Insurance']")).click();
	List<WebElement> termmenulist =  driver.findElements(By.xpath("//a//span[text()='Term Insurance']//following::ul[1]//li//a"));
	for(WebElement termoption : termmenulist)
	{
		String suboptiontext = termoption.getText();
		System.out.println(suboptiontext);
		Assert.assertTrue(suboptiontext.contains("TERM INSURANCE"));
	}
	
	
  }
  
  @Test(priority =3)
  public void validaeNRIPlansMenu() {
	  
	  driver.findElement(By.xpath("//a//span[text()='NRI Plans']")).click();
	  List<WebElement> NRImenulist =  driver.findElements(By.xpath("//a//span[text()='NRI Plans']//following::ul[1]//li//a"));
	  for(WebElement nrioption : NRImenulist)
		{
			String suboptiontext = nrioption.getText();
			if(suboptiontext.equals("CONTACT US"))
			{
				continue;
			}
			System.out.println(suboptiontext);
			Assert.assertTrue(suboptiontext.contains("NRI"));
		}
  
  
  }
  
  @Test(priority =4)
  public void validaeLogin() {
	  
	  driver.findElement(By.id("login-toggle")).click();
	  driver.findElement(By.linkText("Individual")).click();
	  String expectedURL = "QAhttps://customer.iciciprulife.com/csr/cmmn-home.htm?execution=e1s1";
	  String actualURL = driver.getCurrentUrl();
	  Assert.assertEquals(actualURL, expectedURL);
	  
  }
  @AfterMethod
  public void tearDown(ITestResult result) 
	{
		if (result.getStatus() == ITestResult.SUCCESS) 
      {
          
          test.log(Status.PASS, result.getName() + " is passed");
          
      }
		
      if (ITestResult.FAILURE == result.getStatus()) 
      {
          
          test.log(Status.FAIL, result.getName() + " is failed");
          
      }
  }
  
  
  @AfterClass
  public void teardown()
  {	  
	  driver.quit();
	  extent.flush();
  }
  
    
}
