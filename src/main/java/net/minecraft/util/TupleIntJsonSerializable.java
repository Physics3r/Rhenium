package net.minecraft.util;

import lombok.Getter;
import lombok.Setter;

@Setter
public class TupleIntJsonSerializable {
    @Getter
    private int integerValue;
    private IJsonSerializable jsonSerializableValue;

    public <T extends IJsonSerializable> T getJsonSerializableValue() {
        return (T) this.jsonSerializableValue;
    }

}
