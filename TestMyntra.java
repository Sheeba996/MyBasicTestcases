package workoutprograms;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestMyntra {

	public static void main(String[] args) throws InterruptedException
	
	{
		
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[text()='Women']")).perform();
		driver.findElementByXPath("//a[text()='Jackets & Coats']").click();
		
		String text = driver.findElementByClassName("title-count").getText();
		String textfinal=text.replaceAll("\\D","");
		int JacketandCoatCount = Integer.parseInt(textfinal);
		
		System.out.println("The count of Jacket and coat is"+ " " + JacketandCoatCount );
		
		String text2 = driver.findElementByClassName("categories-num").getText();
		String textfinal2=text2.replaceAll("\\D","");
		int JacketCount=Integer.parseInt(textfinal2);
		
		String text3=driver.findElementByXPath("(//*[@class='categories-num'])[1]").getText();
		String textfinal3=text3.replaceAll("\\D","");
	    int CoatCount = Integer.parseInt(textfinal3);
	    
	    int TotalJacketCountandCoatCount=JacketCount+CoatCount;
	    
	    System.out.println("The total count of jacket and coat is" + " " +TotalJacketCountandCoatCount);
	    
		
	    driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
	    driver.findElementByXPath("//*[@class='brand-more']").click();
		
	   driver.findElementByXPath("//*[@class='FilterDirectory-searchInput']").sendKeys("MANGO");
	   
	   WebDriverWait wait=new WebDriverWait(driver,30);
	   wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//label[text()='MANGO']//div[@class='common-checkboxIndicator'])[2]")));
	   driver.findElementByXPath("(//label[text()='MANGO']//div[@class='common-checkboxIndicator'])[2]").click();
	   
	   Thread.sleep(1000);
	   driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
	   
	   List<WebElement> LstMangoBrandCoats = driver.findElementsByXPath("//div[@class='product-productMetaInfo']//h3[text()='MANGO']");
	 
	   boolean flag=true;
	   for (int i = 0; i <LstMangoBrandCoats.size(); i++) 
	   {
		   
		   WebElement CoatEle = LstMangoBrandCoats.get(i);
		   String Coat=CoatEle.getText();
		  if(Coat.equalsIgnoreCase("mango"))
		  {
			  flag=true;
		  }
		  else
			  flag=false;
	   }
	   
	   if(flag==true)
		   System.out.println("All the coats are Mango Brand's");
	   
	   Actions moveToEle=new Actions(driver);
	   moveToEle.moveToElement(driver.findElementByXPath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']")).perform();
	   
	   driver.findElementByXPath("//ul[@class='sort-list']/li/label[text()='Better Discount']").click();
	   
	   Thread.sleep(1000);
	   List<WebElement> CoatList = driver.findElementsByXPath("//div[@class='product-price']/span//span[@class='product-discountedPrice']");
	   String strCoatPrice=CoatList.get(0).getText().toString();
	   
	   String FinalCoatPrice=strCoatPrice.replaceAll("\\D","");
	   int FirstCoatPrice=Integer.parseInt(FinalCoatPrice);
	   System.out.println("The Price of the First Coat is" + " " +FirstCoatPrice );
	   
	   WebElement FirstCoat = driver.findElementByXPath("(//div[@class='product-productMetaInfo'])[1]");
	   moveToEle.moveToElement(FirstCoat).build().perform();
	   
	   wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='wishlist now'])[1]")));
	   driver.findElementByXPath("(//span[text()='wishlist now'])[1]").click();
	   //driver.quit();
	   
	   
	   }

}
