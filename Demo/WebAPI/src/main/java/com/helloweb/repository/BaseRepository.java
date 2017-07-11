package com.helloweb.repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.helloweb.entity.BaseEntity;
import com.helloweb.irepository.IRepository;
import com.helloweb.repository.util.MybatisUtil;

public abstract class BaseRepository<T extends BaseEntity<?>> implements IRepository<T> {

	@Override
	public int add(T entity) {
		int count = 0;

		try (SqlSession session = getSession()) {
			count = session.insert(getStatement("insert"), entity);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int update(T entity) {
		int count = 0;

		try (SqlSession session = getSession()) {
			count = session.update(getStatement("updateById"), entity);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int delete(T entity) {
		int count = 0;

		try (SqlSession session = getSession()) {
			count = session.delete(getStatement("deleteById"), entity.getId());
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public List<T> selectAll() {

		List<T> list = null;

		try (SqlSession session = getSession()) {
			list = session.selectList(getStatement("query"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null == list)
			list = new ArrayList<T>();

		return list;
	}

	private SqlSession getSession() {
		return MybatisUtil.getSession();
	}

	private String getStatement(String statement) {
		return String.format("%s.%s", getTypeName(), statement);
	}

	private String getTypeName() {
		Type sooper = getClass().getGenericSuperclass();
		Type t = ((ParameterizedType) sooper).getActualTypeArguments()[0];

		return t.getTypeName();
	}
}
