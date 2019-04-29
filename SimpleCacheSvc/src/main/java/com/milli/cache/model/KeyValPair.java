package com.milli.cache.model;

public class KeyValPair {
	private Integer key = null;
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	private Integer value = null;
	public KeyValPair(Integer key, Integer val)
	{
		this.key = key;
		this.value= val;
	}
}
