package com.milli.cache.service;

import org.springframework.stereotype.Component;

import com.milli.cache.model.LruCache;


@Component
public class CacheService {

	private static LruCache<Integer, Integer> lruCache = new LruCache<Integer, Integer>(10);
	
	public Integer get(Integer key) {
		return lruCache.get(key);
	}

	public void put(Integer key, Integer val) {
		lruCache.put(key, val);
		return;
	}
}
