package domain.values;

import domain.interfaces.InterfaceType;
import domain.interfaces.InterfaceValue;
import domain.types.IntType;

public class IntValue implements InterfaceValue {

    int value;

    public IntValue(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    @Override
    public InterfaceType getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public InterfaceValue deepCopy() {
        return new IntValue(value);
    }
}
