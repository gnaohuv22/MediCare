<%-- 
    Document   : RegisterHealthcareSpecialist
    Created on : Sep 10, 2023, 1:49:43 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng kí nhân viên</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Đăng kí nhân viên</h1>
        <c:set var="error" value="${REGISTER_ERROR}"></c:set>
        <%--<c:if test="${empty ADMIN}">--%>
            <!--Bạn cần quyền để truy cập vào trang này-->
        <%--</c:if>--%>
        <%--<c:if test="${not empty ADMIN}">--%>
            <form action="DispatchController" method="POST">    
                id:<input type="text" name="id" value="${id}" />${error.getIdError()}<br>
                email:<input type="text" name="email" value="${email}" />${error.getEmailError()}<br>
                Mật khẩu:<input type="password" name="password" value="${password}" />${error.getPasswordError()}<br>
                branchId:
                <select name="branchId" value="${branchId}">
                    <c:forEach var="list" items="${ALL_BRANCH}">
                        <option value="${list.getId()}">${list.getName()}</option>
                    </c:forEach>
                </select>
                <br>
                Tên:<input type="text" name="name" value="${name}" />${error.getNameError()}<br>
                Ngày sinh:<input type="date" name="birthDate" value="${birthDate}" />${error.getBirthDateError()}<br>
                Giới tính:<br>
                <input type="radio" name="gender" value="0" />Nam<br>
                <input type="radio" name="gender" value="1" />Nữ<br>
                Địa chỉ:<input type="text" name="address" value="${address}" />${error.getAddressError()}<br>
                Nơi làm việc:<input type="text" name="workplace" value="${workplace}" />${error.getWorkplaceError()}<br>
                provinceId:
                <select name="provinceId" value="${provinceId}">
                    <c:forEach var="list" items="${ALL_PROVINCE}">
                        <option value="${list.getId()}">${list.getName()}</option>
                    </c:forEach>
                </select>
                <br>
                Điện thoại:<input type="text" name="phone" value="${phone}" />${error.getPhoneError()}<br>
                Dân tộc:<input type="text" name="ethnic" value="${ethnic}" />${error.getEthnicError()}<br>
                roleId:
                <select name="roleId" value="${roleId}">
                    <c:forEach var="list" items="${ALL_EMPLOYEEROLE}">
                        <option value="${list.getId()}">${list.getRole()}</option>
                    </c:forEach>
                </select>
                <br>
                
                <input type="submit" value="Register Employee" name="btAction" />
            </form>
                ${MESSAGE}
        <%--</c:if>--%>
        
    </body>
</html>
