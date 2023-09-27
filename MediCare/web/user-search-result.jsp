<%-- 
    Document   : user-search-result
    Created on : Sep 28, 2023, 12:49:49 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
        <!-- site metas -->
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kết quả tìm kiếm cho "${pattern}"</title>
    </head>
    <body>
        <%@include file="user-header.jsp" %>
        <!-- search result section start -->
        <div id="doctor-carousel" class="carousel slide" data-ride="carousel">
            <div class="search-container container">
                <h1 class="search-doctor-title">Kết quả tìm kiếm cho "${pattern}"</h1>
                <div class="vertical-doctor-list">
                    <c:forEach var="doctor" items="${doctors}">
                        <div class="search-doctor-block">
                            <div class="doctor-img">
                                <img src="assets/client/images/doctor-img.png" width="100" alt="${doctor.getDisplayName()}">
                            </div>
                            <div class="search-doctor-information">
                                <span class="search-doctor-name">${doctor.getDisplayName()}</span><br>
                                <span class="search-doctor-branch-name">${doctor.getBranchName()}</span><br>
                                <span class="search-doctor-intro">${doctor.getCertificates()}</span><br>
                                <span class="search-doctor-intro">${doctor.getARName()}</span><br>
                                <span class="search-doctor-introduce">${doctor.getIntroduce()}</span>
                                <button class="search-find-more">
                                    <a href="user-doctor-detail?doctorId=${doctor.getId()}">Tìm hiểu thêm</a>
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- search result section end -->
        <%@include file="user-footer.jsp" %>
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
