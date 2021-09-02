<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_4.controller.StudentCtl"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@page import="in.co.rays.project_4.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
</head>
<body>
	<form action="<%=ORSView.STUDENT_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.rays.project_4.bean.StudentBean"
			scope="request"></jsp:useBean>
           <%
			List l = (List) request.getAttribute("collegeList");
		%>

		<center>
			<%if(bean.getId()>0) {%>
        
        <h2>Update Student</h2>
       
        <%} else { %>
            <h2>Add Student</h2>
<% }%>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>



			<input type="hidden" name="id" value="<%=bean.getId()%>"> 
			<input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


			<table  align="center" style="margin-left: 38%;">

				<tr>
					<th align="left">First Name<span style="color:red">*</span></th>
					<td><input type="text" name="firstName" placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Last Name<span style="color:red">*</span></th>
					<td><input type="text" name="lastName" placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">MobileNo<span style="color:red">*</span></th>
					<td><input type="text" name="mobileNo" placeholder="Enter Mobile Number" maxlength="10"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request) %></font>
					</td>
				</tr>
				<tr>
					<th align="left">Email<span style="color:red">*</span></th>
					<td><input type="text" name="email" placeholder="Enter Email here"
						value="<%=DataUtility.getStringData(bean.getEmail())%>"> <font
						color="red"><%=ServletUtility.getErrorMessage("email", request) %></font>
					</td>
				</tr>
				  
				
                  
				<tr>
					<th align="left">Date Of Birth<span style="color:red">*</span></th>
					<td><input type="text" name="dob" placeholder="Enter DOB here" value="<%=DataUtility.getDateString(bean.getDob()) %>" id="date" readonly="readonly"><font
						color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				
				</tr>
				
				
				<tr>
				<th align="left"><b>College<span style="color:red">*</span></b></th>
                   <td><%=HTMLUtility.getList("collegeId",String.valueOf(bean.getCollegeId()), l)%> 
                  <font color="red"><%=ServletUtility.getErrorMessage("collegeId", request) %></font>
                    </td>
               
				</tr>
					<th>
					<%if(bean.getId()==0){ %>
					
					<td colspan="2"><input type="submit" name="operation"
						value="<%=StudentCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=StudentCtl.OP_RESET%>"></td>
						<%}  %>
						<%if(bean.getId()>0) {%>  
						<td colspan="2"><input type="submit" name="operation"
						value="<%=StudentCtl.OP_UPDATE%>">&emsp; <input type="submit"
						name="operation" value="<%=StudentCtl.OP_CANCEL%>"></td>
						<%} %>
					</th>
				</tr>
			</table></center>
	</form>
<%@ include file="Footer.jsp"%>
</body>
</html>