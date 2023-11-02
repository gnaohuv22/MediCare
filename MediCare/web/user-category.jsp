<%-- 
    Document   : user-category
    Created on : Oct 9, 2023, 4:29:46 PM
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
        <title>${nc.getName()} | MediCare</title>
        <jsp:include page="user-head.jsp"/>
    </head>
    <body>
        <%@include file="user-header.jsp" %>
    </div>

    <!--Display news by category-->

    <div class="display-sidebar">
        <div class="container">
            <%@include file="breadcrumb.jsp" %>
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
                    <c:forEach items="${topLevel}" var="top">
                        <c:forEach items="${parentIds}" var="id">
                            <c:if test="${top.getId() eq id}">
                                <c:set var="isParent" value="true" />
                            </c:if>
                        </c:forEach>
                        <!-- display other news-->     
                        <c:forEach items="${news}" var="n" varStatus="status">
                            <c:choose>
                                <c:when test="${n.getCategory().getLocateId() eq top.getId() and isParent}">
                                    <div class="row grid-news">
                                        <div class="grid-img col-md-4">
                                            <a href="${pageContext.request.contextPath}/news/${top.getHref()}/${n.getCategory().getHref()}/${n.getSlug()}">
                                                <img src="${n.getCoverImage()}" alt="${n.getTitle()}"/>
                                            </a>
                                        </div>
                                        <div class="news-information col-md-8">
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
                                    <c:if test="${top.getId() eq n.getCategory().getId()}">
                                        <div class="row grid-news">
                                            <div class="grid-img col-md-4">
                                                <a href="${pageContext.request.contextPath}/news/${n.getCategory().getHref()}/${n.getSlug()}">
                                                    <img src="${n.getCoverImage()}" alt="${n.getTitle()}"/>
                                                </a>
                                            </div>
                                            <div class="news-information col-md-8">
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
                </div>
            </div>
        </div>
    </div>
    <%@include file="user-footer.jsp" %>
    <button id="back-to-top" title="Back to top">↑</button>

    <%@include file="user-script.jsp" %>

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

<!--    <script>
        $(document).ready(function () {
            var pathArray = window.location.pathname.split("/");
            var currentCategory = pathArray[2]; // Lấy danh mục từ URL
            var currentSubCategory = pathArray[3]; // Lấy danh mục con từ URL (nếu có)

            $(".news-sidebar a").each(function () {
                var hrefArray = $(this).attr('href').split("/");
                var category = hrefArray[2]; // Lấy danh mục từ href
                var subCategory = hrefArray[3]; // Lấy danh mục con từ href (nếu có)

                if (category === currentCategory && (!currentSubCategory || subCategory === currentSubCategory)) { // Nếu liên kết này trỏ đến danh mục hiện tại và không có danh mục con hoặc liên kết này trỏ đến danh mục con hiện tại
                    $(this).addClass('active'); // Đặt nó thành "active"
                    if ($(this).parent().parent().is('ul')) { // Nếu nó là một danh mục con
                        $(this).parent().parent().show(); // Hiển thị danh mục cha
                        $(this).parent().parent().siblings('.caret-icon').children('i').css('transform', 'rotate(90deg)'); // Xoay icon caret
                    }
                }
            });
        });
    </script>-->
</body>
</html>
