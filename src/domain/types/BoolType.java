package domain.types;

import domain.interfaces.InterfaceType;
import domain.interfaces.InterfaceValue;
import domain.values.BoolValue;

public class BoolType implements InterfaceType {

    public BoolType() {
    }

    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    @Override
    public InterfaceType deepCopy() {
        return new BoolType();
    }

    @Override
    public InterfaceValue defaultValue() {
        return new BoolValue(false);
    }

    public String toString() {
        return "boolean";
    }
}
