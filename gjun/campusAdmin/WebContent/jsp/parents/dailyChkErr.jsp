<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">身分證字號</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="studentid"
				placeholder="請輸入學生身分證" />
		</div>
	</div>
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">密碼</label>
		<div class="col-sm-8">
			<input type="password" class="form-control" id="password"
				placeholder="請輸入密碼" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<p style="color:red;">${msg }</p>
			<button type="submit" class="btn btn-default" id="login">登錄</button>
		</div>
	</div>

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
				//msg="";

				//$("#loginInfo").text("ok")
				//alert("ok");
				/*var obj=new Object();
				obj.id=id.value;
				obj.pw=pw.value;
				var jstr=JSON.stringify(obj);*/
				//$.post('/campusAdmin/dailyChkQueryServlet', {"data":jstr}, stuChk.result);
				$.post('dailyChkQueryServlet', {
					"pw" : pw.value,
					"id" : id.value
				}, stuChk.result);
				//$.post('/campusAdmin/dailyChkQueryServlet', {"pw":pw.value,"id":id.value});

			} else {
				//$("#loginInfo").text(msg)
				alert(msg);
				msg = "";
			}
		},
		result : function(data, status) {
			$('#stuChtShow').html(data)
		}
	};

	function infoCheck() {
		if (id.value.length !== 10) {
			msg = msg + "身分證字號須為10碼\n";
		}
		//if(){msg=msg+"身分證字號須為1碼英文+9碼數字\n";}
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