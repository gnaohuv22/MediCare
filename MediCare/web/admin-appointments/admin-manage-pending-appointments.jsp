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
                                <h4 class="page-title">Quản lí danh sách cuộc hẹn</h4>
                            </div>
                            <div class="col-sm-8 col-9 text-right m-b-20">
                                <a href="add-appointment.html" class="btn btn btn-primary btn-rounded float-right"><i class="fa fa-plus"></i> Add Appointment</a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-responsive">
                                    <table border="1" class="table table-striped custom-table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Tên bệnh nhân</th>
                                                <th>Bác sĩ phụ trách</th>
                                                <th>Chuyên khoa</th>
                                                <th>Chi nhánh</th>
                                                <th>Ngày giờ khám</th>
                                                <th>Trạng thái</th>
                                                <th class="text-right">Hành động</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.ALL_APPOINTMENT}" var="appointment">
                                                <tr>
                                                    <td>${appointment.id}</td>
                                                    <td>${appointment.getProfile().getName()}</td>
                                                    <c:if test="${appointment.doctor.getDisplayName() == null}">
                                                        <td style="text-align: center">-</td>
                                                    </c:if>
                                                    <c:if test="${appointment.doctor.getDisplayName() != null}">
                                                        <td>${appointment.doctor.getDisplayName()}</td>
                                                    </c:if>

                                                    <td>${appointment.getServiceTag().getNametag()}</td>
                                                    <td>${appointment.getBranch().getName()}</td>
                                                    <td>${appointment.getAppointmentTime()} ngày ${appointment.getAppointmentDay()}</td>

                                                    <c:forEach items="${requestScope.statusAppointments}" var="status">
                                                        <c:if test="${appointment.getStatus().equals(status.link)}">
                                                            <td class="status-appointment"><span class="custom-badge ${status.icon}">${status.name}</span></td>
                                                            <td class="text-right">
                                                                <div class="dropdown dropdown-action">
                                                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                                    <div class="dropdown-menu dropdown-menu-right">
                                                                        <a data-pending-appointment-id="${appointment.id}" class="dropdown-item" href="#" onclick="eventClickSolvePendingAppointments(this)"><i class="fa fa-pencil m-r-5"></i> Xử lí cuộc hẹn</a>
                                                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_appointment"><i class="fa fa-trash-o m-r-5"></i> Xóa cuộc hẹn</a>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </c:if>
                                                    </c:forEach>

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
            <i class="fas fa-close close-edit-appointment-btn" onclick="closeSolvePendingAppointmentsForm()"></i>
            <form action="admin-update-schedule-doctor" class="form-container" method="post">
                <!--appointment table - start-->
                <div class="container">
                    <div class="content">
                        <div class="row">
                            <div class="col-lg-8 offset-lg-2">
                                <h4 class="page-title">Xử lí lịch hẹn</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-8 offset-lg-2">
                                <form>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>ID cuộc hẹn</label>
                                                <input class="form-control" type="text" value="APT-0001" readonly="">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Tên bệnh nhân</label>
                                                <input class="form-control" type="text" value="Andrew NG">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Chuyên khoa</label>
                                                <select class="select">
                                                    <option>Select</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Bác sĩ phụ trách</label>
                                                <select class="select">
                                                    <option>Select</option>
                                                    <option selected>Henry Daniels</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Ngày khám</label>
                                                <div>
                                                    <input type="date" class="form-control" name="date">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Slot</label>
                                                <select class="select">
                                                    <option>Select</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Email</label>
                                                <input class="form-control" type="email">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Số điện thoại</label>
                                                <input class="form-control" type="text">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>Triệu chứng</label>
                                        <textarea cols="30" rows="4" class="form-control"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label class="display-block">Trạng thái cuộc hẹn</label>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="status" id="product_active" value="option1" checked>
                                            <label class="form-check-label" for="product_active">
                                                Active
                                            </label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="status" id="product_inactive" value="option2">
                                            <label class="form-check-label" for="product_inactive">
                                                Inactive
                                            </label>
                                        </div>
                                    </div>
                                    <div class="m-t-20 text-center">
                                        <button class="btn btn-primary submit-btn">Save</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--appointment table - end-->

                <!-- Add more input fields as needed -->
                <!--                <input type="hidden" name="method" id="method" value=""/><br/>  
                                <button class="button-container" id="schedule-submit-button" type="submit"></button>-->
                <button type="button" id="schedule-close-button" onclick="closeScheduleOfDoctorForm()">Close</button>
            </form>
        </div>
        <!--Popup - End-->
        <script>
            function eventClickScheduleDoctor(event) {
                console.log("Doctor is click: " + event.getAttribute("data-doctorId"));
                var modal = document.getElementById("scheduleDoctorForm");
                modal.setAttribute("data-doctorId", event.getAttribute("data-doctorId"));
                modal.style.display = "block";
//                document.getElementById("method").value = "add";
//                document.getElementById("schedule-submit-button").innerHTML = "Update";

                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
//                        document.getElementById("method").value = "add";
                    }
                };
            }

            function closeSolvePendingAppointmentsForm() {
                var modal = document.getElementById("editAppointmentForm");
                modal.style.display = "none";
            }


            function eventClickSolvePendingAppointments(event) {
                console.log("EVENT Click SOLVE Pending Appointments:");
                console.log("Event is click: " + event.getAttribute("data-pending-appointment-id"));
                var modal = document.getElementById("editAppointmentForm");
                modal.style.display = "block";
//                document.getElementById("method").value = "add";
//                document.getElementById("schedule-submit-button").innerHTML = "Update";

                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
//                        document.getElementById("method").value = "add";
                    }
                };
                $.ajax({
                    url: "/MediCare/admin-manage-appointments",
                    data: {
                        appointmentId: event.getAttribute("data-pending-appointment-id"),
                        action: "solve-pending-appointment"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var editAppointmentForm = document.getElementById("editAppointmentForm");
                        editAppointmentForm.innerHTML = response;

                        // Lựa chọn các phần tử input type radio trong nội dung mới
                        var statusInputs = editAppointmentForm.querySelectorAll('input[name="status"]');
                        var doctorSelect = editAppointmentForm.querySelector('.doctor-choice-edit-appointment');

                        // Biến để lưu trạng thái cuộc hẹn trước đó
//                        var previousStatus = null;
                        var previousStatus = doctorSelect.getAttribute("data-status");


                        // Lặp qua các phần tử input type radio và thêm sự kiện nghe
                        statusInputs.forEach(input => {
                            input.addEventListener('change', function () {
                                // In ra giá trị của trạng thái và bác sĩ
                                console.log("Trạng thái:", this.value);
                                console.log("Bác sĩ:", doctorSelect.value);
                                console.log("Trang thai ban dau:", previousStatus);


                                if (doctorSelect.value !== "-1") {
                                    console.log("doctorId != -1");
                                    // Bác sĩ đã được chọn, cho phép chọn các trạng thái cụ thể
                                    if (this.value === "0" || this.value === "3") {
                                        this.checked = true;
                                    }
                                } else {
                                    if (this.value === "0" || this.value === "3") {
                                        this.checked = true;
                                    } else {
                                        // Bác sĩ chưa được chọn, báo lỗi và không cho phép chọn các trạng thái cụ thể
                                        this.checked = false;
                                        document.querySelector('input[name="status"][value="' + previousStatus + '"]').checked = true;
                                        console.log("Select value = previous status success");
                                        // Báo lỗi
                                        alert("Vui lòng chọn bác sĩ trước khi chọn trạng thái.");
                                    }
                                }

                                // Cập nhật giá trị trạng thái trước đó
//                                previousStatus = this.value;
                            });
                        });
                    }
                    ,
                    error: function (xhr) {

                    }
                });
            }

            function eventSaveEditingAppointment(event) {
                console.log("Event onclick: " + event);

                // Lựa chọn các phần tử input type radio trong nội dung mới
                var statusInputs = editAppointmentForm.querySelectorAll('input[name="status"]');
                var doctorSelect = editAppointmentForm.querySelector('.doctor-choice-edit-appointment');

                var appointmentId = event.getAttribute("data-pending-appointment-id");
                var statusValue = null;

                // Lặp qua các phần tử input type radio và lấy giá trị trạng thái
                statusInputs.forEach(input => {
                    if (input.checked) {
                        statusValue = input.value;
                    }
                });

                var doctorId = doctorSelect.value;

//                if (!appointmentId || !statusValue || !doctorId) {
//                    alert("Vui lòng chọn tất cả thông tin cần thiết trước khi lưu.");
//                    return;
//                }

                // Gọi AJAX để lưu thông tin
                $.ajax({
                    url: "/MediCare/admin-manage-appointments",
                    data: {
                        appointmentId: appointmentId,
                        status: statusValue,
                        doctorId: doctorId,
                        action: "save"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
//                        var step3container = document.getElementById("step-3-container");
//                        step3container.innerHTML = response;
                        alert("Save success");
                        console.log("success");
                        setTimeout(function () {
                        window.location.href = "admin-manage-appointments";
                    }, 500);
                        
                    },
                    error: function (xhr) {
                        console.log("Error: " + xhr);
                    }
                });
            }



        </script>
    </body>

</html>