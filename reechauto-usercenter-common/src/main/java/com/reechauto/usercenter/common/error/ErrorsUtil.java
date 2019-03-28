package com.reechauto.usercenter.common.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.FieldError;

public class ErrorsUtil {
   public static Map<String,String> fieldError2Map(List<FieldError> list){
	   Map<String,String> map = new HashMap<String,String>();
	   if(CollectionUtils.isNotEmpty(list)) {
		   list.forEach(item->{
			   map.put(item.getField(), item.getDefaultMessage());
		   });
	   }
	   return map;
   }
}
