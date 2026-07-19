package praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER = "/api/auth/register";
    private static final String LOGIN = "/api/auth/login";
    private static final String USER = "/api/auth/user";

    @Step("Создание пользователя через API")
    public ValidatableResponse create(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post(REGISTER)
                .then();
    }

    @Step("Логин пользователя через API")
    public ValidatableResponse login(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post(LOGIN)
                .then();
    }

    @Step("Удаление пользователя через API")
    public void delete(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return;
        }
        given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(BASE_URL)
                .when()
                .delete(USER)
                .then();
    }
}