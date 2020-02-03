<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>数据字典类型列表</title>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/dict/dictType.js?v=1.0"></script>
		<script type="text/javascript">
			var frameId = window.frameElement && window.frameElement.id || '';
			$(function() {
				loadCriteria();
			});
			//加载查询条件
			function loadCriteria() {
				$("#typeName").val("${dictType.typeName }");
			}
		</script>
	</head>
	<body>
		<div class="main_con">
			<form class="tableform" action="${ctx.path}/platform/dict/queryDictTypeList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_SYSTEM_DICT_TYPE_ADD">
								<button type="button" class="btn add_btn" onclick="openAddPage();">
									<i class="minicon addoperation_icon"></i>
									<span>新增</span>
								</button>
							</p:auth>
						</div>
					</div>
				</div>
				<div class="search_box" id="search_box">
					<div class="search_nav">
						<ul>
							<li>
								<input type="text" id="typeName" name="typeName" class="inputtext" placeholder="类型名称" />
							</li>
						</ul>
					</div>
					<div class="search_btncon">
						<p:auth mcode="MENU_SYSTEM_DICT_TYPE_SEARCH">
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
										<th width="80">序号</th>
										<th width="280" class="change">类型名称</th>
										<th width="280" class="change">备注</th>
										<th width="280" class="change">创建时间</th>
										<th width="280" class="operation_th">操作</th>
									</tr>
								</table>
							</div>
						</div>
						<div class="tablelist_tbody">
							<table>
								<c:if test="${empty pageData.data}">
									<tr>
										<td align="center" colspan="4">查无此数据！</td>
									</tr>
								</c:if>
								<c:forEach items="${pageData.data }" var="dictType" varStatus="staus">
									<tr>
										<td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
											${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
										</td>
										<td title="${dictType.typeName }">${dictType.typeName }</td>
										<td title="${dictType.remark }">${dictType.remark }</td>
										<td><fmt:formatDate value="${dictType.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td class="operation_td">
											<p:auth mcode="MENU_SYSTEM_DICT_TYPE_UPDATE">
												<button class="operationbtn" type="button" onclick="openEditPage(${dictType.typeId});">
													<span>编辑</span>
												</button>
											</p:auth> 
											<p:auth mcode="MENU_SYSTEM_DICT_TYPE_DEL">
												<button class="operationbtn" type="button" onclick="delDictType(${dictType.typeId},'${dictType.typeName }');">
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
	
		<!-- 新增数据字典类型 -->
		<div id="dictTypeAdd" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="dictTypeAddForm" action="${ctx.path}/platform/dict/addDictType${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em class="required">*</em>类型名称：
					</h5>
					<div class="add_value">
						<input type="text" id="typeNameAdd" name="typeName" maxlength="50" class="inputtext" />
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
	
		<!-- 编辑数据字典类型 -->
		<div id="dictTypeEdit" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="dictTypeEditForm" action="${ctx.path}/platform/dict/updateDictType${ctx.bizSuffix}">
				<input type="hidden" id="typeId" name="typeId" />
				<div class="add_list">
					<h5>
						<em class="required">*</em>类型名称：
					</h5>
					<div class="add_value">
						<input type="text" id="typeNameEdit" name="typeName" maxlength="50" class="inputtext" />
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