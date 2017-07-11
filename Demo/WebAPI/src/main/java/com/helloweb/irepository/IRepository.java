package com.helloweb.irepository;

import java.util.List;

import com.helloweb.entity.BaseEntity;

public interface IRepository<T extends BaseEntity<?>> {

	List<T> selectAll();

	int add(T entity);

	int update(T entity);

	int delete(T entity);
}
