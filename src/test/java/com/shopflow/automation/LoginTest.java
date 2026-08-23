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

public class LoginTest {

    @Test
    public void validLoginTest() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8081");

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(10)
                    );

            WebElement email =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("email")
                            )
                    );

            WebElement password =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("password")
                            )
                    );

            WebElement loginButton =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.id("loginButton")
                            )
                    );

            Assert.assertTrue(
                    email.isDisplayed(),
                    "Email field should be visible"
            );

            Assert.assertTrue(
                    password.isDisplayed(),
                    "Password field should be visible"
            );

            Assert.assertTrue(
                    loginButton.isEnabled(),
                    "Login button should be enabled"
            );

            email.sendKeys("testuser@shopflow.com");
            password.sendKeys("Test@123");

            loginButton.click();

            WebElement message =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("message")
                            )
                    );

            wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.id("message"),
                            "Login successful"
                    )
            );

            String actualMessage = message.getText();

            System.out.println(
                    "Login message: " + actualMessage
            );

            Assert.assertEquals(
                    actualMessage,
                    "Login successful",
                    "Login message is incorrect"
            );

        } finally {
            driver.quit();
        }
    }
}