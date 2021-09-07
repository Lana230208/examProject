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

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin(){
        loginPage.fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);

        checkExpectedResult("User Label is not visible", loginPage.isUserLabelPresent(),true);
    }

    @Test
    public void invalidLogin(){

        loginPage.fillLoginFormAndSubmit("677006651", "12345678");

        checkExpectedResult("label Invalid Login is present", loginPage.isLabelMessageInvalidLoginPresent(),true);
        checkExpectedResult("User Label is not visible", loginPage.isUserLabelPresent(),false);
    }
    @Test
    @Parameters({"677776655,12345678", "677009987,12345677", "678990077,78988888"})
    public void paramInvalidLogin(String login, String password){

        loginPage.fillLoginFormAndSubmit(login, password);

        checkExpectedResult("label Invalid Login is not present", loginPage.isLabelMessageInvalidLoginPresent(),true);
        checkExpectedResult("User Label is not visible", loginPage.isUserLabelPresent(),false);
    }
}
