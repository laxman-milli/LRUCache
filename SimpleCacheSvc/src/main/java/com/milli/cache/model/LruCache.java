package com.milli.cache.model;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
 
public class LruCache < K, V > extends LinkedHashMap < K, V > {
 
    private int capacity; 
     
    public LruCache(int capacity) { 
    	//Either increase the capcity by 1 or increase the load factor to be more than 1 to make sure the underlying linkedlist does not get doubled asw soon as we reach the capacity.
         super(capacity+1, 1.0f, true); 
        this.capacity = capacity;
    }
     
    protected boolean removeEldestEntry(Entry entry) {
        return (size() > this.capacity);
    } 
}