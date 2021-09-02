<%@page import="in.co.rays.project_4.controller.TimeTableCtl"%>
<%@page import="java.util.HashMap"%>
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

</style>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com//jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <script>
 
$(function(){
	$("#date1").datepicker({
		 beforeShowDay:
			function(dt){
			return[dt.getDay()==0 ? false:true]     ///// to disable sunday
		}, 
		changeMonth:true,
		changeYear:true,
		//maxDate:100,
		//maxYear:2,
		stepMonths: 12,
		minDate: 0+1,
		//beforeShowDay: noSunday,
		//beforeShowDay1: noSunday1,
		
		yearRange:'+0:+5',
		//yearRange:"+10:"
		
		
		defaultDate:"01/01/2020"
	});	
}); 
     

</script> 

</head>
<body>
	<form action="<%=ORSView.TIME_TABLE_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.rays.project_4.bean.TimeTableBean"
			scope="request"></jsp:useBean>

		<%
			List clist = (List) request.getAttribute("courselist");
			List slist = (List) request.getAttribute("subjectlist");
		%>
		<center>
			<%
				if (bean.getId() > 0) {
			%>
			<h2>Update TimeTable</h2>


			<%
				} else {
			%>

			<h2>Add TimeTable</h2>
			<%
				}
			%>


			<H2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</H2>
			<H2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</H2>

			<input type="hidden" name="id" value="<%=bean.getId()%>">
			<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy" 	value="<%=bean.getModifiedBy()%>">
			<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table  align="center" style="margin-left: 38%;">
				<tr>
					<th align="left">Course Name<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), clist) %>
					<font color="red"><%=ServletUtility.getErrorMessage("courseId",request) %></font>
					</td>
				</tr>
				<tr>
					<th align="left">Subject Name<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), slist) %>
					<font color="red"><%=ServletUtility.getErrorMessage("subjectId",request) %></font>
					</td>
				</tr>

				<tr>
					<th align="left">Semester<span style="color: red">*</span></th>
					<td>
					<%
					HashMap map= new HashMap();
					 map.put("1", "1");
					 map.put("2", "2");
					 map.put("3", "3");
					 map.put("4", "4");
					 map.put("5", "5");
					 map.put("6", "6");
					 map.put("7", "7");
					 map.put("8", "8");
					 
					 String semester = HTMLUtility.getList("semester", bean.getSemester(), map);
					 DataUtility.getString("semester");
					%>
			<%=semester%><font color="red"><%=ServletUtility.getErrorMessage("semester", request) %></font>		
					
					</td>
				</tr>

				<tr>
					<th align="left">Exam Time<span style="color: red">*</span></th>
					<td>
						<%
							HashMap map1 = new HashMap();

							map1.put("08:00 AM To 10:00 AM", "08:00 AM To 10:00 AM");
							map1.put("12:00 PM To 02:00 PM", "12:00 PM To 02:00 PM");
							map1.put("03:00 PM To 05:00 PM", "03:00 PM To 05:00 PM");
							String examTimeList = HTMLUtility.getList("examTime", bean.getExamTime(), map1);
						%> <%=examTimeList%> <font color="red"><%=ServletUtility.getErrorMessage("examTime", request)%></font>
					</td>
				</tr>


				<tr>
					<th align="left">Exam Date<span style="color: red">*</span></th>
					<td><input type="text" placeholder="Enter Exam Date" 
						name="examDate" readonly
						value="<%=DataUtility.getDateString(bean.getExamDate())%>"
						id="date1"> <font color="red"><%=ServletUtility.getErrorMessage("examDate", request)%></font>
					</td>
				</tr>
				<tr>
					<th></th>
					<%if(bean.getId()==0){ %>
					
					<td colspan="2"><input type="submit" name="operation"
						value="<%=TimeTableCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=TimeTableCtl.OP_RESET%>"></td>
						<%}  %>
						<%if(bean.getId()>0) {%>  
						<td colspan="2"><input type="submit" name="operation"
						value="<%=TimeTableCtl.OP_UPDATE%>">&emsp; <input type="submit"
						name="operation" value="<%=TimeTableCtl.OP_CANCEL%>"></td>
						<%} %>
				</tr>
			</table>

		</center>
	</form>
	<%@ include file="Footer.jsp" %>
</body>
</html>