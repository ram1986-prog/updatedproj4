<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="in.co.rays.project_4.controller.SubjectCtl"%>
<%@page import="in.co.rays.project_4.util.DataUtility"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
</head>
<body>
	<%@ include file="Header.jsp"%>


	<form action="<%=ORSView.SUBJECT_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.project_4.bean.SubjectBean"
			scope="request"></jsp:useBean>
		<jsp:useBean id="model"
			class="in.co.rays.project_4.model.SubjectModel" scope="request"></jsp:useBean>

		<center>
			<%
				List l = (List) request.getAttribute("courseList");
			%>

			<%
				if (bean.getId() > 0) {
			%>
			<h2>
				Update Subject</font>
			</h2>
			<%
				} else {
			%>
			<h2>Add Subject</h2>
			<%
				}
			%>
			<h2>
				<font color="red" calspan=8><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>
			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDateTime" value="<%=bean.getCreatedDatetime()%>">
			<input type="hidden" name="modifiedDateTime"
				value="<%=bean.getModifiedDatetime()%>">

			<table align="center" style="margin-left: 38%">





				<tr>
					<th align="left">Subject Name:<span style="color: red">*</span></th>
					<td><input type="text" name="subjectName"
						placeholder="Enter Subject Name"
						value="<%=DataUtility.getStringData(bean.getSubjectName())%>"></td>
						<td><font color="red"><%=ServletUtility.getErrorMessage("subjectName", request)%></font>
					</td>

				</tr>

				
				<tr>
					<th align="left">Course Name : <font style="color:red">*</font></th>
					<td style="width: 173px;height: 24px;">
				<%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), l)%>	</td>
				<td><font color="red"><%=ServletUtility.getErrorMessage("courseId", request) %></font>	
					</td>
					


				</tr>

				<tr>
					<th align="left">Description:<span style="color: red">*</span>
					</th>

					<td><input type="text" name="description"
						placeholder="Enter Description"
						value="<%=DataUtility.getStringData(bean.getDescription())%>">
						</td><td><font color="red"><%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<% if(bean.getId()==0){ %>

					<td colspan="2"><input type="submit" name="operation"
						value="<%=SubjectCtl.OP_SAVE%>">&emsp; <input
						type="submit" name="operation" value="<%=SubjectCtl.OP_RESET%>">

					</td>
					<%
						}
					%>
					<%
						if (bean.getId() > 0) {
					%>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=SubjectCtl.OP_UPDATE%>">&emsp; <input
						type="submit" name="operation" value="<%=SubjectCtl.OP_CANCEL%>">

					</td>
					<%
						}
					%>
				</tr>




			</table>

		</center>
	</form>

	<%@ include file="Footer.jsp"%>

</body>
</html>