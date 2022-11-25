package domain.adts;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.StatementException;

import java.util.Collection;
import java.util.Set;

public interface MyInterfaceDictionary<K, V> {
    void put(K key, V value)  throws StatementException, ExpressionException, ADTException;
    Collection<V> values();
    V get(K key)  throws StatementException, ExpressionException, ADTException;
    V lookUp(K key) throws ADTException;
    Set<K> keySet();
    boolean isDefined(K key);
    boolean isEmpty();

    void remove(K key) throws ADTException;
}

