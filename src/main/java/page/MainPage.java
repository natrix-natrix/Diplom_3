package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {

    protected static final String CONSTRUCTOR_TEXT = "Соберите бургер";
    private final By constructorText = By.className("text_type_main-large");
    //кнопка личный кабинет
    private final By personalAreaButton = By.xpath("//*[@href='/account']");
    private final By accountEnterButton = By.xpath("//button[text()='Войти в аккаунт']");

    private final By showBunsButton = By.xpath("//span[text()='Булки']");
    private final By showSauceButton = By.xpath("//span[text()='Соусы']");
    private final By showFillingButton = By.xpath("//span[text()='Начинки']");

    private final By bunsHeader = By.xpath("//h2[text()='Булки']");
    private final By sauceHeader = By.xpath("//h2[text()='Соусы']");
    private final By fillingHeader = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Основная страница: клик на кнопке 'Личный кабинет'")
    public void personalAreaButtonClick() {
        click(driver.findElement(personalAreaButton));
    }

    @Step("Основная страница: клик на кнопке 'Войти в аккаунт'")
    public void loginButtonBottomClick() {
        click(driver.findElement(accountEnterButton));
    }

    @Step("Основная страница: клик на кнопке 'Булки'")
    public void showBunsButtonClick() {
        click(driver.findElement(showBunsButton));
    }

    @Step("Основная страница: клик на кнопке 'Соусы'")
    public void showSauceButtonClick() {
        click(driver.findElement(showSauceButton));
    }

    @Step("Основная страница: клик на кнопке 'Начинки'")
    public void showFillingButtonClick() {
        click(driver.findElement(showFillingButton));
    }

    @Step("Основная страница: на этой ли мы странице?")
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

    @Step("Основная страница: виден ли раздел с булками?")
    public boolean isBunsVisible() {
        return isElementVisible(bunsHeader);
    }

    @Step("Основная страница: виден ли раздел с соусами?")
    public boolean isSauceVisible() {
        return isElementVisible(sauceHeader);
    }

    @Step("Основная страница: виден ли раздел с начинками?")
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
