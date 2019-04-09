package com.bean.req.clientDetails;

import javax.validation.constraints.Min;
import com.bean.req.BaseRequest;

public class ClientDetailsQueryRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
	
	@Min(value = 1, message = "显示条数不能小于1")
	private int pageNum = 10;
	@Min(value = 0, message = "开始位置不能小于0")
	private int start = 0;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
}
