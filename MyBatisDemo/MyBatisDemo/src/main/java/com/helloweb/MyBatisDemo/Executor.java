package com.helloweb.MyBatisDemo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.helloweb.infrastructure.db.MyBatisUtil;

public class Executor {
	public <T> List<T> select(Class<T> type, String statement) {

		List<T> list = null;
		try (SqlSession session = MyBatisUtil.getSqlSession()) {
			list = session.selectList(statement);
		}

		return list;
	}

	public <T> int insert(T entity, String statement) {
		int count = 0;

		try (SqlSession session = MyBatisUtil.getSqlSession()) {
			count = session.insert(statement, entity);
			session.commit();
		}

		return count;
	}

	public <T> int update(T entity, String statement) {
		int count = 0;

		try (SqlSession session = MyBatisUtil.getSqlSession()) {
			count = session.update(statement, entity);
			session.commit();
		}

		return count;
	}

	public <T> int delete(T entity, String statement) {
		int count = 0;

		try (SqlSession session = MyBatisUtil.getSqlSession()) {
			count = session.delete(statement, entity);
			session.commit();
		}

		return count;
	}
}
