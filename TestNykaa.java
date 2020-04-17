package workoutprograms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestNykaa {

	public static void main(String[] args) throws InterruptedException
	{
		//Step 1.Go to the https://nykaa.com/
		
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(700,TimeUnit.SECONDS);
		
		//Steps 2.MouseHover on Brands and MouseHover on popular
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//*[text()='brands']")).perform();
		
		action.moveToElement(driver.findElementByXPath("//*[text()='Popular']")).perform();
		
		
		//Step 3.CLick on L'oreal paris logo
		
		driver.findElementByXPath("(//li[@class='brand-logo menu-links']//img)[5]").click();
		
		Set<String> windowHandles = driver.getWindowHandles();
	    List<String> windowlist=new ArrayList<String>(windowHandles);
	    
	    //Step 4.Switch to a new Window and Extract the title of the browser window
	    WebDriver window = driver.switchTo().window(windowlist.get(1));
	    String ParisTitle = window.getTitle();
	    
	    
	    if(ParisTitle.contains("Buy L'Oreal Paris productsonline"))
	    {
	    System.out.println("The tile of the current window is " + " " + ParisTitle );
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    }
	    
	    
	    //Step 5.Click sort By and select customer top rated
	    driver.findElementByXPath("//div[@class='sort-btn clearfix']//span[@class='pull-left']").click();
	    //driver.findElementByXPath("//div[@class='sort-container show-sort']//span[text()='customer top rated']/following-sibling::input/following-sibling::div").click();

	    //driver.findElementByXPath("//div[@class='sort-btn clearfix']//span[text()='customer top rated']").click();
	    driver.findElementByXPath("(//div[@class='control control--radio text-capitalize'])[4]/span").click();
	    
	    //Step 6 on Category and Shampoo
		 Thread.sleep(2000);
		 action.moveToElement(driver.findElementByXPath("//div[text()='Category']")).click().perform();
		 WebDriverWait wait=new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//span)[1]")));
	     action.moveToElement(driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//span)[1]")).click().perform();
	     
	     
	     //Step 7 Checking whether the right filter is chosen
	     WebElement checkShampoofilterEle = driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//span)[1]");
	     String Shampoo = checkShampoofilterEle.getText();
	     if(Shampoo.contains(Shampoo))
	     {
	     System.out.println("Shampoo filter is applied successfully");
	     }
	   
	     else
		   System.out.println("Shampoo filter is not applied");
        
	    //Step 8 Click on L'oreal paris color protect shampoo
	    driver.findElementByXPath("//span[contains(text(),\"L'Oreal Paris Colour Protect Shampoo\")]").click();
	   
	    //Step 9 Switch to Next window and select size as 175 ml
	    
	    Set<String> CurrentWindow = driver.getWindowHandles();
	    List<String> Windowslist=new ArrayList<String>(CurrentWindow);
	    driver.switchTo().window(Windowslist.get(2));
	    driver.findElementByXPath("//span[text()='175ml']").click();
	    
	    //Step 10 Print the MRP of the product
	    driver.findElementByXPath("//div/button[text()='ADD TO BAG']").click();
	    Thread.sleep(2000);
	    
	    //Step 11 Add to Bag
	    driver.findElementByXPath("//div[@class='AddToBagbox']//div[@class='AddBagIcon']").click();
	    
	    //Step 12 Go to Shopping bag Step 13 Print the grand total Amount
	    String GrandTotalText = driver.findElementByXPath("//div[@class='table-row ']//div[@class='value medium-strong']").getText();
	    System.out.println("The Grand Total Amount: " + GrandTotalText);
	    
	    //Step 14 Click Proceed
	    driver.findElementByXPath("//div[@class='second-col']/button/span[text()='Proceed']").click();
	    
	    //Step 15 Click on Continue as Guest
	    driver.findElementByXPath("//div/button[text()='CONTINUE AS GUEST']").click();
	    
	    //Step 16 Print the warning message
	    String WarningMessage = driver.findElementByXPath("//div[@class='message']").getText();
	    System.out.println("The warning message displayed :" + WarningMessage);
	    
	    //Step 17 Close all the windows
	    
	    
	    driver.quit();   
	   }
	

}
