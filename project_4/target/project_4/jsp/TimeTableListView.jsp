

<%@page import="in.co.rays.project_4.bean.TimeTableBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.project_4.controller.TimeTableListCtl"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Table List</title>

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
		dateFormat: "yy-mm-dd",
		yearRange:'+0:+5',
		//yearRange:"+10:"
		
		
		//defaultDate:"01/01/2019"
	});	
}); 
     

</script>
<style type="text/css">
#content{
max-height:250px;
position: relative;

}
</style>
<!-- <script>
function checkedAll() {

	var totalElement = document.forms[0].elements.length;
	//alert(totalElement);
	for (var i = 0; i < totalElement; i++) {
		var en = document.forms[0].elements[i].name;

		//alert(en);
		if (en != undefined & en.indexOf("chk_") != -1) {
			document.forms[0].elements[i].checked = document.frm.chk_all.checked;

		}
	}
}
function check(){
	var en=document.forms[0].elements[6].name;
	if(en!=undefined & en.indexOf("chk_")!=-1)
	{	
		document.forms[0].elements[6].checked=document.frm.chk_all.unchecked;
	}	
}
</script> -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com//jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT %>/js/CheckBox11.js"></script>


<%@include file="Header.jsp"%>
</head>
<div id="content">


<body>
	
	<form action="<%=ORSView.TIME_TABLE_LIST_CTL %>" method="post" name="frm">
		<jsp:useBean id="bean" class="in.co.rays.project_4.bean.TimeTableBean"
			scope="request"></jsp:useBean>
		<jsp:useBean id="model" class="in.co.rays.project_4.model.TimeTableModel"
			scope="request"></jsp:useBean>
      <h2 align="center"><font >TimeTable List</font></h2>
		<input type="hidden" name="id" value="<%=bean.getId()%>">

		<%
List courseList=(List)request.getAttribute("courseList");
List subjectList=(List)request.getAttribute("subjectList");
%>

		<table width=fixed align="center">
			<tr>
				<td><label  >Course Name:</label><font style="background: green;"><%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), courseList) %></font>
				&emsp;<label  >Subject Name:</label><%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectList)%> &emsp;
				&emsp; <label  >Exam Date:</label><input type="text"
					name="examDate" placeholder="Must be a Date" id="timeTableDate"
					value="<%=ServletUtility.getParameter("examDate", request) %>"  id="date" readonly="readonly"> &emsp;
				<input  type="submit" name="operation" value="<%=TimeTableListCtl.OP_SEARCH %>"> &emsp;
				<input  type="submit" name="operation" value="<%=TimeTableListCtl.OP_RESET%>">
				
				</td>
				 
			</tr>
		</table>
		
		
			
		   <div align="center">
		    <tr>
				<td colspan="8"><font color="red" size="5px"><%=ServletUtility.getErrorMessage(request)%></font></td>
			</tr>
		  </div>
		  
		   <div align="center">
		    <tr>
				<td colspan="8"><font color="green" size="5px"><%=ServletUtility.getSuccessMessage(request)%></font></td>
			</tr>
		  </div>
<%   List list = ServletUtility.getList(request);
if(list.size()!=0){
%>
		<div style="overflow-Y:auto;">
		<table border=single width="100%" >
			<tr  >
				 <th> <input type="checkbox" id="select_all" name="select">Select All </th>
			    <th>S.No</th>
				<th>Course Name</th>
				<th>Subject Name</th>
				<th>Semester</th>
				<th>Exam Date</th>
				<th>Exam Time</th>
				<th>Edit</th>
			</tr>

		<%} %>	

			<%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                  
                    
                    
                    Iterator<TimeTableBean> it=list.iterator();
                    while (it.hasNext()) 
                    {
                        bean = it.next();
                         
                %>
			<tr align="center">
				<td><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=index++%></td>
				<td><%=bean.getCourseName()%></td>
				<td><%=bean.getSubjectName()%></td>
				<td><%=bean.getSemester() %></td>
				<td><%=bean.getExamDate()%></td>
				<td><%=bean.getExamTime()%></td>
				<td><a href="TimeTableCtl?id=<%=bean.getId()%>"><font >Edit</font></a></td>
			</tr>
			<%
                    }
                 long i=bean.getId();
                 
                 System.out.println(model.nextPk()-1);
                 System.out.print("id= "+bean.getId());
                  
                %>
		</table>
<%if(list.size()!=0){ %>

		<table width="100%">
		<tr>
        
         
				<%  if(pageNo==1){ %>
				 <td> <input  type="submit" name="operation" disabled="disabled"
					value="<%=TimeTableListCtl.OP_PREVIOUS%>"></td>

				<% } else {%>
				<td><input  type="submit" name="operation"
					value="<%=TimeTableListCtl.OP_PREVIOUS%>"></td>
				<% }%>



				<td><input  type="submit" name="operation"
					value="<%=TimeTableListCtl.OP_NEW%>"></td>


				<td><input  type="submit" name="operation"
					value="<%=TimeTableListCtl.OP_DELETE %>"></td>

				<%-- <%if((list.size() < pageSize) && ((model.nextPk() - 1) == bean.getId())) {%> --%>
				
				  <%--   <%System.out.println("id from bean"+bean.getId()); %>
				    <%System.out.println("id from next pk"+(model.nextPk()-1)); %> --%>
				    
				    
<%--             <%if(((model.nextPk()-1)== ((TimeTableBean)list.get(list.size()-1)).getId())) {%>
 --%>            
            <%if((model.nextPk()-1)==bean.getId() || list.size()<pageSize){ %>
            
				<td align="right">&nbsp; &nbsp;<input  type="submit" name="operation" disabled="disabled"
					value="<%=TimeTableListCtl.OP_NEXT %>"></td>

				<%} else { %>
          
				<td align="right">&nbsp; &nbsp;<input  type="submit" name="operation"
					value="<%=TimeTableListCtl.OP_NEXT %>"></td>

				<% }%>
				
			</tr>
		</table>
		</div>
<%} else { %>
<table align="center">
<tr>
<td>
<input style="color: maroon; font-family: cursive;" type="submit" name="operation" value="<%=TimeTableListCtl.OP_BACK %>">
</td>
</tr>
</table>
<% }%>
		<input type="hidden" name="pageNo" value="<%=pageNo %>"> <input
			type="hidden" name="pageSize" value="<%=pageSize %>">


	</form>

	
</body></div>
<%@include file="Footer.jsp"%>
</html>