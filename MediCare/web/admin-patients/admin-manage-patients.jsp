<%-- 
    Document   : admin-manage-appointments
    Created on : Oct 19, 2023, 2:24:02 PM
    Author     : tubinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <!-- appointments23:19-->
    <head>
        <jsp:include page="../admin-general/admin-head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/admin-display-table.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Quản lí cuộc hẹn</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/select2.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/style.css">
        <!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/client/css/style.css">-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>

    <body>
        <%@include file="../admin-general/admin-header.jsp"%>
        <%@include file="../admin-general/admin-sidebar.jsp"%>
        <c:if test="${empty EMPLOYEE}">
            Bạn phải là nhân viên để truy cập vào trang này
        </c:if>
        <c:if test="${not empty EMPLOYEE}">
            <div class="main-wrapper">
                <div class="page-wrapper">

                    <div class="content">
                        <div class="row">
                            <div class="col-sm-4 col-3">
                                <h4 class="page-title">Trang quản lí bệnh nhân (Status appointment ở mức 'đang chờ khám' và 'đã khám xong'), (nếu 'chờ xác nhận' hoặc 'đã hủy' -> ko phải bệnh nhân)</h4>
                            </div>
                            <div class="col-sm-8 col-9 text-right m-b-20">
                                <!--<a id="click-add-appointment" href="#" class="btn btn btn-primary btn-rounded float-right" onclick="eventClickAddAppointments(this)"><i class="fa fa-plus"></i> Thêm bệnh nhân</a>-->
                                <!--<a href="add-patient.html" class="btn btn btn-primary btn-rounded float-right"><i class="fa fa-plus"></i> Add Patient</a>-->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-responsive">
                                    <table class="table table-border table-striped custom-table datatable mb-0">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Tên</th>
                                                <th>Số điện thoại</th>
                                                <th>Email</th>
                                                <th>Ngày sinh</th>
                                                <th>Chi nhánh</th>
                                                <th>Địa chỉ</th>
                                                <th class="text-center">Tác vụ</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.patients}" var="p">
                                                <tr>
                                                    <td><a href="">${p.profileId}</a></td>
                                                    <td>${p.name}</td>
                                                    <td>${p.phone}</td>
                                                    <td>${p.email}</td>
                                                    <td>${p.birthDate}</td>
                                                    <td>${p.branch.getName()}</td>
                                                    <td>${p.address==null?'-':p.address}</td>
                                                    <td class="text-center">
                                                        <a class="dropdown-item edit-appointment-button" data-patientId="${p.profileId}" href="#" onclick="eventClickEditPatient(this)"><i class="fa fa-pencil m-r-5"></i> Chỉnh sửa</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="../admin-general/admin-script.jsp"/>
        </c:if>

        <!--Popup - Start-->
        <div id="editAppointmentForm" class="form-popup">
            POPUP
        </div>
        <!--Popup - End-->
        <script>
            function eventClickEditPatient(event) {
                console.log("Doctor is click: " + event.getAttribute("data-patientId"));
                var modal = document.getElementById("editAppointmentForm");
                modal.setAttribute("data-doctorId", event.getAttribute("data-doctorId"));
                modal.style.display = "block";

                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                    }
                };
                
                $.ajax({
                    url: "/MediCare/admin-manage-patients",
                    data: {
                        patientId: event.getAttribute("data-patientId"),
                        action: "edit-patient"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var editAppointmentForm = document.getElementById("editAppointmentForm");
                        editAppointmentForm.innerHTML = response;
                    },
                    error: function (xhr) {
                    }
                });
            }

            function closeSolvePendingAppointmentsForm() {
                var modal = document.getElementById("editAppointmentForm");
                modal.style.display = "none";
            }
        </script>
    </body>

</html>