package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void goToBunsTab() {
        MainPage mainPage = new MainPage(driver);
        // Уходим на другой раздел, так как 'Булки' выбраны по умолчанию
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        assertTrue(mainPage.isBunsTabSelected());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void goToSaucesTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();

        assertTrue(mainPage.isSaucesTabSelected());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void goToFillingsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();

        assertTrue(mainPage.isFillingsTabSelected());
    }
}