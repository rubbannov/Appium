import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppTest {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() {
        var options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:deviceName", "Honor8")
                .amend("appium:app", "ru.netology.testing.uiautomator")
                .amend("appium:automationName", "uiautomator2")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(this.getUrl(), options);
    }

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void spaceSymbolsTest() {
        var el1 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        String topText = el1.getText();

        var el2 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/userInput"));
        el2.click();
        el2.sendKeys("     ");

        var el3 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el3.click();
        var el4 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        String topTextChange = el4.getText();

        Assertions.assertEquals(topText, topTextChange);
    }

    @Test
    public void newActivityTextTest () {
        String testText = "Netology test";
        var el1 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.sendKeys(testText);

        var el2 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        el2.click();
        var el3 = driver.findElement(AppiumBy.id("ru.netology.testing.uiautomator:id/text"));
        String result = el3.getText();

        Assertions.assertEquals(testText, result);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
