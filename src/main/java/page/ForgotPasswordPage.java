package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage{

    private final By loginButton = By.xpath("/html/body/div/div/main/div/div/p/a");
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void loginButtonClick(){
        click(driver.findElement(loginButton));
    }
}
