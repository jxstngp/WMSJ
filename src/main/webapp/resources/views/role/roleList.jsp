<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<%@taglib prefix="p" uri="/custom-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=${ctx.version}"></script>
		<script src="${ctx.path}/resources/script/role/roleList.js?v=${ctx.version}"></script>
		<script type="text/javascript">
			$(function() {
				$("#roleName").val("${role.roleName}");
			});
		</script>
	</head>
	<body>
		<div class="main_con">
			<form class="tableform" action="${ctx.path}/platform/pm/queryRoleList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con" id="operation_box">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_SYSTEM_ROLE_ADD">
								<button type="button" class="btn add_btn" onclick="openAddPage();">
									<i class="minicon addoperation_icon"></i>
									<span>新增</span>
								</button>
							</p:auth>
							<p:auth mcode="MENU_SYSTEM_ROLE_ADD">
								<p:auth mcode="MENU_SYSTEM_ROLE_EXPORT">
									<span class="line">|</span>
								</p:auth>
							</p:auth>
							<p:auth mcode="MENU_SYSTEM_ROLE_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/platform/pm/exportRoleList${ctx.bizSuffix}')">
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
							<li><input type="text" id="roleName" name="roleName" class="inputtext" placeholder="角色名称" /></li>
						</ul>
					</div>
					<div class="search_btncon">
						<button type="button" class="btn add_btn" onClick="doSearch()">
							<i class="minicon search_icon"></i>
							<span>查询</span>
						</button>
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
										<th width="100" class="change">角色名称</th>
										<th width="160" class="change">角色描述</th>
										<th width="200" class="operation_th">操作</th>
									</tr>
								</table>
							</div>
						</div>
						<div class="tablelist_tbody">
							<table>
								<c:forEach items="${pageData.data }" var="role" varStatus="status">
									<tr>
										<td title="${status.index + 1 }">${status.index + 1 }</td>
										<td title="${role.roleName }">${role.roleName }</td>
										<td title="${role.descriptions }">${role.descriptions }</td>
										<td class="operation_td">
											<p:auth mcode="MENU_SYSTEM_ROLE_UPDATE">
												<button type="button" class="operationbtn" onclick="openEditPage(${role.roleId},'${role.roleName }','${role.descriptions }');">
													<span>编辑</span>
												</button>
											</p:auth> 
											<p:auth mcode="MENU_SYSTEM_ROLE_DEL">
												<button type="button" class="operationbtn" onclick="delRole(${role.roleId},'${role.roleName }')">
													<span>删除</span>
												</button>
											</p:auth> 
											<p:auth mcode="MENU_SYSTEM_ROLE_AUTH">
												<button type="button" class="operationbtn" onclick="openAuthPage(${role.roleId},'${role.roleName }')">
													<span>权限设置</span>
												</button>
											</p:auth>
										 </td>
									</tr>
								</c:forEach>
								<c:if test="${empty pageData.data}">
									<tr>
										<td align="center" colspan="4">对不起，没有找到相关数据！</td>
									</tr>
								</c:if>
							</table>
						</div>
					</div>
				</div>
				<jsp:include page="/resources/common/page.jsp"></jsp:include>
			</form>
		</div>
		<!-- 新增角色-->
		<div id="add_box" class="add_box mCustomScrollbar_y" style="display:none;">
			<form method="post" id="roleForm" action="${ctx.path}/platform/pm/addRole${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em class="required">*</em>角色名称：
					</h5>
					<div class="add_value">
						<input type="text" id="roleNameInput" name="roleName" maxlength="50" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>角色描述：</h5>
					<div class="add_value">
						<textarea class="textarea resize" id="descriptionsInput" name="descriptions" rows="4" cols="70" style="height: 90px; width: 235px;" maxlength="250"></textarea>
					</div>
				</div>
			</form>
		</div>
		<!-- 编辑角色 -->
		<div id="edit_box" class="add_box mCustomScrollbar_y" style="display:none;">
			<form method="post" id="editRoleForm" action="${ctx.path}/platform/pm/updateRole${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em class="required">*</em>角色名称：
					</h5>
					<div class="add_value">
						<input type="hidden" id="roleId" name="roleId" /> 
						<input type="text" id="roleNameEdit" name="roleName" maxlength="50" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>角色描述：</h5>
					<div class="add_value">
						<textarea class="textarea resize" id="descriptionsEdit" name="descriptions" rows="4" cols="70" style="height: 90px; width: 235px;" maxlength="250"></textarea>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

