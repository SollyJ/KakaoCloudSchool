<%--
  Created by IntelliJ IDEA.
  User: sollyj
  Date: 2022/12/30
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>쿠키 생성</title>
</head>
<body>
<%
    // 한글을 쓸 경우 인코딩을 해주어야한다.
    Cookie cookie = new Cookie("name", java.net.URLEncoder.encode("사랑","UTF-8"));    // 쿠키 생성
    response.addCookie(cookie); // 쿠키 저장
%>
<a href="viewcookies.jsp">Cookie 읽기</a>
</body>
</html>
