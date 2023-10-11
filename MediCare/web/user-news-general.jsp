<%-- 
    Document   : user-news-general
    Created on : Oct 9, 2023, 10:57:33 PM
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
    <!-- banner section start -->
    <c:forEach items="${sessionScope.bannerList}" var="banner">
        <div class="banner_section layout_padding" style="background-image: url('${pageContext.request.contextPath}/${banner.getImage()}')">
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
    </c:forEach>

    <!--<h1>Hello World!</h1>-->

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
                                    <a href="${pageContext.request.contextPath}/news/${top.getSlug()}">${top.getName()} </a> 
                                    <span class="caret-icon"><i class="fas fa-caret-right"></i></span>
                                    <ul>
                                        <c:forEach items="${subLevel}" var="sub">
                                            <c:if test="${sub.getParentId() eq top.getId()}">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/news/${top.getSlug()}/${sub.getSlug()}">${sub.getName()}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${pageContext.request.contextPath}/news/${top.getSlug()}">${top.getName()}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                <div class="col-md-9">
                    
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
</body>

</html>
