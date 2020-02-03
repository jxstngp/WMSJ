package com.samton.platform.framework.tld;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.samton.platform.framework.util.DictFinalUtil;

@SuppressWarnings("unchecked")
public class DictTag extends TagSupport {
    /*
     * 选择框 id
     */
    private String id;
    /*
     * 选择框 name
     */
    private String name;
    /*
     * 选择框 通过下拉框value默认选中
     */
    private String selectValue;
    /*
     * 选择框 通过下拉框文本默认选中
     */
    private String selectText;

    /*
     * 要显示字典中组的选项
     */
    private String dictGroupId;
    /*
     * 默认值
     */
    private String defaultValue = "-1";
    /*
     * 默认文本
     */
    private String defaultText = "请选择";
    /*
     * 显示控件长度
     */
    private String style;
    /*
     * 样式
     */
    private String className;
    /*
     * 过滤条件
     */
    private String condition;
    /*
     * 事件
     */
    private String onchange;

    public String getStyle() {
	return style;
    }

    public void setStyle(String style) {
	this.style = style;
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

    public String getSelectValue() {
	return selectValue;
    }

    public void setSelectValue(String selectValue) {
	this.selectValue = selectValue;
    }

    public String getSelectText() {
	return selectText;
    }

    public void setSelectText(String selectText) {
	this.selectText = selectText;
    }

    public String getDictGroupId() {
	return dictGroupId;
    }

    public void setDictGroupId(String dictGroupId) {
	this.dictGroupId = dictGroupId;
    }

    public String getClassName() {
	return className;
    }

    public void setClassName(String className) {
	this.className = className;
    }

    public String getCondition() {
	return condition;
    }

    public void setCondition(String condition) {
	this.condition = condition;
    }

    public String getOnchange() {
	return onchange;
    }

    public void setOnchange(String onchange) {
	this.onchange = onchange;
    }

    /**
     * 标签加载开始
     */
    @Override
    public int doStartTag() {
	try {
	    StringBuffer select = new StringBuffer();
	    JspWriter jsp = this.pageContext.getOut();
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
	    if (dictGroupId != null && !"".equals(dictGroupId)) {
		/*
		 * 查找字典表中的选项sql
		 */
		StringBuffer sql = new StringBuffer("SELECT di.dict_id,di.dict_name FROM t_sys_dict_info di,t_sys_dict_type dt WHERE dt.type_id = ? AND dt.type_id = di.type_id ");
		if (condition != null) {
		    sql.append("and ").append(condition);
		}
		sql.append(" ORDER BY di.sort");
		Object[] args = { DictFinalUtil.PineDealStatus.map.getDescByValue(dictGroupId) };
		select.append("<option value='");
		select.append(defaultValue);
		select.append("'>");
		select.append(defaultText);
		select.append("</option>");
		List list = new ArrayList();
		try {
		    /*
		     * 执行sql
		     */
		    JdbcTemplate jdbc = (JdbcTemplate) WebApplicationContextUtils.getWebApplicationContext(this.pageContext.getServletContext()).getBean("jdbcTemplate");
		    list = jdbc.queryForList(sql.toString(), args);
		} catch (Exception e1) {

		}
		if (list != null && list.size() > 0) {
		    for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String selectStatus = "";
			if (selectValue != null
				&& !"".equals(selectValue)
				&& selectValue.equals(map.get("dict_id").toString())) {
			    selectStatus = "selected='selected'";
			}
			if (selectText != null
				&& !"".equals(selectText)
				&& selectText.equals(map.get("dict_name").toString())) {
			    selectStatus = "selected='selected'";
			}
			select.append("<option value='");
			select.append(map.get("dict_id"));
			select.append("' ");
			select.append(selectStatus);
			select.append(">");
			select.append(map.get("dict_name"));
			select.append("</option>");
		    }
		}
		select.append("</select>");
		jsp.print(select.toString());
	    } else {
		select.append("<option value=''>");
		select.append("无下拉选项");
		select.append("</option>");
		select.append("</select>");
		jsp.print(select.toString());
	    }
	} catch (Exception e) {
	}
	reset();
	return SKIP_BODY;
    }

    private void reset() {
	id = null;
	name = null;
	selectValue = null;
	selectText = null;
	dictGroupId = null;
	defaultValue = "-1";
	defaultText = "请选择";
	style = null;
	className = null;
	condition = null;
	onchange = null;
    }
}
