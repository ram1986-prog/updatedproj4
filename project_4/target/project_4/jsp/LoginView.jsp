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
<body>
   <% String uri = (String) request.getAttribute("URI"); %>

    <form action="<%=ORSView.LOGIN_CTL%>" method="post">
        <%@ include file="Header.jsp"%>

         <jsp:useBean id="bean" class="in.co.rays.project_4.bean.UserBean"
            scope="request"></jsp:useBean>
 
        <center>
        
            <h2>Login</h2>
              <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
                 
              </H2>
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
              
              <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table  align="center" style="margin-left: 38% " >
          
                <tr >
                    <th align="left" >LoginId<span style="color:red">*</span></th>
                    <td><input type="text" name="login"  size=30 placeholder="Enter LoginId" 
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
                        color="red">  <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
             
                <tr style="margin-top: 2%">
                    <th align="left">Password<span style="color:red">*</span></th>
                    <td><input type="password" name="password" size=30 placeholder="Enter Password" style="margin-top: 10px"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr style="margin-top: 2%">
                    <th></th>
                    <td colspan="2" ><input type="submit" name="operation" padding="6px,6px" style="margin-top: 10px"
                        value="<%=LoginCtl.OP_SIGN_IN %>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input align="right" type="submit"
                        name="operation" value="<%=LoginCtl.OP_SIGN_UP %>" > &nbsp;</td>
                </tr>
                <tr><th></th>
                <td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b><font >Forget my password</font></b></a>&nbsp;</td>
            </tr>
            </table>
            <input type="hidden" name="uri" value="<%=uri%>">
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</div>
</body>
</html>