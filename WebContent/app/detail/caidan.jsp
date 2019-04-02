<%@ page language="java" contentType="text/html; charset=utf-8" 
pageEncoding="UTF-8"%>
﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
<title>无线点餐平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/app/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/app/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/detail/style/css/index_1.css" />
	<link href="${pageContext.request.contextPath}/app/detail/style/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 显示菜品的div -->
			<div id="top">
				<ul>
					<!-- 循环列出餐品 -->
					<c:forEach items="${foodList }" var="FoodExtend" varStatus="var">
						<li>
							<dl>
								<dt>
									<a href="${pageContext.request.contextPath}/selectFoodDetailById?id=${FoodExtend.id }">
										<img width="214px" height="145px" src="${pageContext.request.contextPath}/admin/detail/style/images/${FoodExtend.img }" />
									</a>
								</dt>
								<dd class="f1">
									<a href="${pageContext.request.contextPath}/selectFoodDetailById?id=${FoodExtend.id }">${FoodExtend.foodname }</a>
								</dd>
								<dd class="f2">
									<a href="${pageContext.request.contextPath}/selectFoodDetailById?id=${FoodExtend.id }">&yen;${FoodExtend.price }</a>
								</dd>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!-- 底部分页导航条div -->
			<div id="foot">
					<span
						style="float:left; line-height:53PX; margin-left:-50px; font-weight:bold; ">
						<span style="font-weight:bold">&lt;&lt;</span>
					</span>
				<div id="btn">
					<ul>
						<c:choose>
							<c:when test="${FoodList.size()>0&&FoodList.size()%6==0 }">
								<c:set var="end" value="${FoodList.size()/6-1 }"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="end" value="${FoodList.size()/6 }"></c:set>
							</c:otherwise>
						</c:choose>
						<c:if test="${end }>0">
							<c:forEach var="i" begin="0" end="${end }">
								<li><a href="${pageContext.request.contextPath}/selectFoodByType?index=${i+1}&typename=${FoodList.get(i).foodtype}">${i+1}</a></li>	
							</c:forEach>
						</c:if>
					</ul>
				</div>
					<span style="float:right; line-height:53px; margin-right:10px;  ">
						<a
						href="#"
						style=" text-decoration:none;color:#000000; font-weight:bold">&gt;&gt;</a>
				</span>
			</div>
		</div>
		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath}/selectAllOrderDetail?tableId=${tableId}">
							<img src="${pageContext.request.contextPath}/app/detail/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					<c:forEach items="${cuisineList }" var="FoodTypeExtend">
						<li>
							<a href="${pageContext.request.contextPath}/selectFoodByType?typename=${FoodTypeExtend.typename }">${FoodTypeExtend.typename }</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="${pageContext.request.contextPath}/userSelectFoodByName" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value" /> 
								<input type="hidden" value="selectFood" name="method">
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="#">
									<img src="${pageContext.request.contextPath}/app/detail/style/images/look.gif" />
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>
