package loginScripts;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    WebDriver driver =null;

    @Parameters("BrowserName")
    
    @BeforeMethod
    public void setUp(String BrowserName) {
        driver = BrowserFactory.initBrowser(BrowserName);
        driver.get("http://localhost:1001/signin");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
