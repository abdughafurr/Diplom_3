package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private static final String CURRENT_TAB_CLASS = "tab_tab_type_current";

    private final WebDriver driver;

    // Кнопки входа
    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//a[@href='/account']");

    // Вкладки конструктора (div, содержащий название раздела)
    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");

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
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Клик по разделу 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Клик по разделу 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Проверка, что раздел 'Булки' выбран")
    public boolean isBunsTabSelected() {
        return isTabSelected(bunsTab);
    }

    @Step("Проверка, что раздел 'Соусы' выбран")
    public boolean isSaucesTabSelected() {
        return isTabSelected(saucesTab);
    }

    @Step("Проверка, что раздел 'Начинки' выбран")
    public boolean isFillingsTabSelected() {
        return isTabSelected(fillingsTab);
    }

    @Step("Ожидание загрузки главной страницы")
    public boolean isMainPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab)).isDisplayed();
    }

    private boolean isTabSelected(By tabLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.attributeContains(tabLocator, "class", CURRENT_TAB_CLASS));
    }
}