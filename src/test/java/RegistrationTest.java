import dto.LoginRequest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LoginPage;
import page.MainPage;
import page.RegistrationPage;

import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @DisplayName("Успешная регистрация")
    @Test
    public void registrationSuccessful() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(3));
        MainPage mainPage = new MainPage(driver);
        mainPage.personalAreaButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registrationButtonClick();

        LoginRequest user = new LoginRequest()
                .setEmail(getEmail())
                .setPassword("qwertfyu9");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setName("sbertbeyby")
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());
        registrationPage.registrationButtonClick();

        assertTrue(loginPage.isLoginPage());
        loginAndDeleteUser(user);
    }

    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
    @Test
    public void registrationWithInvalidPassword() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(3));
        MainPage mainPage = new MainPage(driver);
        mainPage.personalAreaButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registrationButtonClick();

        LoginRequest user = new LoginRequest()
                .setEmail(getEmail())
                .setPassword("12345");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setName("sbertbeyby")
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());
        registrationPage.registrationButtonClick();

        assertFalse(loginPage.isLoginPage());
    }

}
