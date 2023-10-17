<%-- 
    Document   : add-patient.jsp
    Created on : Sep 21, 2023, 1:02:09 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <jsp:include page="../admin-general/admin-head.jsp"/>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/admin-employee.css">
    </head>
    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
            <jsp:include page="../admin-general/admin-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <h4 class="page-title">Thêm người dùng</h4>
                        </div>
                    </div>
                    
                    <c:set var="error" value="${REGISTER_ERROR}"></c:set>
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <form action="${pageContext.request.contextPath}/admin-list-user?add-user=true" method="POST"> 
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>ID</label>
                                            <input class="form-control" type="text" name="id" value="${id}">
                                        </div>
                                            <p class="error-notice">${error.getIdError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Email </label>
                                            <input class="form-control" type="email" name="email" value="${email}">
                                        </div>
                                            <p class="error-notice">${error.getEmailError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Mật khẩu</label>
                                            <input class="form-control" type="password" name="password" value="${password}">
                                        </div>
                                            <p class="error-notice">${error.getPasswordError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Tên </label>
                                            <input class="form-control" type="text" name="name" value="${name}">
                                        </div>
                                            <p class="error-notice">${error.getNameError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Ngày sinh</label>
                                            <!--<div class="cal-icon">-->
                                            <div class="form-group">
                                                <input class="form-control datetimepicker" type="date" name="birthDate" value="${birthDate}">
                                            </div>
                                                       <p class="error-notice">${error.getBirthDateError()}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Giới tính </label>
                                                <span style="font-size:20px;color:#000;margin-left:36px;padding:5px 5px;">
                                                        <input type="radio" name="gender" value="0" <c:if test="${edit.gender != 1}">checked="checked"</c:if>/>Nam
                                                        <input type="radio" name="gender" value="1" <c:if test="${edit.gender == 1}">checked="checked"</c:if>/>Nữ
                                                </span>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Địa chỉ </label>
                                            <input class="form-control" type="text" name="address" value="${address}" >
                                        </div>
                                            <p class="error-notice">${error.getAddressError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Tỉnh</label>
                                            <select class="select" name="provinceId" value="${provinceId}">
                                                <c:forEach var="list" items="${ALL_PROVINCE}">
                                                    <option value="${list.getId()}" <c:if test="${list.getId() eq provinceId}">selected</c:if>>${list.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Số CCCD</label>
                                            <input class="form-control" type="text" name="identity" value="${identity}" >
                                        </div>
                                            <p class="error-notice">${error.getIdentityError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Số BHYT</label>
                                            <input class="form-control" type="text" name="medicalId" value="${medicalId}" >
                                        </div>
                                            <p class="error-notice">${error.getMedicalIdError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Dân tộc </label>
                                            <input class="form-control" type="text" name="ethnic" value="${ethnic}" >
                                        </div>
                                            <p class="error-notice">${error.getEthnicError()}</p>
                                    </div> 
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Số điện thoại</label>
                                            <input class="form-control" type="text" name="phone" value="${phone}" >
                                        </div>
                                            <p class="error-notice">${error.getPhoneError()}</p>
                                    </div> 
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Ảnh</label>
                                            <input class="form-control" type="text" name="profilePicture" value="${profilePicture}" >
                                        </div>
                                            <p class="error-notice">${error.getProfilePictureError()}</p>
                                    </div>
                                            
<!--                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Ảnh</label>
                                            <div class="profile-upload">
                                                <div class="upload-img">
                                                    <img alt="" src="${pageContext.request.contextPath}/assets/img/user.jpg">
                                                </div>
                                                <div class="upload-input">
                                                    <input name="profilePicture" type="file" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <p class="error-notice">${error.getProfilePictureError()}</p>
                                    </div>-->
                                </div>
<!--                                <div class="form-group">
                                    <label class="display-block">Status</label>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="employee_active" value="option1" checked>
                                        <label class="form-check-label" for="employee_active">
                                            Active
                                        </label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="employee_inactive" value="option2">
                                        <label class="form-check-label" for="employee_inactive">
                                            Inactive
                                        </label>
                                    </div>
                                </div>-->
                                <div class="m-t-20 text-center">
                                    <button class="btn btn-primary submit-btn" value="Add User" name="btAction">Đăng kí</button>
                                </div>
                                <div class="m-t-20 text-center">
                                    <h3 style="color: red">${MESSAGE}</h3>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="assets/admin/js/popper.min.js"></script>
        <script src="assets/admin/js/bootstrap.min.js"></script>
        <script src="assets/admin/js/jquery.slimscroll.js"></script>
        <script src="assets/admin/js/select2.min.js"></script>
        <script src="assets/admin/js/moment.min.js"></script>
        <script src="assets/admin/js/bootstrap-datetimepicker.min.js"></script>
        <!--<script src="assets/admin/js/app.js"></script>-->
    </body>
</html>
