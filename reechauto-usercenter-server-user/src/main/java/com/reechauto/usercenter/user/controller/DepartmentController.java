package com.reechauto.usercenter.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.ReechDepartment;
import com.reechauto.usercenter.user.entity.UserAccount;
import com.reechauto.usercenter.user.entity.UserAccountExample;
import com.reechauto.usercenter.user.mapper.UserAccountMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DepartmentController {
	@Autowired
	private UserAccountMapper userAccountMapper;
  
	@PostMapping("/reech/depart")
	public ResponseData getDepartmentInfo(String departMentId) {
		log.info("返回部门信息");
		ReechDepartment depart = new ReechDepartment();
		depart.setDepartmentId(departMentId);
		depart.setDepartmentName("智能网联");
		depart.setHeader("大BOSS");
		return ResponseData.ok().data(depart);
	}
	
	@PostMapping("/api/reech/demo")
	public ResponseData demo() {
		log.info("用户信息");
		UserAccountExample example=new UserAccountExample();
		List<UserAccount> list = userAccountMapper.selectByExample(example);

		return ResponseData.ok().data(list);
	}
}
