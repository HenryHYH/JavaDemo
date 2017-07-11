package com.helloweb.repository;

import java.util.List;

import com.helloweb.entity.User;
import com.helloweb.irepository.IUserRepository;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Start");

		User entity = new User();
		entity.setName("Henry");

		IUserRepository repository = new UserRepository();

		int insertCount = repository.add(entity);
		System.out.println("Insert Count = " + insertCount);
		System.out.println("Insert Id = " + entity.getId());

		entity.setName(entity.getName() + " updated");
		int updateCount = repository.update(entity);
		System.out.println("Update Count = " + updateCount);

		int deleteCount = repository.delete(entity);
		System.out.println("Delete Count = " + deleteCount);

		List<User> list = repository.selectAll();
		if (null == list)
			System.out.println("Count = NULL");
		else {
			System.out.println("Count = " + list.size());

			for (User item : list) {
				System.out.println(item.toString());
			}
		}

		System.out.println("End");
	}
}
