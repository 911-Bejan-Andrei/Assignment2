package domain.values;

import domain.interfaces.InterfaceType;
import domain.interfaces.InterfaceValue;
import domain.types.StringType;

public class StringValue implements InterfaceValue {
    String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public InterfaceType getType() {
        return new StringType();
    }

    @Override
    public InterfaceValue deepCopy() {
        return new StringValue(value);
    }

    public String toString() {
        return "\"" + this.value + "\"";
    }
}
