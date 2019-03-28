package com.reechauto.usercenter.user.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reechauto.usercenter.common.error.ErrorsUtil;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeAddRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeDeleteRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeQueryRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeUpdateRequest;
import com.reechauto.usercenter.user.bean.req.resource.ScopeAddRequest;
import com.reechauto.usercenter.user.bean.req.resource.ScopeDeleteRequest;
import com.reechauto.usercenter.user.bean.req.resource.ScopeUpdateRequest;
import com.reechauto.usercenter.user.service.resource.ResourceScopeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("resourceScope")
public class ResourceScopeController {
	@Autowired
	private ResourceScopeService resourceScopeService;
	/**
	 * 新增资源范围
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseData addResourceScope(@Valid ResourceScopeAddRequest req, BindingResult result) {
		log.info("新增资源范围");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		boolean flag = resourceScopeService.addResourceScope(req.getScope(),req.getScopeTips(),req.getResourceId());
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("添加资源范围失败！");
		}
	}
	/**
	 * 资源范围列表
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseData queryResourceScope(@Valid ResourceScopeQueryRequest req,BindingResult result) {
		log.info("资源范围列表");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		ResponseData res = resourceScopeService.resourceScopeList(req);
		return res;
	}
	/**
	 * 修改资源范围
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseData updateResourceScope(@Valid ResourceScopeUpdateRequest req, BindingResult result) {
		log.info("修改资源范围");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		boolean flag = resourceScopeService.updateResourceScope(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("修改资源范围失败！");
		}
	}
	/**
	 * 删除资源范围
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseData deleteResourceScope(@Valid ResourceScopeDeleteRequest req, BindingResult result) {
		log.info("删除资源范围");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		boolean flag = resourceScopeService.deleteResourceScope(req.getId());
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("删除资源范围失败！");
		}
	}
	/**
	 * 删除资源范围resourceScope中的一个scope
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/scope/delete", method = RequestMethod.POST)
	public ResponseData deleteScope(@Valid ScopeDeleteRequest req, BindingResult result) {
		log.info("删除资源范围resourceScope中的一个scope");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		boolean flag = resourceScopeService.deleteScope(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("删除资源范围resourceScope中的一个scope！");
		}
	}
	/**
	 * 增加资源范围resourceScope中的一个scope
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/scope/add", method = RequestMethod.POST)
	public ResponseData addScope(@Valid ScopeAddRequest req, BindingResult result) {
		log.info("增加资源范围resourceScope中的一个scope");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		boolean flag = resourceScopeService.addScope(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("增加资源范围resourceScope中的一个scope！");
		}
	}
	/**
	 * 修改资源范围resourceScope中的一个scope
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/scope/update", method = RequestMethod.POST)
	public ResponseData updateScope(@Valid ScopeUpdateRequest req, BindingResult result) {
		log.info("修改资源范围resourceScope中的一个scope");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(ErrorsUtil.fieldError2Map(result.getFieldErrors()));
		}
		boolean flag = resourceScopeService.updateScope(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("修改资源范围resourceScope中的一个scope！");
		}
	}
}
