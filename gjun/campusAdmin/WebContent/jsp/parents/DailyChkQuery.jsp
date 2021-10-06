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

<form>

	<div class="col-sm-12">
		
		
		<h3><a href="/campusAdmin/jsp/parents/selchk.jsp">回打卡查詢</a></h3>
	
		<table class="table table-striped">
			
			<tr>
				<td><h5>學生姓名</h5></td>
				<td><h5>學生編號</h5></td>
			</tr>
			<tr>
				<td><h5>${student.getName() }</h5></td>
				<td><h5>${student.getStudentNo() }</h5></td>
			</tr>
			
		</table>
		<br>
		<table class="table table-striped">
						
			<tr>
				<td><h4>日期</h4></td>
				<td><h4>時間</h4></td>
				<td><h4>結果</h4></td>
			</tr>
			
			<c:forEach var="all" items="${data}">
				<tr>
					<td><c:out value="${all.getDate()}"/></td>
					<td><c:out value="${all.getTime()}"/></td>
					<td><c:out value="${all.getStatus()}"/></td>
				</tr>
			</c:forEach>
		</table>

	</div>
	
	<div style="text-align:center">
		<button class="btn btn-default" id="Send" onclick="test()">寄送報表至E-mail</button>
		<br>
		<br>

	</div>
	
</form>

</body>
</html>

<script>

	function test(){

		$.post('dailyChkQueryEmail.jsp', result);

	}

	function result(data, status){
		$('#stuChtShow').html(data);
	}


</script>