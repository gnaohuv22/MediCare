<%-- 
    Document   : header
    Created on : Sep 16, 2023, 11:12:32 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                    <c:forEach items="${sessionScope.navbar}" var="nav">
                        <c:choose>
                            <c:when test="${nav.getName() eq 'Chi nhánh' or nav.getName() eq 'Dịch vụ' or nav.getName() eq 'Tin tức'}">
                                <li class="nav-item list-parent">
                                    <c:choose>
                                        <c:when test="${nav.getName() eq 'Tin tức'}">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/${nav.getHref()}">${nav.getName()} <img src="https://www.svgrepo.com/show/495005/arrow-down.svg" width="10px" height="10px" alt="alt"/></a>
                                            <ul class="list-child">
                                                <c:forEach items="${sessionScope.subMenu}" var="sub">
                                                    <c:if test="${sub.getParentId() eq nav.getId()}">
                                                        <li>
                                                            <a href="${pageContext.request.contextPath}/${nav.getHref()}/${sub.getHref()}">${sub.getName()}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="nav-link" href="${nav.getHref()}">${nav.getName()} <img src="https://www.svgrepo.com/show/495005/arrow-down.svg" width="10px" height="10px" alt="alt"/></a>
                                            <ul class="list-child">
                                        <c:forEach items="${sessionScope.subMenu}" var="sub">
                                            <c:if test="${sub.getParentId() eq nav.getId()}">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/${sub.getHref()}">${sub.getName()}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                            </c:otherwise>
                                        </c:choose>

                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/${nav.getHref()}">${nav.getName()}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
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
                            <img src="${sessionScope.u.getProfilePicture()}" style="border-radius: 50%; object-fit: cover; width: 40px; height: 40px" alt="alt"/>
                            <span>${sessionScope.name}</span>
                            <img src="https://www.svgrepo.com/show/495005/arrow-down.svg" width="10px" height="10px" alt="alt"/>
                        </a>
                        <ul class="list-child">
                            <c:forEach items="${sessionScope.profileMenu}" var="item">
                                <li>
                                    <a href="${pageContext.request.contextPath}/${item.getHref()}">${item.getName()}</a> 
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </c:if>
        </div>
    </nav>
    <!-- header section end -->
    <jsp:include page="user-script.jsp"></jsp:include>
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
