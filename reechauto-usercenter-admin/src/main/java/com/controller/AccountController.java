package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.common.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.internal.LinkedTreeMap;
import com.http.ResponseCodeEnum;
import com.http.ResponseData;
import com.mapper.UserDetailsMapper;
import com.mapper.UserMapper;
import com.model.User;
import com.model.UserDetails;
import com.model.UserDetailsExample;
import com.service.LoginService;

@RestController
public class AccountController {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserDetailsMapper userDetailsMapper;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public com.reechauto.usercenter.common.resp.ResponseData login(@RequestParam(value = "username",required=false)String username,@RequestParam(value = "password",required=false)String password,
			
    		  @RequestParam(value = "captcha",required=false)String captcha, HttpServletRequest request) throws UnsupportedEncodingException, JsonProcessingException{
		
		HttpSession session = request.getSession();
		//验证码校验
		String code = (String)session.getAttribute(Constants.SESSION_CAPTCHA_CODE_KEY);
		session.removeAttribute(Constants.SESSION_CAPTCHA_CODE_KEY);
		if(StringUtils.isBlank(captcha) || StringUtils.isBlank(code)){
			throw new RuntimeException("验证码不合法");
		}
		if(!captcha.equalsIgnoreCase(code)){
			throw new RuntimeException("验证码错误");
		}
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			throw new RuntimeException("用户名或密码为空");
		}
		com.reechauto.usercenter.common.resp.ResponseData resp = loginService.loginByPassword(username, password);
		return resp;
	}
	
	@RequestMapping(value = "/api/userInfo", method = RequestMethod.GET)
	public com.reechauto.usercenter.common.resp.ResponseData getUserInfo(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		String  user =  (String) redisTemplate.opsForValue().get(Constants.SESSION_LOGIN_USER_KEY);
		System.out.println(user);
		ObjectMapper mapper = new ObjectMapper();
		LinkedTreeMap<String, Object> user1 = mapper.readValue(user, new TypeReference<LinkedTreeMap<String, Object>>() {
		});
		System.out.println(user1);
		return com.reechauto.usercenter.common.resp.ResponseData.ok().data(user);
	}
	
	@RequestMapping(value = "/api/search", method = RequestMethod.GET)
	public Map<String, Object> search(@RequestParam String query,@RequestParam(required=false,defaultValue = "1")Integer page,
            @RequestParam(required=false,defaultValue = "10")Integer limit) throws Exception {
		Map<String, Object> result = new HashMap<>();
		UserDetailsExample example = new UserDetailsExample();
		com.model.UserDetailsExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(query)) {
			criteria.andRealNameLike("%"+query+"%");
		}
		Long total = userDetailsMapper.countByExample(example);
		example.setLimitStart((page-1)*limit);
		example.setOffset(limit);
		List<UserDetails> list = userDetailsMapper.selectByExample(example);
		result.put("count", total);
		result.put("data", list);
		result.put("code", 1000);
		result.put("message", "OK");
		return result;
	}
	
	@RequestMapping(value = "/api/delete", method = RequestMethod.POST)
	public ResponseData deleteUser(Integer userId) throws Exception {
		if (userId == null || userId < 1) {
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "删除失败");
		}
		User user  = userMapper.selectByPrimaryKey(userId);
		if(user == null){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "用户不存在");
		}
		
			userMapper.deleteByPrimaryKey(userId);
		
		return new ResponseData();
	}
	
	@RequestMapping(value = "/api/adminUser/update", method = RequestMethod.POST)
	public ResponseData update(Integer id,String username,String role) {
		if(id== null || id < 1){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "参数错误");
		}
		if(StringUtils.isBlank(username) || username.length() > 64){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "名称参数有误");
		}
		if(StringUtils.isBlank(role) || role.length() > 64){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "角色参数有误");
		}
		User user = userMapper.selectByPrimaryKey(id);
		if(user==null){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "用户不存在");
		}
		try {
			/*Role role1 = new Role();
			role1.setId(id);
			role1.setRolename(rolename);
			roleMapper.updateByPrimaryKeySelective(role1);*/
			User user2 = new User();
			user2.setId(id);
			user2.setUsername(username);
			user2.setRole(role);
			userMapper.updateByPrimaryKeySelective(user2);
			return new ResponseData();
		} catch (RuntimeException e) {
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/adminUser/add", method = RequestMethod.POST)
	public ResponseData add(String mobile,String username,String role) {
		if(StringUtils.isBlank(mobile) ){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "手机号不能为空");
		}
		if(StringUtils.isBlank(username) ){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "用户名不能为空");
		}
		if(StringUtils.isBlank(role) ){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "角色不能为空");
		}
		
		try {
			
			User user = new User();
			user.setMobile(mobile);
			user.setUsername(username);
			user.setRole(role);
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			userMapper.insertSelective(user);
			return new ResponseData();
		} catch (RuntimeException e) {
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS,e.getMessage());
		}
	}
}
