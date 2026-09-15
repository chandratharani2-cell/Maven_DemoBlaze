package com.runner;

import com.base.Base_Class;
import com.utility.ReadExcelData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcelTestRunner extends Base_Class {
    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.youtube.com/");
        Thread.sleep(3000);
        String searchText= ReadExcelData.getParticularData(6,0);
        driver.findElement(By.xpath("//input[@name='search_query']")).sendKeys(searchText);
        driver.findElement(By.xpath("//button[@title='Search']")).click();
        Thread.sleep(3000);
        screenShot("searchjava");
    }
}
