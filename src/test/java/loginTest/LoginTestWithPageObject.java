package loginTest;

import baseTest.BaseTest;

import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    public static final String[] invalidLoginData = {"auto:123", "auto2:123", "auto3:789"};

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn(TestData.VALID_LOGIN);
        loginPage.enterPassWordInSignIn(TestData.VALID_PASSWORD);
        loginPage.clickOnButtonSignIn();

//        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }

    @Test
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit("+380677006651", "123");

        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(),true);
        checkExpectedResult("label Invalid Login is not present", loginPage.isLabelMessageInvalidLoginPresent(),true);
    }
    @Test
    @Parameters({"auto,123", "auto2,123", "auto3,789"})
    public void paramInvalidLogin(String login, String password){

        loginPage.fillLoginFormAndSubmit(login, password);

        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("label Invalid Login is not present", loginPage.isLabelMessageInvalidLoginPresent(), true);
    }
}
