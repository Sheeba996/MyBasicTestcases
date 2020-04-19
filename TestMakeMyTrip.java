package workoutprograms;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestMakeMyTrip {

	public static void main(String[] args) throws InterruptedException

	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		
		//Step 1.Go to the url https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Step 2 Click on the Hotels
		driver.findElementByXPath("//span[text()='Hotels']").click();

		//Step 3.Click the cuty as Goa and choose Goa,India
		driver.findElementByXPath("//div[contains(@class,'hsw_inputBox selectHtlCity')]").click();
		driver.findElementByXPath("//input[@class='react-autosuggest__input react-autosuggest__input--open']").sendKeys("Goa");
		driver.findElementByXPath("//div[@class='flexOne']//p[text()='Goa, India']").click();

		
		//Step 4.Enter the Check in Date as Current Month and Check out date as Start date+5
		 driver.findElementByXPath("//input[@id='checkin']").click();
		 String strCurrentMonth=LocalDate.now().toString().substring(5,7);
		 String Date=LocalDate.now().toString().substring(8,10);
		 int MonthInteger=Integer.parseInt(strCurrentMonth); 
		 int date=Integer.parseInt(Date);
		
		 String monthName=Month.of(MonthInteger).toString(); String
		 FinalMonthName=monthName.substring(0,1)+monthName.substring(1,monthName.length()).toLowerCase();
		 System.out.println(FinalMonthName);
		 
		 driver.findElementByXPath("//div[text()='"+FinalMonthName+"']/ancestor::div[@class='DayPicker-Month']//div[text()='"+date+"']").click();
		 
		 
		 int StartDate=Integer.parseInt((driver.findElementByXPath("//div[text()='"+FinalMonthName+"']/ancestor::div[@class='DayPicker-Month']//div[text()='"+date+"']")).getText());
		 System.out.println(StartDate);
		 
		 String EndDate=Integer.toString((StartDate+5));
		 driver.findElementByXPath("//div[text()='"+FinalMonthName+"']/ancestor::div[@class='DayPicker-Month']//div[text()='"+EndDate+"']").click();
		 
		 //Step 5 Click on Rooms and Guests and click on 2 adults and one children(Age 12) Click on Apply button
		 driver.findElementByXPath("//input[@id='guest']").click();
		 driver.findElementByXPath("(//ul/li[text()='2'])[1]").click();
		 driver.findElementByXPath("(//ul/li[text()='1'])[2]").click();
		 
		 WebElement dropdown = driver.findElementByXPath("//select[@class='ageSelectBox']");
		 Select Age=new Select(dropdown);
		 Age.selectByVisibleText("12");
		 driver.findElementByXPath("//button[@class='primaryBtn btnApply']").click();
		 
		 //Step 6 Click on Search Button
		 driver.findElementByXPath("//button[@class='primaryBtn font24 latoBold widgetSearchBtn']").click();
		 
		 //Step 7 Click on Locality as Baga 
		 WebDriverWait wait=new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']")));
		 driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		 
		 WebDriverWait Wait=new WebDriverWait(driver,10);
		 Wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//label[text()='Baga']")));
		 driver.findElementByXPath("//label[text()='Baga']").click();
		 
		 
		 //Step 8 Select 5 star in Star category
		 Thread.sleep(500);
		 driver.findElementByXPath("//label[text()='5 Star']").click();
		 
		 //Step 9 Click on first resulting hotel name and go to the new window
		 WebDriverWait Wait1=new WebDriverWait(driver,10);
		 Wait1.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='flexOne appendLeft20']")));
		 driver.findElementByXPath("//div[@class='flexOne appendLeft20']").click();
		 
		 Set<String> windowHandles = driver.getWindowHandles();
		 List<String> windowLists=new ArrayList<String>(windowHandles);
		 driver.switchTo().window(windowLists.get(1));
		
		 
		//Step 10 Print the Hotel Name
		Thread.sleep(1000);
		System.out.println(driver.findElementByXPath("//h1[@id='detpg_hotel_name']").getText());
		
		//Step 11 Click on More Options link and Select 3 month plan and close		
		driver.findElementByXPath("//div[@class='emiTooltip blackText']//span[text()='MORE OPTIONS']").click();
         
		List<WebElement> tRow = driver.findElementsByTagName("tr");
		
		for (int i = 0; i < tRow.size(); i++)
		{
			if(i==1)
			{
			WebElement eachRow= tRow.get(i);
			List<WebElement> eachColumn = eachRow.findElements(By.tagName("td"));
			if(eachColumn.get(0).getText().contains("3"))
			{
				WebElement planOption = eachColumn.get(5);
				planOption.click();
				break;
			
			}
			}
			
		}
		boolean planOptionSelected= driver.findElementByXPath("//span[text()=' SELECTED']").isDisplayed();
        if(planOptionSelected==true)
		{
		driver.findElementByXPath("//span[@class='close']").click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		}
        
        //Step 12 Close the browser
        driver.quit();  
        	 }

}
