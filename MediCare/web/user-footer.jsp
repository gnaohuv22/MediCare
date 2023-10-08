<%-- 
    Document   : footer
    Created on : Sep 16, 2023, 11:14:43 PM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

    <!-- footer section start -->
    <div class="footer_section layout_padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-sm-6">
                    <div class="footer_logo">
                        <a href="${pageContext.request.contextPath}/user-home"><img width="100" height="100" src="${pageContext.request.contextPath}/assets/client/images/logo.png" /></a>
                    </div>

                    <c:forEach items="${sessionScope.pages}" var="item">
                        <c:if test="${item.getId() eq 4}">
                            <h1 class="adderss_text">${item.getName()}</h1>
                            <c:forEach items="${sessionScope.subMenu}" var="contact">
                                <c:if test="${item.getId() eq contact.getCategoryId()}">
                                    <div class="map_icon">
                                        <img src="${pageContext.request.contextPath}/${contact.getIcon()}"/><span class="paddlin_left_0">${contact.getContent()}</span>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <h1 class="adderss_text">Bác sĩ tiêu biểu</h1>
                    <c:forEach items="${sessionScope.trendDoctors}" var="d">
                        <div class="hiphop_text_1">
                            <a href="${pageContext.request.contextPath}/user-doctor-detail?doctorId=${d.getId()}">${d.getDisplayName()}</a>
                        </div>
                    </c:forEach>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <c:forEach items="${sessionScope.pages}" var="item">
                        <c:if test="${item.getId() eq 5}">
                            <h1 class="adderss_text">${item.getName()}</h1>
                            <c:forEach items="${sessionScope.subMenu}" var="sub">
                                <c:if test="${item.getId() eq sub.getCategoryId()}">
                                    <div class="Useful_text">
                                        <a href="${sub.getHref()}">${sub.getContent()}</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <c:forEach items="${sessionScope.pages}" var="item">
                        <c:if test="${item.getId() eq 6}">
                            <h1 class="adderss_text">${item.getName()}</h1>
                            <div class="social_icon">
                                <ul>
                                    <c:forEach items="${sessionScope.subMenu}" var="sub">
                                        <c:if test="${item.getId() eq sub.getCategoryId()}">
                                            <li>
                                                <a href="${sub.getHref()}"><img src="${pageContext.request.contextPath}/${sub.getIcon()}" alt="${sub.getContent()}"/></a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!-- footer section end -->

    <div class="copyright_section">
        <div class="container">
            <p class="copyright_text">
                2023 by gnaohuv & SWP391_SE1733_NET_G6
            </p>
        </div>
    </div>
    <!-- copyright section end -->
    <!-- Javascript files-->
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

</html>
