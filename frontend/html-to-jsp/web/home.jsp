<%-- 
    Document   : home
    Created on : Sep 16, 2023, 11:10:25 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
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
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- style css -->
        <link rel="stylesheet" href="css/style.css" />
        <!-- Responsive-->
        <link rel="stylesheet" href="css/responsive.css" />
        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css" />
        <!-- Tweaks for older IEs-->
        <link
            rel="stylesheet"
            href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
            />
        <!-- owl stylesheets -->
        <link rel="stylesheet" href="css/owl.carousel.min.css" />
        <link rel="stylesheet" href="css/owl.theme.default.min.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
            media="screen"
            />
    </head>


    <body>
        <%@include file="header.jsp" %>
        <!-- banner section start -->
        <div id="main_slider" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="banner_section">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <h1 class="banner_taital">
                                        Health <br /><span style="color: #151515">Care</span>
                                    </h1>
                                    <p class="banner_text">
                                        There are many variations of passages of Lorem Ipsum
                                    </p>
                                    <div class="btn_main">
                                        <div class="more_bt"><a href="#">Contact Now</a></div>
                                        <div class="contact_bt"><a href="#">Get A Quote</a></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="image_1"><img src="images/img-1.png" /></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="banner_section">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <h1 class="banner_taital">
                                        Health <br /><span style="color: #151515">Care</span>
                                    </h1>
                                    <p class="banner_text">
                                        There are many variations of passages of Lorem Ipsum
                                    </p>
                                    <div class="btn_main">
                                        <div class="more_bt"><a href="#">Contact Now</a></div>
                                        <div class="contact_bt"><a href="#">Get A Quote</a></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="image_1"><img src="images/img-1.png" /></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="banner_section">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <h1 class="banner_taital">
                                        Health <br /><span style="color: #151515">Care</span>
                                    </h1>
                                    <p class="banner_text">
                                        There are many variations of passages of Lorem Ipsum
                                    </p>
                                    <div class="btn_main">
                                        <div class="more_bt"><a href="#">Contact Now</a></div>
                                        <div class="contact_bt"><a href="#">Get A Quote</a></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="image_1"><img src="images/img-1.png" /></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <a
                class="carousel-control-prev"
                href="#main_slider"
                role="button"
                data-slide="prev"
                >
                <i
                    class="fa fa-long-arrow-left"
                    style="font-size: 24px; padding-top: 4px"
                    ></i>
            </a>
            <a
                class="carousel-control-next"
                href="#main_slider"
                role="button"
                data-slide="next"
                >
                <i
                    class="fa fa-long-arrow-right"
                    style="font-size: 24px; padding-top: 4px"
                    ></i>
            </a>
        </div>
    </div>
    <!-- banner section end -->
    <!-- health section start -->
    <div class="health_section layout_padding">
        <div class="container">
            <h1 class="health_taital">Best Of Health care for you</h1>
            <p class="health_text">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                minim veniam, quis
            </p>
            <div class="health_section layout_padding">
                <div class="row">
                    <div class="col-sm-7">
                        <div class="image_main">
                            <div class="main">
                                <img
                                    src="images/img-2.png"
                                    alt="Avatar"
                                    class="image"
                                    style="width: 100%"
                                    />
                            </div>
                            <div class="middle">
                                <div class="text">
                                    <img src="images/icon-1.png" style="width: 40px" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <div class="image_main_1">
                            <div class="main">
                                <img
                                    src="images/img-3.png"
                                    alt="Avatar"
                                    class="image"
                                    style="width: 100%"
                                    />
                            </div>
                            <div class="middle">
                                <div class="text">
                                    <img src="images/icon-1.png" style="width: 40px" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="getquote_bt_1">
                    <a href="#"
                       >Read More <span><img src="images/right-arrow.png" /></span
                        ></a>
                </div>
            </div>
        </div>
    </div>
    <!-- health section end -->
    <!-- knowledge section end -->
    <div class="knowledge_section layout_padding">
        <div class="container">
            <div class="knowledge_main">
                <div class="left_main">
                    <h1 class="knowledge_taital">Knowledge of center</h1>
                    <p class="knowledge_text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                        enim ad minim veniam
                    </p>
                </div>
                <div class="right_main">
                    <div class="play_icon">
                        <a href="#"><img src="images/play-icon.png" /></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- knowledge section end -->
    <!-- news section start -->
    <div class="news_section layout_padding">
        <div class="container">
            <h1 class="health_taital">Why choose 24hr home care</h1>
            <p class="health_text">
                labore et dolore magna aliqua. Ut enim ad minim veniam
            </p>
            <div class="news_section_2 layout_padding">
                <div class="row">
                    <div class="col-lg-4 col-sm-6">
                        <div class="box_main">
                            <div class="icon_1"><img src="images/icon-2.png" /></div>
                            <h4 class="daily_text">Daily care experts</h4>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="box_main active">
                            <div class="icon_1"><img src="images/icon-3.png" /></div>
                            <h4 class="daily_text_1">Available 24/7</h4>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="box_main">
                            <div class="icon_1"><img src="images/icon-4.png" /></div>
                            <h4 class="daily_text_1">Balanced care</h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="getquote_bt">
                <a href="#"
                   >Get A Quote <span><img src="images/right-arrow.png" /></span
                    ></a>
            </div>
        </div>
    </div>
    <!-- news section end -->
    <!-- contact section start -->
    <div class="contact_section layout_padding">
        <div class="container">
            <h1 class="contact_taital">What we do</h1>
            <div class="news_section_2">
                <div class="row">
                    <div class="col-md-6">
                        <div class="icon_main">
                            <div class="icon_7"><img src="images/icon-7.png" /></div>
                            <h4 class="diabetes_text">Diabetes and obesity Counselling</h4>
                        </div>
                        <div class="icon_main">
                            <div class="icon_7"><img src="images/icon-5.png" /></div>
                            <h4 class="diabetes_text">Obstetrics and Gynsecology</h4>
                        </div>
                        <div class="icon_main">
                            <div class="icon_7"><img src="images/icon-6.png" /></div>
                            <h4 class="diabetes_text">Surgical and medical Oncology</h4>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="contact_box">
                            <h1 class="book_text">Book Appoinment</h1>
                            <input
                                type="text"
                                class="Email_text"
                                placeholder="Name"
                                name="Name"
                                />
                            <input
                                type="text"
                                class="Email_text"
                                placeholder="Name"
                                name="Name"
                                />
                            <textarea
                                class="massage-bt"
                                placeholder="Massage"
                                rows="5"
                                id="comment"
                                name="Massage"
                                ></textarea>
                            <div class="send_bt"><a href="#">SEND</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- contact section end -->
    <!-- client section start -->
    <div class="client_section layout_padding">
        <div id="my_slider" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="container">
                        <h1 class="client_taital">What People Say</h1>
                        <p class="client_text">
                            It is a long established fact that a reader will be distracted
                        </p>
                        <div class="client_section_2">
                            <div class="client_left">
                                <div>
                                    <img src="images/client-img.png" class="client_img" />
                                </div>
                            </div>
                            <div class="client_right">
                                <h3 class="distracted_text">Distracted by</h3>
                                <p class="lorem_text">
                                    It is a long established fact that a reader will be
                                    distracted by the readable content of a page when looking at
                                    its layout. The point of using Lorem Ipsum is that it has a
                                    more-or-less normal distribution of letters
                                </p>
                                <div class="quote_icon">
                                    <img src="images/quote-icon.png" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="container">
                        <h1 class="client_taital">What People Say</h1>
                        <p class="client_text">
                            It is a long established fact that a reader will be distracted
                        </p>
                        <div class="client_section_2">
                            <div class="client_left">
                                <div>
                                    <img src="images/client-img.png" class="client_img" />
                                </div>
                            </div>
                            <div class="client_right">
                                <h3 class="distracted_text">Distracted by</h3>
                                <p class="lorem_text">
                                    It is a long established fact that a reader will be
                                    distracted by the readable content of a page when looking at
                                    its layout. The point of using Lorem Ipsum is that it has a
                                    more-or-less normal distribution of letters
                                </p>
                                <div class="quote_icon">
                                    <img src="images/quote-icon.png" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="container">
                        <h1 class="client_taital">What People Say</h1>
                        <p class="client_text">
                            It is a long established fact that a reader will be distracted
                        </p>
                        <div class="client_section_2">
                            <div class="client_left">
                                <div>
                                    <img src="images/client-img.png" class="client_img" />
                                </div>
                            </div>
                            <div class="client_right">
                                <h3 class="distracted_text">Distracted by</h3>
                                <p class="lorem_text">
                                    It is a long established fact that a reader will be
                                    distracted by the readable content of a page when looking at
                                    its layout. The point of using Lorem Ipsum is that it has a
                                    more-or-less normal distribution of letters
                                </p>
                                <div class="quote_icon">
                                    <img src="images/quote-icon.png" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <a
                class="carousel-control-prev"
                href="#my_slider"
                role="button"
                data-slide="prev"
                >
                <i
                    class="fa fa-long-arrow-left"
                    style="font-size: 24px; padding-top: 4px"
                    ></i>
            </a>
            <a
                class="carousel-control-next"
                href="#my_slider"
                role="button"
                data-slide="next"
                >
                <i
                    class="fa fa-long-arrow-right"
                    style="font-size: 24px; padding-top: 4px"
                    ></i>
            </a>
        </div>
    </div>
    <!-- client section end -->
    <%@include file="footer.jsp" %>
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
    <script>
        $(document).ready(function () {
            $("#signin-btn").hover(
                    function () {
                        $(this).css("background-color", "white");
                        $(this).css("color", "green");
                    },
                    function () {
                        $(this).css("background-color", "green");
                        $(this).css("color", "white");
                    }
            );
            $("#register-btn").hover(
                    function () {
                        $(this).css("background-color", "#007bff");
                        $(this).css("color", "white");
                    },
                    function () {
                        $(this).css("background-color", "white");
                        $(this).css("color", "#007bff");
                    }
            );
        });
    </script>
</body>
</html>

