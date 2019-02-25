package com.reechauto.usercenter.auth.mapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.reechauto.usercenter.auth.entity.SysRole;
import com.reechauto.usercenter.auth.entity.user.ReechUser;
import com.reechauto.usercenter.auth.entity.user.ReechUserAccount;

@Component
public class UserMapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ReechUser getReechUser(String accountNum) {
		String sql2 = "SELECT user_id,password,real_name,nick_name,sex,birthday,img_url,city,user_status,is_del FROM user_details a WHERE EXISTS (SELECT 1 FROM user_account b WHERE a.user_id=b.user_id AND b.account_num=?) ";
		RowMapper<ReechUser> rowMapper2 = new BeanPropertyRowMapper<ReechUser>(ReechUser.class);
		ReechUser reechUser = this.jdbcTemplate.queryForObject(sql2, rowMapper2, accountNum);
		if (reechUser == null) {
			throw new UnapprovedClientAuthenticationException("不存在的帐号:" + accountNum);
		}

		String sql1 = "SELECT account_num,account_type,user_id FROM user_account WHERE user_id=?";
		RowMapper<ReechUserAccount> rowMapper = new BeanPropertyRowMapper<ReechUserAccount>(ReechUserAccount.class);
		List<ReechUserAccount> list = this.jdbcTemplate.query(sql1, rowMapper, reechUser.getUserId());
		if (CollectionUtils.isEmpty(list)) {
			throw new UnapprovedClientAuthenticationException("不存在的帐号:" + accountNum);
		}

		reechUser.setAccountInfo(list);
		return reechUser;
	}
	
	/**
	 * 查询角色
	 * @param userId
	 * @return
	 */
	public List<SysRole> queryRoleByUserId(Long userId) {
		String sql = "SELECT a.* FROM sys_role a,sys_user_role b WHERE a.status='Y' AND a.role_id=b.role_id AND b.user_id=?";		
		RowMapper<SysRole> rowMapper = new BeanPropertyRowMapper<SysRole>(SysRole.class);
		List<SysRole> list = this.jdbcTemplate.query(sql, rowMapper,userId);
		return list;
	}
	
}
