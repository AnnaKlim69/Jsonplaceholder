package framework;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static framework.Browser.*;

@Listeners(TestListener.class)

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        getInstance();
        windowMaxsimize();
        navigateTo(PropertyReader.getProperty("bas.URL"));
    }

    @AfterMethod(alwaysRun = true, description = "Closing browser")
    public void tearDown() {
        quit();
    }
}