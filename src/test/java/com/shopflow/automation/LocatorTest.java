package com.shopflow.automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocatorTest {

    @Test
    public void locatorPracticeTest() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8081");

            // 1. ID
            WebElement emailById =
                    driver.findElement(By.id("email"));

            // 2. CSS Selector
            WebElement emailByCss =
                    driver.findElement(By.cssSelector("#email"));

            // 3. XPath
            WebElement emailByXpath =
                    driver.findElement(
                            By.xpath("//input[@id='email']")
                    );

            // 4. Tag Name
            List<WebElement> inputs =
                    driver.findElements(By.tagName("input"));

            // 5. Class Name
            List<WebElement> products =
                    driver.findElements(By.className("product"));

            // 6. CSS class
            List<WebElement> productsByCss =
                    driver.findElements(By.cssSelector(".product"));

            System.out.println("Inputs: " + inputs.size());
            System.out.println("Products: " + products.size());
            System.out.println("Products by CSS: " + productsByCss.size());

            Assert.assertTrue(emailById.isDisplayed());
            Assert.assertTrue(emailByCss.isDisplayed());
            Assert.assertTrue(emailByXpath.isDisplayed());

            Assert.assertEquals(products.size(), productsByCss.size());

        } finally {
            driver.quit();
        }
    }
}