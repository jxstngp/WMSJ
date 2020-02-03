<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/customer/customerInfoManage.js?v=${ctx.version}"></script>
		<script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=1.0"></script>
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
			<form class="tableform" action="${ctx.path}/manage/customer/queryCustomerInfoList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/customer/exportCustomerInfoList${ctx.bizSuffix}')">
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
									<input type="text" id="custCode" name="custCode" class="inputtext" placeholder="客户学号" value="${customerVo.custCode}"/>
								</li>
								<li>
									<input type="text" id="wechatName" name="wechatName" class="inputtext" placeholder="微信昵称" value="${customerVo.wechatName}" />
								</li>
								<li>
									<input type="text" id="mobile" name="mobile" class="inputtext" placeholder="手机号码" value="${customerVo.mobile}" />
								</li>
								<li>
									<label class="inputlabel">注册日期：</label>
									<input type="text" id="registerDateBegin" name="registerDateBegin" class="inputtext" onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'registerDateEnd\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${customerVo.registerDateBegin}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
									<em class="inputto">至</em>
									<input type="text" id="registerDateEnd" name="registerDateEnd" class="inputtext" onclick="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'registerDateBegin\')}'})" style="min-width: 120px;" value="<fmt:formatDate value='${customerVo.registerDateEnd}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
								</li>
								<li>
									<label class="inputlabel">注册类型：</label>
									<select class="select" name="registerType" id="registerType" type="select">
										<option value="">全部</option>
										<option value="0" <c:if test="${customerVo.registerType == 0}">selected</c:if> >手机号码</option>
										<option value="1" <c:if test="${customerVo.registerType == 1}">selected</c:if>>微信</option>
									</select>
								</li>
								<li>
									<label class="inputlabel">禁用状态：</label>
									<select class="select" name="isForbid" id="isForbid" type="select">
										<option value="">全部</option>
										<option value="0" <c:if test="${customerVo.isForbid == 0}">selected</c:if> >正常</option>
										<option value="1"<c:if test="${customerVo.isForbid == 1}">selected</c:if> >已禁用</option>
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
											<th width="160">客户学号</th>
											<th width="160">微信昵称</th>
											<th width="160">手机号码</th>
											<th width="120">绑定微信</th>
											<th width="160">注册类型</th>
											<th width="160">注册日期</th>
											<th width="180" class="operation_th">操作</th>
										</tr>
									</table>
								</div>
							</div>
							<div class="tablelist_tbody">
								<table>
									<c:if test="${empty pageData.data}">
										<tr>
											<td align="center" colspan="10">对不起，没有找到相关数据！</td>
										</tr>
									</c:if>
									<c:forEach items="${pageData.data }" var="data" varStatus="staus">
										<tr>
											<td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
												${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
											</td>
											<td title="${data.custCode }">${data.custCode }</td>
											<td title="${data.wechatName }">${data.wechatName }</td>
											<td title="${data.mobile }">${data.mobile }</td>
											<td title="${data.wechatCode }">${data.wechatCode }</td>
											<td title="${data.registerTypeCN }">${data.registerTypeCN }</td>
											<%--<td title="<c:if test="${data.registerType == 0}">手机号码</c:if><c:if test="${data.registerType == 1}">微信</c:if>">
												<c:if test="${data.registerType == 0}">手机号码</c:if>
												<c:if test="${data.registerType == 1}">微信</c:if>
											</td>--%>
											<td title="<fmt:formatDate value='${data.registerDate }' pattern='yyyy-MM-dd HH:mm:ss'/>">
												<fmt:formatDate value='${data.registerDate }' pattern='yyyy-MM-dd HH:mm:ss'/>
											</td>
											<td class="operation_td">
												<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_VIEW">
												<button class="operationbtn" type="button" onclick="viewPage(${data.custId});">
													<span>查看</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_UPDATE">
												<button class="operationbtn" type="button" onclick="editPage(${data.custId});">
													<span>编辑</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_CUSTOMER_INFO_EXPORT">
												<button class="operationbtn" type="button" onclick="setPage(${data.custId}, ${data.isForbid}, '${data.custCode}');">
													<span>
														<c:if test="${data.isForbid == 0}">禁用</c:if>
														<c:if test="${data.isForbid == 1}">启用</c:if>
													</span>
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

		<!-- 查看 -->
		<div id="viewId" class="add_box mCustomScrollbar_y" >
			<form method="post" id="viewForm">
			<div class="add_list">
				<h5>客户学号：</h5>
				<div class="add_value">
					<span id="custCode"></span>
				</div>
			</div>
			<div class="add_list">
				<h5>微信昵称：</h5>
				<div class="add_value">
					<span id="wechatName"></span>
				</div>
			</div>
			<div class="add_list">
				<h5>手机号码：</h5>
				<div class="add_value">
					<span id="mobile"></span>
				</div>
			</div>
			<div class="add_list">
				<h5>绑定微信：</h5>
				<div class="add_value">
					<span id="wechatCode"></span>
				</div>
			</div>
			<div class="add_list">
				<h5>注册类型：</h5>
				<div class="add_value">
					<span id="registerTypeCN"></span>
				</div>
			</div>
			<div class="add_list">
				<h5>注册日期：</h5>
				<div class="add_value">
					<span id="registerDate"></span>
				</div>
			</div>
			</form>
		</div>

		<!-- 编辑 -->
		<div id="editId" name="editName" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="editForm" action="${ctx.path}/manage/customer/editCustomer${ctx.bizSuffix}">
				<input type="hidden" id="custId" name="custId" />
				<input type="hidden" id="custCodeTmp" name="custCode" />
				<div class="add_list">
					<h5>客户学号：</h5>
					<div class="add_value">
						<span id="custCode"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>微信昵称：</h5>
					<div class="add_value">
						<span id="wechatName"></span>
					</div>
				</div>
				<div class="add_list">
					<h5><em style="color: red;">*</em>手机号码：</h5>
					<div class="add_value">
						<input type="text" id="mobile" name="mobile" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5><em style="color: red;">*</em>绑定微信：</h5>
					<div class="add_value">
						<input type="text" id="wechatCode" name="wechatCode" maxlength="30" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>注册类型：</h5>
					<div class="add_value">
						<span id="registerTypeCN"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>注册日期：</h5>
					<div class="add_value">
						<span id="registerDate"></span>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

