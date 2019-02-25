package com.reechauto.usercenter.auth.config.authorization;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.reechauto.usercenter.auth.exception.custom.CoustomOAuth2WebResponseExceptionTranslator;
import com.reechauto.usercenter.auth.exception.filter.CoustomBasicAuthenticationFilter;
import com.reechauto.usercenter.auth.service.oauth2.ReechClientDetailsService;
import com.reechauto.usercenter.auth.service.oauth2.ReechRedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("coustomOAuth2WebResponseExceptionTranslator")
	private CoustomOAuth2WebResponseExceptionTranslator coustomOAuth2WebResponseExceptionTranslator;
	
	@Autowired
	private CoustomBasicAuthenticationFilter coustomBasicAuthenticationFilter;
	
	@Autowired
	public UserDetailsService reechUserDetailsService;
	@Autowired
	private AccessDeniedHandler reechAccessDeniedHandler;
	@Autowired
	private AuthenticationEntryPoint reechAuthenticationEntryPoint;
	
	

	@Autowired
	@Qualifier("datasource")
	private DataSource datasource;

	@Autowired
	@Qualifier("lettuceConnectionFactory")
	private RedisConnectionFactory connectionFactory;

	@Bean("clientDetailsService")
	public ReechClientDetailsService getJdbcClientDetailsService() {
		return new ReechClientDetailsService(datasource);
	}

	@Bean("reechRedisTokenStore")
	public ReechRedisTokenStore tokenStore() {
		return new ReechRedisTokenStore(connectionFactory);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.tokenStore(tokenStore())
		.authenticationManager(authenticationManager)
		.userDetailsService(reechUserDetailsService)
		.reuseRefreshTokens(true);
		
		//异常处理(自定义)
		endpoints.exceptionTranslator(coustomOAuth2WebResponseExceptionTranslator);	
		//endpoints.pathMapping("/oauth/confirm_access", "/custom/confirm_access");
	}

	/**
	 * 配置客户端一些信息
	 *
	 * @param clients
	 * @throws Exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(getJdbcClientDetailsService());
	}

	/**
	 * springSecurity 授权表达式，
	 * 
	 * @param security
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
				.allowFormAuthenticationForClients()
				.passwordEncoder(passwordEncoder)
				.tokenKeyAccess("permitAll()") // 开启/oauth/token_key验证端口无权限访问
				.checkTokenAccess("isAuthenticated()"); // 开启/oauth/check_token验证端口认证权限访问
		        //.checkTokenAccess("permitAll()"); // 开启/oauth/check_token验证端口认证权限访问
		//异常处理(自定义)
		//coustomBasicAuthenticationFilter.setClientDetailsService(getJdbcClientDetailsService());
		security.addTokenEndpointAuthenticationFilter(coustomBasicAuthenticationFilter);
		security.accessDeniedHandler(reechAccessDeniedHandler).authenticationEntryPoint(reechAuthenticationEntryPoint);
	}
	
}
