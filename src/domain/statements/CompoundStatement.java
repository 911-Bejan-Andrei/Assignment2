package domain.statements;

import domain.PrgState;
import domain.interfaces.InterfaceStatement;
import domain.adts.MyInterfaceStack;

public class CompoundStatement implements InterfaceStatement {

    InterfaceStatement first;
    InterfaceStatement second;

    public CompoundStatement(InterfaceStatement first, InterfaceStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public PrgState execute(PrgState prgState) {
        MyInterfaceStack<InterfaceStatement> stk = prgState.getExeStack();
        stk.push(second);
        stk.push(first);
        return prgState;
    }

    public InterfaceStatement deepCopy() {
        return new CompoundStatement(first.deepCopy(), second.deepCopy());
    }

    @Override
    public String toString() {
        return first.toString() + "\n" + second.toString();
    }
}
