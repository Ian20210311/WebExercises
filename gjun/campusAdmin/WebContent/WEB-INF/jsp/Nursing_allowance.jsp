<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
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
				<td><h4>年度</h4></td>
				<td><h4>件數</h4></td>
				<td><h4>補助金額</h4></td>
			</tr>
			<c:forEach var="all" items="${na}">
				<tr>
						<td>${all.get年度()}</td>
						<td>${all.get件數()}</td>
						<td>${all.get補助金額()}</td>
				</tr>
			</c:forEach>
		</table>
		<a href="index.jsp"><h3>回消息總覽</h3></a>		
	</div>
</body>
</html>