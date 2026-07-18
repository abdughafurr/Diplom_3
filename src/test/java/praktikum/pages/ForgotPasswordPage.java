package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver driver;

    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By restorePasswordHeader = By.xpath("//h2[text()='Восстановление пароля']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по ссылке 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Проверка, что открыта страница восстановления пароля")
    public boolean isRestorePasswordPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(restorePasswordHeader)).isDisplayed();
    }
}