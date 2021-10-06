<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>打造百萬人秒級雲端推播系統，成為萬名行動親師生用戶通知關鍵</title>
    <link
      rel="stylesheet"
      href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
    />
    <!--<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>-->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
      .fakeimg {
        height: 200px;
        background: #aaa;
      }
    </style>
  </head>
  <body onload="onloadset()">
    <!--include top frame-->
    <%@include file="../../html/bodytop.htm"%><!--靜態包含-->
    <div class="container">
      <div class="row">
        <!--include left frame-->
        <%@include file="../../html/parentbodyleft.htm"%><!--靜態包含-->
        <div class="col-sm-8">
          <h2>親子綁定</h2>
          <div>
            <img
              src="../../img/campus.jpg"
              class="img-responsive"
              alt="Cinque Terre"
              width="800"
              height="50"
            />
          </div>
          <br />
          <br />
          <form class="form-horizontal" role="form" id="regitForm" action="/campusAdmin/regitServlet" method="POST">
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label"
                >身分證字號</label
              >
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="firstname"
                  placeholder="請輸入學生身分證" 
                />
              </div>
            </div>
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label">姓名</label>
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="firstname" 
                  name = 'firstname'
                  placeholder="請輸入學生姓名" 
                />
              </div>
            </div>
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label">縣市</label>
              <div class="col-sm-8">
                <select class="form-control" id="city">
                  <option>讀JSON檔</option>
                  <option>讀JSON檔</option>
                  <option>讀JSON檔</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label"
                >鄉鎮[市]區</label
              >
              <div class="col-sm-8">
                <select class="form-control" id="county">
                  <option>中正區</option>
                  <option>大同區</option>
                  <option>萬華區</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label"
                >郵遞區號</label
              >
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="zip"
                  placeholder="請輸入郵遞區號"
                  readonly="true"
                  value="100"
                />
              </div>
            </div>
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label">地址</label>
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="firstname"
                  placeholder="請輸入道路名" 
                />
              </div>
            </div>
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label"
                >家長email address</label
              >
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="firstname"
                  placeholder="請輸入有效email Address" 
                />
              </div>
            </div>
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label">密碼</label>
              <div class="col-sm-8">
                <input
                  type="password"
                  class="form-control"
                  id="firstname"
                  placeholder="請輸入密碼" 
                />
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">登錄</button>
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

<script type="text/javascript" src="taiwan_districts.js"></script>
<script type="text/javascript">
  var objJSON = new Object();
  var strJSON = "";
  var objDistricts = new Object();

  $(document).ready(start);

  function start() {
    $("#city").change(setCounty);
    $("#county").change(setZip);

    $("#regitForm").validate ();  
  }

  //body onload
  function onloadset() {
    //alert(data);

    //轉字串
    strJSON = JSON.stringify(data);
    //alert(strJSON);

    //轉object
    objJSON = JSON.parse(strJSON);
    //alert(objJSON[0].name);

    //設定縣市
    $("#city").empty();
    $.each(objJSON, function (i) {
      var o = new Option(this.name, this.name);
      /// jquerify the DOM object 'o' so we can use the html method
      $(o).html(this.name);
      $("#city").append(o);
    });

    setCounty();
  }

  //設定鄉鎮
  function setCounty() {
    //alert(strJSON);
    //設定鄉鎮
    $("#county").empty();
    $.each(objJSON, function (i) {
      if (this.name == $("#city").val()) {
        //alert(this.name + "==" + $("#city").val());
        objDistricts = this.districts;
        //alert(objDistricts[0].name);

        $.each(objDistricts, function (i) {
          var o = new Option(this.name, this.name);
          /// jquerify the DOM object 'o' so we can use the html method
          $(o).html(this.name);
          $("#county").append(o);
        });
      }
    });

    setZip();
  }

  //設定郵遞區號
  function setZip() {
    //alert(objDistricts[0].zip);
    $.each(objDistricts, function (i) {
      if (this.name == $("#county").val()) {
        //alert(this.name + "==" + $("#county").val());
        document.getElementById("zip").value = this.zip;
      }
    });
  }
</script>
