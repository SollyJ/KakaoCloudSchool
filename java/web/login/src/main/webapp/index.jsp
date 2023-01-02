<%@ page import="com.sollyj.dto.MemberDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - 자바 웹 프로그래밍</title>
</head>
<body>
<h1><%= "쿠키와 세션" %>
</h1>
<br/>
<a href="createcookie.jsp">쿠키 생성 및 읽기</a><br/>
<a href="deletecookie.jsp">쿠키 삭제</a>

<h1><%= "로그인 처리" %>
</h1>
<p>프로젝트 구조 실습</p>

<%
    Object loginInfo = session.getAttribute("loginInfo");
    if (loginInfo == null) {
%>
<a href="login">로그인</a>
<%
} else {
    MemberDTO dto = null;
    dto = (MemberDTO)loginInfo;%>
<%=dto.getMname()%>님 환영합니다.<br/>
<a href="logout">로그아웃</a><%} %>
</body>
</html>