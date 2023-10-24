<%-- 
    Document   : news
    Created on : Sep 16, 2023, 11:24:55 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <title>News Page</title>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>News</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <jsp:include page="user-head.jsp"/>
    </head>

    <body>
        <%@include file="user-header.jsp" %>
        <c:if test="${doctor.getARName()!=null}">
            <c:set var="ArCert" value="${doctor.getARName()}"></c:set>
            <c:if test="${doctor.getCertificates()!=null} && !${doctor.getCertificates().equals('')} && ${doctor.getCertificates().length()!=0}">
                <c:set var="ArCert" value="${doctor.getARName()}, ${doctor.getCertificates()}"></c:set>
            </c:if>
        </c:if>
        <c:if test="${doctor.getARName()==null}">
            <c:if test="${doctor.getCertificates()!=null}">
                <c:set var="ArCert" value="${doctor.getCertificates()}"></c:set>
            </c:if>
        </c:if>
        <!--profile header of a doctor start-->
        <div class="doctor-profile-header">
            <img src="${pageContext.request.contextPath}/assets/client/images/doctor-sample.png" alt="doctor-img" class="branch-image" />
            <p class="branch-name">${ArCert} ${doctor.displayName}</p>
        </div>
        <!--profile header of a doctor end-->
        <div class="doctor-profile-bottom">
            <ul class="tab-components nav-tabs tab-content list-inline">
                <li class="tab-component active">
                    <a href="#detail-info">Detail Info</a>
                </li>   
                <li class="tab-component">
                    <a href="#review">Review</a>
                </li>
                <li  class="tab-component">
                    <a href="#images">Images</a>
                </li>
                <li class="tab-component booking-tab"><a href="#">Booking</a></li>
            </ul>
        </div>
        <!--profile field of a doctor start-->

        <!--detail doctor start-->
        <div class="doctor-container">
            <!--Field 1: DETAIL-INFO start-->
            <div id="detail-info" class="doctor-detail-info-container">
                <div class="row">
                    <!-- Cột 1 -->
                    <div class="col-md-4">
                        <div class="introduce detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#introduce-content">
                                <h2>Introduction</h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="introduce-content" class="collapse show">
                                ${doctor.introduce}
                            </div>
                        </div>
                        <c:if test="${requestScope.numberOfReviews!=0}">

                            <div class="overview-rating detail-field">
                                <div class="detail-field-header" data-target="#overview-rating-content">
                                    <div class="detail-field-header-overview-rating">
                                        <h2>Overview</h2>
                                        <div class="star-rating col-md-2">
                                            <c:forEach begin="1" end="5">
                                                <!--<i class="fas fa-star" style="background-image: linear-gradient(90deg, yellow 50%, transparent 50%); background-clip: text;"></i>-->
                                                <!--<i class="fas fa-star" style=" "></i>-->

                                            </c:forEach>
                                            <%--<c:forEach begin="1" end="${5 - intRating}">--%>
                                                <!--<i class="fas fa-star fa-star-grey"></i>-->
                                            <%--</c:forEach>--%>
                                        </div>

                                        <span style="color: #289a5a">${requestScope.overallRating}/5</span>
                                    </div>
                                </div>
                                <div id="overview-rating-content" class="collapse show">
                                    <div class="overview-rating-content-each">
                                        <span class="rating-field">Chuyên môn</span> <span class = "rating-value">${requestScope.overallRating}</span>
                                    </div>
                                    <div class="overview-rating-content-each">
                                        <span class="rating-field">Thái độ giao tiếp</span> <span class = "rating-value">${requestScope.overallRating}</span>
                                    </div>
                                    <div class="overview-rating-content-each">
                                        <span class="rating-field">Tác phong diện mạo</span> <span class = "rating-value">${requestScope.overallRating}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="overview-review detail-field">
                                <div class="detail-field-header collapse-trigger" data-target="#overview-review-content">
                                    <h2>Comments: (${requestScope.numberOfReviews})</h2>
                                    <span class="toggle-icon">▼</span>
                                </div>
                                <div id="overview-review-content" class="collapse show">
                                    <c:forEach items="${requestScope.reviews}" var="review">
                                        <c:if test="${review.reviewContent != null}">
                                            <div class="each-review-detail">
                                                <div class="rating-and-time row">
                                                    <c:set var="intRating" value="${Double.parseDouble(review.rating)}"></c:set>
                                                        <div class="star-rating col-md-2">
                                                        <c:forEach begin="1" end="${intRating}">
                                                            <i class="fas fa-star"></i>
                                                        </c:forEach>
                                                        <c:forEach begin="1" end="${5-intRating}">
                                                            <i class="fas fa-star fa-star-grey"></i>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="time-rating col-md-10">
                                                        <p>rating at ${review.createdAt}</p>
                                                    </div>
                                                </div>
                                                <p>${review.reviewContent}</p>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>

                    </div>

                    <!-- Cột 2 -->
                    <div class="col-md-4">
                        <!--                        <div class="position-name detail-field">
                                                    <div class="detail-field-header collapse-trigger" data-target="#position-content">
                                                        <h2>Chức vụ</h2>
                                                        <span class="toggle-icon">▼</span>
                                                    </div>
                                                    <div id="position-content" class="collapse show">
                                                        <p>Thiếu chức vụ, có nên thêm vào DB ko??? (eg: Trưởng Đơn nguyên Phòng khám Ung bướu Huyết học kiêm Phụ trách Khoa Ung bướu Huyết học)</p>
                                                    </div>
                                                </div>-->
                        <div class="workplace detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#workplace-content">
                                <h2>Workplace</h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="workplace-content" class="collapse show">
                                <p>Chi nhanh ${doctor.branchName}</p>
                            </div>
                        </div>
                        <div class="experience detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#experience-content">
                                <h2>Kinh nghiệm</h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="experience-content" class="collapse show">
                                <c:forEach items="${requestScope.experiences}" var="e">
                                    <div class="experience-content-each">
                                        <p>${e.description}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="awards detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#awards-content">
                                <h2>Giải thưởng</h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="awards-content" class="collapse show">
                                <c:forEach items="${requestScope.awards}" var="a">
                                    <div class="awards-content-each">
                                        <p>${a.description}</p>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                        <div class="papers detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#papers-content">
                                <h2>Công trình nghiên cứu...</h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="papers-content" class="collapse show">
                                <c:forEach items="${requestScope.papers}" var="p">
                                    <div class="papers-content-each">
                                        ${p.description}
                                    </div>
                                </c:forEach>
                                <!--<p>Thông tin về các công trình nghiên cứu của bác sĩ...Thiếu nốt -> hoặc bỏ qua</p>-->
                            </div>
                        </div>
                    </div>

                    <!-- Cột 3-->
                    <div class="col-md-4">
                        <div class="service detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#service-content">
                                <h2>Service</h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="service-content" class="collapse show">
                                <c:forEach items="${requestScope.servicesOfdoctor}" var="s">
                                    <div class="service-content-each">
                                        <p>${s.nametag}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="education detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#education-content">
                                <h2>Education</h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="education-content" class="collapse show">
                                <c:forEach items="${requestScope.education}" var="e">
                                    <div class="education-content-each">
                                        <p>${e.description}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--Field 1: DETAIL-INFO end-->


            <!--Field 2: REVIEW start-->
            <div id="review" class="doctor-review-container">
                <div class="row">
                    <!-- Cột 1 -->
                    <div class="col-md-6">
                        <div class="rule detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#rule-content">
                                <h2>Customer reviews for doctor ${doctor.displayName}: <span style="color: #289a5a">${requestScope.numberOfRatings} rating</span></h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="rule-content" class="collapse show">
                                <p>Điểm trung bình mức độ hài lòng của khách hàng hiển thị bên dưới được lấy từ khảo sát trải nghiệm khách hàng độc lập của phòng Quản lý chất lượng.</p>


                                <p>Các câu trả lời được đo trên thang điểm từ 1 đến 5 với 5 là điểm tốt nhất tương đương với mức “Rất tốt”. </p>

                                <p> Các ý kiến nhận xét phản ánh quan điểm và ý kiến khách quan của khách hàng.</p>
                                <!--<p style="color: red">Nên thêm 1 table Rule để lưu những thứ như reviewRule</p>-->
                            </div>
                        </div>
                        <c:if test="${requestScope.numberOfReviews!=0}">

                            <div class="overview-rating1 detail-field">
                                
                                
                                
                                <div class="detail-field-header" data-target="#overview-rating1-content">
                                    <div class="detail-field-header-overview-rating">
                                        <h2>Overview</h2>
                                        <div class="star-rating col-md-2">
                                            <c:forEach begin="1" end="5">
                                                <!--<i class="fas fa-star" style="background-image: linear-gradient(90deg, yellow 50%, transparent 50%); background-clip: text;"></i>-->
                                                <!--<i class="fas fa-star" style=" "></i>-->

                                            </c:forEach>
                                            <%--<c:forEach begin="1" end="${5 - intRating}">--%>
                                                <!--<i class="fas fa-star fa-star-grey"></i>-->
                                            <%--</c:forEach>--%>
                                        </div>

                                        <span style="color: #289a5a">${requestScope.overallRating}/5</span>
                                    </div>
                                </div>
                                <div id="overview-rating-content" class="collapse show">
                                    <div class="overview-rating-content-each">
                                        <span class="rating-field">Chuyên môn</span> <span class = "rating-value">${requestScope.overallRating}</span>
                                    </div>
                                    <div class="overview-rating-content-each">
                                        <span class="rating-field">Thái độ giao tiếp</span> <span class = "rating-value">${requestScope.overallRating}</span>
                                    </div>
                                    <div class="overview-rating-content-each">
                                        <span class="rating-field">Tác phong diện mạo</span> <span class = "rating-value">${requestScope.overallRating}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="detail-review1 detail-field">
                                <div class="detail-field-header collapse-trigger" data-target="#detail-review1-content">
                                    <h2>Detail rating</h2>
                                    <span class="toggle-icon">▼</span>
                                </div>
                                <div id="detail-review1-content" class="collapse show">

                                    <c:forEach items="${requestScope.ratingstars}" var="index">
                                        <div class="each-star-rating">
                                            <div class="star-rating-index">${index.id}</div>
                                            <div class="star-rating-icon"><i class="fas fa-star"></i></div>
                                            <div class="rating-bar">
                                                <div class="rating-bar-filler" style="width: ${index.percent}%;"></div>
                                            </div>
                                            <p class="star-rating-text">${index.quantity} rating</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                    </div>

                    <!-- Cột 2 -->
                    <div class="col-md-6">
                        <div class="overview-review1 detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#overview-review1-content">
                                <h2>Comments: <span style="color: #289a5a">(${requestScope.numberOfReviews})</span></h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="overview-review1-content" class="collapse show">
                                <c:if test="${requestScope.numberOfReviews==0}">
                                    <p>Hiện không có nhận xét về bác sĩ này.</p>
                                </c:if>

                                <c:forEach items="${requestScope.reviews}" var="review">
                                    <c:if test="${review.reviewContent != null}">
                                        <div class="each-review-detail">
                                            <div class="rating-and-time row">
                                                <c:set var="intRating" value="${Double.parseDouble(review.rating)}"></c:set>
                                                    <div class="star-rating col-md-2">
                                                    <c:forEach begin="1" end="${intRating}">
                                                        <i class="fas fa-star"></i>
                                                    </c:forEach>
                                                    <c:forEach begin="1" end="${5-intRating}">
                                                        <i class="fas fa-star fa-star-grey"></i>
                                                    </c:forEach>
                                                </div>
                                                <div class="time-rating col-md-10">
                                                    <p>rating at ${review.createdAt}</p>
                                                </div>
                                            </div>
                                            <p>${review.reviewContent}</p>
                                        </div>
                                    </c:if>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--Field 2: REVIEW end-->

            <!--Field 3: IMAGES start-->
            <div id="images">
                <div class="row">
                    <!-- Cột 1 -->
                    <div class="col-md-12">
                        <div class="images detail-field">
                            <div class="detail-field-header collapse-trigger" data-target="#images-doctor-content">
                                <h2>Images of ${doctor.displayName}</h2>
                                <span class="toggle-icon">▼</span>
                            </div>
                            <div id="images-doctor-content" class="collapse show">
                                <p><img src="${pageContext.request.contextPath}/assets/client/images/doctor-sample.png" width="width" height="height" alt="alt"/></p>
                            </div>
                        </div>

                    </div>


                </div>
            </div>
            <!--Field 3: IMAGES end-->
        </div>
        <!--detail doctor end-->


        <button id="back-to-top" title="Back to top">↑</button>
        <%@include file="user-footer.jsp" %>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Mặc định ban đầu, chọn tab "Detail Info"
                var defaultTab = document.querySelector(".tab-component:first-child");
                defaultTab.classList.add("active");

                var tabLinks = document.querySelectorAll(".tab-component a");

                tabLinks.forEach(function (link) {
                    link.addEventListener("click", function (e) {
                        e.preventDefault();

                        // Xóa lớp 'active' từ tất cả các li
                        tabLinks.forEach(function (tabLink) {
                            tabLink.parentElement.classList.remove("active");
                        });

                        // Thêm lớp 'active' cho li của tab hiện tại
                        link.parentElement.classList.add("active");

                        // Lấy id của tab để hiển thị div tương ứng
                        var tabId = link.getAttribute("href").substring(1);
                        var tabContents = document.querySelectorAll(".doctor-container > div");

                        // Ẩn tất cả các div trong .doctor-container
                        tabContents.forEach(function (tabContent) {
                            tabContent.style.display = "none";
                        });

                        // Hiển thị div tương ứng với tab đã chọn
                        document.getElementById(tabId).style.display = "block";
                    });
                });
            });

            document.addEventListener("DOMContentLoaded", function () {
                var headers = document.querySelectorAll('.collapse-trigger');

                headers.forEach(function (header) {
                    header.addEventListener('click', function () {
                        var targetId = this.getAttribute('data-target');
                        var targetContent = document.querySelector(targetId);

                        if (targetContent.classList.contains('show')) {
                            targetContent.classList.remove('show');
                        } else {
                            targetContent.classList.add('show');
                        }

                        var toggleIcon = this.querySelector('.toggle-icon');
                        toggleIcon.style.transform = targetContent.classList.contains('show') ? 'rotate(0deg)' : 'rotate(-90deg)';
                    });
                });
            });





        </script>

        <%@include file="user-script.jsp" %>
    </body>
</html>
