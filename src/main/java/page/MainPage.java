package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {

    protected static final String CONSTRUCTOR_TEXT = "Соберите бургер";
    private final By constructorText = By.xpath("//*[@id='root']/div/main/section[1]/h1");
    //кнопка личный кабинет
    private final By personalAreaButton = By.xpath("/html/body/div/div/header/nav/a");
    private final By accountEnterButton = By.xpath("//*[@id='root']/div/main/section[2]/div/button");

    private final By showBunsButton = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[1]");
    private final By showSauceButton = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[2]");
    private final By showFillingButton = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[3]");

    private final By bunsHeader = By.xpath("//*[@id='root']/div/main/section[1]/div[2]/h2[1]");
    private final By sauceHeader = By.xpath("//*[@id='root']/div/main/section[1]/div[2]/h2[2]");
    private final By fillingHeader = By.xpath("//*[@id='root']/div/main/section[1]/div[2]/h2[3]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void personalAreaButtonClick() {
        click(driver.findElement(personalAreaButton));
    }

    public void loginButtonBottomClick() {
        click(driver.findElement(accountEnterButton));
    }

    public void showBunsButtonClick() {
        click(driver.findElement(showBunsButton));
    }

    public void showSauceButtonClick() {
        click(driver.findElement(showSauceButton));
    }

    public void showFillingButtonClick() {
        click(driver.findElement(showFillingButton));
    }

    public boolean isMainPage() {
        try {
            WebElement element = driver.findElement(constructorText);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element));
            return element.getText().contains(CONSTRUCTOR_TEXT);
        } catch (Exception timeout) {
            return false;
        }
    }

    public boolean isBunsVisible() {
        return isElementVisible(bunsHeader);
    }

    public boolean isSauceVisible() {
        return isElementVisible(sauceHeader);
    }

    public boolean isFillingVisible() {
        return isElementVisible(fillingHeader);
    }

    public boolean isElementVisible(By by) {
        try {
            WebElement element = driver.findElement(by);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception timeout) {
            return false;
        }
    }
}
