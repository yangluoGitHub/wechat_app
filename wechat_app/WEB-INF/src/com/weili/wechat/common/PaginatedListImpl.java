package com.weili.wechat.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/**
 * 实现外部分页
 * @author Administrator
 *
 */
public class PaginatedListImpl implements PaginatedList  {
	List list = new ArrayList();
    int pageNumber = 1;
    int objectsPerPage = 15;
    int fullListSize = 0;
    String sortCriterion;
    SortOrderEnum sortDirection = SortOrderEnum.ASCENDING ;
    String searchId;

    public void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }

    public void setSortDirection(SortOrderEnum sortDirection) {
        this.sortDirection = sortDirection;
    }

    /*
     * @see org.displaytag.pagination.PaginatedList#getList()
     */
    public List getList() {
        return this.list;
    }

    /*
     * @see org.displaytag.pagination.PaginatedList#getPageNumber()
     */
    public int getPageNumber() {

        return this.pageNumber;
    }

    /*
     * @see org.displaytag.pagination.PaginatedList#getObjectsPerPage()
     */
    public int getObjectsPerPage() {
        return this.objectsPerPage;
    }

    /* (non-Javadoc)
     * @see org.displaytag.pagination.PaginatedList#getFullListSize()
     */
    public int getFullListSize() {

        return this.fullListSize;
    }

    /*
     * @see org.displaytag.pagination.PaginatedList#getSortCriterion()
     */
    public String getSortCriterion() {
        return this.sortCriterion;
    }

    /*
     * @see org.displaytag.pagination.PaginatedList#getSortDirection()
     */
    public SortOrderEnum getSortDirection() {
        return this.sortDirection;
    }

    /*
     * @see org.displaytag.pagination.PaginatedList#getSearchId()
     */
    public String getSearchId() {
        return this.searchId;
    }
    
    public static void setParam(PaginatedListImpl lstPage, HashMap retMap, int intCurrentPage) {
    	//设置当前页
		lstPage.setPageNumber(intCurrentPage); 
		
		//从数据库中读取数据并以对象列表的形式返回
		List list = (List)retMap.get("list");
		
		//记录总数
		int intFullCount = Integer.parseInt(String.valueOf(retMap.get("totalRow")));
		
		//设置记录总数
		lstPage.setFullListSize(intFullCount);
		
		//设置显示的对象集合
		lstPage.setList(list);
    }
    
}
