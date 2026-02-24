package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.InventoryPage;
import Pages.LoginPage;
import Utils.DataDriven;

public class InventoryTest extends BaseTest {

    @Test(priority = 4)
    public void verifyInventoryPageElements() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(DataDriven.jsonReader("ValidUsername"));
        loginPage.enterPassword(DataDriven.jsonReader("ValidPassword"));
        loginPage.clickLogin();

        InventoryPage inventoryPage = new InventoryPage(driver);

        Assert.assertEquals(inventoryPage.getPageTitle(), "Swag Labs",
                "Page title should be 'Swag Labs'");
        System.out.println("Page title verified");

        Assert.assertTrue(inventoryPage.isCartIconDisplayed(),
                "Cart icon should be displayed after login");
        System.out.println("Cart icon verified");

        Assert.assertEquals(inventoryPage.getProductsCount(), 6,
                "There should be 6 products displayed on inventory page");
        System.out.println("Products count verified");
    }
}