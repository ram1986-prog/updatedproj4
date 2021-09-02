<%@page import="in.co.rays.project_4.controller.ChangePasswordCtl"%>
<%@page import="in.co.rays.project_4.util.DataUtility"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
</head>
<body>
	<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.project_4.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<h2>Change Password</h2>
			


			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>
			<H3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H3>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> 
			<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
		    <input type="hidden" name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table  align="center" style="margin-left: 38%;">

				<tr>
					<th align="left">Old Password<span style="color:red">*</span></th>
					<td>
					<input type="password" name="oldPassword" placeholder="Enter Old Password" value=<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
					: DataUtility.getString(request.getParameter("oldPassword")))%>>
					<font color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
					</td>
				</tr>

				<tr>
					<th align="left">New Password<span style="color:red">*</span></th>
					<td>
					<input type="password" name="newPassword" placeholder="Enter New Password"
						value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
					: DataUtility.getString(request.getParameter("newPassword")))%>><font
						color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Confirm Password <span style="color:red">*</span></th>
					<td>
					<input type="password" name="confirmPassword" placeholder="Enter Confirm Password"
						value="<%=((bean.getId()==0)?DataUtility.getStringData(bean.getConfirmPassword())
					:DataUtility.getStringData(bean.getPassword()))%>">
						<font
						color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
					</td>
				</tr>

				<tr>
					<th></th>
					<td colspan="2">
					<input type="submit" name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>">&nbsp;
					<input type="submit" name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
						  &nbsp;</td>
				</tr>

			</table>
			
	</form>
	</center>
	<%@ include file="Footer.jsp"%>

</body>
</html>