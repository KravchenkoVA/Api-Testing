package core.api;

import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserApi extends Api {

    private final String USERS_URL = "/api/users";
    private final String USER_URL = "/api/users/{userId}";

    /**
     * Creates user
     * @param body - request body
     * @return - response from server
     */
    @Step("Create user")
    public Response createUser(Object body) {
        Response response = sendRequest(USERS_URL, body, Method.POST);
        return response;
    }

    /**
     * Gets user
     * @param userId - target user id
     * @return - response from server
     */
    @Step("Get user with id \"{0}\"")
    public Response getUser(Object userId) {
        RequestSpecification requestSpecification = defaultSpec().pathParam("userId", userId);
        Response response = sendRequest(requestSpecification, USER_URL, Method.GET);
        return response;
    }

    /**
     * Updates user
     * Uses PUT request
     * @param userId - target user id
     * @param body - request body
     * @return - response from server
     */
    @Step("Update user with id \"{0}\" using PUT")
    public Response updateUserWithPut(Object userId, Object body) {
        RequestSpecification requestSpecification = defaultSpec().pathParam("userId", userId);
        Response response = sendRequest(requestSpecification, USER_URL, body, Method.PUT);
        return response;
    }

    /**
     * Updates user
     * Uses PATCH request
     * @param userId - target user id
     * @param body - request body
     * @return - response from server
     */
    @Step("Update user with id \"{0}\" using PATCH")
    public Response updateUserWithPatch(Object userId, Object body) {
        RequestSpecification requestSpecification = defaultSpec().pathParam("userId", userId);
        Response response = sendRequest(requestSpecification, USER_URL, body, Method.PATCH);
        return response;
    }

    /**
     * Deletes user
     * @param userId - target user id
     * @return - response from server
     */
    @Step("Delete user with id \"{0}\"")
    public Response deleteUser(Object userId) {
        RequestSpecification requestSpecification = defaultSpec().pathParam("userId", userId);
        Response response = sendRequest(requestSpecification, USER_URL, Method.DELETE);
        return response;
    }

    /**
     * Gets users list
     * @param page - selected page
     * @return - response from server
     */
    @Step("Get users list")
    public Response getUsers(int page) {
        RequestSpecification requestSpecification = defaultSpec().queryParam("page", page);
        Response response = sendRequest(requestSpecification, USERS_URL, Method.GET);
        return response;
    }
}