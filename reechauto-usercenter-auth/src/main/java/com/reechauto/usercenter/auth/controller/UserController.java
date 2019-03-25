package com.reechauto.usercenter.auth.controller;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.auth.entity.user.ReechUser;
import com.reechauto.usercenter.auth.mapper.UserMapper;
import com.reechauto.usercenter.auth.service.oauth2.SecurityUtils;
import com.reechauto.usercenter.common.resp.ResponseData;

@RestController
public class UserController {
	@Resource
	private UserMapper userMapper;
		
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@RequestMapping("/userinfo")
	public ResponseData userInfo() {
		ReechUser user = SecurityUtils.getCurrentUser();
		return ResponseData.ok().data(user);
	}
	
	@RequestMapping("/user2")
	public ReechUser user2() {
		return SecurityUtils.getCurrentUser();
	}

}
