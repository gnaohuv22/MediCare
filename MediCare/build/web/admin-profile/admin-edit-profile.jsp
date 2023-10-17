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
                    <form action="${pageContext.request.contextPath}/DispatchController" method="POST">
                        <div class="card-box">
                            <h3 class="card-title">Thông tin cơ bản</h3>
                            <div class="row">
<!--                                    <div class="profile-img-wrap">
                                        <img class="inline-block" src="../assets/admin/img/user.jpg" alt="user">
                                        <div class="fileupload btn">
                                            <span class="btn-text">sửa</span>
                                            <input class="upload" type="file">
                                        </div>
                                    </div>-->
                                    <!--<div class="profile-basic">-->
                                        <!--<div class="row">-->
                                            <div class="col-md-12">
                                                <div class="form-group form-focus">
                                                    <label class="focus-label">Tên</label>
                                                    <input type="text" class="form-control floating" value="${EMPLOYEE.getName()}" name="name">
                                                </div>
                                                    <p class="error-notice">${error.getNameError()}</p>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="form-group form-focus">
                                                    <label class="focus-label">Email</label>
                                                    <input type="text" class="form-control floating" value="${EMPLOYEE.getEmail()}" name="email">
                                                </div>
                                                    <p class="error-notice">${error.getEmailError()}</p>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="form-group form-focus">
                                                    <label class="focus-label">Sinh nhật</label>
                                                        <input class="form-control floating" type="date" value="${EMPLOYEE.getBirthDate()}" name="birthDate">
                                                </div>
                                                        <p class="error-notice">${error.getBirthDateError()}</p>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="form-group form-focus">
                                                    <label class="focus-label">Địa chỉ</label>
                                                    <input type="text" class="form-control floating" value="${EMPLOYEE.getAddress()}" name="address">
                                                </div>
                                                    <p class="error-notice">${error.getAddressError()}</p>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="form-group form-focus">
                                                    <label class="focus-label">Điện thoại</label>
                                                    <input type="text" class="form-control floating" value="${EMPLOYEE.getPhone()}" name="phone">
                                                </div>
                                                    <p class="error-notice">${error.getPhoneError()}</p>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="form-group form-focus select-focus">
                                                    <!--<label class="focus-label">Giới tính</label>-->
                                                    <input type="radio" name="gender" value="0" <c:if test="${EMPLOYEE.getGender() == 0}">checked="checked"</c:if>/>Nam
                                                    <input type="radio" name="gender" value="1" <c:if test="${EMPLOYEE.getGender() == 1}">checked="checked"</c:if>/>Nữ
                                                </div>
                                            </div>
                                        <!--</div>-->
                                    </div>
                                <!--</div>-->
                            
                        </div>
                        
                        <div class="card-box">
                            <h3 class="card-title">Bảo mật</h3>
                            <div class="row">
                                <!--<div class="col-md-12">-->
                                <div class="col-md-12">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">ID</label>
                                        <p type="text" class="form-control floating" value="${EMPLOYEE.getId()}">${EMPLOYEE.getId()}</p>
                                    </div>
                                </div>
<!--                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Mật khẩu cũ</label>
                                        <p type="text"class="form-control floating" value="${EMPLOYEE.getPassword()}"></p>
                                    </div>
                                </div>-->
                                <div class="col-md-12">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Mật khẩu mới</label>
                                        <input type="password" name="newPassword" class="form-control floating">
                                    </div>
                                        <p class="error-notice">${error.getPasswordError()}</p>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Xác nhận mật khẩu mới</label>
                                        <input type="password" name="confirmPassword" class="form-control floating" >
                                    </div>
                                </div>
                            <!--</div>-->
                            </div>
                        </div>
                        <div class="text-center m-t-20">
                            <button class="btn btn-primary submit-btn" type="submit" name="btAction" value="Save employee profile">Lưu</button>
                        </div>
<!--                        <div class="card-box">
                            <h3 class="card-title">Thông tin tương tác</h3>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Địa chỉ</label>
                                        <input type="text" class="form-control floating" value="${EMPLOYEE.getAddress()}">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">BranchId</label>
                                        <select name="branchId" class="select">
                                                <c:forEach var="list" items="${ALL_BRANCH}">
                                                    <option value="${list.getId()}" <c:if test="${list.getId() eq EMPLOYEE.getBranch().getId()}">selected</c:if>>
                                                        ${list.getName()}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Nơi làm việc</label>
                                        <input type="text" class="form-control floating" value="${EMPLOYEE.getWorkplace()}">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Tỉnh</label>
                                        <select class="select" name="provinceId">
                                                <c:forEach var="list" items="${ALL_PROVINCE}">
                                                    <option value="${list.getId()}" <c:if test="${list.getId() eq EMPLOYEE.getProvince().getId()}">selected</c:if>>${list.getName()}</option>
                                                </c:forEach>
                                            </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Dân tộc</label>
                                        <input type="text" class="form-control floating" value="${EMPLOYEE.getEthnic()}">
                                    </div>
                                </div>
                            </div>
                        </div>-->
<!--                        <div class="card-box">
                            <h3 class="card-title">Education Informations</h3>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Institution</label>
                                        <input type="text" class="form-control floating" value="Oxford University">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Subject</label>
                                        <input type="text" class="form-control floating" value="Computer Science">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Starting Date</label>
                                        <div class="cal-icon">
                                            <input type="text" class="form-control floating datetimepicker" value="01/06/2002">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Complete Date</label>
                                        <div class="cal-icon">
                                            <input type="text" class="form-control floating datetimepicker" value="31/05/2006">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Degree</label>
                                        <input type="text" class="form-control floating" value="BE Computer Science">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Grade</label>
                                        <input type="text" class="form-control floating" value="Grade A">
                                    </div>
                                </div>
                            </div>
                            <div class="add-more">
                                <a href="#" class="btn btn-primary"><i class="fa fa-plus"></i> Add More Institute</a>
                            </div>
                        </div>-->

<!--                        <div class="card-box">
                            <h3 class="card-title">Experience Informations</h3>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Company Name</label>
                                        <input type="text" class="form-control floating" value="Digital Devlopment Inc">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Location</label>
                                        <input type="text" class="form-control floating" value="United States">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Job Position</label>
                                        <input type="text" class="form-control floating" value="Web Developer">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Period From</label>
                                        <div class="cal-icon">
                                            <input type="text" class="form-control floating datetimepicker" value="01/07/2007">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Period To</label>
                                        <div class="cal-icon">
                                            <input type="text" class="form-control floating datetimepicker" value="08/06/2018">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="add-more">
                                <a href="#" class="btn btn-primary"><i class="fa fa-plus"></i> Add More Experience</a>
                            </div>
                        </div>-->
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
