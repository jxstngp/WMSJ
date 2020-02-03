<#include "../constant/setHead.ftl" />
<#macro jspEl obj attr>${r"${"}${obj}.${attr}${r"}"}</#macro>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <link rel="stylesheet" href="${r"${ctx.path}"}/resources/components/jqueryUI/jquery-ui.css">
        <script src="${r"${ctx.path}"}/resources/components/jqueryUI/jquery-ui.js"></script>
    </head>
    <body>
        <div id="detail_box" class="detail_box mCustomScrollbar_y" data-mcs-theme="dark">
            <table width="100%">
                <thead></thead>
                <tbody>
                    <#list cfp.columns as column>
                        <#if !column.isAutoInctement && column.isForm>
                            <tr>
                                <th width="20%">
                                    <#if (column.detail?index_of("：") > 0)><#--<em class="wildcard">*</em>-->${column.detail?substring(0, column.detail?index_of("："))}：<#else><#--<em class="wildcard">*</em>-->${column.detail}：</#if>
                                </th>
                                <td width="60%"><@jspEl obj="${classBean.voName?uncap_first}" attr="${column.attrName}" /></td>
                            </tr>
                        </#if>
                    </#list>
                </tbody>
            </table>
        </div>
    </body>
</html>

