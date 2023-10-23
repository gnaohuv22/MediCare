<%-- 
    Document   : change-password.jsp
    Created on : Sep 21, 2023, 12:06:33 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../admin-general/admin-head.jsp" />
    <body>
          <div class="main-wrapper account-wrapper">
        <div class="account-page">
			<div class="account-center">
                <div class="account-box">
                    <form class="form-signin" action="#">
						<div class="account-logo">
                            <a href="homehome.html"><img src="../assets/admin/img/logo-dark.png" alt=""></a>
                        </div>
                        <div class="form-group">
                            <label>Current Password</label>
                            <input type="text" class="form-control" autofocus>
                        </div>
                        <div class="form-group text-center">
                            <button class="btn btn-primary account-btn" type="submit">Reset Password</button>
                        </div>
                        <div class="text-center register-link">
                            <a href="login.html">Back to Login</a>
                        </div>
                    </form>
                </div>
			</div>
        </div>
    </div>
    <div class="main-wrapper">
        <div class="account-page">
            <div class="container">
                <h3 class="account-title">Change Password</h3>
                <div class="account-box">
                    <div class="account-wrapper">
                        <div class="account-logo">
                            <a href="homehome.html"><img src="../assets/admin/img/logo.png" alt=""></a>
                        </div>
                        <form action="http://dreamguys.co.in/preclinic/template/index.html">
                            <div class="form-group form-focus">
                                <label class="focus-label">Current Password</label>
                                <input class="form-control floating" type="password">
                            </div>
                            <div class="form-group form-focus">
                                <label class="focus-label">New Password</label>
                                <input class="form-control floating" type="password">
                            </div>
                            <div class="form-group form-focus">
                                <label class="focus-label">New Repeat Password</label>
                                <input class="form-control floating" type="password">
                            </div>
                            <div class="form-group mb-0 text-center">
                                <button class="btn btn-primary btn-block account-btn" type="submit">Change Password</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>
    </body>
</html>
