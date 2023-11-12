<%-- 
    Document   : admin-schedule-doctor
    Created on : Oct 17, 2023, 3:41:13 AM
    Author     : tubinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../admin-general/admin-head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/admin-display-table.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <title>Trang nhân viên</title>

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
                                <h4 class="page-title">Quản lí lịch trình bác sĩ</h4>
                                <!--<img id="loading" src="${pageContext.request.contextPath}/assets/admin/images/c7e1b7b5753737039e1bdbda578132b8.gif" alt="Loading..." style="display: block;">-->

                            </div>
                            <!--                            <div class="col-sm-8 col-9 text-right m-b-20">
                                                            <a href="${pageContext.request.contextPath}/admin-list-employee?add-employee=true" class="btn btn-primary float-right btn-rounded"><i class="fa fa-plus"></i> Thêm nhân viên</a>
                                                        </div>-->
                        </div>
<!--                        <form action="${pageContext.request.contextPath}/admin-list-employee?">
                            <input type="hidden" name="search-employee" value="true">
                            <div class="row filter-row">
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">ID nhân viên</label>
                                        <input type="text" class="form-control floating" name="searchId" value="${searchId}">
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Tên nhân viên</label>
                                        <input type="text" class="form-control floating" name ="searchName" ${searchName}">
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">

                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <button class="btn btn-success btn-block" name="btAction" value="Search Employee"> Tìm kiếm </button>
                                </div>
                            </div>
                        </form>-->

                        <!--Add schedule, delete schedule - start-->
                        <div class="row">
                            <div class="text-right m-b-20 add-doctor-schedule-btn">
                                <a onclick="eventClickAddSchedule(this)" href="#" class="btn btn-primary float-right btn-rounded"><i class="fa fa-plus"></i> Thêm lịch cho bác sĩ</a>
                            </div>
                            <div class="text-right m-b-20 add-doctor-schedule-btn">
                                <a onclick="eventClickAddEvent(this)" href="#" class="btn btn-primary float-right btn-rounded"><i class="fa fa-plus"></i> Thêm lịch nghỉ lễ</a>
                            </div>
                            <div class="text-right m-b-20 add-doctor-schedule-btn">
                                <a onclick="eventClickAddLeave(this)" href="#" class="btn btn-primary float-right btn-rounded"><i class="fa fa-plus"></i> Thêm ngày nghỉ của bác sĩ</a>
                            </div>
                        </div>
                        <!--Add schedule, delete schedule - end-->

                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-schedule-doctor">
                                        <thead>
                                            <tr>
                                                <c:forEach var="title" items="${TITLE_EMPLOYEE}">
                                                    ${title.toString()}
                                                </c:forEach>
                                                <!--<th class="text-right">Hành động</th>-->
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <c:forEach var="doctor" items="${doctors}">
                                                <tr data-doctorId="${doctor.getId()}" name="display-table-tr" onclick="eventClickScheduleDoctor(this)">
                                                    <td>${doctor.getId()}</td>
                                                    <td>${doctor.getEmail()}</td>
                                                    <td>${doctor.getBranchName()}</td>
                                                    <td>${doctor.getDisplayName()}</td>
                                                    <!--                                                    <td class="text-right">
                                                                                                            <div class="dropdown dropdown-action">
                                                                                                                <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                                                                                <div class="dropdown-menu dropdown-menu-right">
                                                                                                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin-list-employee?edit-employee=true&id=${list.getId()}"><i class="fa fa-pencil m-r-5"></i> Sửa</a>
                                                                                                                    <a class="dropdown-item" onclick="return confirm('Bạn có chắc chắn muốn xóa? (Khi xóa thì dữ liệu này cùng những dữ liệu liên kết sẽ biến mất hoàn toàn và không thể khôi phục lại)');" href="${pageContext.request.contextPath}/admin-delete-employee-controller?id=${list.getId()}" ><i class="fa fa-trash-o m-r-5"></i> Xóa</a>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </td>-->
                                                </tr>
                                            </c:forEach>


                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                        <%@include file="../admin-general/admin-notifications-box.jsp"%>
                    </div>
                </div>

                <!-- pagination section -->
                <nav aria-label="Pagination">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage != 1}">
                            <li class="page-item">
                                <c:if test="${IS_SEARCH==1}">
                                    <a class="page-link" href="admin-list-employee?search-employee=true&page=${currentPage - 1}&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}"><<</a>
                                </c:if>
                                <c:if test="${IS_SEARCH==0}">
                                    <a class="page-link" href="admin-list-employee?page=${currentPage - 1}"><<</a>
                                </c:if>    
                            </li>
                        </c:if>

                        <c:forEach begin="1" end="${pageCount}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="page-item active">
                                        <a class="page-link" href="#">${i}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <c:if test="${IS_SEARCH==1}">
                                            <a class="page-link" href="admin-list-employee?search-employee=true&page=${i}&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}">${i}</a>
                                        </c:if>
                                        <c:if test="${IS_SEARCH==0}">
                                            <a class="page-link" href="admin-list-employee?page=${i}">${i}</a>
                                        </c:if>    
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${currentPage lt pageCount}">
                            <li class="page-item">
                                <c:if test="${IS_SEARCH==1}">
                                    <a class="page-link" href="admin-list-employee?search-employee=true&page=${currentPage + 1}&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}">>></a>
                                </c:if>
                                <c:if test="${IS_SEARCH==0}">
                                    <a class="page-link" href="admin-list-employee?page=${currentPage + 1}">>></a>
                                </c:if>   
                            </li>
                        </c:if>
                    </ul>
                </nav>
                <!-- pagination section -->
            </div>
            <!--        <div class="sidebar-overlay" data-reff=""></div>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.dataTables.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/dataTables.bootstrap4.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.slimscroll.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/select2.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/moment.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap-datetimepicker.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>-->
            <jsp:include page="../admin-general/admin-script.jsp"/>
        </c:if>

        <!--Popup - Start-->
        <div id="scheduleDoctorForm" class="form-popup">

        </div>
        <!--Popup - End-->


        <script>


            function onchangeBranchIdLoadSchedule() {
                var branchId = document.getElementById("branchId");
                var monthSelect = document.getElementById("monthSelect");
                console.log("branchId = " + branchId.value);
                console.log("monthSelect = " + monthSelect.value);
                var modal = document.getElementById("scheduleDoctorForm");
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
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
                        branchId: branchId.value,
                        monthSelect: monthSelect.value,
                        action: "onchange-branch-add-schedule"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
//                        var step3container = document.getElementById("step-3-container");
//                        step3container.innerHTML = response;
                        var scheduleDoctorForm = document.getElementById("scheduleDoctorForm");
                        scheduleDoctorForm.innerHTML = response;
//                        alert("Save success");
                        console.log("success");
//                        setTimeout(function () {
//                            window.location.href = "admin-manage-pending-appointments";
//                        }, 500);

                    },
                    error: function (xhr) {
                        console.log("Error: " + xhr);
                    }
                });
            }
            function saveAddScheduleOfDoctorForm() {
                var branchId = document.getElementById("branchId");
                var monthSelect = document.getElementById("monthSelect");
                console.log("branchId = " + branchId.value);
                console.log("monthSelect = " + monthSelect.value);
                var modal = document.getElementById("scheduleDoctorForm");
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
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
                        branchId: branchId.value,
                        monthSelect: monthSelect.value,
                        action: "save-add-schedule"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
//                        var step3container = document.getElementById("step-3-container");
//                        step3container.innerHTML = response;
                        var scheduleDoctorForm = document.getElementById("scheduleDoctorForm");
                        scheduleDoctorForm.innerHTML = response;
//                        alert("Save success");
                        console.log("success");
//                        setTimeout(function () {
//                            window.location.href = "admin-manage-pending-appointments";
//                        }, 500);

                    },
                    error: function (xhr) {
                        console.log("Error: " + xhr);
                    }
                });
            }
            function saveAddEvent() {
                console.log("Event - save add event:");
                var branchId = document.getElementById("branchId");
                var eventName = document.getElementById("eventName");
                var fromDate = document.getElementById("fromDate").value;
                var toDate = document.getElementById("toDate").value;
                console.log("branchId = " + branchId.value);
                var modal = document.getElementById("scheduleDoctorForm");
                var error = document.getElementById("error-save-add-appointment");
                console.log("error: " + error.innerHTML);
                modal.style.display = "block";

                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                    }
                };

                if (eventName.value === null || eventName.value === "") {
                    console.log("eventName.value === null");
                } else {
                    console.log("eventName.value = " + eventName.value);
                }
                if (fromDate === "Invalid Date") {
                    console.log("fromDate.value is invalid");
                } else {
                    console.log("fromDate.value = " + fromDate);
                }

                if (eventName.value === "" || fromDate === "" || toDate === "" || fromDate === "Invalid Date" || toDate === "Invalid Date" || fromDate === null || toDate === null) {
                    error.innerHTML = "Vui lòng điền tất cả thông tin bắt buộc!";
                }

                // Check if fromDate is less than or equal to toDate
                if (fromDate > toDate) {
                    error.innerHTML = "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc";
                }


                if (eventName.value !== "" && !(fromDate === "" || toDate === "" || fromDate === "Invalid Date" || toDate === "Invalid Date" || fromDate === null || toDate === null) && fromDate <= toDate) {
                    $.ajax({
                        url: "/MediCare/admin-manage-schedule-doctor",
                        data: {
                            branchId: branchId.value,
                            eventName: eventName.value,
                            fromDate: fromDate, // Chuyển đổi thành chuỗi ngày hợp lệ
                            toDate: toDate, // Chuyển đổi thành chuỗi ngày hợp lệ
                            action: "save-add-event"
                        },
                        cache: false,
                        type: "POST",
                        dataType: "json", // Ensure the response is treated as JSON
                        success: function (response) {
                            console.log("json - response:", response);
                            var status = response.status;
                            var msg = response.message;
                            console.log("status: " + status);
                            if (status === "success") {
                                alert(msg);
                                setTimeout(function () {
                                    window.location.href = "admin-manage-schedule-doctor";
                                }, 500);
                            } else {
                                if (confirm(msg)) {
                                    eventName.value = "";
                                    document.getElementById("fromDate").value = "";
                                    document.getElementById("toDate").value = "";
                                } else {
                                    setTimeout(function () {
                                        window.location.href = "admin-manage-schedule-doctor";
                                    }, 500);
                                }
                            }
                            console.log("success");
                        },
                        error: function (xhr) {
                            console.log("Error: " + xhr);
                        }
                    });
                }
            }

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


                console.log("Event - Load Slots By Popup (&& year && doctorId)");
