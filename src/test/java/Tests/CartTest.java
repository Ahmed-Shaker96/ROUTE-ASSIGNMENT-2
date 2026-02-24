package Tests;

import Base.BaseTest;
import Pages.InventoryPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Pages.CartPage;
import Utils.DataDriven;

import java.util.Objects;

public class CartTest extends BaseTest {

    @Test(priority = 5)
    public void verifySocialLinks() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("ValidUsername"));
        loginPage.enterPassword(DataDriven.jsonReader("ValidPassword"));
        loginPage.clickLogin();

        String mainWindow = driver.getWindowHandle();

        // LinkedIn
        driver.findElement(By.cssSelector("a[href*='linkedin']")).click();
        switchToNewTab(mainWindow);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("linkedin"));
        System.out.println("LinkedIn link verified successfully");
        driver.close();
        driver.switchTo().window(mainWindow);

        // Facebook
        driver.findElement(By.cssSelector("a[href*='facebook']")).click();
        switchToNewTab(mainWindow);
        Assert.assertTrue(driver.getCurrentUrl().contains("facebook"));
        System.out.println("Facebook link verified successfully");
        driver.close();
        driver.switchTo().window(mainWindow);

        // Twitter (X)
        driver.findElement(By.cssSelector("a[href*='twitter']")).click();
        switchToNewTab(mainWindow);
        Assert.assertTrue(driver.getCurrentUrl().contains("x.com"));
        System.out.println("Twitter (X) link verified successfully");
        driver.close();
        driver.switchTo().window(mainWindow);
    }


    @Test(priority = 6)
    public void verifyCartIsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("ValidUsername"));
        loginPage.enterPassword(DataDriven.jsonReader("ValidPassword"));
        loginPage.clickLogin();

        driver.findElement(By.id("shopping_cart_container")).click();
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(cartPage.getCartItemsCount(), 0, "Cart should be empty");
        System.out.println("verifyCartIsEmpty executed");

    }

    @Test(priority = 7)
    public void addThreeSpecificProducts() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("ValidUsername"));
        loginPage.enterPassword(DataDriven.jsonReader("ValidPassword"));
        loginPage.clickLogin();

        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.addProduct("Sauce Labs Backpack");
        inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProduct("Sauce Labs Onesie");

        driver.findElement(By.id("shopping_cart_container")).click();
        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bolt T-Shirt"));
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Onesie"));
        System.out.println("addThreeSpecificProducts executed");

    }

    @Test(priority = 8)
    public void removeOneProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("ValidUsername"));
        loginPage.enterPassword(DataDriven.jsonReader("ValidPassword"));
        loginPage.clickLogin();

        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.addProduct("Sauce Labs Backpack");
        inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProduct("Sauce Labs Onesie");

        driver.findElement(By.id("shopping_cart_container")).click();
        CartPage cartPage = new CartPage(driver);

        cartPage.removeProduct("Sauce Labs Bolt T-Shirt");

        driver.findElement(By.id("continue-shopping")).click();

        Assert.assertEquals(inventoryPage.getProductButtonText("Sauce Labs Bolt T-Shirt"), "Add to cart");
        Assert.assertEquals(inventoryPage.getProductButtonText("Sauce Labs Backpack"), "Remove");
        Assert.assertEquals(inventoryPage.getProductButtonText("Sauce Labs Onesie"), "Remove");
        System.out.println("removeOneProduct executed");

    }

    private void switchToNewTab(String mainWindow) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
}