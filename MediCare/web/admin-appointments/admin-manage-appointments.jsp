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
                                <h4 class="page-title">Quản lí danh sách cuộc hẹn</h4>
                            </div>
                            <div class="col-sm-8 col-9 text-right m-b-20">
                                <a id="click-add-appointment" href="#" class="btn btn btn-primary btn-rounded float-right" onclick="eventClickAddAppointments(this)"><i class="fa fa-plus"></i> Thêm bệnh nhân</a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-responsive">
                                    <table border="1" class="table table-striped custom-table">
                                        <thead>
                                            <tr class="text-center">
                                                <th>ID</th>
                                                <th>Tên bệnh nhân</th>
                                                <th>Bác sĩ phụ trách</th>
                                                <th>Chuyên khoa</th>
                                                <th>Chi nhánh</th>
                                                <th>Ngày giờ khám</th>
                                                <th>Trạng thái</th>
                                                <th>Hành động</th>
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
                                                            <td class="text-center">
                                                                <!--<div class="dropdown dropdown-action">-->
                                                                <!--<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>-->
                                                                <!--<div class="dropdown-menu dropdown-menu-right">-->
                                                                <a data-pending-appointment-id="${appointment.id}" data-doctor-name="${appointment.doctor.getDisplayName()}" class="dropdown-item edit-appointment-button" href="#" onclick="eventClickSolvePendingAppointments(this)"><i class="fa fa-pencil m-r-5"></i> Chỉnh sửa</a>
                                                                <!--<a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_appointment"><i class="fa fa-trash-o m-r-5"></i> Xóa cuộc hẹn</a>-->
                                                                <!--</div>-->
                                                                <!--</div>-->
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
                        doctorDisplayName: event.getAttribute("data-doctor-name"),
                        action: "solve-pending-appointment"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var editAppointmentForm = document.getElementById("editAppointmentForm");
                        editAppointmentForm.innerHTML = response;
                        
                        
// Lựa chọn các phần tử input type checkbox và các phần tử hiển thị checkbox
                        var mailCheckboxes = document.querySelectorAll('.mail-checkbox');
                        var mailCheckboxContainers = document.querySelectorAll('.mail-checkbox-container');
                        
// Ẩn tất cả các checkbox và container ban đầu
                        mailCheckboxContainers.forEach(container => {
                            container.style.display = "none";
                        });
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
                                
