package domain.statements;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.MyException;
import com.exception.StatementException;
import com.sun.jdi.Value;
import domain.PrgState;
import domain.adts.MyInterfaceDictionary;
import domain.interfaces.InterfaceStatement;
import domain.interfaces.InterfaceType;
import domain.interfaces.InterfaceValue;

public class VarDeclStatement implements InterfaceStatement {
    String name;
    InterfaceType type;

    public VarDeclStatement(String name, InterfaceType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException, ADTException, StatementException, ExpressionException {
        MyInterfaceDictionary<String, InterfaceValue> symTbl = prgState.getSymTable();
        if(symTbl.isDefined(name)) {
            throw new MyException("Variable is already defined");
        }
        symTbl.put(name, type.defaultValue());
        return prgState;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }

    public InterfaceStatement deepCopy() {
        return new VarDeclStatement(name, type.deepCopy());
    }

}
