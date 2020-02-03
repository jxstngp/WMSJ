$(document).ready(function(){
	//初始化提示控件
	toastr.options = {
	  "closeButton": false,
	  "debug": false,
	  "progressBar": false,
	  "positionClass": "toast-top-center",
	  "onclick": null,
	  "showDuration": "400",
	  "hideDuration": "1000",
	  "timeOut": "2000",
	  "extendedTimeOut": "1000",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	};
});

//创建菜单
var createMenuLi=function(menuObj){
	var menuLiStr = "";
//	var mhref = "";
	if(menuObj.menuType == 0){
		menuLiStr = '<dd class=\"panel\">'
			        +'<div class=\"panel_tit\">'
			        +'<a class=\"collapsed\" data-toggle=\"collapse\" data-parent=\"#mynav\" href=\"#'+menuObj.menuCode+'\">'
			        +'<i class=\"midicon '+menuObj.menuIcon+'\"></i>'
			        +'<em>'+menuObj.menuName+'</em>'
			        +'<i class=\"minicon caret\"></i>'
			        +'</a>'
			        +'</div>';
	}
	if(menuObj.children){
		menuLiStr+='<div id=\"'+menuObj.menuCode+'\" class=\"panel_body collapse in\"><ul>';
		for(var i=0,len=menuObj.children.length;i<len;i++){
			menuLiStr+='<li onclick="openMenu('+menuObj.children[i].menuId+',\''+menuObj.children[i].menuName+'\',\''+(menuObj.children[i].url==''?'':ctx.path+menuObj.children[i].url)+'\');"><a href="javascript:void(0);"><em></em>'+menuObj.children[i].menuName+'</a></li>';
		}
		menuLiStr+='</ul></div>';
	}
	
	menuLiStr+='</dd>';
	
/*	if(menuObj.menuType == 1){
		mhref = ctx.path+menuObj.url;
		menuLiStr ='<li>'
					+'<a class="J_menuItem" href="'+mhref+'">'
					+'<i class="fa fa-columns"></i> <span class="nav-label">'+menuObj.menuName+'</span></a>';
	}else{
		menuLiStr = "<li>"
					+'<a href="'+mhref+'">'
					+'<i class="fa fa-home"></i>'
					+'<span class="nav-label">'+menuObj.menuName+'</span>'
					+'<span class="fa arrow"></span>'
					+'</a>';
	}
	if(menuObj.children){
		menuLiStr+='<ul class="nav nav-second-level">';
		for(var i=0,len=menuObj.children.length;i<len;i++){
			menuLiStr+='<li><a class="J_menuItem" ';
			if(i==0){
				menuLiStr+=' data-index="0" ';
			}
			if(menuObj.children[i].url){
				menuLiStr+='href='+ctx.path+menuObj.children[i].url;
			}
			menuLiStr+='>'+menuObj.children[i].menuName+'</a></li>';
		}
		menuLiStr+='</ul>';
	}
	menuLiStr+='</li>';*/
	$("#mynav").append(menuLiStr);
};

//初始化菜单
var initMainMenus=function(menusMap){
	var rootMenus=menusMap["0"];
	for(var i=0,len=rootMenus.length;i<len;i++){
		var rootMenu=rootMenus[i];
		if(menusMap[rootMenu.menuId+""]){
			rootMenu.children=menusMap[rootMenu.menuId+""];
			delete menusMap[rootMenu.menuId+""];
		}
		createMenuLi(rootMenu);
	}
};

//退出登录
function logout(){
	   var msg="您确定要退出系统吗？";
	   layer.confirm(msg, {icon: 3, title:'提示信息'}, function(index){
		   window.location.href=ctx.path+'/platform/pm/logout.do';
		   layer.close(index);
	   });
}