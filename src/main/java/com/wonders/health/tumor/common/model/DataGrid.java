/**
 *
 */
package com.wonders.health.tumor.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * MiniUI对应的 DataGrid
 *
 * @author xuguobing
 */
public class DataGrid<T> {

    private long total = 0l;
    private List<T> data = new ArrayList<T>();

    public DataGrid() {
    }

    public DataGrid(long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
