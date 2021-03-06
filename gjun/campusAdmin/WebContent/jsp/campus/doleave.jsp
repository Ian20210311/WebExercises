<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>打造百萬人秒級雲端推播系統，成為萬名行動親師生用戶通知關鍵</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <!--<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>-->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
          <%@include file="../../html/bodyleft.htm" %>
            <!--靜態包含-->
            <div class="col-sm-8">
              <h2>線上請假</h2>
              <div>
                <img src="../../img/campus.jpg" class="img-responsive" alt="Cinque Terre" width="800" height="50" />
              </div>
              <br />
              <br />
              <form class="form-horizontal" role="form">
                <div class="form-group">
                  <label for="firstname" class="col-sm-2 control-label">身分證字號</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="firstname" placeholder="請輸入學生身分證" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="name" class="col-sm-2 control-label">假別</label>
                  <div class="col-sm-8">
                    <select class="form-control">
                      <option>婚假-8日</option>
                      <option>
                        喪假-父母、養父母、繼父母、配偶喪亡者，喪假8日
                      </option>
                      <option>普通傷病假-未住院者，1年內合計不得超過30日</option>
                      <option>
                        公傷病假-因職業災害而致失能、傷害或疾病者，其治療、休養期間，給予公傷病假
                      </option>
                      <option>事假-1年內合計不得超過14日</option>
                      <option>公假-依法令規定應給予公假</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label for="firstname" class="col-sm-2 control-label">開始日期</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="leave_begin" placeholder="請輸入請假起始日期" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="firstname" class="col-sm-2 control-label">結束日期</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="leave_end" placeholder="請輸入請假結束日期" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="name" class="col-sm-2 control-label">請假說明</label>
                  <textarea class="form-control" rows="5"></textarea>
                </div>
                <div class="form-group">
                  <label for="firstname" class="col-sm-2 control-label">密碼</label>
                  <div class="col-sm-8">
                    <input type="password" class="form-control" id="firstname" placeholder="請輸入密碼" />
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
      <%@include file="../../html/bodybottom.htm" %>
        <!--靜態包含-->
  </body>

  </html>
  <script>
    $(document).ready(start);

    function start() {
      //$("#leave_begin").click(date_picker.start_date);
      //$("#leave_end").click(date_picker.end_date);

      $("#leave_begin").datepicker();
      $("#leave_end").datepicker();
    }

    /*
    var date_picker = {
      start_date : function()
      {
        $("#leave_begin").datepicker();
      },
      end_date : function()
      {
        $("#leave_end").datepicker();
      }

    };
    */
  </script>