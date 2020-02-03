<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
	<script src="${ctx.path}/resources/script/user/userManage.js?v=1.0"></script>
	<script type="text/javascript">
		    $(function(){
		    	loadCriteria();
		    });
			//加载查询条件
			function loadCriteria() {
				$("#userName").val("${pmUser.userName}");
				$("#loginName").val("${pmUser.loginName}");
		
			}
		</script>
	</head>
	<body>
		<div class="main_con">
			<form class="tableform" action="${ctx.path}/platform/pm/queryUserList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_SYSTEM_USER_ADD">
								<button type="button" class="btn add_btn" onclick="openAddPage();">
									<i class="minicon addoperation_icon"></i>
									<span>新增</span>
								</button>
							</p:auth>
							<span class="line">|</span>
							<p:auth mcode="MENU_SYSTEM_USER_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/platform/pm/exportUserList${ctx.bizSuffix}')">
									<i class="minicon exportoperation_icon"></i>
									<span>导出</span>
								</button>
							</p:auth>
						</div>
					</div>
				</div>
				<div class="search_box" id="search_box">
					<div class="search_nav">
						<ul>
							<li>
								<input type="text" id="loginName" name="loginName" class="inputtext" placeholder="登录名称" />
							</li>
							<li>
								<input type="text" id="userName" name="userName" class="inputtext" placeholder="用户名称" />
							</li>
						</ul>
					</div>
					<div class="search_btncon">
						<p:auth mcode="MENU_SYSTEM_USER_SEARCH">
							<button type="button" class="btn add_btn" onClick="doSearch()">
								<i class="minicon search_icon"></i>
								<span>查询</span>
							</button>
						</p:auth>
						<button type="button" class="btn export_btn clearToolBtn">
							<i class="minicon reload_icon"></i>
							<span>重置</span>
						</button>
					</div>
				</div>
				<div class="tablelist_box tablelistboxH">
					<div class="tablelist_con">
						<div class="tablelist_theadbox">
							<div class="tablelist_thead">
								<table>
									<tr>
										<th width="50">序号</th>
										<th class="change">登录名称</th>
										<th class="change">用户名称</th>
										<th width="200">角色名称</th>
										<th class="change">所属部门</th>
										<th class="change">手机号码</th>
										<!-- <th class="change">邮箱</th> -->
