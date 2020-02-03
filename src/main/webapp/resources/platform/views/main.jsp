<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<%@ page language="java" import="com.samton.platform.framework.util.CurrentUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="renderer" content="webkit" />
		
		<title>${SYSTEM_NAME}</title>
		
		<link rel="shortcut icon" href="${ctx.path}/resources/images/favicon.ico" type="image/x-icon" />
		<link href="${ctx.path}/resources/components/animate/animate.css?v=1.0" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx.path}/resources/components/jqueryUI/jquery-ui.css">
		<script type="text/javascript" src="${ctx.path}/resources/components/jquery/jquery.mousewheel.min.js"></script>
		<script type="text/javascript" src="${ctx.path}/resources/components/windowGroup/windowGroup.js"></script>
		<script type="text/javascript" src="${ctx.path}/resources/components/windowGroup/Windowtab.js"></script>
		<script type="text/javascript" src="${ctx.path}/resources/components/windowGroup/taskBar.js"></script>
		<script type="text/javascript" src="${ctx.path}/resources/components/windowGroup/scrolltext.js"></script>
		<script type="text/javascript" src="${ctx.path}/resources/components/validation/jquery.validate.js"></script>
		<script type="text/javascript" src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script type="text/javascript" src="${ctx.path}/resources/script/main/main.js"></script>
		<script type="text/javascript" src="${ctx.path}/resources/script/main/webscoket.js"></script>
		<script src="${ctx.path}/resources/components/jqueryUI/jquery-ui.js"></script>
		
		<script type="text/javascript">
			$(function(){
				initMainMenus(${menus});
				toastr.options = {
					"closeButton": false,
					"debug": false,
					"progressBar": false,
					"positionClass": "toast-top-center",
					"onclick": null,
					"showDuration": "400",
					"hideDuration": "1000",
					"timeOut": "2000",
					"extendedTimeOut": "1000",
					"showEasing": "swing",
					"hideEasing": "linear",
					"showMethod": "fadeIn",
					"hideMethod": "fadeOut"
				};
					 
				//菜单
				winGroup = new $.WindowGroup($('.main_body')[0], $('.tab_nav ul')[0]);
				desktop = winGroup.createWindow({
					id : 'win_0',
					title : '',
					close : false,
					min : false,
					fix : true,
					url : ctx.path+'/resources/platform/views/index.jsp'
				});
				 
				//刷新
				$(".tab_operation .reload").bind("click",function(){
					var $win = winGroup.windows;
					for(var i=0; i<$win.length; i++){
						if($win[i].windowGroup.getActiveWindow()==$win[i]){
							$win[i].reload();
							progressbar("ifr_"+$win[i].getId());
						}
					}
				});
				 
				//全部关闭
				$(".tab_operation .closeall").click(function(){
					layer.confirm("是否要全部关闭？", {icon: 3, title:"提示信息"}, function (index) {
						var $win = winGroup.windows;
						var i=1;
						while($win.length>1){
							$win[i].taskButton.remove();
							$win[i].windowGroup.taskBar.taskButtons.splice(i,1);
							$win[i].remove();
							$win[i].windowGroup.windows.splice(i,1);
						}
						$win[0].windowGroup.setActiveWindow($win[0]);
						$win[0].windowGroup.taskBar.hideOrShowMoreButton();
						layer.close(index);
					});
				});
				 
				//缩放窗口
				$(window).resize(function(){
					var $win = winGroup.windows;
					$win[0].windowGroup.resize();
				});
				
				
				
				//菜单建议搜索
				$("#menuSearch").autocomplete(
						{
							source : function(request, response) {
								if (!$.trim(request.term) == "") {
									$.ajax({
										url : ctx.path + "/platform/pm/queryMenu" + ctx.noAuthSuffix,
										data : {
											name : request.term
										},
										dataType : 'json',
										success : function(dataObj) {
											response(dataObj); //将数据交给autocomplete去展示
										}
									});
								}
							},
							select : function(event, ui) {
								$("#menu_"+ui.item.id).click();
								event.preventDefault();
							}
						});
				
				
			});
			//点击菜单函数
			function openMenu(id, name, url,fname,icon) {
				var winIfr= winGroup.createWindow({
				       id : 'win_'+id,
				       title : name,
				       url : url
				});
				if(winIfr){
					var iconTitle= "<i class=\"midicon "+icon+"\"></i><span>"+fname+"><em>"+name+"</em></span>";
					//滚动条
					progressbar("ifr_win_"+id,iconTitle);
				}
			}

			//加载进度条
			function progressbar(id,icon){
				var index = layer.load(0, {shade: [0.3,'#000']});
				$("#"+id).load(function() {
					layer.close(index); 
					//导航栏信息
					var currenttit = $(document.getElementById(id).contentWindow.document.body).find(".currenttit");
					currenttit.append(icon);
					var fun = document.getElementById(id).contentWindow.addTitle;
					if(fun != undefined && typeof(fun) == "function"){
						fun();
					}
				});
			};
			     
		</script>
	</head>
	
	<body onbeforeunload="body_onUnload()">
		
		<!-- 导航 开始 -->
		<div class="navbox big_navbox" data-mcs-theme="dark" id="navbox">
			<div class="logo">
				<img src="${ctx.path}/resources/images/logo_09.png" alt="JAVA开发框架" class="big" />
				<img src="${ctx.path}/resources/images/mini_logo.png" alt="JAVA开发框架" class="mini" />
			</div>
			<div class="nav_search">
				<input type="text" placeholder="菜单搜索" id="menuSearch" maxlength="20">
				<a href="javascript:;" class="search_btn">
                   <i class="minicon navsearch_icon"></i>
                </a>
			</div>
			<div class="home_pannel" onclick="openMenu(0,'','/resources/platform/views/first.jsp')">
				<a href="javascript:void(0);">
					<i class="midicon home_icon"></i>
					<em>首页</em>
				</a>
			</div>
			<div class="navcon">
				<dl id="mynav"></dl>
			</div>
		</div>
		<!-- 导航 结束 -->
		<div class="main_box">
			<!-- 头部 开始 -->
			<div class="header">
			<div class="menuslide" id="menuslide">
				<i></i>
			</div>
			<div class="company_name">
				<span>XXXXXX发展股份有限公司</span>
			</div>
			<div class="balance">
				<!--         <span>当前余额：<em>45</em>元</span> -->
				<!--         <span>冻结余额：<em>-36.04</em>元</span> -->
			</div>
			<div class="mininav">
				<dl>
					<dd class="relative" id="tips">
						<a href="javascript:" data-toggle="tooltip" data-placement="bottom" data-original-title="提醒" id="remindNum">
							<!-- 提醒消息数量 -->
							<i class="midicon tips_icon"> 
