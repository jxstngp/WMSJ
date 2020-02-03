<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/log/custLoginLogManage.js?v=1.0"></script>
		
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
			<form class="tableform" action="${ctx.path}/platform/loginlog/queryCustLoginLogList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_MANAGE_LOG_CUST_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/platform/loginlog/exportCustLoginLogList${ctx.bizSuffix}')">
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
									<input type="text" id="custCode" name="custCode" class="inputtext" placeholder="客户学号" value="${custLoginLogVo.custCode}"/>
									<%--<st:commonSelect className="select" name="sysRole.departId" sql="select depart_id as val, depart_name as text from t_sys_depart where status = 1 order by order_num"/>--%>
								</li>
								<li>
									<input type="text" id="wechatName" name="wechatName" class="inputtext" placeholder="微信昵称" value="${custLoginLogVo.wechatName}" />
								</li>
								<li>
									<label class="inputlabel">登陆方式：</label>
									<select class="select" name="loginType">
										<option value="">全部</option>
										<option value= "1" <c:if test="${custLoginLogVo.loginType == 1}">selected</c:if>>手机号</option>
										<option value= "2" <c:if test="${custLoginLogVo.loginType == 2}">selected</c:if>>微信</option>
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
											<th width="120">客户学号</th>
											<th width="120">微信昵称</th>
											<th width="120">绑定微信</th>
											<th width="120">手机号码</th>
											<th width="160">登陆IP</th>
											<th width="120">登陆方式</th>
											<th width="160">登陆时间</th>
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
											<td title="${data.loginIp }">${data.loginIp }</td>
											<td title="${data.type }">${data.type }</td>
											<td title="<fmt:formatDate value='${data.loginDate }' pattern='yyyy-MM-dd HH:mm:ss'/>">
												<fmt:formatDate value='${data.loginDate }' pattern='yyyy-MM-dd HH:mm:ss'/>
											</td>

											<td class="operation_td">
												<button class="operationbtn" type="button" onclick="viewPage(${data.logId});">
													<span>查看</span>
												</button>
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
						登陆IP：
					</h5>
					<div class="add_value">
						<span id="loginIp"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						登陆方式：
					</h5>
					<div class="add_value">
						<span id="type"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						登陆时间：
					</h5>
					<div class="add_value">
						<span id="loginDate"></span>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

