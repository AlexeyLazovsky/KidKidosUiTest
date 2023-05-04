package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BooksResultsPage extends BasePage {
    private static final String BOOKS_RESULTS_CONTAINER = "//div[contains(@class, 'grid-uniform')]";
    private static final String UNIQUE_ELEMENT_XPATH = "//h1[contains(text(), 'English Only')]";
    private static final String FIRST_BOOK_XPATH = "//*[@id='Collection']/div/div[1]/div/a/div[1]";

    private WebDriver driver;

    public BooksResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isLoaded() {
        return elementExists(BOOKS_RESULTS_CONTAINER);
    }

    public boolean isEnglishOnlyBooksPageLoaded() {
        return isElementVisible(By.xpath(UNIQUE_ELEMENT_XPATH));
    }

    public BookDetailsPage clickOnFirstBook() {
        clickElementByXpath(FIRST_BOOK_XPATH);
        return new BookDetailsPage();
}

}