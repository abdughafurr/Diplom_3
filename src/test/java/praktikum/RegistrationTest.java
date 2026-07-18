package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.api.User;
import praktikum.api.UserClient;
import praktikum.api.UserGenerator;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    private final UserClient userClient = new UserClient();
    private String accessToken;

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() {
        User user = UserGenerator.getRandom();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        praktikum.pages.LoginPage loginPage = new praktikum.pages.LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        // После успешной регистрации открывается страница входа
        assertTrue(loginPage.isLoginButtonVisible());

        // Для последующего удаления пользователя получаем токен
        accessToken = userClient.login(user).extract().path("accessToken");
    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем меньше 6 символов")
    public void registrationWithShortPassword() {
        User user = UserGenerator.getRandom();
        user.setPassword("123");

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        praktikum.pages.LoginPage loginPage = new praktikum.pages.LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(registerPage.isPasswordErrorVisible());
    }

    @org.junit.After
    public void deleteUser() {
        userClient.delete(accessToken);
    }
}