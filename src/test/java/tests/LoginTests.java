package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;

public class LoginTests extends UseCaseBase {
    private MainPage mainPage;

    @BeforeEach
    public void beforeEach() {
        mainPage = new MainPage();
        mainPage.navigateToMainPage();
    }

    @Test
    public void loginNegativeTests() {
        LoginPage loginPage = mainPage.openLoginPage();
        loginPage.enterUsername("Username");
        loginPage.enterPassword("Password");
        loginPage.clickLoginButton();
       // assertTrue(loginPage.isErrorMessageVisible(), "Error message should be visible for empty credentials");

        //  loginPage.enterUsername("incorrectUsername");
    //    loginPage.enterPassword("incorrectPassword");
    //    loginPage.clickLoginButton();
       // assertTrue(loginPage.isErrorMessageVisible(), "Error message should be visible for incorrect credentials");
    }



}
