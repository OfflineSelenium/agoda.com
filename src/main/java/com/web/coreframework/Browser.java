package com.web.coreframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.util.logging.Level;

/**
 * Google's Chrome and Microsoft's Internet Explorer browsers use a separate executable
 * to interface between Selenium and the browser. The automation framework looks in specific locations
 * under the user's "home" directory to start them on an as needed basis.
 */
public class Browser {

    private static String browserType;
    private static String browserPath;

    /**
     * Return a browser instance based on parameters above
     */
    public static WebDriver getInstance(String inBrowserType) {
        browserType = inBrowserType;
        browserPath = String.format("%s\\resources\\browser-drivers\\", System.getProperty("user.dir"));
        WebDriver driver = buildDriver();
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Build a "local" browser instance
     */
    private static WebDriver buildDriver() {
        WebDriver driver = null;
        DesiredCapabilities capabilities;
        final LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.SEVERE);

        switch (browserType.toLowerCase()) {
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

                // Get default profile of Firefox
                // final ProfilesIni profilesIni = new ProfilesIni();
                // final FirefoxProfile fp = profilesIni.getProfile("default");
                // capabilities.setCapability(FirefoxDriver.PROFILE, fp);
                driver = new FirefoxDriver(capabilities);
                break;

            case "chrome":
                checkBrowserDriver("chromedriver.exe");
                capabilities = DesiredCapabilities.chrome();
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
                System.setProperty("webdriver.chrome.driver", browserPath + "\\chromedriver.exe");
                driver = new ChromeDriver(capabilities);
                break;

            case "ie":
                checkBrowserDriver("IEDriverServer.exe");
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
                System.setProperty("webdriver.ie.driver", browserPath + "\\IEDriverServer.exe");
                driver = new InternetExplorerDriver(capabilities);
                break;
        }

        return driver;
    }

    /**
     * CheckBrowserDriver looks to see if the browser driver is installed and if not attempts to copy it from a known location
     */
    private static void checkBrowserDriver(String driverExeName) {
        // See if the driver exists and can be executed at the default location of user's home directory.
        File f = new File(browserPath + driverExeName);
        if (f.isFile()) {
            if (!f.canExecute())
                f.setExecutable(true);
            System.out.println(String.format("Found and using %s driver: %s", browserType, f.getAbsolutePath()));
            return;
        }

        // If the driver not found, see if the source is available at the location in the project.
        System.out.println(String.format("System does not found the driver at the path: %s", f.getAbsolutePath()));
    }

}