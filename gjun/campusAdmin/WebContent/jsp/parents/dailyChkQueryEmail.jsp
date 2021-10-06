<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="/campusAdmin/jsp/parents/dailyChkMailServlet">
	
		<fieldset>
			<legend><h3>E-mail確認</h3></legend>
			
			<label>
				<strong>請確認您的E-mail地址是否如下：</strong><br/><br/>
				<p style="color:red;">${sessionScope.dailyChkQueryStudent.getParentMail() }</p>
				<br/><br/>
			</label>

			<label>
				若需更動E-mail地址，請於下方輸入您新的E-mail：<br/><br/>
				<input type="text" id="newMail"/><br/><br/>
			</label>

		</fieldset>
		
		<input type="submit" id="send" value="確認寄出"/>
	
	</form>


</body>
</html>

<script>
	var newMail = document.getElementById("newMail");

	$(document).ready(start);

	function start(){
		$("#send").click(send);
	}

	function send(){
		
		$.post('/campusAdmin/jsp/parents/dailyChkMailServlet', {
			"newMail" : newMail.value
		}, result);
	}

	function result(data, status){
		$('#stuChtShow').html(data)
	}
</script>