package com.helloweb.MyBatisDemo;

import java.util.List;

import com.helloweb.entity.Test;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Start");

		Executor exe = new Executor();

		Test entity = new Test();
		entity.setName("Hello1");
		int insertCount = exe.insert(entity, "com.helloweb.entity.Test.insertAndGetId");
		System.out.println("Insert Count = " + insertCount);
		System.out.println("Insert Id = " + entity.getId());

		entity.setName("Hello updated");
		int updateCount = exe.update(entity, "com.helloweb.entity.Test.update");
		System.out.println("Update Count = " + updateCount);

		int deleteCount = exe.delete(entity.getId(), "com.helloweb.entity.Test.deleteById");
		System.out.println("Delete Count = " + deleteCount);

		List<Test> list = exe.select(Test.class, "com.helloweb.entity.Test.query");
		if (null == list)
			System.out.println("Count = NULL");
		else
			System.out.println("Count = " + list.size());

		System.out.println("End");
	}
}
