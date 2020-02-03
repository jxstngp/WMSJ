<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>登陆日志管理</title>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
				<script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.0"></script>
		<script type="text/javascript">
			var frameId = window.frameElement && window.frameElement.id || '';
			$(function() {
				loadCriteria();
			});
			//加载查询条件
			function loadCriteria() {
				$("#userName").val("${pmLogLogin.userName }");
				$("#loginName").val("${pmLogLogin.loginName }");
				$("#startDateTime").val("<fmt:formatDate value='${pmLogLogin.startDateTime }' pattern='yyyy-MM-dd HH:mm'/>");
				$("#endDateTime").val("<fmt:formatDate value='${pmLogLogin.endDateTime }' pattern='yyyy-MM-dd HH:mm'/>");
			}
			//执行查询
			function doSearch() {
				$("#pageForm").submit();
				top.progressbar(frameId);
			}
		</script>
	</head>
	<body>
		<div class="main_con">
			<form class="tableform" action="${ctx.path}/platform/pm/queryLoginLogList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
					</div>
				</div>
				<div class="search_box" id="search_box">
					<div class="search_nav">
						<ul>
							<li>
								<input type="text" id="userName" name="userName" class="inputtext" placeholder="用户名称" />
							</li>
							<li>
								<label class="inputlabel">登录时间：</label>
								<input type="text" id="startDateTime" name="startDateTime" class="inputtext" onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endDateTime\')}'})" style="min-width: 120px;" /> 
								<em class="inputto">至</em> 
								<input type="text" id="endDateTime" name="endDateTime" class="inputtext" onclick="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startDateTime\')}'})" style="min-width: 120px;" />
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
										<th width="130" class="change">用户名称</th>
										<%--<th width="130" class="change">登录平台</th>--%>
										<th width="130" class="change">IP地址</th>
										<th width="160" class="change">登录时间</th>
									</tr>
								</table>
							</div>
						</div>
						<div class="tablelist_tbody">
							<table>
								<c:if test="${empty pageData.data}">
									<tr>
										<td align="center" colspan="5">查无此数据！</td>
									</tr>
								</c:if>
								<c:forEach items="${pageData.data }" var="loginLog" varStatus="staus">
									<tr>
										<td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
											${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
										</td>
										<td title="${loginLog.userName }">${loginLog.userName }</td>
<%--										<td>--%>
<%--											<c:if test="${loginLog.logType == 0 }">平台</c:if> --%>
<%--											<c:if test="${loginLog.logType == 1 }">APP</c:if>--%>
<%--											<c:if test="${loginLog.logType == 2 }">接口</c:if>--%>
<%--										</td>--%>
										<td title="${loginLog.loginIp }">${loginLog.loginIp }</td>
										<td><fmt:formatDate value="${loginLog.loginDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<jsp:include page="/resources/common/page.jsp"></jsp:include>
			</form>
		</div>
	</body>
</html>