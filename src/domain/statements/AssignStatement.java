package domain.statements;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.StatementException;
import domain.PrgState;
import domain.adts.MyInterfaceDictionary;
import domain.expresions.InterfaceExp;
import domain.interfaces.InterfaceStatement;
import domain.interfaces.InterfaceType;
import domain.interfaces.InterfaceValue;

public class AssignStatement implements InterfaceStatement {
    String id;
    InterfaceExp exp;

    public AssignStatement(String id, InterfaceExp valueExp) {
        this.id = id;
        this.exp = valueExp;
    }

    @Override
    public PrgState execute(PrgState prgState) throws  ADTException, StatementException, ExpressionException {
        MyInterfaceDictionary<String, InterfaceValue> symTbl = prgState.getSymTable();
        if (symTbl.isDefined(id)) {
            InterfaceValue val = exp.eval(symTbl);
            InterfaceType typId = (symTbl.get(id)).getType();
            if(val.getType().equals(typId)) {
                symTbl.put(id, val);
            } else {
                throw new StatementException("declared type of variable " + id + " and type of the assigned expression do not match");
            }
        } else {
            throw  new StatementException("the used variable" + id + "was not declared before");
        }
        return prgState;
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new AssignStatement(id, exp.deepCopy());
    }
}
