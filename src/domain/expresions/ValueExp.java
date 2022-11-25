package domain.expresions;

import domain.adts.MyInterfaceDictionary;
import domain.interfaces.InterfaceValue;

public class ValueExp implements InterfaceExp {
    InterfaceValue e;

    public ValueExp(InterfaceValue e) {
        this.e = e;
    }

    public InterfaceValue eval(MyInterfaceDictionary<String, InterfaceValue> tbl) {
        return e;
    }

    @Override
    public String toString() {
        return "" + e.toString();
    }

    public InterfaceExp deepCopy() {
        return new ValueExp(e.deepCopy());
    }
}
