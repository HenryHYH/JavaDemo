package com.helloweb.springbootvalidate.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.helloweb.springbootvalidate.model.Person;

@Controller
public class HomeController extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm(Person form) {
		return "form";
	}

	@PostMapping("/")
	public String postForm(@Valid Person form, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "form";

		return "redirect:/results";
	}

}
