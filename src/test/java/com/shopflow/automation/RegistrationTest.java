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

public class RegistrationTest {

    @Test
    public void validRegistrationTest() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8081");

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(10)
                    );

            WebElement name =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("registerName")
                            )
                    );

            WebElement email =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("registerEmail")
                            )
                    );

            WebElement password =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("registerPassword")
                            )
                    );

            WebElement registerButton =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.id("registerButton")
                            )
                    );

            name.sendKeys("Selenium User");
            email.sendKeys("selenium_" + System.currentTimeMillis()
                    + "@example.com");
            password.sendKeys("Test@123");

            registerButton.click();

            WebElement message =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("message")
                            )
                    );

            wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.id("message"),
                            "Registration successful"
                    )
            );

            Assert.assertEquals(
                    message.getText(),
                    "Registration successful"
            );

        } finally {
            driver.quit();
        }
    }


    @Test
public void invalidRegistrationTest() {

    WebDriver driver = new ChromeDriver();

    try {
        driver.get("http://localhost:8081");

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );

        WebElement name =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.id("registerName")
                        )
                );

        WebElement email =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.id("registerEmail")
                        )
                );

        WebElement password =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.id("registerPassword")
                        )
                );

        WebElement registerButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.id("registerButton")
                        )
                );

        name.sendKeys("Duplicate User");
        email.sendKeys("shivamtest12345@example.com");
        password.sendKeys("Test@123");

        registerButton.click();

        WebElement message =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.id("message")
                        )
                );

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.id("message"),
                        "Email already registered"
                )
        );

        Assert.assertEquals(
                message.getText(),
                "Email already registered"
        );

    } finally {
        driver.quit();
    }
}
}