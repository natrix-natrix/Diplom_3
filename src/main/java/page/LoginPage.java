package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    //кнопка регистрации
    private final By registrationButton = By.xpath("//*[text()='Зарегистрироваться']");
    private final By loginButton = By.xpath("//*[text()='Войти']");
    private final By emailInput = By.xpath("//*[@name='name']");
    private final By passwordInput = By.xpath("//*[@name='Пароль']");
    private final By textInputs = By.className("input__textfield");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Страница логина: клик на кнопке логина")
    public void registrationButtonClick() {
        WebElement element = driver.findElement(registrationButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Step("Страница логина: ввод email: {0}")
    public LoginPage setEmail(String email) {
        WebElement element = driver.findElement(emailInput);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(email);
        return this;
    }

    @Step("Страница логина: ввод пароля")
    public LoginPage setPassword(String password) {
        WebElement element = driver.findElement(passwordInput);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(password);
        return this;
    }

    @Step("Страница логина: клик на кнопке логина")
    public void loginButtonClick() {
        WebElement element = driver.findElement(loginButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Step("Страница логина: на этой ли мы странице?")
    public boolean isLoginPage() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.numberOfElementsToBeLessThan(textInputs, 3));
            return driver.getCurrentUrl().endsWith("/login");
        } catch (Exception timeout) {
            return false;
        }
    }
}
