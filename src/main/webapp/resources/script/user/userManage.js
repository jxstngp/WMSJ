var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var userAddValidate, userEditValidate;
$(function() {
	// 校验新增表单
	userAddValidate = $("#userForm").validate(
		{
			rules : {
				loginName : {
					required : true
				},
				userName : {
					required : true
				},
				pwd : {
					required : true
				},
				"mobilePhone" : {
					isMobile : true
				},
				"email" : {
					email : true
				}
			},
			messages : {
				loginName : {
					required : "请输入您的登录名！"
				},
				userName : {
					required : "请输入您的用户名！"
				},
				pwd : {
					required : "请输入您的登录密码！"
				},
				"mobilePhone" : {
					isMobile : "请输入正确的手机号码！"
				},
				"email" : {
					email : "请输入正确的邮箱！"
				}
			},

			submitHandler : function(form) {
				$(form).ajaxSubmit(
					{
						// 表单提交成功后的回调
						success : function(responseText,
								statusText, xhr, $form) {
							if (responseText.rs == -1) {
								return false;
							}
							top.toastr.success("新增用户【"
									+ $("#userNameAdd").val()
									+ "】成功！");
							FormUtil.resetForm("userForm");
							doSearch();
							layer.close(index); // 再执行关闭
						},
						error : function(responseText,
								statusText, xhr, $form) {
							top.toastr.error("新增用户【"
									+ $("#userNameAdd").val()
									+ "】失败！");
							FormUtil.resetForm("userForm");
							doSearch();
							layer.close(index); // 再执行关闭
						}
					}
				);
			}
		}
	);

	// 编辑用户
	userEditValidate = $("#userEditForm").validate(
		{
			rules : {
				"loginName" : {
					required : true
				},
				"userName" : {
					required : true
				},
				"mobilePhone" : {
					isMobile : true
				},
				"email" : {
					email : true
				}
			},
			messages : {
				"loginName" : {
					required : "请输入您的登录名！"
				},
				"userName" : {
					required : "请输入您的用户名！"
				},
				"mobilePhone" : {
					isMobile : "请输入正确的手机号码！"
				},
				"email" : {
					email : "请输入正确的邮箱！"
				}
			},
			submitHandler : function(form) {
				$(form).ajaxSubmit(
					{
						// 表单提交成功后的回调
						success : function(responseText, statusText,
								xhr, $form) {
							if (responseText.rs == -1) {
								top.toastr.error("编辑用户【"
										+ $("#loginNameEdit").val()
										+ "】失败！");
								return false;
							}
							top.toastr.success("编辑用户【"
									+ $("#loginNameEdit").val()
									+ "】成功！");
							FormUtil.resetForm("userEditForm");
							doSearch();
							layer.close(index); // 再执行关闭
						}
					}
				);
			}
		}
	);
});

// 执行查询
function doSearch() {
	$("#pageForm").submit();
	top.progressbar(frameId);
}

// 新增用户
function openAddPage() {
	// 获取所有部门
	$.post(ctx.path + '/platform/depart/getDepartList' + ctx.noAuthSuffix , function(data, status) {
		var html = '<option value="0">请选择部门</option>';
		if (data == "" || data == null) {
			$("#departId").html(html);
		} else {
			for (var i = 0; i < data.length; i++) {
				html = html + '<option value="' + data[i].departId + '">' + data[i].departName + '</option>';
			}
			$("#departId").html(html);
		}
	}, "json");
	// 页面层
	index = layer.open({
		type : 1,
		title : '新增用户',
		move : false,
		area : [ '420px', '340px' ], // 宽高
		content : $('#userAdd'),
		btn : [ '保存', '关闭' ],
		yes : function(index, layero) {
			$("#userForm").submit();
		},
		cancel : function(index) {
			FormUtil.resetForm("userForm");
			userAddValidate.resetForm();
		}
	});
}

