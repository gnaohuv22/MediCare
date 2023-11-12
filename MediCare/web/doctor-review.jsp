<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="doctor-head.jsp"/>
        <title>Đánh giá của người dùng | MediCare</title>
    </head>
    <body>
        <jsp:include page="doctor-header.jsp"/>

        <!-- Review section -->
        <div class="container">
            <div class="review-container">
                <div class="row">
                    <div class="col-md-3">
                        <h1 class="filter-text">
                            <i class="fa-solid fa-filter"></i> Bộ lọc 
                        </h1>
                        <button class="filter-button2" id="resetFilter">Xoá bộ lọc</button><br>
                        <div class="star-filter">

                            <h3 class="filter-element">Đánh giá</h3>
                            <!-- dynamic rating filter -->
                            <c:forEach var="rating" items="${requestScope.uniqueRatings}">
                                <c:if test="${rating lt 5.0}">
                                    <input type="checkbox" id="rating${rating}" name="rating" value="${rating}">
                                    <label class="filter-content" for="rating${rating}">${rating} <i class="fa-solid fa-star"></i> - ${rating + 0.5} <i class="fa-solid fa-star"></i></label><br>
                                    </c:if>
                                </c:forEach>
                            <input type="checkbox" id="rating5" name="rating" value="5">
                            <label class="filter-content" for="rating5">5.0 <i class="fa-solid fa-star"></i></label><br>
                        </div>
                        <div class="service-filter">
                            <h3 class="filter-element">Tên dịch vụ</h3>
                            <c:forEach var="service" items="${requestScope.uniqueServicesSet}">
                                <input type="checkbox" id="service${service}" name="service" value="${service}">
                                <label class="filter-content" for="service${service}">${service}</label><br>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div id="reviews">
                            <div class="review-title">
                                <h1 class="text-center">Đánh giá của người dùng</h1>
                            </div>
                            <div class="date-filter">
                                <label class="filter-date" for="start-date">Từ</label>
                                <input class="date-type" type="date" id="startDate" name="startDate" value="">

                                <label class="filter-date" for="end-date">Đến</label>
                                <input class="date-type" type="date" id="endDate" name="endDate" value="">
                                <button class="filter-button" id="submitDate">Lọc</button><br>

                                <p id="errorMessage" style="color: red; font-size: 16px; font-weight: bolder"></p>
                            </div>
                            <c:forEach var="review" items="${requestScope.reviewList}">
                                <c:set var="rating" value="${Double.parseDouble(review.getRating())}"></c:set>
                                <c:set var="intRating" value="${Math.floor(rating)}"></c:set>
                                <div class="card review-section" data-rating="${intRating}" data-service="${review.getAppointment().getSt().getNametag()}" data-date="${review.getCreatedAt()}">
                                    <!--<p>${intRating}</p>-->
                                    <div class="card-header">
                                        <div class="row">
                                            <div class="col">
                                                <img class="review-img" src="${review.getAppointment().getFp().getProfilePicture()}" width="50px" height="50px" alt="Profile picture">
                                                <strong>${review.getAppointment().getFp().getName()}</strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="review">
                                            <div class="stars row star-rating">
                                                <!-- Display stars based on review.getRating() -->
                                                <c:set var="rating" value="${Double.parseDouble(review.getRating())}"></c:set>
                                                <c:forEach begin="1" end="${rating}">
                                                    <i class="fa-solid fa-star"></i>
                                                </c:forEach>
                                                <c:set var="intRating" value="${Math.floor(rating)}"></c:set>
                                                <c:choose>
                                                    <c:when test="${rating gt intRating}">
                                                        <i class="fa-regular fa-star-half-stroke"></i>
                                                        <c:forEach begin="1" end="${5 - intRating - 1}">
                                                            <i class="fa-regular fa-star"></i>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:forEach begin="1" end="${5 - intRating}">
                                                            <i class="fa-regular fa-star"></i>
                                                        </c:forEach>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="review-infor1">
                                                <p class="review-date">${review.getAppointment().getSt().getNametag()}</p>
                                            </div>
                                            <div>
                                                <p>Đánh giá dịch vụ: ${review.getReviewContent()}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <!-- Review section -->

                <!-- Dynamic Pagination section -->
                <nav aria-label="Pagination">
                    <ul id="pagination" class="pagination justify-content-center">

                    </ul>
                </nav>
                <!-- Dynamic Pagination section -->
            </div>
        </div>

        <jsp:include page="doctor-footer.jsp"/>
        <jsp:include page="doctor-script.jsp"/>
        <!-- This script let the rating and service filter working perfectly -->
        <script>
            $(document).ready(function () {
                // Number of reviews per page
                var pageSize = 5;

                // Current page
                var currentPage = 1;

                // Total number of pages
                var totalPages = 0;

                // Function to update the reviews
                function updateReviews() {
                    var startDate = new Date($('#startDate').val());
                    var endDate = new Date($('#endDate').val());

                    var checkedRatingBoxes = $('input[name="rating"]:checked');
                    var checkedServiceBoxes = $('input[name="service"]:checked');

                    // Filter the reviews based on the checkboxes
                    var filteredReviews = $('.review-section').filter(function () {
                        var reviewRating = parseFloat($(this).attr('data-rating'));
                        var reviewService = $(this).attr('data-service');
                        var reviewDate = new Date($(this).attr('data-date'));

                        var matchDate = (reviewDate >= startDate && reviewDate <= endDate);
                        var matchRating = checkedRatingBoxes.length === 0 || Array.from(checkedRatingBoxes).some(function (checkbox) {
                            var rating = parseFloat(checkbox.value);
                            return rating <= reviewRating && reviewRating < rating + 1;
                        });
                        var matchService = checkedServiceBoxes.length === 0 || Array.from(checkedServiceBoxes).some(function (checkbox) {
                            return checkbox.value === reviewService;
                        });
                        return matchRating && matchService && matchDate;
                    });

                    // Update the total number of pages
                    totalPages = Math.ceil(filteredReviews.length / pageSize);

                    // Show only the reviews for the current page
                    $('.review-section').hide();
                    filteredReviews.slice((currentPage - 1) * pageSize, currentPage * pageSize).show();

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

                            //Update the reviews list
                            updateReviews();
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

                            //Update the reviews
                            updateReviews();
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

                            //Update the reviews
                            updateReviews();
                        })
                                );
                        pagination.append(nextLink);
                    }
                }


                // Update the reviews when a checkbox is changed
                $('input[name="rating"], input[name="service"]').change(function () {
                    currentPage = 1;
                    updateReviews();
                });

                // Update the reviews initially
                updateReviews();

                $('#resetFilter').click(function () {
                    // Uncheck all checkboxes
                    $('input[name="rating"], input[name="service"]').prop('checked', false);

                    // Trigger the change event to update the reviews
                    $('input[name="rating"], input[name="service"]').change();
                });

                $('#submitDate').click(function () {
                    var startDate = new Date($('#startDate').val());
                    var endDate = new Date($('#endDate').val());

                    // Filter the reviews based on the date
                    var filteredReviews = $('.review-section').filter(function () {
                        var reviewDate = new Date($(this).attr('data-date'));
                        return reviewDate >= startDate && reviewDate <= endDate;
                    });

                    // Hide all reviews
                    $('.review-section').hide();

                    // Show the filtered reviews
                    filteredReviews.show();

                    // Reset the current page to 1
                    currentPage = 1;

                    // Update the reviews
                    updateReviews();
                });
            });
        </script>

        <!-- This script let date filter work -->
        <script>

        </script>
        <!-- This script disabled the Reset Filter button if no filters applied -->
        <script>
            $(document).ready(function () {
                // Add event listener for change event
                $('input[name="rating"], input[name="service"]').change(function () {
                    var checkedRating = $('input[name="rating"]:checked');
                    var checkedService = $('input[name="service"]:checked');
                    console.log('checkedRating: ', checkedRating.length === 0);
                    console.log('checkedService ', checkedService.length === 0);
                    if (checkedRating.length === 0 && checkedService.length === 0) {
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
                    console.log('sd: ', startDate);
                    console.log('md: ', minDate);
                    console.log('startDate < minDate: ', startDate < minDate);
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

        <!-- This script reformat the date of createdAt -->
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
                $('.review-section').each(function () {
                    var reviewDate = formatDate($(this).attr('data-date'));
                    $(this).find('.review-date').text(reviewDate + ' | ' + $(this).find('.review-date').text());
                });
            });
        </script>
    </body>
</html>
