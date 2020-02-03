<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>操作日志管理</title>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.0"></script>
		<script type="text/javascript">
			var frameId = window.frameElement && window.frameElement.id || '';
			$(function() {
				loadCriteria();
			});
			//加载查询条件
			function loadCriteria() {
				$("#userName").val("${pmLog.userName }");
				$("#loginName").val("${pmLog.loginName }");
				<%--$("#operateType").val("${pmLog.operateType }");--%>
				$("#moduleName").val("${pmLog.moduleName }");
				$("#startDateTime").val("<fmt:formatDate value='${pmLog.startDateTime }' pattern='yyyy-MM-dd HH:mm'/>");
				$("#endDateTime").val("<fmt:formatDate value='${pmLog.endDateTime }' pattern='yyyy-MM-dd HH:mm'/>");
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
			<form class="tableform" action="${ctx.path}/platform/pm/queryOprLogList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
					</div>
				</div>
				<div class="search_box" id="search_box">
					<div class="search_nav">
						<ul>
							<li>
								<input type="text" id="userName" name="userName" class="inputtext" placeholder="操作人员" />
							</li>
							<li>
								<input type="text" id="moduleName" name="moduleName" class="inputtext" placeholder="日志类型" />
							</li>
							<%--<li>
								<label class="inputlabel">登录平台：</label> 
								<select id="operateType" name="operateType" class="select">
									<option value="">全部</option>
									<option value="0">平台</option>
									<option value="1">APP</option>
									<option value="2">接口</option>
								</select>
							</li>--%>
							<li>
								<label class="inputlabel">操作时间：</label> 
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
										<th width="130" class="change">操作人员</th>
										<%--<th width="130" class="change">登录平台</th>--%>
										<th width="130" class="change">日志类型</th>
										<th width="130" class="change">操作描述</th>
										<th width="160" class="change">操作时间</th>
									</tr>
								</table>
							</div>
						</div>
						<div class="tablelist_tbody">
							<table>
								<c:if test="${empty pageData.data}">
									<tr>
										<td align="center" colspan="6">查无此数据！</td>
									</tr>
								</c:if>
								<c:forEach items="${pageData.data }" var="log" varStatus="staus">
									<tr>
										<td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
											${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
										</td>
										<td title="${log.userName }">${log.userName }</td>
										<%--<td>
											<c:if test="${log.operateType == 0 }">平台</c:if> 
											<c:if test="${log.operateType == 1 }">APP</c:if> 
											<c:if test="${log.operateType == 2 }">接口</c:if>
										</td>--%>
										<td title="${log.moduleName }">${log.moduleName }</td>
										<td title="${log.operateDesc}">${log.operateDesc }</td>
										<td><fmt:formatDate value="${log.operateDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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