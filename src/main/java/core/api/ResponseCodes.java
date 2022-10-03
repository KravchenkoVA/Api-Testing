package core.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodes {
    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    FORBIDDEN(403),
    NO_CONTENT(204),
    UNPROCESSABLE_ENTITY(422),
    INTERNAL_ERROR(500);

    private int code;

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
