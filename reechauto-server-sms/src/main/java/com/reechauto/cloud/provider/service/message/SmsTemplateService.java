package com.reechauto.cloud.provider.service.message;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reechauto.cloud.provider.enums.ExceptionCodeEnum;
import com.reechauto.cloud.provider.mapper.SmsTemplateMapper;
import com.reechauto.cloud.provider.model.SmsTemplate;
import com.reechauto.cloud.provider.model.SmsTemplateExample;
import com.reechauto.cloud.provider.model.SmsTemplateExample.Criteria;
import com.reechauto.usercenter.common.exception.DataEmptyException;
import com.zyb.common.tools.common.ResponseData;
import com.zyb.common.tools.utils.DateUtil;

@Service
public class SmsTemplateService {

	@Autowired
	private SmsTemplateMapper smsTemplateMapper;

	public void addTemplate(String templateId, String templateName, String content, Integer clientId) {
		SmsTemplate smsTemplate = new SmsTemplate();
		smsTemplate.setTemplateId(templateId);
		smsTemplate.setTemplateName(templateName);
		smsTemplate.setContent(content);
		smsTemplate.setClientId(clientId);

		smsTemplateMapper.insertSelective(smsTemplate);
	}

	public boolean removeTemplate(String templateId) {

		boolean flag = smsTemplateMapper.deleteByPrimaryKey(templateId) > 0;

		return flag;
	}

	public ResponseData modifyTemplate(SmsTemplate smsTemplateInfo) {

		if (smsTemplateInfo != null && smsTemplateInfo.getTemplateId() != null) {
			SmsTemplate smsTemplate = this.smsTemplateMapper.selectByPrimaryKey(smsTemplateInfo.getTemplateId());
			if (StringUtils.isNotBlank(smsTemplateInfo.getTemplateName())) {
				smsTemplate.setTemplateName(smsTemplateInfo.getTemplateName());
			}
			if (StringUtils.isNotBlank(smsTemplateInfo.getContent())) {
				System.out.println(smsTemplateInfo.getContent());
				smsTemplate.setContent(smsTemplateInfo.getContent());
			}
			if (smsTemplateInfo.getClientId() != null) {
				smsTemplate.setClientId(smsTemplateInfo.getClientId());
			}
			smsTemplate.setModifyTime(new Date());
			this.smsTemplateMapper.updateByPrimaryKeySelective(smsTemplate);
			return new ResponseData(ExceptionCodeEnum.OK);
		} else {
			return new ResponseData(ExceptionCodeEnum.COMMON_PARAMETER_ERROR);
		}
	}

	public List<SmsTemplate> selectTemplate(SmsTemplate smsTemplateInfo) {

		if (smsTemplateInfo != null) {
			SmsTemplateExample smsTemplateExample = new SmsTemplateExample();
			Criteria criteria = smsTemplateExample.createCriteria();
			if (StringUtils.isNotBlank(smsTemplateInfo.getTemplateName())) {
				criteria.andTemplateNameEqualTo(smsTemplateInfo.getTemplateName());
			}
			if (StringUtils.isNotBlank(smsTemplateInfo.getTemplateId())) {
				criteria.andTemplateIdEqualTo(smsTemplateInfo.getTemplateId());
			}
			if (StringUtils.isNotBlank(smsTemplateInfo.getContent())) {
				criteria.andContentEqualTo(smsTemplateInfo.getContent());

			}
			if (smsTemplateInfo.getClientId() != null) {
				criteria.andClientIdEqualTo(smsTemplateInfo.getClientId());
			}
			if (smsTemplateInfo.getCreateTime() != null) {
				criteria.andCreateTimeEqualTo(smsTemplateInfo.getCreateTime());
			}
			if (smsTemplateInfo.getModifyTime() != null) {
				criteria.andModifyTimeEqualTo(smsTemplateInfo.getModifyTime());
			}
			List<SmsTemplate> list = this.smsTemplateMapper.selectByExample(smsTemplateExample);
			if (CollectionUtils.isEmpty(list)) {
				throw new DataEmptyException("沒查到记录");
			}
			return list;
		} else {
			throw new DataEmptyException("传入的对象为null");
		}
	}
}
