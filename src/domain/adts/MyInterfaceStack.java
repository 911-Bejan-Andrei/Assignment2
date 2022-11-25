package domain.adts;

import java.util.List;

public interface MyInterfaceStack<T> {
    T pop();
    void push(T v);
    int search(T v);
    boolean empty();

    List<T> getReversed();
}
