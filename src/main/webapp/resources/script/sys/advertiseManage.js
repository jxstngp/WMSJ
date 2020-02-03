var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var advertiseAddValidate, advertiseEditdValidate;

//执行查询
function doSearch() {
	$("#pageForm").submit();
	top.progressbar(frameId);
}

//打开查看页面
function viewPage(id) {
	if (Number(id)) {
		$.ajax({
			url : ctx.path + '/platform/advertise/getAdvertiseById' + ctx.noAuthSuffix,
			async : true,
			dataType : 'json',
			type : 'POST',
			data : {advertiseId : id},
			success : function(data) {
				if (data.rs== -1) {
					top.toastr.error("获取数据信息失败");
					return false;
				}
				//返回的map对象参数
                var audio = data.data.advertise;
                for (var key in audio) {
                	//对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#viewForm #"+ key).html(audio[key]);
                }
				parentIndex = layer.open({
					title : '广告管理',
					type : 1,
					area : [ '420px', '300px' ], //宽高
                    content : $('#viewAudio'),
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

//删除
function del(id, name) {
	if (Number(id)) {
		var msg = "确定删除广告位置【" + name + "】吗？";
		layer.confirm(msg, {
			icon : 3,
			title : "提示信息"
		}, function(index) {
			$.get(ctx.path + "/platform/advertise/delAdvertise"+ ctx.bizSuffix + "?advertiseId=" + id + "&posited=" + name, function(result) {
				if (result.rs == 1) {
					top.toastr.success("删除广告位置【" + name + "】成功！");
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

// 启用/禁用账户
function disAndEnableAccount(advertiseId, posited, status) {
    var flag = status == 0 ? '启用' : '禁用';
    if (Number(advertiseId)) {
        var msg = "确定" + flag + "【" + posited + "】的图片吗？";
        layer.confirm(msg, {
            icon : 3,
            title : "提示信息"
        }, function(index) {
            $.get(ctx.path + "/platform/advertise/disAndEnableAdvertise"+ ctx.bizSuffix + "?advertiseId=" + advertiseId + "&status=" + status,
                function(result) {
                    if (result.rs == 1) {
                        top.toastr.success(flag + "【" + posited + "】图片成功！");
                        doSearch();// 刷新表单
                    } else {
                        top.toastr.error(flag + "【" + posited + "】图片失败！");
                    }
                }, "json"
            );
            layer.close(index);
        });
    } else {
        top.toastr.error("对不起，该图片不能被" + flag + "！");
    }
}

$(function() {
    //校验新增表单
    advertiseAddValidate = $("#addAdvertiseForm").validate(
        {
            rules: {
                position: {
                    required: true
                },
                advertiseImg: {
                    required: true
                },
                link: {
                    required: true,
                    url:true
                }

            },
            messages: {
                position: {
                    required: "请选择广告位置"
                },
                 advertiseImg: {
                   required: "请输入广告图片！"
               },
                link: {
                    required: "请输入链接地址！",
                    true : "请输入正确的URL地址"
                }

            },
            submitHandler: function (form) {
                $(form).ajaxSubmit(
                    {
                        //表单提交成功后的回调
                        success: function (responseText, statusText,
                                           xhr, $form) {
                            if(responseText.rs == -1){
                                top.toastr.error("新增广告失败！");
                                return false;
                            }
                            /*if(responseText.rs == -2){
                                top.toastr.error("新增部门【" +$("#departName").val()+"】部门名称已存在！");
                                return false;
                            }*/
                            if (responseText.rs > 0) {
                                top.toastr.success("新增广告成功！");
                                FormUtil.resetForm("addAdvertiseForm");
                                doSearch();
                                layer.close(index); //再执行关闭
                            }
                        }
                    });
            }
        });
    //编辑部门
    advertiseEditdValidate = $("#editAdvertiseForm").validate(
        {
            rules : {
                position: {
                    required: true
                },
                advertiseImg: {
                    required: true
                },
                link: {
                    required: true,
                    url:true
                }

            },
            messages: {
                position: {
                    required: "请选择广告位置"
                },
                advertiseImg: {
                    required: "请输入广告图片！"
                },
                link: {
                    required: "请输入链接地址！",
                    true : "请输入正确的URL地址"
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
                                top.toastr.success("编辑广告成功！");
                                FormUtil.resetForm("editAdvertiseForm");
                                doSearch();
                                layer.close(index); //再执行关闭
                            }
                        }
                    });
            }
        });
});

//新增
function openAddPage() {
    //页面层
    index = layer.open({
        type : 1,
        title : '新增广告',
        move : false,
        area : [ '420px', '300px' ], //宽高
        content : $('#addAdvertise'),
        btn : [ '保存', '关闭' ],
        yes : function(index, layero) {
            $("#addAdvertiseForm").submit();
        },
        cancel : function(index) {
            FormUtil.resetForm("addAdvertiseForm");
            advertiseAddValidate.resetForm();
        }
    });

}







//打开编辑页面
function editPage(id) {
    if (Number(id)) {
        $.ajax({
            url : ctx.path + '/platform/advertise/getAdvertiseById' + ctx.noAuthSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {
                advertiseId : id
            },
            success : function(data) {
                if (data.rs == -1) {
                    top.toastr.error("操作失败");
                    return false;
                }
                $("#editAdvertiseForm").fill(data.data.advertise["advertise"]);//表单数据填充
                $("#editAdvertiseForm").find("#advertiseId").val(id);
                $("#editAdvertiseImg").val(data.data.advertise.advertiseImg);
                $("#editPositioned").val(data.data.advertise.posited);
                $("#editPosition").val(data.data.advertise.position);
                $("#editLink").val(data.data.advertise.link);
                $("#editRemark").val(data.data.advertise.remark);
               /* var depart = '<option value="0">顶级部门</option>';
                if (data.data.advertise["advertise"] == "" || data.data.advertise["advertise"] == null) {
                    $("#editPosition").html(html);
                } else {
                    for (var i = 0; i < data.data.advertise["advertise"].length; i++) {
                        if (data.data.advertise["advertise"][i].editPosition == data.data["advertise"].advertiseId) {
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
                }*/



                parentIndex = layer.open({
                    title : '编辑广告',
                    type : 1,
                    area : [ '420px', '300px' ], //宽高
                    btn : [ '保存', '关闭' ],
                    yes : function(index, layero) {
                        $("#editAdvertiseForm").submit();
                    },
                    content : $('#editAdvertise'),
                    cancel : function(index) {
                        FormUtil.resetForm("editAdvertiseForm");
                        advertiseEditdValidate.resetForm();
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


