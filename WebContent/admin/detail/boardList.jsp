<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
 	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>


<!-- ${pageContext.request.contextPath} -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/admin/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/detail/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="${pageContext.request.contextPath}/selectTableByName" method="get">
		<input type="hidden" name="method" value="search">
		<input type="text" name="keyword" title="请输入餐桌名称">
		<input type="submit" value="搜索">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
			<c:forEach items="${BoardList }" var="Board">
				<tr class="TableDetail1">
					<td align="center">${Board.id }&nbsp;</td>
					<td align="center">${Board.tablename }&nbsp;</td>
					<c:choose>
						<c:when test="${Board.tablestatus==0 }">
							<td align="center">空闲</td>
						</c:when>
						<c:otherwise>
							<td align="center">预订</td>
						</c:otherwise>
					</c:choose>
					<td align="center">${Board.orderdate }</td>
					<td>
						<c:if test="${Board.tablestatus==0 }">
						<a href="${pageContext.request.contextPath}/Cancellation?method=update&id=${Board.id }&isBook=0" class="FunctionButton">预订</a>				
						</c:if>
						<c:if test="${Board.tablestatus==1 }">
						<a href="${pageContext.request.contextPath}/Cancellation?method=update&id=${Board.id }&isBook=0" class="FunctionButton">退桌</a>				
						</c:if>
						<a href="${pageContext.request.contextPath}/deleteTable?method=delete&id=${Board.id }" onClick="return delConfirm();"class="FunctionButton">删除</a>				
					</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/admin/detail/saveBoard.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
