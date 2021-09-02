<%@page import="in.co.rays.project_4.controller.ErrorCtl"%>
<%@page import="in.co.rays.project_4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.ERROR_CTL%>/img/logo.png">
<style type="text/css">


.hm-gradient {
	/* background-image: linear-gradient(to top, #f3e7e9 0%, #6c757d 99%, #e3eeff 100%); */

	background-image: url('<%=ORSView.APP_CONTEXT%>/img/bg1.png');
}


</style>
</head>
<body class="hm-gradient">
	<br>
	
	
	<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6" style="white">
	<div align="center">

		<h1 style="color: black"><font size="8%">Oops! something went wrong</font></h1>
		<font style="color: red; size: 25px;">Requested
			resource is not available </font>
		<div style="width: 25%; text-align: justify;color: #00004d">
			<h3>Try: </h3>
			<ul>
				<li>check the network cables,modem,and router</li>
				<li>reconnect to Wi-Fi</li>
			</ul>
		</div>
	</div>
	<h4 align="center">
		<font size="5px" color="black"> <a href="<%=ORSView.WELCOME_CTL%>"
			style="color: #006600;">*Please click here to Go Back*</a></font>
	</h4>
</div>
<div class="col-md-3"></div>
</div>
</body>
</html>