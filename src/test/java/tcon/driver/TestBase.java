package tcon.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    // Get a new WebDriver Instance.
    // There are various implementations for this depending on browser. The required browser can be set as an environment variable.
    // Refer http://getgauge.io/documentation/user/current/managing_environments/README.html
    public static WebDriver getDriver() {

        String browser = System.getenv("BROWSER");

        if ("IE".equals(browser)) {
            return new ChromeDriver();
        }
        if ("FIREFOX".equals(browser)) {
                return new ChromeDriver();
        }

        return new ChromeDriver();

    }
}
