package com.reechauto.usercenter.auth.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.reechauto.usercenter.auth.entity.SysRole;
import com.reechauto.usercenter.auth.entity.user.BaseUserDetail;
import com.reechauto.usercenter.auth.entity.user.ReechUser;
import com.reechauto.usercenter.auth.mapper.UserMapper;

@Service("reechUserDetailsService")
public class ReechUserDetailsService implements UserDetailsService {
	@Resource
	private UserMapper userMapper;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ReechUser reechUser = getUser(username);

		if (reechUser == null)
			throw new UsernameNotFoundException(username + "不存在");	
		
		List<SysRole> roleList = queryRole(reechUser.getUserId());
		List<GrantedAuthority> authorityList = convertToAuthorities(roleList);
		
		User user = new User(reechUser.getUserId()+"", reechUser.getPassword(), isActive(reechUser.getIsDel()), true, true,
				isLock(reechUser.getUserStatus()), authorityList);
		
		 //AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN")
		reechUser.setPassword(null);
		return new BaseUserDetail(reechUser, user);
	}

	private boolean isActive(String active) {
		return "N".equalsIgnoreCase(active) ? true : false;
	}

	private boolean isLock(String active) {
		return "OK".equalsIgnoreCase(active) ? true : false;
	}

	private ReechUser getUser(String username) {
		if (StringUtils.isEmpty(username)) {
			throw new UnapprovedClientAuthenticationException("登录帐号不可以为空");
		}
		return userMapper.getReechUser(username);
	}
	private List<SysRole> queryRole(Long userId) {
		return userMapper.queryRoleByUserId(userId);
	}

	private List<GrantedAuthority> convertToAuthorities(List<SysRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.forEach(e -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleId());
			authorities.add(authority);
		});
		if(CollectionUtils.isEmpty(authorities)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return authorities;
	}

}
