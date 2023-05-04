package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    private static final String YOUR_CART_XPATH = "//*[@id='shopify-section-cart-template']/div/div[1]/h1";

    private static final String QUANTITY_INPUT_XPATH = "//*[@id='updates_33399130177:f2470c135dd715ea0779ca27ccddfaf2']";
    //  private static final String UPDATE_BUTTON_CSS_SELECTOR = "#shopify-section-cart-template form input[type='submit'][name='update']";
    public static final String TOTAL_PRICE_XPATH = "//*[contains(@class, 'cart__total')]";
    public static final String CART_QUANTITY_XPATH = "//input[@type='number' and contains(@class, 'cart__quantity')]";

    private static final String UPDATE_BUTTON_CSS_SELECTOR = "input.btn.btn--secondary.cart__update.cart__update--large.small--hide";


    public boolean isCartPageLoaded() {
        waitForElementVisible(By.xpath(YOUR_CART_XPATH), 5); // Wait for 5 seconds
        return isElementVisible(By.xpath(YOUR_CART_XPATH));
    }

    public void setCartQuantity(int newQuantity) {
        WebElement quantityInput = findElementByXpath(QUANTITY_INPUT_XPATH);
        quantityInput.click(); // Click the input field to focus it
        quantityInput.clear(); // Clear the input field
        quantityInput.sendKeys(String.valueOf(newQuantity)); // Enter the new quantity
    }

    public void clickUpdateButton() {
        WebElement updateButton = findElementByCssSelector(UPDATE_BUTTON_CSS_SELECTOR);

        // Add a short delay before clicking the button
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the update button
        updateButton.click();

        // Wait for the cart total element to be visible
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'cart__total')]")));
    }




    public double getTotalPrice() {
        waitForElementVisible(By.xpath(TOTAL_PRICE_XPATH), 10); // Increase the wait time to 10 seconds
        String priceText = findElementByXpath(TOTAL_PRICE_XPATH).getText();
        return parsePrice(priceText);
    }

    private double parsePrice(String priceText) {
        String priceWithoutCurrency = priceText.replaceAll("[^0-9\\.]", "");
        return Double.parseDouble(priceWithoutCurrency);
    }

    public String getTotalPriceAsString() {
        WebElement totalPriceElement = findElementByXpath(TOTAL_PRICE_XPATH);
        return totalPriceElement.getText();
    }

}