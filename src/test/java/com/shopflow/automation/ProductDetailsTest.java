package com.shopflow.automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductDetailsTest {

    @Test
    public void productDetailsTest() {

        WebDriver driver = new ChromeDriver();

        try {

            driver.get("http://localhost:8081");

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(10)
                    );

            WebElement search =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("search")
                            )
                    );

            WebElement searchButton =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.id("searchButton")
                            )
                    );

            search.sendKeys("laptop");

            searchButton.click();

            WebElement product =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.cssSelector(".product")
                            )
                    );

            WebElement productName =
                    product.findElement(
                            By.cssSelector(".product-name")
                    );

            String expectedProductName =
                    productName.getText();

            WebElement detailsButton =
                    product.findElement(
                            By.cssSelector(
                                    ".product-details-button"
                            )
                    );

            detailsButton.click();

            WebElement details =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("productDetails")
                            )
                    );

            Assert.assertTrue(
                    details.isDisplayed(),
                    "Product details should be visible"
            );

            WebElement detailName =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("detailName")
                            )
                    );

            Assert.assertEquals(
                    detailName.getText(),
                    expectedProductName,
                    "Product name is incorrect"
            );

            WebElement detailDescription =
                    driver.findElement(
                            By.id("detailDescription")
                    );

            WebElement detailPrice =
                    driver.findElement(
                            By.id("detailPrice")
                    );

            WebElement detailStock =
                    driver.findElement(
                            By.id("detailStock")
                    );

            Assert.assertFalse(
                    detailDescription.getText().isEmpty(),
                    "Product description should not be empty"
            );

            Assert.assertFalse(
                    detailPrice.getText().isEmpty(),
                    "Product price should not be empty"
            );

            Assert.assertFalse(
                    detailStock.getText().isEmpty(),
                    "Product stock should not be empty"
            );

        } finally {

            driver.quit();
        }
    }
}