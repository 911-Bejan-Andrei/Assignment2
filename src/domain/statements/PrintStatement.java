package domain.statements;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.MyException;
import com.exception.StatementException;
import com.sun.jdi.Value;
import domain.PrgState;
import domain.adts.MyInterfaceDictionary;
import domain.adts.MyInterfaceList;
import domain.expresions.InterfaceExp;
import domain.expresions.ValueExp;
import domain.interfaces.InterfaceStatement;
import domain.interfaces.InterfaceValue;

public class PrintStatement implements InterfaceStatement {
    InterfaceExp exp;

    public PrintStatement(InterfaceExp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" + exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState prgState) throws ADTException, StatementException, ExpressionException {
        MyInterfaceDictionary<String, InterfaceValue> symbolTable = prgState.getSymTable();
        MyInterfaceList<InterfaceValue> list = prgState.getOut();

        InterfaceValue value = exp.eval(symbolTable);

        list.add(value);

        return prgState;
    }

    @Override
    public PrintStatement deepCopy() {
        return new PrintStatement(exp.deepCopy());
    }
}
