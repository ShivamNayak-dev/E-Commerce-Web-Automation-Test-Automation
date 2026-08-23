package com.shopflow.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

    @Test
    public void openShopFlowTest() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8081");

            String title = driver.getTitle();
            String url = driver.getCurrentUrl();

            System.out.println("Page Title: " + title);
            System.out.println("Current URL: " + url);

            Assert.assertEquals(title, "ShopFlow");
            Assert.assertTrue(url.startsWith("http://localhost:8081"));

        } finally {
            driver.quit();
        }
    }
}