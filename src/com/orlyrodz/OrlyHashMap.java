package com.orlyrodz;

import java.util.ArrayList;

public class OrlyHashMap<K, V> {
    // properties
    int capacity;
    int size;
    ArrayList<Holder<K, V>>[] map;

    // constructors
    public OrlyHashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.map = (ArrayList<Holder<K, V>>[]) new ArrayList[capacity];
        for (int i = 0; i < this.capacity; i++) {
            map[i] = new ArrayList<Holder<K, V>>();
        }
    }

    // supporting data structure
    private class Holder<K, V> {
        K key;
        V value;
        public Holder(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // methods
    public void put(K key, V value) {
        int index = key.hashCode() % capacity;
        map[index].add(new Holder<>(key, value));
    }

    public V get(K key) {
        int index = key.hashCode() % capacity;
        if (map[index].size() == 0) {
            System.out.println("No value available for provided key.");
            return null;
        } else {
            for (Holder<K, V> entry : map[index]) {
                System.out.println("entry.key: " + entry.key + " | key: " + key);
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
            System.out.println("No value available for provided key.");
        }
        return null;
    }
}