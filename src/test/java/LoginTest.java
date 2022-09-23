import dto.CreateUserRequest;
import dto.LoginRequest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.ForgotPasswordPage;
import page.LoginPage;
import page.MainPage;
import page.RegistrationPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    @Test
    public void loginWithButtonOnMainPAge() {
        CreateUserRequest user = getUser();

        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(3));
        MainPage mainPage = new MainPage(driver);
        mainPage.loginButtonBottomClick();

        checkLoginPage(user);
    }

    @DisplayName("вход через кнопку «Личный кабинет»")
    @Test
    public void loginWithPersonalArea() {
        CreateUserRequest user = getUser();

        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(3));
        MainPage mainPage = new MainPage(driver);
        mainPage.personalAreaButtonClick();

        checkLoginPage(user);
    }

    private CreateUserRequest getUser() {
        CreateUserRequest user = new CreateUserRequest()
                .setEmail(getEmail())
                .setPassword("qwertfyu9")
                .setName("aaaaaa");

        createUser(user);
        return user;
    }

    @DisplayName("вход через кнопку в форме регистрации")
    @Test
    public void loginWithRegistrationForm() {
        CreateUserRequest user = getUser();

        driver.get("https://stellarburgers.nomoreparties.site/register");
        new WebDriverWait(driver, Duration.ofSeconds(3));
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.loginButtonClick();

        checkLoginPage(user);
    }

    @DisplayName("вход через кнопку в форме восстановления пароля")
    @Test
    public void loginWithForgotPassword() {
        CreateUserRequest user = getUser();

        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        new WebDriverWait(driver, Duration.ofSeconds(3));
        ForgotPasswordPage forgotPasswordPage  = new ForgotPasswordPage (driver);
        forgotPasswordPage.loginButtonClick();

        checkLoginPage(user);
    }

    private void checkLoginPage(CreateUserRequest user) {
        new LoginPage(driver)
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .loginButtonClick();

        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.isMainPage());

        LoginRequest loginRequest = new LoginRequest()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());

        loginAndDeleteUser(loginRequest);
    }

}
