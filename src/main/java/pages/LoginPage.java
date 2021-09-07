package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.concurrent.TimeUnit;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//header/div[2]/div[1]/div[1]/div[1]/span")
    private WebElement loginIcon;

    @FindBy(id = "phone")
    private TextInput inputLogin;

    @FindBy(id = "pass")
    @Name("Input Pass ")
    private TextInput inputPassWord;

    @FindBy(id = "send2")
    private Button buttonSignIn;

    @FindBy(xpath = "//a[@href='https://www.brocard.ua/ua/customer/account/index/']")
    protected Button userLabel;

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
            WebDriverWait w = new WebDriverWait(webDriver, 60);
            w.until(ExpectedConditions.presenceOfElementLocated(By.id("block-minilogin")));
            logger.info("Login page has login icon");
            // open modal login window
            // clickOnElement(loginIcon); не работает из-за защиты от роботов,
            // используем ActionChain (Actions Java)
            WebElement parent = loginIcon.findElement(By.xpath("./../.."));
            TimeUnit.SECONDS.sleep(10);
            Actions a = new Actions(webDriver);
            a.moveToElement(parent).click();
            a.perform();
        } catch (Exception e) {
            logger.error("Can't work with LoginPage" + e);
            Assert.fail("Can't work with LoginPage");
        }
    }
    public void enterLoginInSignIn(String login) {
        inputLogin.sendKeys(login);
    }

    public void enterPassWordInSignIn(String password) {
        enterTextToElement(inputPassWord, password);
    }

    public void clickOnButtonSignIn(int t) {
        try {
            clickOnElement(buttonSignIn);
            TimeUnit.SECONDS.sleep(t);
        } catch (Exception e) {
            logger.error("Can't work with LoginPage" + e);
            Assert.fail("Can't work with LoginPage");
        }
    }

    public void fillLoginFormAndSubmit(String login, String passWord) {
        openLoginPage();
        enterPassWordInSignIn(passWord);
        clickOnButtonSignIn(1);
        enterLoginInSignIn(login);
        clickOnButtonSignIn(1);
}

        public boolean isLabelMessageEqual(String message) {
        WebDriverWait w = new WebDriverWait(webDriver, 20);
        w.until(ExpectedConditions.visibilityOfElementLocated(By.id("notifications")));
        //@FindBy(id = "notifications")
        WebElement labelMessageNotifications = webDriver.findElement(By.id("notifications"));
        String labelMessage = labelMessageNotifications.getText();
        logger.error("labelMessage: ");
        logger.error(labelMessage);
        return isElementPresent(labelMessageNotifications)&
                (labelMessage.equals(message));
    }

    public boolean isUserLabelPresent() {
        return isElementPresent(userLabel);
    }

    public boolean isLabelMessageInvalidLoginPresent() {
        return isLabelMessageEqual("Невірний логін або пароль.");
    }

    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
}