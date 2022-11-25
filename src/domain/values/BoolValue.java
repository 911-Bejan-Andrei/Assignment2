package domain.values;

import domain.interfaces.InterfaceType;
import domain.interfaces.InterfaceValue;
import domain.types.BoolType;

public class BoolValue implements InterfaceValue {

    boolean value;

    public BoolValue(boolean  value) {
        this.value =  value;
    }

    public boolean getValue() {
        return  value;
    }

    @Override
    public InterfaceType getType() {
        return new BoolType();
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    public InterfaceValue deepCopy() {
        return new BoolValue(this.value);
    }

    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }*/
}
