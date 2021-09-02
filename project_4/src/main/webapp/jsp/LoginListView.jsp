<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_4.controller.UserListCtl"%>
<%@page import="in.co.rays.project_4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#content{
max-height:250px;
position: relative;

}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
</head>
<div id="content">
<body>

 <%@include file="Header.jsp"%>

    <center>
        <h2>User List</h2>

        <form action="<%=ORSView.USER_LIST_CTL%>" method="post">

            <table width="100%">
                <tr>
                    <td align="center"><label>FirstName :</label> <input
                        type="text" name="firstName"
                        value="<%=ServletUtility.getParameter("firstName", request)%>">
                        &emsp; <label>LoginId:</label> <input type="text" name="login"
                        value="<%=ServletUtility.getParameter("login", request)%>">
                        &emsp; <input type="submit" name="operation" value="<%=UserListCtl.OP_SEARCH %>">
                        <input type="submit" name="operation" value="<%=UserListCtl.OP_RESET%>">
                    </td>
                </tr>
            </table>
            

            <table border="1" width="100%">
                <tr>
                    <th><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.No.</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>LoginId</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>Edit</th>
                </tr>

                <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr>

                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request); 
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<UserBean> it = list.iterator();
                    while (it.hasNext()) {
                        UserBean bean = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getFirstName()%></td>
                    <td align="center"><%=bean.getLastName()%></td>
                    <td align="center"><%=bean.getLogin()%></td>
                    <td align="center"><%=bean.getGender()%></td>
                    <td align="center"><%=bean.getDob()%></td>
                    <td align="center"><a href="UserCtl?id=<%=bean.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                    <td ><input type="submit" name="operation"
                        value="<%=UserListCtl.OP_PREVIOUS%>"></td>
                        <td ><input type="submit" name="operation"
                        value="<%=UserListCtl.OP_NEW%>"></td>
                     <td >
                     <input type="submit" name="operation" value="<%=UserListCtl.OP_DELETE%>"></td>
                     <td align="right">
                     <input type="submit" name="operation" value="<%=UserListCtl.OP_NEXT%>"></td>
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>">
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
    <%@include file="Footer.jsp"%>
</body></div>
</html>