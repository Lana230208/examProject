package basketTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class BasketTest extends BaseTest {
    @Test
    public void addFirstItemToBasket() {
        basketPage.openBasketPage();
        basketPage.setSearchText("Good Girl");
        basketPage.putFirstElementToBasket();
        basketPage.deleteFirstElementFromBasket();
    }
}


