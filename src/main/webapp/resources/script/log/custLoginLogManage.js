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
			url : ctx.path + '/platform/loginlog/getCustLoginLogById' + ctx.noAuthSuffix,
			async : true,
			dataType : 'json',
			type : 'POST',
			data : {logId : id},
			success : function(data) {
				if (data.rs== -1) {
					top.toastr.error("获取数据信息失败");
					return false;
				}
				//返回的map对象参数
                var audio = data.data.loginLogVo;
                for (var key in audio) {
                	//对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#viewForm #"+ key).html(audio[key]);
                }
				parentIndex = layer.open({
					title : '用户登陆日志',
					type : 1,
					area : [ '420px', '400px' ], //宽高
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




