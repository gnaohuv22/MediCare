<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="doctor-head.jsp"/>
        <title>Lịch sử cuộc hẹn của ${d.getDisplayName()} | MediCare</title>
    </head>
    <body>
        <jsp:include page="doctor-header.jsp"/>

        <!-- Appointment history section -->
        <div class="container">
            <div class="appointment-container">
                <div class="row">
                    <div class="col-md-3">
                        <h1 class="filter-text">
                            <i class="fa-solid fa-filter"></i> Bộ lọc 
                        </h1>
                        <button class="filter-button2" id="resetFilter">Xoá bộ lọc</button><br>
                        <div class="status-filter">
                            <h3 class="filter-element">Trạng thái</h3>
                            <c:forEach var="status" items="${requestScope.statusSet}">
                                <input type="checkbox" id="status${status}" name="status" value="${status}">
                                <c:choose>
                                    <c:when test="${status == 1}">
                                        <label class="filter-content" for="status${status}">Đang chờ khám</label><br>
                                    </c:when>
                                    <c:when test="${status == 2}">
                                        <label class="filter-content" for="status${status}">Đã khám xong</label><br>
                                    </c:when>
                                    <c:when test="${status == 3}">
                                        <label class="filter-content" for="status${status}">Đã huỷ</label><br>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </div>
                        <div class="service-filter">
                            <h3 class="filter-element">Tên dịch vụ</h3>
                            <c:forEach var="service" items="${requestScope.serviceSet}">
                                <input type="checkbox" id="service${service}" name="service" value="${service}">
                                <label class="filter-content" for="service${service}">${service}</label><br>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div id="appointments">
                            <div class="appointment-title">
                                <h1 class="text-center">Lịch sử cuộc hẹn của ${d.getDisplayName()}</h1>
                            </div>
                            <div class="date-filter">
                                <label class="filter-date" for="start-date">Từ</label>
                                <input class="date-type" type="date" id="startDate" name="startDate" value="">

                                <label class="filter-date" for="end-date">Đến</label>
                                <input class="date-type" type="date" id="endDate" name="endDate" value="">
                                <button class="filter-button" id="submitDate">Lọc</button><br>

                                <p id="errorMessage" style="color: red; font-size: 16px; font-weight: bolder"></p>
                            </div>
                            <c:forEach var="appointment" items="${requestScope.appointments}">
                                <div class="card appointment-section" data-status="${appointment.getStatus()}" data-service="${appointment.getSt().getNametag()}" data-date="${appointment.getPlannedAt()}">
                                    <div class="card-header">
                                        <div class="row">
                                            <div class="col">
                                                <img class="appointment-img" src="${appointment.getFp().getProfilePicture()}" width="50px" height="50px" alt="Profile picture">
                                                <strong>${appointment.getFp().getName()}</strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="appointment-body">
                                            <div class="appointment-infor1">
                                                <p class="appointment-date">${appointment.getSt().getNametag()}</p>
                                            </div>
                                            <div class="symptoms">
                                                <p class="symptoms">Ghi chú của bệnh nhân: ${appointment.getSymptoms()}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <div id="status">

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>


                <!-- Dynamic Pagination section -->
                <nav aria-label="Pagination">
                    <ul id="pagination" class="pagination justify-content-center">

                    </ul>
                </nav>
                <!-- Dynamic Pagination section -->
            </div>
        </div>
        <!-- Appointment history section -->

        <jsp:include page="doctor-footer.jsp"/>
        <jsp:include page="doctor-script.jsp"/>
        <script>
            $(document).ready(function () {
                $('.card.appointment-section').each(function () {
                    var status = $(this).data('status'); // get the status value
                    var statusText = ''; // initialize status text
                    var statusClass = '';
                    // map status value to status text
                    switch (status) {
                        case 1:
                            statusText = 'Đang chờ khám';
                            statusClass = 'status status-1';
                            break;
                        case 2:
                            statusText = 'Đã khám xong';
                            statusClass = 'status status-2';
                            break;
                        case 3:
                            statusText = 'Đã huỷ';
                            statusClass = 'status status-3';
                            break;
                    }
                    // set the status text
                    $(this).find('#status').text(statusText).addClass(statusClass);
                });
            });
        </script>
        <!-- This script let the status and service filter working perfectly -->
        <script>
            $(document).ready(function () {
                // Number of appointments per page
                var pageSize = 5;

                // Current page
                var currentPage = 1;

                // Total number of pages
                var totalPages = 0;

                // Function to update the appointments
                function updateAppointments() {
                    var startDate = new Date($('#startDate').val());
                    var endDate = new Date($('#endDate').val());

                    var checkedStatusBoxes = $('input[name="status"]:checked');
                    var checkedServiceBoxes = $('input[name="service"]:checked');

                    // Filter the appointments based on the checkboxes
                    var filteredAppointments = $('.appointment-section').filter(function () {
                        var appointmentDate = new Date($(this).attr('data-date'));
                        var appointmentStatus = $(this).attr('data-status');
                        var appointmentService = $(this).attr('data-service');
                        
                        var matchDate = (appointmentDate >= startDate && appointmentDate <= endDate);
                        var matchStatus = checkedStatusBoxes.length === 0 || Array.from(checkedStatusBoxes).some(function (checkbox) {
                            return checkbox.value === appointmentStatus;
                        });
                        var matchService = checkedServiceBoxes.length === 0 || Array.from(checkedServiceBoxes).some(function (checkbox) {
                            return checkbox.value === appointmentService;
                        });
                        return matchStatus && matchService && matchDate;
                    });

                    // Update the total number of pages
                    totalPages = Math.ceil(filteredAppointments.length / pageSize);

                    // Show only the appointments for the current page
                    $('.appointment-section').hide();
                    filteredAppointments.slice((currentPage - 1) * pageSize, currentPage * pageSize).show();

                    // Update the pagination
                    updatePagination();
                }

                // Function to update the pagination
                function updatePagination() {
                    var pagination = $('#pagination');

                    // Clear the current pagination
                    pagination.empty();

                    // Add "Previous" button
                    if (currentPage !== 1) {
                        var prevlink = $('<li>').addClass('page-item').append($('<a>').addClass('page-link').attr('href', '#').text('<<').click(function (e) {
                            e.preventDefault();

                            //Update the current page
                            --currentPage;

                            //Update the appointments list
                            updateAppointments();
                        })
                                );
                        pagination.append(prevlink);
                    }

                    // Add a page number for each page
                    for (var i = 1; i <= totalPages; i++) {
                        var pageItem = $('<li>').addClass('page-item');
                        if (i === currentPage) {
                            pageItem.addClass('active');
                        }
                        var pageLink = $('<a>').addClass('page-link').attr('href', '#').text(i).click(function (e) {
                            e.preventDefault();

                            //Update the current page
                            currentPage = parseInt($(this).text());

                            //Update the appointments
                            updateAppointments();
                        });
                        pageItem.append(pageLink);
                        pagination.append(pageItem);
                    }
                    //Add a "next" button
                    if (currentPage < totalPages) {
                        var nextLink = $('<li>').addClass('page-item').append($('<a>').addClass('page-link').attr('href', '#').text('>>').click(function (e) {
                            e.preventDefault();

                            //Update the current page
                            ++currentPage;

                            //Update the appointments
                            updateAppointments();
                        })
                                );
                        pagination.append(nextLink);
                    }
                }


                // Update the appointments when a checkbox is changed
                $('input[name="status"], input[name="service"]').change(function () {
                    currentPage = 1;
                    updateAppointments();
                });

                // Update the appointments initially
                updateAppointments();

                $('#resetFilter').click(function () {
                    // Uncheck all checkboxes
                    $('input[name="status"], input[name="service"]').prop('checked', false);

                    // Trigger the change event to update the appointments
                    $('input[name="status"], input[name="service"]').change();
                });

                $('#submitDate').click(function () {
                    // Reset the current page to 1
                    currentPage = 1;

                    // Update the appointments
                    updateAppointments();
                });
            });
        </script>
        <!-- This script disabled the Reset Filter button if no filters applied -->
        <script>
            $(document).ready(function () {
                // Add event listener for change event
                $('input[name="status"], input[name="service"]').change(function () {
                    var checkedStatus = $('input[name="status"]:checked');
                    var checkedService = $('input[name="service"]:checked');

                    if (checkedStatus.length === 0 && checkedService.length === 0) {
                        $('#resetFilter').prop('disabled', true);
                    } else {
                        $('#resetFilter').prop('disabled', false);
                    }
                }).change(); // Trigger the change event initially
            });
        </script>
        <!-- This script set the default value and limit the user behavior -->
        <script>
            $(document).ready(function ()
            {
                var today = new Date();
                var startDate = new Date();

                startDate.setMonth(startDate.getMonth() - 1);

                $('#startDate').val(startDate.toISOString().split('T')[0]);
                $('#endDate').val(today.toISOString().split('T')[0]);

                var minDate = new Date();
                minDate.setFullYear(minDate.getFullYear() - 1);
                var maxDate = new Date();

                $('#startDate, #endDate').attr('min', minDate.toISOString().split('T')[0]);
                $('#startDate, #endDate').attr('max', maxDate.toISOString().split('T')[0]);

                $('#startDate, #endDate').change(function () {
                    var startDate = new Date($('#startDate').val());
                    startDate.setUTCHours(12);
                    var endDate = new Date($('#endDate').val());
                    endDate.setUTCHours(-12);

                    if (startDate >= endDate || startDate < minDate || endDate > maxDate) {
                        if (startDate >= endDate) {
                            document.getElementById('errorMessage').innerHTML = '';
                            document.getElementById('errorMessage').innerHTML = 'Ngày bắt đầu phải nhỏ hơn ngày kết thúc.';
                        } else if (startDate < minDate) {
                            document.getElementById('errorMessage').innerHTML = '';
                            document.getElementById('errorMessage').innerHTML = 'Chỉ có thể lọc đánh giá trong phạm vi 1 năm trở lại.';
                        } else if (endDate > maxDate) {
                            document.getElementById('errorMessage').innerHTML = '';
                            document.getElementById('errorMessage').innerHTML = 'Ngày kết thúc không thể vượt quá ngày hiện tại.';
                        }
                        $('#submitDate').prop('disabled', true);
                    } else {
                        document.getElementById('errorMessage').innerHTML = '';
                        $('#submitDate').prop('disabled', false);
                    }
                });
                $('#submitDate').trigger('click');
            });
        </script>

        <!-- This script reformat the date of plannedAt -->
        <script>
            function formatDate(dateString)
            {
                var date = new Date(dateString);
                var day = ("0" + date.getDate()).slice(-2);
                var month = ("0" + (date.getMonth() + 1)).slice(-2);
                var year = date.getFullYear();
                var hours = ("0" + date.getHours()).slice(-2);
                var minutes = ("0" + date.getMinutes()).slice(-2);
                return day + "/" + month + "/" + year + " " + hours + ":" + minutes;
            }


            $(document).ready(function () {
                $('.appointment-section').each(function () {
                    var appointmentDate = formatDate($(this).attr('data-date'));
                    $(this).find('.appointment-date').text(appointmentDate + ' | ' + $(this).find('.appointment-date').text());
                });
            });
        </script>
    </body>
</html>
