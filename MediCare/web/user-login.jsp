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
        <title>Đăng nhập | MediCare</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <jsp:include page="user-head.jsp"/>
    </head>
    <body>
        <%@include file="user-header.jsp" %>
        <div class="main-wrapper account-wrapper">
            <div class="account-page">
                <div class="account-center">
                    <div class="account-box">
                        <h1 style="font-weight: bold">Đăng nhập</h1>
                        <form action="user-login" method="POST" class="form-signin">
                            <div class="account-logo">
                                <a href="login"><img src="img/logo.png" alt=""></a>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" autofocus="" value="${sessionScope.email}" name="email" maxlength="32" placeholder="example@example.com" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu</label>
                                <input type="password" name="password" maxlength="32" placeholder="Mật khẩu" class="form-control">
                            </div>
                            <div class="form-group text-center">
                                <p style="color: red">${error}</p>
                            </div>
                            <div class="form-group text-right">
                                <a href="${pageContext.request.contextPath}/#">Quên mật khẩu?</a>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary account-btn">Đăng nhập</button>
                            </div>
                            <div class="text-center bottom-login">
                                <a style="color: blue" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/MediCare/user-login-google-handler&response_type=code&client_id=304835980690-njlmvsh5aa80tmn61q83410iutm5s1q9.apps.googleusercontent.com&approval_prompt=force">Đăng nhập với Google</a>
                            </div>
                            <div class="text-center register-link">
                                Bạn chưa có tài khoản? <a href="${pageContext.request.contextPath}/user-register" style="color: red">Đăng ký ngay bây giờ</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="account-image">
                <img src="${pageContext.request.contextPath}/assets/client/images/banner-account.jpg" alt="Medicare Login Banner"/>
            </div>
        </div>


        <%@include file="user-footer.jsp" %>
        <button id="back-to-top" title="Back to top">↑</button>
        <%@include file="user-script.jsp" %>
    </body>
</html>
