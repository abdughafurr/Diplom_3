package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    // Кнопки входа
    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//a[@href='/account']");

    // Разделы конструктора
    private final By bunsSection = By.xpath("//span[text()='Булки']");
    private final By saucesSection = By.xpath("//span[text()='Соусы']");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']");

    // Заголовки разделов (для проверки, что раздел активен)
    private final By bunsHeader = By.xpath("//h2[text()='Булки']");
    private final By saucesHeader = By.xpath("//h2[text()='Соусы']");
    private final By fillingsHeader = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Клик по разделу 'Булки'")
    public void clickBunsSection() {
        driver.findElement(bunsSection).click();
    }

    @Step("Клик по разделу 'Соусы'")
    public void clickSaucesSection() {
        driver.findElement(saucesSection).click();
    }

    @Step("Клик по разделу 'Начинки'")
    public void clickFillingsSection() {
        driver.findElement(fillingsSection).click();
    }

    @Step("Проверка, что раздел 'Булки' активен")
    public boolean isBunsSectionActive() {
        return isElementVisible(bunsHeader);
    }

    @Step("Проверка, что раздел 'Соусы' активен")
    public boolean isSaucesSectionActive() {
        return isElementVisible(saucesHeader);
    }

    @Step("Проверка, что раздел 'Начинки' активен")
    public boolean isFillingsSectionActive() {
        return isElementVisible(fillingsHeader);
    }

    private boolean isElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }
}