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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa nhân viên</title>
        <jsp:include page="../admin-general/admin-head.jsp" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/admin-employee.css">
    
    </head>
    <body>
            <jsp:include page="../admin-general/admin-header.jsp"/>
            <jsp:include page="../admin-general/admin-sidebar.jsp"/>
        <!-------------------------->
        <%--<c:if test="${empty EMPLOYEE}">--%>
            <!--Bạn phải là nhân viên để truy cập vào trang này-->
        <%--</c:if>--%>
        <%--<c:if test="${not empty EMPLOYEE}">--%>
            <%--<c:if test="${EMPLOYEE.getRole().getId()!=1}">--%>
                <!--Bạn cần quyền để truy cập vào nội dung này-->
            <%--</c:if>--%>
            <%--<c:if test="${EMPLOYEE.getRoleId().getId()==1}">--%>
            <!-------------------------->
        <div class="main-wrapper">
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <h4 class="page-title">Sửa nhân viên</h4>
                        </div>
                    </div>
                    <c:set var="error" value="${EDIT_ERROR}"></c:set>
                    <c:set var="edit" value="${EDIT_EMPLOYEE}"/>
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <form action="${pageContext.request.contextPath}/DispatchController" method="POST"> 
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>ID</label>
                                            <input type="hidden" name="id" value="${edit.getEmployee().getId()}" />
                                            <p class="form-control">${edit.getEmployee().getId()}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Email </label>
                                            <input class="form-control" type="email" name="email" value="${edit.getEmployee().email}"><p class="error-notice">${error.getEmailError()}</p>
                                        </div>
                                    </div>
                                    <input class="form-control" type="hidden" name="password" value="${edit.getEmployee().password}">
                            
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Mật khẩu mới (Không bắt buộc)</label>
                                            <input class="form-control" type="password" name="newPassword"><p class="error-notice">${error.getPasswordError()}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Chi nhánh</label>
                                            <select name="branchId" class="select">
                                                <c:forEach var="list" items="${ALL_BRANCH}">
                                                    <option value="${list.getId()}" <c:if test="${list.getId() eq edit.branch.id}">selected</c:if>>
                                                        ${list.getName()}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Tên </label>
                                            <input class="form-control" type="text" name="name" value="${edit.getEmployee().name}">
                                            <p class="error-notice">${error.getNameError()}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Ngày sinh</label>
                                            <div class="cal-icon">
                                                <input class="form-control datetimepicker" type="date" name="birthDate" value="${edit.getEmployee().birthDate}">
                                                <p class="error-notice">${error.getBirthDateError()}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Giới tính </label>
                                                <span style="font-size:20px;color:#000;margin-left:36px;padding:5px 5px;">
                                                        <input type="radio" name="gender" value="0" <c:if test="${edit.getEmployee().gender == 0}">checked="checked"</c:if>/>Nam
                                                        <input type="radio" name="gender" value="1" <c:if test="${edit.getEmployee().gender == 1}">checked="checked"</c:if>/>Nữ
                                                    
                                                </span>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Địa chỉ </label>
                                            <input class="form-control" type="text" name="address" value="${edit.getEmployee().address}" ><p class="error-notice">${error.getAddressError()}</p>
                                        </div>
                                    </div>
                                        <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Nơi làm việc </label>
                                            <input class="form-control" type="text" name="workplace" value="${edit.getEmployee().workplace}" ><p class="error-notice">${error.getWorkplaceError()}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Tỉnh</label>
                                            <select class="select" name="provinceId" value="${edit.province.id}">
                                                <c:forEach var="list" items="${ALL_PROVINCE}">
                                                    <option value="${list.getId()}" <c:if test="${list.getId() eq edit.province.id}">selected</c:if>>${list.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Điện thoại </label>
                                            <input class="form-control" type="text" name="phone" value="${edit.getEmployee().phone}" ><p class="error-notice">${error.getPhoneError()}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Dân tộc </label>
                                            <input class="form-control" type="text" name="ethnic" value="${edit.getEmployee().ethnic}" ><p class="error-notice">${error.getEthnicError()}</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Quyền</label>
                                            <select class="select" name="roleId">
                                                <c:forEach var="list" items="${ALL_EMPLOYEEROLE}">
                                                    <option value="${list.getId()}" <c:if test="${list.getId() eq edit.employeeRole.id}">selected</c:if>>${list.getRole()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
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
                                    <button class="btn btn-primary submit-btn" value="Edit Employee" name="btAction">Lưu</button>
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
       <%--<jsp:include page="${pageContext.request.contextPath}/general/admin-script.jsp"/>--%>
        <%--</c:if>--%>
        <%--</c:if>--%>
    </body>
</html>