<%--										<th width="150">最后登录时间</th>--%>
										<th width="80">状态</th>
										<th width="280" class="operation_th">操作</th>
									</tr>
								</table>
							</div>
						</div>
						<div class="tablelist_tbody">
							<table>
								<c:if test="${empty pageData.data}">
									<tr>
										<td align="center" colspan="8">对不起，没有找到相关数据！</td>
									</tr>
								</c:if>
								<c:forEach items="${pageData.data }" var="user" varStatus="staus">
									<tr>
										<td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
											${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
										</td>
										<td title="${user.loginName }">${user.loginName }</td>
										<td title="${user.userName }">${user.userName }</td>
										<td title="${user.roleNames }">${user.roleNames }</td>
										<td title="${user.departName }">${user.departName }</td>
										<td title="${user.mobile }">${user.mobile }</td>
										<%-- <td title="${user.email }">${user.email }</td> --%>
<%--										<td><fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
										
										<c:choose>
											<c:when test="${user.state == 1 }">
												<td title="已启用"><span class="recharge_statue recharge_success">已启用</span></td>
											</c:when>
											<c:when test="${user.state == 2 }">
												<td title="已禁用"><span class="recharge_statue recharge_failed">已禁用</span></td>
											</c:when>											
										</c:choose>
										
										<td class="operation_td">
											<p:auth mcode="MENU_SYSTEM_USER_UPDATE">
												<button class="operationbtn" type="button" onclick="editPage(${user.userId});">
													<span>编辑</span>
												</button>
											</p:auth>
											<p:auth mcode="MENU_SYSTEM_USER_ROLE">
												<button class="operationbtn" type="button" onclick="setRole(${user.userId},'${user.userName }');">
													<span>角色设置</span>
												</button>
											</p:auth>
											<p:auth mcode="MENU_SYSTEM_USER_DIS_ENABLE">
												<c:if test="${user.state == 1 }">
													<button class="operationbtn" type="button" onclick="disAndEnableAccount(${user.userId },'${user.userName }',2)">
														<span>禁用</span>
													</button>
												</c:if>
												<c:if test="${user.state == 2 }">
													<button class="operationbtn" type="button" onclick="disAndEnableAccount(${user.userId },'${user.userName }',1)">
														<span>启用</span>
													</button>
												</c:if>
											</p:auth>
											<p:auth mcode="MENU_SYSTEM_USER_DEL">
												<button class="operationbtn" type="button" onclick="del(${user.userId},'${user.userName }');">
													<span>删除</span>
												</button>
											</p:auth>
											<p:auth mcode="MENU_SYSTEM_USER_RESET_PWD">
												<button class="operationbtn" type="button" onclick="resetUserPwd(${user.userId },'${user.userName }');">
													<span>重置密码</span>
												</button>
											</p:auth>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<jsp:include page="/resources/common/page.jsp"></jsp:include>
			</form>
		</div>
		<!-- 新增用户 -->
		<div id="userAdd" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="userForm" action="${ctx.path}/platform/pm/addUser${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em class="required">*</em>登录名称：
					</h5>
					<div class="add_value">
						<input type="text" name="loginName" maxlength="50" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em class="required">*</em>用户名称：
					</h5>
					<div class="add_value">
						<input type="text" id="userNameAdd" name="userName" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em class="required">*</em>登录密码：
					</h5>
					<div class="add_value">
						<input type="password" name="pwd" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>所属部门：</h5>
					<div class="add_value">
						<select class="select" name="departId" id="departId" type="select"></select>
					</div>
				</div>
				<div class="add_list">
					<h5>手机号码：</h5>
					<div class="add_value">
						<input type="text" id="mobilePhoneAdd" name="mobilePhone" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>邮箱：</h5>
					<div class="add_value">
						<input type="text" id="emailAdd" name="email" maxlength="50" class="inputtext" />
					</div>
				</div>
			</form>
		</div>
		<!-- 编辑用户 -->
		<div id="userEdit" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="userEditForm" action="${ctx.path}/platform/pm/updateUser${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em class="required">*</em>登录名称：
					</h5>
					<div class="add_value">
						<input type="hidden" id="userId" name="userId" />
						<input type="text" id="loginNameEdit" name="loginName" maxlength="50" class="inputtext" readonly="readonly" disabled="disabled" />
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em class="required">*</em>用户名称：
					</h5>
					<div class="add_value">
						<input type="text" id="userNameEdit" name="userName" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>所属部门：</h5>
					<div class="add_value">
						<select class="select" name="departId" id="departIdEdit" type="select"></select>
					</div>
				</div>
				<div class="add_list">
					<h5>手机号码：</h5>
					<div class="add_value">
						<input type="text" id="mobilePhoneEdit" name="mobilePhone" maxlength="20" class="inputtext" />
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
		<!-- 设置角色 -->
		<div id="roleSet" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<div class="setRole_row">
				<div class="setRole">
					<div class="setRole_head">
						<p class="fl">该用户拥有角色</p>
						<p class="fr">全部角色</p>
						<div class="clear"></div>
					</div>
					<div class="setRole_body">
						<select id="ownerRole" name="ownerRole" class="haveRole setRole_select fl" size="19" ondblclick="removeRole();" multiple="">
						</select>
						<div class="btn_row fl">
							<button class="btn larrow" type="button" onClick="addRole();">
								<span class="minicon"></span>
							</button>
							<button class="btn rarrow" type="button" onClick="removeRole();">
								<span class="minicon"></span>
							</button>
						</div>
						<select id="roleList" name="roleList" class="allRole setRole_select fr" size="19" ondblclick="addRole();" multiple="">
						</select>
					</div>
					<!-- 	<div class="setRole_foot"> -->
					<!-- 		<button type="button" class="btn add_btn"><span>保存</span></button> -->
					<!-- 		<button type="button" class="btn export_btn"><span>返回</span></button> -->
					<!-- 	</div> -->
				</div>
			</div>
		</div>
	</body>
</html>

