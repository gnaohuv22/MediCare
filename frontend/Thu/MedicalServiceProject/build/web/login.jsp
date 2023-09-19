<%-- 
    Document   : login
    Created on : Sep 9, 2023, 9:40:57 AM
    Author     : tubinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <style>
        /* Basic CSS for styling the login form */
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f2f2f2;
        }
        .container {
            background-color: #ffffff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0px 0px 10px #ccc;
            padding: 20px;
            width: 300px;
            margin: 0 auto;
            margin-top: 100px;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }
        .error {
            color: red;
            margin-top: 10px; /* Add margin to separate the error message from the form */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form action="login-user" method="post">
            Email: <input type="text" name="email" value="${sessionScope.email}"><br><br>
            Password: <input type="password" name="password" value="${sessionScope.password}"><br><br>
            <input type="submit" value="Login">
        </form>
            <p class="error">${error}</p>
    </div>
</body>
</html>
