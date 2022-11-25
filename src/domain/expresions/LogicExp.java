package domain.expresions;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.MyException;
import com.exception.StatementException;
import com.sun.jdi.Value;
import domain.adts.MyInterfaceDictionary;
import domain.interfaces.InterfaceValue;
import domain.types.BoolType;
import domain.values.BoolValue;

import java.util.Objects;

public class LogicExp implements InterfaceExp {
    String op;
    InterfaceExp e1;
    InterfaceExp e2;

    public LogicExp(String op, InterfaceExp e1, InterfaceExp e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public InterfaceValue eval(MyInterfaceDictionary<String, InterfaceValue> tbl) throws ADTException, StatementException, ExpressionException{
        InterfaceValue v1, v2;
        v1 = e1.eval(tbl);
        if(v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl);
            if(v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue)v1;
                BoolValue b2 = (BoolValue)v2;
                boolean n1, n2;
                n1 = b1.getValue();
                n2 = b2.getValue();
                if(Objects.equals(op, "&&")) {
                    return new BoolValue(n1 && n2);
                }
                if(Objects.equals(op, "||")) {
                    return new BoolValue(n1 || n2);
                }
            } else {
                throw new ExpressionException("Operand 1 is not a boolean");
            }
        } else {
            throw new ExpressionException("Operand 2 is not a boolean");
        }
        return null;
    }

    @Override
    public String toString() {
        return "" + e1 + op + e2;
    }

    @Override
    public InterfaceExp deepCopy() {
        return new LogicExp(op, e1.deepCopy(), e2.deepCopy());
    }
}
