package tcon.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {

    // Get a new WebDriver Instance.
    // Refer http://getgauge.io/documentation/user/current/managing_environments/README.html
    public static WebDriver getDriver() {

        String browser = System.getProperty("BROWSER");
        //String browser = System.getenv("BROWSER");
        System.out.println("FFFFFFFFFFFFFFFFFff" + browser);

        if ("IE".equals(browser)) {
            throw new RuntimeException("IE is not available");
        }
        if ("FIREFOX".equals(browser)) {
            throw new RuntimeException("FIREFOX is not available");
        }


        DesiredCapabilities dr=DesiredCapabilities.chrome();
        dr.setBrowserName("chrome");
        dr.setPlatform(Platform.WINDOWS);
        //dr.setPlatform(Platform.LINUX);

        RemoteWebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;

    }

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

    public static WebDriver newCloudDriverFromSauceLab() throws MalformedURLException {
        DesiredCapabilities dr=DesiredCapabilities.chrome();
        dr.setBrowserName("chrome");
        dr.setPlatform(Platform.WINDOWS);

        String USERNAME = "tcon";
        String ACCESS_KEY = "0fb6bb0b-4b4d-485d-b5d3-e889e8d6eeb1";
        String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";


        RemoteWebDriver driver=new RemoteWebDriver(new URL(URL), dr);

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
}