//                var year = document.getElementById("year-schedule-doctor").value;
                var modal = document.getElementById("scheduleDoctorForm");
                var doctorId = modal.getAttribute("data-doctorId");
                console.log("DoctorId: " + doctorId);
//                console.log("Year: " + year);

//                console.log("Year: " + year);
//                console.log("Week: " + event.value);

                $.ajax({
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
//                        year: year,
                        doctorId: doctorId,
//                        week: event.value,
                        onclickPopup: "true"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        console.log("ScheduleForm HTML is changed!");
                        var scheduleDoctorForm = document.getElementById("scheduleDoctorForm");
                        scheduleDoctorForm.innerHTML = response;
                    },
                    error: function (xhr) {

                    }
                });
            }

            function closeScheduleOfDoctorForm() {
                var modal = document.getElementById("scheduleDoctorForm");
                modal.style.display = "none";
                document.getElementById("method").value = "";
                document.getElementById("schedule-submit-button").innerHTML = "";
            }

            function eventClickCreateScheduleDoctor(event) {
                console.log("Doctor is click: " + event.getAttribute("data-doctorId"));
            }

//             Load all week by Year:
            function eventLoadWeekByYear(event) {
                console.log("Event: " + event.value);
                var modal = document.getElementById("scheduleDoctorForm");
                var doctorId = modal.getAttribute("data-doctorId");
                console.log("DoctorId: " + doctorId);
                $.ajax({
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
                        year: event.value,
                        doctorId: doctorId,
                        onchangeYear: "true"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var scheduleDoctorForm = document.getElementById("scheduleDoctorForm");
                        scheduleDoctorForm.innerHTML = response;
                    },
                    error: function (xhr) {

                    }
                });
            }
