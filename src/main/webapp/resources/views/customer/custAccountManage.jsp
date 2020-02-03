<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=1.0"></script>
		<script src="${ctx.path}/resources/script/customer/customerAccountManage.js?v=${ctx.version}"></script>
		<script type="text/javascript">
			$(function() {
			    //加载查询条件省市联动
                loadCityQuery("${customerVo.provinceId}", "${customerVo.cityId}");
			});

			//查询条件省市联动change事件
            function loadCityQuery(provinceId, cityId){
                $('#pageForm #cityId').find("option").remove();
                $.ajax( {
                    type : 'POST',
                    url : ctx.path + '/platform/common/getCityByProvinceId' + ctx.noAuthSuffix,
                    data: ({
                        provinceId : provinceId
                    }),
                    dataType : 'json',
                    success : function(data) {
                        var options = "<option value=''>全部</option>";
                        $.each(data,function(i, row) {
                            if(cityId == row.cityId){
                                options += "<option value='"+row.cityId+"' selected>"+row.cityName+"</option>";
                            }else{
                                options += "<option value='"+row.cityId+"'>"+row.cityName+"</option>";
                            }
                        });
                        $("#pageForm #cityId").html(options);
                    }
                });
            }
		</script>
	</head>
	<body>
		<div class="main_con">
			<form class="tableform" action="${ctx.path}/manage/customer/queryCustomerAccountList${ctx.pageSuffix}" method="post" id="pageForm">
				<div class="operation_box" id="operation_box">
					<div class="operation_con">
						<div class="currenttit"></div>
						<div class="operation_info">
							<p:auth mcode="MENU_MANAGE_CUSTOMER_ACCOUNT_EXPORT">
								<button type="button" class="btn add_btn" onclick="fun_export('${ctx.path}/manage/customer/exportCustomerAccountList${ctx.bizSuffix}')">
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
									<label class="inputlabel">所在城市：</label>
									<st:commonSelect id="provinceId" className="select" name="provinceId" onchange="loadCityQuery(this.value);" defaultValue="" defaultText="全部" selectValue="${customerVo.provinceId}" sql="select province_id as val, province_name as text from t_sys_province order by province_id"/>
								</li>
								<li>
									<select type="select" class="select" id="cityId" name="cityId">
										<option value="">全部</option>
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
											<th width="120">客户姓名</th>
											<th width="160">所在省份</th>
											<th width="160">所在城市</th>
											<th width="160">详细地址</th>
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
											<td title="${data.custName }">${data.custName }</td>
											<td title="${data.provinceName }">${data.provinceName }</td>
											<td title="${data.cityName }">${data.cityName }</td>
											<td title="${data.address }">${data.address }</td>
											<td class="operation_td">
												<p:auth mcode="MENU_MANAGE_CUSTOMER_ACCOUNT_VIEW">
												<button class="operationbtn" type="button" onclick="viewPage(${data.custId});">
													<span>查看</span>
												</button>
												</p:auth>
												<p:auth mcode="MENU_MANAGE_CUSTOMER_ACCOUNT_UPDATE">
												<button class="operationbtn" type="button" onclick="editPage(${data.custId});">
													<span>编辑</span>
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
				<h5>客户姓名：</h5>
				<div class="add_value">
					<span id="custName"></span>
				</div>
			</div>
			<div class="add_list">
				<h5>所在城市：</h5>
				<div class="add_value">
					<span id="provinceName"></span>&nbsp;&nbsp;
					<span id="cityName"></span>
				</div>
			</div>
			<div class="add_list">
				<h5>详细地址：</h5>
				<div class="add_value">
					<span id="address"></span>
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
					<h5>手机号码：</h5>
					<div class="add_value">
						<span id="mobile"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>客户姓名：</h5>
					<div class="add_value">
						<span id="custName"></span>
					</div>
				</div>
				<div class="add_list">
					<h5>所在城市：</h5>
					<div class="add_value">
						<st:commonSelect id="provinceId" className="select" name="provinceId" onchange="loadCityUpdate(this.value);" defaultValue="" defaultText="全部" sql="select province_id as val, province_name as text from t_sys_province order by province_id"/>

						<%--<select type="select" class="select" id="provinceId" name="provinceId" onchange="loadCityUpdate(this.value);">
							<option value="">请选择</option>
						</select>--%>
						<select type="select" class="select" id="cityId" name="cityId">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				<div class="add_list">
					<h5>详细地址：</h5>
					<div class="add_value">
						<input type="text" id="address" name="address" maxlength="200" class="inputtext" />
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

