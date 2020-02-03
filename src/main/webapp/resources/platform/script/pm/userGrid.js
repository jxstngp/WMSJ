var grid = {};
var rolesMap = {};
var parentIndex = null;
$(document).ready(
		function() {
			$.jgrid.defaults.styleUI = 'Bootstrap';
			grid = $("#jqGrid").jqGrid({
						url : ctx.path + '/platform/pm/queryUsers.do',
						mtype : "POST",
						datatype : "json",
						height : top.getFrameHeight() - $("#operation_box").outerHeight() - $("#search_box").outerHeight() - 75,
						colNames : [ '用户ID', '登录名', '用户名', '最后登陆时间' ],
						colModel : [{
									name : 'userId',
									index : 'userId',
									hidden : true
								}, {
									name : 'loginName',
									editable : true,
									index : 'loginName',
									width : 60
								}, {
									name : 'userName',
									editable : true,
									index : 'userName',
									width : 100
								}, {
									name : 'lastLoginTime',
									editable : false,
									index : 'lastLoginTime',
									width : 90,
									sorttype : "date",
									formatter : function(cellvalue) {
										if (typeof (cellvalue) != undefined && cellvalue) {
											return DateUtil.getSmpFormatDateByLong(cellvalue, true);
										} else {
											return "";
										}
									}
								} ],
						multiselect : true,
						autowidth : true,// 自适应宽度
						shrinkToFit : true,
						rowNum : 20,
						rowList : [ 10, 20, 50 ],
						rownumbers : true,// 添加左侧行号
						altRows : true,// 设置为交替行表格,默认为false
						pager : '#jqGridPager',
						viewrecords : true, // 是否在浏览导航栏显示记录总数
						hidegrid : false,
						jsonReader : {
							root : "data.data",// Json数据
							records : "data.totalRows",// 总记录数
							total : "data.totalPages",
							page : "data.page",
							repeatitems : false
						}
					});

			// 设置按钮
			grid.jqGrid('navGrid', '#jqGridPager', {
				edit : false,
				add : false,
				del : false,
				search : false
			}, {
				height : 200,
				reloadAfterSubmit : true
			});

			// 屏幕发生变化的时候计算表格高度
			$(window).bind( 'resize', function() {
						var width = $('.jqGrid_wrapper').width();
						$('#jqGrid').setGridWidth(width);
						var newHeight = top.getFrameHeight() - $("#operation_box").outerHeight() - $("#search_box").outerHeight() - 75;
						$("#jqGrid").jqGrid('setGridHeight', newHeight);
					});

			// 刷新表单
			function refreshGrid() {
				grid.trigger("reloadGrid");
			}

			// 校验新增表单
			$("#userForm").validate({
						submitHandler : function(form) {
							$(form).ajaxSubmit({
								// 表单提交成功后的回调
								success : function(responseText, statusText, xhr, $form) {
									if (responseText.rs == -1) {
										return false;
									}
									refreshGrid();// 重新加载grid
									top.toastr.success("新增用户成功");
									FormUtil.resetForm("userForm");
									layer.close(parentIndex); // 再执行关闭
								}
							});
						}
					});

			// 编辑用户
			$("#userEditForm").validate({
				submitHandler : function(form) {
					$(form).ajaxSubmit({
						// 表单提交成功后的回调
						success : function(responseText,
								statusText, xhr, $form) {
							if (responseText.rs == -1) {
								return false;
							}
							refreshGrid();// 重新加载grid
							top.toastr.success("编辑用户成功");
							layer.close(parentIndex); // 再执行关闭
							FormUtil.resetForm("userEditForm");
						}
					});
				}
			});

		});

// 打开新增页面
function openAddPage() {
	parentIndex = layer.open({
		title : '新增用户',
		type : 1,
		area : [ '45%', '55%' ],
		fix : true,
		maxmin : true,
		btn : [ '提交', '取消' ],
		yes : function(index, layero) {
			$("#userForm").submit();
		},
		content : $('#userAdd'),
		cancel : function(index) {
			FormUtil.resetForm("userForm");
		}
	});
}

// 打开编辑页面
function editPage() {
	var selRows = grid.jqGrid('getGridParam', 'selarrrow');
	if (selRows.length != 1) {
		top.toastr.warning("请选择一行进行编辑！");
		return;
	}
	var row = $("#jqGrid").jqGrid("getRowData", selRows[0]);
	$.ajax({
		url : ctx.path + '/platform/pm/getUser.json',
		async : true,
		dataType : 'json',
		type : 'POST',
		data : {
			userId : row.userId
		},
		success : function(data) {
			if (data.rs == -1) {
				top.toastr.error("操作失败");
				return false;
			}
			$("#userEditForm").fill(data.data);// 表单数据填充
			$("#userEditForm").find("#userId").val(row.userId);
			parentIndex = layer.open({
				title : '编辑用户',
				type : 1,
				area : [ '45%', '55%' ],
				fix : true,
				maxmin : true,
				btn : [ '提交', '取消' ],
				yes : function(index, layero) {
					$("#userEditForm").submit();
				},
				content : $('#userEdit'),
				cancel : function(index) {
					FormUtil.resetForm("userEditForm");
				}
			});
		},
		error : function(jqXHR, textStatus, errorThrown) {
			top.toastr.error("操作失败");
		}
	});
}

