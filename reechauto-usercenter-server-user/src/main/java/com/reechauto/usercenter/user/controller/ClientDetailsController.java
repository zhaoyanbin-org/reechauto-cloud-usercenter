package com.reechauto.usercenter.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.req.clientDetails.ClientDetailsAddRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ClientDetailsDeleteRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ClientDetailsQueryRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ClientDetailsUpdateRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ResourceIdsAddRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ResourceIdsDeleteRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ResourceIdsUpdateRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ScopeAddRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ScopeDeleteRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ScopeUpdateRequest;
import com.reechauto.usercenter.user.service.client.ClientDetailsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("clientDetails")
public class ClientDetailsController {
	@Autowired
	private ClientDetailsService  clientDetailsService;
	/**
	 * 查询客户端详情列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseData queryClientDetails(@Valid ClientDetailsQueryRequest req, BindingResult result) {
		log.info("客户端详情列表");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		ResponseData responseData = clientDetailsService.clientDetailsList(req);
		return responseData;
	}
	
	
	/**
	 * 新增客户端
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseData addClientDetails(@Valid ClientDetailsAddRequest req, BindingResult result) {
		log.info("新增客户端");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = clientDetailsService.addClientDetails(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("添加客户端失败！");
		}
	}
	/**
	 * 修改客户端
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseData updateClientDetails(@Valid ClientDetailsUpdateRequest req, BindingResult result) {
		log.info("修改客户端");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = clientDetailsService.updateClientDetails(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("修改客户端失败！");
		}
	}

	/**
	 * 删除客户端
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ResponseData deleteClientDetails(@Valid ClientDetailsDeleteRequest req, BindingResult result) {
		log.info("删除客户端");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = clientDetailsService.deleteClientDetails(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("删除客户端失败！");
		}
	}
	/**
	 * 在客户端中增加resourceId
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/resourceIds/add", method = RequestMethod.POST)
	public ResponseData addResourceIds(@Valid ResourceIdsAddRequest req, BindingResult result) {
		log.info("在客户端中增加resourceId");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = clientDetailsService.addResourceIds(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("在客户端中增加resourceId失败！");
		}
	}
	/**
	 * 在客户端中删除resourceId
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/resourceIds/delete", method = RequestMethod.POST)
	public ResponseData deleteResourceIds(@Valid ResourceIdsDeleteRequest req, BindingResult result) {
		log.info("在客户端中删除resourceId");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = clientDetailsService.deleteResourceIds(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("在客户端中删除resourceId失败！");
		}
	}
	/**
	 * 在客户端中修改resourceId
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/resourceIds/update", method = RequestMethod.POST)
	public ResponseData updateResourceIds(@Valid ResourceIdsUpdateRequest req, BindingResult result) {
		log.info("在客户端中修改resourceId");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = clientDetailsService.updateResourceIds(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("在客户端中修改resourceId失败！");
		}
	}
	
	/**
	 * 在客户端中增加scope
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/scope/add", method = RequestMethod.POST)
	public ResponseData addScope(@Valid ScopeAddRequest req, BindingResult result) {
		log.info("在客户端中增加scope");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = clientDetailsService.addScope(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("在客户端中增加scope失败！");
		}
	}
	
	/**
	 * 在客户端中删除resourceId
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/scope/delete", method = RequestMethod.POST)
	public ResponseData deleteScope(@Valid ScopeDeleteRequest req, BindingResult result) {
		log.info("在客户端中删除scope");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = clientDetailsService.deleteScope(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("在客户端中删除scope失败！");
		}
	}
	
	/**
	 * 在客户端中修改scope
	 * @param req
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/scope/update", method = RequestMethod.POST)
	public ResponseData updateScope(@Valid ScopeUpdateRequest req, BindingResult result) {
		log.info("在客户端中修改scope");
		if (result.hasErrors()) {
			return ResponseData.argumentsError().data(result.getAllErrors());
		}
		boolean flag = clientDetailsService.updateScope(req);
		if (flag) {
			return ResponseData.ok();
		} else {
			return ResponseData.error("在客户端中修改scope失败！");
		}
	}
}
