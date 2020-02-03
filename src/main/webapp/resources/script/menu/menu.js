//单独出发滚动条
function progress() {
	var index = top.layer.load(0, {
		shade : [ 0.3, '#000' ]
	});
	$("#menuList").load(function() {
		top.layer.close(index);
	});
}

var setting = {
	view : {
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom,
		selectedMulti : false
	},
	async : {
		enable : true,
		url : ctx.path + "/platform/pm/getMenusByMenuTypes"+ ctx.noAuthSuffix ,
		otherParam : {
			"menuTypes" : "0,1",
			"states" : "1,2"
		},
		dataFilter : filter
	},
	data : {
		key : {
			name : "menuName",
			url : "none"
		},
		simpleData : {
			enable : true,
			idKey : "menuId",
			pIdKey : "parentId",
			rootPId : ""
		}
	},
	callback : {
		onAsyncSuccess : onAsyncSuccess,
		onNodeCreated : zTreeOnNodeCreated,
		onClick : zTreeOnClick
	}
};

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) {
		return null;
	}
	return childNodes.data;
}

function onAsyncSuccess(event, treeId, treeNode, msg) {
	zTree = $.fn.zTree.getZTreeObj(treeId);
	zTree.expandAll(true);
}

function zTreeOnNodeCreated(event, treeId, treeNode) {
	if (treeNode.state == 2) {
		treeNode.icon = ctx.path + "/resources/components/zTree/css/zTreeStyle/img/custom/disable.png";
	}
	if (treeNode.menuType == 0) {
		// treeNode.icon=ctx.path+"/resources/components/zTree/css/zTreeStyle/img/diy/1_open.png";
		treeNode.isParent = true;
	}
	// else{
	// treeNode.icon=ctx.path+"/resources/components/zTree/css/zTreeStyle/img/diy/3.png";
	// }
	$.fn.zTree.getZTreeObj(treeId).updateNode(treeNode);
}

function zTreeOnClick(event, treeId, treeNode) {
	if (treeNode.menuType == 1) {
		$("#menuList").attr('src', ctx.path + "/platform/pm/querFuncMenuList"+  ctx.noAuthSuffix +"?parentId=" + treeNode.menuId);
	} else {
		$("#menuList").attr('src', '');
	}
}

var toggleMenuType = function(menuFormId, isFuncMenu) {
	$("#" + menuFormId + " select[name='menuType']").empty().removeAttr("onchange");
	if (isFuncMenu) {
		$("#" + menuFormId + " select[name='menuType']").append('<option value="2">菜单功能</option>');
		$("#" + menuFormId + " select[name='menuType']").append('<option value="3">菜单页面</option>');
	} else {
		$("#" + menuFormId + " select[name='menuType']").attr("onchange","menuTypeChange(this.options[this.options.selectedIndex].value,'urlDiv')").append('<option value="0">菜单分组</option><option value="1">菜单链接</option>');
	}
};

var menuTypeChange = function(menuType, menuTypeDivId) {
	if (menuType == 1 || menuType == 2) {
		$("#" + menuTypeDivId).show();
	} else {
		$("#" + menuTypeDivId).hide();
	}
};

var newCount = 1;
var currentNode = null;
var showAddMenuDiv = function(isFuncMenu) {
	var title = "菜单";
	$("#urlDiv").hide();
	toggleMenuType("menuForm", isFuncMenu);
	if (isFuncMenu) {
		title = "功能";
		$("#urlDiv").show();
		var nodes = zTree.getSelectedNodes();
		$("#menuForm").fill({
			parentId : nodes[0].menuId,
			menuCode : nodes[0].menuCode + "_"
		});
	} else {
		var defaultMenuCode = "";
		if (currentNode.menuCode != "") {
			defaultMenuCode = currentNode.menuCode + "_";
		}
		$("#menuForm").fill({
			parentId : currentNode.menuId,
			menuCode : defaultMenuCode
		});
	}
	parentIndex = layer.open({
		title : '新增' + title,
		type : 1,
		area : [ '500px', '400px' ],
		move : false,
		btn : [ '保存', '关闭' ],
		yes : function(index, layero) {
			$("#menuForm").submit();
		},
		content : $('#menuDiv'),
		cancel : function(index) {
			FormUtil.resetForm("menuForm");
			menuValidator.resetForm();
		}
	});
};

