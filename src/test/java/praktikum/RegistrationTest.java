package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.api.User;
import praktikum.api.UserClient;
import praktikum.api.UserGenerator;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    private static final String SHORT_PASSWORD = "12345";

    private final UserClient userClient = new UserClient();
    private User user;

    @Before
    public void createTestData() {
        user = UserGenerator.getRandom();
    }

    @After
    public void deleteUser() {
        String accessToken = userClient.login(user).extract().path("accessToken");
        userClient.delete(accessToken);
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(loginPage.isLoginButtonVisible());
    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем меньше 6 символов")
    public void registrationWithShortPassword() {
        user.setPassword(SHORT_PASSWORD);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(registerPage.isPasswordErrorVisible());
    }
}