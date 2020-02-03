var createMenuLi = function(menuObj) {
	var menuLiStr = "";
//	var mhref = "";
	if (menuObj.menuType == 0) {
		// 一级菜单
		menuLiStr = '<dd>' + '<div class=\"navone\">'
				+ '<a class=\"collapsed\"  href=\"javascript:;\">'
				+ '<i class=\"midicon ' + menuObj.menuIcon + '\"></i>' + '<em>'
				+ menuObj.menuName + '</em>'
				+ '<i class=\"minicon caret\"></i>' + '</a>' + '</div>';
	}
	if (menuObj.children) {
		menuLiStr += '<div class="navonecon"><ul>';
		for (var i = 0, len = menuObj.children.length; i < len; i++) {
			// 有三级菜单
			if (menuObj.children[i].children) {
				menuLiStr += '<li><div class="navtwo"><a href="javascript:"><i class="plus_icon"></i><em>' + menuObj.children[i].menuName + '</em></a></div>';
				menuLiStr += '<div class="navtwocon"><ul>';
				for (var j = 0, len1 = menuObj.children[i].children.length; j < len1; j++) {
					menuLiStr += '<li><a id=\'menu_'+menuObj.children[i].children[j].menuId+'\' onclick="openMenu('
							+ menuObj.children[i].children[j].menuId
							+ ',\''
							+ menuObj.children[i].children[j].menuName
							+ '\',\''
							+ (menuObj.children[i].children[j].menuUrl == '' ? ctx.path + '/resources/platform/views/error/404.jsp' : ctx.path + menuObj.children[i].children[j].menuUrl)
							+ '\',\'<em>' + menuObj.menuName + '</em>><em>'
							+ menuObj.children[i].menuName + '</em>\',\''
							+ menuObj.menuIcon
							+ '\');" href="javascript:void(0);"><em></em>'
							+ menuObj.children[i].children[j].menuName
							+ '</a></li>';
				}
				menuLiStr += '</ul></div></li>';
			} else {
				// 功能级菜单
				menuLiStr += '<li><div class="navtwo"><a id=\'menu_'+menuObj.children[i].menuId+'\' onclick="openMenu('
						+ menuObj.children[i].menuId
						+ ',\''
						+ menuObj.children[i].menuName
						+ '\',\''
						+ (menuObj.children[i].menuUrl == '' ? ctx.path + '/resources/platform/views/error/404.jsp' : ctx.path + menuObj.children[i].menuUrl)
						+ '\',\'<em>'
						+ menuObj.menuName
						+ '</em>\',\''
						+ menuObj.menuIcon
						+ '\');" href="javascript:void(0);"><i class="minus_icon"></i><em>'
						+ menuObj.children[i].menuName + '</em></a></div></li>';
			}
		}
		menuLiStr += '</ul></div>';
	}

	menuLiStr += '</dd>';

	$("#mynav").append(menuLiStr);
};

var initMainMenus = function(menusMap) {
	var rootMenus = menusMap["0"];
	for (var i = 0, len = rootMenus.length; i < len; i++) {
		var rootMenu = rootMenus[i];
		if (menusMap[rootMenu.menuId + ""]) {
			rootMenu.children = menusMap[rootMenu.menuId + ""];
			for (var n = 0; n < rootMenu.children.length; n++) {
				if (menusMap[rootMenu.children[n].menuId + ""]) {
					rootMenu.children[n].children = menusMap[rootMenu.children[n].menuId + ""];
					delete menusMap[rootMenu.children[n].menuId + ""];
				}
			}
			delete menusMap[rootMenu.menuId + ""];
		}
		createMenuLi(rootMenu);
	}

	

	// 菜单伸缩

	$("#menuslide").click(function() {
		var $navbox = $(".navbox");
		var $mainbox = $(".main_box");
		var $width = $navbox.width();
		if (parseInt($width) ==216) {
			$navbox.removeClass("big_navbox").addClass("mini_navbox");
			$mainbox.stop(true, true).animate({
				left : "58px"
			});
			
		} else {
			$navbox.removeClass("mini_navbox").addClass("big_navbox");
			$mainbox.stop(true, true).animate({
				left : "216px"
			});
		}
		$(".header .logo").toggleClass("mini_logo");
	});
	
	
	// 二级菜单伸缩
	$(".big_navbox .navone").click(function() {
		if($(this).parents().is(".big_navbox")){
			var $navone = $(this).parent("dd").siblings().find(".navone");
			$(this).toggleClass("active").next(".navonecon").slideToggle();
			$navone.removeClass("active").next(".navonecon").slideUp();
		}else{
			return false;
		}
	});

	// 三级菜单伸缩
	$(".big_navbox .navtwo").click(function() {
		var $navtwo = $(".navtwo").not(this);
		$(this).toggleClass("active");
		if ($(this).next().is(".navtwocon")) {
			$(this).next(".navtwocon").slideToggle();
		} else {
			$(this).addClass("active");
		}
		$navtwo.removeClass("active").next(".navtwocon").slideUp();
	});

	// 三级菜单选中效果
	$(".big_navbox .navtwocon li").click(function() {
		$(".navtwocon li").not(this).removeClass("active");
		$(this).addClass("active");
	});
	
	
};

