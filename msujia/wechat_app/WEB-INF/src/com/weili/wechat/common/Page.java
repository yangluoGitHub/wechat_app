package com.weili.wechat.common;


/**
 * ��ҳ����
 * 
 * @author shp
 * @since 2007.2.12
 */

public class Page{

	private static final int INIT_PAGE_SIZE = 50;   //��ʼÿҳ��
	
	private int recordSum;	  //������		
	private int pageSum;      //��ҳ��
	private int pageSize;     //ÿҳ��
	private int currentPage;  //��ǰҳ	
	private int startRow;     //��ѯ��¼���п�ʼ��
	private int endRow;       //��ѯ��¼���н�����
	private boolean isfirstQry;   //�Ƿ��һ�β�ѯ
	private boolean hasNextPage;     //�Ƿ�����һҳ
	private boolean hasPreviousPage;  //�Ƿ�����һҳ
	
	/**
	 * ��õ�ǰҳ
	 */
	public int getCurrentPage() {
		return currentPage;
	}	
	/**
	 * �����ҳ��
	 */
	public int getPageSum() {
		return recordSum % pageSize == 0 ? recordSum/pageSize : recordSum/pageSize + 1;
	}
	/**
	 * ���ÿҳ��
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * ���������
	 */
	public int getRecordSum() {
		return recordSum;
	}
	/**
	 * ��ò�ѯ��¼���п�ʼ��
	 */
	public int getStartRow() {
		return currentPage <= 1 ? 1 : (currentPage - 1) * pageSize + 1;
	}
	/**
	 * ��ò�ѯ��¼���н�����
	 */
	public int getEndRow() {
		return currentPage * pageSize < recordSum ? currentPage * pageSize : recordSum;
	}	
	/**
	 * �ж��Ƿ��һ�β�ѯ
	 */
	public boolean getIsFirstQry() {
		return isfirstQry;
	}
	/**
	 * �ж��Ƿ�����һҳ
	 */
	public boolean getHasNextPage() {
		return getCurrentPage() < getPageSum();
	}
	/**
	 * �ж��Ƿ�����һҳ
	 */
	public boolean getHasPreviousPage() {
		return getCurrentPage() > 1;
	}
	
	
	/**
	 * ���õ�ǰҳ
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * ����ÿҳ��
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * ����������
	 */
	public void setRecordSum(int recordSum) {
		this.recordSum = recordSum;
	}	

	/**
	 * �����Ƿ��һ�β�ѯ
	 */
	public boolean setIsFirstQry(boolean isfirstQry) {
		return this.isfirstQry = isfirstQry;
	}
	
	
	/**
	 * ���캯����ÿҳ��ʹ�ó�ʼֵ��
	 */
	public Page(){
		this.pageSize = INIT_PAGE_SIZE;
		this.currentPage = 1;
	}
	/**
	 * ���캯����ÿҳ��ʹ�ò���ֵ��
	 */
	public Page(int pageSize){
		this.pageSize = pageSize;
		this.currentPage = 1;
	}
	
}

