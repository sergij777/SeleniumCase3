package org.mycomp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.mycomp.model.Good;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parser {
	private static final String SEPARATOR = System.getProperty("file.separator");
	 
	 private static final String USER_DIR = System.getProperty("user.dir");
	 
	 private static final String DRIVER_PATH = USER_DIR + SEPARATOR + "lib" + SEPARATOR + "chromedriver.exe";
	 	 
	 private static final String BASE_URL = "https://rozetka.com.ua/";

	 public static WebDriver getWebDriver() {		 	  
		 System.setProperty("webdriver.chrome.driver", DRIVER_PATH);	  
		 WebDriver driver = new ChromeDriver();	  	  
		 return driver;
	 }
	 
	 
	 
	 public List<Good> selectListGoodsByPrice(String nameOfType, String nameOfCategory, String nameOfSubcategory, int pageCount) {
		 List<Good> goodsList = new ArrayList();
		 
		 WebDriver driver = getWebDriver();		  
		 Timer.waitSeconds(5);
		 driver.get(BASE_URL);
		 Timer.waitSeconds(5);
		 
		 WebElement type = driver.findElement(By.linkText(nameOfType)); 
		 String typeLink = type.getAttribute("href");
		 driver.get(typeLink);		 
		 Timer.waitSeconds(5);
		 
		 WebElement category = driver.findElement(By.linkText(nameOfCategory)); 
		 String categoryLink = category.getAttribute("href");
		 driver.get(categoryLink);
		 Timer.waitSeconds(5);
		 
		 WebElement subcategory = driver.findElement(By.linkText(nameOfSubcategory));
		 String subcategoryLink = subcategory.getAttribute("href");		 

		 //Page navigator		 
		 int pageNumber = 1;
		 String linkNextPage = null;
		 
		 while (pageNumber <= pageCount) {			 
			 if (pageNumber == 1) {
				 driver.get(subcategoryLink);
			 }
			 else driver.get(linkNextPage);
		 
			 Timer.waitSeconds(5);
		 
			 WebElement goodsBlock = driver.findElement(By.id("catalog_goods_block"));	
			 List<WebElement> listGoods = goodsBlock.findElements(By.className("g-i-tile-i-box-desc"));
		 
			 for (WebElement goodParameters : listGoods) {			
				 WebElement priceElement = goodParameters.findElement(By.className("g-price-uah"));
				 String goodPriceText = priceElement.getText();			            
				 goodPriceText = goodPriceText.replaceAll("\\D+", "");			
				 int goodPrice = Integer.parseInt(goodPriceText);
				 
				 if (goodPrice >= 3000 && goodPrice <= 6000) {			
					 WebElement goodNameBlock = goodParameters.findElement(By.className("g-i-tile-i-title"));
					 WebElement tagLink = goodNameBlock.findElement(By.tagName("a"));
					 String goodName = tagLink.getText();	
					 Good good = new Good();
					 good.setGoodName(goodName);
					 good.setGoodPrice(goodPrice);
					 goodsList.add(good);			
				 }
			 }
			 
			 linkNextPage = null;
			 pageNumber++;
			 String idNextPage = "page" + pageNumber;		 
			 WebElement nextPage = driver.findElement(By.id(idNextPage));
			 WebElement buttonNextPage = nextPage.findElement(By.tagName("a"));
			 linkNextPage = buttonNextPage.getAttribute("href");
		 		 
			 if (linkNextPage == null) {
				 System.out.println("Page " + idNextPage + " is not found");
				 break;
			 }
		 }		 
		 driver.quit();		 		 
		 return goodsList;
	 }
	 
	 public List<Good> selectListGoodsByTopOfSale(String nameOfType, String nameOfCategory, String nameOfSubcategory, int pageCount) {
		 List<Good> goodsList = new ArrayList();
		 
		 WebDriver driver = getWebDriver();		  
		 Timer.waitSeconds(5);
		 driver.get(BASE_URL);
		 Timer.waitSeconds(5);
		 
		 WebElement type = driver.findElement(By.linkText(nameOfType)); 
		 String typeLink = type.getAttribute("href");
		 driver.get(typeLink);		 
		 Timer.waitSeconds(5);
		 
		 WebElement category = driver.findElement(By.linkText(nameOfCategory)); 
		 String categoryLink = category.getAttribute("href");
		 driver.get(categoryLink);
		 Timer.waitSeconds(5);
		 
		 WebElement subcategory = driver.findElement(By.linkText(nameOfSubcategory));
		 String subcategoryLink = subcategory.getAttribute("href");		 

		 //Page navigator		 
		 int pageNumber = 1;
		 String linkNextPage = null;
		 
		 while (pageNumber <= pageCount) {			 
			 if (pageNumber == 1) {
				 driver.get(subcategoryLink);
			 }
			 else driver.get(linkNextPage);
		 
			 Timer.waitSeconds(5);
		 
			 WebElement goodsBlock = driver.findElement(By.id("catalog_goods_block"));	
			 List<WebElement> listGoods = goodsBlock.findElements(By.className("g-i-tile-i-box-desc"));
		 
			 for (WebElement goodParameters : listGoods) {
				 List<WebElement> listTags = goodParameters.findElements(By.className("g-tag-icon-small-popularity"));
				 if (listTags.size() == 1) {				 
					 WebElement priceElement = goodParameters.findElement(By.className("g-price-uah"));
					 String goodPriceText = priceElement.getText();			            
					 goodPriceText = goodPriceText.replaceAll("\\D+", "");			
					 int goodPrice = Integer.parseInt(goodPriceText);				 			
					 WebElement goodNameBlock = goodParameters.findElement(By.className("g-i-tile-i-title"));
					 WebElement tagLink = goodNameBlock.findElement(By.tagName("a"));
					 String goodName = tagLink.getText();					 
					 Good good = new Good();
					 good.setGoodName(goodName);
					 good.setGoodPrice(goodPrice);
					 goodsList.add(good);			 
				 }
			 }
			 
			 linkNextPage = null;
			 pageNumber++;
			 String idNextPage = "page" + pageNumber;		 
			 WebElement nextPage = driver.findElement(By.id(idNextPage));
			 WebElement buttonNextPage = nextPage.findElement(By.tagName("a"));
			 linkNextPage = buttonNextPage.getAttribute("href");
		 		 
			 if (linkNextPage == null) {
				 System.out.println("Page " + idNextPage + " is not found");
				 break;
			 }
		 }		 
		 driver.quit();		 		 
		 return goodsList;
	 }
	 
	 public List<Good> sorterGoodsByPrice(List<Good> goods) {		 
		 int listSize = goods.size();
			for (int i = 0; i < listSize - 1; i++) {	
				boolean sign = true;	
				for (int j = 0; j < listSize - 1 - i; j++) {
					if (goods.get(j).getGoodPrice() < goods.get(j+1).getGoodPrice()) {
						Good goodTemp = new Good();
						try {
							goodTemp = (goods.get(j)).clone();
						} catch (CloneNotSupportedException e) {							
							e.printStackTrace();
						}
						goods.set(j, goods.get(j+1));
						goods.set(j+1, goodTemp);						
						sign = false;
					}
				}
			if (sign) break;
			}		 
		 return goods;
	 }
}
