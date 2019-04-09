package com.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.feign.ResourceServerService;
import com.reechauto.usercenter.common.resp.ResponseData;

@RestController
public class ResourceServerController {

	@Autowired
	private ResourceServerService resourceServerService;
	
	@RequestMapping(value = "/resourceServer/query", method = RequestMethod.GET)
	public Map<String, Object> queryResourceServer(@RequestParam String query,@RequestParam(required=false,defaultValue = "1")Integer page,
            @RequestParam(required=false,defaultValue = "10")Integer limit ){
		ResponseData responseData =  resourceServerService.queryResourceServer(query,(page-1)*limit,limit,"ct","cv",0L);
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
	
	@RequestMapping(value = "/resourceServer/add", method = RequestMethod.POST)
	public ResponseData add(String resourceId,String resourceName) {
		if(StringUtils.isBlank(resourceId) ){
			throw new RuntimeException("资源服务器ID不能为空");
		}
		if(StringUtils.isBlank(resourceName) ){
			throw new RuntimeException("资源服务器名称不能为空");
		}
		ResponseData responseData = resourceServerService.addResourceServer(resourceId, resourceName,"ct","ccv",0L);
		return responseData;
	}
	
	@RequestMapping(value = "/resourceServer/update", method = RequestMethod.POST)
	public ResponseData update(String oldResourceId,String newResourceId,String newResourceName) {
		if(StringUtils.isBlank(oldResourceId) ){
			throw new RuntimeException("旧资源服务器ID不能为空");
		}
		if(StringUtils.isBlank(newResourceId) ){
			throw new RuntimeException("新资源服务器ID不能为空");
		}
		if(StringUtils.isBlank(newResourceName) ){
			throw new RuntimeException("新资源服务器ID不能为空");
		}
		ResponseData responseData = resourceServerService.updateResourceServer(oldResourceId, newResourceId,newResourceName,"ct","ccv",0L);
		return responseData;
	}
	
	@RequestMapping(value = "/resourceServer/delete", method = RequestMethod.POST)
	public ResponseData delete(String resourceId) {
		if(StringUtils.isBlank(resourceId) ){
			throw new RuntimeException("旧资源服务器ID不能为空");
		}
		ResponseData responseData = resourceServerService.deleteResourceServer(resourceId,"ct","ccv",0L);
		return responseData;
	}
}
