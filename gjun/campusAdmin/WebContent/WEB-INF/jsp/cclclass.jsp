<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="col-sm-12">
	<a href="index.jsp"><h3>回消息總覽</h3></a>
		<table class="table table-striped">
			<tr>
				<td><h4>課程名稱</h4></td>
				<td><h4>開課教師</h4></td>


				<td><h4>課程簡介</h4></td>

				<td><h4>上課時數</h4></td>
			</tr>
			<c:forEach var="all" items="${data}">
				<tr>
					<td>${all.getCourse_name()}</td>
					<td>${all.getTeacher()}</td>


					<td>${all.getCourse_description()}</td>

					<td>${all.getHours()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>