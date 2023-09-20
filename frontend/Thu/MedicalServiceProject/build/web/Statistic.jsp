<%-- 
    Document   : Statistic
    Created on : Sep 18, 2023, 11:08:44 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang thống kê</title>
    </head>
    <body>
        <a href="StatisticEmployee">thống kê nhân viên</a>
        <br>
        <c:if test="${not empty E_OK}">
            <table border = 1>
            <tr>
            <th>id</th>
            <th>email</th>
            <th>Mật khẩu</th>
            <th>branchId</th>
            <th>Tên</th>
            <th>Ngày sinh</th>
            <th>Giới tính</th>
            <th>Địa chỉ</th>
            <th>Nơi làm việc</th>
            <th>provinceId</th>
            <th>Điện thoại</th>
            <th>Dân tộc</th>
            <th>roleId</th>
            <th>Ngày tạo</th>
            </tr>
            <c:forEach var="list" items="${ALL_EMPLOYEE}">
            <tr border = 1>
                <td>${list.getId()}</td>
                <td>${list.getEmail()}</td>
                <td>${list.getPassword()}</td>
                <td>${list.getBranchId()}</td>
                <td>${list.getName()}</td>
                <td>${list.getBirthDate()}</td>
                <td>${list.getGender()}</td>
                <td>${list.getAddress()}</td>
                <td>${list.getWorkplace()}</td>
                <td>${list.getProvinceId()}</td>
                <td>${list.getPhone()}</td>
                <td>${list.getEthnic()}</td>
                <td>${list.getRoleId()}</td>
                <td>${list.getCreateAt()}</td>
            </tr>
            </c:forEach>
            </table>
        </c:if>
        <br>
    </body>
</html>
