package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    // Locators
    private final By Username = By.id("user-name");
    private final By Password = By.id("password");
    private final By Login = By.id("login-button");
    private final By Error = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(Username).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(Password).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(Login).click();
    }

    public String getError() {
        return driver.findElement(Error).getText();
    }
}