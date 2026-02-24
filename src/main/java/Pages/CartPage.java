package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;

    // Locators
    private final By cartItems = By.className("cart_item");
    private By removeButton(String productName) {
        return By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button");
    }

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isProductInCart(String productName) {
        return !driver.findElements(By.xpath("//div[text()='" + productName + "']")).isEmpty();
    }

    public void removeProduct(String productName) {
        driver.findElement(removeButton(productName)).click();
    }
}