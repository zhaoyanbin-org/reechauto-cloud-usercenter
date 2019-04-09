package com.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.feign.ResourceScopeService;
import com.reechauto.usercenter.common.resp.ResponseData;

@RestController
public class ResourceScopeController {

	@Autowired
	private ResourceScopeService resourceScopeService;
	
	@RequestMapping(value = "/resourceScope/query", method = RequestMethod.GET)
	public Map<String, Object> queryResourceServer(@RequestParam String query,@RequestParam(required=false,defaultValue = "1")Integer page,
            @RequestParam(required=false,defaultValue = "10")Integer limit ){
		ResponseData responseData =  resourceScopeService.queryResourceScope((page-1)*limit,limit,"ct","cv",0L);
		Map<String, Object> result = new HashMap<String,Object>();
		System.out.println(responseData.getData().get("total"));
		System.out.println(responseData.getData().get("context"));
		System.out.println(responseData.getCode());
		result.put("count", responseData.getData().get("total"));
		result.put("data", responseData.getData().get("context"));
		result.put("code", 1000);
		result.put("message", responseData.getMessage());
		return result;
	}
	
	@RequestMapping(value = "/resourceScope/add", method = RequestMethod.POST)
	public ResponseData add(String scope,String scopeTips,String resourceId) {
		if(StringUtils.isBlank(scope) ){
			throw new RuntimeException("资源范围不能为空");
		}
		if(StringUtils.isBlank(scopeTips) ){
			throw new RuntimeException("资源范围说明不能为空");
		}
		if(StringUtils.isBlank(resourceId) ){
			throw new RuntimeException("资源服务器ID不能为空");
		}
		ResponseData responseData = resourceScopeService.addResourceScope(scope,scopeTips, resourceId,"ct","ccv",0L);
		return responseData;
	}
	
	@RequestMapping(value = "/resourceScope/update", method = RequestMethod.POST)
	public ResponseData update(Integer id,String scope,String scopeTips,String resourceId) {
		if(StringUtils.isBlank(scope) ){
			throw new RuntimeException("资源范围不能为空");
		}
		if(StringUtils.isBlank(scopeTips) ){
			throw new RuntimeException("资源范围说明不能为空");
		}
		if(StringUtils.isBlank(resourceId) ){
			throw new RuntimeException("资源服务器ID不能为空");
		}
		ResponseData responseData = resourceScopeService.updateResourceScope(id,scope,scopeTips,resourceId,"ct","ccv",0L);
		return responseData;
	}
	
	@RequestMapping(value = "/resourceScope/delete", method = RequestMethod.POST)
	public ResponseData delete(Integer id) {
		if(id==null ){
			throw new RuntimeException("旧资源服务器ID不能为空");
		}
		ResponseData responseData = resourceScopeService.deleteResourceScope(id,"ct","ccv",0L);
		return responseData;
	}
}
