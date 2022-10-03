package tests;

import core.api.ResponseCodes;
import core.api.UserApi;
import core.dto.SimpleUserDTO;
import core.dto.UserGetDTO;
import core.dto.UsersListDTO;
import core.helpers.UserHelper;
import core.matchers.UserMatcher;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Api task")
public class ApiTest extends TestBase {
    private static UserApi api = new UserApi();
    private static UsersListDTO usersList;

    private UserMatcher matcher = new UserMatcher();

    @BeforeAll
    public static void getUsers() {
        usersList = UserHelper.getUsers(1);
    }

    @DisplayName("Create user")
    @Test
    public void createUser() {
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO("Batman", "Kicking ...");
        Response response = api.createUser(simpleUserDTO);

        matcher.assertCreatedUser(simpleUserDTO, response);
    }

    @DisplayName("Get user")
    @Test
    public void getUser() {
        int expectedUserIndex = 0;
        Response response = api.getUser(usersList.getData().get(expectedUserIndex).getId());

        matcher.assertResponseCode(ResponseCodes.OK, response);
        matcher.assertUser(expectedUserIndex, usersList, response.as(UserGetDTO.class));
    }

    @DisplayName("Update user by PUT")
    @Test
    public void updateByPut() {
        String targetUserId = usersList.getData().get(1).getId();
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO("Joker", "Do something insane");
        Response response = api.updateUserWithPut(targetUserId, simpleUserDTO);

        matcher.assertUpdatedUser(simpleUserDTO, response);
    }

    @DisplayName("Update user by PATCH")
    @Test
    public void updateByPatch() {
        String targetUserId = usersList.getData().get(1).getId();
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO("Harley Queen", "Join Joker");
        Response response = api.updateUserWithPatch(targetUserId, simpleUserDTO);

        matcher.assertUpdatedUser(simpleUserDTO, response);
    }

    @DisplayName("Delete user")
    @Test
    public void deleteUser() {
        String targetUserId = usersList.getData().get(2).getId();
        Response response = api.deleteUser(targetUserId);

        matcher.assertResponseCode(ResponseCodes.NO_CONTENT, response);
    }
}
