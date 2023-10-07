<%-- 
    Document   : user-appointment
    Created on : Sep 28, 2023, 10:48:40 AM
    Author     : phuon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="user-head.jsp"/>
        <title>Profile</title>
    </head>
    <body>
        <jsp:include page="user-header.jsp"/>
        <main>
            <div class="main-wrapper">
                <div class="sidebar">
                    <ul>
                        <li >
                            <a href="user-profile">Profile</a>
                        </li>
                        <li class="sidebar-active">
                            <a href="user-appointment">Appointment</a>
                        </li>
                        <li>
                            <a href="user-payment-history">Payment History</a>
                        </li>
                        <li>
                            <a href="user-logout">Log out</a>
                        </li>
                    </ul>
                </div>
            </div>
        </main>
        <jsp:include page="user-footer.jsp"/>
        <jsp:include page="user-script.jsp"/>
    </body>
</html>

