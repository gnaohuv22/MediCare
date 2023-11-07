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
                <div id="reviews">
                    <div class="review-title">
                        <h1 class="text-center">Đánh giá của người dùng</h1>
                    </div>
                    <!-- Each review will be a card with the user's name, rating, and comment -->
                    <!-- This will be populated dynamically using JSTL -->
                    <c:forEach var="review" items="${requestScope.reviewList}">
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col">
                                        <strong>${review.getAppointment().getPlannedAt()}</strong>
                                    </div>
                                    <div class="col text-right">
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
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-2">
                                        <img src="${review.getAppointment().getFp().getProfilePicture()}" alt="Profile picture">
                                    </div>
                                    <div class="col">
                                        <div>${review.getAppointment().getFp().getName()}</div>
                                        <div>${review.getAppointment().getSt().getNametag()}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <button class="btn btn-primary" data-review-id="${review.getId()}">View Details</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- Review section -->

        <jsp:include page="doctor-footer.jsp"/>
        <jsp:include page="doctor-script.jsp"/>
    </body>
</html>
