//初始化信息
$(function(){
	
	//单个菜单功能按钮方法
	$(".add_value .checkbox").click(function(){
		//获得当前标签ID
		var id = $(this).attr("id");
		
		//截取获得一级菜单ID
		id = id.substring(id.indexOf('_') + 1);
		var supMenuId = id.substring(0, id.indexOf('_'));
		
		//截取获得二级菜单ID
		id = id.substring(id.indexOf('_') + 1);
		var subMenuId = id.substring(0, id.indexOf('_'));
		
		//截取获得三级级菜单ID
		id = id.substring(id.indexOf('_') + 1);
		var secSubMenuId = id.substring(0, id.indexOf('_'));
		
		//判断没有选择的数量
		if($("#content_" + supMenuId + " label").not($("#content_" + supMenuId + " .checked")).size() > 0){
			$("#all_" + supMenuId).removeClass("checked");
		} else {
			$("#all_" + supMenuId).addClass("checked");
		}
		
		//判断是否拥有二、三级菜单权限
		if($("#value_" + secSubMenuId + " .checked").size() > 0){
			$("#sub_" + subMenuId).addClass("checked");
			$("#sub_" + secSubMenuId).addClass("checked");
		} else {
			$("#sub_" + subMenuId).removeClass("checked");
			$("#sub_" + secSubMenuId).removeClass("checked");
		}
		
		//判断是否拥有一级菜单权限
		if($("#content_" + supMenuId + " .checked").size() > 0){
			$("#sup_" + supMenuId).addClass("checked");
		} else {
			$("#sup_" + supMenuId).removeClass("checked");
		}
	});
	
	//全选按钮方法
	$(".character_tit .checkbox").click(function(){
		//获得当前标签的id class参数
		var id = $(this).attr("id");
		var clazz = $(this).attr("class");
		id = id.substring(id.lastIndexOf('_') + 1);
		
		//设置当前标签下的checkbox的是否全选
		if(clazz.indexOf('checked') > 0){
			$("#sup_" + id).addClass("checked");
			$("#content_" + id + " .checkbox").addClass("checked");
		} else {
			$("#sup_" + id).removeClass("checked");
			$("#content_" + id + " .checkbox").removeClass("checked");
		}
	});
	
	init();
	
});

function init(){
	//设置全选按钮
	$.each($(".character_content"), function(i,n){
		if($(n).find(".checkbox.menuId").not($(n).find(".checkbox.menuId.checked")).size() > 0){
			$(".character_tit").eq(i).find(".checkbox").not($(".character_tit").eq(i).find(".checkbox.menuId")).eq(0).removeClass("checked");
		} else {
			$(".character_tit").eq(i).find(".checkbox").not($(".character_tit").eq(i).find(".checkbox.menuId")).eq(0).addClass("checked");
		}
	});
}

//保存方法
function roleMenuSave(){
	var menuIds = "";
	$.each($("label.menuId.checked"),function(i,n){
		var id = $(n).attr("id");
		id = id.substring(id.lastIndexOf('_') + 1);
		menuIds += id + ",";
	});
	menuIds = menuIds.substring(0, menuIds.length - 1);
	return menuIds;
}
