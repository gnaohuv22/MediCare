<%-- 
    Document   : medicine
    Created on : Sep 16, 2023, 11:23:10 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Medicine Page</title>

        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Medicine</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <jsp:include page="user-head.jsp"/>
    </head>

    <body>
        <%@include file="user-header.jsp" %>
        <!--profile header of a branch start-->
        <div class="branch-profile-header">
            <img src="${pageContext.request.contextPath}/assets/client/images/branch-img.jpg" alt="branch-img" class="branch-image" />
            <p class="branch-name">MediCare - Service ${requestScope.serviceTag.getNametag()}</p>
        </div>
        <!--profile header of a branch end-->
        
        <!--list doctors in a service start-->
        <!--list all doctors start-->
        <div class="doctors-container">
            <c:forEach items="${requestScope.doctorsOfService}" var="doctor">

                <div class="doctor-card">
                    <img src="${pageContext.request.contextPath}/assets/client/images/doctor-img.png" width="30%" height="height" alt="doctor-image"/>
                    <div class="doctor-info">
                        <h2 class="doctor-name">${doctor.getDisplayName()}</h2>
                        <p class="academic-degree">
                            <c:if test="${doctor.getARName()!=null}">
                                ${doctor.getARName()}
                                <c:if test="${doctor.getCertificates()!=null}">
                                    , ${doctor.getCertificates()}
                                </c:if>
                            </c:if>
                            <c:if test="${doctor.getARName()==null}">
                                <c:if test="${doctor.getCertificates()!=null}">
                                    ${doctor.getCertificates()}
                                </c:if>
                            </c:if>
                        </p>
                        <p class="department">Khoa: ${doctor.getDepartmentName()}</p>
                        <p class="branch">${doctor.getBranchName()}</p>
                        <div class="button-container">
                            <button class="book-appointment"> <a class="book-appointment-url" href="doctor-detail?doctorId=${doctor.getId()}">Đặt khám</a></button>
                        </div>
                    </div>
                </div>
            </c:forEach> 
        </div>
        <!--list all doctors end-->
        <!--list doctors in a service end-->
        <%@include file="user-footer.jsp" %>
        <button id="back-to-top" title="Back to top">↑</button>
        <%@include file="user-script.jsp" %>
    </body>
</html>
