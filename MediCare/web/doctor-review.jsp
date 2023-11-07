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
                        filter: 
                        <p>rating</p>
                        <p>date</p>
                        <p>service name</p>
                    </div>
                    <div class="col-md-9">
                        <div id="reviews">
                            <div class="review-title">
                                <h1 class="text-center">Đánh giá của người dùng</h1>
                            </div>
                            <!-- Each review will be a card with the user's name, rating, and comment -->
                            <!-- This will be populated dynamically using JSTL -->
                            <c:forEach var="review" items="${requestScope.reviewList}">
                                <div class="card review-section">
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


        <!-- pagination section -->
        <nav aria-label="Pagination">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/view-review?page=${currentPage - 1}"><<</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${pages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active">
                                <a class="page-link" href="#" >${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/view-review?page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt pages}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/view-review?page=${currentPage + 1}">>></a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <!-- pagination section -->

        <jsp:include page="doctor-footer.jsp"/>
        <jsp:include page="doctor-script.jsp"/>
    </body>
</html>