// Ẩn tất cả checkbox ban đầu
                                mailCheckboxContainers.forEach(container => {
                                    container.style.display = "none";
                                });
                                
                                // Hiển thị checkbox tương ứng nếu trạng thái là 1, 2 hoặc 3
                                if (this.value === "1") {
                                    mailCheckboxContainers[0].style.display = "block"; // Hiện checkbox 1
                                } else if (this.value === "2") {
                                    mailCheckboxContainers[1].style.display = "block"; // Hiện checkbox 2
                                } else if (this.value === "3") {
                                    mailCheckboxContainers[2].style.display = "block"; // Hiện checkbox 3
                                }
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
            function eventClickAddAppointments(event) {
                console.log("EVENT Click Add Appointments:");
                console.log("Event is click: " + event.id);
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
                        doctorDisplayName: event.getAttribute("data-doctor-name"),
                        action: "add-appointment"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        console.log("ajax add success");
                        var editAppointmentForm = document.getElementById("editAppointmentForm");
                        editAppointmentForm.innerHTML = response;
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
                
// Lựa chọn phần tử input type checkbox
                var mailCheckbox = document.querySelector('.mail-checkbox');
                
                var actionSendMailValue = mailCheckbox.checked ? mailCheckbox.getAttribute("data-action-send-mail") : '';
                console.log("actionSendMailValue onclick: " + actionSendMailValue);
                
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
                        action: "save",
                        actionSendMail: actionSendMailValue // Thêm tham số actionSendMail
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
//                        var step3container = document.getElementById("step-3-container");
//                        step3container.innerHTML = response;
                        alert("Save success");
                        console.log("success");
                        setTimeout(function () {
                            window.location.href = "admin-manage-pending-appointments";
                        }, 500);
                        
                    },
                    error: function (xhr) {
                        console.log("Error: " + xhr);
                    }
                });
            }
            function eventSaveAddAppointment(event) {
                console.log("Event click: " + event.id);
                
                var branchId = document.getElementById("branchId").value;
                console.log("branchId: " + branchId);
                
                var serviceId = document.getElementById("serviceId").value;
                console.log("serviceId: " + serviceId);
                
                var doctorId = document.getElementById("doctorId").value;
                console.log("doctorId: " + doctorId);
                
                var date = document.getElementById("booking-calendar").value;
                console.log("date: " + date);
                
                var slotId = document.getElementById("booking-slot").value;
                console.log("slotId: " + slotId);
                
                var patientName = document.getElementById("patientName").value;
                console.log("patientName: " + patientName);
                
                var birthDate = document.getElementById("birthDate").value;
                console.log("birthDate: " + birthDate);
                
                var genderRadios = document.getElementsByName("gender");
                var gender;
                for (var i = 0; i < genderRadios.length; i++) {
                    if (genderRadios[i].checked) {
                        gender = genderRadios[i].value;
                        break;
                    }
                }
                console.log("gender: " + gender);
                
                var phone = document.getElementById("phone").value;
                console.log("phone: " + phone);
                
                var email = document.getElementById("email").value;
                console.log("email: " + email);
                
                var description = document.getElementById("description").value;
                console.log("description: " + description);
                
                // Kiểm tra điều kiện trước khi thực hiện Ajax
                if (branchId && serviceId && doctorId && date && slotId && patientName && birthDate && gender && phone && email) {
                    $.ajax({
                        url: "/MediCare/admin-manage-appointments",
                        data: {
                            branchId: branchId,
                            serviceId: serviceId,
                            doctorId: doctorId,
                            date: date,
                            slotId: slotId,
                            patientName: patientName,
                            birthDate: birthDate,
                            gender: gender,
                            phone: phone,
                            email: email,
                            description: description,
                            action: "submit-add-appointment"
                        },
                        cache: false,
                        type: "POST",
                        success: function (response) {
                            alert("Thêm bệnh nhân thành công!");
                            console.log("success");
                            setTimeout(function () {
                                window.location.href = "admin-manage-appointments";
                            }, 500);
                        },
                        error: function (xhr) {
                            console.log("Error: " + xhr);
                        }
                    });
                } else {
                    var errorSaveAddAppointmen = document.getElementById("error-save-add-appointment");
                    errorSaveAddAppointmen.innerHTML = "Vui lòng điền tất cả thông tin trước khi lưu!";
                }
            }
            
            
            
            
            
        </script>

        <!--Create Profile JS - Popup - start-->
        <script>
            function openAddProfileForm() {
                var modal = document.getElementById("profileForm");
                modal.style.display = "block";
                document.getElementById("method").value = "add";
                document.getElementById("submit-button").innerHTML = "Add";
                
                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                        document.getElementById("method").value = "add";
                    }
                };
            }
            
            function closeAddProfileForm() {
                var modal = document.getElementById("profileForm");
                modal.style.display = "none";
                document.getElementById("method").value = "";
                document.getElementById("submit-button").innerHTML = "";
            }
            
            function toggleCheckbox() {
                var checkbox = document.getElementById("confirm-edit-profile");
                
                if (checkbox) {
                    checkbox.checked = !checkbox.checked;
                }
                getCheckboxValue();
            }
            
            // Lấy giá trị của checkbox
            function getCheckboxValue() {
                var checkbox = document.getElementById("confirm-edit-profile");
                
                if (checkbox) {
                    if (checkbox.checked) {
                        console.log("Checkbox is checked");
                    } else {
                        console.log("Checkbox is not checked");
                    }
                }
            }
            
            // OTP Submit:
            function onclickSubmitOTP(event) {
                console.log("Event OTP: " + event);
                var otp = document.getElementById("otp").value;
                var step3container = document.getElementById('step-3-container');
                var trueOTP = step3container.getAttribute("data-OTP");
                
                console.log("Ajax - JS - submit OTP of appointment:");
                var branchId = document.getElementById('branchId').value;
                var serviceId = document.getElementById('serviceId').value;
                var doctorId = document.getElementById('doctorId').value;
                var date = document.getElementById('booking-calendar').value;
                var slotId = document.getElementById('booking-slot').value;
                
                var patientName = document.getElementById("patientName").value;
                var birthDate = document.getElementById("birthDate").value;
                var genderRadios = document.getElementsByName("gender");
                var gender;
                for (var i = 0; i < genderRadios.length; i++) {
                    if (genderRadios[i].checked) {
                        gender = genderRadios[i].value;
                        break;
                    }
                }
                var phone = document.getElementById("phone").value;
                var email = document.getElementById("email").value;
                var description = document.getElementById("description").value;
                
                $.ajax({
                    url: "/MediCare/user-book-appointment",
                    data: {
                        branchId: branchId,
                        serviceId: serviceId,
                        doctorId: doctorId,
                        date: date,
                        slotId: slotId,
                        otp: otp,
                        trueOTP: trueOTP,
                        patientName: patientName,
                        birthDate: birthDate,
                        gender: gender,
                        phone: phone,
                        email: email,
                        description: description,
                        action: "submitOTP"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        console.log("response: " + response);
                        var message = response;
                        console.log("Message from JSON: " + message);
                        if (message === "Đặt lịch khám thành công!") {
                            console.log("message = 'dat lich thanh cong'");
                            
                            setTimeout(function () {
                                window.location.href = "user-home";
                            }, 500);
                        } else {
                            var confirmOTPForm = document.getElementById("confirm-OTP-Form");
                            confirmOTPForm.style.display = 'none';
                            document.getElementById("otp").value = '';
                        }
                        window.alert(message);
                        console.log("success");
                    },
                    error: function (xhr) {
                        console.log("Error: " + xhr);
                    }
                });
                
            }
            
        </script>
        <!--Create Profile JS - Popup - end-->

        <!--Profile JS - start-->
        <script>
            function handleProfileClick(element) {
                var profileId = element.getAttribute("data-profile-id");
                var ownerId = element.getAttribute("data-ownerId");
                console.log("Clicked on profile with ID: " + profileId);
                
                // Loại bỏ lớp active từ tất cả các phần tử có lớp each-family-profile
                var allProfileElements = document.querySelectorAll('.each-family-profile');
                allProfileElements.forEach(function (el) {
                    el.classList.remove('each-family-profile-active');
                });
                // Add active class for this element:
                element.classList.add('each-family-profile-active');
                
                $.ajax({
                    url: "/MediCare/load-each-family-profile",
                    data: {
                        profileId: profileId,
                        ownerId: ownerId
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var profileInfo = document.getElementById("step-1-container");
                        profileInfo.innerHTML = response;
                    },
                    error: function (xhr) {
                        
                    }
                });
            }
            
            function handleAddProfile(element) {
                console.log("Clicked on profile with ID: " + element);
            }
        </script>
        <!--Profile JS - end-->


        <script>
            var dataLoaded = false;
            
            // Get current date:
            var today = new Date();
            
            var dateInput = document.getElementById('booking-calendar');
            
            
            // Đặt thuộc tính min của input là ngày hiện tại (để disable các ngày trước ngày hiện tại)
            dateInput.min = today.toISOString().split('T')[0];
            
            // Danh sách các ngày nghỉ lễ (định dạng là yyyy-mm-dd)
            var holidays = ['2023-09-30', '2023-10-20', '2023-12-25']; // Thêm các ngày nghỉ lễ khác nếu cần
            
            // Sử dụng sự kiện "input" để kiểm tra ngày mà người dùng chọn
            dateInput.addEventListener('input', function () {
                var selectedDate = dateInput.value;
                
                // Kiểm tra xem ngày đã chọn có trong danh sách ngày nghỉ lễ không
                if (holidays.includes(selectedDate)) {
                    // Nếu ngày đã chọn là ngày nghỉ lễ, xử lý tùy ý, ví dụ: thông báo lỗi hoặc đặt giá trị trống
                    alert('Ngày này là ngày nghỉ lễ. Vui lòng chọn ngày khác.');
                    dateInput.value = '';
                }
            });
            
            var birthDateInput = document.getElementById("birthDate");
            birthDateInput.max = today.toISOString().split('T')[0];
            
            
            
            
            // Get list doctor by serviceId - using ajax:
            
            function loadDoctors() {
                console.log("---LOAD DOCTORS---");
                var serviceId = document.getElementById('serviceId');
                var branchId = document.getElementById('branchId').value;
                
                var doctorBox = document.getElementById("doctor-select-box");
                console.log("Event Id =" + doctorId.id);
                
                if (doctorBox.style.display === 'none' || doctorBox.style.display === 'undefined' || doctorBox.style.display === undefined || doctorBox.style.display === '') {
                    console.log("Element doctorBox - display = null");        
                    console.log("(BEFORE) doctorBox.display =" + doctorBox.style.display);
//                    console.log("doctorId = none");
                    doctorBox.style.display = 'block';
                    console.log("(AFTER) doctorBox.display =" + doctorBox.style.display);
                }
                console.log("serviceId: " + serviceId.id);
                console.log("sevice display: " + serviceId.style.display);
                $.ajax({
                    url: "/MediCare/load-doctors-by-serviceid?branchId=" + branchId + "&serviceid=" + serviceId.value,
                    data: {
                    },
                    cache: false,
                    type: "GET",
                    success: function (response) {
                        console.log("ajax - load - doctor: success!")
                        var doctorSelectBox = document.getElementById("doctor-select-box");
                        doctorSelectBox.innerHTML = response;
                    },
                    error: function (xhr) {
                        
                    }
                }).then(function () {
                    doctorId = document.getElementById('doctorId').value;
                    var date = document.getElementById('booking-calendar').value;
                    console.log("doctorId: " + doctorId);
                    var serviceError = document.getElementById("serviceError");
                    serviceError.innerHTML = '';
                    $.ajax({
                        url: "/MediCare/load-schedule-booking?branchId=" + branchId + "&serviceid=" + serviceId.value + "&doctorId=" + doctorId + "&date=" + date,
                        data: {
                        },
                        cache: false,
                        type: "GET",
                        success: function (response) {
                            var doctorSelectBox = document.getElementById("slot-select-box");
                            doctorSelectBox.innerHTML = response;
                        },
                        error: function (xhr) {
                        }
                    });
                });
                dataLoaded = true;
            }
            
            function loadScheduleOfDoctor() {
                var serviceId = document.getElementById('serviceId').value;
                var doctorId = document.getElementById('doctorId').value;
                var date = document.getElementById('booking-calendar').value;
                var branchId = document.getElementById('branchId').value;
                
                console.log("doctorId " + doctorId);
                $.ajax({
                    url: "/MediCare/load-schedule-booking?branchId=" + branchId + "&serviceid=" + serviceId + "&doctorId=" + doctorId + "&date=" + date,
                    data: {
                    },
                    cache: false,
                    type: "GET",
                    success: function (response) {
                        var doctorSelectBox = document.getElementById("slot-select-box");
                        doctorSelectBox.innerHTML = response;
                    },
                    error: function (xhr) {
                        
                    }
                });
                dataLoaded = true;
                
            }
            function loadInfoOfAppointment() {
                console.log("Ajax - JS - load info of appointment:");
                var branchId = document.getElementById('branchId').value;
                var serviceId = document.getElementById('serviceId').value;
                var doctorId = document.getElementById('doctorId').value;
                var date = document.getElementById('booking-calendar').value;
                var slotId = document.getElementById('booking-slot').value;
                
                var patientName = document.getElementById("patientName").value;
                var birthDate = document.getElementById("birthDate").value;
                var genderRadios = document.getElementsByName("gender");
                var gender;
                for (var i = 0; i < genderRadios.length; i++) {
                    if (genderRadios[i].checked) {
                        gender = genderRadios[i].value;
                        break;
                    }
                }
                var phone = document.getElementById("phone").value;
                var email = document.getElementById("email").value;
                var description = document.getElementById("description").value;
                
                console.log("doctorId " + doctorId);
                $.ajax({
                    url: "/MediCare/load-info-appointment",
                    data: {
                        branchId: branchId,
                        serviceId: serviceId,
                        doctorId: doctorId,
                        date: date,
                        slotId: slotId,
                        
                        patientName: patientName,
                        birthDate: birthDate,
                        gender: gender,
                        phone: phone,
                        email: email,
                        description: description
                    },
                    cache: false,
                    type: "GET",
                    success: function (response) {
//                        var doctorSelectBox = document.getElementById("slot-select-box");
//                        doctorSelectBox.innerHTML = response;
                        var step3container = document.getElementById("step-3-container");
                        step3container.innerHTML = response;
                        console.log("success");
                    },
                    error: function (xhr) {
                        console.log("Error: " + xhr);
                    }
                });
            }
            
            function onServiceChange() {
                loadDoctors();
                loadScheduleOfDoctor();
            }
            
            // Validate for step1-container:
            function validateFormStep1() {
                var branchId = document.getElementById("branchId").value;
                var serviceId = document.getElementById("serviceId").value;
                var serviceError = document.getElementById("serviceError");
                console.log("serviceError");
                
                console.log("BranchId: " + branchId);
                if (branchId < 0 || serviceId < 0) {
                    console.log("Service Error: ");
                    serviceError.innerHTML = "* Thông tin bắt buộc";
                }
                
                var serviceId = document.getElementById("serviceId").value;
                console.log("ServiceId " + serviceId);
                var slotId = document.getElementById("booking-slot").value;
                console.log("SlotId " + slotId);
                
                var dateError = document.getElementById("dateError");
                console.log("dateError: " + dateError.value);
                // Lấy ngày hiện tại
                console.log("get current date:");
                var today = new Date();
                today.setDate(today.getDate() - 1);
//                var today = new Date(Date.now() + new Date().getTimezoneOffset() * 60000);
                
                // Lấy giá trị của ngày từ input
                var selectedDate = new Date(dateInput.value);
                // Kiểm tra nếu ngày đã chọn nhỏ hơn ngày hiện tại
                if (selectedDate < today) {
                    dateError.innerHTML = "* Ngày không thể nhỏ hơn ngày hiện tại.";
                    return false;
                } else {
                    dateError.innerHTML = ''; // Xóa thông báo lỗi nếu ngày hợp lệ
                }
                if (dataLoaded) {
                    console.log("dataloaded: TRUE");
                    var slotError = document.getElementById("slotError");
                    if (slotId < 0) {
                        console.log("Slot Error: ");
                        slotError.innerHTML = "* Thông tin bắt buộc";
                    }
                    if (branchId > 0 && serviceId > 0 && slotId > 0) {
                        return true;
                    }
                } else {
                    console.log("dataloaded: False");
                    
                }
                return false;
            }
            
            // Validate for step2-container:
            function validateFormStep2() {
                var patientName = document.getElementById("patientName").value;
                var name = document.getElementById("patientName");
                var birthDate = document.getElementById("birthDate").value;
                var birthDate2 = document.getElementById("birthDate");
                var phone = document.getElementById("phone").value;
                var phone1 = document.getElementById("phone");
                var description = document.getElementById("description").value;
                var email = document.getElementById("email");
                
                var inputNameError = document.getElementById("inputNameError");
                var inputBirthDateError = document.getElementById("inputBirthDateError");
                var inputPhoneError = document.getElementById("inputPhoneError");
                var inputSymptomsError = document.getElementById("inputSymptomsError");
                var inputEmailError = document.getElementById("inputEmailError");
                
                console.log("patient name: " + patientName);
                console.log("Birth date: " + birthDate);
                console.log("Phone: " + phone);
                console.log("Description: " + description);
                
                if (patientName === '') {
                    console.log("patient name === ''");
//                    inputNameError.innerHTML = "* Thông tin bắt buộc";
                } else {
                    if (!/^[a-zA-Z0-9\s-àáảãạăắằẳẵặâấầẩẫậèéẻẽẹêếềểễệđòóỏõọôốồổỗộơớờởỡợùúủũụưứừửữựìíỉĩịỳýỷỹỵ]+$/i.test(name.value.trim())) {
                        name.setCustomValidity("Tên chỉ được chứa chữ cái, số, khoảng trắng và dấu gạch ngang.");
                        name.reportValidity();
                        return false;
                    } else {
                        name.setCustomValidity("");
                    }
                }
                if (birthDate === '') {
                    console.log("birth date === ''");
                    inputBirthDateError.innerHTML = "* Thông tin bắt buộc";
                }
                
                if (phone === '') {
                    console.log("phone === ''");
                    inputPhoneError.innerHTML = "* Thông tin bắt buộc";
                } else {
                    if (!/^\d{10}$/.test(phone1.value.trim())) {
                        phone1.setCustomValidity("Số điện thoại chỉ được chứa số và phải có đúng 10 chữ số.");
                        phone1.reportValidity();
                        return false;
                    } else {
                        phone1.setCustomValidity("");
                    }
                }
                if (email.value === '') {
                    console.log("email === ''");
                    inputEmailError.innerHTML = "* Thông tin bắt buộc";
                } else {
                    if (!/^([a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z]{2,})+)*$/.test(email.value.trim())) {
                        email.setCustomValidity("Địa chỉ email không hợp lệ, vui lòng nhập lại.");
                        email.reportValidity();
                        return false;
                    } else {
                        email.setCustomValidity("");
                    }
                }
                
                if (description === '') {
                    console.log("Description === ''");
                    inputSymptomsError.innerHTML = "* Thông tin bắt buộc";
                }
                if (patientName === '' || birthDate === '' || phone === '' || description === '' || email.value === '') {
                    return false;
                }
                
                
                return true;
            }
            
            
            // Validate before submit:
            function validateForm() {
                var branchId = document.getElementById("branchId").value;
                console.log("BranchId: " + branchId);
                // Kiểm tra xem email có giá trị trước khi kiểm tra password
                if (!email.value.trim()) {
                    email.setCustomValidity("Vui lòng nhập địa chỉ email.");
                    email.reportValidity();
                    return false;
                } else {
                    email.setCustomValidity("");
                }
                
                if (email.value.trim() && !/^([a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z]{2,})+)*$/.test(email.value.trim())) {
                    email.setCustomValidity("Địa chỉ email không hợp lệ, vui lòng nhập lại.");
                    email.reportValidity();
                    return false;
                } else {
                    email.setCustomValidity("");
                }
                
                
                
                if (password.value.trim().length < 8) {
                    password.setCustomValidity("Mật khẩu phải bao gồm ít nhất 8 kí tự.");
                    password.reportValidity();
                    return false;
                } else {
                    password.setCustomValidity("");
                }
                
                if (password.value.trim() !== repassword.value.trim()) {
                    repassword.setCustomValidity("Vui lòng nhập lại giống với mật khẩu.");
                    repassword.reportValidity();
                    return false;
                } else {
                    repassword.setCustomValidity("");
                }
                
                if (!/^[a-zA-Z0-9\s-àáảãạăắằẳẵặâấầẩẫậèéẻẽẹêếềểễệđòóỏõọôốồổỗộơớờởỡợùúủũụưứừửữựìíỉĩịỳýỷỹỵ]+$/i.test(name.value.trim())) {
                    name.setCustomValidity("Tên chỉ được chứa chữ cái, số, khoảng trắng và dấu gạch ngang.");
                    name.reportValidity();
                    return false;
                } else {
                    name.setCustomValidity("");
                }
                
                if (address.value.trim() !== "") {
                    if (!/^[a-zA-Z0-9\s.,#'-àáảãạăắằẳẵặâấầẩẫậèéẻẽẹêếềểễệđòóỏõọôốồổỗộơớờởỡợùúủũụưứừửữựìíỉĩịỳýỷỹỵ]+$/i.test(address.value.trim())) {
                        address.setCustomValidity("Địa chỉ không hợp lệ.");
                        address.reportValidity();
                        return false;
                    } else {
                        address.setCustomValidity("");
                    }
                } else {
                    address.setCustomValidity(""); // Không cần validate nếu địa chỉ không được điền
                }
                
                
                
                var today = new Date();
                var birthDate = new Date(birthDate.value.trim());
                var age = today.getFullYear() - birthDate.getFullYear();
                var m = today.getMonth() - birthDate.getMonth();
                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                    age--;
                }
                
                if (age < 12) {
                    birthDate.setCustomValidity("Yêu cầu người dùng ít nhất 12 tuổi!");
                    birthDate.reportValidity();
                    return false;
                } else {
                    birthDate.setCustomValidity("");
                }
                
                if (!/^\d{10}$/.test(phone.value.trim())) {
                    phone.setCustomValidity("Số điện thoại chỉ được chứa số và phải có đúng 10 chữ số.");
                    phone.reportValidity();
                    return false;
                } else {
                    phone.setCustomValidity("");
                }
                
                return true;
            }
            
            function handleSubmit(clickedElement) {
                var elementId = clickedElement.id;
                var step1container = document.getElementById('step-1-container');
                var step2container = document.getElementById('step-2-container');
                var step3container = document.getElementById('step-3-container');
                console.log("Ten cua the duoc click la: " + elementId);
                if (elementId === 'step-2-to-3') {
                    console.log("element = step 2 to 3");
                    if (validateFormStep1()) {
                        console.log("step 1: TRUE");
                        step2container.style.display = 'none';
                        step3container.style.display = 'block';
                    } else {
                        console.log("step 1: FALSE");
                    }
                    loadInfoOfAppointment();
                } else if (elementId === 'step-2-to-1') {
                    console.log("element = step 2 to 1");
                    step2container.style.display = 'none';
                    step1container.style.display = 'block';
                } else if (elementId === 'step-1-to-2') {
                    console.log("element = step 1 to 2");
                    var inputNameError = document.getElementById("inputNameError");
                    var inputBirthDateError = document.getElementById("inputBirthDateError");
                    var inputPhoneError = document.getElementById("inputPhoneError");
                    var inputSymptomsError = document.getElementById("inputSymptomsError");
                    var inputEmailError = document.getElementById("inputEmailError");
                    
                    inputNameError.innerHTML = '';
                    inputBirthDateError.innerHTML = '';
                    inputPhoneError.innerHTML = '';
                    inputSymptomsError.innerHTML = '';
                    inputEmailError.innerHTML = '';
                    if (validateFormStep2()) {
                        console.log("step 2: TRUE");
                        step1container.style.display = 'none';
                        step2container.style.display = 'block';
                    } else {
                        console.log("step 2: FALSE");
                    }
                    
                } else if (elementId === 'step-3-to-2') {
                    console.log("element = step 3 to 2");
                    step3container.style.display = 'none';
                    step2container.style.display = 'block';
                } else if (elementId === 'submit') {
                    var confirmOTPForm = document.getElementById("confirm-OTP-Form");
                    confirmOTPForm.style.display = 'block';
                    // Close the modal if the user clicks outside of it
                    window.onclick = function (event) {
                        if (event.target === confirmOTPForm) {
                            confirmOTPForm.style.display = "none";
                        }
                    };
                    console.log("element = submit");
                    
                    console.log("Ajax - JS - submit to get OTP of appointment:");
                    var branchId = document.getElementById('branchId').value;
                    var serviceId = document.getElementById('serviceId').value;
                    var doctorId = document.getElementById('doctorId').value;
                    var date = document.getElementById('booking-calendar').value;
                    var slotId = document.getElementById('booking-slot').value;
                    
                    var patientName = document.getElementById("patientName").value;
                    var birthDate = document.getElementById("birthDate").value;
                    var genderRadios = document.getElementsByName("gender");
                    var gender;
                    for (var i = 0; i < genderRadios.length; i++) {
                        if (genderRadios[i].checked) {
                            gender = genderRadios[i].value;
                            break;
                        }
                    }
                    var phone = document.getElementById("phone").value;
                    var email = document.getElementById("email").value;
                    var description = document.getElementById("description").value;
                    console.log("requestOPT - JS: ");
                    console.log("slotId: " + slotId);
                    
                    $.ajax({
                        url: "/MediCare/user-book-appointment",
                        data: {
                            branchId: branchId,
                            serviceId: serviceId,
                            doctorId: doctorId,
                            date: date,
                            slotId: slotId,
                            
                            patientName: patientName,
                            birthDate: birthDate,
                            gender: gender,
                            phone: phone,
                            email: email,
                            description: description,
                            action: "requestOTP"
                        },
                        cache: false,
                        type: "POST",
                        success: function (response) {
                            console.log("response: " + response);
                            var message = response;
                            console.log("Message from JSON: " + message);
                            step3container.setAttribute('data-OTP', message);
                            console.log("data-OTP:" + step3container.getAttribute("data-OTP"));
                            console.log("success");
                        },
                        error: function (xhr) {
                            console.log("Error: " + xhr);
                        }
                    });
                }
                
            }
            
            function onchangeBranchId() {
                console.log("onchange branch");
                var branchId = document.getElementById("branchId").value;
                var serviceId = document.getElementById("serviceId");
                console.log("serviceId =" + serviceId.value);
                
                
                console.log("BranchId: " + branchId);
                if (serviceId.style.display === 'none' || serviceId.style.display === 'undefined' || serviceId.style.display === undefined || serviceId.style.display === '') {
                    console.log("serviceId.display =" + serviceId.display);
                    console.log("serviceId = none");
                    serviceId.style.display = 'block';
                }
                var serviceError = document.getElementById("serviceError");
                serviceError.innerHTML = '';
            }
            
            function onchangeSlot() {
                var slotError = document.getElementById("slotError");
                slotError.innerHTML = '';
            }
            
//            window.onload = function () {
//                var branchId = document.getElementById("branchId");
//                if (branchId.style.display === 'none') {
//                    branchId.style.display = 'block';
//                }
//            };
        </script>
    </body>

</html>