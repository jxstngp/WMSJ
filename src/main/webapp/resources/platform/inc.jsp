<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="st" uri="/stisp-tags" %>
<%@taglib prefix="p" uri="/custom-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String,Object> ctx=new HashMap<String,Object>();
ctx.put("path", path);
ctx.put("basePath", basePath);
ctx.put("version", "1.0");
ctx.put("pageSuffix", ".do");
ctx.put("bizSuffix", ".json");
ctx.put("noAuthSuffix", ".exjson");
pageContext.setAttribute("ctx", ctx);
%>
<base href="${ctx.path}">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />    
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />

<link rel="stylesheet" href="${ctx.path}/resources/css/reset.css?v=1.0" rel="stylesheet" />
<link rel="stylesheet" href="${ctx.path}/resources/css/index.css?v=1.0" rel="stylesheet" />
<link href="${ctx.path}/resources/css/main.css?v=1.0" rel="stylesheet" />
<link href="${ctx.path}/resources/components/bootstrap/bootstrap-datetimepicker.css?v=2.0" rel="stylesheet" />
<link href="${ctx.path}/resources/components/select2/select2.css" rel="stylesheet" />
<link href="${ctx.path}/resources/components/jquery/jquery.mCustomScrollbar.css?v=1.0" rel="stylesheet" />
<link href="${ctx.path}/resources/components/toastr/toastr.min.css" rel="stylesheet" />
<link href="${ctx.path}/resources/css/progressbar.css?v=1.0" rel="stylesheet" />

<!-- 全局js -->
<script src="${ctx.path}/resources/components/jquery/jquery-1.12.1.min.js?v=1.12.1"></script>
<script src="${ctx.path}/resources/components/bootstrap/bootstrap.min.js?v=3.3.5"></script>
<script type="text/javascript" src="${ctx.path}/resources/components/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${ctx.path}/resources/components/jquery/jquery.formFill.js"></script>
<script type="text/javascript" src="${ctx.path}/resources/components/bootstrap/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx.path}/resources/components/bootstrap/bootstrap-datetimepicker.zh-TW.js"></script>    
<script type="text/javascript" src="${ctx.path}/resources/components/select2/select2.js"></script>
<script type="text/javascript" src="${ctx.path}/resources/components/select2/select2_locale_zh-CN.js"></script>
<script type="text/javascript" src="${ctx.path}/resources/components/jquery/jquery.mCustomScrollbar.concat.min.js?v=3.1.13"></script>
<script src="${ctx.path}/resources/js/common/common.js?v=1.0"></script>
<script src="${ctx.path}/resources/components/toastr/toastr.min.js"></script>

<!-- 弹窗插件 -->
<link rel="stylesheet" href="${ctx.path}/resources/components/layer/layer.css?v=2.1" rel="stylesheet">
<script src="${ctx.path}/resources/components/layer/layer.js?v=2.1"></script>

<!-- 验证插件 -->
<script src="${ctx.path}/resources/components/validation/jquery.validate.js"></script>
<script src="${ctx.path}/resources/components/validation/additional-methods.js"></script>
<script src="${ctx.path}/resources/components/validation/messages_zh.js"></script>
<script src="${ctx.path}/resources/js/common_validate.js"></script>
<script src="${ctx.path}/resources/js/common_check.js"></script>

<script type="text/javascript">
	var ctx = ctx || {};
	ctx.path = '${ctx.path}';
	ctx.basePath = '${ctx.basePath}';
	ctx.pageSuffix = '${ctx.pageSuffix}';
	ctx.bizSuffix = '${ctx.bizSuffix}';
	ctx.noAuthSuffix = '${ctx.noAuthSuffix}';
	
	//全局使用。即所有弹出层都默认采用，但是单个配置skin的优先级更高
	layer.config({
	  skin: 'defined-class'
	});
	
	$(function(){  
	    // 设置 Ajax全局的参数  
		$.ajaxSetup({
		    global: true,
		    complete:function(xhr, status, err){
	    	    if(xhr.responseText =="timeout"){
	    	    	top.toastr.error("对不起，操作已超时，请您重新登录系统！");
	    	    }
			if(typeof(xhr.responseJSON)!="undefined"&&xhr.responseJSON.rs == -1){
				if(xhr.responseJSON.alertExpFlag == 1){
					top.toastr.error(xhr.responseJSON.msg);
				}
			}
		    },
		    error: function(xhr, status, err) {
		    	//定义变量 发生400 500刷新页面回到主界面
		        if (xhr.status == 404) {
		          	window.location.href = ctx.path + "/resources/platform/views/error/404.jsp";
		        }else{
		        	window.location.href = ctx.path + "/resources/platform/views/error/505.jsp";
		        }
		    }
		});
	});	
</script>