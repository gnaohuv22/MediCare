<%-- 
    Document   : home
    Created on : Sep 8, 2023, 12:24:04 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ${EMPLOYEE.getName()}
        <a href="loginEmployee">Login Employee</a><br>
        <!--<a href="loginDoctor">Login Doctor</a><br>-->
        <a href="Logout">Logout</a><br>
        ${MESSAGE}
        <a href="EmployeePage">Employee Page</a>
        <!--<a href="DoctorPage.jsp">Doctor Page</a>-->
        <br>
        So luong nguoi truy cap: 
        ${sessionScope.counterUser}
    </body>
</html>
