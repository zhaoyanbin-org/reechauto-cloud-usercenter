package com.reechauto.cloud.provider.service.common;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.reechauto.cloud.provider.model.WeightCategory;

@Service
public class LoadBalancingService {
	
	private static Random random = new Random();
	
	
	
	public String balance(List<WeightCategory>  list) {
		
		Integer weightSum = 0;
		for(WeightCategory wc:list) {
			weightSum+= wc.getWeight();
		}
		
		Integer n = random.nextInt(weightSum);
		
		Integer m = 0;
		
		for(WeightCategory wc:list) {
			
			if(m<=n&&n<m+wc.getWeight()) {
				
				return wc.getCategory();
			}
			
			m = m + wc.getWeight();
			
		}
		
		return null;
	}

}
