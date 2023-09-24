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
<TABLE>
login to
<%
   ArrayList<UserBean> list = (ArrayList<UserBean>)request.getAttribute("UserBeanList");
   for (int i=0; i<list.size(); i++) {
	   UserBean sb = (UserBean)list.get(i);
%>
 <%=sb.getName() %>
<FORM ACTION="DataSubmitServlet">
<Input TYPE="hidden" VALUE="<%=sb.getName() %> " NAME="UserName">
<INPUT TYPE="SUBMIT" VALUE="data submit">
</FORM>
<%

   } // for ループの終わり
%>
</TABLE>
<a href="sample.html">戻る</a>



</body>
</html>