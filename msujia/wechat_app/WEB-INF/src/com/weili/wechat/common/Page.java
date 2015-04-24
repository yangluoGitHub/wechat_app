package com.weili.wechat.common;


/**
 * 分页对象
 * 
 * @author shp
 * @since 2007.2.12
 */

public class Page{

	private static final int INIT_PAGE_SIZE = 50;   //初始每页数
	
	private int recordSum;	  //总行数		
	private int pageSum;      //总页数
	private int pageSize;     //每页数
	private int currentPage;  //当前页	
	private int startRow;     //查询记录集中开始行
	private int endRow;       //查询记录集中结束行
	private boolean isfirstQry;   //是否第一次查询
	private boolean hasNextPage;     //是否有下一页
	private boolean hasPreviousPage;  //是否有上一页
	
	/**
	 * 获得当前页
	 */
	public int getCurrentPage() {
		return currentPage;
	}	
	/**
	 * 获得总页数
	 */
	public int getPageSum() {
		return recordSum % pageSize == 0 ? recordSum/pageSize : recordSum/pageSize + 1;
	}
	/**
	 * 获得每页数
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 获得总行数
	 */
	public int getRecordSum() {
		return recordSum;
	}
	/**
	 * 获得查询记录集中开始行
	 */
	public int getStartRow() {
		return currentPage <= 1 ? 1 : (currentPage - 1) * pageSize + 1;
	}
	/**
	 * 获得查询记录集中结束行
	 */
	public int getEndRow() {
		return currentPage * pageSize < recordSum ? currentPage * pageSize : recordSum;
	}	
	/**
	 * 判断是否第一次查询
	 */
	public boolean getIsFirstQry() {
		return isfirstQry;
	}
	/**
	 * 判断是否有下一页
	 */
	public boolean getHasNextPage() {
		return getCurrentPage() < getPageSum();
	}
	/**
	 * 判断是否有上一页
	 */
	public boolean getHasPreviousPage() {
		return getCurrentPage() > 1;
	}
	
	
	/**
	 * 设置当前页
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 设置每页数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 设置总行数
	 */
	public void setRecordSum(int recordSum) {
		this.recordSum = recordSum;
	}	

	/**
	 * 设置是否第一次查询
	 */
	public boolean setIsFirstQry(boolean isfirstQry) {
		return this.isfirstQry = isfirstQry;
	}
	
	
	/**
	 * 构造函数（每页数使用初始值）
	 */
	public Page(){
		this.pageSize = INIT_PAGE_SIZE;
		this.currentPage = 1;
	}
	/**
	 * 构造函数（每页数使用参数值）
	 */
	public Page(int pageSize){
		this.pageSize = pageSize;
		this.currentPage = 1;
	}
	
}

