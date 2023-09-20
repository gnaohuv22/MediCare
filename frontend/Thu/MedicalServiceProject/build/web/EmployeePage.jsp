<%-- 
    Document   : AdminPage
    Created on : Sep 11, 2023, 2:47:29 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang của nhân viên</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Chào ${EMPLOYEE.getName()}</h1>
        <c:if test="${empty EMPLOYEE}">Bạn phải là nhân viên để có thể truy cập vào nội dung của trang</c:if>
        <c:if test="${not empty EMPLOYEE}">
            <a href="getInfoRegisterEmployee">Đăng kí nhân viên (chưa phân quyền)</a>
            <br>
            <a href="Statistic.jsp">Thống kê, Quản lí (chưa phân quyền)</a>
        </c:if>
    </body>
</html>
