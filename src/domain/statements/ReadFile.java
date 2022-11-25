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
import domain.types.IntType;
import domain.types.StringType;
import domain.values.IntValue;
import domain.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements InterfaceStatement {

    InterfaceExp expression;
    String varName;
    public ReadFile(InterfaceExp expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, StatementException, ADTException, ExpressionException {
        MyInterfaceDictionary<String, InterfaceValue> symTable = state.getSymTable();
        MyInterfaceDictionary<String, BufferedReader> fileTable = state.getFileTable();

        if (symTable.isDefined(varName)) {
            InterfaceValue value = symTable.lookUp(varName);
            if (value.getType().equals(new IntType())) {
                value = expression.eval(symTable, state.getHeap());
                if (value.getType().equals(new StringType())) {
                    StringValue castValue = (StringValue) value;
                    if (fileTable.isDefined(castValue.getValue())) {
                        BufferedReader br = fileTable.lookUp(castValue.getValue());
                        try {
                            String line = br.readLine();
                            if (line == null) {
                                line = "0";
                            }
                            symTable.put(varName, new IntValue(Integer.parseInt(line)));
                        } catch (IOException e) {
                            throw new StatementException(String.format("Could not read from file %s", castValue));
                        }
                    } else {
                        throw new StatementException(String.format("The file table does not contain %s", castValue));
                    }
                }else {
                    throw new StatementException(String.format("%s does not evaluate to StringType", value));
                }
            } else {
                throw new StatementException(String.format("%s is not of type IntType", value));
            }
        }else {
            throw new StatementException(String.format("%s is not present in the symTable.", varName));
        }
        return state;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new ReadFile(expression.deepCopy(), varName);
    }

    @Override
    public String toString() {
        return String.format("ReadFile(%s, %s)", expression.toString(), varName);
    }
}
