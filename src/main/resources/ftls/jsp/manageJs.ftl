<#include "../constant/setHead.ftl" />
<#assign colNames = "" />
<#assign colModel = "" />
<#assign param = "" />
<#list  cfp.columns as column>
    <#if column.isColumn>
        <#if column.isAutoInctement>
            <#assign colIndex = column.attrName />
            <#assign colNames = colNames + "'主键'" />
            <#assign colModel = colModel + "{name: '" + column.attrName + "',index: '" + column.attrName + "',key:true,hidden:true}" />
        <#else>
            <#if (column.detail?index_of("：") > 0)>
                <#assign colNames = colNames + "'" + column.detail?substring(0, column.detail?index_of("：")) + "'" />
            <#else>
                <#assign colNames = colNames + "'" + column.detail + "'" />
            </#if>
            <#assign colModel = colModel + "{name: '" + column.attrName + "',index: '" + column.attrName + "',sortable:false,width:100}" />
        </#if>
        <#if column_has_next>
            <#assign colNames = colNames + "," />
            <#assign colModel = colModel + ";" />
            <#assign param = param + "," />
        </#if>
    </#if>
    <#if column.isSearchable>
        <#assign param = param + column.attrName />
    </#if>
</#list>
<#if colNames?substring((colNames?length - 1)) == ",">
	<#assign colNames = colNames + "'操作'" />
<#else>
	<#assign colNames = colNames + ",'操作'" />
</#if>
<#if colModel?substring((colModel?length - 1)) == ";">
	<#assign colModel = colModel + "{name: 'act',index: 'act',width:120,formatter:operate}" />
<#else>
	<#assign colModel = colModel + ";{name: 'act',index: 'act',width:120,formatter:operate}" />
</#if>
//代码生成方法JS对象
var ${cfp.moduleName?uncap_first}ManageJs = {};

var ${cfp.moduleName?uncap_first}Grid = {};

$(function(){
    //生成列表主体
    ${cfp.moduleName?uncap_first}Grid = $("#jqGrid").jqGrid({
        url: ctx.path + '/api/${cfp.moduleName?uncap_first}/query${cfp.moduleName?cap_first }List' + ctx.bizSuffix,
        mtype: "POST",
        datatype: "json",
        styleUI : 'Bootstrap',
        autowidth: true,//自动宽
        shrinkToFit:true,
        autoScroll: true,
        loadui: "Disable",//移除'加载中...'
        rowNum: 20,
        rowList: [20, 50, 100],
        colNames: [${colNames}],
        colModel: [
            <#list colModel?split(";") as col>
                ${col}<#if col_has_next>,</#if>
            </#list>
        ],
        height: tableGridHeight(),
        rownumbers:true,//添加左侧行号
        altRows:true,//设置为交替行表格,默认为false
        pager: "#jqGridPager",
        viewrecords: true, //是否在浏览导航栏显示记录总数
        loadComplete:function(data){ //完成服务器请求后，回调函数
            top.layer.closeAll();
            layer.closeAll();
        }
    });

    //屏幕发生变化的时候计算表格高度
    $(window).resize( function () {
        var width=document.documentElement.clientWidth-20;
        $("#jqGrid").setGridWidth(width);
        $("#jqGrid").setGridHeight(tableGridHeight());
    });

});

//刷新数据
${cfp.moduleName?uncap_first}ManageJs.reloadGrid = function (){
    ${cfp.moduleName?uncap_first}Grid.trigger("reloadGrid");
}

//执行查询
${cfp.moduleName?uncap_first}ManageJs.doSearch = function() {
    var params = {};
    <#list param?split(",") as p>
        <#if p != ''>
            var ${p} = $("#${p}").val();
        </#if>
    </#list>
    <#list param?split(",") as p>
        <#if p != ''>
            params['${p}'] = $.trim(${p});
        </#if>
    </#list>

    top.layer.load(0, {shade: [0.3,'#000']});
    ${cfp.moduleName?uncap_first}Grid.jqGrid('setGridParam',{page:1,postData:params}).trigger("reloadGrid");
};

//查看${cfp.tableNameZN}
${cfp.moduleName?uncap_first}ManageJs.openViewPage = function (id){
    //页面层
    index = layer.open({
        type : 2,
        title : '查看代理商信息',
        move : false,
        area : [ '460px', '480px' ], //宽高
        content : ctx.path + '/api/${cfp.moduleName?uncap_first}/open${cfp.moduleName}View' + ctx.pageSuffix + '?id=' + id ,
        btn : [ '关闭' ]
    });
};

<#list cfp.createMethodType as method>
    <#if method == 1>
        //新增${cfp.tableNameZN}
        ${cfp.moduleName?uncap_first}ManageJs.openAddPage = function (){
            //页面层
            index = layer.open({
                type : 2,
                title : '新增${cfp.tableNameZN}',
                move : false,
                area : [ '460px', '480px' ], //宽高
                content : ctx.path + '/api/${classBean.modelName?uncap_first}/open${classBean.modelName}Add' + ctx.noAuthSuffix ,
                btn : [ '保存', '关闭' ],
                yes : function(index, layero) {
                    //获取iframe中对象
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    //获取iframe中js方法
                    iframeWin.${cfp.moduleName?uncap_first}AddJs.submitForm();
                },
                cancel : function(index) {

                }
            });
        };

    <#elseif method == 2>
        //删除${cfp.tableNameZN}
        ${cfp.moduleName?uncap_first}ManageJs.del${cfp.moduleName?cap_first} = function (id, name){
            var msg = "确定删除${cfp.tableNameZN}【" + name + "】吗？";
            layer.confirm(msg, {icon: 3, title:"提示信息"}, function(index){
                $.get(ctx.path + '/api/${cfp.moduleName?uncap_first}/del${cfp.moduleName}' + ctx.bizSuffix + '?id=' + id, function(result){
                    if(result.rs==1) {
                        top.toastr.success("删除${cfp.tableNameZN}【" + name + "】成功！");
                        ${cfp.moduleName?uncap_first}ManageJs.reloadGrid();//刷新表单
                    }else{
                        top.toastr.error("删除${cfp.tableNameZN}【" + name + "】失败！");
                    }
                }, "json");
                layer.close(index);
            });
        };

    <#elseif method == 3>
        //修改${cfp.tableNameZN}
        ${cfp.moduleName?uncap_first}ManageJs.openEditPage = function (id){
            //页面层
            index = layer.open({
                type : 2,
                title : '编辑${cfp.tableNameZN}',
                move : false,
                area : [ '460px', '480px' ], //宽高
                content : ctx.path + '/api/${cfp.moduleName?uncap_first}/open${cfp.moduleName?cap_first }Update' + ctx.noAuthSuffix + '?id=' + id ,
                btn : [ '保存', '关闭' ],
                yes : function(index, layero) {
                    //获取iframe中对象
                    var iframeWin = window[layero.find('iframe')[0]['name']];
                    //获取iframe中js方法
                    iframeWin.${cfp.moduleName?uncap_first}UpdateJs.submitForm();
                },
                cancel : function(index) {
                }
            });
        };

    </#if>
</#list>



  