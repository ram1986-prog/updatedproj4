<%@page import="javax.mail.Session"%>
<%@page import="in.co.rays.project_4.controller.MyProfileCtl"%>
<%@page import="in.co.rays.project_4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@page import="in.co.rays.project_4.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
<style type="text/css">
.ali{
width:173px;
height:24px;

}
</style>
</head>
<body>

<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

        <%@ include file="Header.jsp"%>
        <script type="text/javascript" src="../js/calendar.js"></script>
        <jsp:useBean id="bean" class="in.co.rays.project_4.bean.UserBean"
            scope="request"></jsp:useBean> 

        <center>
            <h2>My Profile</h2>
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
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table  align="center" style="margin-left: 38%;">
                <tr >
                      <th align="left">LoginId<span style="color:red">*</span></th>
                    <td><input type="text" name="login"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"readonly="readonly"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>

                <tr style="margin-top: 2%">
                    <th align="left">First Name<span style="color:red">*</span></th>
                    <td><input type="text" name="firstName"
                        value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr style="margin-top: 2%">
                    <th align="left">Last Name<span style="color:red">*</span></th>
                    <td><input type="text" name="lastName"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr style="margin-top: 2%">
                    <th align="left">Gender<span style="color:red">*</span></th>
                    <td class="ali">
                        <%
                            HashMap map = new HashMap();
                            map.put("Male", "Male");
                            map.put("Female", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> <%=htmlList%>
                        <font
						color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
                    </td>
                </tr>
                <tr style="margin-top: 2%">
                    <th align="left">Mobile No<span style="color:red">*</span></th>
                    <td><input type="text" name="mobileNo" maxlength="10"
                        value="<%=DataUtility.getStringData(bean.getMobileNo())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
                </tr>

               <tr style="margin-top: 2%">
					<th align="left">Date Of Birth<span style="color:red">*</span></th>
					<td><input type="text" name="dob" placeholder="(MM/dd/yyyy)" value="<%=DataUtility.getDateString(bean.getDob())%>"  id="date" readonly> <font
						color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
                
            
                
                <tr style="margin-top: 2%">
                    <th></th>
                    <td colspan="2">
                    <input type="submit" name="operation" value="<%=MyProfileCtl.OP_SAVE %>">
                    <input type="submit" name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD %>"> &nbsp; </td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>

</body>
</html>