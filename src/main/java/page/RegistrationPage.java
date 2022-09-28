package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private final By textInputs = By.xpath("//input[@name='name' or @name='Пароль']");
    private final int nameIndex = 0;
    private final int emailIndex = 1;
    private final int passwordIndex = 2;

    private final By registrationButton = By.xpath("//*[text()='Зарегистрироваться']");

    private final By loginButton = By.xpath("//*[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Страница регистрации: ввод имени: {0}")
    public RegistrationPage setName(String name) {
        driver.findElements(textInputs).get(nameIndex).sendKeys(name);
        return this;
    }

    @Step("Страница регистрации: ввод email: {0}")
    public RegistrationPage setEmail(String email) {
        driver.findElements(textInputs).get(emailIndex).sendKeys(email);
        return this;
    }

    @Step("Страница регистрации: ввод пароля")
    public RegistrationPage setPassword(String password) {
        driver.findElements(textInputs).get(passwordIndex).sendKeys(password);
        return this;
    }

    @Step("Страница регистрации: клик на кнопке 'Регистрация'")
    public void registrationButtonClick() {
        click(driver.findElement(registrationButton));
    }

    @Step("Страница регистрации: клик на кнопке 'Войти'")
    public void loginButtonClick() {
        click(driver.findElement(loginButton));
    }
}
