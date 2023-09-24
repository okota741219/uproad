<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="morse.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>データベースの内容表示</title>
</head>
<body>

<h1>データベースの内容を表示します</h1>

<TABLE>
    <TR>
    <TD>学籍番号</TD>
    <TD>氏名</TD>
    <TD>password </TD>
	</TR>
<%
   ArrayList<UserBean> list = (ArrayList<UserBean>)request.getAttribute("UserBeanList");
   for (int i=0; i<list.size(); i++) {
	   UserBean sb = (UserBean)list.get(i);
%>
    <TR>
    <TD><%=sb.getId() %></TD>
    <TD><%=sb.getName() %></TD>
    <TD><%=sb.getPass() %></TD>
	</TR>
<%
   } // for ループの終わり
%>
</TABLE>
<a href="sample.html">戻る</a>

</body>
</html>