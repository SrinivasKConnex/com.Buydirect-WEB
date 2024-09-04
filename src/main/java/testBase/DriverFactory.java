package testBase;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    // Singleton design pattern
    private DriverFactory() {
        // Private constructor to prevent instantiation
    }
    
    // Eager initialization
    private static final DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }
    
    // ThreadLocal to ensure thread safety
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }
    
    public void setDriver(WebDriver driverParam) {
        driver.set(driverParam);
    }
    
    public void closeDriver() {
        WebDriver driverInstance = driver.get();
        if (driverInstance != null) {
            driverInstance.quit();
            driver.remove();
        }
    }
}
