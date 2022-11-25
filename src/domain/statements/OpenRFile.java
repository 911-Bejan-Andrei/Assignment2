package domain.statements;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.StatementException;
import domain.PrgState;
import domain.adts.MyInterfaceDictionary;
import domain.expresions.InterfaceExp;
import domain.interfaces.InterfaceStatement;
import domain.interfaces.InterfaceValue;
import domain.types.StringType;
import domain.values.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements InterfaceStatement{
    private final InterfaceExp expression;

    public OpenRFile(InterfaceExp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException, ADTException {
        InterfaceValue value = expression.eval(state.getSymTable(), state.getHeap());
        if (value.getType().equals(new StringType())) {
            StringValue fileName = (StringValue) value;
            MyInterfaceDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.isDefined(fileName.getValue())) {
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader(fileName.getValue()));
                } catch (FileNotFoundException e) {
                    throw new StatementException(String.format("%s could not be opened", fileName.getValue()));
                }
                fileTable.put(fileName.getValue(), br);
                state.setFileTable(fileTable);
            } else {
                throw new StatementException(String.format("%s is already opened", fileName.getValue()));
            }
        } else {
            throw new StatementException(String.format("%s does not evaluate to StringType", expression.toString()));
        }
        return state;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new OpenRFile(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("OpenReadFile(%s)", expression.toString());
    }

}
