package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.http.ResponseCodeEnum;
import com.http.ResponseData;
import com.mapper.RoleMapper;
import com.model.Role;
import com.model.RoleExample;
import com.model.RoleExample.Criteria;


@RestController
public class RoleController {

	@Autowired
	private RoleMapper roleMapper;
	
	
	@RequestMapping(value = "/api/adminRole/selectList", method = RequestMethod.POST)
	public Map<String, Object> search(String name) throws Exception {
	//	query = URLDecoder.decode(query, "UTF-8");
		Map<String, Object> result = new HashMap<>();
		/*List<User> userList = new ArrayList<>();
		AccountUserEntity user = accountUserService.getByIdentify(query);
		if (user != null) {
			userList.add(user);
		}*/
		/*UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(name);
		List<User> list = userMapper.selectByExample(example);*/
		
		RoleExample roleExample = new RoleExample();
		Criteria criteria = roleExample.createCriteria();
		if (!StringUtils.isBlank(name)) {
			criteria.andRolenameEqualTo(name);
		}
		
		List<Role> list = roleMapper.selectByExample(roleExample);
		result.put("count", list.size());
		result.put("data", list);
		result.put("code", ResponseCodeEnum.OK.getCode());
		result.put("message", ResponseCodeEnum.OK.getMessage());
		return result;
	}
	
	@RequestMapping(value = "/api/adminRole/delete", method = RequestMethod.POST)
	public ResponseData deleteUser(Integer id) throws Exception {
		if (id == null || id < 1) {
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "删除失败");
		}
		Role role = roleMapper.selectByPrimaryKey(id);
		if(role == null){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "用户不存在");
		}
		
			roleMapper.deleteByPrimaryKey(id);
		
		return new ResponseData();
	}
	
	@RequestMapping(value = "/api/adminRole/add", method = RequestMethod.POST)
	public ResponseData add(String rolename,String code) {
		if(StringUtils.isBlank(rolename)||StringUtils.isBlank(code) ){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "角色名称或编码不能为空");
		}
		
		try {
			Role role = new Role();
			role.setCode(code);
			role.setRolename(rolename);
			role.setCreateTime(new Date());
			role.setUpdateTime(new Date());
			roleMapper.insertSelective(role);
			
			return new ResponseData();
		} catch (RuntimeException e) {
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/adminRole/update", method = RequestMethod.POST)
	public ResponseData update(Integer id,String rolename) {
		if(id== null || id < 1){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "参数错误");
		}
		if(StringUtils.isBlank(rolename) || rolename.length() > 64){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "名称参数有误");
		}
		Role role = roleMapper.selectByPrimaryKey(id);
		if(role==null){
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS, "角色不存在");
		}
		try {
			Role role1 = new Role();
			role1.setId(id);
			role1.setRolename(rolename);
			roleMapper.updateByPrimaryKeySelective(role1);
			return new ResponseData();
		} catch (RuntimeException e) {
			return new ResponseData(ResponseCodeEnum.INVALID_PARAMETERS,e.getMessage());
		}
	}
}