var showUpdateMenuDiv = function(isFuncMenu, mid) {
	var title = "菜单";
	toggleMenuType("menuUpdateForm", isFuncMenu);
	var menuType = null;
	if (isFuncMenu) {
		title = "功能";
		$.ajax({
			url : ctx.path + '/platform/pm/getMenuById' + ctx.noAuthSuffix,
			async : true,
			dataType : 'json',
			type : 'POST',
			data : {
				menuId : mid
			},
			success : function(data) {
				if (data.rs == -1) {
					top.toastr.error("修改数据异常！");
					return false;
				}
				$("#urlUpdateDiv").show();
				$("#menuUpdateForm").fill(data.data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				top.toastr.error("操作失败！");
			}
		});

	} else {
		menuType = currentNode.menuType;
		$("#menuUpdateForm").fill(currentNode);
	}
	menuTypeChange(menuType, 'urlUpdateDiv');
	parentIndex = layer.open({
		title : '编辑' + title,
		type : 1,
		area : [ '500px', '400px' ],
		move : false,
		btn : [ '保存', '关闭' ],
		yes : function(index, layero) {
			$("#menuUpdateForm").submit();
		},
		content : $('#menuUpdateDiv'),
		cancel : function(index) {
			FormUtil.resetForm("menuUpdateForm");
			menuUpdateValidator.resetForm();
		}
	});
};

function addHoverDom(treeId, treeNode) {
	currentNode = treeNode;
	var sObj = $("#" + treeNode.tId + "_span");
	if ($("#removeBtn_" + treeNode.tId).length > 0 || $("#addBtn_" + treeNode.tId).length > 0) {
		return;
	}
	var enableStr = "<span class='button enable' id='enableBtn_" + treeNode.tId + "' title='恢复' onfocus='this.blur();' onclick='disableMenu(0)' ></span>";
	var disableStr = "<span class='button disable' id='disableBtn_" + treeNode.tId + "' title='禁用' onfocus='this.blur();' onclick='disableMenu(1)' ></span>";
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='新增' onfocus='this.blur();' onclick='showAddMenuDiv(false)' ></span>";
	var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId + "' title='编辑' onfocus='this.blur();' onclick='showUpdateMenuDiv(false)' ></span>";
	var delStr = "<span class='button remove' id='removeBtn_" + treeNode.tId + "' title='删除' onfocus='this.blur();' onclick='delMenu()'></span>";
	var upStr = "<span class='button up' id='upBtn_" + treeNode.tId + "' title='上移' onfocus='this.blur();' onclick='changeMenusSortby(1)'></span>";
	var downStr = "<span class='button down' id='downBtn_" + treeNode.tId + "' title='下移' onfocus='this.blur();' onclick='changeMenusSortby(0)'></span>";

	if (treeNode.menuId == 0) {
		sObj.after(addStr);
		return;
	}
	if (!treeNode.isFirstNode) {
		sObj.after(upStr);
	}
	if (!treeNode.isLastNode) {
		sObj.after(downStr);
	}
	sObj.after(delStr);
	sObj.after(editStr);
	if (treeNode.menuType == 0) {
		sObj.after(addStr);
	}
	if (treeNode.state == 2) {
		sObj.after(enableStr);
	} else {
		sObj.after(disableStr);
	}
};
function removeHoverDom(treeId, treeNode) {
	$("#enableBtn_" + treeNode.tId).unbind().remove();
	$("#disableBtn_" + treeNode.tId).unbind().remove();
	if (treeNode.menuType == 0) {
		$("#addBtn_" + treeNode.tId).unbind().remove();
	}
	$("#editBtn_" + treeNode.tId).unbind().remove();
	$("#removeBtn_" + treeNode.tId).unbind().remove();
	$("#upBtn_" + treeNode.tId).unbind().remove();
	$("#downBtn_" + treeNode.tId).unbind().remove();
};

function delMenu() {
	var delContext = "您确定删除菜单【" + currentNode.menuName + "】吗？<br>";
	delContext += "删除成功之后，该操作将无法恢复。";
	layer.confirm(delContext, {
		icon : 3,
		title : "提示"
	}, function(index) {
		$.post(ctx.path + '/platform/pm/delMenus' + ctx.bizSuffix, {
			menuIds : currentNode.menuId
		}, function(data, status) {
			if (data.rs == 1) {
				zTree.removeNode(currentNode);
				top.toastr.success("删除菜单【" + currentNode.menuName + "】成功！");
			}
		}, "json");
		layer.close(index);
	});
}

function disableMenu(isDisable) {
	var context = "您确定";
	var stateContext = null;
	if (isDisable == 1) {
		stateContext = "禁用";
	} else {
		stateContext = "恢复";
	}
	context += stateContext;
	context += "菜单【" + currentNode.menuName + "】吗？";
	layer.confirm(
		context,
		{
			icon : 3,
			title : "提示"
		},
		function(index) {
			$.post(ctx.path + '/platform/pm/disableMenus' + ctx.bizSuffix,
				{
					menuIds : currentNode.menuId,
					isDisable : isDisable
				},
				function(data, status) {
					if (data.rs == 1) {
						var enableStr = "<span class='button enable' id='enableBtn_" + currentNode.tId + "' title='恢复' onfocus='this.blur();' onclick='disableMenu(0)' ></span>";
						var disableStr = "<span class='button disable' id='disableBtn_" + currentNode.tId + "' title='禁用' onfocus='this.blur();' onclick='disableMenu(1)' ></span>";
						if (isDisable == 1) {
							$("#enableBtn_" + currentNode.tId).unbind().remove();
							$("#disableBtn_" + currentNode.tId).unbind().remove();
							$("#" + currentNode.tId + "_span").after(enableStr);
							currentNode.state = 2;
							currentNode.icon = ctx.path + "/resources/components/zTree/css/zTreeStyle/img/custom/disable.png";
						} else {
							$("#enableBtn_" + currentNode.tId).unbind().remove();
							$("#disableBtn_" + currentNode.tId).unbind().remove();
							$("#" + currentNode.tId + "_span").after(disableStr);
							currentNode.state = 1;
							currentNode.icon = null;
						}
						zTree.updateNode(currentNode);
						top.toastr.success(stateContext + "菜单成功！");
					}
				}, "json");
			layer.close(index);
		}
	);
}

function disableFuncMenu(isDisable, id, name) {

	var context = "您确定";
	var stateContext = null;
	if (isDisable == 1) {
		stateContext = "禁用";
	} else {
		stateContext = "恢复";
	}
	context += stateContext;
	context += "功能【" + name + "】吗？";

	layer.confirm(context, {
		icon : 3,
		title : "提示"
	}, function(index) {
		$.post(ctx.path + '/platform/pm/disableMenus' + ctx.bizSuffix, {
			menuIds : id,
			isDisable : isDisable
		}, function(data, status) {
			if (data.rs == 1) {
				top.toastr.success(stateContext + "成功！");
				$("#menuList")[0].contentWindow.doSearch();
			}
		}, "json");
		layer.close(index);
	});
}

function changeMenusSortby(isUp) {
	// $.post(ctx.path+'/platform/pm/changeMenusSortby.do',
	// {menuIds:currentNode.menuId,isUp:isUp}, function (data, status){
	// if(data.rs==1){
	// var msg=null;
	// if(isUp){
	// zTree.moveNode(currentNode.getPreNode(),currentNode,"prev");
	// msg="上移成功";
	// }else{
	// zTree.moveNode(currentNode.getNextNode(),currentNode,"next");
	// msg="下移成功";
	// }
	// top.toastr.success(msg);
	// }else if(data.rs==0){
	// top.toastr.warning("移动失败！");
	// }
	// },"json");
	$.ajax({
		type : "post",
		url : ctx.path + '/platform/pm/changeMenusSortby'+ ctx.bizSuffix,
		data : {
			menuIds : currentNode.menuId,
			isUp : isUp
		},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.rs == 1) {
				var msg = null;
				if (isUp) {
					zTree.moveNode(currentNode.getPreNode(), currentNode, "prev");
					msg = "上移成功";
				} else {
					zTree.moveNode(currentNode.getNextNode(), currentNode, "next");
					msg = "下移成功";
				}
				top.toastr.success(msg);
			} else if (data.rs == 0) {
				top.toastr.warning("移动失败！");
			}
		}
	});
}

