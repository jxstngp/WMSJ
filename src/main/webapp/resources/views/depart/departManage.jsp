<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/depart/departManage.js?v=1.0"></script>
		
		<script type="text/javascript">
			$(function() {
				loadCriteria();
			});
			//加载查询条件
			function loadCriteria() {
				$("#departName").val("${pmDepart.departName}");
				$("#doSearchParentDepartId   option[value='${pmDepart.parentDepartId}']").attr("selected", true);
			}
		</script>
	</head>
	<body>
		<div class="main_con">
			<form class="tableform" action="${ctx.path}/platform/depart/queryDepartList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_SYSTEM_DEPT_ADD">
								<button type="button" class="btn add_btn" onclick="openAddPage();">
									<i class="minicon addoperation_icon"></i><span>新增</span>
								</button>
								<span class="line">|</span>
							</p:auth>
							<p:auth mcode="MENU_SYSTEM_DEPT_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/platform/depart/exportDepartList${ctx.bizSuffix}')">
									<i class="minicon exportoperation_icon"></i><span>导出</span>
								</button>
							</p:auth>
						</div>
					</div>
				</div>
					<div class="search_box" id="search_box">
						<div class="search_nav">
							<ul>
								<li>
									<input type="text" id="departName" name="departName" class="inputtext" placeholder="部门名称" />
								</li>
								<li>
									<label class="inputlabel">上级部门：</label> 
									<select class="select" name="parentDepartId" id="doSearchParentDepartId" type="select">
										<option value="">全部</option>
										<c:forEach items="${mpDeparts }" var="depart" varStatus="staus">
											<option value="${depart.departId}">${depart.departName }</option>
										</c:forEach>
									</select>
								</li>
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
											<th width="160">部门名称</th>
											<th width="160">部门负责人</th>
											<th width="160" class="change">上级部门</th>
											<th width="160">备注</th>
											<th width="180" class="operation_th">操作</th>
										</tr>
									</table>
								</div>
							</div>
							<div class="tablelist_tbody">
								<table>
									<c:if test="${empty pageData.data}">
										<tr>
											<td align="center" colspan="5">对不起，没有找到相关数据！</td>
										</tr>
									</c:if>
									<c:forEach items="${pageData.data }" var="depart" varStatus="staus">
										<tr>
											<td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
												${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
											</td>
											<td title="${depart.departName }">${depart.departName }</td>
											<td title="${depart.managerUserNmae }">${depart.managerUserNmae }</td>
											<td title="${depart.parentDepartName }">${depart.parentDepartName }</td>
											<td title="${depart.remark }">${depart.remark }</td>
											<td class="operation_td">
												<p:auth mcode="MENU_SYSTEM_DEPT_UPDATE">
													<button class="operationbtn" type="button" onclick="editPage(${depart.departId});">
														<span>编辑</span>
													</button>
												</p:auth> 
												<p:auth mcode="MENU_SYSTEM_DEPT_DEL">
													<button class="operationbtn" type="button" onclick="del(${depart.departId},'${depart.departName }');">
														<span>删除</span>
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
		<div id="addDepart" name="addDepart" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="addDepartForm" action="${ctx.path}/platform/depart/addDepart${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>上级部门：
					</h5>
					<div class="add_value">
						<select class="select" name="parentDepartId" id="parentDepartId" type="select">
						</select>
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>部门名称：
					</h5>
					<div class="add_value">
						<input type="text" id="addDepartName" name="departName" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>部门负责人：
					</h5>
					<div class="add_value">
						<select class="select" name="managerUserId" id="managerUserId" type="select">
						</select>
					</div>
				</div>
				<div class="add_list">
					<h5>备注：</h5>
					<div class="add_value">
						<textarea class="textarea" id="remarkAdd" name="remark" maxlength="100"></textarea>
					</div>
				</div>
			</form>
		</div>
		<!-- 编辑部门 -->
		<div id="editDepart" name="editDepart" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="editDepartForm" action="${ctx.path}/platform/depart/editDepart${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>上级部门：
					</h5>
					<div class="add_value">
						<select class="select" name="parentDepartId" id="editParentDepartId" type="select">
						</select>
					</div>
				</div>
	
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>部门名称：
					</h5>
					<div class="add_value">
						<input type="hidden" id="departId" name="departId"> <input type="text" id="editDepartName" name="departName" maxlength="20" class="inputtext" />
					</div>
				</div>
	
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>部门负责人：
					</h5>
					<div class="add_value">
						<select class="select" name="managerUserId" id="editManagerUserId" type="select">
						</select>
					</div>
				</div>
	
				<div class="add_list">
					<h5>备注：</h5>
					<div class="add_value">
						<textarea class="textarea" id="remarkEdit" name="remark" maxlength="100"></textarea>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

