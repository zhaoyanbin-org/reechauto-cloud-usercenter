package com.reechauto.usercenter.user.service.user;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.common.utils.code.IdGenerator;
import com.reechauto.usercenter.common.utils.date.DateUtil;
import com.reechauto.usercenter.common.utils.regex.RegexUtil;
import com.reechauto.usercenter.user.bean.UserEntity;
import com.reechauto.usercenter.user.bean.enums.AccountType;
import com.reechauto.usercenter.user.bean.enums.UserIsDel;
import com.reechauto.usercenter.user.bean.enums.UserStatus;
import com.reechauto.usercenter.user.bean.req.user.UserDetailModifyReq;
import com.reechauto.usercenter.user.entity.UserAccount;
import com.reechauto.usercenter.user.entity.UserAccountExample;
import com.reechauto.usercenter.user.entity.UserAccountExample.Criteria;
import com.reechauto.usercenter.user.entity.UserDetails;
import com.reechauto.usercenter.user.mapper.UserAccountMapper;
import com.reechauto.usercenter.user.mapper.UserDetailsMapper;

@Service
public class UserServer {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsMapper userDetailsMapper;
	@Autowired
	private UserAccountMapper userAccountMapper;

	@Transactional
	public Long registerUser(String accountNum, String password) {
		accountNum = accountNum.trim();
		UserAccount userAccount = this.userAccountMapper.selectByPrimaryKey(accountNum);
		if (userAccount != null) {
			throw new RuntimeException("帐号" + accountNum + "已存在");
		}
		AccountType accountType = AccountType.account;
		if (RegexUtil.checkEmail(accountNum)) {
			accountType = AccountType.email;
		}
		if (RegexUtil.checkMobile(accountNum)) {
			accountType = AccountType.mobile;
		}
		if (RegexUtil.checkIdCard(accountNum)) {
			accountType = AccountType.idcard;
		}

		if (accountType.equals(AccountType.account)) {
			if (RegexUtil.checkAccount(accountNum))
				throw new RuntimeException("帐号不能由数字开头，应由4-10位的字母,数字,下划线组成");
		}

		String passwordEN = passwordEncoder.encode(password);
		Long userId = IdGenerator.getInstance().nextId();

		UserDetails record = new UserDetails();
		record.setUserId(userId);
		record.setPassword(passwordEN);
		record.setNickName("用户_" + accountNum);

		UserAccount accountRecord = new UserAccount();
		accountRecord.setAccountNum(accountNum);
		accountRecord.setAccountType(accountType.getValue());
		accountRecord.setUserId(userId);

		this.userDetailsMapper.insertSelective(record);
		this.userAccountMapper.insertSelective(accountRecord);
		return userId;
	}

	public UserEntity queryByUserId(Long userId) {
		if (userId <= 0) {
			throw new RuntimeException("用户Id不可以小于等于0");
		}
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<UserAccount> list = this.userAccountMapper.selectByExample(example);
		UserDetails userDetails = this.userDetailsMapper.selectByPrimaryKey(userId);
		userDetails.setPassword(null);
		UserEntity user = new UserEntity();
		user.setUserAccount(list);
		user.setUserDetails(userDetails);
		return user;
	}

	public UserEntity accountBind(Long userId, String accountNum, AccountType accountType) {
		UserAccount userAccount1 = queryUserAccount(userId,accountType);
		if(userAccount1!=null) {
			throw new RuntimeException(accountType.getText()+"已绑定");
		}
		UserAccount userAccount2 = queryUserAccountByNum(accountNum);
		if(userAccount2!=null) {
			throw new RuntimeException(accountNum+"已存在");
		}
		
		if (accountType.equals(AccountType.email)) {
			if (!RegexUtil.checkEmail(accountNum))
				throw new RuntimeException("绑定邮箱格式不正确");
		}
		if (accountType.equals(AccountType.mobile)) {
			if (!RegexUtil.checkMobile(accountNum))
				throw new RuntimeException("绑定手机格式不正确");
		}
		if (accountType.equals(AccountType.idcard)) {
			if (RegexUtil.checkIdCard(accountNum))
				throw new RuntimeException("绑定身份证号码不正确");
		}
		if (accountType.equals(AccountType.account)) {
			if (RegexUtil.checkAccount(accountNum))
				throw new RuntimeException("帐号不能由数字开头，应由4-10位的字母,数字,下划线组成");
		}
		UserAccountExample example = new UserAccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		long total = this.userAccountMapper.countByExample(example);
		if (total == 0) {
			throw new RuntimeException("帐号ID不存在");
		}

		criteria.andAccountTypeEqualTo(accountType.getValue());
		List<UserAccount> list = this.userAccountMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			throw new RuntimeException(accountType.getText() + "类型的帐号已存在");
		}

		UserAccount accountRecord = new UserAccount();
		accountRecord.setAccountNum(accountNum);
		accountRecord.setAccountType(accountType.getValue());
		accountRecord.setUserId(userId);

		boolean flag = this.userAccountMapper.insertSelective(accountRecord) > 0;
		if (!flag) {
			throw new RuntimeException("绑定失败");
		}
		return queryByUserId(userId);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param req
	 * @return
	 */
	public UserEntity modifyUserDetail(UserDetailModifyReq req) {
		UserDetails userDetails = this.userDetailsMapper.selectByPrimaryKey(req.getUserId());
		if (userDetails == null) {
			throw new RuntimeException("错误的用户ID");
		}
		if (StringUtils.isNotBlank(req.getRealName())) {
			userDetails.setRealName(req.getRealName());
		}
		if (StringUtils.isNotBlank(req.getNickName())) {
			userDetails.setNickName(req.getNickName());
		}
		if (StringUtils.isNotBlank(req.getSex())) {
			if ("女".equals(req.getSex()) || "男".equals(req.getSex())) {
				userDetails.setSex(req.getSex());
			}
		}
		if (StringUtils.isNotBlank(req.getBirthday())) {
			userDetails.setBirthday(DateUtil.convert2Date(req.getBirthday(), "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(req.getImgUrl())) {
			userDetails.setImgUrl(req.getImgUrl());
		}
		if (StringUtils.isNotBlank(req.getCity())) {
			userDetails.setCity(req.getCity());
		}
		if (StringUtils.isNotBlank(req.getUserStatus())) {
			UserStatus userStatus = UserStatus.get(req.getUserStatus().toUpperCase());
			userDetails.setUserStatus(userStatus.getValue());
		}
		if (StringUtils.isNotBlank(req.getIsDel())) {
			UserIsDel userIsDel = UserIsDel.get(req.getIsDel().toUpperCase());
			userDetails.setIsDel(userIsDel.getValue());
		}
		userDetails.setModifyTime(new Date());
		this.userDetailsMapper.updateByPrimaryKeySelective(userDetails);
		return queryByUserId(req.getUserId());
	}
	
	public UserAccount queryUserAccount(Long userId, AccountType accountType) {
		UserAccountExample example=new UserAccountExample();
		example.createCriteria().andAccountTypeEqualTo(accountType.getValue()).andUserIdEqualTo(userId);
		List<UserAccount> list = this.userAccountMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
	
	public UserAccount queryUserAccountByNum(String accountNum) {
		UserAccount userAccount = this.userAccountMapper.selectByPrimaryKey(accountNum);
        return userAccount;
	}

}
