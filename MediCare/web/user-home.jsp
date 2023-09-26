<%-- 
    Document   : home
    Created on : Sep 16, 2023, 11:10:25 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
        <!-- site metas -->
        <title>Medical Service</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="assets/user/css/bootstrap.min.css" />
        <!-- style css -->
        <link rel="stylesheet" href="assets/user/css/style.css" />
        <!-- Responsive-->
        <link rel="stylesheet" href="assets/user/css/responsive.css" />
        <!-- fevicon -->
        <link rel="icon" href="assets/user/images/favicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="assets/user/css/jquery.mCustomScrollbar.min.css" />
        <!-- Tweaks for older IEs-->
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
            />
        <!-- owl stylesheets -->
        <link rel="stylesheet" href="assets/user/css/owl.carousel.min.css" />
        <link rel="stylesheet" href="assets/user/css/owl.theme.default.min.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
            media="screen"
            />
    </head>


    <body>
        <%@include file="user-header.jsp" %>
        <!-- banner section start -->
        <div id="main_slider" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="banner_section">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <h1 class="banner-taital">
                                        Medi <br /><span style="color: #151515">Care</span>
                                    </h1>
                                    <p class="banner-text">
                                        Chăm sóc sức khỏe toàn diện
                                    </p>
                                    <div class="btn_main">
                                        <div class="more_bt"><a href="#">Contact Now</a></div>
                                        <div class="contact_bt"><a href="#">Get A Quote</a></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="image_1"><img src="assets/user/images/img-1.png" /></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- banner section end -->
        <!-- hot doctor section start -->
        <div id="doctor-carousel" class="carousel slide" data-ride="carousel">
            <div class="trend-container container">
                <h1 class="trend-doctor-title">Bác sĩ nổi bật</h1>
                <div class="row">
                    <div class="horizontal-doctor-list">
                        <c:forEach var="doctor" items="${trendDoctors}">
                            <div class="doctor-block">
                                <div class="doctor-img">
                                    <img src="assets/user/images/doctor-img.png" width="100" alt="${doctor.getDisplayName()}">
                                </div>
                                <div class="doctor-information">
                                    <span class="doctor-name">${doctor.getDisplayName()}</span><br>
                                    <span class="doctor-branch-name">${doctor.getBranchName()}</span><br>
                                    <span class="doctor-intro">${doctor.getCertificates()}</span><br>
                                    <span class="doctor-intro">${doctor.getARName()}</span><br>
                                    <button class="find-more">
                                        <a class="book-appointment-url" href="user-doctor-detail?doctorId=${doctor.getId()}">Tìm hiểu thêm</a>
                                    </button>
                                </div>
                            </div>
                        </c:forEach>
                        <a class="carousel-control-prev" href="#doctor-carousel" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#doctor-carousel" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- hot doctor section end -->
        <!-- banner section start -->
        <c:forEach items="${sessionScope.bannerList}" var="banner">
            <div class="banner_section layout_padding" style="background-image: url('${banner.getImage()}')">
                <div class="container">
                    <div class="banner_main">
                        <h1 class="banner_title">${banner.getTitle()}</h1>
                        <p class="banner_text">
                            ${banner.getDescription()}
                        </p>
                        <div class="row banner_bullet_points">
                            <c:forEach items="${sessionScope.bannerDetailsList}" var="detail">
                                <c:if test="${detail.getBannerId() eq banner.getId()}">
                                    <div class="col flex-grow-1 banner-number">${detail.getNumber()}</div>
                                </c:if>
                            </c:forEach>
                        </div>
                        <div class="row banner_bullet_points">
                            <c:forEach items="${sessionScope.bannerDetailsList}" var="detail">
                                <c:if test="${detail.getBannerId() eq banner.getId()}">
                                    <p class="col flex-grow-1 number-description">${detail.getInformation()}</p>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    <!-- banner section end -->
    <!-- news section start -->
    <div class="news_section layout_padding">
        <div class="container">
            <h1 class="health_taital">Bài viết nổi bật từ các Chuyên gia</h1>
            <p class="health_text">
                “Đi sâu vào các bài viết sâu sắc từ các chuyên gia chăm sóc sức khỏe hàng đầu, đưa ra những quan điểm mới mẻ về sức khỏe và thể chất.”
            </p>
            <div class="news_section_3 layout_padding">
                <c:forEach items="${topNews}" var="news">
                    <a class="news-block" href="user-news/id=${news.getId()}">
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
                            <i class="fas fa-calendar"> ${news.getCreatedAt()}</i>
                        </div>
                        <div class="news-view-count">
                            <i class="fas fa-eye"> ${news.getViewCount()}</i>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- news section end -->
    <!-- contact section start -->
    <div class="contact_section layout_padding">
        <div class="container">
            <h1 class="contact_taital">Đặt khám ngay bây giờ</h1>
            <div class="news_section_2">
                <div class="row">
                    <div class="col-md-6">
                        <div class="icon_main">
                            <div class="icon_7"><img src="assets/user/images/icon-7.png" /></div>
                            <h4 class="diabetes_text">Đội ngũ y bác sĩ nhiệt tình</h4>
                        </div>
                        <div class="icon_main">
                            <div class="icon_7"><img src="assets/user/images/icon-5.png" /></div>
                            <h4 class="diabetes_text">Năng lực chuyên môn cao</h4>
                        </div>
                        <div class="icon_main">
                            <div class="icon_7"><img src="assets/user/images/icon-6.png" /></div>
                            <h4 class="diabetes_text">Tiếp nhận bệnh nhân nhanh như chớp</h4>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="contact_box">
                            <h1 class="book_text">Book Appoinment</h1>
                            <input
                                type="text"
                                class="Email_text"
                                placeholder="Name"
                                name="Name"
                                />
                            <input
                                type="text"
                                class="Email_text"
                                placeholder="Name"
                                name="Name"
                                />
                            <textarea
                                class="massage-bt"
                                placeholder="Massage"
                                rows="5"
                                id="comment"
                                name="Massage"
                                ></textarea>
                            <div class="send_bt"><a href="#">SEND</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- contact section end -->
    <!-- client section start -->
    <div class="client_section layout_padding">
        <div id="my_slider" class="carousel slide" data-ride="carousel" data-interval="5000">
            <ol class="carousel-indicators">
                <c:forEach items="${sessionScope.topReviewList}" var="review" varStatus="loop">
                    <li data-target="#my_slider" data-slide-to="${loop.index}" class="${loop.first ? 'active' : ''}"></li>
                    </c:forEach>
            </ol>
            <h1 class="client_taital">Mọi người nói về chúng tôi</h1>
            <div class="carousel-inner">
                <c:forEach items="${sessionScope.topReviewList}" var="review" varStatus="loop">
                    <div class="carousel-item ${loop.first ? 'active' : ''}">
                        <div class="container">
                            <div class="client_section_2">
                                <div class="client_left">
                                    <c:forEach items="${sessionScope.listUser}" var="user">
                                        <c:if test="${user.getId() eq review.getUserId()}">
                                            <div>
                                                <img src="${user.getProfilePicture()}" class="client_img" />
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="client_right">
                                    <c:forEach items="${sessionScope.listUser}" var="user">
                                        <c:if test="${user.getId() eq review.getUserId()}">
                                            <h3 class="distracted_text">${user.getName()}</h3>
                                        </c:if>
                                    </c:forEach>
                                    <div class="review-content">
                                        <p class="lorem_text">${review.getReviewContent()}</p>
                                        <div class="quote_icon">
                                            <img src="assets/user/images/quote-icon.png" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- client section end -->
    <%@include file="user-footer.jsp" %>
    <!-- Javascript files-->
    <script src="assets/user/js/jquery.min.js"></script>
    <script src="assets/user/js/popper.min.js"></script>
    <script src="assets/user/js/bootstrap.bundle.min.js"></script>
    <script src="assets/user/js/jquery-3.0.0.min.js"></script>
    <script src="assets/user/js/plugin.js"></script>
    <!-- sidebar -->
    <script src="assets/user/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="assets/user/js/custom.js"></script>
    <!-- javascript -->
    <script src="assets/user/js/owl.carousel.js"></script>
    <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
    <script>
        $(document).ready(function () {

            // Tính toán chiều rộng của mỗi doctor-block
            var doctorBlockWidth = 360 + 22.5;

            // Đặt chiều rộng cho mỗi doctor-block
            $('.doctor-block').css('width', doctorBlockWidth + 'px');

            // Đặt chiều rộng cho danh sách
            var doctorListWidth = doctorBlockWidth * $('.doctor-block').length;
            $('#doctor-carousel .horizontal-doctor-list').css('width', doctorListWidth + 'px'); // Sửa tên ID

            // Sự kiện cuộn ngang khi nhấp vào nút "next"
            $('.carousel-control-next').click(function () {
                $('#doctor-carousel .horizontal-doctor-list').animate({scrollLeft: '+=' + doctorBlockWidth}, 'slow'); // Sửa tên ID
            });

            // Sự kiện cuộn ngang khi nhấp vào nút "prev"
            $('.carousel-control-prev').click(function () {
                $('#doctor-carousel .horizontal-doctor-list').animate({scrollLeft: '-=' + doctorBlockWidth}, 'slow'); // Sửa tên ID
            });
        });
    </script>
</body>
</html>

