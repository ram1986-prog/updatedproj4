<%@page import="in.co.rays.project_4.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.rays.project_4.bean.MarksheetBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
        <h2>Marksheet Merit List</h2>

        <form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="POST">
        <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr>
            <br>
            <table border="1" width="100%">
                <tr>
                     <th>S.No.</th>
                    <th>Roll No</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>
                    <th>Total</th>
                    <th>percentage</th>
                    <th>Remark</th>
                    

                </tr>
                
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                        MarksheetBean bean = it.next();
                        
                        long phy=bean.getPhysics();
                        long chem=bean.getChemistry();
                        long math=bean.getMaths();
                        
                        long total=phy+chem+math;
                        long per=(total*100)/300;
                %>
                <tr>
     

						
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getRollNo()%></td>
                    <td align="center"><%=bean.getName()%></td>
                    <td align="center"><%=bean.getPhysics()%></td>
                    <td align="center"><%=bean.getChemistry()%></td>
                    <td align="center"><%=bean.getMaths()%></td>
                    <td align="center"><%=total%></td>
                    <td align="center"><%=per%>%</td>
                    <%
                    if(bean.getPhysics()<35|| bean.getChemistry()<35|| bean.getMaths()<35){
                    %>
                    <th><font color="red">FAIL</font></th>
                    <%}else{ %>
                    <th><font color="green">Pass</font></th>
                   <%} %> 
                </tr>

                <%
                    }
                %>
            </table>
            <table>
                <tr>
                    <input type="submit" name="operation"
                        value="<%=MarksheetMeritListCtl.OP_BACK%>">
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
    <%@include file="Footer.jsp"%>

</body></div>
</html>