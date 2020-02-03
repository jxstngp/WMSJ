<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<%@taglib prefix="p" uri="/custom-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="${ctx.path}/resources/js/common/DataForm.js?v=${ctx.version}"></script>
		<script src="${ctx.path}/resources/script/role/roleMenu.js?v=${ctx.version}"></script>
	</head>
	<body>
		<div id="auth_box">
			<div id="character_box" class="character_box mCustomScrollbar_y">
				<div class="character_con">
					<c:forEach items="${menu.subMenus }" var="supMenu">
						<div class="character_tit">
							<h2>${supMenu.menuName }</h2>
							<label class="checkbox" id="all_${supMenu.menuId }"><em>全选</em></label>
							<label class="checkbox menuId <c:if test="${supMenu.isCheck }">checked</c:if>" id="sup_${supMenu.menuId }" style="display: none;"></label>
						</div>
						<div class="character_content" id="content_${supMenu.menuId }">
							<c:forEach items="${supMenu.subMenus }" var="subMenu">
								<label class="checkbox menuId <c:if test="${subMenu.isCheck }">checked</c:if>" id="sub_${subMenu.menuId }" style="display: none;"></label>
								<c:if test="${subMenu.menuType == 0 }">
									<c:forEach items="${subMenu.subMenus }" var="secSubMenu">
										<div class="add_list">
											<h5>
												<label class="checkbox menuId <c:if test="${secSubMenu.isCheck }">checked</c:if>" id="sub_${secSubMenu.menuId }" style="display: none;"></label>
												<span class="wildcard"></span>${secSubMenu.menuName }：
											</h5>
											<div class="add_value" id="value_${secSubMenu.menuId }">
												<c:forEach items="${secSubMenu.subMenus }" var="function">
													<label class="checkbox menuId <c:if test="${function.isCheck }">checked</c:if>" id="menu_${supMenu.menuId }_${subMenu.menuId }_${secSubMenu.menuId }_${function.menuId }"><em>${function.menuName }</em></label>
												</c:forEach>
											</div>
										</div>
									</c:forEach>
								</c:if>
								<c:if test="${subMenu.menuType != 0 }">
									<div class="add_list">
										<h5>
											<span class="wildcard"></span>${subMenu.menuName }：
										</h5>
										<div class="add_value" id="value_${subMenu.menuId }">
											<c:forEach items="${subMenu.subMenus }" var="function">
												<label class="checkbox menuId <c:if test="${function.isCheck }">checked</c:if>" id="menu_${supMenu.menuId }_${subMenu.menuId }_${subMenu.menuId }_${function.menuId }"><em>${function.menuName }</em></label>
											</c:forEach>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</body>
</html>

