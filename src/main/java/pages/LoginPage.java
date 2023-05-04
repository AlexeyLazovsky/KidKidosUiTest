package pages;

public class LoginPage extends BasePage {
    private static final String USERNAME_INPUT = "//input[@id='username']";
    private static final String PASSWORD_INPUT = "//input[@id='CustomerPassword']";
    private static final String LOGIN_BUTTON = "//input[@type='submit' and @value='Sign In']";
    private static final String ERROR_MESSAGE_XPATH = "//li[text()='Incorrect email or password.']";

    public void enterUsername(String username) {
        sendTextToElementByXpath("//input[@id='CustomerEmail']", username);
    }
    public void enterPassword(String password) {
        sendTextToElementByXpath(PASSWORD_INPUT, password);
    }

    public void clickLoginButton() {
        clickElementByXpath(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return findElementByXpath(ERROR_MESSAGE_XPATH).getText();
    }

    public boolean isErrorMessageVisible() {
        return elementExists(ERROR_MESSAGE_XPATH);
    }



}
