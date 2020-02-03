<#include "../constant/setHead.ftl" />
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" href="${r"${ctx.path}"}/resources/components/jqueryUI/jquery-ui.css">
    <script src="${r"${ctx.path}"}/resources/components/jqueryUI/jquery-ui.js"></script>
    <script src="${r"${ctx.path}"}/resources/script/${cfp.moduleName?uncap_first}/${cfp.moduleName?uncap_first}Add.js"></script>
</head>
<body>
    <div id="add_box" class="add_box mCustomScrollbar_y" ata-mcs-theme="dark">
        <form class="tableform" action="${r"${ctx.path}"}/api/${cfp.moduleName?uncap_first}/add${cfp.moduleName?cap_first}${r"${ctx.bizSuffix}"}" method="post" id="add${cfp.moduleName?cap_first}Form">
            <#list cfp.columns as column>
                <#if !column.isAutoInctement && column.isForm>
                    <div class="add_list">
                        <h5><#if (column.detail?index_of("：") > 0)><#--<em class="wildcard">*</em>-->${column.detail?substring(0, column.detail?index_of("："))}：<#else><#--<em class="wildcard">*</em>-->${column.detail}：</#if></h5>
                        <div class="add_value">
                            <input type="text" name="${column.attrName}" id="${column.attrName}" <#if column.fieldLength != 0>maxlength="${column.fieldLength}"</#if> class="inputtext" />
                        </div>
                    </div>
                </#if>
            </#list>
        </form>
    </div>
</body>
</html>

