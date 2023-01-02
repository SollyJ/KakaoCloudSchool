<%--
  Created by IntelliJ IDEA.
  User: sollyj
  Date: 2022/12/30
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>쿠키 읽기</title>
</head>
<body>
    <%
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            String value = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");  // 한글을 썼으니까 디코딩
    %>
        <%=cookie.getName()%>: <%=value%>
    <br/>
    <%
        }
    %>
</body>
</html>
