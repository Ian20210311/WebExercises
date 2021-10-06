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
    <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
    <%@include file="../../html/bodytop.htm" %>
    <!--靜態包含-->

    <div class="container">
      <div class="row">
        <!--include left frame-->
        <%@include file="../../html/pdfbodyleft.htm" %>
        <!--靜態包含-->
        <div class="col-sm-8">
          <h2>報表推撥</h2>
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
          <form class="form-horizontal" role="form">
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label"
                >身分證字號</label
              >
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  id="stdID"
                  placeholder="請輸入學生身分證"
                />
              </div>
            </div>
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label">密碼</label>
              <div class="col-sm-8">
                <input
                  type="password"
                  class="form-control"
                  id="pwd"
                  placeholder="請輸入密碼"
                />
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" id="btn">
                  登錄
                </button>
              </div>
              <div id="msg"></div>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!--include bottom frame-->
    <%@include file="../../html/bodybottom.htm" %>
    <!--靜態包含-->
  </body>
</html>
<script>
    $(document).ready(start);

    function start() {
      $("#btn").click(chkusr);
    }

    /*
    function chkusr() {
      var obj = new Object();
      obj.stdID = $("#stdID").val();
      obj.pwd = $("#pwd").val();
      var jstr = JSON.stringify(obj);
      var str = $("#stdID").val() + ":" + $("#pwd").val();
      alert(str);
      
      $.ajax({
        type: "POST",
        //url: "http://localhost:8080/campusAdmin/chkusrServlet",
        url: "/campusAdmin/chkusrServlet",
        dataType: "script",
        contentType: "application/json;charset=utf-8",
        data:  { data: str }, //傳送給後端的資料
        success: function (result, status, xhr) {
          //轉字串
          strJSON = JSON.stringify(result);
          //alert(strJSON);

          //轉object
          //objJSON = JSON.parse(result);
          //alert(objJSON);
          alert(status);
        },
        error: function (xhr, status, error) {
          alert(
            "Result: " +
              status +
              " " +
              error +
              " " +
              xhr.status +
              " " +
              xhr.statusText
          );
        },
      });
    }
    */

    function chkusr() {
      var obj = new Object();
      obj.stdID = $("#stdID").val();
      obj.pwd = $("#pwd").val();
      var jstr = JSON.stringify(obj);
  	var str = $("#stdID").val()+":"+$("#pwd").val();
  	//alert(jstr);
  	//alert(str);

  	$.post("/campusAdmin/chkusrServlet", { data: str }, result);

    }

    function result(data, status){
      //alert(status);
      //document.getElementById('stdID').value = '' ;
      //document.getElementById('pwd').value = '' ;
      $("#msg").html(data);
     }

 
</script>
