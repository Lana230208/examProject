package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasketPage extends ParentPage {

    private WebElement goodItemLink;

    WebDriverWait w;

    public void openBasketPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Basket page was opened");
            w.until(ExpectedConditions.presenceOfElementLocated(By.id("block-minilogin")));
            // ждём, пока не загрузится JavaScript поиска
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            logger.error("Can't work with BasketPage" + e);
            Assert.fail("Can't work with BasketPage");
        }
    }

    public BasketPage(WebDriver webDriver) {
        super(webDriver);
        w = new WebDriverWait(webDriver, 20);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }
    public boolean setSearchText(String text) {
        try {
            w.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
            WebElement inputSearch = webDriver.findElement(By.id("search"));
            inputSearch.sendKeys(text);
            w.until(ExpectedConditions.visibilityOfElementLocated(By.id("multisearch-root")));
            TimeUnit.SECONDS.sleep(1);
            return true;
        } catch (Exception e) {
            logger.error("Can't work with BasketPage" + e);
            Assert.fail("Can't work with BasketPage");
            return false;
        }
    }
    public boolean putFirstElementToBasket() {
        try {
            WebElement rootContainer = webDriver.findElement(By.id("multisearch-root"));
            goodItemLink = rootContainer.findElement(By.xpath("./div/div/div[3]/div/a[1]"));
            goodItemLink.click();
            w.until(ExpectedConditions.visibilityOfElementLocated(By.id("product-addtocart-button")));
            TimeUnit.SECONDS.sleep(3);
            logger.info("cartButton============");
            //WebElement cartButton = rootContainer.findElement(By.id("product-addtocart-button"));
            WebElement cartButton = webDriver.findElement(By.xpath("//button[@id='product-addtocart-button']"));
            logger.info(cartButton.getText());
            cartButton.click();
            return true;
        } catch (Exception e) {
            logger.error("Can't work with BasketPage" + e);
            Assert.fail("Can't work with BasketPage");
            return false;
        }
    }
    public boolean deleteFirstElementFromBasket() {
        w.until(ExpectedConditions.visibilityOfElementLocated(By.id("mini-cart")));
        WebElement miniCart = webDriver.findElement(By.id("mini-cart"));
        goodItemLink = miniCart.findElement(By.xpath("./li/div/div/div[3]/a"));
        goodItemLink.click();
        return true;
    }
}
