package com.helloweb.springbootshiro.shiro;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.helloweb.springbootshiro.entity.User;

@Component
public class UserRealm extends AbstractUserRealm {

	@Override
	public UserRolesAndPermissions doGetGroupAuthorizationInfo(User user) {
		Set<String> userRoles = new HashSet<>();
		Set<String> userPermissions = new HashSet<>();

		return new UserRolesAndPermissions(userRoles, userPermissions);
	}

	@Override
	public UserRolesAndPermissions doGetRoleAuthorizationInfo(User user) {
		Set<String> userRoles = new HashSet<>();
		Set<String> userPermissions = new HashSet<>();

		return new UserRolesAndPermissions(userRoles, userPermissions);
	}

}
