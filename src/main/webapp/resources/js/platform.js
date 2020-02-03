//表单工具类
var FormUtil={
	//重置表单
	resetForm:function(formId){
		var _formId = "commonForm";
		if(typeof(formId) !="undefined"){
			_formId = formId;
		}
		$("#"+_formId).resetForm();//弹窗隐藏后表单清空
		$("#"+_formId).find(":input").each(function(){
			if($(this).attr("type") == "button")return;
			if($(this).attr("type") == "reset")return;
			if($(this).attr("type") == "radio")return;
			if($(this).attr("type") == "checkbox")return;
			if($(this).attr("type") == "hidden"){
				$(this).val("");
				return;
			}
			$(this).removeClass("error").siblings("label").hide();
		});
	},
	//给对象赋默认值
	getDefaultStrValue:function(obj){
		return obj || "";
	}
};

$(function(){  
    // 设置 Ajax全局的参数  
	$.ajaxSetup({
	    global: true,
	    complete:function(xhr, status, err){
	    		  if(typeof(xhr.responseJSON)!="undefined"&&xhr.responseJSON.rs==-1){
	        		  //session过期异常
	        		  if(xhr.responseJSON.detailCode=="40_901"||xhr.responseJSON.detailCode=="40_902"){
	        			  //location.href = angularRootScope.rootHref;
	        		  }else if(xhr.responseJSON.alertExpFlag==1){
	        			  top.toastr.error(xhr.responseJSON.msg);
	        			  //angularRootScope.sysAlert.showError(xhr.responseJSON.msg);        			  
	        		  }
	        	  }
	    },
	    error: function(xhr, status, err) {
	    	//angularRootScope.errorPageTime = true;//定义变量 发生400 500刷新页面回到主界面
	        if (xhr.status == 404) {
	        	//angularState.go('main.404');
//	           window.location.href = angularRootScope.rootHref+"#main/404";
	        }else{
	        	//angularState.go('main.500');
//	        	window.location.href = angularRootScope.rootHref+"#main/500";
	        }
	    }
	});
});

//将表单封装为对象
(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        var array=this.serializeArray();  
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){  
                    serializeObj[this.name].push(this.value);  
                }else{  
                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
                }  
            }else{  
                serializeObj[this.name]=this.value;   
            }  
        });  
        return serializeObj;  
    };  
})(jQuery);