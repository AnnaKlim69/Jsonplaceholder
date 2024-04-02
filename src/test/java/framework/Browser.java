package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static Browser instance;
    private static WebDriver driver;

    public static Browser getInstance() {
        if (instance == null) {
            driver = DriverFactory.getDriver();
            driver.manage().timeouts().implicitlyWait(PropertyReader.getIntProperty("timeout"),
                    TimeUnit.SECONDS);
        } else {
            System.out.println("Driver does not instance!");
        }
        return instance = new Browser();
    }

    public static void windowMaxsimize() {//открыть окно максимально
        driver.manage().window().maximize();
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }

    public static void quit() {
        driver.quit();
        instance = null;
        System.out.println("Browser has been closed.");
    }

    public static void waitForPageLoad() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyReader
                .getIntProperty("page.load.timeout")));
        wait.until(driver -> executor.executeScript("return document.readyState").equals("complete"));

    }
    public static WebDriver getDriver() {
        return driver;
    }
}