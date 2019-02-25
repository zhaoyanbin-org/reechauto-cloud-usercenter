package com.reechauto.usercenter.auth.entity;

import java.io.Serializable;

public class ReechDepartment implements Serializable {
	private static final long serialVersionUID = 1L;
	private String departmentId;// 部门ID
	private String departmentName;// 部门名称
	private String header;// 负责人

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

}
