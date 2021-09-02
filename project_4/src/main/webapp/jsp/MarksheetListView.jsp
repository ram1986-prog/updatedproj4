<%@page import="javax.swing.plaf.SliderUI"%>
<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="in.co.rays.project_4.util.DataUtility"%>
<%@page import="in.co.rays.project_4.bean.MarksheetBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_4.controller.MarksheetListCtl"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com//jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT %>/js/CheckBox11.js"></script>
<style type="text/css">
#content{
max-height:250px;
position: relative;

}
</style>

</head>
<div id="content">
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean id="model" class="in.co.rays.project_4.model.MarksheetModel" scope="request"></jsp:useBean>
	<jsp:useBean id="bean" class="in.co.rays.project_4.bean.MarksheetBean" scope="request"></jsp:useBean>

	<center>
		<h2>Marksheet List</h2>

		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">
		
		<% 	List sl=(List)request.getAttribute("studentList"); 	%>
		
		<%
		/* pagination has bean applied here */ 
		int pageNo = ServletUtility.getPageNo(request);
        int pageSize = ServletUtility.getPageSize(request);
        int index = ((pageNo - 1) * pageSize) + 1;

        List list = ServletUtility.getList(request);
        Iterator<MarksheetBean> it = list.iterator();
		%>
		
			<% if(list.size()!=0){%>
					<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				</tr>
				<tr>
					<td colspan="8"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></td>
				</tr>

			<table width="fixed">
				<tr>
					<td align="center">
					
					<label> Name :</label> 
					
					<input type="text" name="name"  placeholder="Enter name here" value="<%=ServletUtility.getParameter("name", request)%>">&emsp;
					 
					<label>RollNo :</label> 
					
					<input style="border-style:groove; height:15px" type="text" name="rollNo" placeholder="Enter Your RollNo."
                        value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
						  
						
					<input type="submit" name="operation" value="<%=MarksheetListCtl.OP_SEARCH %>">&emsp;
					
					<input type="submit" name="operation" value="<%=MarksheetListCtl.OP_RESET%>"></td>
					
				</tr>
				

			</table>
			
			<div style="overflow-Y:auto;">
			<table border="1" width="100%">
				<tr>
					<th><input type="checkbox" id="select_all" name="select">Select All</th>
					<th>S.No.</th>
					<th>RollNo</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Total Marks</th>
					<th>percentage</th>
					<th>Grade</th>
					<th>Result</th>
					<th>Edit</th>
				</tr>

				<%
                    

                    while (it.hasNext()) {

                    bean = it.next();
                    int physics=bean.getPhysics();
                    int chemistry=bean.getChemistry();
                    int maths= bean.getMaths();
                  
                                                       
                   int total=physics+chemistry+maths;
                   float per=(total*100)/300;
                   String grade="f";
                   
                   if(per>30 && per<50){
                	   if(physics<34 || chemistry<34 || maths<34){
                		   grade="f";
                	   }else{
                		   grade="D";
                	   }
                	   
                	   
                   }else if(per>51 && per<60){
                	   if(physics<34 || chemistry<34 || maths<34){
                		   grade="f";
                	   }else{
                		   grade="C";
                	   }
                   }else if(per>61 && per<=80){
                	   if(physics<=34 || chemistry<=34 || maths<=34){                		  
                		   grade="f";
                	   }else{
                		   grade="B";
                	   }
                   }else if(per>81 && per<100){
                	   if(physics<=34 || chemistry<=34 || maths<=34){
                		   grade="f";
                	   }else{
                		   grade="A";
                	   }
                   }
                   
                     
                        
                %>
                
				<tr>
					<td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"></td>
					<td align="center"><%=index++%></td>


					<td align="center"><%=bean.getRollNo()%></td>
					<td align="center"><%=bean.getName()%></td>
					<%if(physics>=35) { %>
					<td align="center" ><%=bean.getPhysics()%></td>
					<%} else {%>
					<td align="center" style="color: red"><%=bean.getPhysics()%>*</td>
					<%} %>
					<%if(chemistry>=35) { %>
					<td align="center"><%=bean.getPhysics()%></td>
					<%} else {%>
					<td align="center" style="color: red"><%=bean.getChemistry()%>*</td>
					<%} %>
					<%if(maths>=35) { %>
					<td align="center"><%=bean.getMaths()%></td>
					<%} else {%>
					<td align="center" style="color: red"><%=bean.getMaths()%>*</td>
					<%} %>

					<td align="center"><%=total%></td>
					<td align="center"><%=per%></td>
					<td align="center"><%=grade%></td>
				  <%if(grade.equalsIgnoreCase("f")){%>
					<td align="center" style="color: red;">FAIL</td>
					<%} else { %>
					<td align="center" style="color: green;">PASS</td>
					<%} %>
					
				 <td align="center"><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
                    }
                %>
			</table>
			</div>
			<table align="center" width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_DELETE%>"></td>
					
					<%if((model.nextPK()-1)==bean.getId() || list.size()<pageSize){ %>

					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=MarksheetListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEXT%>"></td>

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
            <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_BACK %>">
            </td>
            </tr>
            </table>
            
            <%} %>
			 
			
			
			      <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			      <input type="hidden" name="pageSize" value="<%=pageSize%>">
		
		</form>
	</center>
	<%@include file="Footer.jsp"%>

</body></div>
</html>