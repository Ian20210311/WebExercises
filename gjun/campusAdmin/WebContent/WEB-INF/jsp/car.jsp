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
			<tr><td><h4>最後更新日期: ${data.getUpdateTime()}</h4></td></tr>
			<tr>
				<td><h4>發生時間/狀況</h4></td>
				<td><h4>預計結束時間</h4></td>
			</tr>
			
			<c:forEach var="all" items="${data.getNews()}">
				<tr>
					<td>${all.getChtmessage()}</td>
					<td>${all.getEndtime()}</td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>