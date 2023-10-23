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
        <!-- bootstrap css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="${pageContext.request.contextPath}/assets/client/images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
        <!-- owl stylesheets --> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
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
        <script src="${pageContext.request.contextPath}/assets/client/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/jquery-3.0.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/plugin.js"></script>
        <!-- sidebar -->
        <script src="${pageContext.request.contextPath}/assets/client/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/custom.js"></script>
        <!-- javascript --> 
        <script src="${pageContext.request.contextPath}/assets/client/js/owl.carousel.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
    </body>
</html>
