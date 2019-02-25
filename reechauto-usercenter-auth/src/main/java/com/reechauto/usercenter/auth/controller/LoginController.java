package com.reechauto.usercenter.auth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.reechauto.usercenter.auth.entity.constant.ReechAuthConstant;
import com.reechauto.usercenter.common.resp.ResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@GetMapping("/login")
	public String index(HttpServletRequest req, HttpServletResponse resp, Model model) {
		model.addAttribute("loginProcessUrl",ReechAuthConstant.REECHLOGIN_LOGINPROCESSING_URL);
		return "login/index";
	}

	@RequestMapping(ReechAuthConstant.REECHLOGIN_LOGINPAGE_URL)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ResponseData requireAuthentication(HttpServletRequest request, HttpServletResponse response){
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (null != savedRequest) {
			String targetUrl = savedRequest.getRedirectUrl();
			log.info("引发跳转的请求是:" + targetUrl);
			try {
				redirectStrategy.sendRedirect(request, response, "/login");
			} catch (IOException e) {
				return ResponseData.serverInternalError();
			}
		}
		// 如果访问的是接口资源
		return ResponseData.unauthorized();
	}

}
