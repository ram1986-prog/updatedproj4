<%@page import="in.co.rays.project_4.controller.CollegeCtl"%>
<%@page import="in.co.rays.project_4.util.DataUtility"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
</head>
<body>
<form action="<%=ORSView.COLLEGE_CTL%>" method="post">
 
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.rays.project_4.bean.CollegeBean"
            scope="request"></jsp:useBean>

        <center>
           <%if(bean.getId()>0) {%>
        
        <h1>Update College</h1>
       
        <%} else { %>
            <h2>Add College</h2>
<% }%>
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>"> 
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy"value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime"value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime"value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table  align="center" style="margin-left: 38%;">
                <tr>
                    <th align="left">Name<span style="color:red">*</span></th>
                    <td><input type="text" name="name" placeholder="Enter Name"
                        value="<%=DataUtility.getStringData(bean.getName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Address<span style="color:red">*</span></th>
                    <td><input type="text" name="address" placeholder="Enter Address"
                        value="<%=DataUtility.getStringData(bean.getAddress())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">State<span style="color:red">*</span></th>
                    <td><input type="text" name="state" placeholder="Enter State"
                        value="<%=DataUtility.getStringData(bean.getState())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">City<span style="color:red">*</span></th>
                    <td><input type="text" name="city" placeholder="Enter City"
                        value="<%=DataUtility.getStringData(bean.getCity())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">PhoneNo<span style="color:red">*</span></th>
                    <td><input type="text" name="phoneNo" placeholder="Enter PhoneNumber" maxlength="10"
                        value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
                </tr>


                <tr>
                    <th></th>
                    <%if(bean.getId()==0){ %>
					
					<td colspan="2"><input type="submit" name="operation"
						value="<%=CollegeCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=CollegeCtl.OP_RESET%>"></td>
						<%}  %>
						<%if(bean.getId()>0) {%>  
						<td colspan="2"><input type="submit" name="operation"
						value="<%=CollegeCtl.OP_UPDATE%>">&emsp; <input type="submit"
						name="operation" value="<%=CollegeCtl.OP_CANCEL%>"></td>
						<%} %>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>