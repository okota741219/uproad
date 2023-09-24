<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="morse.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="index.css">
<title>スタート画面</title>
</head>
<body>
<TABLE>
your account is
<%
   ArrayList<UserBean> list = (ArrayList<UserBean>)request.getAttribute("UserBeanList");
   for (int i=0; i<list.size(); i++) {
	   UserBean sb = (UserBean)list.get(i);
%>


<%=sb.getName() %>

<% } %>
</TABLE>

<H1>モールス信号師シミュレータ</H1>

<section>
<a href="GetExamServlet"class="btn">PLAY</a>
<br/>
<a href="login.html" class= "btn2">login</a> 
<br/>
<a href="UserUpdate.html" class="btn3">account</a>
</section>

</body>
</html>