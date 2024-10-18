package loginScripts;
//its POM - used for storing web element
//work here...

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage_POM {

    WebDriver driver;

    public LoginPage_POM(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("/html/body/section/div/div/div/div/div[2]/form/button");
    private By errorMessage = By.xpath("//p[@id='error-message']");

    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    //Explicit wait until element not visible
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait up to 10 seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)); // Wait for error message to be visible
        return driver.findElement(errorMessage).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    
}

