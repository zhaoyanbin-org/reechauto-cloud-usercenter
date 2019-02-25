package com.reechauto.usercenter.auth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {

	@RequestMapping("/oauth/confirm_access")
	public String getAccessConfirmation(HttpServletRequest request, Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes")
				: request.getAttribute("scopes"));
		List<String> scopeList = new ArrayList<>();
		if (scopes != null) {
			scopeList.addAll(scopes.keySet());
		}
		model.put("scopeList", scopeList);
		return "oauth/oauth_approval";
	}
}
