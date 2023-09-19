
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trang đăng nhập cho nhân viên</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/LoginEmployeePage.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
<!--        <div id="square" class="loginForm">
        <form action="DispatchController" method="POST">
            Email:<input type="text" name="email" value="" /><br/>
            Password:<input type="password" name="password" value="" /><br/>
            <input type="submit" value="LoginEmployee" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        ${MESSAGE}
        <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/MedicalServiceProject/LoginGoogleEmployee&response_type=code&client_id=715355702366-sf7g8mndhsmfp23df64m7rkf7f7a5526.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>  
        <br>
        </div>
        <br>-->
        <div class="container">
            <h2>Đăng nhập nhân viên</h2>
            <form action="DispatchController" method="POST">
                Email:<input type="text" name="email" value="" /><br/>
                Mật khẩu:<input type="password" name="password" value="" /><br/>
                <input type="submit" value="LoginEmployee" name="btAction" />
            </form>
            ${MESSAGE}<br>
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/MedicalServiceProject/LoginGoogleEmployee&response_type=code&client_id=715355702366-sf7g8mndhsmfp23df64m7rkf7f7a5526.apps.googleusercontent.com&approval_prompt=force">Đăng nhập với Google</a>  
        <br>
        </div>
    </body>
</html>
