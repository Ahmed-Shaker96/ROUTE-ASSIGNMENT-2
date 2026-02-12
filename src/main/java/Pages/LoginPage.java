package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterUsername(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }
    public void clickLogin() {
        driver.findElement(By.id("login-button")).click();
    }
    public String getErrorMessage() {
        return driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
    }
}