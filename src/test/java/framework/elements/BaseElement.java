package framework.elements;

import framework.Browser;
import framework.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static framework.Browser.getDriver;
import static framework.PropertyReader.getIntProperty;

public abstract class BaseElement {
    protected WebElement element;

    protected List<WebElement> elements;

    protected By by;
    protected String name;

    protected WebDriverWait wait;

    public BaseElement(By by) {//Базовый элемент
        this.by = by;
    }

    abstract String getElementType();

    protected List<? extends BaseElement> returnElements;

    List<String> broKenImgList = new ArrayList<>();

    public BaseElement(By by, String name) {
        this.by = by;
        this.name = name;
    }

    public static String getProperty(String key) {//получить свойство
        return key;
    }

    public boolean isElementPresent() {
        try {
            getDriver().manage().timeouts().implicitlyWait(new PropertyReader("config.properties").
                    getIntProperty("timeout"), TimeUnit.SECONDS);
            element = getDriver().findElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println(getElementType() + ":" + by + "is not present. Exception - " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public WebElement getElement() {
        isElementPresent();
        return element;
    }

    public List<WebElement> getElements() {
        isElementPresent();
        return elements;
    }

    public String getText() {
        isElementPresent();
        return element.getText();
    }

    public static String getLoc(final String key) {//получить лок???
        return getProperty(key);
    }

    public String sendKeys(String senKeys) {
        waitForIsElementPressent();
        element.sendKeys(senKeys);
        return senKeys;
    }

    public void selectByValue(String v) {
        waitForIsElementPressent();
        new Select(element).selectByValue(v);
        waitForElementLoaded();
    }

    public void select(String volue) {
        Select select = new Select(element);
        select.selectByVisibleText(volue);
    }

    public boolean isSelected() {
        waitForIsElementPressent();
        System.out.println((getProperty("log.select") + element.getText()));
        return element.isSelected();
    }

    public boolean isDisplayed() {
        waitForIsElementPressent();
        element.isDisplayed();
        if (getElement().isDisplayed()) {
            System.out.println(getElementType() + ":" + by + "is displeted");
            return true;
        } else {
            System.out.println(getElementType() + ":" + by + "is not displeted");
            return false;
        }
    }

    public void click() {
        waitForIsElementPressent();
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid orange'", element);
            isElementClickable();
            element.click();
        }
        System.out.println(getProperty("log.click") + " : " + getElementType() + " : " + by);
    }

    public void clickViaJS() {
        if (isElementPresent()) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.browser='3px solid blue", element);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
        }
    }

    public void clickAndWait() {
        isElementPresent();
        isElementClickable();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.browser='3px solid blue", element);
        getElement().click();
        waitForElementLoaded();
        System.out.println(getProperty("log.click") + " : " + getElementType() + " : " + by);
    }

    public void moveAndClickByActions() {
        Actions actions = new Actions(Browser.getInstance().getDriver());
        actions.moveToElement(element).click().perform();
    }

    public void moveToElement() {
        Actions actions = new Actions(getDriver());
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.browser='3px solid green", element);
        actions.moveToElement(element);
    }

    public void scrollIntoView() {
        waitForIsElementPressent();
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    public String getAttribute(String attribute) {
        return getElement().getAttribute(attribute);
    }

    public boolean waitForIsElementPressent() {
        return isElementPresent();
    }

    public boolean waitForIsElementsArePressent() {
        waitForIsElementPressent();
        return false;
    }

    public boolean isElementClickable() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(getIntProperty("element.timeout")))
                    .until(ExpectedConditions.elementToBeClickable(getElement()));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element isn't clickable" + getElementType() + by);
            return false;
        }
    }

    public void waitForElementLoaded() {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(getIntProperty("element.load.timeout")));
    }
}