package pages;
// test github update

import consts.Consts;

public class MainPage extends BasePage {

    private static final String LOGO_IMG = "//img[@itemprop='logo']";
    private static final String CONTACT_US_OPTION = "//a[text()='Contact us']";
    private static final String LOGIN_ICON = "a.site-header__account > svg";

    private static final String BOOKS_BY_LANGUAGE_DROPDOWN = "a.site-nav__link--main[href='/collections']";
    private static final String ENGLISH_ONLY_OPTION = "a.site-nav__child-link[href='/collections/english-only']";


    public void  navigateToMainPage(){
        webDriver.get(Consts.MAIN_URL);

    }

    public boolean isLogoVisible(){
       Boolean isVisible = elementExists(LOGO_IMG);
       return isVisible;

    }


    public  ContactUsPage openContactUsTab(){
        clickElementByXpath(CONTACT_US_OPTION);
        return new ContactUsPage();
    }



    public LoginPage openLoginPage() {
        clickElementByCssSelector(LOGIN_ICON);
        return new LoginPage();
    }

    public void openBooksByLanguageDropdown() {
        clickElementByXpath("//*[@id='SiteNav']/li[2]/a");
    }

    public BooksResultsPage selectEnglishOnlyOption() {
        openBooksByLanguageDropdown();
        clickElementByXpath("//*[@id='SiteNavLabel-books-by-language']/ul/li[10]/a");
        return new BooksResultsPage(webDriver);
    }

}
