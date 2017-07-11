package com.helloweb.repository.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	private static SqlSessionFactory factory;

	static {
		String config = "mybatis-config.xml";
		try {
			InputStream stream = Resources.getResourceAsStream(config);
			factory = new SqlSessionFactoryBuilder().build(stream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession getSession() {
		return factory.openSession();
	}
}