<!--  								<span class="tips_num" >1</span>  -->
 							</i>
						</a>
						<!-- 提醒消息内容 -->
			            <div id="remindContent" class="tipcon" style="display:none;">
<!-- 			         <ul>
			                <li><a href="javascript:void(0);"><i class="minicon sms_icon"></i>XXXXX发展股份有限公司XXXXX发展股份有限公司XXXXX发展股份有限公司XXXXX发展股份有限公司XXXXX发展股份有限公司<span>2016-3-22</span></a></li>
			              </ul>
			              <div class="tipall"><a href="javascript:void(0);">查看所有>></a><span>已经阅读</span></div> -->
			            </div> 
					</dd>
					<dd>
						<a href="javascript:" data-toggle="tooltip" data-placement="bottom" data-original-title="帮助中心">
							<i class="midicon help_icon"></i>
						</a>
					</dd>
					<dd class="relative">
						<div class="admin_con">
							<a href="javascript:" class="admin_toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<span class="portrait">
									<img src="${ctx.path}/resources/images/admin_img_18.jpg" alt="" />
								</span>
								<span class="name" id="currentUserName"><%=CurrentUtil.getCurrentUser().getUserName()%></span>
								<span class="caret"></span>
							</a>
							<ul class="admin_menu" aria-labelledby="dropdownMenu2">
								<li>
									<a href="javascript:;" onclick="openPersonSettingPage(<%=CurrentUtil.getCurrentUser().getUserId()%>)">
									<i class="minicon minsetup_icon"></i>个人设置
									</a>
								</li>
								<li>
									<a href="javascript:;" onclick="openChangePwdPage(<%=CurrentUtil.getCurrentUser().getUserId()%>)">
										<i class="minicon changepass_icon"></i>修改密码
									</a>
								</li>
							</ul>
						</div>
					</dd>
					<dd>
						<a href="javascript:logout();" data-toggle="tooltip" data-placement="bottom" data-original-title="退出">
							<i class="midicon exit_icon"></i>
						</a>
					</dd>
				</dl>
			</div>
		</div>
	
		<!-- 头部 结束 -->
			<!-- 选显卡 开始 -->
			<div class="tab_box">
				<div class="tab_nav">
					<ul></ul>
				</div>
				<div class="tab_operation">
					<a href="javascript:" class="closeall">
						<i class="minicon close_icon"></i>
						<em>关闭</em>
					</a>
					<a href="javascript:" class="reload">
						<i class="minicon reload_icon"></i>
						<em>刷新</em>
					</a>
				</div>
			</div>
			<!-- 选项卡 结束 -->
			<!-- 内容 开始 -->
			<div class="main_body">
				<!-- <iframe width="100%" height="100%" frameborder="0" src="main.html"></iframe> -->
	
			</div>
			<!-- 内容 结束 -->
			<!-- 版权信息 开始 -->
			<!-- <div class="footer">
		        <p>Copyright © 2014-2015 XXXXX发展有限公司  粤ICP备150611231号-14. All rights reserved</p>
		      </div> -->
			<!-- 版权信息 结束 -->
			<!-- 公告信息 开始 -->
			<!--      <div class="alert announce_con" role="alert"> -->
			<!--         <i class="midicon announce_icon"></i> -->
			<!--         <div class="announce_nav"> -->
			<!--             <ul id="demo"> -->
			<!--               <li><a href="#">公告公告公告公告公告公告公告公告公告公告公告公告</a></li> -->
			<!--               <li><a href="#">公告公告公告公告公告公告公告公告公告公告公告公告</a></li> -->
			<!--               <li><a href="#">公告公告公告公告公告公告公告公告公告公告公告公告</a></li> -->
			<!--               <li><a href="#">公告公告公告公告公告公告公告公告公告公告公告公告</a></li> -->
			<!--               <li><a href="#">公告公告公告公告公告公告公告公告公告公告公告公告</a></li> -->
			<!--             </ul> -->
			<!--         </div> -->
			<!--         <a href="#" class="close" data-dismiss="alert"><i class="minicon announceclose_icon"></i></a> -->
			<!--       </div> -->
			<!-- 公告信息 结束 -->
		</div>

	
		<!-- 个人设置  -->
		<div id="personInfoEdit" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="personInfoEditForm" action="${ctx.path}/platform/pm/updateUser${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em class="required">*</em>登录名：
					</h5>
					<div class="add_value">
						<input type="hidden" id="userId" name="userId">
						<input type="text" id="loginNameEdit" name="loginName" style="color: #999;" maxlength="50" class="inputtext" readonly="readonly" />
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em class="required">*</em>用户名：
					</h5>
					<div class="add_value">
						<input type="text" id="userNameEdit" name="userName" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>部门：</h5>
					<div class="add_value">
						<input type="hidden" id="departId" name="departId" />
						<input type="text" id="departNameEdit" style="color: #999;" name="departName" maxlength="20" class="inputtext" readonly="readonly" />
					</div>
				</div>
				<div class="add_list">
					<h5>手机号码：</h5>
					<div class="add_value">
						<input type="text" id="mobilePhoneEdit" name="mobile" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>邮箱：</h5>
					<div class="add_value">
						<input type="text" id="emailEdit" name="email" maxlength="50" class="inputtext" />
					</div>
				</div>
			</form>
		</div>
		<!-- 修改密码 -->
		<div id="changePwd" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="pwdForm" action="${ctx.path}/platform/pm/changePwd${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em class="required">*</em>原密码：
					</h5>
					<div class="add_value">
						<input type="hidden" id="userId" name="userId" value="<%=CurrentUtil.getCurrentUser().getUserId()%>">
						<input type="password" id="oldPwd" name="oldPwd" maxlength="16" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em class="required">*</em>新密码：
					</h5>
					<div class="add_value">
						<input type="password" isNotSameid="oldPwd" id="newPwd" name="newPwd" maxlength="16" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em class="required">*</em>确认密码：
					</h5>
					<div class="add_value">
						<input type="password" isSameid="newPwd" id="newPwdAgain" name="newPwdAgain" maxlength="16" class="inputtext" />
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
