<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	新北市立案短期補習班基本資料</title>
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
				<td><h4>地區縣市</h4></td>
				<td><h4>短期補習班名稱</h4></td>
				<td><h4>短期補習班類別</h4></td>
				<td><h4>地址</h4></td>
				<td><h4>立案時間</h4></td>
				<td><h4>各地短期補習班數量</h4></td>			
			</tr>
			<c:forEach var="all" items="${afterschoolList}">
				<tr>
					<td>${all.getCity()}</td>
					<td>${all.getName()}</td>
					<td>${all.getKind()}</td>
					<td>${all.getAddress()}</td>
					<td>${all.getTime()}</td>
					<td>${all.getNumber()}</td>
										
				</tr>
			</c:forEach>
		</table>
		<a href="index.jsp"><h3>回消息總覽</h3></a>
	</div>
</body>
</html>