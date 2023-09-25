<%-- 
    Document   : login
    Created on : Sep 19, 2023, 10:28:49 AM
    Author     : phuon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


    <!-- login23:11-->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/admin/images/favicon.png">
        <title>MediCare Doctor</title>
        <link rel="stylesheet" type="text/css" href="assets/admin/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/admin/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/admin/css/style.css">
        <!--[if lt IE 9]>
            <script src="assets/admin/js/html5shiv.min.js"></script>
            <script src="assets/admin/js/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <div class="main-wrapper account-wrapper">
            <div class="account-page">
                <div class="account-center">
                    <div class="account-box">
                        <form action="doctor-login" method="post" class="form-signin">
                            <div class="account-logo">
                                <a href="login"><img src="assets/admin/images/logo-dark.png" alt=""></a>
                            </div>
                            <div class="form-group">
                                <label>Username or Email</label>
                                <input type="text" autofocus="" name="email" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="password" class="form-control">
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
        <script src="assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="assets/admin/js/popper.min.js"></script>
        <script src="assets/admin/js/bootstrap.min.js"></script>
        <script src="assets/admin/js/app.js"></script>
    </body>


    <!-- login23:12-->
</html>
