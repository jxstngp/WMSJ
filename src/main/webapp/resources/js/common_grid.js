var common = {};

/**
 * jqgrid表单公共查询
 * 页面引入common-grid.js才可使用
 * 1、默认查询<form id="pageForm">
 * 2、jqgrid默认指定id为:jqGrid
 */
common.search = function search(gridId,searchId){
	var params = {};
	//若果不传参数过来,例：common.search(),则默认指定  id="pageForm"及id="jqGrid"
	var _searchId = "pageForm";
	var _gridId   = "jqGrid";
	
	if(typeof(searchId) !="undefined"){
		_searchId = searchId;
	}
	if(typeof(gridId) !="undefined"){
		_gridId = gridId;    
	}
	//显示消息
	$("#"+_gridId)[0].grid.beginReq();
	var fields = $('#'+_searchId).serializeArray();
	$.each( fields, function(i, field){
		params[field.name] = $.trim(field.value);
	});
	//设置参数
	$("#"+_gridId).jqGrid('setGridParam',{page:1,rows:20,postData:params});
	//隐藏消息
	$("#"+_gridId)[0].grid.endReq();
	//刷新表单
	$("#"+_gridId).trigger("reloadGrid"); 
};

/**
 * 查询条件清空条件
 * 默认页面查询 <form id="pageForm">
 */
common.clear = function clear(searchId){
	var _searchId = "pageForm";
	if(typeof(searchId) !="undefined"){
		_searchId = searchId;
	}
	
	$("#"+_searchId).find("input").each(function(){
		if($(this).attr("type") == "button")return;
		if($(this).attr("type") == "reset")return;
		if($(this).attr("type") == "radio")return;
		if($(this).attr("type") == "checkbox"){
			$(this).attr("checked",false);
			return
		};
		$(this).val("");
	});
	$("#"+_searchId).find("select").each(function(){
		$(this)[0].selectedIndex = 0;
	});
};