function changeFuncMenusSortby(isUp, id, name) {
	$.post(ctx.path + '/platform/pm/changeMenusSortby' + ctx.bizSuffix , {
		menuIds : id,
		isUp : isUp
	}, function(data, status) {
		if (data.rs == 1) {
			var msg = null;
			if (isUp) {
				msg = "上移【" + name + "】成功！";
			} else {
				msg = "下移【" + name + "】成功！";
			}
			top.toastr.success(msg);
			$("#menuList")[0].contentWindow.doSearch();
		} else if (data.rs == 0) {
			top.toastr.error("移动【" + name + "】失败！");
		}
	}, "json");
}

function delFuncMenus(id, menuname) {

	var delContext = "您确定删除";
	delContext += "菜单【" + menuname + "】吗？<br>";
	delContext += "删除成功之后，该操作将无法恢复。";

	layer.confirm(delContext, {
		icon : 3,
		title : "提示"
	}, function(index) {
		$.post(ctx.path + '/platform/pm/delMenus' + ctx.bizSuffix, {
			menuIds : id
		}, function(data, status) {
			if (data.rs == 1) {
				top.toastr.success("删除菜单【" + menuname + "】成功！");
				$("#menuList")[0].contentWindow.doSearch();
			} else {
				top.toastr.warning("删除菜单【" + menuname + "】失败！");
			}
		}, "json");
		layer.close(index);
	});
}

