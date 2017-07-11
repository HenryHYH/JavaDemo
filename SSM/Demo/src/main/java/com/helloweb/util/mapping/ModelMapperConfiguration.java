package com.helloweb.util.mapping;

import org.modelmapper.ModelMapper;

public class ModelMapperConfiguration {

	private static final ModelMapper mapper = new ModelMapper();

	static {
		init();
	}

	public static void init() {
		// mapper.addMappings(new PropertyMap<User, UserDto>() {
		// @Override
		// protected void configure() {
		// map().setName(source.getName());
		// }
		// });
	}

	public static ModelMapper instance() {
		return mapper;
	}
}
