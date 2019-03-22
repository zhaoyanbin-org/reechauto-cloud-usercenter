package com.reechauto.usercenter.auth.controller;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.auth.entity.user.ReechUser;
import com.reechauto.usercenter.auth.mapper.UserMapper;
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ReechUser user = userMapper.getReechUserByUserId(Long.parseLong(authentication.getName()));
		return ResponseData.ok().data(user);
	}

}
