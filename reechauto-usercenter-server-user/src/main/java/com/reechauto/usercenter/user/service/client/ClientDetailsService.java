package com.reechauto.usercenter.user.service.client;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.req.clientDetails.ClientDetailsAddRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ClientDetailsDeleteRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ClientDetailsQueryRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.ClientDetailsUpdateRequest;
import com.reechauto.usercenter.user.entity.ClientDetails;
import com.reechauto.usercenter.user.entity.ClientDetailsExample;
import com.reechauto.usercenter.user.entity.ClientDetailsExample.Criteria;
import com.reechauto.usercenter.user.mapper.ClientDetailsMapper;

@Service
public class ClientDetailsService {

	@Autowired
	private ClientDetailsMapper clientDetailsMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ResponseData clientDetailsList(ClientDetailsQueryRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Long total = clientDetailsMapper.countByExample(example);
		example.setLimitStart(req.getStart());
		example.setOffset(req.getPageNum());
		List<ClientDetails> list = this.clientDetailsMapper.selectByExample(example);
		return ResponseData.ok().data(list).data("total",total);
	}
	
	public boolean addClientDetails(ClientDetailsAddRequest  req) {
		
		ClientDetails record = new ClientDetails();
		record.setClientId(req.getClientId());
		if (StringUtils.isNotBlank(req.getResourceIds())) {
			record.setResourceIds(req.getResourceIds());
		}
		if (StringUtils.isNotBlank(req.getClientSecret())) {
			record.setClientSecret(passwordEncoder.encode(req.getClientSecret()));
		}
		if (StringUtils.isNotBlank(req.getScope())) {
			record.setScope(req.getScope());
		}
		if (StringUtils.isNotBlank(req.getAuthorizedGrantTypes())) {
			record.setAuthorizedGrantTypes(req.getAuthorizedGrantTypes());
		}
		if (StringUtils.isNotBlank(req.getWebServerRedirectUri())) {
			record.setWebServerRedirectUri(req.getWebServerRedirectUri());
		}
		if (StringUtils.isNotBlank(req.getAuthorities())) {
			record.setAuthorities(req.getAuthorities());
		}
		if (StringUtils.isNotBlank(req.getAdditionalInformation())) {
			record.setAdditionalInformation(req.getAdditionalInformation());
		}
		if (StringUtils.isNotBlank(req.getAutoapprove())) {
			record.setAutoapprove(req.getAutoapprove());
		}
		if (req.getAccessTokenValidity()!=null) {
			record.setAccessTokenValidity(req.getAccessTokenValidity());
		}
		if (req.getRefreshTokenValidity()!=null) {
			record.setRefreshTokenValidity(req.getRefreshTokenValidity());
		}
		return clientDetailsMapper.insertSelective(record)>0;
	}
	
	public boolean updateClientDetails(ClientDetailsUpdateRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getOldClientId().trim());
		ClientDetails record = new ClientDetails();
		if (StringUtils.isNotBlank(req.getNewClientId())) {
			record.setClientId(req.getNewClientId());
		}
		if (StringUtils.isNotBlank(req.getNewResourceIds())) {
			record.setResourceIds(req.getNewResourceIds());
		}
		if (StringUtils.isNotBlank(req.getNewClientSecret())) {
			record.setClientSecret(req.getNewClientSecret());
		}
		if (StringUtils.isNotBlank(req.getNewScope())) {
		    record.setScope(req.getNewScope());
		}
		if (StringUtils.isNotBlank(req.getNewAuthorizedGrantTypes())) {
		    record.setAuthorizedGrantTypes(req.getNewAuthorizedGrantTypes());
		}
		if (StringUtils.isNotBlank(req.getNewWebServerRedirectUri())) {
		    record.setWebServerRedirectUri(req.getNewWebServerRedirectUri());
		}
		if (StringUtils.isNotBlank(req.getNewAuthorities())) {
		    record.setAuthorities(req.getNewAuthorities());
		}
		if (req.getNewAccessTokenValidity()!=null) {
		    record.setAccessTokenValidity(req.getNewAccessTokenValidity());
		}
		if (req.getNewRefreshTokenValidity()!=null) {
		    record.setRefreshTokenValidity(req.getNewRefreshTokenValidity());
		}
		if (StringUtils.isNotBlank(req.getNewAdditionalInformation())) {
		    record.setAdditionalInformation(req.getNewAdditionalInformation());
		}
		if (StringUtils.isNotBlank(req.getNewAutoapprove())) {
		    record.setAutoapprove(req.getNewAutoapprove());
		}
		return clientDetailsMapper.updateByExampleSelective(record, example)>0;
	}
	
	public boolean deleteClientDetails(ClientDetailsDeleteRequest req) {
		return clientDetailsMapper.deleteByPrimaryKey(req.getClientId())>0;
	}
}
