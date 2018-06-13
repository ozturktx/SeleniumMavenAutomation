package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		// Set up chrome driver path
		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\oozturk\\Documents\\SeleniumDependencies\\drivers\\chromedriver.exe");
      // open selenium webdriver
		WebDriver driver=new ChromeDriver();
		
		//fullscreen
		//driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		
		//set universal wait time in case webpage is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
//Step 1- Launch the browser and navigate to https://dice.com*/
		String url="https://dice.com";
		driver.get(url);
//Checking to see if the code worked and page is opened
		String actualTitle=driver.getTitle();
		String expectedTitle="Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle))
		{
			System.out.println("STEP PASS. Dice HomePage successfully loaded");
		}else {
			System.out.println("FAILED. Dice HomePage could not loaded");
			throw new RuntimeException("FAILED. Dice HomePage could not loaded");
		}
//Step2- 
		String keyword="java developer";
		driver.findElement(By.id("search-field-keyword")).clear();//Clear the place
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		//Thread.sleep(3000);
		
		String location="77095";
		driver.findElement(By.id("search-field-location")).clear(); // Clear before putting any info
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		String count=driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		//ensure count is more than 0
		int countResult=Integer.parseInt(count.replaceAll(",", ""));
		
		if(countResult>0)
			System.out.println("Step PASS: Keyword : " +keyword+"Search returned " +countResult+" results in " + location);
		else
			System.out.println("Step FAIL: Keyword : " +keyword+"Search returned " +countResult+" results in " + location);
		driver.close();
		
	}

}
