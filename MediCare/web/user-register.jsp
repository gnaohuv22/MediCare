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
        <title>Đăng ký | MediCare</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <jsp:include page="user-head.jsp"/>
    </head>
    <body>
        <%@include file="user-header.jsp" %>

        <div class="main-wrapper account-wrapper main-wrapper-register">
            <div class="account-page account-page-register ">
                <div class="account-center">
                    <div class="account-box">
                        <h1 style="font-weight: bold">Register</h1>
                        <form action="user-register" method="POST" class="form-signin">
                            <div class="account-logo">
                                <a href="${pageContext.request.contextPath}/user-register"><img src="img/logo.png" alt=""></a>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" id="email" autofocus="" value="${requestScope.email}" name="email" maxlength="32" placeholder="example@example.com" required class="form-control">
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
                                <input type="password" id="phone" name="phone" placeholder="Eg: 0123456789"  value="${phone}" maxlength="10" class="form-control">
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary account-btn"  onclick="handleSubmit()">Register</button>
                            </div>
                            <div class="form-group text-center">
                                <p style="color: red">${errorMessage}</p>
                            </div>
                            <div class="text-center register-link" style="text-decoration: underline">
                                <a href="${pageContext.request.contextPath}/user-login" >I am already a member</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="account-image account-image-register">
                <img src="${pageContext.request.contextPath}/assets/client/images/login-banner.jpg" alt="Medicare Login Banner"/>
            </div>

        </div>
        <button id="back-to-top" title="Back to top">↑</button>
        <script>
            function validateForm() {
                var email = document.getElementById("email");
                var password = document.getElementById("password");
                var repassword = document.getElementById("repassword");
                var name = document.getElementById("name");
                var address = document.getElementById("address");
                var phone = document.getElementById("phone");
                var birthDate = document.getElementById("birthDate");

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
                var birthDate = new Date(birthDate.value.trim());
                var age = today.getFullYear() - birthDate.getFullYear();
                var m = today.getMonth() - birthDate.getMonth();
                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                    age--;
                }

                if (age < 12) {
                    birthDate.setCustomValidity("Yêu cầu người dùng ít nhất 12 tuổi!");
                    birthDate.reportValidity();
                    return false;
                } else {
                    birthDate.setCustomValidity("");
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

        <%--<%@include file="user-footer.jsp" %>--%>
        <!-- Javascript files-->
        <script src="${pageContext.request.contextPath}/assets/client/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/jquery-3.0.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/plugin.js"></script>
        <!-- sidebar -->
        <script src="${pageContext.request.contextPath}/assets/client/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/client/js/custom.js"></script>
        <!-- javascript -->
        <script src="${pageContext.request.contextPath}/assets/client/js/owl.carousel.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
    </body>
</html>
