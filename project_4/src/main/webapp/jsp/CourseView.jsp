<%@page import="in.co.rays.project_4.controller.CourseCtl"%>
<%@page import="java.util.List"%>
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
	<form action="<%=ORSView.COURSE_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.rays.project_4.bean.CourseBean" scope="request"></jsp:useBean>

		<center>
			<%if(bean.getId()>0)
			 {%>
        
       		 <h2>Update Course</h2>
       
       		 <%} else { %>
       		 
            	<h2>Add Course</h2>
					<% }%>
			<%-- <%
     List list=(List)request.getAttribute(" ");
     %> --%>


			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
			</h2>
			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
			</h2>

			<input type="hidden" name="id" value="<%=bean.getId() %>"> 
			<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy" value=<%=bean.getModifiedBy() %>>
			<input type="hidden" name="createdDateTime" value=<%=DataUtility.getTimestamp(bean.getCreatedDatetime()) %>>
			<input type="hidden" name="modifiedDateTime" value=<%=DataUtility.getTimestamp(bean.getModifiedDatetime()) %>>
			
			<table  align="center" style="margin-left: 38%;">
				<tr>
					<th align="left">Course Name <font color="red">*</th>
					<td>
					<input type="text" name="courseName" placeholder="Enter Course Name"
						value="<%= DataUtility.getStringData(bean.getCourseName()) %>">
						<font color="red"><%=ServletUtility.getErrorMessage("courseName", request) %></font>
					</td>
				</tr>
				<tr>
				<th align="left">Description<font color="red">*</font>
				</th> 
				
				<td><input type="text" name="description" placeholder="Enter Description" value="<%=DataUtility.getStringData(bean.getDescription())%>">
					<font color="red"><%=ServletUtility.getErrorMessage("description", request) %></font>
				</td>
			</tr>
				<tr>
					<th></th>

					<%if(bean.getId()==0){ %>
					
					<td colspan="2">
					<input type="submit" name="operation" value="<%=CourseCtl.OP_SAVE%>">&emsp; 
					<input type="submit" name="operation" value="<%=CourseCtl.OP_RESET%>">
					</td>
					
					<%}  %>
					<%if(bean.getId()>0) {%>  
					
					<td colspan="2">
					<input type="submit" name="operation" value="<%=CourseCtl.OP_UPDATE%>">&emsp; 
					<input type="submit" name="operation" value="<%=CourseCtl.OP_CANCEL%>">
					</td>
					
					<%} %>

				</tr>

			</table>
		</center>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>