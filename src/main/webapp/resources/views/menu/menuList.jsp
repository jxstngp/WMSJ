<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/menu/menuFun.js?v=1.0"></script>
		<script type="text/javascript">
			$(function() {
				loadCriteria();
			});
			//加载查询条件
			function loadCriteria() {
				$("#menuName").val("${menu.menuName}");
			}
		</script>
	</head>
	<body>
		<div class="main_con">
			<form class="tableform" action="${ctx.path}/platform/pm/querFuncMenuList${ctx.noAuthSuffix}?parentId=${menu.parentId}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit">
							<i class="midicon systemset_icon"></i>
							<span>
								<em>系统管理</em>&gt;
								<em>菜单功能管理</em>
							</span>
						</div>
						<div class="operation_info">
							<button type="button" class="btn add_btn" onclick="parent.showAddMenuDiv(true);">
								<i class="minicon addoperation_icon"></i>
								<span>新增</span>
							</button>
						</div>
					</div>
				</div>
				<div class="search_box" id="search_box">
					<div class="search_nav">
						<ul>
							<li>
								<input type="text" id="menuName" name="menuName" class="inputtext" placeholder="功能名称" />
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
										<th width="120">功能名称</th>
										<th width="260" class="change">功能编码</th>
										<th width="120" class="change">功能描述</th>
										<th width="80" class="change">状态</th>
										<th width="200" class="operation_th">操作</th>
									</tr>
								</table>
							</div>
						</div>
						<div class="tablelist_tbody">
							<table>
								<c:if test="${empty pageData.data}">
									<tr>
										<td align="center" colspan="6">对不起，没有找到相关数据！</td>
									</tr>
								</c:if>
								<c:forEach items="${pageData.data }" var="menuFun" varStatus="staus">
									<tr>
										<td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }</td>
										<td title="${menuFun.menuName }">${menuFun.menuName }</td>
										<td title="${menuFun.menuCode }">${menuFun.menuCode }</td>
										<td title="${menuFun.descriptions }">${menuFun.descriptions }</td>
										<td title="${menuFun.state==1?'正常':'禁用'}"><span class="recharge_statue ${menuFun.state==1?'recharge_success':'recharge_failed'}">${menuFun.state==1?'正常':'禁用'}</span></td>
										<td class="operation_td">
											<button class="operationbtn" type="button" onclick="parent.showUpdateMenuDiv(true,${menuFun.menuId})">
												<span>编辑</span>
											</button>
											<button class="operationbtn" type="button" onclick="parent.delFuncMenus(${menuFun.menuId},'${menuFun.menuName}')">
												<span>删除</span>
											</button> 
											<c:if test="${menuFun.state == 1 }">
												<button class="operationbtn" type="button" onclick="parent.disableFuncMenu(1,${menuFun.menuId},'${menuFun.menuName}')">
													<span>禁用</span>
												</button>
											</c:if> 
											<c:if test="${menuFun.state == 2 }">
												<button class="operationbtn" type="button" onclick="parent.disableFuncMenu(2,${menuFun.menuId},'${menuFun.menuName}')">
													<span>启用</span>
												</button>
											</c:if>
											<button class="operationbtn" type="button" onclick="parent.changeFuncMenusSortby(1,${menuFun.menuId},'${menuFun.menuName}');">
												<span>上移</span>
											</button>
											<button class="operationbtn" type="button" onclick="parent.changeFuncMenusSortby(0,${menuFun.menuId},'${menuFun.menuName}');">
												<span>下移</span>
											</button>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<jsp:include page="/resources/common/pageIfr.jsp"></jsp:include>
			</form>
		</div>
	</body>
</html>

