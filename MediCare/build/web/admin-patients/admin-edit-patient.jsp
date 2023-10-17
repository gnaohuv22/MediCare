<%-- 
    Document   : edit-employee
    Created on : Sep 21, 2023, 1:15:42 AM
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
                            <h4 class="page-title">Sửa người dùng</h4>
                        </div>
                    </div>
                    <c:set var="edit" value="${EDIT_USER}"></c:set>
                    <c:set var="error" value="${EDIT_ERROR}"></c:set>
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <form action="${pageContext.request.contextPath}/admin-list-user?edit-user=true" method="POST"> 
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>ID</label>
                                            <input type="hidden" name="id" value="${edit.id}">
                                            <p class="form-control">${edit.id}</p>
                                        </div>
                                            <p class="error-notice">${error.getIdError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Email </label>
                                            <input class="form-control" type="email" name="email" value="${edit.email}">
                                        </div>
                                            <p class="error-notice">${error.getEmailError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Mật khẩu mới (không bắt buộc)</label>
                                            <input type="hidden" name="password" value="${edit.password}">
                                            <input class="form-control" type="password" name="newPassword">
                                        </div>
                                            <p class="error-notice">${error.getPasswordError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Tên </label>
                                            <input class="form-control" type="text" name="name" value="${edit.name}">
                                        </div>
                                            <p class="error-notice">${error.getNameError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Ngày sinh</label>
                                            <!--<div class="cal-icon">-->
                                            <div class="form-group">
                                                <input class="form-control datetimepicker" type="date" name="birthDate" value="${edit.birthDate}">
                                            </div>
                                                       <p class="error-notice">${error.getBirthDateError()}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Giới tính </label>
                                                <span style="font-size:20px;color:#000;margin-left:36px;padding:5px 5px;">
                                                    <input type="radio" name="gender" value="0" <c:if test="${edit.gender==0}">checked="checked"</c:if>/>Nam
                                                        <input type="radio" name="gender" value="1" <c:if test="${edit.gender==1}">checked="checked"</c:if>/>Nữ
                                                </span>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Địa chỉ </label>
                                            <input class="form-control" type="text" name="address" value="${edit.address}" >
                                        </div>
                                            <p class="error-notice">${error.getAddressError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Tỉnh</label>
                                            <select class="select" name="provinceId" value="${provinceId}">
                                                <c:forEach var="list" items="${ALL_PROVINCE}">
                                                    <option value="${list.getId()}" <c:if test="${list.getId() eq edit.province.id}">selected</c:if>>${list.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Số CCCD</label>
                                            <input class="form-control" type="text" name="identity" value="${edit.identity}" >
                                        </div>
                                            <p class="error-notice">${error.getIdentityError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Số BHYT</label>
                                            <input class="form-control" type="text" name="medicalId" value="${edit.medicalId}" >
                                        </div>
                                            <p class="error-notice">${error.getMedicalIdError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Dân tộc </label>
                                            <input class="form-control" type="text" name="ethnic" value="${edit.ethnic}" >
                                        </div>
                                            <p class="error-notice">${error.getEthnicError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Số điện thoại</label>
                                            <input class="form-control" type="text" name="phone" value="${edit.phone}" >
                                        </div>
                                            <p class="error-notice">${error.getPhoneError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Ảnh</label>
                                            <input class="form-control" type="text" name="profilePicture" value="${edit.profilePicture}" >
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
                                <div class="m-t-20 text-center">
                                    <button class="btn btn-primary submit-btn" value="Edit User" name="btAction">Sửa</button>
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
