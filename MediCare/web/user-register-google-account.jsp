<%-- 
    Document   : login
    Created on : Sep 19, 2023, 12:36:10 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="icon" href="assets/client/imagesimages/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="assets/client/css/jquery.mCustomScrollbar.min.css" />
        <!-- Tweaks for older IEs-->
        <link
            rel="stylesheet"
            href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
            />
        <!-- owl stylesheets -->
        <link rel="stylesheet" href="assets/client/css/owl.carousel.min.css" />
        <link rel="stylesheet" href="assets/client/css/owl.theme.default.min.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
            media="screen"
            />
        <title>JSP Page</title>
    </head>
    <body>

        <div class="main-wrapper account-wrapper main-wrapper-register">
            <div class="account-page account-page-register ">
                <div class="account-center">
                    <div class="account-box">
                        <h1 style="font-weight: bold">Register</h1>
                        <form action="user-register-google-handler" method="POST" class="form-signin">
                            <div class="account-logo">
                                <a href="user-register"><img src="img/logo.png" alt=""></a>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" id="email" autofocus="" value="${requestScope.email}" name="email" maxlength="32" placeholder="example@example.com" readonly required class="form-control">
                            </div>
                            <c:if test="${requestScope.error == null}">
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" id="password" name="password" maxlength="32" placeholder="Password" required class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Re-password</label>
                                    <input type="password" id="repassword" name="repassword" maxlength="32" placeholder="Password" required class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input type="text" id="name" name="name" value="${requestScope.name}" maxlength="32" placeholder="Eg: Nguyen Van A" required class="form-control">
                                </div>

                            </c:if>


                            <h2 style="color: red">${requestScope.error}</h2>

                            <div class="form-group text-center">
                                <p style="color: red">${errorMessage}</p>
                            </div>
                            <c:if test="${requestScope.error == null}">
                                <div class="form-group text-center">
                                    <button type="submit" class="btn btn-primary account-btn" onclick="handleSubmit()">Register</button>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.error != null}">
                                <div class="text-center register-link" style="text-decoration: underline">
                                    <a style="color: red" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/MediCare/user-login-google-handler&response_type=code&client_id=304835980690-njlmvsh5aa80tmn61q83410iutm5s1q9.apps.googleusercontent.com&approval_prompt=force">Login with Google</a>
                                </div>
                                <div class="text-center register-link" style="text-decoration: underline">
                                    <a style="color: blue" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/MediCare/user-register-google-handler&response_type=code&client_id=304835980690-njlmvsh5aa80tmn61q83410iutm5s1q9.apps.googleusercontent.com&approval_prompt=force">Try to register with Google</a>
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
            <div class="account-image account-image-register">
                <img src="assets/client/images/login-banner.jpg" alt="Medicare Login Banner"/>
            </div>
            <c:if test="${not empty confirmSuccess}">
                <script>
                    //Set a delay for 3 seconds
                    setTimeout(function () {
                        window.location.href = "user-login";
                    }, 3000);
                </script>
            </c:if>
        </div>
        <script>
            function validateForm() {
                var email = document.getElementById("email");
                var password = document.getElementById("password");
                var repassword = document.getElementById("repassword");
                var name = document.getElementById("name");
                // Kiểm tra xem email có giá trị trước khi kiểm tra password
                if (!email.value.trim()) {
                    email.setCustomValidity("Vui lòng nhập địa chỉ email.");
                    email.reportValidity();
                    return false;
                } else {
                    email.setCustomValidity("");
                }


                if (email.value.trim() && !/^([a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z]{2,})+)*$/.test(email.value.trim())) {
                    email.setCustomValidity("Địa chỉ email không hợp lệ, vui lòng nhập lại.");
                    email.reportValidity();
                    return false;
                } else {
                    email.setCustomValidity("");
                }

                if (password.value.trim().length < 8) {
                    password.setCustomValidity("Mật khẩu phải bao gồm ít nhất 8 kí tự.");
                    password.reportValidity();
                    return false;
                } else {
                    password.setCustomValidity("");
                }

                if (password.value.trim() !== repassword.value.trim()) {
                    repassword.setCustomValidity("Vui lòng nhập lại giống với mật khẩu.");
                    repassword.reportValidity();
                    return false;
                } else {
                    repassword.setCustomValidity("");
                }

                if (!/^[a-zA-Z0-9\s-àáảãạăắằẳẵặâấầẩẫậèéẻẽẹêếềểễệđòóỏõọôốồổỗộơớờởỡợùúủũụưứừửữựìíỉĩịỳýỷỹỵ]+$/i.test(name.value.trim())) {
                    name.setCustomValidity("Tên chỉ được chứa chữ cái, số, khoảng trắng và dấu gạch ngang.");
                    name.reportValidity();
                    return false;
                } else {
                    name.setCustomValidity("");
                }

                return true;
            }

            function handleSubmit() {
                if (!validateForm()) {
                    event.preventDefault();
                }
            }
        </script>

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
