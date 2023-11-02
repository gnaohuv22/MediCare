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
        <title>Đăng ký | MediCare</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <jsp:include page="user-head.jsp"/>
    </head>
    <body>

        <div class="main-wrapper account-wrapper main-wrapper-register">
            <div class="account-page account-page-register ">
                <div class="account-center">
                    <div class="account-box">
                        <h1 style="font-weight: bold">Register</h1>
                        <form action="user-register" method="POST" class="form-signin">
                            <div class="account-logo">
                                <a href="user-register"><img src="img/logo.png" alt=""></a>
                            </div>
                            <div class="form-group">
                                                                <label>Email</label>
                                                                <input type="text" id="email" value="${requestScope.email}" name="email" maxlength="32" placeholder="example@example.com" required class="form-control">

<!--                                <label>Email</label>
                                <input type="text" id="email" name="email" placeholder="example@example.com" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}" title="Địa chỉ email không hợp lệ, vui lòng nhập lại.">-->


                            </div>
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
                                <input type="text" id="name" name="name" ${name} maxlength="32" placeholder="Eg: Nguyen Van A" required class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Date of birth</label>
                                <input type="date" id="birthDate" name="birthDate" value="${requestScope.birthDate}" required class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Gender</label> <br>
                                <input type="radio" id="gender" checked name="gender"> Male &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="radio" id="gender" name="gender" ${gender.equals('1')?'checked':''} value="1"> Female
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <input type="text" id="address" name="address" maxlength="255" placeholder="Eg: Thạch Thất - Hà Nội"  value="${address}" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input type="tel" id="phone" name="phone" placeholder="Eg: 0123456789"  value="${phone}" maxlength="10" pattern="[0-9]{10}" class="form-control">
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary account-btn" onclick="handleSubmit()">Register</button>
                            </div>
                            <div class="form-group text-center">
                                <p style="color: red">${errorMessage}</p>
                            </div>
                            <div class="text-center register-link" style="text-decoration: underline">
                                <a href="user-login" >I am already a member</a>
                            </div>
                            <div class="text-center register-link" style="text-decoration: underline">
                                <!--<a href="user-register?value=1" >Register with Google Account</a>-->
                                <a style="color: blue" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/MediCare/user-register-google-handler&response_type=code&client_id=304835980690-njlmvsh5aa80tmn61q83410iutm5s1q9.apps.googleusercontent.com&approval_prompt=force">Register with Google</a>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="account-image account-image-register">
                <img src="assets/client/images/login-banner.jpg" alt="Medicare Login Banner"/>
            </div>

        </div>
        <button id="back-to-top" title="Back to top">↑</button>
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
                var address = document.getElementById("address");
                var phone = document.getElementById("phone");
                var birthDate1 = document.getElementById("birthDate");
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

                if (address.value.trim() !== "") {
                    if (!/^[a-zA-Z0-9\s.,#'-àáảãạăắằẳẵặâấầẩẫậèéẻẽẹêếềểễệđòóỏõọôốồổỗộơớờởỡợùúủũụưứừửữựìíỉĩịỳýỷỹỵ]+$/i.test(address.value.trim())) {
                        address.setCustomValidity("Địa chỉ không hợp lệ.");
                        address.reportValidity();
                        return false;
                    } else {
                        address.setCustomValidity("");
                    }
                } else {
                    address.setCustomValidity(""); // Không cần validate nếu địa chỉ không được điền
                }



                var today = new Date();
                var birthDate = new Date(birthDate1.value.trim());
                var age = today.getFullYear() - birthDate.getFullYear();
                var m = today.getMonth() - birthDate.getMonth();
                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                    age--;
                }

                if (age < 12) {
                    console.log("Age < 12");
                    birthDate1.setCustomValidity("Yêu cầu người dùng ít nhất 12 tuổi!");
                    birthDate1.reportValidity();
                    return false;
                } else {
                    birthDate1.setCustomValidity("");
                }

                if (!/^\d{10}$/.test(phone.value.trim())) {
                    phone.setCustomValidity("Số điện thoại chỉ được chứa số và phải có đúng 10 chữ số.");
                    phone.reportValidity();
                    return false;
                } else {
                    phone.setCustomValidity("");
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
