package com.samton.platform.framework.tld;

import com.samton.platform.common.service.ICommonSelectService;
import com.samton.platform.framework.bean.CommonSelect;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @Description: 自定义标签：下拉列表框TAG
 * @Author: YangYangen
 * @Date: 2019/10/26 10:26
 * Copyright (c) 2019, Samton. All rights reserved
*/
public class CommonSelectTag extends SimpleTagSupport {

    private String id;
    private String name;
    private String style;
    private String className;
    private String onchange;
    private String defaultValue = "";
    private String defaultText = "请选择";
    private String selectValue;
    private String table;
    private String val;
    private String text;
    private String where;
    private String order;
    private String sql;
    private boolean showDefault=true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
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

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOnchange() {
        return onchange;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public boolean isShowDefault() {
        return showDefault;
    }

    public void setShowDefault(boolean showDefault) {
        this.showDefault = showDefault;
    }

    @Override
    public void doTag() throws JspException, IOException {
        try {
            StringBuffer select = new StringBuffer();
            JspWriter jsp = this.getJspContext().getOut();
            select.append("<select");
            /*
             * 给select加上id
             */
            if (id != null && !"".equals(id)) {
                select.append(" id='").append(id).append("'");
            }
            /*
             * 给select加上name
             */
            if (name != null && !"".equals(name)) {
                select.append(" name='").append(name).append("'");
            }
            /*
             * 给select加上style
             */
            if (style != null && !"".equals(style)) {
                select.append(" style='").append(style).append("'");
            }
            /*
             * 加上class
             */
            if (className != null && !"".equals(className)) {
                select.append(" class='").append(className).append("'");
            }
            /*
             * 加上onchange事件
             */
            if (onchange != null && !"".equals(onchange)) {
                select.append(" onchange='").append(onchange).append("'");
            }

            select.append(">");
            if (isShowDefault()) {
                select.append("<option value='");
                select.append(defaultValue);
                select.append("'>");
                select.append(defaultText);
                select.append("</option>");
            }


            List list = new ArrayList();
            try {
                //注入service
                ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
                WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
                ICommonSelectService commonSelectService = (ICommonSelectService) wac.getBean("commonSelectService");
                /*
                 * 执行sql
                 */
                CommonSelect commonSelect = new CommonSelect(val, text, table, where, order, sql);

                list = commonSelectService.loadOptions(commonSelect);
            } catch (Exception e1) {
//                throw new RuntimeException("CommonSelectTag database query error!");
                e1.printStackTrace();
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String selectStatus = "";
                    if (selectValue != null
                            && !"".equals(selectValue)
                            && selectValue.equals(map.get("val")
                            .toString())) {
                        selectStatus = "selected='selected'";
                    }
                    select.append("<option value='");
                    select.append(map.get("val"));
                    select.append("' ");
                    select.append(selectStatus);
                    select.append(">");
                    select.append(map.get("text"));
                    select.append("</option> ");
                }
            }
            select.append("</select>");
            jsp.print(select.toString());
        } catch (Exception e) {
        }
        reset();
    }

    private void reset() {
        id = null;
        name = null;
        selectValue = null;
        defaultValue = "";
        defaultText = "请选择";
        style = null;
        className = null;
        table = null;
        val = null;
        text = null;
        order = null;
        onchange = null;
        sql = null;
    }
}
