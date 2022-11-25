package domain.adts;

import java.util.*;

public class MyStack<T> implements MyInterfaceStack<T> {
    Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    @Override
    public void push(T v) {
        stack.add(v);
    }

    @Override
    public T pop() {
        if(empty()) {
            throw new EmptyStackException();
        }
        return stack.pop();
    }

    @Override
    public int search(T v) {
        return stack.search(v);
    }

    @Override
    public boolean empty() {
        return stack.empty();
    }

    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        Iterator stackIterator = stack.iterator();
        String result = "";
        while(stackIterator.hasNext()) {
            result = result + stackIterator.next() + " ";
        }
        return result;
    }

    @Override
    public List<T> getReversed() {
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }
}
