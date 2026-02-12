package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
    public String GetPageTitle() {
        return driver.findElement(By.className("app_logo")).getText();
    }
    public boolean IsCartIconDisplayed() {
        return driver.findElement(By.id("shopping_cart_container")).isDisplayed();
    }
    public int GetProductsCount() {
        return driver.findElements(By.className("inventory_item")).size();
    }
}