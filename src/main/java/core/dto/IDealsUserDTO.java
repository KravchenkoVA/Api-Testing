package core.dto;

import core.serializer.JsonSerializer;

public abstract class IDealsUserDTO {

    @Override
    public String toString() {
        return JsonSerializer.getJson(this);
    }
}
