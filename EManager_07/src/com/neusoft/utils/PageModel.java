package com.neusoft.utils;

import java.util.List;

/**
 * 封装分页信息
 * @author Administrator
 *
 */
public class PageModel<A> {

	//结果集
	private List<A> list;
	
	//查询记录数
	private int totalRecords;
	
	//查询当前页数
	private int pageNo;
	
	//每页多少条数据
	private int pageSize;
	
	//总页数
	private int totalPageNo;
	
	
	public int getTotalPageNo() {
		return totalPageNo;
	}

	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}

	/**
	 * 一共有几页
	 * @return
	 */
	public int getTotalPages(){
		return (totalRecords + pageSize - 1)/pageSize;
	}

	/**
	 * 取得首页页码
	 * @return
	 */
	public int getTopPageNo(){
		return 1;
	}
	
	/**
	 * 取得尾页
	 * @return
	 */
	public int getBottonPageNo(){
		return getTotalPages();
	}
	
	/**
	 * 取得上一页
	 * @return
	 */
	public int getPreviousPageNo(){
		if(pageNo <= 1){
			return 1;
		}
		return pageNo - 1;
	}
	
	/**
	 * 取得下一页
	 * @return
	 */
	public int getNextPageNo(){
		if(pageNo >= getTotalPages()){
			return getTotalPages();
		}
		return pageNo + 1;
	}
	public List<A> getList() {
		return list;
	}

	public void setList(List<A> list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

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
}
