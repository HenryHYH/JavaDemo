package com.helloweb.infrastructure.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private static SqlSessionFactory factory;

	static {
		try {
			// 获取配置文件资源
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			// 获取 SqlSessionFactory 对象
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取SqlSession对象
	 * 
	 * @return SqlSession
	 */
	public static SqlSession getSqlSession() {
		return factory.openSession();
	}

	/**
	 * 关闭SqlSession对象
	 * 
	 * @param session
	 */
	public static void closeSqlSession(SqlSession session) {
		if (null != session) {
			session.close();
		}
		session = null;
	}
}
