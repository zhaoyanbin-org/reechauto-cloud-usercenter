package com.reechauto.usercenter.user.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeAddRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeDeleteRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeQueryRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeUpdateRequest;
import com.reechauto.usercenter.user.entity.ResourceScope;
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
	public ResponseData addResourceServer(@Valid ResourceScopeAddRequest req, BindingResult result) {
		log.info("新增资源范围");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
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
	public ResponseData queryResourceServer(ResourceScopeQueryRequest req) {
		log.info("资源范围列表");
		List<ResourceScope> list= resourceScopeService.resourceScopeList(req);
		return ResponseData.ok().data(list);
	}
	/**
	 * 修改资源范围
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseData updateResourceServer(@Valid ResourceScopeUpdateRequest req, BindingResult result) {
		log.info("修改资源范围");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
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
	public ResponseData deleteResourceServer(@Valid ResourceScopeDeleteRequest req, BindingResult result) {
		log.info("删除资源范围");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = resourceScopeService.deleteResourceScope(req.getId());
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("删除资源范围失败！");
		}
	}

}
