var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var dictInfoAddValidate, dictInfoEditValidate;
$(function(){
	dictInfoAddValidate = $("#dictInfoAddForm").validate({
		rules: {
			"dictName": {
				required: true
			},
			"sort": {
				required: true
			},
			"typeId": {
				min: 0
			}
		},
		messages: {
			"dictName": {
				required: "请输入您的字典名称！"
            },
            "sort": {
				required: "请输入您的字典排序！"
            },
            "typeId": {
				min: "请选择字典所属类型！"
			}
		},
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success: function(responseText, statusText, xhr, $form) {
					if (responseText.rs == -1) {
						 return false;
					 }
					 top.toastr.success("新增字典【" + $("#dictNameAdd").val() + "】成功！");
		        	 FormUtil.resetForm("dictInfoAddForm");
		        	 doSearch(); 
					 layer.close(index); //再执行关闭 
				},
				error: function(responseText, statusText, xhr, $form) {
		        	 top.toastr.error("新增字典【" + $("#dictNameAdd").val() + "】失败！");
		        	 FormUtil.resetForm("dictInfoAddForm");
		        	 doSearch(); 
					 layer.close(index); //再执行关闭
		        }
			});
		}
	});
	dictInfoEditValidate = $("#dictInfoEditForm").validate({
		rules: {
			"dictName": {
				required: true
			},
			"sort": {
				required: true
			},
			"typeId": {
				min: 0
			}
		},
		messages: {
			"dictName": {
				required: "请输入您的字典名称！"
            },
            "sort": {
				required: "请输入您的字典排序！"
            },
            "typeId": {
				min: "请选择字典所属类型！"
			}
		},
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success: function(responseText, statusText, xhr, $form) {
					if (responseText.rs == -1) {
						 return false;
					 }
					 top.toastr.success("编辑字典类型【" + $("#dictNameEdit").val() + "】成功！");
		        	 FormUtil.resetForm("dictTypeEditForm");
		        	 doSearch(); 
					 layer.close(index); //再执行关闭 
				},
				error: function(responseText, statusText, xhr, $form) {
		        	 top.toastr.error("编辑字典类型【" + $("#dictNameEdit").val() + "】失败！");
		        	 FormUtil.resetForm("dictTypeEditForm");
		        	 doSearch(); 
					 layer.close(index); //再执行关闭
		        }
			});
		}
	});
});

function doSearch() {
	$("#pageForm").submit();
	top.progressbar(frameId);
}

// 新增字典
function openAddPage(){
	//获取所有字典类型
	$.post(ctx.path+'/platform/dict/getDictTypeList'+ ctx.noAuthSuffix, function (data, status){ 
		var html='<option value="-1">请选择字典类型</option>';
		if(data=="" || data==null){
			$("#typeIdAdd").html(html);
    	} else {
    		for (var i = 0; i < data.length; i++) {
    			html = html + '<option value="'+data[i].typeId+'">'+data[i].typeName+'</option>';
			}
    		$("#typeIdAdd").html(html);
    	}
	},"json");
	//页面层	
	index=layer.open({
		type: 1,
		title:'新增字典',
		move: false,
		area: ['420px', '300px'], //宽高
		content: $('#dictInfoAdd'),
		btn:['保存', '关闭'],
	    yes:function(index, layero){
	    	$("#dictInfoAddForm").submit();
	    },
	    cancel:function(index){
	    	FormUtil.resetForm("dictInfoAddForm");
	    	dictInfoAddValidate.resetForm();
	    }	    
	});
}

// 编辑字典类型
function openEditPage(dictId) {
	$.ajax({
		url:  ctx.path +'/platform/dict/getDictInfo'+ ctx.noAuthSuffix,
        async: true,
        dataType: 'json',
        type: 'POST',
        data: {dictId: dictId},
        success: function(result) {
        	if (result.rs == -1) {
        		top.toastr.error("获取字典数据失败，请重新尝试！");
        		return false;
        	}
        	//获取所有字典类型
        	$.post(ctx.path+'/platform/dict/getDictTypeList' + ctx.noAuthSuffix, function (data, status){ 
        		var html='<option value="-1">请选择字典类型</option>';
        		if(data=="" || data==null){
        			$("#typeIdEdit").html(html);
            	} else {
            		for (var i = 0; i < data.length; i++) {
            			if(result.data.typeId == data[i].typeId){
            				html += '<option value="'+data[i].typeId+'" selected>'+data[i].typeName+'</option>';
       					}else{
       						html += '<option value="'+data[i].typeId+'">'+data[i].typeName+'</option>';
       					}
        			}
            	}
        		$("#typeIdEdit").html(html);
        	},"json");
        	$("#dictInfoEditForm").fill(result.data);//表单数据填充
	        $("#dictInfoEditForm").find("#dictIdEdit").val(dictId);
	        $("#dictInfoEditForm").find("#remarkEdit").val(result.data.remark);
	        parentIndex = layer.open({
	        	title:'编辑字典',
	       	    type: 1,
	       	    area: ['420px', '300px'], //宽高
	       	    btn:['保存', '关闭'],
		 	    yes:function(index, layero){
		 	    	$("#dictInfoEditForm").submit();
		 	    },
	       	    content: $('#dictInfoEdit'),
	       	    cancel:function(index){
	       	    	FormUtil.resetForm("dictInfoEditForm");
	       	    	dictInfoEditValidate.resetForm();
	       	    }
	        });
        },
        error: function(jqXHR , textStatus , errorThrown){
        	top.toastr.error("获取字典数据失败，请重新尝试！");
        }
	});
}

// 删除字典类型
function delDictInfo(dictId, dictName){
	var msg = "确定删除字典【" + dictName + "】吗？";
	layer.confirm(msg, {icon: 3, title:"提示信息"}, function(index){
		$.get(ctx.path +"/platform/dict/delDictInfo"+ ctx.bizSuffix +"?dictId=" + dictId + "&dictName=" + dictName, function(result){
			if(result.rs==1) {
				top.toastr.success("删除字典类型【" + dictName + "】成功！");
				doSearch();//刷新表单
			}else{
				top.toastr.error("删除字典类型【" + dictName + "】失败！");
		   	}
		}, "json");
		layer.close(index);
	});
}
