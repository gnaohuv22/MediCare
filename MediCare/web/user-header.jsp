<%-- 
    Document   : header
    Created on : Sep 16, 2023, 11:12:32 PM
    Author     : hoang
--%>

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
    <!-- header section start -->
    <div class="header_section">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="logo">
                <a href="user-home.jsp"><img src="images/logo.png" /></a>
            </div>
            <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
                >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="mx-auto">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="user-home.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="user-health.jsp">Health</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="user-medicine.jsp">Medicine</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="user-news.jsp">News</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="user-client.jsp">Client</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="user-contact.jsp">Contact Us</a>
                        </li>
                    </ul>
                </div>

                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a
                            class="nav-link"
                            href="user-login" 
                            id="signin-btn"
                            style="
                            background-color: #68b2a0;
                            color: white;
                            border-radius: 10px;
                            margin-right: 5px;
                            "
                            >Sign-in</a
                        >
                    </li>
                    <li class="nav-item">
                        <a
                            class="nav-link"
                            href="user-register"
                            id="register-btn"
                            -btn
                            style="
                            background-color: white;
                            color: #2c6975;
                            border-radius: 10px;
                            margin-left: 5px;
                            "
                            >Register</a
                        >
                    </li>
                </ul>
            </div>
        </nav>
        <!-- header section end -->

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
                            $(this).css("color", "#68b2a0");
                        },
                        function () {
                            $(this).css("background-color", "#68b2a0");
                            $(this).css("color", "white");
                        }
                );
                $("#register-btn").hover(
                        function () {
                            $(this).css("background-color", "#2c6975");
                            $(this).css("color", "white");
                        },
                        function () {
                            $(this).css("background-color", "white");
                            $(this).css("color", "#2c6975");
                        }
                );
            });
        </script>
</html>
