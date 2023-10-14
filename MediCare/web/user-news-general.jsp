<%-- 
    Document   : user-news-general
    Created on : Oct 9, 2023, 10:57:33 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
        <title>Tin tức | MediCare</title>
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
    </head>
    <body>
        <%@include file="user-header.jsp" %>
    </div>

    <!--News general display section-->

    <div class="display-sidebar">
        <div class="container">
            <div class="row">
                <div class="col-md-3 news-sidebar">
                    <c:forEach items="${topLevel}" var="top">
                        <c:set var="isParent" value="false" />
                        <c:forEach items="${parentIds}" var="id">
                            <c:if test="${top.getId() eq id}">
                                <c:set var="isParent" value="true" />
                            </c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${isParent}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/news/${top.getHref()}">${top.getName()} </a> 
                                    <span class="caret-icon"><i class="fas fa-caret-right"></i></span>
                                    <ul>
                                        <c:forEach items="${subLevel}" var="sub">
                                            <c:if test="${sub.getParentId() eq top.getId()}">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/news/${top.getHref()}/${sub.getHref()}">${sub.getName()}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${pageContext.request.contextPath}/news/${top.getHref()}">${top.getName()}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                <div class="col-md-9">
                    <div class="centered-news">
                        <a href="${pageContext.request.contextPath}/news/${centeredNews.getCategorySlug()}/${centeredNews.getSlug()}">
                            <div class="centered-img">
                                <img src="${centeredNews.getCoverImage()}" alt="${centeredNews.getTitle()}"/>
                            </div>
                            <h1 class="display-title">${centeredNews.getTitle()}</h1>
                            <div class="centered-category">
                                <a class="news-href" href="${pageContext.request.contextPath}/news/${centeredNews.getCategorySlug()}">
                                    <i class="fas fa-tags"></i> ${centeredNews.getCategory()}
                                </a>
                            </div>
                            <span class="centered-subtitle">
                                <p>${centeredNews.getSubtitle()}</p>
                            </span>
                        </a>
                    </div>
                    <!-- display other news-->     
                    <c:forEach items="${news}" var="n" varStatus="status">
                        <div class="row grid-news">
                            <div class="grid-img col-md-4">
                                <a href="${pageContext.request.contextPath}/news/${n.getCategorySlug()}/${n.getSlug()}">
                                    <img src="${n.getCoverImage()}" alt="${n.getTitle()}"/>
                                </a>
                            </div>
                            <div class="news-information col-md-8">
                                <a href="${pageContext.request.contextPath}/news/${n.getCategorySlug()}/${n.getSlug()}">
                                    <h3 class="grid-title">
                                        ${n.getTitle()}
                                    </h3>
                                </a>
                                <span class="grid-subtitle">
                                    ${n.getSubtitle()}
                                </span>
                                <a class="news-href" href="${pageContext.request.contextPath}/news/${n.getCategorySlug()}">
                                    <i class="fas fa-tags"></i> ${n.getCategory()}
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>


    <%@include file="user-footer.jsp" %>
    <!-- Javascript files-->
    <jsp:include page="user-script.jsp"/>
    <script>
        $('.caret-icon').click(function (event) {
            event.stopPropagation();
            $(this).siblings('ul').toggle();
            var i = $(this).children('i');
            if (i.hasClass('rotated')) {
                i.css('transform', 'rotate(0deg)');
                i.removeClass('rotated');
            } else {
                i.css('transform', 'rotate(90deg)');
                i.addClass('rotated');
            }
        });
    </script>

    <script>
        $(document).ready(function () {
            $('.centered-news').each(function () {
                var maxLength = 200; // Set the maximum length for the content
                var subtitleElement = $(this).find('.centered-subtitle');
                if (subtitleElement.text().length > maxLength) {
                    var shortText = subtitleElement.text().substr(0, maxLength) + '...'; // Cut the content
                    var longText = subtitleElement.text().substr(maxLength);

                    // Create the "Read more" button
                    var btn = $('<a href="' + $(this).attr('href') + '" style="color: #68B2A0; text-decoration: underline;"> Read more>></a>');

                    // Replace the content with the cut content and the "Read more" button
                    subtitleElement.html(shortText);
                    subtitleElement.append(btn);
                }
            });
        });
    </script>
    <script>
        $(document).ready(function () {
            $('.grid-news').each(function () {
                var maxLength = 315; // Set the maximum length for the content
                var subtitleElement = $(this).find('.grid-subtitle');
                if (subtitleElement.text().length > maxLength) {
                    var shortText = subtitleElement.text().substr(0, maxLength) + '...'; // Cut the content
                    var longText = subtitleElement.text().substr(maxLength);

                    // Create the "Read more" button
                    var btn = $('<a href="' + $(this).find('a').attr('href') + '" style="color: #68B2A0; text-decoration: underline;"> Read more>></a>');

                    // Replace the content with the cut content and the "Read more" button
                    subtitleElement.html(shortText);
                    subtitleElement.append(btn);
                }
            });
        });
    </script>
</body>

</html>
