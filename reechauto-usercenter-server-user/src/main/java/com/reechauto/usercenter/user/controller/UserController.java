package com.reechauto.usercenter.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.UserEntity;
import com.reechauto.usercenter.user.bean.enums.AccountType;
import com.reechauto.usercenter.user.bean.req.user.MobileRegisterReq;
import com.reechauto.usercenter.user.bean.req.user.UserAccountBindReq;
import com.reechauto.usercenter.user.bean.req.user.UserDetailModifyReq;
import com.reechauto.usercenter.user.bean.req.user.UserRegisterReq;
import com.reechauto.usercenter.user.service.user.UserServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserServer userServer;

	/**
	 * 注册用户
	 * 
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseData registerUser(@Valid UserRegisterReq req, BindingResult result) {
		log.info("注册新用户");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		Long userId = userServer.registerUser(req.getAccountNum(), req.getPassword());
		UserEntity userEntity = userServer.queryByUserId(userId);
		return ResponseData.ok().data(userEntity);
	}
	
	@RequestMapping(value = "/register/mobile", method = RequestMethod.POST)
	public ResponseData registerUserMobile(@Valid MobileRegisterReq req, BindingResult result) {
		log.info("手机注册新用户");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		Long userId = userServer.registerUser(req.getMobile(), req.getPassword());
		UserEntity userEntity = userServer.queryByUserId(userId);
		return ResponseData.ok().data(userEntity);
	}

	/**
	 * 绑定Email
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/bind/email", method = RequestMethod.POST)
	public ResponseData bindUserEmail(@Valid UserAccountBindReq req, BindingResult result) {
		log.info("绑定Email");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		UserEntity userEntity = userServer.accountBind(req.getUserId(), req.getAccountNum(), AccountType.email);
		return ResponseData.ok().data(userEntity);
	}

	/**
	 * 绑定手机号
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/bind/mobile", method = RequestMethod.POST)
	public ResponseData bindUserMobile(@Valid UserAccountBindReq req, BindingResult result) {
		log.info("绑定手机");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		UserEntity userEntity = userServer.accountBind(req.getUserId(), req.getAccountNum(), AccountType.mobile);
		return ResponseData.ok().data(userEntity);
	}

	/**
	 * 绑定身份证
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/bind/idcard", method = RequestMethod.POST)
	public ResponseData bindUserIdCard(@Valid UserAccountBindReq req, BindingResult result) {
		log.info("绑定身份证");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		UserEntity userEntity = userServer.accountBind(req.getUserId(), req.getAccountNum(), AccountType.idcard);
		return ResponseData.ok().data(userEntity);
	}

	/**
	 * 绑定帐号
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/bind/account", method = RequestMethod.POST)
	public ResponseData bindUserAccount(@Valid UserAccountBindReq req, BindingResult result) {
		log.info("绑定account");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		UserEntity userEntity = userServer.accountBind(req.getUserId(), req.getAccountNum(), AccountType.account);
		return ResponseData.ok().data(userEntity);
	}
	
	/**
	 * 修改用户信息
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/userdetails/modify", method = RequestMethod.POST)
	public ResponseData modifyUserDetail(@Valid UserDetailModifyReq req, BindingResult result) {
		log.info("修改用户信息");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		UserEntity userEntity = userServer.modifyUserDetail(req);
		return ResponseData.ok().data(userEntity);
	}

}
