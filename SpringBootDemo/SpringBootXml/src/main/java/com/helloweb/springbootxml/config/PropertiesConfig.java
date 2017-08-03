package com.helloweb.springbootxml.config;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = "classpath:context.properties", reader = PropertiesBeanDefinitionReader.class)
public class PropertiesConfig {

}
