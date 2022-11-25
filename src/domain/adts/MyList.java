package domain.adts;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyInterfaceList<T> {
    List<T> list;

    public MyList() {
        this.list = new ArrayList<>();
    }
    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(T e : list) {
            result.append(e.toString()).append(" ");
        }
        return result.toString();
    }
}
