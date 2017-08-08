package com.helloweb.springbootshiro.shiro;

import java.util.Set;

public class UserRolesAndPermissions {

	private Set<String> userRoles;
	private Set<String> userPermissons;

	public UserRolesAndPermissions(
			Set<String> userRoles,
			Set<String> userPermissions) {
		this.userRoles = userRoles;
		this.userPermissons = userPermissions;
	}

	public Set<String> getUserRoles() {
		return userRoles;
	}

	public Set<String> getUserPermissons() {
		return userPermissons;
	}

}
