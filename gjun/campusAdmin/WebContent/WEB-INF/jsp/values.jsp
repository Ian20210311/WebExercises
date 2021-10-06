<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Water Info</title>
</head>
<body>
<div class="col-sm-12">
		<a href="index.jsp"><h3>回消息總覽</h3></a>
		<table class="table table-striped">
			<tr>
				<td><h4>淨水塲名稱</h4></td>
				<td><h4>PH值</h4></td>
				<td><h4>濁度</h4></td>
				<td><h4>餘氯</h4></td>
				
			</tr>
			<c:forEach var="all" items="${WaterList}">
				<tr>
					<td>${all.getStation_name()}</td>
					<td>${all.getpH_value()}</td>
					<td>${all.getTurbidity()}</td>
					<td>${all.getResidual_chlorine()}</td>
				</tr>
			</c:forEach>
		</table>
		<a href="index.jsp"><h3>回消息總覽</h3></a>
	</div>
</body>
</html>