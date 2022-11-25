package domain.adts;

import com.exception.ADTException;
import domain.interfaces.InterfaceValue;

import java.util.HashMap;
import java.util.Set;

public interface MyInterfaceHeap {
    int getFreeValue();
    HashMap<Integer, InterfaceValue> getContent();
    void setContent(HashMap<Integer, InterfaceValue> newMap);
    int add(InterfaceValue value);
    void update(Integer position, InterfaceValue value) throws ADTException;
    InterfaceValue get(Integer position) throws ADTException;
    boolean containsKey(Integer position);
    void remove(Integer key) throws ADTException;
    Set<Integer> keySet();
}
