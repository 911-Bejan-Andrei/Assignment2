package domain.types;

import domain.interfaces.InterfaceType;
import domain.interfaces.InterfaceValue;
import domain.values.StringValue;

public class StringType implements InterfaceType {
    public StringType() {
    }

    public boolean equals(Object another) {
        return another instanceof StringType;
    }


    @Override
    public InterfaceType deepCopy() {
        return new StringType();
    }

    @Override
    public InterfaceValue defaultValue() {
        return new StringValue("");
    }

    public String toString() {
        return "string";
    }
}
