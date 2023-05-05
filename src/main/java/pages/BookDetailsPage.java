package pages;
// test github update

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookDetailsPage extends BasePage {
    private static final String BOOK_DETAILS_CONTAINER = "//div[contains(@class, 'product-single')]";
    private static final String BOOK_TITLE_XPATH = "//*[@id='ProductSection-product-template']/div/div[2]/div/h1";

    private static final String FORMAT_DROPDOWN_XPATH = "//*[@id='SingleOptionSelector-0']";
    private static final String HARDCOVER_OPTION_XPATH = "//*[@id='SingleOptionSelector-0']/option[2]";

    private static final String BOOK_QUANTITY_INPUT_XPATH = "//*[@id='Quantity']";
    private static final String ADD_TO_CART_BUTTON_XPATH = "//*[@id='AddToCartText-product-template']";

    private WebDriver driver;

    public BookDetailsPage() {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean isLoaded() {
        return elementExists(BOOK_DETAILS_CONTAINER);
    }

    public String getBookTitle() {
        return findElementByXpath(BOOK_TITLE_XPATH).getText();
    }

    public void selectHardcoverFormat() {
        clickElementByXpath(FORMAT_DROPDOWN_XPATH);
        clickElementByXpath(HARDCOVER_OPTION_XPATH);
    }

    public boolean isHardcoverFormatSelected() {
        WebElement formatDropdown = findElementByXpath(FORMAT_DROPDOWN_XPATH);
        String selectedOptionValue = new Select(formatDropdown).getFirstSelectedOption().getAttribute("value");
        return selectedOptionValue.equalsIgnoreCase("Hardcover");
    }

    public void setBookQuantity(int quantity) {
        WebElement quantityInput = findElementByXpath(BOOK_QUANTITY_INPUT_XPATH);
        quantityInput.clear();
        quantityInput.sendKeys(Integer.toString(quantity));
    }

    public int getBookQuantity() {
        WebElement quantityInput = findElementByXpath(BOOK_QUANTITY_INPUT_XPATH);
        return Integer.parseInt(quantityInput.getAttribute("value"));
    }

    public CartPage clickAddToCartButton() {
        waitForElementVisible(By.xpath(ADD_TO_CART_BUTTON_XPATH), 10);
        clickElementByXpath(ADD_TO_CART_BUTTON_XPATH);
        return new CartPage();
    }
}