<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:choose>
        <c:when test="${studentInfo != null}">
            <div class="col-sm-8">
                <h2>報表申請</h2>
                <ul>
                    <h3><li><a href="/campusAdmin/requestReceiptServlet" id="receipt_info">註冊繳費通知單</a></li></h3>
                    <h3><li><a href="/campusAdmin/requestAttendServlet" id="class_info">出席紀錄報告(即時)</a></li></h3>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <div class="col-sm-8" style="text-align: center;">
                <h2><a href="pdfidx.jsp" id="movie_info">登入失敗，請重新登入!!</h2>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>