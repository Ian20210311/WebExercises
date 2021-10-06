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
          <form class="form-horizontal" role="form">
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
                <label for="firstname" class="col-sm-2 control-label"
                  >姓名</label
                >
                <div class="col-sm-8">
                  <input
                    type="text"
                    class="form-control"
                    id="firstname"
                    placeholder="請輸入學生姓名"
                  />
                </div>
              </div>
              <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label"
                  >縣市</label
                >
                <div class="col-sm-8">
                    <select class="form-control">
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
                    <select class="form-control">
                        <option>讀JSON檔</option>
                        <option>讀JSON檔</option>
                        <option>讀JSON檔</option>
                      </select>
                </div>
              </div>
              <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label"
                  >郵遞區號</label
                >
              </div>
              <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label"
                  >地址</label
                >
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
                <label for="firstname" class="col-sm-2 control-label"
                  >密碼</label
                >
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
