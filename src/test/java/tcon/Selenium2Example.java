package tcon;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Selenium2Example {

    public static WebDriver newDriver() {

        System.setProperty("webdriver.chrome.driver", "C:/chrome-v2-33/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
        //WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

        return driver;
    }

    public static WebDriver newCloudDriver() throws MalformedURLException {
        DesiredCapabilities dr=DesiredCapabilities.chrome();
        dr.setBrowserName("chrome");
        dr.setPlatform(Platform.WINDOWS);

        RemoteWebDriver driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dr);

        return driver;
    }


    public static final String USERNAME = "info";
    public static final String ACCESS_KEY = "0237c87d1c462408827c3f97e45d806c";
    public static final String KEY = USERNAME + ":" + ACCESS_KEY;
    public static final String URL = "http://hub.testinium.io/wd/hub";

    public static WebDriver newCloudDriver2() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("key", KEY);

        capabilities.setCapability(CapabilityType.PLATFORM, "WIN10");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.VERSION, "LATEST");
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capabilities.setCapability("recordsVideo", true);
        capabilities.setCapability("screenResolution", "SXGA");

        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);

        return driver;
    }


    @Test
    public void runTest() throws MalformedURLException {

        WebDriver driver;

        driver = newDriver();

        //driver = newCloudDriver();

        //driver = newCloudDriver2();

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        //element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        //element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
       /* (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });*/

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

        Assertions.assertThat(driver.getTitle()).isEqualTo("Google");

        //Close the browser
        driver.quit();
    }
}