<%@page import="in.co.rays.project_4.controller.MarksheetCtl"%>
<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
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
	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.project_4.bean.MarksheetBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("studentList");
		%>

		<center>
			<%
		
				if (bean.getId() > 0) {
			%>

			<h2>Update Marksheet</h2>

			<%
				} else {
			%>
			<h2>Add Marksheet</h2>
			<%
				}
			%>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			
		 <input type="hidden" name="id" value="<%=bean.getId()%>"> 
		 
		 <input	type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			
		 <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
		
		 <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			
		 <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


<table align="center" style="margin-left: 38%;">
 <tr>
	<th align="left">Rollno<span style="color: red">*</span></th>
			<td>
					
				<input type="text" name="rollNo" 
				value="<%=DataUtility.getStringData(bean.getRollNo())%>"
					   <%=(bean.getId() > 0) ? "readonly" : ""%>>
				<font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>
			</td>
		</tr>
				
				<tr>
					<th align="left">Name<span style="color: red">*</span></th>
					<td ><%=HTMLUtility.getList("studentId", String.valueOf(bean.getStudentId()), l)%>
						<font color="red"> <%=ServletUtility.getErrorMessage("studentId", request)%></font>
					</td>
				</tr>
				 
				<tr>
					<th align="left">Physics<span style="color: red">*</span></th>
 					<td>
					<input type="text" name="physics" placeholder="Enter Physics Number"
						value="<%=DataUtility.getStringData(bean.getPhysics()).equals("0") ? ""
					: DataUtility.getStringData(bean.getPhysics())%>">
					<font color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font>
					</td>
				</tr>
				
				<tr>
					<th align="left">Chemistry<span style="color: red">*</span></th>
					<td>
					<input type="text" name="chemistry" placeholder="Enter Chemistry Number"
									   value="<%=DataUtility.getStringData(bean.getChemistry()).equals("0") ? ""
					: DataUtility.getStringData(bean.getChemistry())%>">
					<font color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font>
					</td>
				</tr>
				
				<tr>
					<th align="left">Maths<span style="color: red">*</span></th>
					<td>
					<input type="text" name="maths" placeholder="Enter Maths Number"
									   value="<%=DataUtility.getStringData(bean.getMaths()).equals("0") ? ""
					: DataUtility.getStringData(bean.getMaths())%>">
					<font color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font>
					</td>
				</tr> 
				
				<tr>
					<th></th>
					<%
						if (bean.getId() == 0) {
					%>

					<td colspan="2">
					<input type="submit" name="operation" value="<%=MarksheetCtl.OP_SAVE%>">&emsp;
					<input type="submit" name="operation" value="<%=MarksheetCtl.OP_RESET%>">
					</td>
					<%
						}
					%>
					<%
						if (bean.getId() > 0) {
					%>
					<td colspan="2">
					<input type="submit" name="operation" value="<%=MarksheetCtl.OP_UPDATE%>">&emsp; 
					<input type="submit" name="operation" value="<%=MarksheetCtl.OP_CANCEL%>">
					</td>
					<%
						}
					%>

				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>

</body>
</html>