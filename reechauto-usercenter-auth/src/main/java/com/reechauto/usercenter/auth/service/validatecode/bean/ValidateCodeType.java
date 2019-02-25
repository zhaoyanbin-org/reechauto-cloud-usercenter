package com.reechauto.usercenter.auth.service.validatecode.bean;

import com.reechauto.usercenter.auth.entity.constant.ReechAuthConstant;

public enum ValidateCodeType {
	mobile {
		@Override
		public String getParamNameDeviceidOnValidate() {
			return ReechAuthConstant.PARAMETER_NAME_ON_VALIDATE_DEVICEID_MOBILE;
		}

		@Override
		public String getParamNameVCodeOnValidate() {
			return ReechAuthConstant.PARAMETER_NAME_ON_VALIDATE_VCODE_MOBILE;
		}

	},
	image {
		@Override
		public String getParamNameDeviceidOnValidate() {
			return ReechAuthConstant.PARAMETER_NAME_ON_VALIDATE_DEVICEID_IMAGE;
		}

		@Override
		public String getParamNameVCodeOnValidate() {
			return ReechAuthConstant.PARAMETER_NAME_ON_VALIDATE_VCODE_IMAGE;
		}

	};

	public abstract String getParamNameDeviceidOnValidate();

	public abstract String getParamNameVCodeOnValidate();
}
