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
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- style css -->
        <link rel="stylesheet" href="css/style.css" />
        <!-- Responsive-->
        <link rel="stylesheet" href="css/responsive.css" />
        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css" />
        <!-- Tweaks for older IEs-->
        <link
            rel="stylesheet"
            href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
            />
        <!-- owl stylesheets -->
        <link rel="stylesheet" href="css/owl.carousel.min.css" />
        <link rel="stylesheet" href="css/owl.theme.default.min.css" />
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
                                    <h1 class="banner_taital">
                                        Medi <br /><span style="color: #151515">Care</span>
                                    </h1>
                                    <p class="banner_text">
                                        Chăm sóc sức khỏe toàn diện
                                    </p>
                                    <div class="btn_main">
                                        <div class="more_bt"><a href="#">Contact Now</a></div>
                                        <div class="contact_bt"><a href="#">Get A Quote</a></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="image_1"><img src="images/img-1.png" /></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- banner section end -->
        <!-- health section start -->
        <div id="doctor-carousel" class="carousel slide" data-ride="carousel">
            <div class="trend-container container">
                <h1 class="trend-doctor-title">Bác sĩ nổi bật</h1>
                <div class="row">
                    <div class="horizontal-doctor-list">
                        <c:forEach var="doctor" items="${trendDoctors}">
                            <div class="doctor-block">
                                <div class="doctor-img">
                                    <img src="images/doctor-img.png" width="100" alt="${doctor.getDisplayName()}">
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

        <!-- health section end -->
        <!-- knowledge section end -->
        <div class="knowledge_section layout_padding">
            <div class="container">
                <div class="knowledge_main">
                    <div class="left_main">
                        <h1 class="knowledge_taital">Knowledge of center</h1>
                        <p class="knowledge_text">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                            enim ad minim veniam
                        </p>
                    </div>
                    <div class="right_main">
                        <div class="play_icon">
                            <a href="#"><img src="images/play-icon.png" /></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<!--        <div class="knowledge_section layout_padding">
            <div class="container">
                <div class="knowledge_main">
                    <div class="left_main">
                        <h1 class="knowledge_taital">Vingroup</h1>
                        <p class="knowledge_text">
                            Vingroup là công ty Việt Nam đầu tiên phát triển hệ thống bệnh viện tiêu chuẩn JCI toàn cầu, với mục tiêu "Phục vụ nhân dân Việt Nam, mang lại niềm vui và hạnh phúc".
                        </p>
                        <ul class="banner_bullet_points">
                            <li>- 10 năm kinh nghiệm</li>
                            <li>- 12 bệnh viện đạt tiêu chuẩn JCI</li>
                            <li>- Hơn 400 bác sĩ và y tá được đào tạo trong hệ thống đào tạo quốc tế của Vingroup</li>
                        </ul>
                    </div>>
                </div>
            </div>
        </div>-->
        <!-- knowledge section end -->
        <!-- news section start -->
        <div class="news_section layout_padding">
            <div class="container">
                <h1 class="health_taital">Why choose 24hr home care</h1>
                <p class="health_text">
                    labore et dolore magna aliqua. Ut enim ad minim veniam
                </p>
                <div class="news_section_2 layout_padding">
                    <div class="row">
                        <div class="col-lg-4 col-sm-6">
                            <div class="box_main">
                                <div class="icon_1"><img src="images/icon-2.png" /></div>
                                <h4 class="daily_text">Daily care experts</h4>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6">
                            <div class="box_main active">
                                <div class="icon_1"><img src="images/icon-3.png" /></div>
                                <h4 class="daily_text_1">Available 24/7</h4>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6">
                            <div class="box_main">
                                <div class="icon_1"><img src="images/icon-4.png" /></div>
                                <h4 class="daily_text_1">Balanced care</h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="getquote_bt">
                    <a href="#"
                       >Get A Quote <span><img src="images/right-arrow.png" /></span
                        ></a>
                </div>
            </div>
        </div>
        <!-- news section end -->
        <!-- contact section start -->
        <div class="contact_section layout_padding">
            <div class="container">
                <h1 class="contact_taital">What we do</h1>
                <div class="news_section_2">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="icon_main">
                                <div class="icon_7"><img src="images/icon-7.png" /></div>
                                <h4 class="diabetes_text">Diabetes and obesity Counselling</h4>
                            </div>
                            <div class="icon_main">
                                <div class="icon_7"><img src="images/icon-5.png" /></div>
                                <h4 class="diabetes_text">Obstetrics and Gynsecology</h4>
                            </div>
                            <div class="icon_main">
                                <div class="icon_7"><img src="images/icon-6.png" /></div>
                                <h4 class="diabetes_text">Surgical and medical Oncology</h4>
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
                <div class="carousel-inner">
                    <c:forEach items="${sessionScope.topReviewList}" var="review" varStatus="loop">
                        <div class="carousel-item ${loop.first ? 'active' : ''}">
                            <div class="container">
                                <h1 class="client_taital">What People Say</h1>
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
                                        <p class="lorem_text">${review.getReviewContent()}</p>
                                        <div class="quote_icon">
                                            <img src="images/quote-icon.png" />
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
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/jquery-3.0.0.min.js"></script>
        <script src="js/plugin.js"></script>
        <!-- sidebar -->
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="js/custom.js"></script>
        <!-- javascript -->
        <script src="js/owl.carousel.js"></script>
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

