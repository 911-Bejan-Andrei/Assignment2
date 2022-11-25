package domain.types;

import domain.interfaces.InterfaceType;
import domain.interfaces.InterfaceValue;
import domain.values.IntValue;

public class IntType implements InterfaceType {
    public IntType() {
    }

    public boolean equals(Object another) {
        return another instanceof IntType;
    }

    @Override
    public InterfaceType deepCopy() {
        return new IntType();
    }

    @Override
    public InterfaceValue defaultValue() {
        return new IntValue(0);
    }

    public String toString() {
        return "int";
    }
}

