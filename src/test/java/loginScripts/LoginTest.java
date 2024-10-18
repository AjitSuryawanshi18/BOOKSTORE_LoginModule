package loginScripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Feature;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    @Description("Login test for various credentials.")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login Feature")
    public void loginTest(String email, String password, boolean isValid) {
        LoginPage_POM loginPage = new LoginPage_POM(driver);

        // Log steps with Allure
        enterEmailStep(loginPage, email);
        enterPasswordStep(loginPage, password);
        clickLoginStep(loginPage);

        // Validate login
        if (isValid) {
            validateSuccessfulLogin(loginPage);
        } else {
            validateFailedLogin(loginPage);
        }
    }

    @Step("Enter email: {0}")
    public void enterEmailStep(LoginPage_POM loginPage, String email) {
        loginPage.enterEmail(email);
    }

    @Step("Enter password: {0}")
    public void enterPasswordStep(LoginPage_POM loginPage, String password) {
        loginPage.enterPassword(password);
    }

    @Step("Click the login button")
    public void clickLoginStep(LoginPage_POM loginPage) {
        loginPage.clickLogin();
    }

    @Step("Validate successful login")
    public void validateSuccessfulLogin(LoginPage_POM loginPage) {
        String expectedUrl = "http://localhost:1001/user/user_Home";
        Assert.assertEquals(loginPage.getCurrentUrl(), expectedUrl, "User should be redirected to the home page.");
    }

    @Step("Validate failed login with 'Bad credentials' message")
    public void validateFailedLogin(LoginPage_POM loginPage) {
        String actualError = loginPage.getErrorMessage();
        String expectedError = "Bad credentials";
        Assert.assertEquals(actualError, expectedError, "Error message does not match.");
    }
}
