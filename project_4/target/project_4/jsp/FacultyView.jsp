<%@page import="in.co.rays.project_4.controller.FacultyCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_4.util.DataUtility"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@page import="in.co.rays.project_4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
<script type="text/javascript" src="../js/calendar.js"></script>
</head>
<body>
    
<form action="<%=ORSView.FACULTY_CTL%>" method="post">
<%@ include file="Header.jsp"%>

<jsp:useBean id="bean" class="in.co.rays.project_4.bean.FacultyBean" scope="request"></jsp:useBean>
   <%long ids=DataUtility.getLong(request.getParameter("id")); %>
        <center>
   
        <%if(ids>0) {%>
        
        <h2>Update Faculty</h2>
       
        <%} else { %>
            <h2>Add Faculty</h2>
<% }%>
   
   <%
   List l=(List)request.getAttribute("collegeList");
   List l2=(List)request.getAttribute("subjectList");
   List l3=(List)request.getAttribute("courseList");
  
   %>
   
   <h2><font color="red"><%=ServletUtility.getErrorMessage(request) %></font></h2>
   <h2><font color="green"><%=ServletUtility.getSuccessMessage(request) %></font></h2>
   
   <input type="hidden" name="id" value="<%=bean.getId()%>">
   <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
   <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
   <input type="hidden" name="createdDateTime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
   <input type="hidden" name="modifiedDateTime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
  
   
   <table align="center" style="margin-left: 38%">
  <tr>
  <th align="left">First Name<span style="color:red">*</span></th>
  <td><input type="text" name="firstName" placeholder="Enter First Name" value="<%=DataUtility.getStringData(bean.getFirstName())%>">
  </td><td><font color="red"><%=ServletUtility.getErrorMessage("firstName", request) %></font>
  </td>
  </tr>
  
  <tr>
  <th align="left">Last Name<span style="color:red">*</span></th>
  <td><input type="text" name="lastName" placeholder="Enter First Name" value="<%=DataUtility.getStringData(bean.getLastName())%>">
  </td><td><font color="red"><%=ServletUtility.getErrorMessage("lastName", request) %></font></td>
  </tr>
  
  <tr>
  <th align="left">Joining Date<span style="color:red">*</span></th>
  <td><input type="text" name="doj" id="date"  readonly="readonly" placeholder="Enter Joining Date" value=<%=DataUtility.getDateString(bean.getDoj()) %> >
  </td><td><font color="red"><%=ServletUtility.getErrorMessage("doj", request) %></font>
  </td>
  </tr>
  
  <tr>
  <th align="left">Qualification<span style="color:red">*</span></th>
  <td><input type="text" name="qualification" placeholder="Enter Qualification" value="<%=DataUtility.getStringData(bean.getQualification())%>">
  </td><td><font color="red"><%=ServletUtility.getErrorMessage("qualification", request) %></font>
  </td>
  </tr>
  
  <tr>
  <th align="left">Mobile No<span style="color:red">*</span></th>
  <td><input type="text" name="mobileNo" maxlength="10" placeholder="Enter Mobile Number" value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
  </td><td><font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request) %></font>
  </td>
  </tr>
  
  <tr>
  <th align="left">Login ID<span style="color:red">*</span></th>
  <td><input type="text" name="emailId" placeholder="Enter LoginId" value="<%=DataUtility.getStringData(bean.getEmailId())%>">
  </td><td><font color="red"><%=ServletUtility.getErrorMessage("emailId", request) %></font>
  </td>
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
			</td><td>			<font color="red"><%=ServletUtility.getErrorMessage("gender", request) %></font>
						</td>
						</tr>
  
  <tr>
  <th align="left">College Name<span style="color:red">*</span></th>
  <td style="width: 173px;height: 24px;"><%=HTMLUtility.getList("collegeId", String.valueOf(bean.getCollegeId()), l) %>
  </td><td><font color="red"><%=ServletUtility.getErrorMessage("collegeId", request) %></font>
  </td>
  </tr>
  
  <tr>
  <th align="left">CourseName<span style="color:red">*</span></th>
  <td style="width: 173px;height: 24px;"><%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), l3) %>
  </td><td><font color="red"><%=ServletUtility.getErrorMessage("courseId", request) %></font>
  </td>
  </tr>
  
  <tr>
  <th align="left">Subject Name<span style="color:red">*</span></th>
  <td style="width: 173px;height: 24px;"><%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), l2) %>
  </td><td><font color="red"><%=ServletUtility.getErrorMessage("subjectId",request) %></font>
  </td>
  </tr>
  
  
  <tr>
					<th></th>
					<%if(bean.getId()==0){ %>
					
					<td colspan="2"><input type="submit" name="operation"
						value="<%=FacultyCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=FacultyCtl.OP_RESET%>"></td>
						<%}  %>
						<%if(bean.getId()>0) {%>  
						<td colspan="2"><input type="submit" name="operation"
						value="<%=FacultyCtl.OP_UPDATE%>">&emsp; <input type="submit"
						name="operation" value="<%=FacultyCtl.OP_CANCEL%>"></td>
						<%} %>
				</tr>
   </table>
   </center>

</form>

	<%@ include file="Footer.jsp"%>


</body>
</html>