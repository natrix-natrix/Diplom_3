package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAreaPage extends BasePage{

    protected static final String PERSONAL_AREA_TEXT = "В этом разделе вы можете изменить свои персональные данные";
    private final By personalAreaText = By.xpath("//*[@id='root']/div/main/div/nav/p");
    private final By constructorButton = By.xpath("//*[@id='root']/div/header/nav/ul/li[1]");
    private final By logoButton = By.xpath("/html/body/div/div/header/nav/div/a");
    private final By logoutButton = By.xpath("/html/body/div/div/main/div/nav/ul/li[3]/button");


    public PersonalAreaPage(WebDriver driver) {
        super(driver);
    }

    public void constructorButtonClick(){
        click(driver.findElement(constructorButton));
    }

    public void logoButtonClick(){
        click(driver.findElement(logoButton));
    }

    public void logoutButtonClick() {
        click(driver.findElement(logoutButton));
    }

    public boolean isPersonalAreaPage() {
        try {
            WebElement element = driver.findElement(personalAreaText);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element));
            return element.getText().contains(PERSONAL_AREA_TEXT);
        } catch (Exception timeout) {
            return false;
        }
    }
}
