<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/script/codegeneration/codegeneration.js?v=${ctx.version}"></script>
	</head>
	<body>
		<div class="main_con">
			<div class="operation_box" id="operation_box">
				<div class="operation_con">
					<div class="currenttit"></div>
				</div>
			</div>
			<div class="search_box code_box">
				<div class="code_from">
					<table width="100%">
						<tbody>
							<tr>
								<th>表名：</th>
								<td class="code_addon">
									<input type="text" class="inputtext" id="tableName" value="" />
									<a href="javascript:void(0);" onclick="codeGeneration.getDbColumnByTableName()" class="addon_value">选择</a>
								</td>
								<th>表描述：</th>
								<td><input type="text" class="inputtext" id="tableNameZN" value="" placeholder="人员信息"></td>
							</tr>
							<tr>
								<th>模块名称：</th>
								<td class="code_addon">
									<input type="text" class="inputtext" id="moduleName" value="" placeholder="ModuleName" />
								</td>
								<th>包路径：</th>
								<td><input type="text" class="inputtext" id="packageUrl" value="" placeholder="com.samton.api"></td>
							</tr>
							<tr>
								<th>作者：</th>
								<td class="code_addon" colspan="3">
									<input type="text" class="inputtext" id="authorName" value="" placeholder="AuthorName" />
								</td>
							</tr>
							<tr>
								<th>需要生成的代码：</th>
								<td class="code_addon">
									<input type="checkbox" value="1" checked="checked" onclick="codeGeneration.getCheckBoxValue(this)">Entity</input>
									<input type="checkbox" value="2" checked="checked" onclick="codeGeneration.getCheckBoxValue(this)">Jsp</input>
									<input type="checkbox" value="3" checked="checked" onclick="codeGeneration.getCheckBoxValue(this)">Controller</input>
									<input type="checkbox" value="4" checked="checked" onclick="codeGeneration.getCheckBoxValue(this)">Service</input>
									<input type="checkbox" value="5" checked="checked" onclick="codeGeneration.getCheckBoxValue(this)">Dao</input>
									<input type="hidden" id="createFileType" value="1,2,3,4,5" />
								</td>
								<th>需要生成的方法：</th>
								<td>
									<input type="checkbox" value="1" checked="checked" onclick="codeGeneration.getCheckBoxValue(this)">增加</input>
									<input type="checkbox" value="2" checked="checked" onclick="codeGeneration.getCheckBoxValue(this)">删除</input>
									<input type="checkbox" value="3" checked="checked" onclick="codeGeneration.getCheckBoxValue(this)">编辑</input>
									<input type="hidden" id="createMethodType" value="1,2,3" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tablelist_box tablelistboxH">
				<div class="tablelist_con">
					<div class="tablelist_theadbox">
						<div class="tablelist_thead">
							<table>
								<tr>
									<th width="60">序号</th>
									<th width="220" class="change">字段名称</th>
									<th width="200" class="change">字段备注</th>
									<th width="160" >控制长度</th>
                                    <th width="80">表单显示</th>
                                    <th width="80">列表显示</th>
									<th width="80">是否查询	</th>
									<th width="200">查询类型</th>
								</tr>
							</table>
						</div>
					</div>
					<div class="tablelist_tbody">
						<table width="100%" id="columnTable"></table>
					</div>
				</div>
			</div>
			<div class="page_box">
				<div class="code_footer">
					<button class="btn add_btn" type="button" onclick="codeGeneration.doGeneration()"><span>生成</span></button>
				</div>
			</div>
		</div>
	</body>
</html>

