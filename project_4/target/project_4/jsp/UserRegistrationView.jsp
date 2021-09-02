<%@page import="in.co.rays.project_4.controller.UserRegistrationCtl"%>
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
 <script type="text/javascript" src="./js/calendar.js"></script>
</head>
<body>
<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

        <%@ include file="Header.jsp"%>
       
        <jsp:useBean id="bean" class="in.co.rays.project_4.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h2>User Registration</h2>

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId() %>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table  align="center" style="margin-left: 38%;">

                <tr>
                    <th align="left">First Name<span style="color:red">*</span></th>
                    <td><input type="text" name="firstName"placeholder="FirstName"
                        value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Last Name<span style="color:red">*</span></th>
                    <td><input type="text" name="lastName"placeholder="LastName"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">LoginId<span style="color:red">*</span></th>
                    <td><input type="text" name="login" placeholder="Must be Email ID"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
                        color="red"><%=ServletUtility.getErrorMessage("login",request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Password<span style="color:red">*</span></th>
                    <td><input type="password" name="password" placeholder="Password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Confirm Password<span style="color:red">*</span></th>
                    <td><input type="password" name="confirmPassword"placeholder="confirmPassword"
                        value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
                        
                </tr>
                <tr>
                    <th align="left">Gender<span style="color:red">*</span></th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("Male", "Male");
                            map.put("Female", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),map);
                        %> <%=htmlList%>
               <font color="red"><%=ServletUtility.getErrorMessage("gender",request) %></font>
                    </td>
                </tr>
                <tr>
						<th align="left">Mobile No. <span style="color:red">*</span></th>
						<td><input type="text" name="mobileNo" maxlength="10" placeholder="Enter MobileNO"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("mobileNo",request) %></font>
						</td>
						</tr>

                <tr>
                    <th align="left">Date Of Birth<span style="color:red">*</span></th>
                    <td><input type="text" name="dob" id="date"placeholder="MM/dd/yyyy" readonly="readonly"
                        value="<%=DataUtility.getDateString(bean.getDob())%>"> 
                        
                   <font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td colspan="2">&nbsp;
                        &nbsp; <input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP %>">
                     &nbsp; <input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_RESET %>">
                    
                    </td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>