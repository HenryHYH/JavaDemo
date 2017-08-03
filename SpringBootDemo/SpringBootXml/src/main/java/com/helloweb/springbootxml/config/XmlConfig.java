package com.helloweb.springbootxml.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "context.xml", "com/helloweb/springbootxml/beans/context.xml" })
public class XmlConfig {

}
