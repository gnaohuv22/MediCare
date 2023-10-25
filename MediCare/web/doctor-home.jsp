<%-- 
    Document   : doctor-home
    Created on : Oct 21, 2023, 11:29:57 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ dành cho bác sĩ | MediCare</title>
        <%@include file="doctor-head.jsp" %>
    </head>
    <body>
        <%@include file="doctor-header.jsp" %>

        <!-- Expect: Appointment Schedule in this week -->
        <div class="container">
            <c:choose>
                <c:when test="${doctorLoggedIn}">
                    <div class="week-schedule">
                        <div class="schedule-inner">
                            <div id="monthYearContainer"></div>
                            <div class="row" id="weekDaysContainer"></div>
                            <div id="appointmentSchedule"></div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row alert alert-warning" role="alert">
                        Hãy đăng nhập để theo dõi lịch làm việc
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Expect: Appointment Schedule in this week -->


        <!-- Expect: Recent review -->

        <!-- Expect: Recent review -->


        <!-- Expect: Display news -->
        <div class="news_section layout_padding">
            <div class="container">
                <h1 class="health_taital" style="font-weight: bold">Bài viết nổi bật</h1>
                <p class="health_text">
                    “Đi sâu vào các bài viết sâu sắc từ các chuyên gia chăm sóc sức khỏe hàng đầu, đưa ra những quan điểm mới mẻ về sức khỏe và thể chất.”
                </p>
                <div class="news_section_3 layout_padding">
                    <c:forEach items="${topNews}" var="news">
                        <a class="news-block" href="${pageContext.request.contextPath}/news/${news.getCategory().getHref()}/${news.getSlug()}">
                            <div class="news-cover">
                                <img src="${news.getCoverImage()}" alt="Cover image of ${news.getTitle()}"/>
                            </div>
                            <div class="news-title">
                                ${news.getTitle()}
                            </div>
                            <c:forEach items="${employees}" var="e">
                                <c:if test="${e.getEmail() eq news.getAuthor()}">
                                    <div class="news-author">
                                        Author: ${e.getName()}
                                    </div>
                                </c:if>
                            </c:forEach>

                            <div class="news-create-time">
                                <i class="fas fa-calendar"> </i> <span>${news.getCreatedAt()}</span>
                            </div>
                            <div class="news-view-count">
                                <i class="fas fa-eye"> </i> <span>${news.getViewCount()}</span>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- Expect: Display news -->

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
                        <img id="profile-picture" width="fit-content" height="auto" src="" alt="Patient Profile Picture"/>
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

        <%@include file="doctor-footer.jsp" %>

        <%@include file="doctor-script.jsp" %>
        <script>
            function loadAppointments(dateString) {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        var appointments = JSON.parse(this.responseText);
                        var scheduleContainer = document.getElementById('appointmentSchedule');
                        scheduleContainer.innerHTML = '';

                        if (appointments.length === 0) {
                            //If there are no appointment.
                            var alertElement = document.createElement('div');
                            alertElement.className = 'row alert alert-warning';
                            alertElement.role = 'alert';
                            var parts = dateString.split('-');
                            var formatted = parts.reverse().join('/');

                            alertElement.textContent = 'Ngày ' + formatted + ' không có cuộc hẹn với bệnh nhân nào.';
                            scheduleContainer.appendChild(alertElement);
                        } else {
                            appointments.forEach(function (appointment) {
                                var appointmentElement = document.createElement('div');
                                appointmentElement.className = 'card';

                                var headerElement = document.createElement('div');
                                headerElement.className = 'card-header';

                                var titleElement = document.createElement('h5');
                                titleElement.className = 'card-title';
                                titleElement.textContent = 'Thời gian: ' + appointment.plannedAt.split(' ')[1];

                                var subtitleElement = document.createElement('h6');
                                subtitleElement.className = 'card-subtitle mb-2 text-muted';
                                subtitleElement.textContent = 'Tên bệnh nhân: ' + (appointment.fp.name || "—");
                                headerElement.appendChild(titleElement);
                                headerElement.appendChild(subtitleElement);

                                var bodyElement = document.createElement('div');
                                bodyElement.className = 'card-body';
                                var serviceTextElement = document.createElement('p');
                                serviceTextElement.className = 'card-text';
                                serviceTextElement.textContent = 'Dịch vụ Khám bệnh: ' + (appointment.st.nametag || "—");
                                bodyElement.appendChild(serviceTextElement);

                                var footerElement = document.createElement('div');
                                footerElement.className = 'card-footer text-muted';

                                var statusElement = document.createElement('div');
                                statusElement.className = 'show-status';

                                switch (appointment.status) {
                                    case '0':
                                        statusElement.textContent = 'Chờ xác nhận';
                                        statusElement.className = 'status status-0';
                                        break;

                                    case '1':
                                        statusElement.textContent = 'Đang chờ khám';
                                        statusElement.className = 'status status-1';
                                        break;

                                    case '2':
                                        statusElement.textContent = 'Đã khám xong';
                                        statusElement.className = 'status status-2';
                                        break;

                                    case '3':
                                        statusElement.textContent = 'Đã huỷ';
                                        statusElement.className = 'status status-3';
                                        break;

                                    default:
                                        statusElement.textContent = 'Trạng thái không xác định';
                                        statusElement.className = 'status status-default';
                                        break;
                                }
                                footerElement.appendChild(statusElement);

                                appointmentElement.appendChild(headerElement);
                                appointmentElement.appendChild(bodyElement);
                                appointmentElement.appendChild(footerElement);

                                //When choose an appointment, display modal windows with details
                                appointmentElement.addEventListener('click', function () {
                                    var detailsElement = document.getElementById('appointmentDetails');
                                    detailsElement.innerHTML = '';

                                    var bodyElement = document.createElement('div');

                                    var serviceTextElement = document.createElement('p');
                                    serviceTextElement.className = 'card-text';
                                    serviceTextElement.textContent = 'Dịch vụ Khám bệnh: ' + (appointment.st.nametag || "—");

                                    var patientEmailElement = document.createElement('p');
                                    patientEmailElement.className = 'card-text';
                                    patientEmailElement.textContent = 'Địa chỉ Email: ' + (appointment.fp.email || "—");

                                    var patientNameElement = document.createElement('p');
                                    patientNameElement.className = 'card-text';
                                    patientNameElement.textContent = 'Tên: ' + (appointment.fp.name || "—");

                                    var patientGenderElement = document.createElement('p');
                                    patientGenderElement.className = 'card-text';
                                    patientGenderElement.textContent = 'Giới tính: ' + (appointment.fp.gender || "—");

                                    var patientAddressElement = document.createElement('p');
                                    patientAddressElement.className = 'card-text';
                                    patientAddressElement.textContent = 'Địa chỉ: ' + (appointment.fp.address || "—");

                                    var patientPhoneElement = document.createElement('p');
                                    patientPhoneElement.className = 'card-text';
                                    patientPhoneElement.textContent = 'SĐT: ' + (appointment.fp.phone || "—");

                                    var patientSymptomsElement = document.createElement('p');
                                    patientSymptomsElement.className = 'card-text';
                                    patientSymptomsElement.textContent = 'Triệu chứng: ' + (appointment.symptoms || "—");

                                    bodyElement.appendChild(serviceTextElement);
                                    bodyElement.appendChild(patientEmailElement);
                                    bodyElement.appendChild(patientNameElement);
                                    bodyElement.appendChild(patientGenderElement);
                                    bodyElement.appendChild(patientAddressElement);
                                    bodyElement.appendChild(patientPhoneElement);
                                    bodyElement.appendChild(patientSymptomsElement);

                                    var footerElement = document.createElement('div');
                                    footerElement.className = 'modal-footer';
                                    var insideFooter = document.createElement('div');

                                    switch (appointment.status) {
                                        case '0':
                                            insideFooter.textContent = 'Chờ xác nhận';
                                            insideFooter.className = 'status status-0';
                                            break;

                                        case '1':
                                            insideFooter.textContent = 'Đang chờ khám';
                                            insideFooter.className = 'status status-1';
                                            break;

                                        case '2':
                                            insideFooter.textContent = 'Đã khám xong';
                                            insideFooter.className = 'status status-2';
                                            break;

                                        case '3':
                                            insideFooter.textContent = 'Đã huỷ';
                                            insideFooter.className = 'status status-3';
                                            break;

                                        default:
                                            insideFooter.textContent = 'Trạng thái không xác định';
                                            insideFooter.className = 'status status-default';
                                            break;
                                    }
                                    footerElement.appendChild(insideFooter);

                                    //Change the status of an appointment AJAX
                                    var confirmButton = document.createElement('button');
                                    confirmButton.className = 'confirm-button';
                                    confirmButton.textContent = 'Xác nhận';

                                    var completeButton = document.createElement('button');
                                    completeButton.className = 'complete-button';
                                    completeButton.textContent = 'Hoàn tất Khám bệnh';

                                    if (appointment.status === '0') {
                                        footerElement.appendChild(confirmButton);
                                        footerElement.appendChild(completeButton);
                                        completeButton.style.display = 'none';
                                    }

                                    if (appointment.status === '1') {
                                        footerElement.appendChild(completeButton);
                                    }

                                    var confirmModal = document.getElementById('confirmModal');
                                    var confirmYes = document.getElementById('confirmYes');
                                    var confirmNo = document.getElementById('confirmNo');

                                    confirmButton.addEventListener('click', function () {
                                        confirmModal.style.display = 'block';
                                        confirmYes.addEventListener('click', function () {
                                            //Send AJAX request to Servlet to update the status
                                            var xhttp = new XMLHttpRequest();
                                            xhttp.onreadystatechange = function () {
                                                if (this.readyState === 4 && this.status === 200) {
                                                    //When request completed, display notification window
                                                    if (this.responseText === 'Trạng thái đã được cập nhật thành công!') {
                                                        document.getElementById('notificationMessage').textContent = this.responseText;
                                                        document.getElementById('notificationModal').style.display = 'block';
                                                        confirmButton.style.display = 'none';
                                                        completeButton.style.display = 'block';
                                                    } else {
                                                        document.getElementById('notificationMessage').textContent = this.responseText;
                                                        document.getElementById('notificationModal').style.display = 'block';
                                                    }
                                                }
                                            };
                                            xhttp.open("POST", "getAppointments", true);
                                            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                            xhttp.send("id=" + appointment.id + "&status=1");

                                            // Update class name of status to reflect the new state
                                            insideFooter.textContent = 'Đang chờ khám';
                                            insideFooter.className = 'status status-1';
                                            statusElement.textContent = 'Đang chờ khám';
                                            statusElement.className = 'status status-1';
                                            confirmModal.style.display = 'none';
                                            appointment.status = '1';
                                        });

                                        confirmNo.addEventListener('click', function () {
                                            confirmModal.style.display = 'none';
                                        });
                                    });

                                    completeButton.addEventListener('click', function () {
                                        confirmModal.style.display = 'block';
                                        confirmYes.addEventListener('click', function () {
                                            //Send AJAX request to Servlet to update the status
                                            var xhttp = new XMLHttpRequest();
                                            xhttp.onreadystatechange = function () {
                                                if (this.readyState === 4 && this.status === 200) {
                                                    //When request completed, display notification window
                                                    if (this.responseText === 'Trạng thái đã được cập nhật thành công!') {
                                                        document.getElementById('notificationMessage').textContent = this.responseText;
                                                        document.getElementById('notificationModal').style.display = 'block';
                                                        completeButton.style.display = 'none';
                                                    } else {
                                                        document.getElementById('notificationMessage').textContent = this.responseText;
                                                        document.getElementById('notificationModal').style.display = 'block';
                                                    }
                                                }
                                            };
                                            xhttp.open("POST", "getAppointments", true);
                                            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                            xhttp.send("id=" + appointment.id + "&status=2");

                                            // Update class name of status to reflect the new state
                                            insideFooter.textContent = 'Đã khám xong';
                                            insideFooter.className = 'status status-2';
                                            statusElement.textContent = 'Đã khám xong';
                                            statusElement.className = 'status status-2';
                                            appointment.status = '2';
                                        });
                                        confirmNo.addEventListener('click', function () {
                                            confirmModal.style.display = 'none';
                                        });
                                    });

                                    detailsElement.appendChild(bodyElement);
                                    detailsElement.appendChild(footerElement);

                                    document.getElementById('profile-picture').src = appointment.fp.profilePicture;

                                    var parts = appointment.plannedAt.split(' ')[0].split('-');
                                    var formatted = parts.reverse().join('/');
                                    document.getElementById('modalTitle').textContent = 'Chi tiết lịch hẹn - ' + formatted + ' ' + appointment.plannedAt.split(' ')[1];
                                    document.getElementById('appointmentModal').style.display = "block";
                                });

                                scheduleContainer.appendChild(appointmentElement);
                            });

                        }
                    }
                };
                xhttp.open("GET", "getAppointments?day=" + dateString, true);
                xhttp.send();
            }
        </script>
        <script>
            // Lấy ngày hiện tại
            var currentDay = new Date();

            // Tính toán ngày đầu tuần (Thứ Hai)
            var firstDayOfWeek = new Date(currentDay.getTime() - ((currentDay.getDay() + 6) % 7) * 86400000);

            // Tạo mảng weekDays
            var weekDays = [];
            for (var i = 0; i < 7; i++) {
                var day = new Date(firstDayOfWeek.getTime() + i * 86400000);
                weekDays.push(day);
            }

            function createClickHandler(dateString) {
                return function (e) {
                    e.preventDefault();

                    var activeElement = weekDaysContainer.querySelectorAll('a.active');
                    for (var i = 0; i < activeElement.length; ++i) {
                        activeElement[i].classList.remove('active');
                    }

                    e.target.classList.add('active');
                    loadAppointments(dateString);
                };
            }

            var weekDaysContainer = document.getElementById('weekDaysContainer');
            var monthYearContainer = document.getElementById('monthYearContainer');

            var scheduleLink = document.createElement('a');
            scheduleLink.href = "${pageContext.request.contextPath}/schedule";
            scheduleLink.textContent = "Lịch làm việc >>";
            var scheduleHeader = document.createElement('h3');
            scheduleHeader.appendChild(scheduleLink);
            monthYearContainer.appendChild(scheduleHeader);

            var monthYearHeader = document.createElement('h2');
            var currentMonth = new Intl.DateTimeFormat('vi-VN', {month: 'long', year: 'numeric'}).format(currentDay);
            monthYearHeader.textContent = currentMonth;
            monthYearContainer.appendChild(monthYearHeader);

            for (var i = 0; i < weekDays.length; i++) {
                var day = weekDays[i];
                day.setUTCHours(12);
                var dayElement = document.createElement('div');
                dayElement.className = 'col';
                var dateString = day.toISOString().split('T')[0];
                dayElement.innerHTML = '<a href="?day=' + dateString + '">' + day.getDate() + '</a>';
                dayElement.querySelector('a').addEventListener('click', createClickHandler(dateString));
                weekDaysContainer.appendChild(dayElement);
            }

            // Tự động tải lịch hẹn cho ngày hiện tại và thêm class 'active'
            var currentDayElement = weekDaysContainer.querySelector('a[href="?day=' + currentDay.toISOString().split('T')[0] + '"]');
            if (currentDayElement) {
                loadAppointments(currentDay.toISOString().split('T')[0]);
                currentDayElement.classList.add('active');
            }
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
