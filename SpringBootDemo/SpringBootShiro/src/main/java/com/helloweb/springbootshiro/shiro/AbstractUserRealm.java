package com.helloweb.springbootshiro.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.helloweb.springbootshiro.entity.User;
import com.helloweb.springbootshiro.service.UserService;

public abstract class AbstractUserRealm extends AuthorizingRealm {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractUserRealm.class);

	@Autowired
	private UserService userService;

	// 获取用户组的权限信息
	public abstract UserRolesAndPermissions doGetGroupAuthorizationInfo(User user);

	// 获取用户角色的权限信息
	public abstract UserRolesAndPermissions doGetRoleAuthorizationInfo(User user);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		LOG.info("doGetAuthorizationInfo Start");

		String userName = (String) principalCollection.getPrimaryPrincipal();
		Set<String> userRoles = new HashSet<>();
		Set<String> userPermissions = new HashSet<>();
		User user = userService.findByLoginName(userName);
		if (null != user) {
			UserRolesAndPermissions groupContainer = doGetGroupAuthorizationInfo(user);
			UserRolesAndPermissions roleContainer = doGetRoleAuthorizationInfo(user);
			userRoles.addAll(groupContainer.getUserRoles());
			userRoles.addAll(roleContainer.getUserRoles());
			userPermissions.addAll(groupContainer.getUserPermissons());
			userPermissions.addAll(roleContainer.getUserPermissons());
		} else {
			throw new AuthorizationException();
		}

		// 为当前用户设置角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRoles(userRoles);
		authorizationInfo.addStringPermissions(userPermissions);
		LOG.info("###【获取角色成功】[SessionId] => {}", SecurityUtils.getSubject().getSession().getId());

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		User user = userService.findByLoginName(token.getUsername());
		if (null != user)
			return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
		else
			throw new AuthenticationException();
	}

}
