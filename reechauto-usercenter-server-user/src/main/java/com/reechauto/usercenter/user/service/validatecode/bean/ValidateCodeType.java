package com.reechauto.usercenter.user.service.validatecode.bean;

import com.reechauto.usercenter.user.bean.ReechAuthConstant;

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
