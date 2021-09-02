<%@page import="in.co.rays.project_4.controller.ForgetPasswordCtl"%>
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
 <form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.rays.project_4.bean.UserBean"
            scope="request"></jsp:useBean>
           

        <center>
            <h2>Forgot your password?</h2>
            <input type="hidden" name="id" value="<%=bean.getId()%>">
             <H2 >
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
            
<lable>Submit your email address and we'll send you password.</lable><br><br>
            <table>
                 
                <tr>
                
                <th><label align="left">Email Id :</label>&emsp;</th>
                <th><input type="text" name="login" placeholder="Enter ID Here"
                    value="<%=ServletUtility.getParameter("login", request)%>">&emsp;</th>
               <th> <input type="submit" name="operation" value="<%=ForgetPasswordCtl.OP_GO%>"></th>
                
                
               <th style="position: fixed"> <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></th>
            </table>
            
           
            
    </form>
    </center>
    <%@ include file="Footer.jsp"%>



</body>
</html>