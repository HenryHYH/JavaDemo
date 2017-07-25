package com.helloweb.utildemo.serialization;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class TestXmlObject {

	private String name;
	private int age;
	private Date birthday;
	private List<TestXmlFavorite> favorites;

	@XmlElement(name = "nickname")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@XmlElementWrapper(name = "favorites")
	@XmlElement(name = "favorite")
	public List<TestXmlFavorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<TestXmlFavorite> favorites) {
		this.favorites = favorites;
	}
}
