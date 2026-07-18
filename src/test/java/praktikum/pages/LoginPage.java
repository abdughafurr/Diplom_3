package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By restorePasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Вход с указанными данными")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickRestorePasswordLink() {
        driver.findElement(restorePasswordLink).click();
    }

    @Step("Проверка, что открыта страница входа")
    public boolean isLoginButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }
}