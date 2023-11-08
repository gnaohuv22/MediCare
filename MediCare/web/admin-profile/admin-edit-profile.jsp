<%-- 
    Document   : edit-profile
    Created on : Sep 20, 2023, 12:48:16 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../admin-general/admin-head.jsp" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/admin-employee.css">
    </head>
    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
            <jsp:include page="../admin-general/admin-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-12">
                            <h4 class="page-title">Sửa profile</h4>
                            <h3 style="color: red">${MESSAGE}</h3>
                        </div>
                    </div>
                    <c:set var="error" value="${EDIT_ERROR}"></c:set>
                    <form action="${pageContext.request.contextPath}/admin-edit-profile" method="POST">
                        <div class="card-box">
                            <h3 class="card-title">Thông tin cơ bản</h3>
                            <div class="form-group row justify-content-center">
                                <label for="input-name" class="col-sm-2 col-form-label" style="font-size: 15px">Tên</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" value="${EMPLOYEE.getName()}" id="input-name">
                                </div>
                            </div>
                            <p class="error-notice">${error.getNameError()}</p>
                            <div class="form-group row justify-content-center">
                                <label for="input-email" class="col-sm-2 col-form-label" style="font-size: 15px">Email</label>
                                <div class="col-sm-6">
                                    <input type="email" class="form-control" value="${EMPLOYEE.getEmail()}" id="input-email">
                                </div>                               
                            </div>
                            <p class="error-notice">${error.getEmailError()}</p>
                            <div class="form-group row justify-content-center">
                                <label for="input-date" class="col-sm-2 col-form-label" style="font-size: 15px">Sinh nhật</label>
                                <div class="col-sm-6">
                                    <input type="date" class="form-control" value="${EMPLOYEE.getBirthDate()}" id="input-date">
                                </div>
                            </div>
                            <p class="error-notice">${error.getBirthDateError()}</p>
                            <div class="form-group row justify-content-center">
                                <label for="input-address" class="col-sm-2 col-form-label" style="font-size: 15px">Địa chỉ</label>
                                <div class="col-sm-6">
                                    <input type="email" class="form-control" value="${EMPLOYEE.getAddress()}" id="input-address">
                                </div>
                            </div>
                            <p class="error-notice">${error.getAddressError()}</p>
                            <div class="form-group row justify-content-center">
                                <label for="input-phone" class="col-sm-2 col-form-label" style="font-size: 15px">Điện thoại</label>
                                <div class="col-sm-6">
                                    <input type="email" class="form-control" value="${EMPLOYEE.getPhone()}" id="input-phone">
                                </div>
                            </div>
                            <p class="error-notice">${error.getPhoneError()}</p>
                            <div class="form-group row justify-content-center">
                                <label for="input-phone" class="col-sm-2 col-form-label" style="font-size: 15px">Giới tính</label>
                                <div class="col-sm-6">
                                    <div class="d-flex align-items-center mr-4">
                                        <input class="mr-2" type="radio" name="gender" value="0" <c:if test="${EMPLOYEE.getGender() == 0}">checked="checked"</c:if>/>
                                            <span> Nam</span>
                                        </div>
                                        <div class="d-flex align-items-center" >
                                            <input  class="mr-2" type="radio" name="gender" value="1" <c:if test="${EMPLOYEE.getGender() == 1}">checked="checked"</c:if>/>
                                        <span> Nữ</span>
                                    </div>
                                </div>
                            </div>                                

                            </div>

                            <div class="card-box">
                                <h3 class="card-title">Bảo mật</h3>
                                
                                <div class="form-group row justify-content-center">
                                    <label for="input-id" class="col-sm-2 col-form-label" style="font-size: 15px">ID</label>
                                    <div class="col-sm-6">
                                        <input type="email" class="form-control" value="${EMPLOYEE.getId()}" id="input-id" readonly="true">
                                </div>
                            </div>
                            <div class="form-group row justify-content-center">
                                <label for="input-id" class="col-sm-2 col-form-label" style="font-size: 15px">Mật khẩu mới</label>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control" name="newPassword"  id="input-id" >
                                </div>
                            </div>
                            <p class="error-notice">${error.getPasswordError()}</p>
                            <div class="form-group row justify-content-center">
                                <label for="input-id" class="col-sm-2 col-form-label" style="font-size: 15px">Xác nhận mật khẩu mới</label>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control" name="confirmPassword"  id="input-id" >
                                </div>
                            </div>   
                        </div>
                        <div class="text-center m-t-20">
                            <button class="btn btn-primary submit-btn" type="submit">Lưu</button>
                        </div>
                    </form>
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.slimscroll.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>
    </body>
</html>
