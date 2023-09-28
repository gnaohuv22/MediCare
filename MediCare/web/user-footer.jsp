<%-- 
    Document   : footer
    Created on : Sep 16, 2023, 11:14:43 PM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
        <!-- site metas -->
        <title>Medical Service</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="assets/client/css/bootstrap.min.css" />
        <!-- style css -->
        <link rel="stylesheet" href="assets/client/css/style.css" />
        <!-- Responsive-->
        <link rel="stylesheet" href="assets/client/css/responsive.css" />
        <!-- fevicon -->
        <link rel="icon" href="assets/client/images/favicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="assets/client/css/jquery.mCustomScrollbar.min.css" />
        <!-- Tweaks for older IEs-->
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
            />
        <!-- owl stylesheets -->
        <link rel="stylesheet" href="assets/client/css/owl.carousel.min.css" />
        <link rel="stylesheet" href="assets/client/css/owl.theme.default.min.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
            media="screen"
            />
    </head>

    <!-- footer section start -->
    <div class="footer_section layout_padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-sm-6">
                    <div class="footer_logo">
                        <a href="user-home.jsp"><img width="100" height="100" src="assets/client/images/logo.png" /></a>
                    </div>
                    <h1 class="adderss_text">Liên hệ với chúng tôi</h1>
                    <c:forEach items="${sessionScope.contacts}" var="contact">
                        <div class="map_icon">
                            <img src="${contact.getIcon()}"/><span class="paddlin_left_0">${contact.getContact()}</span>
                        </div>
                    </c:forEach>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <h1 class="adderss_text">Bác sĩ tiêu biểu</h1>
                    <c:forEach items="${sessionScope.trendDoctors}" var="d">
                        <div class="hiphop_text_1">
                            <a href="user-doctor-detail?doctorId=${d.getId()}">${d.getDisplayName()}</a>
                        </div>
                    </c:forEach>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <h1 class="adderss_text">Tham khảo</h1>
                    <c:forEach items="${sessionScope.usefulLinks}" var="ul">
                        <div class="Useful_text">
                            <a href="${ul.getLink()}">${ul.getTitle()}</a>
                        </div>
                    </c:forEach>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <h1 class="adderss_text">Kết nối với chúng tôi</h1>
                    <div class="social_icon">
                        <ul>
                            <c:forEach items="${sessionScope.socialNetworks}" var="sn">
                                <li>
                                    <a href="${sn.getLink()}"><img src="${sn.getIcon()}" alt="${sn.getSocialName()}"/></a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- footer section end -->

    <div class="copyright_section">
        <div class="container">
            <p class="copyright_text">
                2023 by gnaohuv & SWP391_SE1733_NET_G6
            </p>
        </div>
    </div>
    <!-- copyright section end -->
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

</html>
