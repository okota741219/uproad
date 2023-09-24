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
   ArrayList<UserBean> list = (ArrayList<UserBean>)request.getAttribute("FoodsList");
   for (int i=0; i<list.size(); i++) {
	   UserBean sb = (UserBean)list.get(i);
%>
 <%=sb.getName()%>
 <%=sb.getPass() %>
<%} %>
</body>
</html>