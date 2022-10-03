package core.matchers;

import core.api.ResponseCodes;
import core.dto.SimpleUserDTO;
import core.dto.UserGetDTO;
import core.dto.UsersListDTO;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMatcher {

    // acceptable range for createdAt and updatedAt attributes
    private final Long DELTA_MINUTES = 1L;

    /**
     * Checks created user
     * @param expected - expected user
     * @param response - actual response from server
     */
    @Step("Check created user")
    public void assertCreatedUser(SimpleUserDTO expected, Response response) {
        assertResponseCode(ResponseCodes.CREATED, response);
        SimpleUserDTO actual = response.as(SimpleUserDTO.class);
        assertCommonUserAttributes(expected, actual);
        MatcherAssert.assertThat("Id should present", actual.getId(), Matchers.notNullValue());
        Assertions.assertTrue(isNumericValue(actual.getId()), "Id should be numeric");

        // Set +- 1 minute range for createdAt attribute
        MatcherAssert.assertThat("Created at date out of range", actual.getCreatedAt(),
                Matchers.anyOf(
                        Matchers.lessThanOrEqualTo(ZonedDateTime.now().plusMinutes(DELTA_MINUTES)),
                        Matchers.greaterThanOrEqualTo(ZonedDateTime.now().minusMinutes(DELTA_MINUTES))));
    }

    /**
     * Checks updated user
     * @param expected - expected user
     * @param response - actual response from server
     */
    @Step("Check updated user")
    public void assertUpdatedUser(SimpleUserDTO expected, Response response) {
        assertResponseCode(ResponseCodes.OK, response);
        SimpleUserDTO actual = response.as(SimpleUserDTO.class);
        assertCommonUserAttributes(expected, actual);

        // Set +- 1 minute range for updatedAt attribute
        MatcherAssert.assertThat("Updated at date out of range", actual.getUpdatedAt(),
                Matchers.anyOf(
                        Matchers.lessThanOrEqualTo(ZonedDateTime.now().plusMinutes(DELTA_MINUTES)),
                        Matchers.greaterThanOrEqualTo(ZonedDateTime.now().minusMinutes(DELTA_MINUTES))));
    }

    /**
     * Checks common user attributes (between create and update)
     * @param expected - expected user
     * @param actual - actual user
     */
    private void assertCommonUserAttributes(SimpleUserDTO expected, SimpleUserDTO actual) {
        assertEquals(expected.getName(), actual.getName(), "Name mismatch");
        assertEquals(expected.getJob(), actual.getJob(), "Job mismatch");
    }

    /**
     * Check user with user item in list
     * @param expectedUserIndex - target user index in list (expected user)
     * @param usersList - users list
     * @param actualUser - actual user from GET
     */
    @Step("Check user with list")
    public void assertUser(int expectedUserIndex, UsersListDTO usersList, UserGetDTO actualUser) {
        assertEquals(usersList.getData().get(expectedUserIndex), actualUser.getData(), "User data mismatch");
        assertEquals(usersList.getAd(), actualUser.getAd(), "AD mismatch");
    }

    /**
     * See if value is numeric
     * @param value - specified value
     * @return - true or false
     */
    private boolean isNumericValue(String value) {
        try {
            Long.parseLong(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks response code
     * @param expectedCode - expected response code
     * @param response - actual response
     */
    @Step("Check response code")
    public void  assertResponseCode(ResponseCodes expectedCode, Response response) {
        assertEquals(expectedCode.getCode(), response.getStatusCode(), "Response code mismatch");
    }
}
