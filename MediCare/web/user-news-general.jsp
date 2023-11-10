<%-- 
    Document   : user-news-general
    Created on : Oct 9, 2023, 10:57:33 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <title>Tin tức | MediCare</title>
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


    <!--News general display section-->

    <div class="display-sidebar">
        <div class="container">
            <%@include file="breadcrumb.jsp" %>
            <div class="row">
                <c:forEach items="${topLevel}" var="top">
                    <c:set var="isParent" value="false"></c:set>
                    <c:forEach items="${parentIds}" var="id">
                        <c:if test="${top.getId() eq id}">
                            <c:set var="isParent" value="true"></c:set>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${isParent and centeredNews.getCategory().getLocateId() eq top.getId()}">
                            <div class="centered-news">
                                <a href="${pageContext.request.contextPath}/news/${top.getHref()}/${centeredNews.getCategory().getHref()}/${centeredNews.getSlug()}">
                                    <div class="centered-img">
                                        <img src="${centeredNews.getCoverImage()}" alt="${centeredNews.getTitle()}"/>
                                    </div>
                                    <h1 class="display-title">${centeredNews.getTitle()}</h1>
                                    <div class="centered-category">
                                        <a class="news-href" href="${pageContext.request.contextPath}/news/${top.getHref()}/${centeredNews.getCategory().getHref()}">
                                            <i class="fas fa-tags"></i> ${centeredNews.getCategory().getName()}
                                        </a>
                                    </div>
                                    <span class="centered-subtitle">
                                        <p>${centeredNews.getSubtitle()}</p>
                                    </span>
                                </a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${centeredNews.getCategory().getId() eq top.getId()}">
                                <div class="centered-news">
                                    <a href="${pageContext.request.contextPath}/news/${centeredNews.getCategory().getHref()}/${centeredNews.getSlug()}">
                                        <div class="centered-img">
                                            <img src="${centeredNews.getCoverImage()}" alt="${centeredNews.getTitle()}"/>
                                        </div>
                                        <h1 class="display-title">${centeredNews.getTitle()}</h1>
                                        <div class="centered-category">
                                            <a class="news-href" href="${pageContext.request.contextPath}/news/${centeredNews.getCategory().getHref()}">
                                                <i class="fas fa-tags"></i> ${centeredNews.getCategory().getName()}
                                            </a>
                                        </div>
                                        <span class="centered-subtitle">
                                            <p>${centeredNews.getSubtitle()}</p>
                                        </span>
                                    </a>
                                </div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <!-- display other news by their category-->  

                <div class="display-by-category">
                    <c:forEach items="${requestScope.categorizedNews}" var="entry">
                        <div class="category-content">
                            <div class="main-category">
                                <a href="${pageContext.request.contextPath}/news/${entry.key.getHref()}">${entry.key.getName()}</a>
                            </div>
                            <div class="sub-categories">
                                <c:forEach items="${subLevel}" var="item">
                                    <c:if test="${item.getParentId() eq entry.key.getId()}">
                                        <a href="${pageContext.request.contextPath}/news/${entry.key.getHref()}/${item.getHref()}">
                                            ${item.getName()}
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>

                        <c:forEach items="${topLevel}" var="top">
                            <c:set var="isParent" value="false"></c:set>
                            <c:forEach items="${parentIds}" var="id">
                                <c:if test="${top.getId() eq id}">
                                    <c:set var="isParent" value="true"></c:set>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${entry.value}" var="n">
                                <c:choose>
                                    <c:when test="${isParent and n.getCategory().getLocateId() eq top.getId()}">
                                        <div class="grid-news">
                                            <div class="grid-img col-md-4">
                                                <a href="${pageContext.request.contextPath}/news/${top.getHref()}/${n.getCategory().getHref()}/${n.getSlug()}">
                                                    <img src="${n.getCoverImage()}" alt="${n.getTitle()}"/>
                                                </a>
                                            </div>
                                            <div class="col-md-8 news-information">
                                                <a href="${pageContext.request.contextPath}/news/${top.getHref()}/${n.getCategory().getHref()}/${n.getSlug()}">
                                                    <h3 class="grid-title">
                                                        ${n.getTitle()}
                                                    </h3>
                                                </a>
                                                <span class="grid-subtitle">
                                                    ${n.getSubtitle()}
                                                </span>
                                                <a class="news-href" href="${pageContext.request.contextPath}/news/${top.getHref()}/${n.getCategory().getHref()}">
                                                    <i class="fas fa-tags"></i> ${n.getCategory().getName()}
                                                </a>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${n.getCategory().getId() eq top.getId()}">
                                            <div class="grid-news">
                                                <div class="grid-img col-md-4">
                                                    <a href="${pageContext.request.contextPath}/news/${n.getCategory().getHref()}/${n.getSlug()}">
                                                        <img src="${n.getCoverImage()}" alt="${n.getTitle()}"/>
                                                    </a>
                                                </div>
                                                <div class="col-md-8 news-information">
                                                    <a href="${pageContext.request.contextPath}/news/${n.getCategory().getHref()}/${n.getSlug()}">
                                                        <h3 class="grid-title">
                                                            ${n.getTitle()}
                                                        </h3>
                                                    </a>
                                                    <span class="grid-subtitle">
                                                        ${n.getSubtitle()}
                                                    </span>
                                                    <a class="news-href" href="${pageContext.request.contextPath}/news/${n.getCategory().getHref()}">
                                                        <i class="fas fa-tags"></i> ${n.getCategory().getName()}
                                                    </a>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:forEach>
                    </c:forEach>
                </div>
                <!-- display other news by their category--> 
            </div>
        </div>
    </div>


    <%@include file="user-footer.jsp" %>
    <button id="back-to-top" title="Back to top">↑</button>
    <!-- Javascript files-->
    <jsp:include page="user-script.jsp"/>

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
            var pathArray = window.location.pathname.split("/");
            var currentCategory = pathArray[2]; // Lấy danh mục từ URL
            var currentSubCategory = pathArray[3]; // Lấy danh mục con từ URL (nếu có)

            $(".news-sidebar a").each(function () {
                var hrefArray = $(this).attr('href').split("/");
                var category = hrefArray[2]; // Lấy danh mục từ href
                var subCategory = hrefArray[3]; // Lấy danh mục con từ href (nếu có)

                if (category === currentCategory) { // Nếu liên kết này trỏ đến danh mục hiện tại
                    if (!currentSubCategory || subCategory === currentSubCategory) { // Nếu không có danh mục con hoặc liên kết này trỏ đến danh mục con hiện tại
                        $(this).addClass('active'); // Đặt nó thành "active"
                        if ($(this).parent().parent().is('ul')) { // Nếu nó là một danh mục con
                            $(this).parent().parent().show(); // Hiển thị danh mục cha
                            $(this).parent().parent().siblings('.caret-icon').children('i').css('transform', 'rotate(90deg)'); // Xoay icon caret
                        }
                    }
                }
            });
        });
    </script>

</body>

</html>
