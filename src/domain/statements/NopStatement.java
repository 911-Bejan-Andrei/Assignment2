package domain.statements;

import com.exception.MyException;
import domain.PrgState;
import domain.interfaces.InterfaceStatement;

public class NopStatement implements InterfaceStatement {
    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        return null;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new NopStatement();
    }
}
