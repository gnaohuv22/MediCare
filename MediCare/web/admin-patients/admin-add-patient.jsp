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
        <title>Quản lý người dùng</title>
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
                            <c:if test="${add_user}">
                                <h2 class="page-title">Thêm người dùng</h2>
                            </c:if>
                            <c:if test="${edit_user}">
                                <h2 class="page-title">Sửa người dùng</h2>
                            </c:if>
                        </div>
                    </div>
                    <c:set var="user" value="${EDIT_USER}"></c:set>
                    <c:set var="error" value="${EDIT_ERROR}"></c:set>
                    <c:set var="error" value="${REGISTER_ERROR}"></c:set>
                        <div class="row">
                            <div class="col-lg-8 offset-lg-2">
                                <form action="${pageContext.request.contextPath}/admin-list-user" method="POST"> 
                                <c:if test="${add_user}">
                                    <input type="hidden" name="add-user" value="true">
                                    <c:set var="user" value="${ADD_USER}"/>
                                    <c:set var="error" value="${REGISTER_ERROR}"></c:set>
                                </c:if>
                                <c:if test="${edit_user}">
                                    <input type="hidden" name="edit-user" value="true">
                                    <c:set var="user" value="${EDIT_USER}"/>
                                    <c:set var="error" value="${EDIT_ERROR}"></c:set>
                                </c:if>
                                <div class="row">
                                    <input class="form-control" type="hidden" name="id" value="${user.id}">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Email </label>
                                            <input class="form-control" type="email" name="email" placeholder="Ví dụ: example@gmail.com" value="${user.email}">
                                        </div>
                                        <p class="error-notice">${error.getEmailError()}</p>
                                    </div>
                                    <input class="form-control" type="hidden" name="password" value="${user.password}">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Tên </label>
                                            <input class="form-control" type="text" name="name" placeholder="Ví dụ: Nguyễn Văn A" value="${user.name}">
                                        </div>
                                        <p class="error-notice">${error.getNameError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Ngày sinh</label>
                                            <!--<div class="cal-icon">-->
                                            <div class="form-group">
                                                <input class="form-control datetimepicker" type="date" name="birthDate" value="${user.birthDate}">
                                            </div>
                                            <p class="error-notice">${error.getBirthDateError()}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Giới tính </label><br>
                                            <span style="font-size:20px;color:#000;margin-left:36px;padding:5px 5px;">
                                                <input type="radio" name="gender" value="0" <c:if test="${user.gender != 1}">checked="checked"</c:if>/>Nam
                                                <input type="radio" name="gender" value="1" <c:if test="${user.gender == 1}">checked="checked"</c:if>/>Nữ
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label>Địa chỉ </label>
                                                <input class="form-control" type="text" name="address" placeholder="Ví dụ: 123/3 đường Lê Lợi, phường Bến Nghé, Quận 1, Thành phố Hồ Chí Minh" value="${user.address}" >
                                        </div>
                                        <p class="error-notice">${error.getAddressError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Tỉnh</label><br>
                                            <select class="select" name="provinceId" value="${provinceId}">
                                                <c:forEach var="list" items="${ALL_PROVINCE}">
                                                    <option value="${list.getId()}" <c:if test="${list.getId() eq user.getProvince().getId()}">selected</c:if>>${list.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Số CCCD</label>
                                            <h6 style="color: #D7D7D7">CCCD có 10 chữ số</h6>
                                            <input class="form-control" type="text" name="identity" placeholder="Ví dụ: 0123456789" value="${user.identity}" >
                                        </div>
                                        <p class="error-notice">${error.getIdentityError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Số BHYT</label>
                                            <h6 style="color: #D7D7D7">BHYT có 12 chữ số</h6>
                                            <input class="form-control" type="text" name="medicalId" placeholder="Ví dụ: 012345678910" value="${user.medicalId}" >
                                        </div>
                                        <p class="error-notice">${error.getMedicalIdError()}</p>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Dân tộc </label>
                                            <input class="form-control" type="text" name="ethnic" placeholder="Ví dụ: Kinh, Thái,.." value="${user.ethnic}" >
                                        </div>
                                        <p class="error-notice">${error.getEthnicError()}</p>
                                    </div> 
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Số điện thoại</label><h6 style="color: #D7D7D7">Số điện thoại có 10 chữ số</h6>
                                            <input class="form-control" type="text" name="phone" placeholder="Ví dụ: 0989123456" value="${user.phone}" >
                                        </div>
                                        <p class="error-notice">${error.getPhoneError()}</p>
                                    </div> 
<!--                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Ảnh</label>
                                            <input class="form-control" type="text" name="profilePicture" value="${user.profilePicture}" >
                                        </div>
                                        <p class="error-notice">${error.getProfilePictureError()}</p>
                                    </div>-->

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
                                    <c:if test="${add_user}">
                                        <button class="btn btn-primary submit-btn" >Đăng kí</button>
                                    </c:if>
                                    <c:if test="${edit_user}">
                                        <button class="btn btn-primary submit-btn" >Lưu</button>
                                    </c:if>
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
