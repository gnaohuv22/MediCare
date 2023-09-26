<%-- 
    Document   : contact
    Created on : Sep 16, 2023, 11:28:17 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Contact</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="assets/user/css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="assets/user/css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="assets/user/css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="assets/user/images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="assets/user/css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
        <!-- owl stylesheets --> 
        <link rel="stylesheet" href="assets/user/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/user/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <!-- contact section start -->
        <div class="contact_section layout_padding margin_90">
            <div class="container">
                <h1 class="contact_taital">What we do</h1>
                <div class="news_section_2">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="icon_main">
                                <div class="icon_7"><img src="assets/user/images/icon-7.png"></div>
                                <h4 class="diabetes_text">Diabetes and obesity Counselling </h4>
                            </div>
                            <div class="icon_main">
                                <div class="icon_7"><img src="assets/user/images/icon-5.png"></div>
                                <h4 class="diabetes_text">Obstetrics and Gynsecology</h4>
                            </div>
                            <div class="icon_main">
                                <div class="icon_7"><img src="assets/user/images/icon-6.png"></div>
                                <h4 class="diabetes_text">Surgical and medical Oncology</h4>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="contact_box">
                                <h1 class="book_text">Book Appoinment</h1>
                                <input type="text" class="Email_text" placeholder="Name" name="Name">
                                <input type="text" class="Email_text" placeholder="Name" name="Name">
                                <textarea class="massage-bt" placeholder="Massage" rows="5" id="comment" name="Massage"></textarea>
                                <div class="send_bt"><a href="#">SEND</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- contact section end -->
        <%@include file="footer.jsp" %>
        <!-- Javascript files-->
        <script src="assets/user/js/jquery.min.js"></script>
        <script src="assets/user/js/popper.min.js"></script>
        <script src="assets/user/js/bootstrap.bundle.min.js"></script>
        <script src="assets/user/js/jquery-3.0.0.min.js"></script>
        <script src="assets/user/js/plugin.js"></script>
        <!-- sidebar -->
        <script src="assets/user/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="assets/user/js/custom.js"></script>
        <!-- javascript --> 
        <script src="assets/user/js/owl.carousel.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>

    </body>
</html>
