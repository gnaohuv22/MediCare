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
        <link rel="stylesheet" href="assets/user/css/bootstrap.min.css" />
        <!-- style css -->
        <link rel="stylesheet" href="assets/user/css/style.css" />
        <!-- Responsive-->
        <link rel="stylesheet" href="assets/user/css/responsive.css" />
        <!-- fevicon -->
        <link rel="icon" href="assets/user/images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="assets/user/css/jquery.mCustomScrollbar.min.css" />
        <!-- Tweaks for older IEs-->
        <link
            rel="stylesheet"
            href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/assets/user/css/font-awesome.css"
            />
        <!-- owl stylesheets -->
        <link rel="stylesheet" href="assets/user/css/owl.carousel.min.css" />
        <link rel="stylesheet" href="assets/user/css/owl.theme.default.min.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
            media="screen"
            />
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="user-header.jsp" %>

        <div class="main-wrapper account-wrapper">
            <div class="account-page">
                <div class="account-center">
                    <div class="account-box">
                        <h1 style="font-weight: bold">Login</h1>
                        <form action="user-login" method="POST" class="form-signin">
                            <div class="account-logo">
                                <a href="login"><img src="img/logo.png" alt=""></a>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" autofocus="" value="${sessionScope.email}" name="email" maxlength="32" placeholder="example@example.com" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="password" maxlength="32" placeholder="Password" class="form-control">
                            </div>
                            <div class="form-group text-center">
                                <p style="color: red">${error}</p>
                            </div>
                            <div class="form-group text-right">
                                <a href="#">Forgot your password?</a>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary account-btn">Login</button>
                            </div>
                            <div class="text-center bottom-login">
                                <a style="color: blue" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/MediCare/user-login-google-handler&response_type=code&client_id=304835980690-njlmvsh5aa80tmn61q83410iutm5s1q9.apps.googleusercontent.com&approval_prompt=force">Login with Google</a>
                            </div>
                            <div class="text-center register-link">
                                Don’t have an account? <a href="user-register" style="color: red">Register Now</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="account-image">
                <img src="assets/user/images/banner-account.jpg" alt="Medicare Login Banner"/>
            </div>
        </div>


        <%@include file="user-footer.jsp" %>
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
