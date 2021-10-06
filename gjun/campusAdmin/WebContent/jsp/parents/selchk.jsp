<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<h2>打卡查詢</h2>
				<div>
					<img src="../../img/campus.jpg" class="img-responsive"
						alt="Cinque Terre" width="800" height="50" />
				</div>
				<br /> <br />
				<form class="form-horizontal" role="form" onsubmit="return false;">
					<div id="stuChtShow">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">身分證字號</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="studentid"
								placeholder="請輸入學生身分證" value="A123456789"/>
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">密碼</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="password"
								placeholder="請輸入密碼" value="1234"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default" id="login">登錄</button>
						</div>
					</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!--include bottom frame-->
	<%@include file="../../html/bodybottom.htm"%><!--靜態包含-->
</body>
</html>

<script>
	var msg = "";
	var id = document.getElementById("studentid");
	var pw = document.getElementById("password");

	$(document).ready(start);

	function start() {
		$('#login').click(stuChk.query);
	}

	var stuChk = {
		query : function() {
			if (infoCheck()) {

				$.post('/campusAdmin/jsp/parents/dailyChkQueryServlet', {
					"pw" : pw.value,
					"id" : id.value
				}, stuChk.result);

			} else {
				alert(msg);
				msg = "";
			}
		},
		result : function(data, status) {
			$('#stuChtShow').html(data)
		}
	};

	function infoCheck() {
		var reg = /^[A-Z]+$/;
		if (id.value.length !== 10) {
			msg = msg + "身分證字號須為10碼\n";
		} else if (id.value.charAt(1) == 1){
		} else if (id.value.charAt(1) == 2){
		} else {
			msg = msg + "身分證字號格式錯誤\n";
		}
		if (reg.test(id.value.charAt(0))){
		} else {
			msg = msg + "身分證字號第一碼需為大寫英文\n";
		}
		if (pw.value.length === 0) {
			msg = msg + "密碼不能為空\n";
		}

		if (msg.length == 0) {
			return true;
		} else {
			return false;
		}
	}
</script>
