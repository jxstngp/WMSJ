<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet" href="${ctx.path}/resources/components/plyr/plyr.css">
		<script src="${ctx.path}/resources/components/plyr/plyr.js"></script>
	</head>
	<body>
		<div id="audio_box">
	        <div class="ibox-content">
				<div class="player">
					<audio controls>
						<source id="audioUrl" src="${ctx.path}${audioUrl}" type="audio/mp3">
					</audio>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var path = ctx.path +"${audioUrl}";
			$(function(){
				$.ajax({
					type : "GET",
					url : path,
					success : function(data,textStatus){
			    		plyr.setup();
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						if(XMLHttpRequest.status != 200){
				    		plyr.setup({html : "<div style='text-align: center;'>对不起，彩铃文件不存在，请重新选择！<div>"});
						}
					}
				});
			});
		</script>
	</body>
</html>