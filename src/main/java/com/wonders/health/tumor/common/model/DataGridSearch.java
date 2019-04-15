/**
 *
 */
package com.wonders.health.tumor.common.model;

/**
 * MiniUI对应的 DataGrid的搜索类
 * @author xuguobing
 */
public class DataGridSearch {
    private int pageIndex = 0;
    private int pageSize = 20;
    private String sortField;
    private String sortOrder; //desc,asc

    public Integer getStartRecord() {
        return pageIndex * pageSize;
    }

    public Integer getEndRecord() {
        return (pageIndex + 1) * pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
