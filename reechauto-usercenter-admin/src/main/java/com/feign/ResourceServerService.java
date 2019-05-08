package com.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.reechauto.usercenter.common.resp.ResponseData;

@FeignClient(name = "usercenter-server-user")
public interface ResourceServerService {

	@PostMapping("/resourceServer/list")
	public ResponseData queryResourceServer(@RequestParam("query") String query,
			@RequestParam("start") Integer start, @RequestParam("pageNum") Integer pageNum,
			@RequestParam("ct") String ct, @RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);

	@PostMapping("/resourceServer/add")
	public ResponseData addResourceServer(@RequestParam("resourceId") String resourceId,
			@RequestParam("resourceName") String resourceName, @RequestParam("ct") String ct,
			@RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);

	@PostMapping("/resourceServer/update")
	public ResponseData updateResourceServer(@RequestParam("oldResourceId") String oldResourceId,
			@RequestParam("newResourceId") String newResourceId,
			@RequestParam("newResourceName") String newResourceName, @RequestParam("ct") String ct,
			@RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);

	@PostMapping("/resourceServer/delete")
	public ResponseData deleteResourceServer(@RequestParam("resourceId") String resourceId,
			@RequestParam("ct") String ct, @RequestParam("cv") String cv, @RequestParam("timeStamp") Long timeStamp);
}
