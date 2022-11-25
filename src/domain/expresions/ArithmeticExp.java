package domain.expresions;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.MyException;
import com.exception.StatementException;
import com.sun.jdi.Value;
import domain.adts.MyInterfaceDictionary;
import domain.interfaces.InterfaceValue;
import domain.types.IntType;
import domain.values.IntValue;

public class ArithmeticExp implements InterfaceExp {
    String op;
    InterfaceExp e1;
    InterfaceExp e2;

    public ArithmeticExp(String op, InterfaceExp e1, InterfaceExp e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public InterfaceValue eval(MyInterfaceDictionary<String, InterfaceValue> tbl) throws ADTException, StatementException, ExpressionException {
        InterfaceValue v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                switch (op) {
                    case ("+"):
                        return new IntValue(n1 + n2);
                    case ("-"):
                        return new IntValue(n1 - n2);
                    case ("*"):
                        return new IntValue(n1 * n2);
                    case ("/"): {
                        if (n2 == 0) throw new ExpressionException("division by zero");
                        return new IntValue(n1 / n2);
                    }
                }
            } else {
                throw new ExpressionException("second operand is not an integer");
            }
        } else {
            throw new ExpressionException("first operand is not an integer");
        }
        return null;
    }

    @Override
    public String toString() {
        return "" + e1 + op + e2;
    }

    @Override
    public InterfaceExp deepCopy() {
        return null;
    }
}
