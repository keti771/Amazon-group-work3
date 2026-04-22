package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CellphonesPage extends BasePage {
// cart validation logic
    public CellphonesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchInput;

    @FindBy(xpath = "//*[contains(text(),'Best Sellers')]")
    WebElement bestSellers;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCart;

    @FindBy(id = "nav-cart")
    WebElement cartBtn;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']//span")
    WebElement subtotal;

    double price1;
    double price2;


    private void clickProduct(int index) {

        List<WebElement> products = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("h2 a")
                )
        );

        wait.until(ExpectedConditions.elementToBeClickable(products.get(index))).click();
    }

    private double getProductPrice() {
        WebElement priceElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[@class='a-price-whole']")
                )
        );

        String priceText = priceElement.getText().replace(",", "");
        return Double.parseDouble(priceText);
    }


    @Step("Search Cell phones")
    public void searchCellphones() {

        searchInput.clear();
        searchInput.sendKeys("Cell phones");
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-component-type='s-search-result']")
        ));
    }


    @Step("Validate Best Sellers")
    public boolean isBestSellersDisplayed() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2500)");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'Best Sellers')]")
        ));

        return bestSellers.isDisplayed();
    }
    @Step("Add first two phones")
    public void addFirstTwoProducts() {

        // FIRST PRODUCT
        clickProduct(0);

        price1 = getProductPrice();

        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();

        driver.navigate().back();

        // wait for page reload (IMPORTANT)
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-component-type='s-search-result']")
        ));

        // SECOND PRODUCT
        clickProduct(1);

        price2 = getProductPrice();

        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
    }

    @Step("Go to cart")
    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn)).click();
    }
// subtotal validation
        @Step("Validate subtotal")
    public boolean validateSubtotal() {

        double expected = price1 + price2;

        String subtotalText = wait.until(
                        ExpectedConditions.visibilityOf(subtotal)
                ).getText()
                .replace("$", "")
                .replace(",", "");

        double actual = Double.parseDouble(subtotalText);

        return Math.abs(actual - expected) < 0.01;
    }

}