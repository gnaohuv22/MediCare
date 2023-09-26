<%-- 
    Document   : news
    Created on : Sep 16, 2023, 11:24:55 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>News Page</title>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>News</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="assets/client/css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="assets/client/css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="assets/client/css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="assets/client/images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="assets/client/css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
        <!-- owl stylesheets --> 
        <link rel="stylesheet" href="assets/client/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/client/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
    </head>

    <body>
        <%@include file="user-header.jsp" %>

        <!--profile header of a branch start-->
        <div class="branch-profile-header">
            <img src="assets/client/images/branch-img.jpg" alt="branch-img" class="branch-image" />
            <p class="branch-name">MediCare - List of doctors nationwide</p>
        </div>
        <!--profile header of a branch end-->

        <!--list all doctors start-->
        <div class="doctors-container">
            <c:forEach items="${requestScope.doctors}" var="doctor">
                <div class="doctor-card">
                    <img src="assets/client/images/doctor-img.png" width="100" height="100" alt="doctor-image"/>
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
                            <button class="book-appointment"> <a class="book-appointment-url" href="user-doctor-detail?doctorId=${doctor.getId()}">Đặt khám</a></button>
                        </div>
                    </div>
                </div>
            </c:forEach> 
        </div>
        <!--list all doctors end-->


        <!-- news section start -->
        <div class="news_section layout_padding">
            <div class="container">
                <h1 class="health_taital">Why choose 24hr home care</h1>
                <p class="health_text">labore et dolore magna aliqua. Ut enim ad minim veniam</p>
                <div class="news_section_2 layout_padding">
                    <div class="row">
                        <div class="col-lg-4 col-sm-6">
                            <div class="box_main">
                                <div class="icon_1"><img src="assets/client/images/icon-2.png"></div>
                                <h4 class="daily_text">Daily care experts</h4>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6">
                            <div class="box_main active">
                                <div class="icon_1"><img src="assets/client/images/icon-3.png"></div>
                                <h4 class="daily_text_1">Available 24/7</h4>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6">
                            <div class="box_main">
                                <div class="icon_1"><img src="assets/client/images/icon-4.png"></div>
                                <h4 class="daily_text_1">Balanced care</h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="getquote_bt"><a href="#">Get A Quote <span><img src="assets/client/images/right-arrow.png"></span></a></div>
            </div>
        </div>
        <!-- news section end -->
        <%@include file="user-footer.jsp" %>
        <!-- Javascript files-->
        <script src="assets/client/js/jquery.min.js"></script>
        <script src="assets/client/js/popper.min.js"></script>
        <script src="assets/client/js/bootstrap.bundle.min.js"></script>
        <script src="assets/client/js/jquery-3.0.0.min.js"></script>
        <script src="assets/client/js/plugin.js"></script>
        <!-- sidebar -->
        <script src="assets/client/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="assets/client/js/custom.js"></script>
        <!-- javascript --> 
        <script src="assets/client/js/owl.carousel.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
    </body>
</html>
