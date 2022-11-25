package domain.expresions;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.MyException;
import com.exception.StatementException;
import com.sun.jdi.Value;
import domain.adts.MyInterfaceDictionary;
import domain.interfaces.InterfaceValue;

public class VarExp implements InterfaceExp {
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public InterfaceValue eval(MyInterfaceDictionary<String, InterfaceValue> tbl) throws StatementException, ExpressionException, ADTException {
        return tbl.get(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public InterfaceExp deepCopy() {
        return new VarExp(id);
    }
}
