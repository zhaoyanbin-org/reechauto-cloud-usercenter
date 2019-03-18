package com.reechauto.usercenter.user.service.client;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.req.clientDetails.AuthorizedGrantTypesAddRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.AuthorizedGrantTypesDeleteRequest;
import com.reechauto.usercenter.user.bean.req.clientDetails.AuthorizedGrantTypesUpdateRequest;
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
			record.setClientSecret(passwordEncoder.encode(req.getNewClientSecret()));
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
	
	public boolean addResourceIds(ResourceIdsAddRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getClientId().trim());
		ClientDetails record1 = clientDetailsMapper.selectByPrimaryKey(req.getClientId().trim());
		String resourceIds = record1.getResourceIds()==null?"":record1.getResourceIds();
		String[] resourceIds3 = resourceIds.split(",");
		for(int i = 0;i<resourceIds3.length;i++) {
			if (resourceIds3[i].equalsIgnoreCase(req.getResourceId())) {
				throw new RuntimeException("该资源ID已存在");
			}
		}
		StringBuffer resourceIds1 = new StringBuffer(resourceIds);
		if ("".equals(resourceIds)) {
			resourceIds1.append(req.getResourceId());
		}else {
			resourceIds1.append(",").append(req.getResourceId());
		}
		String resourceIds2 = new String(resourceIds1);
		ClientDetails record = new ClientDetails();
		record.setResourceIds(resourceIds2);
		return clientDetailsMapper.updateByExampleSelective(record,example)>0;
	}
	
	public boolean deleteResourceIds(ResourceIdsDeleteRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getClientId().trim());
		ClientDetails record1 = clientDetailsMapper.selectByPrimaryKey(req.getClientId().trim());
		String resourceIds = record1.getResourceIds()==null?"":record1.getResourceIds();
		String[] resourceIds2 = resourceIds.split(",");
		StringBuffer resourceIds1 = new StringBuffer();
		for(int i = 0;i<resourceIds2.length;i++) {
			if (!resourceIds2[i].equalsIgnoreCase(req.getResourceId().trim())) {
				if (i==0||i==1&&StringUtils.isBlank(new String(resourceIds1))) {
					resourceIds1.append(resourceIds2[i]);
				}else {
					resourceIds1.append(",").append(resourceIds2[i]);
				}
			}
		}
		String resourceIds3 = new String(resourceIds1);
		ClientDetails record = new ClientDetails();
		record.setResourceIds(resourceIds3);
		return clientDetailsMapper.updateByExampleSelective(record,example)>0;
	}
	
	public boolean updateResourceIds(ResourceIdsUpdateRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getClientId().trim());
		ClientDetails record1 = clientDetailsMapper.selectByPrimaryKey(req.getClientId().trim());
		String resourceIds = record1.getResourceIds()==null?"":record1.getResourceIds();
		String[] resourceIds2 = resourceIds.split(",");
		StringBuffer resourceIds1 = new StringBuffer();
		for(int i = 0;i<resourceIds2.length;i++) {
			if (!resourceIds2[i].equalsIgnoreCase(req.getOldResourceId().trim())) {
				if (i==0||i==1&&StringUtils.isBlank(new String(resourceIds1))) {
					resourceIds1.append(resourceIds2[i]);
				}else {
					resourceIds1.append(",").append(resourceIds2[i]);
				}
			}else {
				if (i==0||i==1&&StringUtils.isBlank(new String(resourceIds1))) {
					resourceIds1.append(req.getNewResourceId());
				}else {
					resourceIds1.append(",").append(req.getNewResourceId());
				}
			}
		}
		String resourceIds3 = new String(resourceIds1);
		ClientDetails record = new ClientDetails();
		record.setResourceIds(resourceIds3);
		return clientDetailsMapper.updateByExampleSelective(record,example)>0;
	}
	public boolean addScope(ScopeAddRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getClientId().trim());
		ClientDetails record1 = clientDetailsMapper.selectByPrimaryKey(req.getClientId().trim());
		String scopes = record1.getScope()==null?"":record1.getScope();
		String[] scopes3 = scopes.split(",");
		for(int i = 0;i<scopes3.length;i++) {
			if (scopes3[i].equalsIgnoreCase(req.getScope())) {
				throw new RuntimeException("该scope已存在");
			}
		}
		StringBuffer scopes1 = new StringBuffer(scopes);
		if ("".equals(scopes)) {
			scopes1.append(req.getScope());
		}else {
			scopes1.append(",").append(req.getScope());
		}
		String scopes2 = new String(scopes1);
		ClientDetails record = new ClientDetails();
		record.setScope(scopes2);
		return clientDetailsMapper.updateByExampleSelective(record,example)>0;
	}
	
	public boolean deleteScope(ScopeDeleteRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getClientId().trim());
		ClientDetails record1 = clientDetailsMapper.selectByPrimaryKey(req.getClientId().trim());
		String scopes = record1.getScope()==null?"":record1.getScope();
		String[] scopes2 = scopes.split(",");
		StringBuffer scopes1 = new StringBuffer();
		for(int i = 0;i<scopes2.length;i++) {
			if (!scopes2[i].equalsIgnoreCase(req.getScope().trim())) {
				if (i==0||i==1&&StringUtils.isBlank(new String(scopes1))) {
					scopes1.append(scopes2[i]);
				}else {
					scopes1.append(",").append(scopes2[i]);
				}
			}
		}
		String scopes3 = new String(scopes1);
		ClientDetails record = new ClientDetails();
		record.setScope(scopes3);
		return clientDetailsMapper.updateByExampleSelective(record,example)>0;
	}
	
	public boolean updateScope(ScopeUpdateRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getClientId().trim());
		ClientDetails record1 = clientDetailsMapper.selectByPrimaryKey(req.getClientId().trim());
		String scopes = record1.getScope()==null?"":record1.getScope();
		String[] scopes2 = scopes.split(",");
		StringBuffer scopes1 = new StringBuffer();
		for(int i = 0;i<scopes2.length;i++) {
			if (!scopes2[i].equalsIgnoreCase(req.getOldScope().trim())) {
				if (i==0||i==1&&StringUtils.isBlank(new String(scopes1))) {
					scopes1.append(scopes2[i]);
				}else {
					scopes1.append(",").append(scopes2[i]);
				}
			}else {
				if (i==0||i==1&&StringUtils.isBlank(new String(scopes1))) {
					scopes1.append(req.getNewScope());
				}else {
					scopes1.append(",").append(req.getNewScope());
				}
			}
		}
		String scopes3 = new String(scopes1);
		ClientDetails record = new ClientDetails();
		record.setScope(scopes3);
		return clientDetailsMapper.updateByExampleSelective(record,example)>0;
	}
	
	public boolean addAuthorizedGrantTypes(AuthorizedGrantTypesAddRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getClientId().trim());
		ClientDetails record1 = clientDetailsMapper.selectByPrimaryKey(req.getClientId().trim());
		String authorizedGrantTypes = record1.getAuthorizedGrantTypes()==null?"":record1.getAuthorizedGrantTypes();
		String[] authorizedGrantTypes3 = authorizedGrantTypes.split(",");
		for(int i = 0;i<authorizedGrantTypes3.length;i++) {
			if (authorizedGrantTypes3[i].equalsIgnoreCase(req.getAuthorizedGrantType().trim())) {
				throw new RuntimeException("该authorizedGrantType已存在");
			}
		}
		StringBuffer authorizedGrantTypes1 = new StringBuffer(authorizedGrantTypes);
		if ("".equals(authorizedGrantTypes)) {
			authorizedGrantTypes1.append(req.getAuthorizedGrantType());
		}else {
			authorizedGrantTypes1.append(",").append(req.getAuthorizedGrantType());
		}
		String authorizedGrantTypes2 = new String(authorizedGrantTypes1);
		ClientDetails record = new ClientDetails();
		record.setAuthorizedGrantTypes(authorizedGrantTypes2);
		return clientDetailsMapper.updateByExampleSelective(record,example)>0;
	}
	
	public boolean deleteAuthorizedGrantTypes(AuthorizedGrantTypesDeleteRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getClientId().trim());
		ClientDetails record1 = clientDetailsMapper.selectByPrimaryKey(req.getClientId().trim());
		String authorizedGrantTypes = record1.getAuthorizedGrantTypes()==null?"":record1.getAuthorizedGrantTypes();
		String[] authorizedGrantTypes2 = authorizedGrantTypes.split(",");
		StringBuffer authorizedGrantTypes1 = new StringBuffer();
		for(int i = 0;i<authorizedGrantTypes2.length;i++) {
			if (!authorizedGrantTypes2[i].equalsIgnoreCase(req.getAuthorizedGrantType().trim())) {
				if (i==0||i==1&&StringUtils.isBlank(new String(authorizedGrantTypes1))) {
					authorizedGrantTypes1.append(authorizedGrantTypes2[i]);
				}else {
					authorizedGrantTypes1.append(",").append(authorizedGrantTypes2[i]);
				}
			}
		}
		String authorizedGrantTypes3 = new String(authorizedGrantTypes1);
		ClientDetails record = new ClientDetails();
		record.setAuthorizedGrantTypes(authorizedGrantTypes3);
		return clientDetailsMapper.updateByExampleSelective(record,example)>0;
	}
	
	public boolean updateAuthorizedGrantTypes(AuthorizedGrantTypesUpdateRequest req) {
		ClientDetailsExample example = new ClientDetailsExample();
		Criteria criteria = example.createCriteria();
		criteria.andClientIdEqualTo(req.getClientId().trim());
		ClientDetails record1 = clientDetailsMapper.selectByPrimaryKey(req.getClientId().trim());
		String authorizedGrantTypes = record1.getAuthorizedGrantTypes()==null?"":record1.getAuthorizedGrantTypes();
		String[] authorizedGrantTypes2 = authorizedGrantTypes.split(",");
		StringBuffer authorizedGrantTypes1 = new StringBuffer();
		for(int i = 0;i<authorizedGrantTypes2.length;i++) {
			if (!authorizedGrantTypes2[i].equalsIgnoreCase(req.getOldAuthorizedGrantType().trim())) {
				if (i==0||i==1&&StringUtils.isBlank(new String(authorizedGrantTypes1))) {
					authorizedGrantTypes1.append(authorizedGrantTypes2[i]);
				}else {
					authorizedGrantTypes1.append(",").append(authorizedGrantTypes2[i]);
				}
			}else {
				if (i==0||i==1&&StringUtils.isBlank(new String(authorizedGrantTypes1))) {
					authorizedGrantTypes1.append(req.getNewAuthorizedGrantType());
				}else {
					authorizedGrantTypes1.append(",").append(req.getNewAuthorizedGrantType());
				}
			}
		}
		String authorizedGrantTypes3 = new String(authorizedGrantTypes1);
		ClientDetails record = new ClientDetails();
		record.setAuthorizedGrantTypes(authorizedGrantTypes3);
		return clientDetailsMapper.updateByExampleSelective(record,example)>0;
	}
}