// 退出登录
function logout() {
	var msg = "您确定要退出系统吗？";
	layer.confirm(msg, {
		icon : 3,
		title : '提示信息'
	}, function(index) {
		window.location.href = ctx.path + '/platform/pm/logout' + ctx.noAuthSuffix;
		layer.close(index);
	});
}

$(function() {
	$("#personInfoEditForm").validate(
			{
				rules : {
					"userName" : {
						required : true
					},
					"mobile" : {
						isMobile : true
					},
					"email" : {
						email : true
					}
				},
				messages : {
					"userName" : {
						required : "请输入您的用户名！"
					},
					"mobile" : {
						isMobile : "请输入正确的手机号码！"
					},
					"email" : {
						email : "请输入正确的邮箱！"
					}
				},
				submitHandler : function(form) {
					$(form).ajaxSubmit(
							{
								success : function(responseText, statusText,
										xhr, $form) {
									if (responseText.rs == -1) {
										top.toastr.error("编辑用户【" + $("#userNameEdit").val() + "】个人信息失败！");
										return false;
									}
									top.toastr.success("编辑用户【" + $("#userNameEdit").val() + "】个人信息成功！");
									FormUtil.resetForm("personInfoEditForm");
									layer.close(index); // 再执行关闭
								}
							});
				}
			});

	pwdFormValidate = $("#pwdForm").validate(
			{
				rules : {
					"oldPwd" : {
						required : true,
						rangelength : [ 6, 16 ]
					},
					"newPwd" : {
						required : true,
						rangelength : [ 6, 16 ],
						isNotSameValue : true
					},
					"newPwdAgain" : {
						required : true,
						rangelength : [ 6, 16 ],
						isSameValue : true
					}
				},
				messages : {
					"oldPwd" : {
						required : "请输入原密码！ ",
						rangelength : "密码长度不能少于6！ "
					},
					"newPwd" : {
						required : "请输入新密码！ ",
						rangelength : "密码长度不能少于6！",
						isNotSameValue : "新密码不能与原密码一致！"
					},
					"newPwdAgain" : {
						required : "请输入确认密码！ ",
						rangelength : "密码长度不能少于6！ ",
						isSameValue : "新密码与确认密码不一致！"
					}
				},
				submitHandler : function(form) {
					$(form).ajaxSubmit(
							{
								success : function(responseText, statusText,
										xhr, $form, result) {
									if (responseText.rs == -1) {
										return false;
									}
									top.toastr.success("修改密码成功，新密码将在下次登录生效！");
									FormUtil.resetForm("pwdForm");
									layer.close(index); // 再执行关闭
								}
							});
				}
			});

});
var index = null;
// 打开个人设置页面
function openPersonSettingPage(userId) {
	// if (Number(userId)) {
	$.ajax({
		url : ctx.path + '/platform/pm/getUser' + ctx.noAuthSuffix,
		async : true,
		dataType : 'json',
		type : 'POST',
		data : {
			userId : userId
		},
		success : function(result) {
			if (result.rs == -1) {
				top.toastr.error("操作失败");
				return false;
			}
			$("#personInfoEditForm").fill(result.data);// 表单数据填充
			$("#personInfoEditForm").find("#userId").val(userId);
			index = layer.open({
				title : '个人设置',
				type : 1,
				area : [ '420px', '300px' ], // 宽高
				btn : [ '确定', '取消' ],
				yes : function(index, layero) {
					$("#personInfoEditForm").submit();
				},
				content : $('#personInfoEdit'),
				cancel : function(index) {
					FormUtil.resetForm("personInfoEditForm");
				}
			});
		},
		error : function(jqXHR, textStatus, errorThrown) {
			top.toastr.error("操作失败");
		}
	});
}

function openChangePwdPage(userId) {
	$("#pwdForm").find("#userId").val(userId);
	index = layer.open({
		title : '修改密码',
		type : 1,
		move : false,
		area : [ '420px', '260px' ], // 宽高
		btn : [ '确定', '取消' ],
		yes : function(index, layero) {
			$("#pwdForm").submit();
		},
		content : $('#changePwd'),
		cancel : function(index) {
			FormUtil.resetForm("pwdForm");
		},
		end : function() {
			pwdFormValidate.resetForm();
		}
	});
}
