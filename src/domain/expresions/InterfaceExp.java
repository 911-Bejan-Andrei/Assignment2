package domain.expresions;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.StatementException;
import domain.adts.MyInterfaceDictionary;
import domain.adts.MyInterfaceHeap;
import domain.interfaces.InterfaceValue;

public interface InterfaceExp {
    InterfaceValue eval(MyInterfaceDictionary<String, InterfaceValue> symTable, MyInterfaceHeap heap) throws ExpressionException, StatementException, ADTException;

    InterfaceExp deepCopy();
}
