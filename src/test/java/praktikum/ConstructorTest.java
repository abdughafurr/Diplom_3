package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void goToBunsSection() {
        MainPage mainPage = new MainPage(driver);
        // Сначала уходим на другой раздел, потом возвращаемся к Булкам
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();

        assertTrue(mainPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void goToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesSection();

        assertTrue(mainPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void goToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsSection();

        assertTrue(mainPage.isFillingsSectionActive());
    }
}