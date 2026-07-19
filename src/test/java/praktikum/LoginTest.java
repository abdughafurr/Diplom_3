package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.api.User;
import praktikum.api.UserClient;
import praktikum.api.UserGenerator;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private final UserClient userClient = new UserClient();
    private User user;
    private String accessToken;

    @Before
    public void createUser() {
        user = UserGenerator.getRandom();
        accessToken = userClient.create(user).extract().path("accessToken");
    }

    @After
    public void deleteUser() {
        userClient.delete(accessToken);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void loginViaMainButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isMainPageOpened());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isMainPageOpened());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginViaRegisterForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isMainPageOpened());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginViaForgotPasswordForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRestorePasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isMainPageOpened());
    }
}