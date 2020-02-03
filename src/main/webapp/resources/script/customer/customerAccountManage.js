var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var editValidate;
$(function() {
	//编辑校验
    editValidate = $("#editForm").validate({
		rules : {
            proviceId : {
				required : true
			},
            cityId : {
				required : true
			},
            address : {
                required : true
            }
		},
		messages : {
            proviceId : {
                required : "请选择省份！"
            },
            cityId : {
                required : "请选择城市！"
            },
            address : {
                required : "请输入详细地址！"
            }
		},
		submitHandler : function(form) {
			$(form).ajaxSubmit({
				//表单提交成功后的回调
				success : function(responseText, statusText, xhr, $form) {
					if (responseText.rs > 0) {
						top.toastr.success("编辑账户信息【" + $("#editForm").find("#custCode").text() + "】成功！");
						FormUtil.resetForm("editForm");
						doSearch();
						layer.close(index); //再执行关闭
					}
				}
			});
		}
	});

});

//编辑省市联动change事件
function loadCityUpdate(provinceId, cityId){
    $('#editForm #cityId').find("option").remove();
    $.ajax( {
        type : 'POST',
        url : ctx.path + '/platform/common/getCityByProvinceId' + ctx.noAuthSuffix,
        data: ({
            provinceId : provinceId
        }),
        dataType : 'json',
        success : function(data) {
            var options = "<option value=''>请选择</option>";
            $.each(data,function(i, row) {
                if(cityId == row.cityId){
                    options += "<option value='"+row.cityId+"' selected>"+row.cityName+"</option>";
                }else{
                    options += "<option value='"+row.cityId+"'>"+row.cityName+"</option>";
                }
            });
            $("#editForm #cityId").html(options);
        }
    });
}

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
                console.info(dataRet);
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
                $("#editForm #custCode").html(dataRet.custCode);
                $("#editForm #wechatName").html(dataRet.wechatName);
                $("#editForm #mobile").html(dataRet.mobile);
                $("#editForm #custName").html(dataRet.custName);
                $("#editForm").find("#custCodeTmp").val(dataRet.custCode);
				$("#editForm").find("#custId").val(id);
                $("#editForm").find("#provinceId").val(dataRet.provinceId);

				$("#editForm").find("#address").val(dataRet.address);
                //加载编辑省市联动
                loadCityUpdate(dataRet.provinceId, dataRet.cityId);

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
