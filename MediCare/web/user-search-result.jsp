<%-- 
    Document   : user-search-result
    Created on : Sep 28, 2023, 12:49:49 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <!-- basic -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- site metas -->
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <jsp:include page="user-head.jsp"/>
    <title>Kết quả tìm kiếm cho "${pattern}" | MediCare</title>
</head>
<body>
    <c:choose>
        <c:when test="${doctorLoggedIn}">
            <%@include file="doctor-header.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="user-header.jsp" %>
        </c:otherwise>
    </c:choose>

    <div class="branch-profile-header">
        <img src="${pageContext.request.contextPath}/assets/client/images/branch-img.jpg" alt="branch-img" class="branch-image" />
        <p class="branch-name">Kết quả tìm kiếm cho "${pattern}"</p>
    </div>
    <!-- search result section start -->

    <div class="search-container container">
        <div class="vertical-doctor-list">
            <c:forEach var="doctor" items="${doctors}">
                <div class="search-doctor-block">
                    <div class="doctor-img">
                        <img src="${doctor.key.getProfilePicture()}" style="width: 100px; height: 100px; object-fit: cover; border-radius: 50%" alt="${doctor.key.getDisplayName()}">
                        <div class="doctor-rating">
                            <c:set var="rating" value="${doctor.value}"></c:set>
                            <c:forEach begin="1" end="${rating}">
                                <i class="fas fa-star"></i>
                            </c:forEach>
                            <c:set var="intRating" value="${Math.floor(rating)}"></c:set>
                            <c:choose>
                                <c:when test="${rating gt intRating}">
                                    <i class="fas fa-star-half-alt"></i>
                                    <c:forEach begin="1" end="${5 - intRating - 1}">
                                        <i class="fas fa-star fa-star-grey"></i>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach begin="1" end="${5 - intRating}">
                                        <i class="fas fa-star fa-star-grey"></i>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>

                    </div>
                    <div class="search-doctor-information">
                        <span class="search-doctor-name">${doctor.key.getDisplayName()}</span><br>
                        <span class="search-doctor-branch-name">${doctor.key.getBranchName()}</span><br>
                        <span class="search-doctor-intro">${doctor.key.getCertificates()}</span><br>
                        <span class="search-doctor-intro">${doctor.key.getARName()}</span><br>
                        <span class="search-doctor-introduce" data-doctor-id="${doctor.key.getId()}">${doctor.key.getIntroduce()}</span>
                        <button class="search-find-more">
                            <a href="${pageContext.request.contextPath}/user-doctor-detail?doctorId=${doctor.key.getId()}">Tìm hiểu thêm</a>
                        </button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- pagination section -->
    <nav aria-label="Pagination">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/search-doctor?pattern=${pattern}&page=${currentPage - 1}"><<</a>
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
                            <a class="page-link" href="${pageContext.request.contextPath}/search-doctor?pattern=${pattern}&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt pageCount}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/search-doctor?pattern=${pattern}&page=${currentPage + 1}">>></a>
                </li>
            </c:if>
        </ul>
    </nav>
    <!-- pagination section -->

    <!-- search result section end -->
    <%@include file="user-footer.jsp" %>
    <button id="back-to-top" title="Back to top">↑</button>
    <%@include file="user-script.jsp" %>
    <script>
        $(document).ready(function () {
            $('.search-doctor-introduce').each(function () {
                var maxLength = 365; // Đặt độ dài tối đa cho nội dung
                if ($(this).text().length > maxLength) {
                    var shortText = $(this).text().substr(0, maxLength) + '...'; // Cắt nội dung
                    var longText = $(this).text().substr(maxLength);

                    var doctorId = $(this).data('doctor-id');

                    // Tạo nút "Xem thêm"
                    var btn = $('<a href="user-doctor-detail?doctorId=' + doctorId + '" style="color: #68B2A0; text-decoration: underline;"> Xem thêm>></a>');

                    // Thay thế nội dung bằng nội dung đã được cắt và nút "Xem thêm"
                    $(this).html(shortText);
                    $(this).append(btn);
                }
            });
        });
    </script>
</body>


