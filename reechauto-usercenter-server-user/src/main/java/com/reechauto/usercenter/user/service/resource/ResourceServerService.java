package com.reechauto.usercenter.user.service.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reechauto.usercenter.common.resp.ResponseData;
import com.reechauto.usercenter.user.bean.req.resource.ResourceServerQueryRequest;
import com.reechauto.usercenter.user.entity.ResourceServer;
import com.reechauto.usercenter.user.entity.ResourceServerExample;
import com.reechauto.usercenter.user.entity.ResourceServerExample.Criteria;
import com.reechauto.usercenter.user.mapper.ResourceServerMapper;

@Service
public class ResourceServerService {
	@Autowired
	private ResourceServerMapper resourceServerMapper;

	public boolean addResourceService(String resourceId, String resourceName) {
		ResourceServer record = new ResourceServer();
		record.setResourceId(resourceId);
		record.setResourceName(resourceName);
		return this.resourceServerMapper.insert(record) > 0;
	}

	public ResponseData resourceServerList(ResourceServerQueryRequest req) {
		ResourceServerExample example = new ResourceServerExample();
		Long total = resourceServerMapper.countByExample(example);
		example.setLimitStart(req.getStart());
		example.setOffset(req.getPageNum());
		List<ResourceServer> list = this.resourceServerMapper.selectByExample(example);
		return ResponseData.ok().data(list).data("total",total);
	}
	
	public boolean deleteResourceServer(String resourceId) {
		return this.resourceServerMapper.deleteByPrimaryKey(resourceId)>0;
	}

	public boolean updateResourceServer(String oldResourceId,String newResourceId,String newResourceName ) {
		ResourceServerExample example = new ResourceServerExample();
		Criteria criteria = example.createCriteria();
		criteria.andResourceIdEqualTo(oldResourceId);
		ResourceServer resourceServer = new ResourceServer();
		resourceServer.setResourceId(newResourceId);
		resourceServer.setResourceName(newResourceName);
		return resourceServerMapper.updateByExampleSelective(resourceServer, example)>0;
	}
	
}
