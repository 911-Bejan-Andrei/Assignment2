package domain.statements;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.MyException;
import com.exception.StatementException;
import domain.PrgState;
import domain.adts.MyInterfaceDictionary;
import domain.expresions.InterfaceExp;
import domain.interfaces.InterfaceStatement;
import domain.interfaces.InterfaceValue;
import domain.types.StringType;
import domain.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements InterfaceStatement {
    InterfaceExp expression;

    CloseRFile(InterfaceExp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, StatementException, ADTException, ExpressionException {
        InterfaceValue value = expression.eval(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType()))
            throw new StatementException(String.format("%s does not evaluate to StringValue", expression));
        StringValue fileName = (StringValue) value;
        MyInterfaceDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName.getValue()))
            throw new StatementException(String.format("%s is not present in the FileTable", value));
        BufferedReader br = fileTable.lookUp(fileName.getValue());
        try {
            br.close();
        } catch (IOException e) {
            throw new StatementException(String.format("Unexpected error in closing %s", value));
        }
        fileTable.remove(fileName.getValue());
        state.setFileTable(fileTable);
        return state;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new CloseRFile(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("CloseReadFIle(%s", expression.toString());
    }
}
