<%-- 
    Document   : login
    Created on : Sep 19, 2023, 12:36:10 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="main-wrapper account-wrapper">
            <div class="account-page">
                <div class="account-center">
                    <div class="account-box">
                        <form action="login" method="POST" class="form-signin">
                            <div class="account-logo">
                                <a href="login"><img src="img/logo.png" alt=""></a>
                            </div>
                            <div class="form-group">
                                <label>Username or Email</label>
                                <input type="text" autofocus="" name="email" maxlength="32" placeholder="example@example.com" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="password" maxlength="32" placeholder="Password" class="form-control">
                            </div>
                            <div class="form-group text-right">
                                <a href="forgot">Forgot your password?</a>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary account-btn">Login</button>
                            </div>
                            <div class="text-center register-link">
                                Don’t have an account? <a href="register">Register Now</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


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
