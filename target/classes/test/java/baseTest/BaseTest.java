package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.BasketPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

    public class BaseTest {
        WebDriver webDriver;
        protected LoginPage loginPage;
//        protected HomePage homePage;
        protected BasketPage basketPage;
        protected Logger logger = Logger.getLogger(getClass());

        @Rule
        public TestName testName = new TestName();

        @Before
        public void setUp() {
            logger.info("-------" + testName.getMethodName() + " was started ---------");
            webDriver = initDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();

            loginPage = new LoginPage(webDriver);
            basketPage = new BasketPage(webDriver);
        }

        @After
        public void tearDown() {
            webDriver.quit();
            logger.info("-------" + testName.getMethodName() + "was ended ---------");
        }

        protected void checkExpectedResult(String message, boolean actualResult, boolean expectedResult) {
//        Assert.assertThat(message, actualResult, is(expectedResult));
            Assert.assertEquals(message, expectedResult, actualResult);
        }

        private WebDriver initDriver() {
            String browser = System.getProperty("browser");
            if ((browser == null) || browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("fireFox")) {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
            } else if ("ie".equalsIgnoreCase(browser)) {
                //WebDriverManager.iedriver().setup();

                // in most cases 32bit version is needed
                WebDriverManager.iedriver().arch32().setup();
                return new InternetExplorerDriver();
            }
            return webDriver;
        }
    }

