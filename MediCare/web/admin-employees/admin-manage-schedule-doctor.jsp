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
                            </div>
                            <div class="col-sm-8 col-9 text-right m-b-20">
                                <a href="${pageContext.request.contextPath}/admin-list-employee?add-employee=true" class="btn btn-primary float-right btn-rounded"><i class="fa fa-plus"></i> Thêm nhân viên</a>
                            </div>
                        </div>
                        <form action="${pageContext.request.contextPath}/admin-list-employee?">
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
                        </form>

                        <!--Add schedule, delete schedule - start-->
                        <div class="row">
                            <div class="text-right m-b-20 add-doctor-schedule-btn">
                                <a href="${pageContext.request.contextPath}/admin-manage-schedule-doctor" class="btn btn-primary float-right btn-rounded"><i class="fa fa-plus"></i> Thêm lịch cho nhân viên</a>
                            </div>
                        </div>
                        <!--Add schedule, delete schedule - end-->

                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-responsive">
                                    <table class="table custom-table table-schedule-doctor">
                                        <thead>
                                            <tr>
                                                <c:forEach var="title" items="${TITLE_EMPLOYEE}">
                                                    ${title.toString()}
                                                </c:forEach>
                                                <th class="text-right">Hành động</th>
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
                                                    <td class="text-right">
                                                        <div class="dropdown dropdown-action">
                                                            <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                            <div class="dropdown-menu dropdown-menu-right">
                                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin-list-employee?edit-employee=true&id=${list.getId()}"><i class="fa fa-pencil m-r-5"></i> Sửa</a>
                                                                <!--<a class="dropdown-item" onclick="return confirm('Bạn có chắc chắn muốn xóa? (Khi xóa thì dữ liệu này cùng những dữ liệu liên kết sẽ biến mất hoàn toàn và không thể khôi phục lại)');" href="${pageContext.request.contextPath}/admin-delete-employee-controller?id=${list.getId()}" ><i class="fa fa-trash-o m-r-5"></i> Xóa</a>-->
                                                            </div>
                                                        </div>
                                                    </td>
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
            <p id="year-schedule-doctor-txt">YEAR</p>
            <select class="year-schedule-doctor" name="year-schedule-doctor" id="year-schedule-doctor" onchange="eventLoadWeekByYear(this)">
                <c:forEach items="${requestScope.years}" var="year" >
                    <c:if test="${requestScope.currentYear.equals(year)}">
                        <option selected value="${year}">${year}</option>
                    </c:if>
                    <c:if test="${!requestScope.currentYear.equals(year)}">
                        <option value="${year}">${year}</option>
                    </c:if>
                </c:forEach>

            </select>
            <p id="week-schedule-doctor-txt">WEEk</p>
            <select class="week-schedule-doctor" name="week-schedule-doctor" id="week-schedule-doctor" onchange="eventLoadSlotsByWeek(this)">
                <c:forEach items="${requestScope.weeksIn5Year}" var="week">

                    <option value="${week.slotId}">${week.startTime} To ${week.endTime}</option>
                </c:forEach>
            </select>
            <i class="fas fa-close" id="close-schedule-btn" onclick="closeScheduleOfDoctorForm()"></i>
            <form action="admin-update-schedule-doctor" class="form-container" method="post">
                <h3><b>Chỉnh sửa lịch trình của bác sĩ</b></h3>

                <!--schedule table - start-->
                <div class="container">
                    <div class="timetable-img text-center">
                        <img src="img/content/timetable.png" alt="">
                    </div>
                    <div class="table-responsive">
                        <table class="table table-bordered text-center schedule-doctor-detail-table">
                            <thead>
                                <c:set var="daysOfCurrentWeek" value="${requestScope.daysOfCurrentWeek}"></c:set>
                                <c:set var="daysOfCurrentWeek_raw" value="${requestScope.daysOfCurrentWeek_raw}"></c:set>
                                    <tr class="bg-light-gray">
                                        <th class="text-uppercase">Slot</th>
                                        <th class="text-uppercase"><p>Thứ 2</p><p>${daysOfCurrentWeek.get(0)}</p></th>
                                    <th class="text-uppercase"><p>Thứ 3</p><p>${daysOfCurrentWeek.get(1)}</p></th>
                                    <th class="text-uppercase"><p>Thứ 4</p><p>${daysOfCurrentWeek.get(2)}</p></th>
                                    <th class="text-uppercase"><p>Thứ 5</p><p>${daysOfCurrentWeek.get(3)}</p></th>
                                    <th class="text-uppercase"><p>Thứ 6</p><p>${daysOfCurrentWeek.get(4)}</p></th>
                                    <th class="text-uppercase"><p>Thứ 7</p><p>${daysOfCurrentWeek.get(5)}</p></th>
                                    <th class="text-uppercase"><p>Chủ nhật</p><p>${daysOfCurrentWeek.get(6)}</p></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="slotsOfDoctor" value="${requestScope.slotsOfDoctor}"></c:set>
                                <c:forEach items="${requestScope.slots}" var="slot">
                                    <tr>
                                        <td class="align-middle">Slot ${slot.getSlotId()}</td>
                                        <c:forEach begin="1" end="7" var="i">
                                            <td>
                                                <c:set var="slotFound" value="false" />
                                                <c:forEach items="${requestScope.slotsOfDoctor}" var="s">
                                                    <c:if test="${daysOfCurrentWeek_raw.get(i-1).equals(s.getWorkDate()) && slot.getSlotId().equals(s.getSlotId())}">
                                                        <i class="fas fa-close delete-schedule-icon" data-scheduleDetailId="${slot.id}" onclick="eventClickDeleteScheduleIcon(this)"></i>
                                                        <!--<span class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom font-size16 xs-font-size13">Dance</span>-->
                                                        <c:forEach items="${sessionScope.slotStatus}" var="status" >
                                                            <c:if test="${s.slotStatus().equals(status.getLink())}">
                                                                <div class="status-appointment"><span class="custom-badge ${status.icon}">${status.name}</span></td>
                                                            </c:if>
                                                        </c:forEach>
                                            <div class="margin-10px-top font-size10 time-slot-css">${slot.getStartTime()} - ${slot.getEndTime()}</div>
                                            <!--<div class="font-size13 text-light-gray">Ivana Wong</div>-->
                                            <c:set var="slotFound" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${not slotFound}">
                                        <!--<img src="../assets/client/images/add-icon.png" >-->
                                        <!--<img src="../assets/client/images/active-icon.png" alt="alt"/>-->
                                        <i class="fas fa-plus create-schedule-icon" onclick="eventClickCreateScheduleDoctor(this)"></i>
                                        <span>-</span>
                                        <!--<i class="fas fa-close delete-schedule-icon"></i>-->

                                    </c:if>
                                    </td>

                                </c:forEach>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--schedule table - end-->

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


                console.log("Event - Load Slots By Popup (&& year && doctorId)");
                var year = document.getElementById("year-schedule-doctor").value;
                var modal = document.getElementById("scheduleDoctorForm");
                var doctorId = modal.getAttribute("data-doctorId");
                console.log("DoctorId: " + doctorId);
                console.log("Year: " + year);

//                console.log("Year: " + year);
//                console.log("Week: " + event.value);

                $.ajax({
                    url: "/MediCare/admin-manage-schedule-doctor",
                    data: {
                        year: year,
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
        </script>


    </body>
</html>
