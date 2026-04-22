package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "nav-hamburger-menu")
    private WebElement allMenuButton;

    @FindBy(xpath = "//ul[@class='hmenu hmenu-visible']//a[div='Electronics']")
    private WebElement electronicsCategory;

    @FindBy(xpath = "//a[contains(@class, 'hmenu-compressed-btn')]")
    private WebElement seeAllButton;

    @Step("All მენიუს გახსნა და 'See All'-ზე დაჭერა")
    public void openAllMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(allMenuButton)).click();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(seeAllButton)).click();
        } catch (Exception e) {
        }
    }

    @Step("მენიუში 'Electronics'-ზე შესვლა")
    public void selectElectronics() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='hmenu hmenu-visible']//a[div='Electronics']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", electronicsCategory);

        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", electronicsCategory);
    }
}