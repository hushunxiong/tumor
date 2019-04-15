package com.wonders.health.tumor.gen.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by xuguobing on 2016/11/1 0001.
 */
public class GenTable implements Serializable {
    private String schema;//库名
    private String table; //表名

    private Set<String> entityIm = Sets.newHashSet();
    private Set<String> searchIm = Sets.newHashSet();
    private Set<String> serviceIm = Sets.newHashSet();

    private List<GenColumn> columns = Lists.newArrayList(); //表中存在的类

    public String getName() {
//        if (StringUtils.isNotBlank(schema)) {
//            return schema + "." + table;
//        } else {
//            return table;
//        }
        return table;
    }

    /**
     * 获取表单列的二维数组
     *
     * @return
     */
    public List<List<GenColumn>> getFormCols() {
        List<List<GenColumn>> gens = Lists.newArrayList();
        for (GenColumn col : columns) {
            if ("1".equals(col.getEdit())) {
                col.setCols(1);
                addCols(gens, col);
            }
        }
        return gens;
    }

    /**
     * 获取搜索列的二维数组
     *
     * @return
     */
    public List<List<GenColumn>> getSearchCols() {
        List<List<GenColumn>> gens = Lists.newArrayList();
        for (GenColumn col : columns) {
            if ("1".equals(col.getQuery())) {
                if (GenColumn.QUERY_TYPE_BW.equals(col.getQueryType())) {
                    col.setCols(2);
                } else {
                    col.setCols(1);
                }
                addCols(gens, col);
            }
        }
        GenColumn btn = new GenColumn();
        btn.setCols(1);
        btn.setShowType(GenColumn.SHOW_TYPE_BUTTON);
        addCols(gens, btn);
        return gens;
    }

    /**
     * 组装成4列的二维数组
     *
     * @param cols
     * @param col
     */
    public void addCols(List<List<GenColumn>> cols, GenColumn col) {
        Integer n = col.getCols();
        if (cols == null) {
            cols = Lists.newArrayList();
        }
        if (cols.size() == 0) {
            cols.add(Lists.<GenColumn>newArrayList());
        }
        for (List<GenColumn> gc : cols) {
            Integer size = 0;
            for (GenColumn c : gc) {
                size += c.getCols();
            }
            if (n + size <= 4) {
                gc.add(col);
                return;
            }
        }
        List<GenColumn> cs = Lists.newArrayList();
        cs.add(col);
        cols.add(cs);
    }

    /**
     * 获取主键列表
     *
     * @return
     */
    public List<GenColumn> getColumnPks() {
        List<GenColumn> pks = Lists.newArrayList();
        for (GenColumn col : columns) {
            if ("1".equals(col.getPk())) {
                pks.add(col);
            }
        }
        return pks;
    }

    /**
     * 判断主键是否为ID
     *
     * @return
     */
    public Boolean getPkIsId() {
        for (GenColumn col : columns) {
            if ("1".equals(col.getPk())) {
                if ("id".equals(col.getJavaField().toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否存在update_date列
     *
     * @return
     */
    public Boolean getUpdateDateExists() {
        for (GenColumn c : columns) {
            if ("update_date".equals(c.getName()) || "UPDATE_DATE".equals(c.getName())) {
                return true;
            }
        }
        return false;
    }

    public String getUpdateDateSqlCol() {
        for (GenColumn c : columns) {
            if ("update_date".equals(c.getName()) || "UPDATE_DATE".equals(c.getName())) {
                return c.getName();
            }
        }
        return null;
    }

    /**
     * 是否存在del_flag列
     *
     * @return
     */
    public Boolean getDelFlagExists() {
        for (GenColumn c : columns) {
            if ("STATUS".equals(c.getName()) || "status".equals(c.getName())
                    || "del_flag".equals(c.getName()) || "DEL_FLAG".equals(c.getName())) {
//            if ("del_flag".equals(c.getName()) || "DEL_FLAG".equals(c.getName())) {
                return true;
            }
        }
        return false;
    }

    public String getDelFlagSqlCol() {
        for (GenColumn c : columns) {
            if ("STATUS".equals(c.getName()) || "status".equals(c.getName())) {
                return c.getName();
            } else if ("del_flag".equals(c.getName()) || "DEL_FLAG".equals(c.getName())) {
                return c.getName();
            }
        }
        return null;
    }

    public void addEntityIm(String im) {
        entityIm.add(im);
    }

    public void addSearchIm(String im) {
        searchIm.add(im);
    }

    public void addServiceIm(String im) {
        serviceIm.add(im);
    }

    public void addColumn(GenColumn col) {
        columns.add(col);
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Set<String> getEntityIm() {
        return entityIm;
    }

    public void setEntityIm(Set<String> entityIm) {
        this.entityIm = entityIm;
    }

    public Set<String> getSearchIm() {
        return searchIm;
    }

    public void setSearchIm(Set<String> searchIm) {
        this.searchIm = searchIm;
    }

    public Set<String> getServiceIm() {
        return serviceIm;
    }

    public void setServiceIm(Set<String> serviceIm) {
        this.serviceIm = serviceIm;
    }

    public List<GenColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<GenColumn> columns) {
        this.columns = columns;
    }
}