// 打开编辑页面
function editPage(id) {
	if (Number(id)) {
		$.ajax({
			url : ctx.path + '/platform/pm/getUser' + ctx.noAuthSuffix,
			async : true,
			dataType : 'json',
			type : 'POST',
			data : {
				userId : id
			},
			success : function(result) {
				if (result.rs == -1) {
					top.toastr.error("操作失败");
					return false;
				}
				// 获取所有部门
				$.post(
					ctx.path+ '/platform/depart/getDepartList' + ctx.noAuthSuffix,
					function(data, status) {
						var html = '<option value="0">请选择部门</option>';
						if (data == "" || data == null) {
							$("#departIdEdit").html(html);
						} else {
							for (var i = 0; i < data.length; i++) {
								if (result.data.departId == data[i].departId) {
									html = html
											+ '<option value="'
											+ data[i].departId
											+ '" selected>'
											+ data[i].departName
											+ '</option>';
								} else {
									html = html
											+ '<option value="'
											+ data[i].departId
											+ '">'
											+ data[i].departName
											+ '</option>';
								}
							}
						}
						$("#departIdEdit").html(html);
					}, "json"
				);
				$("#userEditForm").fill(result.data);// 表单数据填充
				$("#userEditForm").find("#userId").val(id);
				parentIndex = layer.open({
					title : '编辑用户',
					type : 1,
					area : [ '420px', '300px' ], // 宽高
					btn : [ '保存', '关闭' ],
					yes : function(index, layero) {
						$("#userEditForm").submit();
					},
					content : $('#userEdit'),
					cancel : function(index) {
						FormUtil.resetForm("userEditForm");
						userEditValidate.resetForm();
					}
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				top.toastr.error("操作失败");
			}
		});
	} else {
		top.toastr.error("对不起，管理员账号不能被编辑！");
	}
}

// 删除
function del(id, name) {
	if (Number(id)) {
		var msg = "确定删除用户【" + name + "】吗？";
		layer.confirm(msg, {
			icon : 3,
			title : "提示信息"
		}, function(index) {
			$.get(ctx.path + "/platform/pm/delUsers"+ ctx.bizSuffix + "?userIds=" + id,
				function(result) {
					if (result.rs == 1) {
						top.toastr.success("删除用户【" + name + "】成功！");
						doSearch();
						;// 刷新表单
					} else {
						top.toastr.error("删除用户【" + name + "】失败！");
					}
				}, "json"
			);
			layer.close(index);
		});
	} else {
		top.toastr.error("对不起，管理员账号不能被删除！");
	}
}

// 重置密码
function resetUserPwd(userId, userName) {
	if (Number(userId)) {
		var msg = "确定重置用户【" + userName + "】密码吗？";
		layer.confirm(msg, {
			icon : 3,
			title : "提示信息"
		}, function(index) {
			$.get(ctx.path + "/platform/pm/resetUserPwd"+ ctx.bizSuffix + "?userId=" + userId,
				function(result) {
					if (result.rs == 1) {
						layer.alert("重置用户【" + userName + "】密码成功！<br/>新密码：【"
								+ result.data + "】", {
							icon : 1
						});
					} else {
						top.toastr.error("重置用户【" + userName + "】密码失败！");
					}
				}, "json"
			);
			layer.close(index);
		});
	} else {
		top.toastr.error("对不起，管理员账号密码不允许被重置！");
	}
}

// 启用/禁用账户
function disAndEnableAccount(userId, userName, status) {
	var flag = status == 1 ? '启用' : '禁用';
	if (Number(userId)) {
		var msg = "确定" + flag + "用户【" + userName + "】账号吗？";
		layer.confirm(msg, {
			icon : 3,
			title : "提示信息"
		}, function(index) {
			$.get(ctx.path + "/platform/pm/disAndEnableAccount"+ ctx.bizSuffix + "?userId=" + userId + "&status=" + status,
				function(result) {
					if (result.rs == 1) {
						top.toastr.success(flag + "用户【" + userName + "】账号成功！");
						doSearch();// 刷新表单
					} else {
						top.toastr.error(flag + "用户【" + userName + "】账号失败！");
					}
				}, "json"
			);
			layer.close(index);
		});
	} else {
		top.toastr.error("对不起，管理员账号不能被" + flag + "！");
	}
}

var rolesMap = {};
// 设置角色
function setRole(userId, username) {

	$.post(
		ctx.path + '/platform/pm/getUserRoles'+ctx.noAuthSuffix,
		{
			userId : userId
		},
		function(data, status) {
			$("#ownerRole").empty();
			$("#roleList").empty();
			if (data.rs == 1) {
				for (var i = 0, len = data.roles.length; i < len; i++) {
					rolesMap[data.roles[i].roleId] = data.roles[i];
					data.roles[i].isSet = false;
					for (var j = 0, userRolesLen = data.userRoles.length; j < userRolesLen; j++) {
						if (data.roles[i].roleId == data.userRoles[j]) {
							data.roles[i].isSet = true;
							break;
						}
					}
					if (data.roles[i].isSet) {
						createRoleOption('ownerRole', data.roles[i]);
					} else {
						createRoleOption('roleList', data.roles[i]);
					}
				}
				parentIndex = layer.open({
					title : '设置【' + username + '】角色',
					type : 1,
					area : [ '600', '400' ],
					move : false,
					btn : [ '保存', '关闭' ],
					yes : function(index, layero) {
						saveGroupRole(userId, username);// 保存角色
					},
					content : $("#roleSet")
				});
			}
		}, "json"
	);
}

// 保存角色
function saveGroupRole(userId, username) {
	var setRoleIds = [];
	$("#ownerRole > option").each(function() {
		setRoleIds.push($(this).val());
	});
	$.ajax({
		type : "POST",
		url : ctx.path + '/platform/pm/setUserRoles' + ctx.bizSuffix,
		traditional : true,
		data : {
			userId : userId,
			roleIds : setRoleIds
		},
		dataType : "json",
		success : function(data) {
			if (data.rs == 1) {
				top.toastr.success("设置用户【" + username + "】角色成功");
				layer.close(parentIndex); // 再执行关闭
			} else {
				top.toastr.error("设置用户【" + username + "】角色失败");
			}
			doSearch(); // 刷新表单
		}
	});
}
// 添加角色
function addRole() {
	$("#roleList > option:selected").each(
		function() {
			var ownerRole = $("#ownerRole:not(:has(option[value=" + $(this).val() + "]))");
			if (ownerRole.length == 0) {
				top.toastr.error("访问该角色已添加，请重新选择！");
			} else {
				$(this).clone().appendTo(ownerRole);
				$(this).remove();
			}
		}
	);
}

// 移除角色
function removeRole() {
	var roleList = $("#roleList");
	$("#ownerRole > option:selected").clone().appendTo(roleList);
	$("#ownerRole > option:selected").remove();
}

// 创建角色选项
var createRoleOption = function(selectId, roleObj) {
	option = $("#" + selectId);
	option.append(" <option value = " + roleObj.roleId + ">" + roleObj.roleName + "</option>");
	//return "<li id='roleLi_"+roleObj.roleId+"'  onclick='selectRole("+roleObj.roleId+")' ondblclick='toggleRole("+roleObj.roleId+")'><a>"+roleObj.roleName+"</a></li>";
};