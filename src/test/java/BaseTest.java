import dto.CreateUserRequest;
import dto.LoginRequest;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.html5.WebStorage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class BaseTest {
    protected static WebDriver driver;

    @After
    public void clearDriver() {
        driver.manage().deleteAllCookies();
        if (driver instanceof WebStorage) {
            WebStorage webStorage = (WebStorage) driver;
            webStorage.getSessionStorage().clear();
            webStorage.getLocalStorage().clear();
        }
    }

    @BeforeClass
    public static void startUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions(); //Драйвер для браузера
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

//        WebDriverManager.firefoxdriver().driverVersion("0.31.0").setup();
//        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--headless");
//        driver = new FirefoxDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1024, 768));
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }

    protected String getEmail() {
        return UUID.randomUUID() + "@gmail.com";
    }

    protected void loginAndDeleteUser(LoginRequest login) {
        String accessToken = loginUser(login)
                .then().assertThat()
                .statusCode(200)
                .and()
                .body("success", is(true))
                .extract()
                .body()
                .path("accessToken");

        deleteUser(accessToken).then().assertThat()
                .statusCode(202);
    }

    protected Response loginUser(LoginRequest user) {
        return postRequest(user, null, "/auth/login");
    }

    protected Response deleteUser(String token) {
        return deleteRequest("/auth/user", token);
    }

    protected <T> Response postRequest(T body, String token, String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        if (Objects.nonNull(token)) {
            headers.put("Authorization", token);
        }
        return given()
                .headers(headers)
                .and()
                .body(body)
                .when()
                .post(url);
    }

    protected Response deleteRequest(String url, String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(url);
    }

    protected Response createUser(CreateUserRequest user) {
        return postRequest(user, null, "/auth/register");
    }
}
