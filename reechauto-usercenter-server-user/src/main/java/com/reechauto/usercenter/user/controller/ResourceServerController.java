package com.reechauto.usercenter.user.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.common.error.ErrorsUtil;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.req.resource.ResourceServerAddRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceServerDeleteRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceServerQueryRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceServerUpdateRequest;
import com.reechauto.usercenter.user.service.resource.ResourceServerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("resourceServer")
public class ResourceServerController {
	@Autowired
	private ResourceServerService resourceService;

	/**
	 * 新增资源服务器
	 * 
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseData addResourceServer(@Valid ResourceServerAddRequest req, BindingResult result) {
		log.info("新增资源服务器");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
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
	public ResponseData queryResourceServer(@Valid ResourceServerQueryRequest req, BindingResult result) {
		log.info("资源服务器列表");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		ResponseData responseData = resourceService.resourceServerList(req);
		return responseData;
	}
	/**
	 * 修改资源服务器
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseData updateResourceServer(@Valid ResourceServerUpdateRequest req, BindingResult result) {
		log.info("修改资源服务器");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		boolean flag = resourceService.updateResourceServer(req.getOldResourceId(),req.getNewResourceId(),req.getNewResourceName());
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("修改资源服务器失败！");
		}
	}
	/**
	 * 删除资源服务器
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseData deleteResourceServer(@Valid ResourceServerDeleteRequest req, BindingResult result) {
		log.info("删除资源服务器");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		boolean flag = resourceService.deleteResourceServer(req.getResourceId());
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("删除资源服务器失败！");
		}
	}

}
