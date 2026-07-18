package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;

    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By passwordError = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Регистрация с указанными данными")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Проверка появления ошибки некорректного пароля")
    public boolean isPasswordErrorVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }
}