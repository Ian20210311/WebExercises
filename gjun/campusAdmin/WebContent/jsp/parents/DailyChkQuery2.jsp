<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>打造百萬人秒級雲端推播系統，成為萬名行動親師生用戶通知關鍵</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
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
	<!--include top frame-->
	<%@include file="../../html/bodytop.htm"%><!--靜態包含-->

	<div class="container">
		<div class="row">
			<!--include left frame-->
			<%@include file="../../html/parentbodyleft.htm"%><!--靜態包含-->
			<div class="col-sm-8">
				<h2>打卡查詢結果</h2>
				<div>
					<img src="../../img/campus.jpg" class="img-responsive"
						alt="Cinque Terre" width="800" height="50" />
				</div>
				<br /> <br />

				<div class="col-sm-12">
					<a href="selchk.jsp"><h3>回打卡查詢</h3></a>
					<table class="table table-striped">

						<tr>
							<td><h4>資料編號</h4></td>
							<td><h4>打卡時間</h4></td>
						</tr>

						<c:forEach var="all" items="${data}">
							<tr>
								<td><c:out value="${all.getDailyNo()}" /></td>
								<td><c:out value="${all.getChkInTime()}" /></td>
							</tr>
						</c:forEach>
					</table>

				</div>

			</div>
		</div>
	</div>


	<!--include bottom frame-->
	<%@include file="../../html/bodybottom.htm"%><!--靜態包含-->
</body>
</html>