import dto.CreateUserRequest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LoginPage;
import page.MainPage;
import page.PersonalAreaPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class GoToPersonalAreaTest extends BaseTest {

    @DisplayName("переход по клику на «Личный кабинет»")
    @Test
    public void goToPersonalAreaTest(){
        CreateUserRequest user = new CreateUserRequest()
                .setEmail(getEmail())
                .setPassword("qwertfyu9")
                .setName("aaaaaa");

        createUser(user);

        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(3));
        MainPage mainPage = new MainPage(driver);
        mainPage.personalAreaButtonClick();

        new LoginPage(driver)
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .loginButtonClick();

        mainPage.personalAreaButtonClick();

        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);

        assertTrue(personalAreaPage.isPersonalAreaPage());
    }
}
