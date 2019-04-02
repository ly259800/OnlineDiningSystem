<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app/detail/style/css/index.css" />
	<!-- <script type="text/javascript">
		// 通知服务员结账
		function callPay(node) {
			var orderId = node.lang;
			window.location.href = "app/detail/jiezhang.jsp";
		}
	</script> -->
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 	</tr>
				 	<c:set var="countNum" value="0"></c:set>
				 	<c:forEach items="${OrderDetails }" var="OrderDetailExtend">
					<tr height="60">
					 		<td align="center" width="20%">${OrderDetailExtend.foodExtend.foodname }</td>
					 		<td align="center" width="20%">￥${OrderDetailExtend.foodExtend.price }</td>
					 		<td align="center" width="20%">${OrderDetailExtend.foodcount }</td>
					 		<td align="center" width="20%">${OrderDetailExtend.foodExtend.price*OrderDetailExtend.foodcount }</td>
				 		</tr>
				 		<c:set var="countNum" value="${countNum+OrderDetailExtend.foodExtend.price*OrderDetailExtend.foodcount }"></c:set>
				 	</c:forEach>
					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;</span>
							<label
								id="counter" style="font-size:36px">${countNum }</label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							<input type="button" value="结账" class="btn_next" lang="" onclick='window.location.href ="${pageContext.request.contextPath}/appCheckOut?countNum=${countNum }&tableId=${tableId }"' />
						</td>
					</tr>
				</table>
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
