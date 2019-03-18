package com.reechauto.usercenter.user.service.resource;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeQueryRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeUpdateRequest;
import com.reechauto.usercenter.user.bean.req.resource.ScopeAddRequest;
import com.reechauto.usercenter.user.bean.req.resource.ScopeDeleteRequest;
import com.reechauto.usercenter.user.bean.req.resource.ScopeUpdateRequest;
import com.reechauto.usercenter.user.entity.ResourceScope;
import com.reechauto.usercenter.user.entity.ResourceScopeExample;
import com.reechauto.usercenter.user.entity.ResourceScopeExample.Criteria;
import com.reechauto.usercenter.user.mapper.ResourceScopeMapper;

@Service
public class ResourceScopeService {
	@Autowired
	private ResourceScopeMapper  resourceScopeMapper;
	
	public boolean addResourceScope(String scope, String scopeTips,String resourceId) {
		ResourceScope resourceScope = new ResourceScope();
		resourceScope.setScope(scope);
		resourceScope.setScopeTips(scopeTips);
		resourceScope.setResourceId(resourceId);
		return resourceScopeMapper.insert(resourceScope)>0;
	}
	
	public ResponseData resourceScopeList(ResourceScopeQueryRequest req) {
		ResourceScopeExample example = new ResourceScopeExample();
		Long total = resourceScopeMapper.countByExample(example);
		example.setLimitStart(req.getStart());
		example.setOffset(req.getPageNum());
		List<ResourceScope> list= resourceScopeMapper.selectByExample(example);
		return ResponseData.ok().data(list).data("total", total);
	}
	
	public boolean deleteResourceScope(Integer id) {
		return resourceScopeMapper.deleteByPrimaryKey(id)>0;
	}
	
    public boolean updateResourceScope(ResourceScopeUpdateRequest req) {
    	ResourceScope resourceScope = resourceScopeMapper.selectByPrimaryKey(req.getId());
    	if (StringUtils.isNotBlank(req.getScope())) {
    		resourceScope.setScope(req.getScope());
		}
    	if (StringUtils.isNotBlank(req.getScopeTips())) {
			resourceScope.setScopeTips(req.getScopeTips());
		}
    	if (StringUtils.isNotBlank(req.getResourceId())) {
			resourceScope.setResourceId(req.getResourceId());
		}
    	return resourceScopeMapper.updateByPrimaryKeySelective(resourceScope)>0;
    }
    
    public boolean addScope(ScopeAddRequest req) {
		ResourceScopeExample example = new ResourceScopeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(req.getId());
		ResourceScope record1 = resourceScopeMapper.selectByPrimaryKey(req.getId());
		String scopes = record1.getScope()==null?"":record1.getScope();
		String[] scopes3 = scopes.split(",");
		for(int i = 0;i<scopes3.length;i++) {
			if (scopes3[i].equalsIgnoreCase(req.getScope().trim())) {
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
		ResourceScope record = new ResourceScope();
		record.setScope(scopes2);
		return resourceScopeMapper.updateByExampleSelective(record,example)>0;
	}
	
	public boolean deleteScope(ScopeDeleteRequest req) {
		ResourceScopeExample example = new ResourceScopeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(req.getId());
		ResourceScope record1 = resourceScopeMapper.selectByPrimaryKey(req.getId());
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
		String scope3 = new String(scopes1);
		ResourceScope record = new ResourceScope();
		record.setScope(scope3);
		return resourceScopeMapper.updateByExampleSelective(record,example)>0;
	}
	
	public boolean updateScope(ScopeUpdateRequest req) {
		ResourceScopeExample example = new ResourceScopeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(req.getId());
		ResourceScope record1 = resourceScopeMapper.selectByPrimaryKey(req.getId());
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
		ResourceScope record = new ResourceScope();
		record.setScope(scopes3);
		return resourceScopeMapper.updateByExampleSelective(record,example)>0;
	}
}
