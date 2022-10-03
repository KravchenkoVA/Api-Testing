package core.dto;

import lombok.Getter;

@Getter
public class UserGetDTO extends IDealsUserDTO {

    private UserDataDTO data;
    private UserAdDTO ad;
}
