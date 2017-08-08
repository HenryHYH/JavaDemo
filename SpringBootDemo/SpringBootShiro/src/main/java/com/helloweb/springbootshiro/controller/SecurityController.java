package com.helloweb.springbootshiro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.helloweb.springbootshiro.entity.User;

@Controller
public class SecurityController {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityController.class);

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@Valid User user,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (bindingResult.hasErrors())
			return "login";

		String loginName = user.getName();
		LOG.info("准备登录用户 => {}", loginName);
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, user.getPassword());
		Subject currentUser = SecurityUtils.getSubject();
		try {
			LOG.info("对用户[" + loginName + "]进行登录验证..验证开始");
			currentUser.login(token);
			LOG.info("对用户[" + loginName + "]进行登录验证..验证通过");
		} catch (UnknownAccountException e) {
			LOG.info("对用户[" + loginName + "]进行登录验证..验证未通过，未知账户");
			redirectAttributes.addFlashAttribute("message", "未知账户");
		} catch (IncorrectCredentialsException e) {
			LOG.info("对用户[" + loginName + "]进行登录验证..验证未通过，密码不正确");
			redirectAttributes.addFlashAttribute("message", "密码不正确");
		} catch (LockedAccountException e) {
			LOG.info("对用户[" + loginName + "]进行登录验证..验证未通过，账户已锁定");
			redirectAttributes.addFlashAttribute("message", "账户已锁定");
		} catch (ExcessiveAttemptsException e) {
			LOG.info("对用户[" + loginName + "]进行登录验证..验证未通过，用户名或密码错误次数过多");
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
		} catch (AuthenticationException e) {
			LOG.info("对用户[" + loginName + "]进行登录验证..验证未通过，用户名或密码不正确");
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
		}

		if (currentUser.isAuthenticated()) {
			LOG.info("用户[" + loginName + "]登录认证通过");
			String url = WebUtils.getSavedRequest(request).getRequestUrl();
			LOG.info("URL = {}", url);
			return String.format("redirect:%s", url);
		} else {
			token.clear();
			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout(RedirectAttributes redirectAttributes) {
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");

		return "redirect:/login";
	}

}
