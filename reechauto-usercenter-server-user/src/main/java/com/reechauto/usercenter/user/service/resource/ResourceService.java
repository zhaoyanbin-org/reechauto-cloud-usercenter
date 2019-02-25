package com.reechauto.usercenter.user.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reechauto.usercenter.user.entity.ResourceServer;
import com.reechauto.usercenter.user.entity.ResourceServerExample;
import com.reechauto.usercenter.user.mapper.ResourceServerMapper;

@Service
public class ResourceService {
	@Autowired
	private ResourceServerMapper resourceServerMapper;

	public boolean addResourceService(String resourceId, String resourceName) {
		ResourceServer record = new ResourceServer();
		return this.resourceServerMapper.insert(record) > 0;
	}

	public List<ResourceServer> resourceServerList() {
		ResourceServerExample example = new ResourceServerExample();
		return this.resourceServerMapper.selectByExample(example);
	}

}
