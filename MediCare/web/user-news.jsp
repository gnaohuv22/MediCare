<%-- 
    Document   : user-news
    Created on : Sep 26, 2023, 8:42:29 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <jsp:include page="user-head.jsp"/>
    </head>
    <body>
        <c:choose>
            <c:when test="${doctorLoggedIn}">
                <%@include file="doctor-header.jsp" %>
            </c:when>
            <c:otherwise>
                <%@include file="user-header.jsp" %>
            </div>
        </c:otherwise>
    </c:choose>


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
                        Danh mục:
                        <c:forEach items="${topLevel}" var="top">
                            <c:set var="isParent" value="false"></c:set>
                            <c:forEach items="${parentIds}" var="id">
                                <c:if test="${top.getId() eq id}">
                                    <c:set var="isParent" value="true"></c:set>
                                </c:if>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${isParent and n.getCategory().getLocateId() eq top.getId()}">
                                    <a href="${pageContext.request.contextPath}/news/${top.getHref()}/${n.getCategory().getHref()}" class="category-box">
                                        ${n.getCategory().getName()}
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${n.getCategory().getId() eq top.getId()}">
                                        <a href="${pageContext.request.contextPath}/news/${n.getCategory().getHref()}" class="category-box">
                                            ${n.getCategory().getName()}
                                        </a>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </p>
                </div>
                <div class="col-md-3 bordered-news">
                    <h3 class="related-news" style="font-weight: bold; font-size: 22px; color: #2C6975">Tin cùng chuyên mục</h3>
                    <!-- Replace this forEach with your actual data -->
                    <c:forEach items="${topLevel}" var="top">
                        <c:set var="isParent" value="false"></c:set>
                        <c:forEach items="${parentIds}" var="id">
                            <c:if test="${top.getId() eq id}">
                                <c:set var="isParent" value="true"></c:set>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="news" items="${related}">
                            <c:choose>
                                <c:when test="${isParent and news.getCategory().getLocateId() eq top.getId()}">
                                    <div class="card mb-4">
                                        <img src="${news.getCoverImage()}" alt="${news.getTitle()}" class="card-img-top">
                                        <div class="card-body">
                                            <h5 class="card-title">${news.getTitle()}</h5>
                                            <div class=" d-flex flex-column justify-content-center align-items-center">
                                                <a href="${pageContext.request.contextPath}/news/${top.getHref()}/${news.getCategory().getHref()}/${news.getSlug()}" class="btn btn-primary btn-read-more">Read More</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${news.getCategory().getId() eq top.getId()}">
                                        <div class="card mb-4">
                                            <img src="${news.getCoverImage()}" alt="${news.getTitle()}" class="card-img-top">
                                            <div class="card-body">
                                                <h5 class="card-title">${news.getTitle()}</h5>
                                                <div class=" d-flex flex-column justify-content-center align-items-center">
                                                    <a href="${pageContext.request.contextPath}/news/${news.getCategory().getHref()}/${news.getSlug()}" class="btn btn-primary btn-read-more">Read More</a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!-- display news section -->
    <%@include file="user-footer.jsp" %>
    <button id="back-to-top" title="Back to top">↑</button>
    <!-- Javascript files-->
    <%@include file="user-script.jsp" %>
    <script>
        $(window).on('load', function () {
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
                    "fullScreen",
                    "download",
                    "thumbs",
                    "close"
                ],
                loop: true
            });
            console.log('Fancybox initialized');
        });
    </script>
</body>
</html>
