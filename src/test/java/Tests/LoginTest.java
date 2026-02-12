package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Utils.DataDriven;

public class LoginTest extends BaseTest {

    @Test (priority = 1)

    public void verifySuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("ValidUsername"));
        loginPage.enterPassword(DataDriven.jsonReader("ValidPassword"));
        loginPage.clickLogin();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "User should be redirected to inventory page");
        System.out.println("VerifySuccessfulLogin");
    }

    @Test (priority = 2)

    public void verifyInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("InvalidUsername"));
        loginPage.enterPassword(DataDriven.jsonReader("InvalidPassword"));
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message should indicate invalid login");
        System.out.println("VerifyInvalidLogin");

    }

    @Test (priority = 3)

    public void VerifyLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("ValidUsername"));
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"),
                "Error message should indicate missing password");
        System.out.println("VerifyLoginWithoutPassword");
    }
}