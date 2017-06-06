package com.homelearning;

import java.util.LinkedHashMap;

public class LibraryCache<K, V> {
    private LinkedHashMap<K, V> map;
    private int maxSize;

    public LibraryCache(int maxSize) {
        map = new LinkedHashMap<>();
        this.maxSize = maxSize;
    }

    public LibraryCache() {
        this(10);
    }

    public V get(K key){
        return map.get(key);
    }

    public V put (K key, V value){
        if (map.size() == maxSize) map.remove(map.keySet().iterator().next());
        return map.put(key, value);
    }
}
