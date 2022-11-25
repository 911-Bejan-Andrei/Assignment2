package domain.adts;

import com.exception.ADTException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class MyDictionary<K, V> implements MyInterfaceDictionary<K, V> {
    HashMap<K, V> hashMap;

    public MyDictionary() {
        this.hashMap = new HashMap<>();
    }

    @Override
    public void put(K key, V value) throws ADTException {
        if(key == null || value == null) {
            throw new ADTException("The key or the value is null");
        } else {
            hashMap.put(key, value);
        }
    }

    @Override
    public Collection<V> values() {
        return this.hashMap.values();
    }

    @Override
    public V get(K key) throws ADTException {
        if(key == null) {
            throw new ADTException("The key is null");
        }
        return hashMap.get(key);
    }

    @Override
    public Set<K> keySet() {
        return hashMap.keySet();
    }

    @Override
    public boolean isDefined(K key) {
        return hashMap.containsKey(key);
    }

    public V lookUp(K key) throws ADTException {
        if (!isDefined(key))
            throw new ADTException( key + " is not defined.");
        return this.hashMap.get(key);
    }

    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public void remove(K key) throws ADTException {
        if (!isDefined(key))
            throw new ADTException(key + " is not defined.");
        this.hashMap.remove(key);
    }

    @Override
    public String toString() {
        return this.hashMap.toString();
    }
}
