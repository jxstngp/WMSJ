var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var departAddValidate, departEditdValidate;
$(function() {
	//校验新增表单
	departAddValidate = $("#addDepartForm").validate(
			{
				rules : {
					parentDepartId : {
						required : true
					},
					departName : {
						required : true
					},
					managerUserId : {
						required : true
					}
				},
				messages : {
					parentDepartId : {
						required : "请选择上级部门！"
					},
					departName : {
						required : "请输入部门名称！"
					},
					managerUserId : {
						required : "请选择部门负责人！"
					}
				},
				submitHandler : function(form) {
					$(form).ajaxSubmit(
							{
								//表单提交成功后的回调
								success : function(responseText, statusText,
										xhr, $form) {
									/*if(responseText.rs == -1){
										top.toastr.error("新增部门【" + $("#departName").val() + "】失败！");
										return false;
									}
									if(responseText.rs == -2){
										top.toastr.error("新增部门【" +$("#departName").val()+"】部门名称已存在！");
										return false;
									}*/
									if (responseText.rs > 0) {
										top.toastr.success("新增部门【" + $("#addDepartName").val() + "】成功！");
										FormUtil.resetForm("addDepartForm");
										doSearch();
										layer.close(index); //再执行关闭 
									}
								}
							});
				}
			});

	//编辑部门
	departEditValidate = $("#editDepartForm").validate(
			{
				rules : {
					parentDepartId : {
						required : true
					},
					departName : {
						required : true
					},
					managerUserId : {
						required : true
					}
				},
				messages : {
					parentDepartId : {
						required : "请选择上级部门！"
					},
					departName : {
						required : "请输入部门名称！"
					},
					managerUserId : {
						required : "请选择部门负责人！"
					}
				},
				submitHandler : function(form) {
					$(form).ajaxSubmit(
							{
								//表单提交成功后的回调
								success : function(responseText, statusText,
										xhr, $form) {
									/*if(responseText.rs == -1){
										top.toastr.error("编辑部门【" + $("#editDepartName").val() + "】失败！");
										return false;
									}
									if(responseText.rs == -2){
										top.toastr.error("编辑部门【" + $("#editDepartName").val() + "】部门名称已存在！");
										return false;
									}*/
									if (responseText.rs > 0) {
										top.toastr.success("编辑部门【" + $("#editDepartName").val() + "】成功！");
										FormUtil.resetForm("editDepartForm");
										doSearch();
										layer.close(index); //再执行关闭 
									}
								}
							});
				}
			});

});

//执行查询
function doSearch() {
	$("#pageForm").submit();
	top.progressbar(frameId);
}

//新增
function openAddPage() {
	//获取所有部门
	$.post(ctx.path + '/platform/depart/getDepartList' + ctx.noAuthSuffix, function(data, status) {
		var html = '<option value="0">顶级部门</option>';
		if (data == "" || data == null) {
			$("#parentDepartId").html(html);
		} else {
			for (var i = 0; i < data.length; i++) {
				html = html + '<option value="' + data[i].departId + '">' + data[i].departName + '</option>';
			}
			$("#parentDepartId").html(html);
		}
	}, "json");
	//获取所有用户
	$.post(ctx.path + '/platform/depart/selectUserList'+ ctx.noAuthSuffix, function(data, status) {
		var html = '<option value="">请选择</option>';
		if (data == "" || data == null) {
			$("#managerUserId").html(html);
		} else {
			for (var i = 0; i < data.length; i++) {
				html = html + '<option value="' + data[i].userId + '">' + data[i].userName + '</option>';
			}
			$("#managerUserId").html(html);
		}
	}, "json");

	//页面层	
	index = layer.open({
		type : 1,
		title : '新增部门',
		move : false,
		area : [ '420px', '300px' ], //宽高
		content : $('#addDepart'),
		btn : [ '保存', '关闭' ],
		yes : function(index, layero) {
			$("#addDepartForm").submit();
		},
		cancel : function(index) {
			FormUtil.resetForm("addDepartForm");
			departAddValidate.resetForm();
		}
	});

}

//打开编辑页面
function editPage(id) {
	if (Number(id)) {
		$.ajax({
			url : ctx.path + '/platform/depart/getDepartById' + ctx.noAuthSuffix,
			async : true,
			dataType : 'json',
			type : 'POST',
			data : {
				departId : id
			},
			success : function(data) {
				if (data.rs == -1) {
					top.toastr.error("操作失败");
					return false;
				}
				$("#editDepartForm").fill(data.data["depart"]);//表单数据填充
				$("#editDepartForm").find("#departId").val(id);
				$("#editDepartName").val(data.data["depart"].departName);
				$("#remarkEdit").val(data.data["depart"].remark);
				var depart = '<option value="0">顶级部门</option>';
				if (data.data["departs"] == "" || data.data["departs"] == null) {
					$("#editParentDepartId").html(html);
				} else {
					for (var i = 0; i < data.data["departs"].length; i++) {
						if (data.data["departs"][i].departId == data.data["depart"].parentDepartId) {
							depart = depart
									+ '<option value="'
									+ data.data["departs"][i].departId
									+ '" selected>'
									+ data.data["departs"][i].departName
									+ '</option>';
						} else {
							depart = depart
									+ '<option value="'
									+ data.data["departs"][i].departId
									+ '">'
									+ data.data["departs"][i].departName
									+ '</option>';
						}
					}
					$("#editParentDepartId").html(depart);
				}

				var user = '<option value="">请选择</option>';
				if (data.data["users"] == "" || data.data["users"] == null) {
					$("#editManagerUserId").html(user);
				} else {
					for (var i = 0; i < data.data["users"].length; i++) {
						if (data.data["users"][i].userId == data.data["depart"].managerUserId) {
							user = user + '<option value="'
									+ data.data["users"][i].userId
									+ '" selected>'
									+ data.data["users"][i].userName
									+ '</option>';
						} else {
							user = user + '<option value="'
									+ data.data["users"][i].userId
									+ '">'
									+ data.data["users"][i].userName
									+ '</option>';
						}
					}
					$("#editManagerUserId").html(user);
				}

				parentIndex = layer.open({
					title : '编辑部门',
					type : 1,
					area : [ '420px', '300px' ], //宽高
					btn : [ '保存', '关闭' ],
					yes : function(index, layero) {
						$("#editDepartForm").submit();
					},
					content : $('#editDepart'),
					cancel : function(index) {
						FormUtil.resetForm("editDepartForm");
						departEditValidate.resetForm();
					}
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				top.toastr.error("操作失败");
			}
		});

	} else {
		top.toastr.error("数据异常！");

	}

}

//删除
function del(id, name) {
	if (Number(id)) {
		var msg = "确定删除部门【" + name + "】吗？";
		layer.confirm(msg, {
			icon : 3,
			title : "提示信息"
		}, function(index) {
			$.get(ctx.path + "/platform/depart/delDepart"+ ctx.bizSuffix + "?departId=" + id + "&departName=" + name, function(result) {
				if (result.rs == 1) {
					top.toastr.success("删除部门【" + name + "】成功！");
					doSearch();
					//刷新表单
				}
			}, "json");
			layer.close(index);
		});
	} else {
		top.toastr.error("数据异常！");
	}
}
