<%-- 
    Document   : login
    Created on : Sep 20, 2023, 12:49:47 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <jsp:include page="../admin-general/admin-head.jsp" />

    <body>
        <div class="main-wrapper account-wrapper">
            <div class="account-page">
                <div class="account-center">
                    <div class="account-box">
                        <form  class="form-signin" action="admin-login-employee" method="POST">
                            <div class="account-logo">
                                <a href="admin-home-page"><img src="${pageContext.request.contextPath}/assets/admin/img/logo-dark.png" alt=""></a>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" autofocus="" class="form-control" id="email" value="${email}" name="email" placeholder="example@example.com">
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu</label>
                                <input type="password" class="form-control" name="password" placeholder="Mật khẩu">
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary account-btn" value="LoginEmployee" name="btAction">Đăng nhập</button>
                            </div>
                            <div class="form-group text-center">
                                <a class="btn btn-primary account-btn" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/MediCare/login-google-employee-controller&response_type=code&client_id=715355702366-sf7g8mndhsmfp23df64m7rkf7f7a5526.apps.googleusercontent.com&approval_prompt=force">Đăng nhập với Google</a>  
                            </div>
                            <div class="form-group text-right">
                                <a id="forgotPassword" onclick=forgotPassword()>Quên mật khẩu</a>
                            </div>
                            <div class="form-group text-center">
                                <p style="color: red">${MESSAGE}</p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function forgotPassword(){
                var email = document.getElementById("email").value;
//                window.location.href = "admin-send-email-forgot-password?email="+email;
                window.location.href = "admin-forgot-password?email="+email;
            }
        </script>
        <jsp:include page="../admin-general/admin-script.jsp"/>
    </body>
</html>
