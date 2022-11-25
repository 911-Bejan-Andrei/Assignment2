package domain.interfaces;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.MyException;
import com.exception.StatementException;
import domain.PrgState;

public interface InterfaceStatement {
    PrgState execute(PrgState state) throws StatementException, ADTException, ExpressionException;

    InterfaceStatement deepCopy();
}
