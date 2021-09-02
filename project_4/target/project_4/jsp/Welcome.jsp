<%@page import="in.co.rays.project_4.bean.RoleBean"%>
<%@page import="in.co.rays.project_4.bean.UserBean"%>
<%@page import="in.co.rays.project_4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
 .glow {
  font-size: 80px;
  color: #fff;
  text-align: center;
  animation: glow 1s ease-in-out infinite alternate;
}
@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px #e60073, 0 0 40px #e60073, 0 0 50px #e60073, 0 0 60px #e60073, 0 0 70px #e60073;
  }
  
  to {
    text-shadow: 0 0 20px #fff, 0 0 30px #ff4da6, 0 0 40px #ff4da6, 0 0 50px #ff4da6, 0 0 60px #ff4da6, 0 0 70px #ff4da6, 0 0 80px #ff4da6;
  }
}
 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
</head>
<body>

	<form action="<%=ORSView.WELCOME_CTL%>">
		<%@ include file="Header.jsp"%>
		
		<h1 align="Center" class = "glow" style="margin-top: 10%">
		
		
			<font size="10px" color="red">Welcome to ORS </font>
		</h1>

		<%
		
		
			UserBean beanUserBean = (UserBean) session.getAttribute("user");
			if (beanUserBean != null) {
				if (beanUserBean.getRoleId() == RoleBean.ADMIN) {
		%>

		<h2 align="Center">
			<%-- <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your
				Marksheet </a> --%>
		</h2>

		<%
			}
			}
		%>

	</form>
<%@ include file="Footer.jsp"%>
</body>

</html>