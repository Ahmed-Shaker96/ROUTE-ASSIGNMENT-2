package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Utils.DataDriven;

import java.util.Objects;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void verifySuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("ValidUsername"));
        loginPage.enterPassword(DataDriven.jsonReader("ValidPassword"));
        loginPage.clickLogin();

        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("inventory.html"),
                "User should be redirected to inventory page after successful login");
        System.out.println("verifySuccessfulLogin executed");
    }

    @Test(priority = 2)
    public void verifyInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("InvalidUsername"));
        loginPage.enterPassword(DataDriven.jsonReader("InvalidPassword"));
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getError().contains("Username and password do not match"),
                "Error message should indicate invalid login attempt");
        System.out.println("verifyInvalidLogin executed");
    }

    @Test(priority = 3)
    public void verifyLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("ValidUsername"));
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getError().contains("Password is required"),
                "Error message should indicate missing password");
        System.out.println("verifyLoginWithoutPassword executed");
    }
}