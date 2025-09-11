<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    // 서버 시간 계산
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
    String serverTime = formatter.format(new Date());
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>홈 페이지</title>
    <style>
        body { font-family: sans-serif; text-align: center; margin-top: 100px; }
        div { padding: 20px; border: 1px solid #ccc; display: inline-block; }
        h1 { color: #2c5eff; }
    </style>
</head>
<body>

<div>
    <h1>페이지가 성공적으로 열렸습니다!</h1>
    <p>Spring MVC 프로젝트가 정상적으로 동작하고 있습니다.</p>
    <p>현재 서버 시간: <strong><%= serverTime %></strong></p>
</div>

</body>
</html>