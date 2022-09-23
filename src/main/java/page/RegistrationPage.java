package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private final By textInputs = By.className("input__textfield");
    private final int nameIndex = 0;
    private final int emailIndex = 1;
    private final int passwordIndex = 2;
    private final By registrationButton = By.xpath("/html/body/div/div/main/div/form/button");

    private final By loginButton = By.xpath("//*[@id='root']/div/main/div/div/p/a");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage setName(String name) {
        driver.findElements(textInputs).get(nameIndex).sendKeys(name);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        driver.findElements(textInputs).get(emailIndex).sendKeys(email);
        return this;
    }

    public RegistrationPage setPassword(String password) {
        driver.findElements(textInputs).get(passwordIndex).sendKeys(password);
        return this;
    }

    public void registrationButtonClick() {
        click(driver.findElement(registrationButton));
    }

    public void loginButtonClick() {
        click(driver.findElement(loginButton));
    }
}
