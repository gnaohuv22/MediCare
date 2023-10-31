<%-- 
    Document   : doctor-schedule
    Created on : Oct 26, 2023, 1:37:37 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="doctor-head.jsp"/>
        <title>Lịch hẹn khám | MediCare</title>
    </head>
    <body>
        <jsp:include page="doctor-header.jsp"/>
        <div class="container">
            <div class="schedule-doctor">
                <div class="card">
                    <div class="card-header text-center align-items-center">
                        <span id="prev" class="fas fa-chevron-left float-left mt-2 nav-button"></span>
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="monthYear" data-toggle="dropdown">
                            </button>
                            <div class="dropdown-menu" id="monthYearDropdown"></div>
                        </div>
                        <span id="next" class="fas fa-chevron-right float-right mt-2 nav-button"></span>
                    </div>
                    <table class="table table-bordered table-responsive-sm" id="calendar">
                        <thead></thead>
                        <tbody></tbody>
                    </table>
                </div>
                <div id="appointmentSchedule"></div>
            </div>
        </div>

        <button id="back-to-top" title="Back to top">↑</button>

        <div id="appointmentModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 id="modalTitle"></h2>
                    <span class="close">
                        &times;
                    </span>
                </div>
                <div class="modal-body row">
                    <div class="col-md-8" id="appointmentDetails"></div>
                    <div class="col-md-4 prpt">
                        <img id="profile-picture" width="fit-content" height="auto" style="border-radius: 50%" src="" alt="Patient Profile Picture"/>
                    </div>
                </div>
            </div>
        </div>

        <div id="notificationModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                </div>
                <p id="notificationMessage"></p>
            </div>
        </div>

        <!-- Create a confirm modal window -->
        <div id="confirmModal" class="modal">
            <div class="modal-content">
                <p>Bạn có muốn thay đổi trạng thái cuộc hẹn này?</p>
                <div class="d-flex flex-row" style="text-align: right">
                    <button id="confirmYes">Đồng ý</button>
                    <button id="confirmNo">Huỷ bỏ</button>
                </div>
            </div>
        </div>

        <jsp:include page="doctor-footer.jsp"/>
        <jsp:include page="doctor-script.jsp"/>
        <script src="${pageContext.request.contextPath}/assets/doctor/js/load-appointment.js"></script>
        <script>
            var activeDate = new string();

            function createClickHandler(dateString) {
                return function (e) {
                    e.preventDefault();
                    $('#calendar a.active').removeClass('active');
                    $(this).addClass('active');
                    activeDate = dateString;
                    loadAppointments(dateString);
                };
            }
        </script>

        <script>
            $(document).ready(function () {

                var date = new Date();
                var month = date.getMonth();
                var year = date.getFullYear();

                function createCalendar() {
                    var firstDayOfMonth = new Date(year, month, 1).getDay();
                    firstDayOfMonth = (firstDayOfMonth === 0) ? 6 : firstDayOfMonth - 1;
                    var daysInMonth = new Date(year, month + 1, 0).getDate();
                    $('#monthYear').text('Tháng ' + (month + 1) + ', ' + year);
                    var calendarBody = $('#calendar tbody');
                    calendarBody.empty();

                    var daysOfWeek = ['Thứ hai', 'Thứ ba', 'Thứ tư', 'Thứ năm', 'Thứ sáu', 'Thứ bảy', 'Chủ nhật'];
                    var daysRow = $('<tr></tr>');
                    for (var i = 0; i < 7; ++i) {
                        var dayCell = $('<th>' + daysOfWeek[i] + '</th>');
                        daysRow.append(dayCell);
                    }
                    calendarBody.append(daysRow);

                    var cellCount = 0;
                    var row;

                    for (var i = 0; i < firstDayOfMonth; ++i) {
                        if (cellCount % 7 === 0) {
                            row = $('<tr></tr>');
                            calendarBody.append(row);
                        }
                        var dayCell = $('<td></td>');
                        row.append(dayCell);
                        ++cellCount;
                    }

                    for (var i = 1; i <= daysInMonth; ++i) {
                        if (cellCount % 7 === 0) {
                            row = $('<tr></tr>');
                            calendarBody.append(row);
                        }
                        var dayCell = 0;
                        if (i < 10)
                            dayCell = $('<td><a class="schedule-day" href="?day=' + year + '-' + (month + 1) + '-' + i + '">' + '0' + i + '</a></td>');
                        else
                            dayCell = $('<td><a class="schedule-day" href="?day=' + year + '-' + (month + 1) + '-' + i + '">' + i + '</a></td>');
                        var dayString = year + '-' + (month + 1) + '-' + i;
                        if (dayString === activeDate) {
                            dayCell.find('a').addClass('active');
                        }
                        dayCell.find('a').click(createClickHandler(year + '-' + (month + 1) + '-' + i));
                        row.append(dayCell);
                        ++cellCount;
                    }
                }


                $('#prev').click(function () {
                    if (month === 0) {
                        --year;
                        month = 11;
                    } else {
                        --month;
                    }
                    createCalendar();
                });

                $('#next').click(function () {
                    if (month === 11) {
                        month = 0;
                        ++year;
                    } else {
                        ++month;
                    }
                    createCalendar();
                });

                createCalendar();
                var currentDay = new Date();
                // Tự động tải lịch hẹn cho ngày hiện tại và thêm class 'active'
                var currentDayElement = $('#calendar a[href="?day=' + currentDay.toISOString().split('T')[0] + '"]');
                if (currentDayElement.length) {
                    activeDate = currentDay.toISOString().split('T')[0];
                    loadAppointments(currentDay.toISOString().split('T')[0]);
                    currentDayElement.addClass('active');
                }
                for (var i = year - 2; i <= year + 2; ++i) {
                    var yearDropdownItem = $('<div class="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#">' + i + '</a></div>');
                    $('#monthYearDropdown').append(yearDropdownItem);
                    var monthDropdownMenu = $('<div class="dropdown-menu"></div>');
                    yearDropdownItem.append(monthDropdownMenu);
                    for (var j = 0; j < 12; ++j) {
                        var monthDropdownItem = $('<a class="dropdown-item" href="#">Tháng ' + (j + 1) + '</a>');
                        monthDropdownItem.click(createClickHandlerForChangeDate(i, j));
                        monthDropdownMenu.append(monthDropdownItem);
                    }
                }

                $(document).ready(function () {
                    $('.dropdown-submenu a.dropdown-toggle').on('click', function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                    });
                });

                $(document).ready(function () {
                    $('.dropdown-submenu a.dropdown-toggle').on('mouseenter', function (e) {
                        e.stopPropagation();

                        // Đóng tất cả các menu cấp 2
                        $('.dropdown-submenu .dropdown-menu').hide();

                        // Mở menu hiện tại
                        $(this).next('.dropdown-menu').show();
                    });

                    $('.dropdown-submenu a.dropdown-toggle').parent().on('mouseleave', function (e) {
                        e.stopPropagation();

                        $('.dropdown-submenu .dropdown-menu').hide();

                        // Đóng menu hiện tại
                        $(this).children('.dropdown-menu').hide();
                    });
                });

                function createClickHandlerForChangeDate(selectedYear, selectedMonth) {
                    return function (e) {
                        e.preventDefault();
                        month = selectedMonth;
                        year = selectedYear;
                        createCalendar();
                    };
                }
            });
        </script>

        <script>
            dayElement.querySelector('a').addEventListener('click', function (e) {
                e.preventDefault();
                loadAppointments(day.toLocaleDateString());
            });
        </script>
        <script>
            var appointmentModal = document.getElementById('appointmentModal');
            var appointmentSpan = document.getElementsByClassName("close")[0];

            // Khi người dùng nhấp vào nút đóng (x), đóng cửa sổ modal
            appointmentSpan.onclick = function () {
                appointmentModal.style.display = "none";
            };

            // Khi người dùng nhấp vào bất kỳ đâu bên ngoài cửa sổ modal, đóng nó
            window.addEventListener('click', function (event) {
                if (event.target === appointmentModal) {
                    appointmentModal.style.display = "none";
                }
            });

            var notificationModal = document.getElementById('notificationModal');
            var notificationSpan = document.getElementsByClassName("close")[1]; // Lưu ý rằng chúng ta sử dụng chỉ số 1 ở đây

            // Khi người dùng nhấp vào nút đóng (x), đóng cửa sổ modal
            notificationSpan.onclick = function () {
                notificationModal.style.display = "none";
            };

            // Khi người dùng nhấp vào bất kỳ đâu bên ngoài cửa sổ modal, đóng nó
            window.addEventListener('click', function (event) {
                if (event.target === notificationModal) {
                    notificationModal.style.display = "none";
                }
            });

        </script>
    </body>
</html>
