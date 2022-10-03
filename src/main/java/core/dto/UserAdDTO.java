package core.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class UserAdDTO extends IDealsUserDTO {

    private String company;
    private String url;
    private String text;
}
