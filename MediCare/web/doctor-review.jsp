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
                        <h1 class="filter-text">Bộ lọc</h1>
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
                                <input class="date-type" type="date" id="startDate" name="startDate">
                                
                                <label class="filter-date" for="end-date">Đến</label>
                                <input class="date-type" type="date" id="endDate" name="endDate">
                                <button class="filter-button" id="submitDate">Lọc</button>
                            </div>
                            <c:forEach var="review" items="${requestScope.reviewList}">
                                <c:set var="rating" value="${Double.parseDouble(review.getRating())}"></c:set>
                                <c:set var="intRating" value="${Math.floor(rating)}"></c:set>
                                <div class="card review-section" data-rating="${intRating}" data-service="${review.getAppointment().getSt().getNametag()}" date-date="${review.getCreatedAt()}">
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
                                                <p>${review.getAppointment().getPlannedAt()} | ${review.getAppointment().getSt().getNametag()}</p>
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
            </div>
        </div>

        <jsp:include page="doctor-footer.jsp"/>
        <jsp:include page="doctor-script.jsp"/>
        <script>
            $('input[name="rating"], input[name="service"]').change(function () {
                var checkedRatingBoxes = $('input[name="rating"]:checked');
                var checkedServiceBoxes = $('input[name="service"]:checked');

                if (checkedRatingBoxes.length === 0 && checkedServiceBoxes.length === 0) {
                    // If no checkboxes are checked, show all reviews
                    $('.review-section').show();
                } else {
                    // If some checkboxes are checked, show only the reviews with matching ratings and services
                    $('.review-section').hide();
                    $('.review-section').each(function () {
                        var reviewRating = parseFloat($(this).attr('data-rating'));
                        var reviewService = $(this).attr('data-service');
                        var matchRating = checkedRatingBoxes.length === 0 || Array.from(checkedRatingBoxes).some(function (checkbox) {
                            var rating = parseFloat(checkbox.value);
                            return rating <= reviewRating && reviewRating < rating + 1;
                        });
                        var matchService = checkedServiceBoxes.length === 0 || Array.from(checkedServiceBoxes).some(function (checkbox) {
                            return checkbox.value === reviewService;
                        });
                        if (matchRating && matchService) {
                            $(this).show();
                        }
                    });
                }
            });

        </script>
        <script>
            $('#submitDate').click(function() {
               var startDate = new Date($('#startDate').val());
               var endDate = new Date($('#endDate').val());
               
               $('.review-section').each(function() {
                  var reviewDate = new Date($(this).attr('data-date'));
                  if (reviewDate >= startDate && reviewDate <= endDate) {
                      $(this).show();
                  } else {
                      $(this).hide();
                  }
               });
            });
        </script>
    </body>
</html>
