package domain.statements;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.StatementException;
import domain.PrgState;
import domain.expresions.InterfaceExp;
import domain.interfaces.InterfaceStatement;
import domain.adts.MyInterfaceStack;
import domain.interfaces.InterfaceValue;
import domain.values.BoolValue;

public class IfStatement implements InterfaceStatement {
    InterfaceExp exp;
    InterfaceStatement thenS;
    InterfaceStatement elseS;

    public PrgState execute(PrgState state) throws ADTException, ExpressionException, StatementException {
        InterfaceValue result = this.exp.eval(state.getSymTable());
        if(result instanceof BoolValue boolResult) {
            InterfaceStatement statement;
            if (boolResult.getValue()) {
                statement = thenS;
            } else {
                statement = elseS;
            }
            MyInterfaceStack<InterfaceStatement> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            return state;
        }
        else {
            throw new StatementException("there is no boolean expression in the if statement");
        }
    }

    public IfStatement(InterfaceExp e, InterfaceStatement t, InterfaceStatement el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "(IF(" + exp.toString()+") THEN(" + thenS.toString()
                + ")ELSE("+elseS.toString()+"))";
    }

    public IfStatement deepCopy() {
        return new IfStatement(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}
