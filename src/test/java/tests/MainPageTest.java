package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends UseCaseBase {
    private static MainPage mainPage;
    private static final Logger logger = LogManager.getLogger(MainPageTest.class);

    @BeforeAll
    public static void classSetup() {
        mainPage = new MainPage();
    }

    @BeforeEach
    public void beforeTest() {
        mainPage.navigateToMainPage();
    }

    @Test
    public void mainPageLoadTest() {
        logger.info("Main Page load test");
        Boolean success = mainPage.isLogoVisible();
        mainPage.takeScreenShot("MainPageTest");
        assertTrue(success);
    }

    @Test
    public void openContactUsPage() {
        ContactUsPage contactUsPage = mainPage.openContactUsTab();
        boolean isLoaded = contactUsPage.isPageTitleVisible();
        assertTrue(isLoaded);
    }

    @Test
    public void scenario2Test() {
        mainPage.navigateToMainPage();
        BooksResultsPage booksResultsPage = mainPage.selectEnglishOnlyOption();
        assertTrue(booksResultsPage.isEnglishOnlyBooksPageLoaded(), "English Only books page should be loaded");
        BookDetailsPage bookDetailsPage = booksResultsPage.clickOnFirstBook();
        assertTrue(bookDetailsPage.isLoaded(), "Book details page should be loaded");
    }

    @Test
    public void changeBookFormatTest() {
        mainPage.navigateToMainPage();
        BooksResultsPage booksResultsPage = mainPage.selectEnglishOnlyOption();
        BookDetailsPage bookDetailsPage = booksResultsPage.clickOnFirstBook();
        bookDetailsPage.selectHardcoverFormat();
        assertTrue(bookDetailsPage.isHardcoverFormatSelected(), "Hardcover format should be selected");
        bookDetailsPage.setBookQuantity(5);
        int currentQuantity = bookDetailsPage.getBookQuantity();
        assertEquals(5, currentQuantity, "Quantity should be set to 5");

        CartPage cartPage = bookDetailsPage.clickAddToCartButton();
        assertTrue(cartPage.isCartPageLoaded(), "Cart page should be opened");

        // Update the cart quantity and verify the expected price
        double singleBookPrice = 33.99;
        int expectedQuantity = 6;
        double expectedTotalPrice = singleBookPrice * expectedQuantity;

//        cartPage.setCartQuantity(expectedQuantity);
//        cartPage.clickUpdateButton();
//
//        // Retrieve the actual total price from the page and verify it
//        String actualTotalPriceText = cartPage.getTotalPrice();
//        double actualTotalPrice = Double.parseDouble(actualTotalPriceText.substring(1)); // Remove the "$" symbol
//        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01);
    }
}
