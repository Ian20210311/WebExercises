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
          <h2>即時訊息發送</h2>
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
              <label for="name" class="col-sm-2 control-label"
                >Line主動推播</label
              >
              <textarea class="form-control" rows="5" id="line_msg"></textarea>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" id="btnLine">
                  Push Msg
                </button>
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
    $("#btnLine").click(sendtoLine);
  }

  function sendtoLine() {
    //replace \ with \\

    if ($("#line_msg").val().trim().length > 0) {
      var dataJSON = {};
      //dataJSON["message"] = $("#line_msg").val();
      dataJSON.message = $("#line_msg").val();
      //alert(dataJSON);

      $.ajax({
        type: "POST",
        //url: "http://localhost:8080/lineBotRestful/lineBroadMsg",
        url: "/lineBotRestful/lineBroadMsg",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(dataJSON), //傳送給後端的資料
        success: function (result, status, xhr) {
          //轉字串
          strJSON = JSON.stringify(result);
          //alert(strJSON);

          //轉object
          //objJSON = JSON.parse(result);
          //alert(objJSON);
          alert(status);

          /*
                    var table = $("<table><tr><th>Weather Description</th></tr>");
  
                    table.append("<tr><td>City:</td><td>" + result["name"] + "</td></tr>");
                    table.append("<tr><td>Country:</td><td>" + result["sys"]["country"] + "</td></tr>");
                    table.append("<tr><td>Current Temperature:</td><td>" + result["main"]["temp"] + "°C</td></tr>");
                    table.append("<tr><td>Humidity:</td><td>" + result["main"]["humidity"] + "</td></tr>");
                    table.append("<tr><td>Weather:</td><td>" + result["weather"][0]["description"] + "</td></tr>");
  
                    $("#message").html(table);
                    */
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
    } else {
      alert("請輸入需要傳遞字串");
    }
  }

  //convert escape characters
  function editMsg(intputStr) {
    alert(intputStr);
    intputStr
      .replace(/\\n/g, "\\\n")
      .replace(/\\'/g, "\\'")
      .replace(/\\"/g, '\\"')
      .replace(/\\&/g, "\\\&")
      .replace(/\\r/g, "\\\r")
      .replace(/\\t/g, "\\\t")
      .replace(/\\b/g, "\\\b")
      .replace(/\\f/g, "\\\f");
    alert("aaaaaaaaaaa" + intputStr);
    return intputStr;
  }
</script>
