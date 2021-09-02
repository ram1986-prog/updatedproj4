<%@page import="in.co.rays.project_4.controller.UserCtl"%>
<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_4.util.DataUtility"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
</head>
<body>

	<form action="<%=ORSView.USER_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.rays.project_4.bean.UserBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("roleList");
		%>

		<center>
		
			<%if(bean.getId()>0) {%>
        
        <h2>Update User</h2>
       
        <%} else { %>
            <h2>Add User</h2>
<% }%>

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>



			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


			<table align="center" style="margin-left: 38%" >
				<tr>
					<th  align="left">First Name<span style="color:red">*</span></th>
					<td><input type="text" name="firstName" placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td><td><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Last Name<span style="color:red">*</span></th>
					<td><input type="text" name="lastName" placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td><td><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">LoginId<span style="color:red">*</span></th>
					<td><input type="text" name="login" placeholder="Enter LoginId" 
						value="<%=DataUtility.getStringData(bean.getLogin())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Password<span style="color:red">*</span></th>
					<td><input type="password" name="password" placeholder="Enter Your Password"
					 value="<%=DataUtility.getStringData(bean.getPassword())%>" <%=(bean.getId() > 0) ? "readonly" : ""%> ></td><td><font
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				
				</tr>
				
				<tr>
				
					<th align="left">Confirm Password <span style="color:red">*</span></th>
					<td><input type="password" name="confirmPassword" placeholder="Enter Confirm Password"
						value="<%=((bean.getId()==0)?DataUtility.getStringData(bean.getConfirmPassword()):DataUtility.getStringData(bean.getPassword()))%>" <%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font
						color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Gender<span style="color:red">*</span></th>
					<td style="width: 173px;height: 24px;">
						<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%>
						</td><td><font
						color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
						
						</td>
						</tr>
						<tr>
						<th align="left">Mobile No. <span style="color:red">*</span></th>
						<td><input type="text" name="mobileNo" maxlength="10" placeholder="Enter MobileNO"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
						</td><td><font color="red"><%=ServletUtility.getErrorMessage("mobileNo",request) %></font>
						</td>
						</tr>
						<tr>
						<th  align="left">Role <span style="color:red">*</span></th>
						<td style="width: 173px;height: 24px;">
					  <%=HTMLUtility.getList("roleId",String.valueOf(bean.getRoleId()), l)%>
					  </td><td><font
						color="red"> <%=ServletUtility.getErrorMessage("roleId", request)%></font>
					  </td>
				</tr>
				<tr>
					<th align="left">Date Of Birth<span style="color:red">*</span></th>
					<td><input type="text" name="dob" placeholder="(MM/dd/yyyy)" value="<%=DataUtility.getDateString(bean.getDob())%>"  id="date" readonly></td><td> <font
						color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<%if(bean.getId()==0){ %>
					
					<td colspan="2"><input type="submit" name="operation"
						value="<%=UserCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=UserCtl.OP_RESET%>"></td>
						<%}  %>
						<%if(bean.getId()>0) {%>  
						<td colspan="2"><input type="submit" name="operation"
						value="<%=UserCtl.OP_UPDATE%>">&emsp; <input type="submit"
						name="operation" value="<%=UserCtl.OP_CANCEL%>"></td>
						<%} %>
						
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>