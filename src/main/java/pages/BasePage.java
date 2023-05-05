package pages;
// test github update

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {

    public static final Logger logger = LogManager.getLogger(BasePage.class);

  public static  WebDriver webDriver;


    protected static WebDriverWait wait;
    public void setWebDriver(WebDriver webDriver){
        this.webDriver=webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

    }
protected
 void clickElementByXpath(String xpath){

        logger.info("Clicking element with xpath " + xpath);

         findElementByXpath(xpath).click();

}

protected void sendTextToElementByXpath(String xpath, String text){
        findElementByXpath(xpath).sendKeys(text);

}

protected boolean elementExists(String xpath){
        try {
            logger.info("Check element with xpath exist " + xpath);

            webDriver.findElement(By.xpath(xpath));
          //  findElementByXpath(xpath);
            return true;
        }
        catch (Exception err){
            return false;
        }



}

    protected void waitForElementVisible(By locator, long timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
        customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


   public WebElement findElementByXpath(String xpath){
        WebElement element;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

       element = webDriver.findElement(By.xpath(xpath));
       return element;

    }

    protected String getCurrentPageURL(){
        return webDriver.getCurrentUrl();
    }

    public void takeScreenShot(String name){
        TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("Screenshots/" + name+ ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected WebElement findElementByCssSelector(String cssSelector) {
        WebElement element;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
        element = webDriver.findElement(By.cssSelector(cssSelector));
        return element;
    }

    protected void clickElementByCssSelector(String cssSelector) {
        logger.info("Clicking element with CSS selector " + cssSelector);
        findElementByCssSelector(cssSelector).click();
    }


    protected boolean isElementVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }



}


