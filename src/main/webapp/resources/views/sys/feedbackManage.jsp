<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/sys/feedbackManage.js?v=1.0"></script>
		
		<script type="text/javascript">
			$(function() {
				loadCriteria();
			});
			//加载查询条件
			function loadCriteria() {

			}
		</script>
	</head>
	<body>
		<div class="main_con">
			<form class="tableform" action="${ctx.path}/platform/feedback/queryFeedbackList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_SYSTEM_FEEDBACK_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/platform/feedback/exportFeedbackList${ctx.bizSuffix}')">
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
									<input type="text" id="custCode" name="custCode" class="inputtext" placeholder="客户学号" value="${feedbackVo.custCode}"/>
									<%--<st:commonSelect className="select" name="sysRole.departId" sql="select depart_id as val, depart_name as text from t_sys_depart where status = 1 order by order_num"/>--%>
								</li>
								<li>
									<input type="text" id="wechatName" name="wechatName" class="inputtext" placeholder="微信昵称" value="${feedbackVo.wechatName}" />
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
											<th width="120">客户学号</th>
											<th width="120">微信昵称</th>
											<th width="120">绑定微信</th>
											<th width="120">手机号码</th>
											<th width="280">意见反馈</th>
											<th width="160">反馈时间</th>
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
									<c:forEach items="${pageData.data }" var="data" varStatus="staus">
										<tr>
											<td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
												${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
											</td>
											<td title="${data.custCode }">${data.custCode }</td>
											<td title="${data.wechatName }">${data.wechatName }</td>
											<td title="${data.wechatCode }">${data.wechatCode }</td>
											<td title="${data.mobile }">${data.mobile }</td>
											<td title="${data.feedback }">${data.feedback }</td>
											<td title="<fmt:formatDate value='${data.feedbackDate }' pattern='yyyy-MM-dd HH:mm:ss'/>">
												<fmt:formatDate value='${data.feedbackDate }' pattern='yyyy-MM-dd HH:mm:ss'/>
											</td>

											<td class="operation_td">
												<button class="operationbtn" type="button" onclick="viewPage(${data.feedbackId});">
													<span>查看</span>
												</button>
												<p:auth mcode="MENU_SYSTEM_FEEDBACK_DEL">
													<button class="operationbtn" type="button" onclick="del(${data.feedbackId});">
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


		<!-- 查看广告-->
		<div id="viewAudio" class="add_box mCustomScrollbar_y" >
			<form method="post" id="viewForm">
				<div class="add_list">
					<h5>
						客户学号：
					</h5>
					<div class="add_value">
						<span id="custCode"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						微信昵称：
					</h5>
					<div class="add_value">
						<span id="wechatName"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						手机号码：
					</h5>
					<div class="add_value">
						<span id="mobile"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						绑定微信：
					</h5>
					<div class="add_value">
						<span id="wechatCode"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						意见反馈：
					</h5>
					<div class="add_value">
						<span id="feedback"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						反馈时间：
					</h5>
					<div class="add_value">
						<span id="feedbackDate"></span>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

