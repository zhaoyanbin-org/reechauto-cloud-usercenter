package com.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reechauto.usercenter.common.resp.ResponseData;

@FeignClient(name = "usercenter-server-user1")
public interface ResourceScopeService {

	@PostMapping("/resourceScope/list")
	public ResponseData queryResourceScope(@RequestParam("start") Integer start,
			@RequestParam("pageNum") Integer pageNum, @RequestParam("ct") String ct, @RequestParam("cv") String cv,
			@RequestParam("timeStamp") Long timeStamp);

	@PostMapping("/resourceScope/add")
	public ResponseData addResourceScope(@RequestParam("scope") String scope,
			@RequestParam("scopeTips") String scopeTips, @RequestParam("resourceId") String resourceId,
			@RequestParam("ct") String ct, @RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);

	@PostMapping("/resourceScope/update")
	public ResponseData updateResourceScope(@RequestParam("id") Integer id, @RequestParam("scope") String scope,
			@RequestParam("scopeTips") String scopeTips, @RequestParam("resourceId") String resourceId,
			@RequestParam("ct") String ct, @RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);

	@PostMapping("/resourceScope/delete")
	public ResponseData deleteResourceScope(@RequestParam("id") Integer id, @RequestParam("ct") String ct,
			@RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);

}
