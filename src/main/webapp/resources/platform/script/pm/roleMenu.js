var zTree = null;
var roleListMap = {};
var parentIndex = null;
$(document).ready(function() {
	//校验表单
	$("#roleForm").validate({
		submitHandler : function(form) {
			var roleId = $('#roleForm input[name="roleId"]').val();
			var roleObj = getRoleObj();
			roleObj.roleId = roleId;
			$(form).ajaxSubmit({
				//表单提交成功后的回调
				success : function(responseText, statusText, xhr, $form) {
					if (responseText.rs == -1) {
						return false;
					}

					var tempId = $('#roleForm input[name="roleId"]').val();
					if (!tempId) {//新增
						roleObj.roleId = responseText.data;
						addRoleDiv(roleObj);
						roleListMap[roleObj.roleId] = roleObj;
						top.toastr.success("新增角色成功");
					} else {//编辑
						roleListMap[tempId] = roleObj;
						$("#roleObjName_" + tempId).html(roleObj.roleName);
						top.toastr.success("编辑角色成功");
					}
					FormUtil.resetForm("roleForm");
					layer.close(parentIndex); //再执行关闭 
				}
			});
		}
	});
});
function loadRoleMenu() {
	$.post(ctx.path + '/platform/pm/getRolesAndMenus.do', {}, function(data, status) {
		var setting = {
			check : {
				enable : true
			},
			data : {
				key : {
					name : "menuName"
				},
				simpleData : {
					enable : true,
					idKey : "menuId",
					pIdKey : "parentId",
					rootPId : ""
				}
			}
		};
		$.fn.zTree.init($("#treeDemo"), setting, data.menus);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandAll(true);
		if (data.rs == 1) {
			var roles = data.roles;
			roles[0].menuIds = data.defaultMenus;
			for (var i = 0, len = roles.length; i < len; i++) {
				addRoleDiv(roles[i]);
				roleListMap[roles[i].roleId] = roles[i];
			}
			initRoleMenu(roles[0].roleId);
		}
	}, "json");
}

var initRoleId = null;
var initRoleMenu = function(roleId) {
	$(".iradio_btn").removeClass("iCheck_btn");
	$("#check_" + roleId).addClass("iCheck_btn");
	initRoleId = roleId;
	var roleObj = roleListMap[roleId];
	if (roleId) {
		$.ajax({
			type : "POST",
			url : ctx.path + '/platform/pm/getMenusByRoleId'+ ctx.pageSuffix,
			async : false,
			data : {
				roleId : roleId
			},
			dataType : "json",
			success : function(data) {
				if (data.rs == 1) {
					roleObj.menuIds = data.data;
				} else {
					roleObj.menuIds = [];
				}
			}
		});
	}

	zTree.checkAllNodes(false);
	for (var i = 0, len = roleObj.menuIds.length; i < len; i++) {
		var chkNode = zTree.getNodeByParam("menuId", roleObj.menuIds[i], null);
		zTree.checkNode(chkNode, true, false);
	}
};

var setRoleMenus = function() {
	var setRoleMenuObj = {};
	setRoleMenuObj.roleId = initRoleId;
	var chkNodes = zTree.getCheckedNodes(true);
	var chkMenuIds = [];
	for (var i = 0, len = chkNodes.length; i < len; i++) {
		chkMenuIds.push(chkNodes[i].menuId);
	}
	setRoleMenuObj.menuIds = chkMenuIds;
	$.ajax({
		type : "POST",
		url : ctx.path + '/platform/pm/setRoleMenus.do',
		traditional : true,
		data : setRoleMenuObj,
		dataType : "json",
		success : function(data) {
			if (data.rs == 1) {
				roleListMap[initRoleId].menuIds = chkMenuIds;
				top.toastr.success("设置成功");
			} else {
				top.toastr.error("设置失败");
			}
		}
	});
};

var initRoleForm = function(roleObj) {
	$('#roleForm input[name="roleId"]').val(FormUtil.getDefaultStrValue(roleObj.roleId));
	$('#roleForm input[name="roleName"]').val(FormUtil.getDefaultStrValue(roleObj.roleName));
	$('#roleForm textarea[name="descriptions"]').val(FormUtil.getDefaultStrValue(roleObj.descriptions));
};

function showAddRole() {
	initRoleForm({});
	parentIndex = layer.open({
		title : '新增角色',
		type : 1,
		area : [ '45%', '50%' ],
		fix : true,
		maxmin : true,
		btn : [ '提交', '取消' ],
		yes : function(index, layero) {
			$("#roleForm").submit();
		},
		content : $('#roleDiv'),
		cancel : function(index) {
			FormUtil.resetForm("roleForm");
		}
	});
	$("#roleForm").attr("action", ctx.path + "/platform/pm/addRole.do");
}

function showUpdateRole(roleId) {
	var roleObj = roleListMap[roleId];
	initRoleForm(roleObj);
	parentIndex = layer.open({
		title : '编辑角色',
		type : 1,
		area : [ '45%', '50%' ],
		fix : true,
		maxmin : true,
		btn : [ '提交', '取消' ],
		yes : function(index, layero) {
			$("#roleForm").submit();
		},
		content : $('#roleDiv'),
		cancel : function(index) {
			FormUtil.resetForm("roleForm");
		}
	});
	$("#roleForm").attr("action", ctx.path + "/platform/pm/updateRole.do");
}

var getRoleObj = function() {
	var roleObj = {};
	roleObj.roleName = $('#roleForm input[name="roleName"]').val();
	roleObj.descriptions = $('#roleForm textarea[name="descriptions"]').val();
	return roleObj;
};

var addRoleDiv = function(roleObj) {
	var roleObjStr = '<li id="roleObj_'
			+ roleObj.roleId
			+ '" onclick="initRoleMenu('
			+ roleObj.roleId
			+ ')"><span id="roleObjName_'
			+ roleObj.roleId
			+ '">'
			+ roleObj.roleName
			+ '</span><span onclick="initRoleMenu('
			+ roleObj.roleId
			+ ')" id="check_'
			+ roleObj.roleId
			+ '" class="iradio_btn"></span><span onclick="delRole('
			+ roleObj.roleId
			+ ')" class="del_btn" title="删除"></span><span onclick="showUpdateRole('
			+ roleObj.roleId + ')" class="edit_btn"  title="编辑"></span></li>';
	$("#roleListDiv").html($("#roleListDiv").html() + roleObjStr);
};

//删除角色
function delRole(roleId) {
	var roleObj = roleListMap[roleId];
	var delContext = "您确定删除";
	delContext += "角色名【" + roleObj.roleName + "】吗？<br>";
	delContext += "删除成功之后，该操作将无法恢复。";
	layer.confirm(delContext, {
		icon : 3,
		title : "提示"
	}, function(index) {
		$.post(ctx.path + '/platform/pm/delRole.do', roleObj, function(data,
				status) {
			if (data.rs == 1) {
				delete roleListMap[roleId];
				$("#roleObj_" + roleId).remove();
				top.toastr.success("删除角色成功");
			}
		}, "json");
		layer.close(index);
	});
}