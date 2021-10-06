<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>好豬大集合</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.fakeimg {
	height: 200px;
	background: #aaa;
}
</style>
</head>
<body>
	<div class="col-sm-12">
		<a href="index.jsp"><h3>回消息總覽</h3></a>
		<table class="table table-striped">
			<tr>
				<td><h4>名稱</h4></td>
				<td><h4>地址</h4></td>
				<td><h4>營業時間</h4></td>
				<td><h4>商家說明</h4></td>
				<td><h4>商家型態</h4></td>
				<td><h4>營業時間</h4></td>
			</tr>
			<c:forEach var="all" items="${pigsList}">
				<tr>
					<td>${all.getMarket_name()}</td>
					<td>${all.getAddr()}</td>
					<td>${all.getBusiness_week()}</td>
					<td>${all.getContext()}</td>
					<td>${all.getType()}</td>
					<td>${all.getBusiness_hours()} ~ ${all.getBusiness_hurs_end()}</td>
				</tr>
			</c:forEach>
		</table>
		<a href="index.jsp"><h3>回消息總覽</h3></a>
	</div>
</body>
</html>