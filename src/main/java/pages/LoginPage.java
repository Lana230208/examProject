package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='login[username]']")
    private TextInput inputLogin;

    @FindBy(xpath = ".//input[@name='login[password]']")
    @Name("Input Pass ")
    private TextInput inputPassWord;

    @FindBy(xpath = ".//*[@class='action action-login primary']")
    private Button buttonSignIn;

    @FindBy(xpath = "//div[contains(text(),'Invalid username / password')]")
    private TextInput labelMessageInvalidLogin;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can't work with LoginPage" + e);
            Assert.fail("Can't work with LoginPage");
        }
    }
    public void enterLoginInSignIn(String login) {
        enterTextToElement(inputLogin, login);
    }

    public void enterPassWordInSignIn(String password) {
        enterTextToElement(inputPassWord, password);
    }


    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String login, String passWord) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPassWordInSignIn(passWord);
        clickOnButtonSignIn();
}

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }
    public boolean isLabelMessageInvalidLoginPresent() {
        return isElementPresent(labelMessageInvalidLogin);
    }

    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
}