var zTree = null;
var validator;
var parentIndex = null;
var menuValidator = null;
var menuUpdateValidator = null;
$(function() {
	$.fn.zTree.init($("#treeDemo"), setting, []);

	$("#tree").height($(window).height() - 155);

	$(window).bind('resize', function() {
		$("#tree").height($(window).height() - 155);
	});

	menuValidator = $("#menuForm").validate({
		rules : {
			"menuCode" : {
				remote : ctx.path + "/platform/pm/checkMenuForm" + ctx.noAuthSuffix,
				required : true
			},
			"menuName" : {
				required : true
			},
			"menuUrl" : {
				required : true
			}
		},
		messages : {
			"menuCode" : {
				remote : "菜单编码重复！",
				required : "请输入菜单编码！"
			},
			"menuName" : {
				required : "请输入菜单名称！"
			},
			"menuUrl" : {
				required : "请输入资源路径！"
			}
		},
		submitHandler : function(form) {
			$(form).ajaxSubmit({
				// 表单提交成功后的回调
				success : function(responseText, statusText, xhr, $form) {
					var menuName = $("#addMenuName").val();
					if (responseText.rs == 1) {
						var menuObj = $("#menuForm").serializeJson();
						if (menuObj.menuType == 2) {
							$("#menuList")[0].contentWindow.doSearch();
							top.toastr.success("新增功能【" + menuName + "】成功！");
						} else {
							menuObj.menuId = responseText.data;
							var nodes = zTree.getSelectedNodes();
							var parentNode = nodes[0];
							zTree.addNodes(parentNode, menuObj);
							top.toastr.success("新增菜单【" + menuName + "】成功！");
						}

						layer.close(parentIndex); // 再执行关闭
						FormUtil.resetForm("menuForm");
					}
				}
			});
		}
	});

	menuUpdateValidator = $("#menuUpdateForm").validate(
		{
			rules : {
				"menuCode" : {
					remote : {
						type : "POST",
						url : ctx.path + "/platform/pm/checkMenuForm" + ctx.noAuthSuffix,
						data : {
							menuId : function() {
								return $("#menuUpdateForm input[name='menuId']").val();
							}
						}
					},
					required : true
				},
				"menuName" : {
					required : true
				},
				"menuUrl" : {
					required : true
				}
			},
			messages : {
				"menuCode" : {
					remote : "菜单编码重复！",
					required : "请输入菜单编码！"
				},
				"menuName" : {
					required : "请输入菜单名称！"
				},
				"menuUrl" : {
					required : "请输入资源路径！"
				}
			},
			submitHandler : function(form) {
				$(form).ajaxSubmit(
					{
						// 表单提交成功后的回调
						success : function(responseText, statusText, xhr, $form) {
							var menuName = $("#updateMenuName").val();
							if (responseText.rs == 1) {
								var menuObj = $("#menuUpdateForm").serializeJson();
								if (menuObj.menuType == 2) {
									$("#menuList")[0].contentWindow.doSearch();
									top.toastr.success("编辑功能【" + menuName + "】成功！");
								} else {
									var nodes = zTree.getSelectedNodes();
									var currentNode = nodes[0];
									currentNode.menuType = menuObj.menuType;
									currentNode.menuCode = menuObj.menuCode;
									currentNode.menuName = menuObj.menuName;
									currentNode.descriptions = menuObj.descriptions;
									zTree.updateNode(currentNode);
									top.toastr.success("编辑菜单【" + menuName + "】成功！");
								}

								layer.close(parentIndex); //再执行关闭
								FormUtil.resetForm("menuUpdateForm");
							}
						}
					}
				);
			}
		}
	);
});