//             Load all week by Week (And Year):
            function eventLoadSlotsByWeek(event) {
                console.log("Event - Load Slots By Week (&& year && doctorId)");
                console.log("Event is onchange: " + event.value);
                var year = document.getElementById("year-schedule-doctor").value;
                var modal = document.getElementById("scheduleDoctorForm");
                var doctorId = modal.getAttribute("data-doctorId");
                console.log("DoctorId: " + doctorId);
                console.log("Year: " + year);
                console.log("Week: " + event.value);

                $.ajax({
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
                        year: year,
                        doctorId: doctorId,
                        week: event.value,
                        onchangeWeek: "true"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var scheduleDoctorForm = document.getElementById("scheduleDoctorForm");
                        scheduleDoctorForm.innerHTML = response;
                    },
                    error: function (xhr) {

                    }
                });
            }

//            Delete a slot (delete ScheduleDetail object by id) by id:
            function eventClickDeleteScheduleIcon(event) {

                console.log("Event: Click To Delete Schedule By ScheduleDetailId:");
                console.log("ScheduleDetailId = " + event.getAttribute("data-scheduleDetailId"));
                var year = document.getElementById("year-schedule-doctor").value;
                var week = document.getElementById("week-schedule-doctor").value;
                var modal = document.getElementById("scheduleDoctorForm");
                var doctorId = modal.getAttribute("data-doctorId");
                var scheduleDetailId = event.getAttribute("data-scheduleDetailId");
                console.log("Year = " + year);
                console.log("Week = " + week);
                console.log("DoctorId = " + doctorId);
                var confirmed = confirm("Bạn có chắc chắn muốn xóa slot này của bác sĩ " + doctorId + "?");
                if (confirmed) {
                    $.ajax({
                        url: "/MediCare/admin-manage-schedule-doctor",
                        data: {
                            year: year,
                            doctorId: doctorId,
                            week: week,
                            scheduleDetailId: scheduleDetailId,
                            deleteSchedule: "true"
                        },
                        cache: false,
                        type: "POST",
                        success: function (response) {
                            var scheduleDoctorForm = document.getElementById("scheduleDoctorForm");
                            scheduleDoctorForm.innerHTML = response;
                            window.alert("Xóa thành công!");
                        },
                        error: function (xhr) {

                        }
                    });
                }
            }
            function eventClickAddSchedule(event) {
                console.log("Event: Click To Add Main schedule: " + event);
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
                $.ajax({
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
                        action: "add-schedule-all-doctor"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var scheduleDoctorForm = document.getElementById("scheduleDoctorForm");
                        scheduleDoctorForm.innerHTML = response;
                    },
                    error: function (xhr) {

                    }
                });
            }
            function eventClickAddEvent(event) {
                console.log("Event: Click To Add Event schedule: " + event);
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
                $.ajax({
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
                        action: "add-event"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var scheduleDoctorForm = document.getElementById("scheduleDoctorForm");
                        scheduleDoctorForm.innerHTML = response;
                    },
                    error: function (xhr) {

                    }
                });
            }
            function eventClickAddLeave(event) {
                console.log("Event: Click To Add Leave " + event);
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
                $.ajax({
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
                        action: "add-leave"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var scheduleDoctorForm = document.getElementById("scheduleDoctorForm");
                        scheduleDoctorForm.innerHTML = response;
                    },
                    error: function (xhr) {

                    }
                });
            }

            function onClickChooseDoctor(event) {
                console.log("Event choose doctor: " + event);
                var doctorId = event.getAttribute('data-doctorId');
                var doctorName = event.getAttribute('data-doctorName');
                console.log("Attribute - doctorId: " + doctorId);
                var textChooseDoctor = document.getElementById("text-doctor-add-leave");
                console.log(textChooseDoctor.innerHTML);
                textChooseDoctor.innerHTML = "Bạn đang thêm ngày nghỉ cho bác sĩ " + doctorName;
                console.log(textChooseDoctor.innerHTML);
                textChooseDoctor.setAttribute("check-choose-doctor", "true");
                textChooseDoctor.setAttribute("data-doctorName", doctorName);
            }

            function saveAddLeave() {
                console.log("Event - save add leave:");
                var fromDate = document.getElementById("fromDate").value;
                console.log("from date: " + fromDate);
                var toDate = document.getElementById("toDate").value;
                console.log("to date: " + toDate);
                var modal = document.getElementById("scheduleDoctorForm");
                var textChooseDoctor = document.getElementById("text-doctor-add-leave");
                var checkChooseDoctor = textChooseDoctor.getAttribute("check-choose-doctor");
                var error = document.getElementById("error-save-add-appointment");
                console.log("check-choose-doctor: " + checkChooseDoctor);
                var textChooseDoctor = document.getElementById("text-doctor-add-leave");
                var doctorId = textChooseDoctor.getAttribute("data-doctorName");

                console.log("error: " + error.innerHTML);
//                modal.style.display = "block";

                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                    }
                };
                if (checkChooseDoctor === null || checkChooseDoctor === "") {
                    error.innerHTML = "Vui lòng chọn 1 bác sĩ cụ thể!";

                } else {
                    error.innerHTML = "";
                    if (fromDate === "" || toDate === "") {
                        error.innerHTML = "Vui lòng điền tất cả thông tin bắt buộc!";
                    } else {
                        // Check if fromDate is less than or equal to toDate
                        if (fromDate > toDate) {
                            error.innerHTML = "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc";
                        } else {
                            error.innerHTML = "";
                        }
                    }
                }

                if (fromDate !== "" && toDate !== "" && fromDate <= toDate && checkChooseDoctor !== "") {
                    console.log("Enough condition!!");
                    $.ajax({
                        url: "/MediCare/admin-manage-schedule-doctor",
                        data: {
                            doctorId: doctorId,
                            fromDate: fromDate, // Chuyển đổi thành chuỗi ngày hợp lệ
                            toDate: toDate, // Chuyển đổi thành chuỗi ngày hợp lệ
                            action: "save-add-leave"
                        },
                        cache: false,
                        type: "POST",
                        dataType: "json", // Ensure the response is treated as JSON
                        success: function (response) {
                            console.log("json - response:", response);
                            var status = response.status;
                            var msg = response.message;
                            console.log("status: " + status);
                            if (status === "success") {
                                alert(msg);
                                setTimeout(function () {
                                    window.location.href = "admin-manage-schedule-doctor";
                                }, 500);
                            } else {
                                if (confirm(msg)) {
                                    eventName.value = "";
                                    document.getElementById("fromDate").value = "";
                                    document.getElementById("toDate").value = "";
                                } else {
                                    setTimeout(function () {
                                        window.location.href = "admin-manage-schedule-doctor";
                                    }, 500);
                                }
                            }
                            console.log("success");
                        },
                        error: function (xhr) {
                            console.log("Error: " + xhr);
                        }
                    });
                }
            }

            function oninputSearchDoctor(event) {
                console.log("oninput - search - doctor:");
                var searchPattern = event.value;
                console.log("Search - pattern: " + searchPattern);
                $.ajax({
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
                        searchPattern: searchPattern,
                        action: "search-doctor-oninput"
                    },
                    cache: false,
                    type: "POST",
//                        dataType: "json", // Ensure the response is treated as JSON
                    success: function (response) {
                        console.log("json - response:", response);
                        document.getElementById("search-doctor-table").innerHTML = response;
                        console.log("success");
                    },
                    error: function (xhr) {
                        console.log("Error: " + xhr);
                    }
                });

            }

        </script>
    </body>
</html>
