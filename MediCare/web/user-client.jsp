<%-- 
    Document   : client
    Created on : Sep 16, 2023, 11:28:23 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Client</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <!-- owl stylesheets --> 
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
        <title>Client Page</title>
    </head>

    <body>
        <%@include file="user-header.jsp" %>
        <!-- client section start -->
        <div class="client_section layout_padding">
            <div id="my_slider" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="container">
                            <h1 class="client_taital">What People Say</h1>
                            <p class="client_text">It is a long established fact that a reader will be distracted </p>
                            <div class="client_section_2">
                                <div class="client_left">
                                    <div><img src="images/client-img.png" class="client_img"></div>
                                </div>
                                <div class="client_right">
                                    <h3 class="distracted_text">Distracted by</h3>
                                    <p class="lorem_text">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters</p>
                                    <div class="quote_icon"><img src="images/quote-icon.png"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="container">
                            <h1 class="client_taital">What People Say</h1>
                            <p class="client_text">It is a long established fact that a reader will be distracted </p>
                            <div class="client_section_2">
                                <div class="client_left">
                                    <div><img src="images/client-img.png" class="client_img"></div>
                                </div>
                                <div class="client_right">
                                    <h3 class="distracted_text">Distracted by</h3>
                                    <p class="lorem_text">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters</p>
                                    <div class="quote_icon"><img src="images/quote-icon.png"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="container">
                            <h1 class="client_taital">What People Say</h1>
                            <p class="client_text">It is a long established fact that a reader will be distracted </p>
                            <div class="client_section_2">
                                <div class="client_left">
                                    <div><img src="images/client-img.png" class="client_img"></div>
                                </div>
                                <div class="client_right">
                                    <h3 class="distracted_text">Distracted by</h3>
                                    <p class="lorem_text">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters</p>
                                    <div class="quote_icon"><img src="images/quote-icon.png"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#my_slider" role="button" data-slide="prev">
                    <i class="fa fa-long-arrow-left" style="font-size:24px; padding-top: 4px;"></i>
                </a>
                <a class="carousel-control-next" href="#my_slider" role="button" data-slide="next">
                    <i class="fa fa-long-arrow-right" style="font-size:24px; padding-top: 4px;"></i>
                </a>
            </div>
        </div>
        <%@include file="user-footer.jsp" %>
        <!-- client section end -->
        <!-- Javascript files-->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/jquery-3.0.0.min.js"></script>
        <script src="js/plugin.js"></script>
        <!-- sidebar -->
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="js/custom.js"></script>
        <!-- javascript --> 
        <script src="js/owl.carousel.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>

    </body>
</html>
