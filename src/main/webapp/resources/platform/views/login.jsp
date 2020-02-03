<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
		<title>${SYSTEM_NAME}</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<link rel="shortcut icon" href="${ctx.path}/resources/images/favicon.ico">
		<link href="${ctx.path}/resources/css/login.css?v=1.0" rel="stylesheet">
		
		<script type="text/javascript">
			$(function() {
				var strCookie = document.cookie;
				if (strCookie.indexOf(";", 0) > 0) {
					$("#remePwd").addClass("checked");
					var arrCookie = strCookie.split(";");
					for (var i = 0; i < arrCookie.length; i++) {
						var arr = arrCookie[i].replace(/\s+/g, "").split("=");
						if ("loginName" == arr[0]) {
							$("#loginName").val(arr[1]);
						}
						if ("pwd" == arr[0]) {
							$("#pwd").val(arr[1]);
						}
					}
				}
			});
		
			document.onkeydown = function(event) {
				if (event && event.keyCode == 13) {
					login();
				}
			};
			if (window.top !== window.self) {
				window.top.location = window.location;
			}
		
			function login() {
				var loginName = $("#loginName").val();
				var pwd = $("#pwd").val();
				var rand = $("#rand").val();
		
				if (loginName == "") {
					$("#error").html("请输入用户名！");
					return;
				}
		
				if (pwd == "") {
					$("#error").html("请输入登录密码！");
					return;
				}
		
				if (rand == "") {
					$("#error").html("请输入验证码！");
					return;
				}
		
				$.post(ctx.path + '/platform/pm/login'+ctx.pageSuffix, {
					loginName : loginName,
					pwd : pwd,
					rand : rand
				}, function(data, status) {
					if (data.data.rs) {
						//是否记住密码
						if ($("#remePwd").hasClass("checked")) {
							document.cookie = "loginName=" + loginName;
							document.cookie = "pwd=" + pwd;
						} else {
							var date = new Date();
							date.setTime(date.getTime() - 10000);
							document.cookie = "loginName=" + loginName + "; expires="+ date.toGMTString();
							document.cookie = "pwd=" + pwd + "; expires="+ date.toGMTString();
						}
		
						location.href = ctx.path + "/platform/pm/index" + ctx.pageSuffix;
					} else {
						$("#error").html(data.data.errorMsg);
					}
				}, "json");
			}
		
			function refresh() {
				document.getElementById("authImg").src = '${ctx.path}/platform/pm/getVaildImg${ctx.pageSuffix}?now='+ new Date();
			}
		</script>
	
	</head>
	<body class="white">
		<div class="loginheader">
			<div class="layout">
				<a href="javascript:void(0);" class="logo"> <img
					src="${ctx.path}/resources/images/login_img/login_logo_06.png" alt="img" />
				</a>
			</div>
		</div>
		<div class="loginbody">
			<div class="layout">
				<div class="loginbox">
					<div class="logintit">
						<h2>登录</h2>
					</div>
					<div class="logincon">
						<div class="error">
							<span><font color="red" id="error">${errorMsg}</font></span>
						</div>
						<div class="logintext">
							<label class="minicon user_icon"></label> <input type="text" placeholder="请输入用户名" class="text" name="loginName" id="loginName" />
						</div>
						<div class="logintext">
							<label class="minicon password_icon"></label> <input type="password" placeholder="请输入登录密码" class="text" name="pwd" id="pwd" />
						</div>
						<div class="logintext codetext">
							<input type="text" class="text" name="rand" id="rand" value="0000" /> 
							<span class="code_img"> 
								<img onclick="refresh()" src="${ctx.path}/platform/pm/getVaildImg${ctx.pageSuffix}" alt="img" id="authImg" />
							</span>
						</div>
						<div class="forgetpass">
							<label class="checkbox" id="remePwd"> <em>记住密码</em>
							</label>
						</div>
						<div class="loginbtncon">
							<button type="button" onclick="login()" class="loginbtn">登 录</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="loginfooter">
			<p>${COPYRIGHT_INFO}</p>
		</div>
	</body>
</html>
