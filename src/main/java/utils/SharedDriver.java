package utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class SharedDriver {

    private static WebDriver webDriver;

    public enum Browser {

        CHROME,
        FIREFOX,
        IE

    }

    public static WebDriver getWebDriver(Browser browser) {

        switch (browser) {
            case CHROME:

                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;

            case FIREFOX:

                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;

            case IE:
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
                break;


        }


        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        return webDriver;

    }

    public static void closeDriver() {
        if (webDriver != null) {
            webDriver.close();
        }


    }

}
