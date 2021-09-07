package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class);

    protected final String baseUrl = configProperties.base_url();

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                , this);
        webDriverWait10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DEFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page "
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );
    }

    protected void enterTextToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(" ' " + text + " ' was input in the element " + getElementName(webElement));
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = " '" + ((TypifiedElement) webElement).getName() + "'";
        }
        return elementName;
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + "Element was clicked");
        } catch (Exception e) {
            writeErrorAndStopTest(e);
        }
    }

    protected boolean isElementPresent(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + "Element is present ");
            } else {
                logger.info(getElementName(webElement) + "Element is not present");
            }
            return state;
        } catch (Exception e) {
            logger.info(getElementName(webElement) + "Element is not present");
            return false;
        }
    }

    private void writeErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}