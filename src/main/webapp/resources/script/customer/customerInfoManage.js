var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var editValidate;
$(function() {
	//编辑校验
    editValidate = $("#editForm").validate({
		rules : {
            mobile : {
				required : true
			},
            wechatCode : {
				required : true
			}
		},
		messages : {
            mobile : {
                required : "请输入手机号码！"
            },
            wechatCode : {
                required : "请输入绑定微信！"
            }
		},
		submitHandler : function(form) {
			$(form).ajaxSubmit({
				//表单提交成功后的回调
				success : function(responseText, statusText, xhr, $form) {
					if (responseText.rs > 0) {
						top.toastr.success("编辑客户信息【" + $("#editForm").find("#custCode").text() + "】成功！");
						FormUtil.resetForm("editForm");
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


//打开查看页面
function viewPage(id) {
    if (Number(id)) {
        $.ajax({
            url : ctx.path + '/manage/customer/viewCustomer' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {custId : id},
            success : function(data) {
                if (data.rs== -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#viewForm #"+ key).html(dataRet[key]);
                }
                parentIndex = layer.open({
                    title : '查看',
                    type : 1,
                    area : [ '450px', '350px' ], //宽高
                    content : $('#viewId'),
                    btn : [ '关闭' ],
                    cancel : function(index) {
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

//打开编辑页面
function editPage(id) {
	if (Number(id)) {
		$.ajax({
			url : ctx.path + '/manage/customer/viewCustomer' + ctx.bizSuffix,
			async : true,
			dataType : 'json',
			type : 'POST',
			data : {
                custId : id
			},
			success : function(data) {
				if (data.rs == -1) {
					top.toastr.error("操作失败");
					return false;
				}
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#editForm #"+ key).html(dataRet[key]);
                }
                //$("#editForm").fill(dataRet);//表单数据填充
				$("#editForm").find("#custId").val(id);
                $("#editForm").find("#custCodeTmp").val(dataRet.custCode);
				$("#editForm").find("#mobile").val(dataRet.mobile);
				$("#editForm").find("#wechatCode").val(dataRet.wechatCode);

				parentIndex = layer.open({
					title : '编辑',
					type : 1,
					area : [ '450px', '350px' ], //宽高
					btn : [ '保存', '关闭' ],
					yes : function(index, layero) {
						$("#editForm").submit();
					},
					content : $('#editId'),
					cancel : function(index) {
						FormUtil.resetForm("editForm");
						editValidate.resetForm();
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

//禁用/启用
function setPage(id, flag, name) {
	//操作禁用和启用的时候正好相反
	var oprStr = flag == 0 ? "禁用" : "启用";
	var opr = flag == 0 ? 1 : 0;
    if (Number(id)) {
        var msg = "确定"+oprStr+"客户【" + name + "】吗？";
        layer.confirm(msg, {
            icon : 3,
            title : "提示信息"
        }, function(index) {
            $.get(ctx.path + "/manage/customer/setCustomer"+ ctx.bizSuffix + "?custId=" + id + "&isForbid=" + opr + "&custCode=" + name, function(result) {
                if (result.rs == 1) {
                    top.toastr.success(oprStr+"客户【" + name + "】成功！");
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

