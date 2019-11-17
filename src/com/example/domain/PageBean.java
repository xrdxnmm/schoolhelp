package com.example.domain;

import java.util.List;

public class PageBean<T> {

	private int pageNo;
	private int pageSize;
	private int totalCount;
	private int totalPage;
	private List<Mission> list;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<Mission> getList() {
		return list;
	}
	public void setList(List<Mission> list) {
		this.list = list;
	}
	
}
