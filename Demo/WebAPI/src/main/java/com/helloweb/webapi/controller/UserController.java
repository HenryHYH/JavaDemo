package com.helloweb.webapi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.helloweb.entity.User;
import com.helloweb.irepository.IUserRepository;
import com.helloweb.repository.UserRepository;

@Controller
public class UserController {

	@RequestMapping("/user")
	public ModelAndView index() {

		test();

		ModelAndView result = new ModelAndView("user/index");

		return result;
	}

	private void test() {
		System.out.println("Web Api Start");

		User entity = new User();
		entity.setName("Henry");

		IUserRepository repository = new UserRepository();

		System.out.println("In");

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

		System.out.println("Web Api End");
	}
}
