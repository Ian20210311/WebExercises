<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顯示文化部音樂表演資訊</title>
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
				<td><h4>團體名稱</h4></td>
				<td><h4>說明</h4></td>
				<td><h4>優惠說明</h4></td>
				<td><h4>細項聲明</h4></td>
			</tr>
			<c:forEach var="all" items="${allShow}">
				<tr>
					<td>${all.getTitle()}</td>
					<td>${all.getShowUnit()}</td>
					<td>${all.getDescriptionFilterHtml()}</td>
					<td><a href="${all.getWebSales()}">${all.getTitle()}</a></td>
					<td><a href="#" id="${all.getMyUID()}">${all.getTitle()}</a></td>
					<!--<input id="musicId" name="musicId" type="hidden" value="${all.getMyUID()}">-->
					<div id="music_info_msg"></divi>
				</tr>
			</c:forEach>
		</table>
		<a href="index.jsp"><h3>回消息總覽</h3></a>
	</div>
</body>
</html>
<script>

$(document).ready(start);

function start() {
  //$('#music_detail').click(music_info.queryDetail);
  $("[href='#']").click(prepareServlet);
  //$('#'+p).empty().append(strHtml);
  }

  function prepareServlet()
  {
	var id = $(this).attr("id"); // equals：this.id
	//alert(id);
	$('#'+id).click(music_info.queryDetail(id));
  }

var music_info = 
{
	queryDetail : function(id)
	{
		//alert('AAAAAAAAAAAAA - >'+ id);
        //var btAdd=$("#addsubmit").val();
        //$.post("addcartServlet",{"item":item,"submit":btAdd},addresult)

        //$.post("primaryServlet",{"addr":address} ,addresult)
		$.post("musicDetailServlet", {"id":id},music_info.detailResult);
	},
	detailResult : function(jsonData, status)
	{
		$('#music_info_msg').html(jsonData);
	}
};  
</script>