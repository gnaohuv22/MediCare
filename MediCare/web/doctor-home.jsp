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
                                titleElement.textContent = 'Time: ' + appointment.plannedAt.split(' ')[1];

                                var subtitleElement = document.createElement('h6');
                                subtitleElement.className = 'card-subtitle mb-2 text-muted';
                                subtitleElement.textContent = 'Name: ' + appointment.fp.name;
                                headerElement.appendChild(titleElement);
                                headerElement.appendChild(subtitleElement);

                                var bodyElement = document.createElement('div');
                                bodyElement.className = 'card-body';
                                var serviceTextElement = document.createElement('p');
                                serviceTextElement.className = 'card-text';
                                serviceTextElement.textContent = appointment.st.nametag;
                                bodyElement.appendChild(serviceTextElement);

                                var footerElement = document.createElement('div');
                                footerElement.className = 'card-footer text-muted';
                                switch (appointment.status) {
                                    case '0':
                                        footerElement.textContent = 'Chờ xác nhận';
                                        break;

                                    case '1':
                                        footerElement.textContent = 'Đang chờ khám';
                                        break;

                                    case '2':
                                        footerElement.textContent = 'Đã khám xong';
                                        break;

                                    case '3':
                                        footerElement.textContent = 'Đã huỷ';
                                        break;

                                    default:
                                        footerElement.textContent = 'Trạng thái không xác định';
                                        break;
                                }

                                appointmentElement.appendChild(headerElement);
                                appointmentElement.appendChild(bodyElement);
                                appointmentElement.appendChild(footerElement);

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
                console.log('loaded');
            }
        </script>
        <script>
            dayElement.querySelector('a').addEventListener('click', function (e) {
                e.preventDefault();
                loadAppointments(day.toLocaleDateString());
            });
        </script>
    </body>
</html>
