package com.samton.platform.framework.bean;

/**
 * @Description: 自定义标签：下拉列表框Bean
 * @Author: YangYangen
 * @Date: 2019/10/26 10:16
 * Copyright (c) 2019, Samton. All rights reserved
*/
public class CommonSelect {
    private String val;
    private String text;
    private String table;
    private String where;
    private String order;
    private String sql;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public CommonSelect(String val, String text, String table, String where, String order, String sql) {
        this.val = val;
        this.text = text;
        this.table = table;
        this.where = where;
        this.order = order;
        this.sql = sql;
    }
}
