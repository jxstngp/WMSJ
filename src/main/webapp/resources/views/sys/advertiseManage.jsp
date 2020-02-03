<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/sys/advertiseManage.js?v=1.0"></script>
		
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
			<form class="tableform" action="${ctx.path}/platform/advertise/queryAdvertiseList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_SYSTEM_ADVERTISE_ADD">
								<button type="button" class="btn add_btn" onclick="openAddPage();">
									<i class="minicon addoperation_icon"></i><span>新增</span>
								</button>
								<span class="line">|</span>
							</p:auth>
							<p:auth mcode="MENU_SYSTEM_ADVERTISE_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/platform/advertise/exportAdvertiseList${ctx.bizSuffix}')">
									<i class="minicon exportoperation_icon"></i><span>导出</span>
								</button>
							</p:auth>
						</div>
					</div>
				</div>
					<div class="search_box" id="search_box">
						<div class="search_nav">
							<ul>
								<%--<li>
									<label class="inputlabel">：</label>
									<select class="select" name="position" id="doSearchParentAdvertiseId" type="select">
										<option value="">全部</option>
										<c:forEach items="${advertiseVo }" var="depart" varStatus="staus">
											<option value="${depart.departId}">${depart.departName }</option>
										</c:forEach>
									</select>
								</li>--%>
								<li>
									<label class="inputlabel">所属平台：</label>
									<select class="select" name="position">
										<option value="">全部</option>
										<option value= "0" <c:if test="${advertiseVo.position == 0}">selected</c:if>>首页-顶部区域</option>
										<option value= "1" <c:if test="${advertiseVo.position == 1}">selected</c:if>>首页-中部区域</option>
										<%--<option value="2" <c:if test="${advertiseVo.position == 2}">selected</c:if>>首页-顶部区域</option>--%>
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
											<th width="140">广告位置</th>
											<th width="160">广告图片</th>
											<th width="180">链接地址</th>
											<th width="190">备注</th>
											<th width="130">是否禁用</th>
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
											<td title="${data.posited }">${data.posited }</td>
											<td title="${data.advertiseImg }">${data.advertiseImg }</td>
											<td title="${data.link }">${data.link }</td>
											<td title="${data.remark }">${data.remark }</td>
											<td title="${data.forbid }">${data.forbid }</td>
											<td class="operation_td">

												<button class="operationbtn" type="button" onclick="viewPage(${data.advertiseId});">
													<span>查看</span>
												</button>
												<p:auth mcode="MENU_SYSTEM_ADVERTISE_UPDATE">
													<button class="operationbtn" type="button" onclick="editPage(${data.advertiseId});">
														<span>编辑</span>
													</button>
												</p:auth> 
												<p:auth mcode="MENU_SYSTEM_ADVERTISE_DEL">
													<button class="operationbtn" type="button" onclick="del(${data.advertiseId},'${data.posited}');">
														<span>删除</span>
													</button>
												</p:auth>
												<p:auth mcode="MENU_SYSTEM_ADVERTISE_DIS_ENABLE">
													<c:if test="${data.isForbid == 0 }">
														<button class="operationbtn" type="button" onclick="disAndEnableAccount(${data.advertiseId },'${data.posited }',1)">
															<span>禁用</span>
														</button>
													</c:if>
													<c:if test="${data.isForbid == 1 }">
														<button class="operationbtn" type="button" onclick="disAndEnableAccount(${data.advertiseId },'${data.posited }',0)">
															<span>启用</span>
														</button>
													</c:if>
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
						广告位置：
					</h5>
					<div class="add_value">
						<span id="posited"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						广告图片：
					</h5>
					<div class="add_value">
						<span id="advertiseImg"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						链接地址：
					</h5>
					<div class="add_value">
						<span id="link"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						备注：
					</h5>
					<div class="add_value">
						<span id="remark"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>
						是否禁用：
					</h5>
					<div class="add_value">
						<span id="forbid"></span>
					</div>
				</div>
			</form>
		</div>

		<!-- 新增广告 -->
		<div id="addAdvertise" name="addAdvertise" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="addAdvertiseForm" action="${ctx.path}/platform/advertise/addAdvertise${ctx.bizSuffix}">
				<%--<div class="add_list">
					<h5>
						<em style="color: red;">*</em>广告位置：
					</h5>
					<div class="add_value">
						<select class="select" name="position" id="addPosition" type="select">
						</select>
					</div>
				</div>--%>
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>广告位置：
					</h5>
					<div class="add_value">

						<select class="select" name="position" id="addPosition" type="select">
						<option value="">请选择</option>
						<option value="0">首页-顶部区域</option>
						<option value="1">首页-中部区域</option>
						<option value="2">首页-底部区域</option>
						</select>
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>广告图片：
					</h5>
					<div class="add_value">
						<input type="text" id="addAdvertiseImg" name="advertiseImg" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>链接地址：
					</h5>
					<div class="add_value">
						<input type="text" id="addLink" name="link" maxlength="22" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>备注：</h5>
					<div class="add_value">
						<textarea class="textarea" id="addRemark" name="remark" maxlength="100"></textarea>
					</div>
				</div>
			</form>
		</div>


		<!-- 编辑广告 -->
		<div id="editAdvertise" name="editAdvertise" class="add_box mCustomScrollbar_y" data-mcs-theme="dark" style="display:none;">
			<form method="post" id="editAdvertiseForm" action="${ctx.path}/platform/advertise/editAdvertise${ctx.bizSuffix}">
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>广告位置：
					</h5>
					<div class="add_value">
					    <input type="text" id="editPositioned" name="positioned" class="inputtext" readonly="readonly">
						<input type="hidden" id="editPosition" name="position" class="inputtext" readonly="readonly">
					</div>
					<%--<div class="add_value">
						<select class="select" name="position" id="editPosition" type="select">
							<option value="">请选择</option>
							<option value="0">首页-顶部区域</option>
							<option value="1">首页-中部区域</option>
							<option value="2">首页-底部区域</option>
						</select>
					</div>--%>
				</div>
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>广告图片：
					</h5>
					<div class="add_value">
						<input type="text" id="editAdvertiseImg" name="advertiseImg" maxlength="20" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>
						<em style="color: red;">*</em>链接地址：
					</h5>
					<div class="add_value">
						<input type="hidden" id="advertiseId" name="advertiseId"><input type="text" id="editLink" name="link" maxlength="22" class="inputtext" />
					</div>
				</div>
				<div class="add_list">
					<h5>备注：</h5>
					<div class="add_value">
						<textarea class="textarea" id="editRemark" name="remark" maxlength="100"></textarea>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

