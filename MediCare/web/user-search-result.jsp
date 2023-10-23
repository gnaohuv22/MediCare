<%-- 
    Document   : user-search-result
    Created on : Sep 28, 2023, 12:49:49 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <!-- bootstrap css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/bootstrap.min.css" />
    <!-- style css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/style.css" />
    <!-- Responsive-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/responsive.css" />
    <!-- fevicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/assets/client/images/favicon.png" type="image/gif" />
    <!-- Scrollbar Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/jquery.mCustomScrollbar.min.css" />
    <!-- Tweaks for older IEs-->
    <link
        rel="stylesheet"
        href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
        />
    <!-- owl stylesheets -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/owl.carousel.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/client/css/owl.theme.default.min.css" />
    <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
        media="screen"
        />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kết quả tìm kiếm cho "${pattern}"</title>
</head>

<%@include file="user-header.jsp" %>

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
                    <img src="${pageContext.request.contextPath}/assets/client/images/doctor-img.png" width="100" height="100" alt="${doctor.getDisplayName()}">
                    <div class="doctor-rating">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star-half-alt"></i><br>
                        demo ver
                    </div>

                </div>
                <div class="search-doctor-information">
                    <span class="search-doctor-name">${doctor.getDisplayName()}</span><br>
                    <span class="search-doctor-branch-name">${doctor.getBranchName()}</span><br>
                    <span class="search-doctor-intro">${doctor.getCertificates()}</span><br>
                    <span class="search-doctor-intro">${doctor.getARName()}</span><br>
                    <span class="search-doctor-introduce" data-doctor-id="${doctor.getId()}">${doctor.getIntroduce()}</span>
                    <button class="search-find-more">
                        <a href="${pageContext.request.contextPath}/user-doctor-detail?doctorId=${doctor.getId()}">Tìm hiểu thêm</a>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/#">${i}</a>
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
<script src="${pageContext.request.contextPath}/assets/client/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/client/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/client/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/client/js/jquery-3.0.0.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/client/js/plugin.js"></script>
<!-- sidebar -->
<script src="${pageContext.request.contextPath}/assets/client/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/client/js/custom.js"></script>
<!-- javascript -->
<script src="${pageContext.request.contextPath}/assets/client/js/owl.carousel.js"></script>
<script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
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

