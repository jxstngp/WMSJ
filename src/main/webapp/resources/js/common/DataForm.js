
$().ready(function() {
	//绑定清除按钮清除查询条件
	$(".clearToolBtn").click(function(){
		$("#search_box").find("input").each(function(){
			if($(this).attr("type") == "button")return;
			if($(this).attr("type") == "reset")return;
			if($(this).attr("type") == "radio")return;
			if($(this).attr("type") == "checkbox"){
				$(this).attr("checked",false);
				return
			};
			$(this).val("");
		});
		$("#search_box").find("select").each(function(){
			$(this)[0].selectedIndex = 0;
		});
		$("#currentPageNo").val(1);
	});
});

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



var last_export_time = 0;
function fun_export(newAction) {
	var this_time = new Date().getTime();
	if(this_time-last_export_time < 30*1000){
		alert('对不起,导出过于频繁,请30秒后重试,谢谢！');
		last_export_time = this_time;
		return;
	}
	last_export_time = this_time;
    var action = $("#pageForm").attr("action");
	$("#pageForm").attr("action",newAction);
	eporting = true;
	$("#pageForm").submit();
	eporting = false;
	$("#pageForm").attr("action",action);
}

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