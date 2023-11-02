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

        <%@include file="doctor-footer.jsp" %>

        <%@include file="doctor-script.jsp" %>
        <script src="${pageContext.request.contextPath}/assets/doctor/js/load-appointment.js"></script>
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
                dayElement.innerHTML = '<a href="?day=' + dateString + '">' + (day.getDate < 10 ? '0' + day.getDate() : day.getDate()) + '</a>';
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
