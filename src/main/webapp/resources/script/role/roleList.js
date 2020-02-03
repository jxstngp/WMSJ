var index = null;
var roleAddValidate, roleEditValidate;
//初始化信息
$(function() {
	//校验新增表单
	roleAddValidate = $("#roleForm").validate({
		rules : {
			roleName : {
				required : true
			}
		},
		messages : {
			roleName : {
				required : "请输入角色名！"
			}
		},
		submitHandler : function(form) {
			$(form).ajaxSubmit(
				{
					//表单提交成功后的回调
					success : function(responseText, statusText,
							xhr, $form) {
						if (responseText.rs == -1) {
							return false;
						}
						top.toastr.success("新增角色【"
								+ $("#roleNameInput").val()
								+ "】成功！");
						FormUtil.resetForm("roleForm");
						doSearch();
						layer.close(index); //再执行关闭 
					}
				}
			);
		}
	});

	//校验编辑表单
	roleEditValidate = $("#editRoleForm").validate(
		{
			rules : {
				roleName : {
					required : true
				}
			},
			messages : {
				roleName : {
					required : "请输入角色名！"
				}
			},
			submitHandler : function(form) {
				$(form).ajaxSubmit(
					{
						//表单提交成功后的回调
						success : function(responseText, statusText, xhr, $form) {
							if (responseText.rs == -1) {
								return false;
							}
							top.toastr.success("编辑角色【" + $("#roleNameEdit").val() + "】成功！");
							FormUtil.resetForm("editRoleForm");
							doSearch();
							layer.close(index); //再执行关闭 
						}
					}
				);
			}
		}
	);
});

//执行查询
function doSearch() {
	$("#pageForm").submit();
}

//新增角色
function openAddPage() {
	//页面层
	index = layer.open({
		type : 1,
		title : '新增角色信息',
		area : [ '420px', '260px' ], //宽高
		content : $('#add_box'),
		btn : [ '保存', '关闭' ],
		yes : function() {
			$("#roleForm").submit();
		},
		btn2 : function() {
		},
		end : function() {
			$("#roleNameInput").val("");
			$("#descriptionsInput").val("");
			roleAddValidate.resetForm();
		}
	});
}

//编辑角色
function openEditPage(id, name, desc) {
	//页面层
	index = layer.open({
		type : 1,
		title : '编辑角色信息',
		area : [ '420px', '260px' ], //宽高
		content : $('#edit_box'),
		btn : [ '保存', '关闭' ],
		success : function() {
			$("#roleId").val(id);
			$("#roleNameEdit").val(name);
			$("#descriptionsEdit").val(desc);
		},
		yes : function() {
			$("#editRoleForm").submit();
		},
		btn2 : function() {
		},
		end : function() {
			$("#roleId").val("");
			$("#roleNameEdit").val("");
			$("#descriptionsEdit").val("");
			roleEditValidate.resetForm();
		}
	});
}

//删除角色
function delRole(roleId, name) {
	var msg = "确定删除角色【" + name + "】吗？";
	$.post(ctx.path + '/platform/pm/getRoleById'+ctx.noAuthSuffix, {
		roleId : roleId
	}, function(data, status) {
		if (data.data.sortby == 0) {
			layer.confirm(msg, {
				icon : 3,
				title : '提示信息'
			}, function(index) {
				$.post(ctx.path + '/platform/pm/delRole' + ctx.bizSuffix, {
					roleId : roleId,
					roleName : name
				}, function(data, status) {
					if (data.rs > 0) {
						layer.close(index);
						top.toastr.success("删除角色【" + name + "】成功！");
						doSearch();
					}
				}, "json");
			});
		} else {
			top.toastr.error("对不起，角色【" + name + "】被使用中，不能删除！");
		}
	}, "json");
}
var iframeWin;
//权限设置
function openAuthPage(roleId, name) {
	//页面层
	index = layer.open({
		type : 2,
		title : '权限设置【角色名称：' + name + '】',
		area : [ '680px', '500px' ], //宽高
		content : ctx.path + '/platform/pm/getMenusByRoleId'+ ctx.noAuthSuffix +'?roleId=' + roleId,
		btn : [ '保存', '关闭' ],
		success : function(layero, index) {
			iframeWin = window[layero.find('iframe')[0]['name']];
		},
		yes : function() {
			$.post(ctx.path + '/platform/pm/setRoleMenus' + ctx.bizSuffix , {
				roleId : roleId,
				menuIds : iframeWin.roleMenuSave(),
				roleName : name
			}, function(data, status) {
				if (data.rs > 0) {
					layer.close(index);
					top.toastr.success("角色【" + name + "】权限设置成功！");
				}
			}, "json");
		},
		btn2 : function() {
		},
		end : function() {
		}
	});
}