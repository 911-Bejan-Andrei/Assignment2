package domain.adts;

import java.util.List;

public interface MyInterfaceList<T> {
    void add(T v);
    List<T> getList();
}
