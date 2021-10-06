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
            <div><img src="../../img/campus.jpg" class="img-responsive" alt="Cinque Terre" width="800" height="50"> </div>
            <br>
            <br>
            <form class="form-horizontal" role="form" action="chkresp.jsp" method="POST">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Line主動推播</label>
                    <textarea class="form-control" rows="5"></textarea>
                  </div>
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Push Msg</button>
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
