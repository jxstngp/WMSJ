<#include "../constant/setHead.ftl" />
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<%@taglib prefix="p" uri="/custom-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" href="${r"${ctx.path}"}/resources/components/jqgrid/ui.jqgrid.css">
    <link rel="stylesheet" href="${r"${ctx.path}"}/resources/components/jqgrid/glyphicons.css">
    <script src="${r"${ctx.path}"}/resources/components/jqgrid/i18n/grid.locale-cn.js"></script>
    <script src="${r"${ctx.path}"}/resources/components/jqgrid/jquery.jqGrid.min.js"></script>
    <script src="${r"${ctx.path}"}/resources/script/${cfp.moduleName?uncap_first}/${cfp.moduleName?uncap_first}Manage.js?v=${r"${ctx.version}"}"></script>
    <script src="${r"${ctx.path}"}/resources/js/common/DataForm.js?v=${r"${ctx.version}"}"></script>
    <script type="text/javascript">
        function operate(cellValue,options,rowObject) {
            var button = "";
					<#list  cfp.columns as column>
						<#if column.isAutoInctement>
                            button += "<p:auth mcode='MENU_MANAGE_BUSINESS_${cfp.moduleName?upper_case}_VIEW'>";
                            button += "<button class=\"operationbtn\" type=\"button\" onclick=\"${cfp.moduleName?uncap_first}ManageJs.openViewPage("+ rowObject.${column.attrName} +");\">";
                            button += "<span>查看</span></button></p:auth>";
							<#list cfp.createMethodType as method>
								<#if method == 3>
                                    button += "<p:auth mcode='MENU_MANAGE_BUSINESS_${cfp.moduleName?upper_case}_UPDATE'>";
                                    button += "<button class=\"operationbtn\" type=\"button\" onclick=\"${cfp.moduleName?uncap_first}ManageJs.openEditPage("+ rowObject.${column.attrName} +");\">";
                                    button += "<span>编辑</span></button></p:auth>";
                                <#elseif method == 2>
                                    button += "<p:auth mcode='MENU_MANAGE_BUSINESS_${cfp.moduleName?upper_case}_DEL'>";
                                    button += "<button class=\"operationbtn\" type=\"button\" onclick=\"${cfp.moduleName?uncap_first}ManageJs.del${cfp.moduleName?cap_first}("+ rowObject.${column.attrName} +");\">";
                                    button += "<span>删除</span></button></p:auth>";
								</#if>
							</#list>
						</#if>
					</#list>
            return button;
        }
    </script>
</head>
<body style="overflow:hidden;">
<div class="main_con">
    <div class="headerbox">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
					<#list cfp.createMethodType as method>
						<#if method == 1>
                            <p:auth mcode="MENU_MANAGE_BUSINESS_${cfp.moduleName?upper_case}_ADD">
                                <button type="button" class="btn add_btn" onclick="${cfp.moduleName?uncap_first}ManageJs.openAddPage();">
                                    <i class="minicon addoperation_icon"></i><span>新增</span>
                                </button>
                            </p:auth>
						</#if>
					</#list>
                </div>
            </div>
        </div>
        <div class="search_box" id="search_box">
            <form id="searchForm">
                <div class="search_nav">
                    <ul>
						<#list  cfp.columns as column>
							<#if column.isSearchable>
								<li>
									<input type="text" id="${column.attrName}" name="${column.attrName}" <#if column.fieldLength != 0>maxlength="${column.fieldLength}"</#if> class="inputtext" placeholder="${column.detail}" />
								</li>
							</#if>
						</#list>
                    </ul>
                </div>
            </form>
            <div class="search_btncon">
                <button type="button" class="btn add_btn" onClick="${cfp.moduleName?uncap_first}ManageJs.doSearch()">
                    <i class="minicon search_icon"></i>
                    <span>查询</span>
                </button>
                <button type="button" class="btn export_btn clearToolBtn">
                    <i class="minicon reload_icon"></i>
                    <span>重置</span>
                </button>
            </div>
        </div>
    </div>
    <div class="tablelist_box">
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </div>
</div>
</body>
</html>