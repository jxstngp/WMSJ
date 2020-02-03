<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.samton.platform.framework.mybatis.pagination.Pagination"%>
<script type="text/javascript">
	var basePath = ctx.path;
	$().ready(function() {
		var currentPageNo = $("#currentPageNo");
		var totalPages = $("#totalPages");
		var totalRecordNum = $("#totalRecordNum");
		$("#pageFirst").click(function() {
			currentPageNo.val("1");
			$("#pageForm").submit();
		});
		$("#pagePrevious").click(function() {
			if (currentPageNo.val() == 1) {
				return;
			}
			currentPageNo.val(Number(currentPageNo.val()) - 1);
			$("#pageForm").submit();
		});
		$("#pageNext").click(function() {
			if (Number(currentPageNo.val()) >= totalPages.val()) {
				return;
			}
			currentPageNo.val(Number(currentPageNo.val()) + 1);
			$("#pageForm").submit();
		});
		$("#pageLast").click(function() {
			currentPageNo.val(totalPages.val());
			$("#pageForm").submit();
		});

		$("#pageList li[class='num']").click(function() {
			//$("#pageList li").removeClass("active");
			//$(this).addClass("active");
			var nv = parseInt($(this).text());
			if (!isNaN(nv)) {
				currentPageNo.val(nv);
				$("#pageForm").submit();
			}

		});

		$("#selectPage").change(function() {
			currentPageNo.val("1");
			$("#pageForm").submit();
		});

		$("#page_no").keydown(function(e) {
			if (e.keyCode == 13) {
				var nv = parseInt(this.value);
				if (isNaN(nv)) {
					currentPageNo.val(1);
				} else if (Number(nv) > Number(totalPages.val())) {
					currentPageNo.val(totalPages.val());
				} else {
					currentPageNo.val(nv);
				}
			}
		});
		$("#pageGoto").click(function() {
			var nv = parseInt($("#page_no").val());
			if (isNaN(nv) || nv < 0) {
				currentPageNo.val(1);
			} else if (Number(nv) > Number(totalPages.val())) {
				currentPageNo.val(totalPages.val());
			} else {
				currentPageNo.val(nv);
			}

			$("#pageForm").submit();

		});
		// 	try {

		// 		if(top.index){
		// 			closeShade();
		// 		}
		// 	} catch (e) {
		// 	}
	});
</script>

<div class="page_box">
	<div class="page_con">

		<div class="page_nav">
			<ul id="pageList">
				<li class="prev"><a href="javascript:void(0);" id="pagePrevious"><i></i></a></li>

				<%
					Pagination pageData = (Pagination) request.getAttribute("pageData");
					int listnum = 0;
					if (pageData.getTotalPages() != 999999) {
						/* 					if(pageData.getTotalPages()<6){
						 listnum = pageData.getTotalPages();
						 for(int i = 0;i<listnum;i++){
						 out.print("<li class=\"num\"><a href=\"javascript:void(0);\">"+i+"</a></li>");
						 }
						 //out.print("<li class=\"num\"><a href=\"javascript:void(0);\">&hellip;</a></li>");
						 } */
						int start = 1;
						if (pageData.getPage() > 4) {
							start = pageData.getPage() - 1;
							out.print("<li class=\"num\"><a href=\"javascript:void(0);\">1</a></li>");
							out.print("<li class=\"num\"><a href=\"javascript:void(0);\">2</a></li>");
							out.print("<li class=\"num\"><a href=\"javascript:void(0);\">&hellip;</a></li>");
						}
						int end = pageData.getPage() + 1;
						if (end > pageData.getTotalPages()) {
							end = pageData.getTotalPages();
						}
						for (int i = start; i <= end; i++) {
							if (pageData.getPage() == i) {
								out.print("<li class=\"num active\"><a href=\"javascript:void(0);\">" + i + "</a></li>");
							} else {
								out.print("<li class=\"num\"><a href=\"javascript:void(0);\">" + i + "</a></li>");
							}
						}

						if (end < pageData.getTotalPages() - 2) {
							out.print("<li class=\"num\"><a href=\"javascript:void(0);\">&hellip;</a></li>");
						}
						if (end < pageData.getTotalPages() - 1) {
							out.print("<li class=\"num\"><a href=\"javascript:void(0);\">" + (pageData.getTotalPages() - 1) + "</a></li>");
						}
						if (end < pageData.getTotalPages()) {
							out.print("<li class=\"num\"><a href=\"javascript:void(0);\">" + pageData.getTotalPages() + "</a></li>");
						}
					}
				%>

				<li class="next"><a href="javascript:void(0);" id="pageNext"><i></i></a></li>
				<li class="input"><input type="text" class="inputtext" id="page_no" value="${pageData.page}" />
					<button class="page_btn" id="pageGoto">go</button>
				</li>
			</ul>
		</div>
		<span class="totalnum">
			总计<em>${pageData.totalRows == 999999 ? "--" : pageData.totalRows}</em>条，
			每页
			<select id="selectPage" class="select" name="rows">
				<option value="10"
					<c:if test="${pageData.rows==10 }">selected="selected"</c:if>>10</option>
				<option value="20"
					<c:if test="${pageData.rows==20 }">selected="selected"</c:if>>20</option>
				<option value="50"
					<c:if test="${pageData.rows==50 }">selected="selected"</c:if>>50</option>
			</select>条， 分
			<em>
			<%
			 	if (pageData.getTotalPages() == 999999) {
			 		out.print("--");
			 	} else {
			 		out.print(pageData.getTotalPages());
			 	}
			 %>
			</em>
			页显示，当前第
			<em>${pageData.page}</em>页
		</span>

		<!-- 是否是导出-->
		<input type="hidden" name="export" id="export">
		<!-- 是否是表单提交 1为是0为否-->
		<input value="1" name="isForm" type="hidden">
		<!-- 是否是普通查询 1为是0为否 -->

		<input type="hidden" value="${pageData.page}" id="currentPageNo" name="page" title="当前页数" />
		<input type="hidden" value="${pageData.totalPages}" id="totalPages" title="总页数" />
		<input type="hidden" value="${pageData.rows}" id="pageSize" title="每页显示条数" />
		<input type="hidden" value="${pageData.totalRows}" id="totalRecordNum" title="总记录数" />
	</div>
</div>
