package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final By modal = By.className("Modal_modal_overlay__x2ZCr");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(element));
        if (driver instanceof JavascriptExecutor) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(
                        "document.getElementsByClassName('Modal_modal_overlay__x2ZCr')[0].remove();\n" +
                        "document.getElementsByClassName('Modal_modal_overlay__x2ZCr')[0].remove();\n");

                js.executeScript("arguments[0].scrollIntoView(true);", element);

            }catch (Exception e) {
                //
            }
        }
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }
}
