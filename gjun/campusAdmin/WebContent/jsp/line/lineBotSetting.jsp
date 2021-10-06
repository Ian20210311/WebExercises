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
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
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
    <%@include file="../../html/bodytop.htm"%><!--靜態包含-->

    <div class="container">
      <div class="row">
        <!--include left frame-->
        <%@include file="../../html/linebodyleft.htm"%><!--靜態包含-->
        <div class="col-sm-8">
          <h2>Line Bot聊天機器回應設定</h2>
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
          <form
            class="form-horizontal"
            role="form"
            action="chkresp.jsp"
            method="POST"
          >
            <div class="form-group">
              <label for="name" class="col-sm-4 control-label"
                >文字模式Line Bot聊天機器回應設定</label
              >
              <textarea class="form-control" rows="5" id="txtlineMsg"></textarea>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-4 col-sm-8">
                <button type="button" class="btn btn-default" id="btnLine">Push Msg</button>
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
$(document).ready(start);

function start() {
    $("#btnLine").click(lineBotSetting);
  }

  function lineBotSetting() {
    if($("#txtlineMsg").val().trim().length > 0)
    {
      var dataJSON = {};
      dataJSON.message = $("#txtlineMsg").val();

      $.ajax({
        type: "POST",
        //url: "http://localhost:8080/lineBotRestful/lineBotTxtSetting",
        url: "/lineBotRestful/lineBotTxtSetting",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(dataJSON), //傳送給後端的資料
        success: function (result, status, xhr) {
          //轉字串
          strJSON = JSON.stringify(result);
          alert(strJSON);

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
  }

</script>
