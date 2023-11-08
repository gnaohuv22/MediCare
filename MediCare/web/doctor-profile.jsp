<%-- 
    Document   : doctor-profile
    Created on : Sep 24, 2023, 5:31:35 PM
    Author     : phuon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="doctor-head.jsp"/>
        <title>Hồ sơ bác sĩ | MediCare</title>
    </head>
    <body>
        <jsp:include page="doctor-header.jsp"/>
        
        <div class="container">
            <div class="profile-container">
                <div class="text-center">
                    <h1 class="profile-title">Hồ sơ bác sĩ</h1>
                </div>
            </div>
        </div>
        
        <jsp:include page="doctor-footer.jsp"/>
        <jsp:include page="doctor-script.jsp"/>
    </body>
</html>
