package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CellphonesPage;
import pages.HomePage;

@Epic("Amazon Automation")
@Feature("Navigation & Section Validation")
public class AmazonTests extends BaseTest {

    @Test
    public void testAmazonCartValidation() {

        CellphonesPage page = new CellphonesPage(driver);

        page.searchCellphones();

        Assert.assertTrue(page.isBestSellersDisplayed());

        page.addFirstTwoProducts();

        page.goToCart();

        Assert.assertTrue(page.validateSubtotal());
    }
}