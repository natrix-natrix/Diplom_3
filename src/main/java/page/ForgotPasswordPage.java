package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage{

    private final By loginButton = By.xpath(".//*[text()='Войти']");
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Страница восстановления пароля: клик на кнопке логина")
    public void loginButtonClick(){
        click(driver.findElement(loginButton));
    }
}
