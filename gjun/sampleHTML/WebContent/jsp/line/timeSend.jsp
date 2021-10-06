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
    <link
      rel="stylesheet"
      type="text/css"
      href="../../assets/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="../../dist/bootstrap-clockpicker.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="../../assets/css/github.min.css"
    />

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
          <h2>訊息發送(排程)</h2>
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
            <!-- <div class="clearfix"> -->
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label"
                >請選擇時間</label
              >
              <div
                class="input-group clockpicker pull-center"
                data-placement="left"
                data-align="top"
                data-autoclose="true"
              >
                <input type="text" class="form-control" value="13:14" />
                <span class="input-group-addon">
                  <span class="glyphicon glyphicon-time"></span>
                </span>
              </div>
            </div>
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label"
                >Line排程推播</label
              >
              <textarea class="form-control" rows="5"></textarea>
            </div>

            <div class="form-group">
              <div class="col-sm-offset-4 col-sm-8">
                <button type="submit" class="btn btn-default">
                  <h4>Push Msg</h4>
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
<script type="text/javascript" src="../../assets/js/jquery.min.js"></script>
<script type="text/javascript" src="../../assets/js/bootstrap.min.js"></script>
<script
  type="text/javascript"
  src="../../dist/bootstrap-clockpicker.min.js"
></script>
<script type="text/javascript">
  $(".clockpicker")
    .clockpicker()
    .find("input")
    .change(function () {
      console.log(this.value);
    });
  var input = $("#single-input").clockpicker({
    placement: "bottom",
    align: "left",
    autoclose: true,
    default: "now",
  });

  $(".clockpicker-with-callbacks")
    .clockpicker({
      donetext: "Done",
      init: function () {
        console.log("colorpicker initiated");
      },
      beforeShow: function () {
        console.log("before show");
      },
      afterShow: function () {
        console.log("after show");
      },
      beforeHide: function () {
        console.log("before hide");
      },
      afterHide: function () {
        console.log("after hide");
      },
      beforeHourSelect: function () {
        console.log("before hour selected");
      },
      afterHourSelect: function () {
        console.log("after hour selected");
      },
      beforeDone: function () {
        console.log("before done");
      },
      afterDone: function () {
        console.log("after done");
      },
    })
    .find("input")
    .change(function () {
      console.log(this.value);
    });

  // Manually toggle to the minutes view
  $("#check-minutes").click(function (e) {
    // Have to stop propagation here
    e.stopPropagation();
    input.clockpicker("show").clockpicker("toggleView", "minutes");
  });
  if (/mobile/i.test(navigator.userAgent)) {
    $("input").prop("readOnly", true);
  }
</script>
<script type="text/javascript" src="../../assets/js/highlight.min.js"></script>
<script type="text/javascript">
  hljs.configure({ tabReplace: "    " });
  hljs.initHighlightingOnLoad();
</script>
