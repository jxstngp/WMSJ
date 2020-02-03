<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人员选择器</title>
	<link rel="stylesheet" href="${ctx.path}/resources/components/jqueryUI/jquery-ui.css">
	<script src="${ctx.path}/resources/components/jqueryUI/jquery-ui.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//代理商建议搜索
			$("#searchUser").autocomplete(
					{
						source : function(request, response) {
							if (!$.trim(request.term) == "") {
								$.ajax({
									url : ctx.path + "/platform/common/queryUserName",
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
							if(checkChooseUser($.trim(ui.item.id),true)){
								var user = "<li value="+ui.item.id+"><img src='"+ctx.path+"/resources/images/img_tx.png'><span>"+ui.item.label+"</span></li>";
								$(user).append("<a href=\"javascript:;\" onclick=\"removeUser("+ui.item.id+")\"><i class=\"midicon remove_ico\"></i></a>").appendTo(chooseUser);
							}
							countNum();
							$("#searchUser").val("");
							event.preventDefault();
						}
					});			
			
			
			//显示数据
			$.ajax({
				url : ctx.path + "/platform/common/personnelSelectorData",
				type : "POST",
				success : function(data) {
					//清空数据
					$("#allUser").empty();
					$("#departUser").empty();
					$("#chooseUser").empty();
					
					var userObject= data.users;
					var departObject = data.depart;

					//显示全员信息
					var userData ="";
					$.each(userObject, function(i, user) {
						userData += "<li value="+user.userId+"><img src='"+ctx.path+"/resources/images/img_tx.png'><span>"+user.userName+"</span></li>";
					});
					$("#allUser").append(userData);
					
					//显示部门人员信息
					var departUserData ="<dd><ul>";
					$.each(userObject, function(i, user) {
						//没有部门人员
						if (user.departId == 0) {
							departUserData += "<li value="+user.userId+"><img src='"+ctx.path+"/resources/images/img_tx.png'><span>"+user.userName+"</span></li>";
						}

					});
					
					departUserData += "</ul></dd>";
					$.each(departObject, function(i, depart) {
						departUserData += "<dt><i class=\"minicon tit_arrow\"></i>"+depart.departName+"<a href=\"javascript:;\"></a></dt>";
						departUserData += "<dd style=\"display: none;\"><ul>";
						$.each(userObject, function(i, user) {
							if(depart.departId==user.departId){
								departUserData += "<li value="+user.userId+"><img src='"+ctx.path+"/resources/images/img_tx.png'><span>"+user.userName+"</span></li>";
							}
						});						
						departUserData += "</ul></dd>";
					});					
					$("#departUser").append(departUserData);

					},
					dataType : "json"
				});
			
			//给所有dt添加事件做显隐
			$("#personnelSelector").on("click", "dt", function() {
					$(this).toggleClass("active");
					$(this).nextUntil("dt").toggle();
				}).on("click", "li", function() {
					//判断有没有重复添加以及设置单选还是多选人员
					if(checkChooseUser($(this).attr("value"),true)){
						$(this).clone().append("<a href=\"javascript:;\" onclick=\"removeUser("+$(this).attr("value")+")\"><i class=\"midicon remove_ico\"></i></a>").appendTo(chooseUser);
					}
					countNum();
					
			});
			
		});
		
		//计算已选人员个数
		function countNum(){
			$("#userNum").text($("#chooseUser li").length);
		}	
		//清空用户
		function clearUser(){
			$("#chooseUser").empty();
			countNum();
		}
		
		//判断选中人员是否重复
		function checkChooseUser(value,i){
			var result = true;  
			if(i){
				$("#chooseUser").empty();
			}
			$("#chooseUser li").each(function(){
				if($(this).attr("value")==value){
					result = false; 
					return false;
				}
			});
			return result;
		}
		//单个删除已选中的人员
		function removeUser(value){
			$("#chooseUser li[value="+value+"]").remove();
			countNum();
		}
		
		//组装已选人员数据
		function backData(){
			var result = {};
			var names = [];
			var values = [];
			$("#chooseUser li").each(function(){
				var n = $.trim($(this).find("span").text());
				var v = $(this).attr("value");
				names.push(n);
				values.push(v);
			
			});
			result.name=names.join(",");
			result.value=values.join(",");
			return result;
		}
		
	</script>

</head>
<body>
	<div class="selcontactsbtn_box" id="selcontacts_box">
		<div class="selcontacts_left">
			<div class="discountsetlist_search">
				<input type="text" id="searchUser" class="inputtext" maxlength="30" />
				<i class="minicon search_icon"></i>
			</div>
			<div class="selcontacts_list">
				<div class="tab_hd">
					<h3 class="active">按员工</h3>
					<h3>按部门</h3>
				</div>
				<div class="tab_bd" id="personnelSelector">
					<div class="staff_con  mCustomScrollbar_y" data-mcs-theme="dark">
						<div class="staff_list">
							<dl>
								<dt class="active">
									<i class="minicon tit_arrow"></i>公司全体员工
									<!-- <a href="javascript:;"><i class="midicon choice_ico"></i></a> -->
								</dt>
								<dd>
									<!-- 全体人员 -->
									<ul id="allUser"></ul>
								</dd>
							</dl>
						</div>
					</div>
					<div class="staff_con  mCustomScrollbar_y" data-mcs-theme="dark" style="display: none;">
						<div class="staff_list">
						    <!-- 部门人员 -->
							<dl id="departUser"></dl>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="selcontacts_arrow fl"></div>
		<div class="selcontacts_right">
			<div class="tit">
				<em>已选人员：</em><span id="userNum">0</span>
				<a href="javascript:;" onclick="clearUser();">
					<i class="minicon empty_ico"></i>清空
				</a>
			</div>
			<div class="staff_list mCustomScrollbar_y" data-mcs-theme="dark">
			    <!-- 已选人员 -->
				<ul id="chooseUser"></ul>
			</div>
		</div>
	</div>
</body>
</html>