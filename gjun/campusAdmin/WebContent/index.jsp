<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>打造百萬人秒級雲端推播系統，成為萬名行動親師生用戶通知關鍵</title>
		<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<style>
			.fakeimg {
				height: 200px;
				background: #aaa;
			}
		</style>
	</head>


	<body>
		<!--include top frame-->
		<%@include file="html/bodytop.htm" %>
			<!--靜態包含-->
			<div class="container">
				<div class="row">
					<!--include left frame-->
					<%@include file="html/bodyleft.htm" %>
						<!--靜態包含-->
						<div class="col-sm-8">
							<h2>活動訊息</h2>
							<div>
								<img src="img/campus.jpg" class="img-responsive" alt="Cinque Terre" width="800"
									height="50">
							</div>
							<ul>
								<!-- morris  start-->
								<li><a href="#" id="morris_info1">m1創作音樂得獎作品</a></li>
								<div id="morris_msg1"></div>
								<li><a href="#" id="morris_info2">m2教育中心服務內容</a></li>
								<div id="morris_msg2"></div>
								<li><a href="#" id="morris_info3">m3看護補助</a></li>
								<div id="morris_msg3"></div>
								<!-- morris  end-->
								<li><a href="#" id="water_info">台灣自來水公司產水監控資料</a></li>
								<div id="water_msg"></divi>
								<li><a href="#" id="study_info">客語結合十二年國教校訂課程</a></li>
								<div id="study_msg"></divi>
								<li><a href="#" id="afterschool_info">新北市立案短期補習班基本資料</a></li>
								<div id="afterschool_msg"></divi>

								<li><a href="#" id="haka_a">客家委員會組織人力概況</a></li>
								<div id="hakaamsg"></divi>
								<li><a href="#" id="haka_b">客家委員會委員</a></li>
								<div id="hakabmsg"></divi>
								<li><a href="#" id="haka_c">客家委員會客家網路廣播</a></li>
								<div id="hakacmsg"></divi>

								<li><a href="#" id="car_info">臺北市即時交通訊息</a></li>
								<div id="carmsg"></divi>
								<li><a href="#" id="CCLclass">客家委員會客語文學課程表</a></li>
								<div id="CCLclassmsg"></divi>
								<li><a href="#" id="study108">新竹縣108年公務人員參訓人數</a></li>
								<div id="study108msg"></divi>

								<!--<li><a href="https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=tR9TIFWlvquB" id="jason1">客家委員會好客小學堂</a></li>-->
								<li><a href="#" id="pig_info">臺灣豬證明標章商家資料</a></li>
								<div id="pigmsg"></divi>
								<!--<li><a href="https://cloud.hakka.gov.tw/Pub/Opendata/DTST20200800001.json" id="jason1">客家委員會好客小學堂</a></li>-->
								<li><a href="#" id="haka_info">客家委員會好客小學堂</a></li>
								<div id="hakamsg"></divi>
								<li><a href="#"	id="music_info">文化部本部及所屬各級機關(構)、以及其他公、民營單位最新音樂劇場表演訊息</a></li>
								<div id="music_msg"></divi>

								<li><a href="#" id="movie_info">看影片學客語</a></li>
								<div id="hakamovie"></divi>
								<li><a href="#" id="class_info">客語資訊課程表</a></li>
								<div id="hakaclass"></divi>
								<li><a href="#" id="cert_info">桐花文學獎</a></li>
								<div id="hakacert"></divi>

							</ul>
						</div>
				</div>
			</div>

			<!--include bottom frame-->
			<%@include file="html/bodybottom.htm" %>
				<!--靜態包含-->
	</body>

	</html>
	<script>
		$(document).ready(start);

		function start() {

			$('#haka_info').click(haka.queryHaKa);
			$('#music_info').click(music.queryMusic);
			$('#pig_info').click(allpigs.queryPigs);

			$('#water_info').click(allwater.querywater);
			$('#study_info').click(allstudy.querystudy);
			$('#afterschool_info').click(allafterschool.queryafterschool);

			$('#haka_a').click(hakaa.queryHaKaa);
			$('#haka_b').click(hakab.queryHaKab);
			$('#haka_c').click(hakac.queryHaKac);

			$('#car_info').click(carf.query);
			$('#CCLclass').click(CCLclassf.query);
			$('#study108').click(study108f.query);

			$("#morris_info1").click(info1.query);
			$("#morris_info2").click(info2.query);
			$("#morris_info3").click(info3.query);

			$("#movie_info").click(hakam.query);
			$("#class_info").click(hakac.query);
			$("#cert_info").click(hakace.query);

		}



		//m1創作音樂得獎作品
		var info1 = {
			query: function () {
				$.post("musiccreationServlet", info1.musiccreationResult);
			},
			musiccreationResult: function (jsonData, status) {
				$('#morris_msg1').html(jsonData);
			}
		};
		//m2教育中心服務內容
		var info2 = {
			query: function () {
				$.post("CenterServiceContentServlet", info2.CenterServiceContentResult);
			},
			CenterServiceContentResult: function (jsonData, status) {
				$('#morris_msg2').html(jsonData);
			}
		};
		//m3看護補助
		var info3 = {
			query: function () {
				$.post("NursingAllowanceServlet", info3.NursingAllowanceResult);
			},
			NursingAllowanceResult: function (jsonData, status) {
				$('#morris_msg3').html(jsonData);
			}
		};



		//客家委員會好客小學堂
		var haka = {
			queryHaKa: function () {
				//var address = $('#jason1').attr("href");
				//var address = $('#jason1').attr("target");

				//var item=$("#item").val();
				//var btAdd=$("#addsubmit").val();
				//$.post("addcartServlet",{"item":item,"submit":btAdd},addresult)

				//$.post("primaryServlet",{"addr":address} ,addresult)
				$.post("hakaServlet", haka.hakaResult);
			},
			hakaResult: function (jsonData, status) //顯示客家委員會好客小學堂
			{
				$('#hakamsg').html(jsonData);
			}
		};

		//音樂劇場表演訊息
		var music = {
			queryMusic: function () {
				$.post("musicServlet", music.musicResult);
			},
			musicResult: function (jsonData, status) //顯示音樂劇場表演訊息
			{
				$('#music_msg').html(jsonData);
			}
		};

		//臺灣豬證明標章商家資料
		var allpigs = {
			queryPigs: function () {
				$.post("pigServlet", allpigs.pigResult);
			},
			pigResult: function (jsonData, status) {
				$('#pigmsg').html(jsonData);
			}
		};

		//台灣自來水公司產水監控資料
		var allwater = {
			querywater: function () {
				$.post("waterServlet", allwater.waterResult);
			},
			waterResult: function (jsonData, status) {
				$('#water_msg').html(jsonData);
			}
		};
		//客語結合十二年國教校訂課程
		var allstudy = {
			querystudy: function () {
				$.post("studyServlet", allstudy.studyResult);
			},
			studyResult: function (jsonData, status) {
				$('#study_msg').html(jsonData);
			}
		};

		//新北市立案短期補習班基本資料
		var allafterschool = {
			queryafterschool: function () {
				$.post("afterschoolservlet", allafterschool.afterschoolResult);
			},
			afterschoolResult: function (jsonData, status) {
				$('#afterschool_msg').html(jsonData);
			}
		};

		//客家委員會組織人力概況
		var hakaa =
		{
			queryHaKaa: function () {
				$.post("hakaaServlet", hakaa.hakaaResult);
			},
			hakaaResult: function (jsonData, status) //顯示音樂劇場表演訊息
			{
				$('#hakaamsg').html(jsonData);
			}
		};
		//客家委員會委員
		var hakab =
		{
			queryHaKab: function () {
				$.post("hakabServlet", hakab.hakabResult);
			},
			hakabResult: function (jsonData, status) //顯示音樂劇場表演訊息
			{
				$('#hakabmsg').html(jsonData);
			}
		};
		//客家文化
		var hakac =
		{
			queryHaKac: function () {
				$.post("hakacServlet", hakac.hakacResult);
			},
			hakacResult: function (jsonData, status) //顯示音樂劇場表演訊息
			{
				$('#hakacmsg').html(jsonData);
			}
		};


		//臺北市即時交通訊息
		var carf = {
			query: function () {
				$.post("carServlet", carf.Result);
			},
			Result: function (jsonData, status) //顯示音樂劇場表演訊息
			{
				$('#carmsg').html(jsonData);
			}
		};

		//客家委員會客語文學課程表
		var CCLclassf = {
			query: function () {
				$.post("CCLclassServlet", CCLclassf.Result);
			},
			Result: function (jsonData, status) //顯示音樂劇場表演訊息
			{
				$('#CCLclassmsg').html(jsonData);
			}
		};

		//新竹縣108年公務人員參訓人數
		var study108f = {
			query: function () {
				$.post("study108Servlet", study108f.Result);
			},
			Result: function (jsonData, status) //顯示音樂劇場表演訊息
			{
				$('#study108msg').html(jsonData);
			}
		};

		//看影片學客語
		var hakam = {
			query: function () {
				$.post("hakamovieServlet", hakam.hakmovie);
			},
			hakmovie: function (jsonData, status) {
				$('#movie_info').html(jsonData);
			}
		};
		//客語資訊課程表
		var hakac = {
			query: function () {
				$.post("hakaInformatioCourseServlet", hakac.hakaclass);
			},
			hakaclass: function (jsonData, status) {
				$('#class_info').html(jsonData);
			}
		};
		//桐花文學獎
		var hakace = {
			query: function () {
				$.post("hakaTLAServlet", hakace.hakaCertificate);
			},
			hakaCertificate: function (jsonData, status) {
				$('#cert_info').html(jsonData);
			}
		};
	</script>