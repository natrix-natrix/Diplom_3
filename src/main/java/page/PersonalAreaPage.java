package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAreaPage extends BasePage{

    protected static final String PERSONAL_AREA_TEXT = "В этом разделе вы можете изменить свои персональные данные";
    private final By personalAreaText = By.className("Account_text__fZAIn");
    private final By constructorButton = By.className("AppHeader_header__link__3D_hX");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private final By logoutButton = By.xpath("//button[text()='Выход']");


    public PersonalAreaPage(WebDriver driver) {
        super(driver);
    }

    @Step("Личный кабинет: клик на кнопке 'Конструктор'")
    public void constructorButtonClick(){
        click(driver.findElement(constructorButton));
    }

    @Step("Личный кабинет: клик на лого")
    public void logoButtonClick(){
        click(driver.findElement(logoButton));
    }

    @Step("Личный кабинет: клик на кнопке 'Выход'")
    public void logoutButtonClick() {
        click(driver.findElement(logoutButton));
    }

    @Step("Личный кабинет: на этой ли мы странице?")
    public boolean isPersonalAreaPage() {
        try {
            WebElement element = driver.findElement(personalAreaText);
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOf(element));
            return element.getText().contains(PERSONAL_AREA_TEXT);
        } catch (Exception timeout) {
            return false;
        }
    }
}
