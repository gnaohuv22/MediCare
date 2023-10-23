<%-- 
    Document   : user-news
    Created on : Sep 26, 2023, 8:42:29 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
        <title>${n.getTitle()} | MediCare</title>
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
        <!-- Add Fancybox CSS -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.css" />
    </head>
    <body>
        <%@include file="user-header.jsp" %>
    </div>

    <!-- display news section -->
    <div class="display-news">
        <div class="container ">
            <%@include file="breadcrumb.jsp" %>
            <div class="row">
                <div class="news col-md-9">
                    <h1 class="display-title">${n.getTitle()}</h1>
                    <div class="row">
                        <div class="col-md-6 text-left">
                            <p>
                                <i class="fas fa-user"></i> Author: ${n.getAuthor()}
                            </p>
                        </div>
                        <div class="col-md-6 text-right">
                            <p>
                                <i class="fas fa-calendar"></i> ${n.getCreatedAt()}
                            </p>
                        </div>
                    </div>
                    <h2 style="color: #2C6975">${n.getSubtitle()}</h2>
                    <img src="${n.getCoverImage()}" alt="${n.getTitle()}" class="fit-height">
                    <div class="news-content">
                        <c:out value="${n.getContent()}" escapeXml="false" />
                    </div>
                    <p>
                        <i class="fas fa-tags"></i> 
                        Categories: 
                        <c:forEach var="category" items="${categories}">
                            <c:if test="${category.getId() eq n.getCategoryId()}">
                                <a href="${pageContext.request.contextPath}/news/${category.getHref()}" class="category-box">
                                    ${category.getName()}
                                </a>
                            </c:if>
                        </c:forEach>
                    </p>
                </div>
                <div class="col-md-3 bordered-news">
                    <h3 class="related-news" style="font-weight: bold; font-size: 22px; color: #2C6975">Có thể bạn quan tâm</h3>
                    <!-- Replace this forEach with your actual data -->
                    <c:forEach var="news" items="${related}">
                        <div class="card mb-4">
                            <img src="${news.getCoverImage()}" alt="${news.getTitle()}" class="card-img-top">
                            <div class="card-body">
                                <h5 class="card-title">${news.getTitle()}</h5>
                                <div class=" d-flex flex-column justify-content-center align-items-center">
                                    <a href="${pageContext.request.contextPath}/news/${news.getCategorySlug()}/${news.getSlug()}" class="btn btn-primary btn-read-more">Read More</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!-- display news section -->
    <%@include file="user-footer.jsp" %>
    <!-- Javascript files-->
    <script src="${pageContext.request.contextPath}/assets/client/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/client/js/bootstrap.bundle.min.js"></script>
    <!-- Add jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/client/js/plugin.js"></script>
    <!-- sidebar -->
    <script src="${pageContext.request.contextPath}/assets/client/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/client/js/custom.js"></script>
    <!-- javascript -->
    <script src="${pageContext.request.contextPath}/assets/client/js/owl.carousel.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.js"></script>
    <script>
        $(window).on('load', function() {
            // Get all images from news content
            var images = $('.news-content img');
            console.log(images.length + ' images found');

            // Wrap each image with a link to itself
            images.each(function () {
                var image = $(this);
                image.wrap('<a data-fancybox="gallery" href="' + image.attr('src') + '"></a>');
            });

            // Initialize Fancybox
            $('[data-fancybox="gallery"]').fancybox({
                buttons: [
                    "zoom",
                    "slideShow",
                    "fullScreen",
                    "download",
                    "thumbs",
                    "close"
                ],
                animationEffect: "zoom",
                transitionEffect: "slide",
                loop: true
            });
            console.log('Fancybox initialized');
        });
    </script>
</body>
</html>