// 删除
function del() {
	var selRows = grid.jqGrid('getGridParam', 'selarrrow'); // 选中行id数组
	if (selRows.length <= 0) {
		top.toastr.warning("请选择数据删除");
		return false;
	}
	var ids = new Array();
	var len = selRows.length;
	var rowData = new Object();
	var delContext = "您确定删除";
	if (selRows.length == 1) { // 删除单条数据
		rowData = grid.jqGrid('getRowData', selRows[0]);
		delContext += "登录名【" + rowData.loginName + "】吗？<br>";
		delContext += "删除成功之后，该操作将无法恢复。";
	} else {
		delContext += "选中的" + selRows.length + "条数据吗？<br>";
		delContext += "删除成功之后，该操作将无法恢复。";
	}
	for (var i = 0; i < len; i++) {
		var tempId = selRows[i];
		var row = grid.jqGrid("getRowData", tempId);
		var id = row.userId;// 获取选择行数据id
		ids.push(id);
	}
	layer.confirm(delContext, {
		icon : 3,
		title : "提示"
	}, function(index) {
		$.get(ctx.path + "/platform/pm/delUsers.json?userIds=" + ids.join(","),
				function(result) {
					if (result.rs == 1) {
						grid.trigger("reloadGrid");// 刷新表单
						top.toastr.success("删除用户成功！");
					} else {
						top.toastr.warning("删除用户失败！");
					}
				}, "json");
		layer.close(index);
	});
}

// 设置角色
function setRole() {
	var id = grid.jqGrid('getGridParam', 'selrow');
	var ids = grid.jqGrid('getGridParam', 'selarrrow');
	if (id == null) {
		top.toastr.warning("请点击您要设置角色的用户");
		return;
	}
	if (ids.length > 1) {
		top.toastr.warning("请选择一行进行操作");
		return;
	}
	var row = grid.jqGrid("getRowData", ids[0]);
	$.post(
		ctx.path + '/platform/pm/getUserRoles.do',
		{
			userId : row.userId
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
					title : '设置角色',
					type : 1,
					area : [ '60%', '80%' ],
					fix : false, // 不固定
					maxmin : true,
					btn : [ '提交', '取消' ],
					yes : function(index, layero) {
						saveGroupRole();// 保存角色
					},
					content : $("#roleSet")
				});
			}
		}, "json");
}

// 创建角色选项
var createRoleOption = function(selectId, roleObj) {
	option = $("#" + selectId);
	option.append(" <option value = " + roleObj.roleId + ">" + roleObj.roleName + "</option>");
	// return "<li id='roleLi_"+roleObj.roleId+"'
	// onclick='selectRole("+roleObj.roleId+")'
	// ondblclick='toggleRole("+roleObj.roleId+")'><a>"+roleObj.roleName+"</a></li>";
};

// 移除角色
function removeRole() {
	var roleList = $("#roleList");
	$("#ownerRole > option:selected").clone().appendTo(roleList);
	$("#ownerRole > option:selected").remove();
}

// 设置角色
function addRole() {
	$("#roleList > option:selected").each(
		function() {
			var ownerRole = $("#ownerRole:not(:has(option[value="
					+ $(this).val() + "]))");
			if (ownerRole.length == 0) {
				top.toastr.error("访问该角色已添加，请重新选择！");
			} else {
				$(this).clone().appendTo(ownerRole);
				$(this).remove();
			}
		});
}

// 保存角色
function saveGroupRole() {
	var selRows = $("#jqGrid").jqGrid("getGridParam", "selarrrow"); // 选中行id数组
	var row = $("#jqGrid").jqGrid("getRowData", selRows[0]);
	var setRoleIds = [];
	$("#ownerRole > option").each(function() {
		setRoleIds.push($(this).val());
	});
	$.ajax({
		type : "POST",
		url : ctx.path + '/platform/pm/setUserRoles.do',
		traditional : true,
		data : {
			userId : row.userId,
			roleIds : setRoleIds
		},
		dataType : "json",
		success : function(data) {
			if (data.rs == 1) {
				top.toastr.success("设置角色成功");
				layer.close(parentIndex); //再执行关闭
			} else {
				top.toastr.error("设置角色失败");
			}
			grid.trigger("reloadGrid");//重新加载grid
		}
	});
}