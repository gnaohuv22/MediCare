<%-- 
    Document   : doctor-profile
    Created on : Sep 24, 2023, 5:31:35 PM
    Author     : phuon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="doctor-head.jsp"/>
        <title>Hồ sơ bác sĩ | MediCare</title>
    </head>
    <body>
        <jsp:include page="doctor-header.jsp"/>


        <form action="doctor-profile-2-pdf" method="GET">
            <input type="submit" value="Xuất PDF">
        </form>
        <jsp:include page="doctor-profile.jsp"/>


        <jsp:include page="doctor-footer.jsp"/>
        <jsp:include page="doctor-script.jsp"/>
    </body>
</html>
