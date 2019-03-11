package com.reechauto.usercenter.user.service.resource;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeQueryRequest;
import com.reechauto.usercenter.user.bean.req.resource.ResourceScopeUpdateRequest;
import com.reechauto.usercenter.user.entity.ResourceScope;
import com.reechauto.usercenter.user.entity.ResourceScopeExample;
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
	public List<ResourceScope> resourceScopeList(ResourceScopeQueryRequest req) {
		ResourceScopeExample example = new ResourceScopeExample();
		example.setLimitStart(req.getStart());
		example.setOffset(req.getPageNum());
		return resourceScopeMapper.selectByExample(example);
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
}
