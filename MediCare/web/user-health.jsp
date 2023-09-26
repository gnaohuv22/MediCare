<%-- 
    Document   : health
    Created on : Sep 16, 2023, 11:19:22 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Health Page</title>

        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Health</title>
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
        <!-- health section start -->
        <div class="health_section layout_padding">
            <div class="container">
                <h1 class="health_taital">Best Of  Health care for you</h1>
                <p class="health_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis</p>
                <div class="health_section layout_padding">
                    <div class="row">
                        <div class="col-sm-7">
                            <div class="image_main">
                                <div class="main">
                                    <img src="assets/client/images/img-2.png" alt="Avatar" class="image" style="width:100%">
                                </div>
                                <div class="middle">
                                    <div class="text"><img src="assets/client/images/icon-1.png" style="width: 40px;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <div class="image_main_1">
                                <div class="main">
                                    <img src="assets/client/images/img-3.png" alt="Avatar" class="image" style="width:100%">
                                </div>
                                <div class="middle">
                                    <div class="text"><img src="assets/client/images/icon-1.png" style="width: 40px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="getquote_bt_1"><a href="#">Read More <span><img src="assets/client/images/right-arrow.png"></span></a></div>
                </div>
            </div>
        </div>
        <!-- health section end -->
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
