package core.helpers;

import core.api.ResponseCodes;
import core.api.UserApi;
import core.dto.UsersListDTO;
import io.restassured.response.Response;

public class UserHelper {

    /**
     * Gets users list
     * @param page - selected page
     * @return - users list
     */
    public static UsersListDTO getUsers(int page) {
        Response response = new UserApi().getUsers(page);
        if (response.getStatusCode() != ResponseCodes.OK.getCode()) {
            throw new RuntimeException("Failed to get users list on page \"" + page + "\"");
        }
        return response.as(UsersListDTO.class);
    }
}
