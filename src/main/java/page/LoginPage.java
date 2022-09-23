package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    //кнопка регистрации
    private final By registrationButton = By.className("Auth_link__1fOlj");
    private final By loginButton = By.xpath("/html/body/div/div/main/div/form/button");
    private final By textInputs = By.className("input__textfield");
    private final int emailIndex = 0;
    private final int passwordIndex = 1;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void registrationButtonClick() {
        WebElement element = driver.findElement(registrationButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public LoginPage setEmail(String email) {
        WebElement element = driver.findElements(textInputs).get(emailIndex);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        WebElement element = driver.findElements(textInputs).get(passwordIndex);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(password);
        return this;
    }

    public void loginButtonClick() {
        WebElement element = driver.findElement(loginButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean isLoginPage() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.numberOfElementsToBeLessThan(textInputs, 3));
            return driver.getCurrentUrl().endsWith("/login");
        } catch (Exception timeout) {
            return false;
        }
    }
}
