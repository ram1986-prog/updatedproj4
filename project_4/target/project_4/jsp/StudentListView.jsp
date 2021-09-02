<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="in.co.rays.project_4.bean.StudentBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_4.controller.StudentListCtl"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com//jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
#content{
max-height:250px;
position: relative;

}
</style>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT %>/js/CheckBox11.js"></script>

</head>
<div id="content">
<body>

	<%@include file="Header.jsp"%>
	<center>
		<h2>Student List</h2>
		
		 <%
        List l=(List)request.getAttribute("collegelist");
        %>
		

		<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
			<jsp:useBean id="model"
				class="in.co.rays.project_4.model.StudentModel" scope="request"></jsp:useBean>
			<jsp:useBean id="bean" class="in.co.rays.project_4.bean.StudentBean"
				scope="request"></jsp:useBean>
			<tr>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<StudentBean> it = list.iterator();
				%>
				 <% if(list.size()!=0){%>
				<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
			</tr>
			<tr>
				<td colspan="8"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></td>
			</tr>
			<table width="100%">
				<tr>
					<td align="center"><label> FirstName :</label>
				<input type="text" name="firstName" placeholder="Enter First name"value="<%=ServletUtility.getParameter("firstName", request)%>">

						<label>Email Id:</label> 
					<input type="text" name="email" placeholder="Enter emailid" value="<%=ServletUtility.getParameter("email", request)%>">

                       <label>College:</label>
                    <%=HTMLUtility.getList("collegeId", String.valueOf(bean.getCollegeId()), l) %>&emsp;   

						<input type="submit" name="operation"
						value="<%=StudentListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=StudentListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			
			<div style="overflow-y:scroll;">
			<table border="1" width="100%">
				<tr>
					<th><input type="checkbox" id="select_all" name="select">Select All</th>



					<th>S.No.</th>



					<th>First Name</th>
					<th>Last Name</th>
					<th>Date Of Birth</th>
					<th>Mobile No</th>
					<th>College</th>
					<th>Email ID</th>
					<th>Edit</th>
				</tr>

				<%
					while (it.hasNext()) {

							bean = it.next();
				%>
				<tr>
					<td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"></td>

					<td align="center"><%=index++%></td>


					<td align="center"><%=bean.getFirstName()%></td>
					<td align="center"><%=bean.getLastName()%></td>
					<td align="center"><%=bean.getDob()%></td>
					<td align="center"><%=bean.getMobileNo()%></td>
					<td align="center"><%=bean.getCollegeName()%></td>
					<td align="center"><%=bean.getEmail()%></td>
					<td align="center"><a href="StudentCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			</div>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=StudentListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_DELETE%>"></td>
					<%
						if ((model.nextPK() - 1) == bean.getId() || list.size() < pageSize) {
					%>

					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=StudentListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_NEXT%>"></td>

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
            <input type="submit" name="operation" value="<%=StudentListCtl.OP_BACK %>">
            </td>
            </tr>
            </table>
            
            <%} %>
				</tr>
			</table>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
				type="hidden" name="pageSize" value="<%=pageSize%>">


		</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body></div>
</html>