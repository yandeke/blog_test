package com.ydk.service;

public interface BaseService<T> {
	
	public void addObject(T t);
	
	public T findById(Long id);
	
}
