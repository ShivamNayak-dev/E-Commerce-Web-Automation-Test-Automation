package com.shopflow.automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest {

    @Test
public void productSearchTest() {

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

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".product")
                )
        );

        List<WebElement> products =
                driver.findElements(
                        By.cssSelector(".product")
                );

        Assert.assertTrue(
                products.size() > 0,
                "Search should return at least one product"
        );

        System.out.println(
                "Search results: " + products.size()
        );

        for (int i = 0; i < products.size(); i++) {

            List<WebElement> currentProducts =
                    driver.findElements(
                            By.cssSelector(".product")
                    );

            String productText =
                    currentProducts.get(i).getText();

            System.out.println(
                    "Product: " + productText
            );

            Assert.assertTrue(
                    productText.toLowerCase()
                            .contains("laptop"),
                    "Search result does not match keyword"
            );
        }

    } finally {

        driver.quit();
    }
}
    



@Test
public void noSearchResultsTest() {

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

        search.sendKeys("xyzproductdoesnotexist");

        searchButton.click();

        wait.until(
                ExpectedConditions.attributeToBe(
                        By.id("products"),
                        "innerHTML",
                        ""
                )
        );

        List<WebElement> products =
                driver.findElements(
                        By.cssSelector(".product")
                );

        Assert.assertEquals(
                products.size(),
                0,
                "Expected no products for invalid search"
        );

    } finally {

        driver.quit();
    }
}
}