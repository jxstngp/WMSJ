var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var dictTypeAddValidate, dictTypeEditValidate;
$(function(){
	dictTypeAddValidate = $("#dictTypeAddForm").validate({
		rules: {
			"typeName": {
				required: true
			}
		},
		messages: {
			"typeName": {
				required: "请输入您的字典类型名称！"
            }
		},
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success: function(responseText, statusText, xhr, $form) {
					if (responseText.rs == -1) {
						 return false;
					 }
					 top.toastr.success("新增字典类型【" + $("#typeNameAdd").val() + "】成功！");
		        	 FormUtil.resetForm("dictTypeAddForm");
		        	 doSearch(); 
					 layer.close(index); //再执行关闭 
				},
				error: function(responseText, statusText, xhr, $form) {
		        	 top.toastr.error("新增字典类型【" + $("#typeNameAdd").val() + "】失败！");
		        	 FormUtil.resetForm("dictTypeAddForm");
		        	 doSearch(); 
					 layer.close(index); //再执行关闭
		        }
			});
		}
	});
	dictTypeEditValidate = $("#dictTypeEditForm").validate({
		rules: {
			"typeName": {
				required: true
			}
		},
		messages: {
			"typeName": {
				required: "请输入您的字典类型名称！"
            }
		},
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success: function(responseText, statusText, xhr, $form) {
					if (responseText.rs == -1) {
						 return false;
					 }
					 top.toastr.success("编辑字典类型【" + $("#typeNameEdit").val() + "】成功！");
		        	 FormUtil.resetForm("dictTypeEditForm");
		        	 doSearch(); 
					 layer.close(index); //再执行关闭 
				},
				error: function(responseText, statusText, xhr, $form) {
		        	 top.toastr.error("编辑字典类型【" + $("#typeNameEdit").val() + "】失败！");
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

// 新增字典类型
function openAddPage(){
	//页面层	
	index=layer.open({
		type: 1,
		title:'新增字典类型',
		move: false,
		area: ['420px', '240px'], //宽高
		content: $('#dictTypeAdd'),
		btn:['保存', '关闭'],
	    yes:function(index, layero){
	    	$("#dictTypeAddForm").submit();
	    },
	    cancel:function(index){
	    	FormUtil.resetForm("dictTypeAddForm");
	    	dictTypeAddValidate.resetForm();
	    }	    
	});
}

// 编辑字典类型
function openEditPage(typeId) {
	$.ajax({
		url:  ctx.path +'/platform/dict/getDictType' + ctx.noAuthSuffix,
        async: true,
        dataType: 'json',
        type: 'POST',
        data: {typeId: typeId},
        success: function(result) {
        	if (result.rs == -1) {
        		top.toastr.error("获取字典类型数据失败，请重新尝试！");
        		return false;
        	}
        	$("#dictTypeEditForm").fill(result.data);//表单数据填充
	        $("#dictTypeEditForm").find("#typeId").val(typeId);
	        $("#dictTypeEditForm").find("#remarkEdit").val(result.data.remark);
	        parentIndex = layer.open({
	        	title:'编辑字典类型',
	       	    type: 1,
	       	    area: ['420px', '240px'], //宽高
	       	    btn:['保存', '关闭'],
		 	    yes:function(index, layero){
		 	    	$("#dictTypeEditForm").submit();
		 	    },
	       	    content: $('#dictTypeEdit'),
	       	    cancel:function(index){
	       	    	FormUtil.resetForm("dictTypeEditForm");
	       	    	dictTypeEditValidate.resetForm();
	       	    }
	        });
        },
        error: function(jqXHR , textStatus , errorThrown){
        	top.toastr.error("获取字典类型数据失败，请重新尝试！");
        }
	});
}

// 删除字典类型
function delDictType(typeId, typeName){
	var msg = "确定删除字典类型【" + typeName + "】吗？";
	layer.confirm(msg, {icon: 3, title:"提示信息"}, function(index){
		$.get(ctx.path +"/platform/dict/delDictType"+ ctx.bizSuffix +"?typeId=" + typeId + "&typeName="+ typeName, function(result){
			if(result.rs==1) {
				top.toastr.success("删除字典类型【" + typeName + "】成功！");
				doSearch();//刷新表单
			}else{
				top.toastr.error("删除字典类型【" + typeName + "】失败！");
		   	}
		}, "json");
		layer.close(index);
	});
}
