package com.reechauto.usercenter.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.req.resource.ResouceServiceAddRequest;
import com.reechauto.usercenter.user.entity.ResourceServer;
import com.reechauto.usercenter.user.service.resource.ResourceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("resourceserver")
public class ResourceServerController {
	@Autowired
	private ResourceService resourceService;

	/**
	 * 新增资源服务器
	 * 
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseData addResourceServer(@Valid ResouceServiceAddRequest req, BindingResult result) {
		log.info("新增资源服务器");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = resourceService.addResourceService(req.getResourceId(), req.getResourceName());
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("添加资源服务器失败！");
		}
	}

	/**
	 * 资源服务器列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseData queryResourceServer() {
		log.info("资源服务器列表");
		List<ResourceServer> list = resourceService.resourceServerList();
		return ResponseData.ok().data(list);
	}

}
