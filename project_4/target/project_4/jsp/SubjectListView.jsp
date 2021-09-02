<%@page import="in.co.rays.project_4.model.CourseModel"%>
<%@page import="in.co.rays.project_4.bean.CourseBean"%>
<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="in.co.rays.project_4.bean.SubjectBean"%>
<%@page import="in.co.rays.project_4.model.SubjectModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.project_4.controller.SubjectListCtl"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
<style type="text/css">
#content {
	max-height: 270px;
	position: relative;
}
</style>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT %>/js/CheckBox11.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com//jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
#content{
max-height:250px;
position: relative;

}
</style>

</head>
<div id="content">
<body>
	<%@ include file="Header.jsp"%>
	<center>
		<h2>Subject List</h2>
		<jsp:useBean id="bean" class="in.co.rays.project_4.bean.SubjectBean"
			scope="request"></jsp:useBean>





		<form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post">

			<%
			List l = (List) request.getAttribute("courseList");
		%>
		
		<%
		int pageNo = ServletUtility.getPageNo(request);
		int pageSize = ServletUtility.getPageSize(request);
		int index = ((pageNo - 1) * pageSize) + 1;

		List list = ServletUtility.getList(request);
		Iterator<SubjectBean> it = list.iterator();
		%>
		<% if(list.size()!=0){%>

			<tr>
				<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
			</tr>
			<tr>
				<td colspan="8"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></td>
			</tr>
			<table width="100%">
				<tr>
					<td align="center"><label>SubjectName :</label> <input
						type="text" name="subjectName" placeholder="Enter subject name"
						value="<%=ServletUtility.getParameter("subjectName", request)%>">
						&emsp; <label>CourseName :</label> <%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), l) %>
						&emsp; <label>Description :</label> <input type="text"
						name="description"
						value="<%=ServletUtility.getParameter("description", request)%>">
						&emsp; <input type="submit" name="operation" placeholder="Enter Description"
						value="<%=SubjectListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=SubjectListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			
			<table border=single width="100%">
				<tr>
					<th><input type="checkbox" id="select_all" name="select">Select All</th>
					<th>S.No</th>
					<th>Subject Name</th>
					<th>Course Name</th>
					<th>Descriptions</th>
					<th>Edit</th>
				</tr>
				<%
	
	SubjectModel model=new SubjectModel();
	
	
	
	while (it.hasNext()) {
	 bean = it.next();
	 
	
	%>
				<tr>
					<td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"></td>


					<td align="center"><%=index++%></td>
					<td align="center"><%=bean.getSubjectName()%></td>
					<td align="center"><%=bean.getCourseName()%></td>
					<td align="center"><%=bean.getDescription()%></td>
					<td align="center"><a href="SubjectCtl?id=<%=bean.getId()%>">Edit</a></td>

					<%} %>


				</tr>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=SubjectListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_DELETE%>"></td>

					<%if((model.nextPk()-1)==bean.getId() || list.size()<pageSize){ %>

					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=SubjectListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_NEXT%>"></td>

					<%
						}
					%>


				</tr>
			</table>
			
			 <%} if(list.size()==0) { %>
            <table>
            <tr>
            <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				</tr>
				<tr>
					<td colspan="8"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></td>
				</tr>
				
            <tr>
            <td align="center">
            <input type="submit" name="operation" value="<%=SubjectListCtl.OP_BACK %>">
            </td>
            </tr>
            </table>
            
            <%} %>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">




		</form>
	</center>

	<%@include file="Footer.jsp"%>

</body></div>
</html>