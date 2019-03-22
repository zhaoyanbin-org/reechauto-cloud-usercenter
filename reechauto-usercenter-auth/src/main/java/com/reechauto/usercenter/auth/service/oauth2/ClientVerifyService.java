package com.reechauto.usercenter.auth.service.oauth2;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Component("clientVerifyService")
public class ClientVerifyService {

	@Autowired
	private ReechClientDetailsService clientDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ClientDetails verifyClientHeader(HttpServletRequest request,String grantType) throws IOException {
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Basic ")) {
			throw new UnapprovedClientAuthenticationException("请求头中无client信息");
		}
		String[] tokens = this.extractAndDecodeHeader(header, request);

		assert tokens.length == 2;

		String clientId = tokens[0];
		String clientSecret = tokens[1];
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

		if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId 对应的配置信息不存在" + clientId);
		} else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
			throw new UnapprovedClientAuthenticationException("clientSecret 不匹配" + clientId);
		}
		boolean flag = false;
		Set<String> grantTypes =clientDetails.getAuthorizedGrantTypes();
		if(CollectionUtils.isNotEmpty(grantTypes)) {
			for (String gt : grantTypes) {
				if(gt.equalsIgnoreCase(grantType.trim())) {
					flag=true;
				}
			}
		}
		if(!flag) {
			throw new UnapprovedClientAuthenticationException("clientId 对应的grantType 不包括mobile登录" + clientId);
		} 
		
		return clientDetails;
	}
	
	public ClientDetails verifyClientRequest(HttpServletRequest request) throws IOException {
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Basic ")) {
			throw new UnapprovedClientAuthenticationException("请求头中无client信息");
		}
		String[] tokens = this.extractAndDecodeHeader(header, request);

		assert tokens.length == 2;

		String clientId = tokens[0];
		String clientSecret = tokens[1];
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

		if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId 对应的配置信息不存在" + clientId);
		} else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
			throw new UnapprovedClientAuthenticationException("clientSecret 不匹配" + clientId);
		}
		
		
		return clientDetails;
	}

	/**
	 * 解码
	 *
	 * @param header
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
		byte[] base64Token = header.substring(6).getBytes("UTF-8");

		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException var7) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");
		int delim = token.indexOf(":");
		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		} else {
			return new String[] { token.substring(0, delim), token.substring(delim + 1) };
		}
	}
}
