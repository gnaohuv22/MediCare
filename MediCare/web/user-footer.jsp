<%-- 
    Document   : footer
    Created on : Sep 16, 2023, 11:14:43 PM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <h1 class="adderss_text">${item.getTitle()}</h1>
                            <c:forEach items="${sessionScope.contacts}" var="contact">
                                <div class="map_icon">
                                    <img src="${pageContext.request.contextPath}/${contact.getIcon()}"/><span class="paddlin_left_0">${contact.getName()}</span>
                                </div>
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
                            <h1 class="adderss_text">${item.getTitle()}</h1>
                            <c:forEach items="${sessionScope.references}" var="reference">
                                <div class="Useful_text">
                                    <a href="${reference.getHref()}">${reference.getName()}</a>
                                </div>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <c:forEach items="${sessionScope.pages}" var="item">
                        <c:if test="${item.getId() eq 6}">
                            <h1 class="adderss_text">${item.getTitle()}</h1>
                            <div class="social_icon">
                                <ul>
                                    <c:forEach items="${sessionScope.snsList}" var="sns">
                                        <li>
                                            <a href="${sns.getHref()}"><img src="${pageContext.request.contextPath}/${sns.getIcon()}" alt="${sns.getName()}"/></a>
                                        </li>
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
