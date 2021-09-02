<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="in.co.rays.project_4.bean.FacultyBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_4.model.FacultyModel"%>
<%@page import="in.co.rays.project_4.controller.FacultyListCtl"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#content{
max-height:200px;
position: relative;

}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com//jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
 
$(function(){
	$("#timeTableDate").datepicker({
		 beforeShowDay:
			function(dt){
			return[dt.getDay()==0 ? false:true]     ///// to disable sunday
		}, 
		changeMonth:true,
		changeYear:true,
		//maxDate:100,
		//maxYear:2,
		//stepMonths: 12,
		//minDate: 0+1,
		//beforeShowDay: noSunday,
		//beforeShowDay1: noSunday1,
		//dateFormat: "yyyy-MM-dd",
		yearRange:'+0:+5',
		//yearRange:"+10:"
		
		
		defaultDate:"01/01/2019"
	});	
}); 
     

</script>
<style type="text/css">
#content{
max-height:230px;
position: relative;

}
</style>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>

<script type="text/javascript" src="<%=ORSView.APP_CONTEXT %>/js/CheckBox11.js"></script>
</head>
<div id="content">
<body>
<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">
   <%@ include file="Header.jsp" %>
   
   <center>
   <h2>Faculty List</h2>
   
<%             
             List clglist=(List)request.getAttribute("college");
             List Courselist=(List)request.getAttribute("course");
%>   
   
   <jsp:useBean id="bean" class="in.co.rays.project_4.bean.FacultyBean" scope="request"></jsp:useBean>
   
   <%
   int pageNo=ServletUtility.getPageNo(request);
   int pageSize=ServletUtility.getPageSize(request);
   int index=((pageNo-1)*pageSize)+1;
   
   List list = ServletUtility.getList(request);
	Iterator<FacultyBean> it = list.iterator();
   %>
    <% if(list.size()!=0){%>
   <tr>
   <td colspan="8"><font color="red" size="4px"><%=ServletUtility.getErrorMessage(request) %></font></td>
   
   </tr>
    <tr>
   <td colspan="8"><font color="green" size="4px"><%=ServletUtility.getSuccessMessage(request) %></font></td>
   
   </tr>
<table width="100%">
      <tr>
      <td align="center"><label>First Name</label>
      <input type="text" name="firstName" placeholder="Enter Your First Name" value="<%=ServletUtility.getParameter("firstName", request)%>"> &emsp;
     
      <label>Email Id</label>
      <input type="text" name="user"  placeholder="Enter Your Emailid" value="<%=ServletUtility.getParameter("user", request)%>"> &emsp;
      
      <label>College</label>
       <%=HTMLUtility.getList("college", String.valueOf(bean.getCollegeId()), clglist ) %>&emsp;

      <label>Course</label>
      <%=HTMLUtility.getList("course", String.valueOf(bean.getCourseId()), Courselist ) %>&emsp;
      
      <input type="submit" name="operation" value="<%=FacultyListCtl.OP_SEARCH%>">
      
      <input type="submit" name="operation" value="<%=FacultyListCtl.OP_RESET%>">
         </td>
      
      </tr>
</table> 

          <table border="1" width="100%">
           <tr>
           <th><input type="checkbox" id="select_all" name="select">Select All</th>
           <th>S.No</th>
           <th>First Name</th>
           <th>Last Name</th>
           <th>Qualification</th>
           <th>Gender</th>
           <th>Email Id</th>
           <th>Joining Date</th>
           <th>College Name</th>
           <th>Course Name</th>
           <th>Subject Name</th>
           <th>Edit</th>
           </tr>          
          
          <%
          
      	FacultyModel model= new FacultyModel();
      	
      	
      	
      	while (it.hasNext()) {
      	 bean = it.next();
      	 
      	
          %>
          
          <tr>
	<td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"></td>
	
	
                    <td align="center"><%=index++%></td>
	<td align="center"><%=bean.getFirstName()%></td>
	<td align="center"><%=bean.getLastName()%></td>
	<td align="center"><%=bean.getQualification()%></td>
	<td align="center"><%=bean.getGender()%></td>
	
	<td align="center"><%=bean.getEmailId()%></td>
	<td align="center"><%=bean.getDoj()%></td>
	
	<td align="center"><%=bean.getCollegeName()%></td>
	<td align="center"><%=bean.getCourseName()%></td>
	<td align="center"><%=bean.getSubjectName()%></td>
	
	<td align="center"><a href="FacultyCtl?id=<%=bean.getId()%>">Edit</a></td>
	<%} %>
						
	</td>
	
	
          </tr>
          </table>
          <table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=FacultyListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
						<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_DELETE%>"></td>
					<%if((model.nextPK()-1)==bean.getId() || list.size()<pageSize){ %>

					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=FacultyListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEXT%>"></td>

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
            <input type="submit" name="operation" value="<%=FacultyListCtl.OP_BACK %>">
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

 <%@ include file="Footer.jsp"%>

</body></div>
</html>