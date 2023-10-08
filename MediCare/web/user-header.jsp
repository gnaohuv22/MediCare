<%-- 
    Document   : header
    Created on : Sep 16, 2023, 11:12:32 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="icon" href="${pageContext.request.contextPath}/assets/client/images/fevicon.png" type="image/gif" />
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
    <!-- header section start -->
    <div class="header_section">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="logo">
                <a href="${pageContext.request.contextPath}/user-home"><img width="100px" height="100px" src="${pageContext.request.contextPath}/assets/client/images/logo.png" /></a>
            </div>
            <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
                >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="mx-auto d-flex">
                    <ul class="navbar-nav mr-auto">
                        <form class="form-inline my-2 my-lg-0 mr-3" action="search-doctor" method="GET">
                            <input class="form-control mr-sm-2" type="search" placeholder="Nhập tên bác sĩ..." aria-label="Search" name="pattern" required>

                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                                <i class="fas fa-search"></i>
                            </button>
                        </form>

                        <c:forEach items="${sessionScope.subMenu}" var="sub">
                            <c:if test="${sub.getCategoryId() eq 1}">
                                <c:choose>
                                    <c:when test="${sub.getContent() eq 'Chi nhánh' or sub.getContent() eq 'Dịch vụ' or sub.getContent() eq 'Tin tức'}">
                                        <li class="nav-item list-parent">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/${sub.getHref()}">${sub.getContent()} <img src="https://www.svgrepo.com/show/495005/arrow-down.svg" width="10px" height="10px" alt="alt"/></a>
                                            <ul class="list-child">
                                                <c:forEach items="${sessionScope.subMenu}" var="item">
                                                    <c:if test="${item.getParentId() eq sub.getId()}">
                                                        <li>
                                                            <a href="${pageContext.request.contextPath}/${item.getHref()}">${item.getContent()}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/${sub.getHref()}">${sub.getContent()}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>

                <c:if test="${sessionScope.loginValue.equals('false') || sessionScope.loginValue==null}">

                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a
                                class="nav-link"
                                href="${pageContext.request.contextPath}/user-login" 
                                id="signin-btn"
                                style="
                                background-color: #68b2a0;
                                color: white;
                                border-radius: 10px;
                                margin-right: 5px;
                                "
                                >Đăng nhập</a
                            >
                        </li>
                        <li class="nav-item">
                            <a
                                class="nav-link"
                                href="${pageContext.request.contextPath}/user-register"
                                id="register-btn"
                                -btn
                                style="
                                background-color: white;
                                color: #2c6975;
                                border-radius: 10px;
                                margin-left: 5px;
                                "
                                >Đăng ký</a
                            >
                        </li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope.loginValue.equals('true')}">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item list-parent">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user-profile">
                                <img src="https://www.svgrepo.com/show/497407/profile-circle.svg" width="24px" height="24px" style="filter: invert(32%) sepia(55%) saturate(465%) hue-rotate(142deg) brightness(96%) contrast(88%)" alt="alt"/>
                                <span>${sessionScope.name}</span>
                                <img src="https://www.svgrepo.com/show/495005/arrow-down.svg" width="10px" height="10px" alt="alt"/>
                            </a>
                            <ul class="list-child">
                                <c:forEach items="${sessionScope.subMenu}" var="item">
                                    <c:if test="${item.getCategoryId() eq 8}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/${item.getHref()}">${item.getContent()}</a> 
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </c:if>
            </div>
        </nav>
        <!-- header section end -->

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
        <script>
            $(document).ready(function () {
                $("#signin-btn").hover(
                        function () {
                            $(this).css("background-color", "white");
                            $(this).css("color", "#68b2a0");
                        },
                        function () {
                            $(this).css("background-color", "#68b2a0");
                            $(this).css("color", "white");
                        }
                );
                $("#register-btn").hover(
                        function () {
                            $(this).css("background-color", "#2c6975");
                            $(this).css("color", "white");
                        },
                        function () {
                            $(this).css("background-color", "white");
                            $(this).css("color", "#2c6975");
                        }
                );
            });
        </script>
</html>
