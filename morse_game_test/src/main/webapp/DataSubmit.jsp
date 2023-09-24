<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="morse.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ここにタイトルを追加</title>
</head>
<body>
<%
String UserId=(String)request.getAttribute("UserId");
%>
your account is <% out.println(UserId); %>
</body>
</html>