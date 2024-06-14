package se.ifmo.ru.web.lab4.pointchecker;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.UUID;

@SpringBootTest
class SeleniumTest {
    private static String email = UUID.randomUUID() + "@gmail.com";
//    private static String email = "6641c3bf-8579-4f14-96e6-7da71bdc0be0@gmail.com";
    private static String password = "123456789";

    @Test
    @Order(1)
    void registration() {
        System.out.println(email);
        System.setProperty("webdriver.chrome.driver", "/Users/neonik/IdeaProjects/selenium-test/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000");

        WebElement emailField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[1]"));
        WebElement passwordField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[2]"));
        WebElement registerButton = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/div[1]/button[1]"));
        new Actions(driver)
                .click(emailField)
                .sendKeys(email)
                .click(passwordField)
                .sendKeys(password)
                .click(registerButton)
                .perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By submitButton = getLocatorByXpath("/html/body/div/div[2]/form/div[4]/button");
        wait.until(ExpectedConditions.presenceOfElementLocated(submitButton));
    }

    @Test
    @Order(2)
    void login() {
        System.setProperty("webdriver.chrome.driver", "/Users/neonik/IdeaProjects/selenium-test/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000");

        WebElement emailField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[1]"));
        WebElement passwordField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[2]"));
        WebElement loginButton = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/div[1]/button[2]"));
        new Actions(driver)
                .click(emailField)
                .sendKeys(email)
                .click(passwordField)
                .sendKeys(password)
                .click(loginButton)
                .perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By submitButton = getLocatorByXpath("/html/body/div/div[2]/form/div[4]/button");
        wait.until(ExpectedConditions.presenceOfElementLocated(submitButton));
    }

    @Test
    @Order(3)
    void logout() {
        System.setProperty("webdriver.chrome.driver", "/Users/neonik/IdeaProjects/selenium-test/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000");

        WebElement emailField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[1]"));
        WebElement passwordField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[2]"));
        WebElement loginButton = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/div[1]/button[2]"));
        new Actions(driver)
                .click(emailField)
                .sendKeys(email)
                .click(passwordField)
                .sendKeys(password)
                .click(loginButton)
                .perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By submitButton = getLocatorByXpath("/html/body/div/div[1]/div/button");
        wait.until(ExpectedConditions.presenceOfElementLocated(submitButton));
        new Actions(driver).
                click(driver.findElement(submitButton))
                .perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(getLocatorByXpath("/html/body/div/div[2]/form/div[1]/button[2]")));
    }

    @Test
    @Order(4)
    void submitSimplePoint() {
        System.setProperty("webdriver.chrome.driver", "/Users/neonik/IdeaProjects/selenium-test/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000");

        WebElement emailField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[1]"));
        WebElement passwordField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[2]"));
        WebElement loginButton = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/div[1]/button[2]"));
        new Actions(driver)
                .click(emailField)
                .sendKeys(email)
                .click(passwordField)
                .sendKeys(password)
                .click(loginButton)
                .perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By submitButton = getLocatorByXpath("/html/body/div/div[2]/form/div[4]/button");
        wait.until(ExpectedConditions.presenceOfElementLocated(submitButton));
        new Actions(driver)
                .click(driver.findElement(submitButton))
                .perform();
    }

    @Test
    void submitWrongPoint() {
        System.setProperty("webdriver.chrome.driver", "/Users/neonik/IdeaProjects/selenium-test/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000");

        WebElement emailField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[1]"));
        WebElement passwordField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/input[2]"));
        WebElement registerButton = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/div[1]/button[1]"));
        new Actions(driver)
                .click(emailField)
                .sendKeys(UUID.randomUUID() + "@gmail.com")
                .click(passwordField)
                .sendKeys(password)
                .click(registerButton)
                .perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By submitButton = getLocatorByXpath("/html/body/div/div[2]/form/div[4]/button");

        wait.until(ExpectedConditions.presenceOfElementLocated(submitButton));
        WebElement yField = driver.findElement(getLocatorByXpath("/html/body/div/div[2]/form/div[2]/input"));
        new Actions(driver)
                .click(yField)
                .sendKeys("123")
                .click(driver.findElement(submitButton))
                .perform();
    }

    private By.ByXPath getLocatorByXpath(String xpath) {
        return new By.ByXPath(xpath);
    }
}
