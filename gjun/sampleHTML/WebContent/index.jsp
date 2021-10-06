<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
  <%@include file="html/bodytop.htm"%><!--靜態包含-->
  
  <div class="container">
    <div class="row">
      <!--include left frame-->
      <%@include file="html/bodyleft.htm"%><!--靜態包含-->
      <div class="col-sm-8">
        <h2>活動訊息</h2>
        <div><img src="img/campus.jpg" class="img-responsive" alt="Cinque Terre" width="800" height="50"> </div>
        <ul>
          <!--<li><a href="https://cloud.hakka.gov.tw/Pub/Opendata/DTST20200800001.json" id="jason1">客家委員會好客小學堂</a></li>-->
          <li><a href="#" id="jason1">客家委員會好客小學堂</a></li>
          <div id="msg">
            </divi>
            <li><a href="https://cloud.hakka.gov.tw/Pub/Opendata/DTST20201200001.json">客家委員會客語辭彙</a></li>
            <li><a href="	https://cloud.hakka.gov.tw/Pub/Opendata/DTST20201000002.json">客語結合十二年國教校訂課程</a></li>
            <li><a href="https://cloud.hakka.gov.tw/Pub/Opendata/DTST20200800002.json">客家委員會好客小學堂冒險任務</a></li>
        </ul>
      </div>
    </div>
  </div>

  <!--include bottom frame-->
  <%@include file="html/bodybottom.htm"%><!--靜態包含-->
</body>

</html>
<script>
  $(document).ready(start);

  function start() {
    $('#jason1').click(addItem);
  }

  function addItem() {
    //var address = $('#jason1').attr("href");
    //var address = $('#jason1').attr("target");


    //var item=$("#item").val();
    //var btAdd=$("#addsubmit").val();
    //$.post("addcartServlet",{"item":item,"submit":btAdd},addresult)

    //$.post("primaryServlet",{"addr":address} ,addresult)

    $.post("primaryServlet", addresult)
  }

  function addresult(jsonData, status) {
    //alert(jsonData);
    $('#msg').html(jsonData);
    //$('#msg').html("abc");
  }



</script>