<%-- 
    Document   : user-payment-history
    Created on : Oct 9, 2023, 11:44:17 PM
    Author     : phuon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="user-head.jsp"/>
        <title>Lịch sử thanh toán | MediCare</title>
    </head>
    <body>
        <jsp:include page="user-header.jsp"/>
        <main>
            <div class="main-wrapper profile-wrapper">
                <div class="sidebar">
                    <ul>
                        <c:forEach items="${sessionScope.subMenu}" var="item">
                            <c:if test="${item.getCategoryId() eq 9}">
                                <c:choose>
                                    <c:when test="${'user-payment-history' eq item.getHref()}">
                                        <li class="sidebar-active">
                                            <a href="${item.getHref()}">${item.getContent()}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li> 
                                            <a href="${item.getHref()}">${item.getContent()}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
                <div class="search-box">
                    <form action="" method="post" class="search-bar">
                        <input placeholder="Tìm nhanh hồ sơ" type="search" name="search-profile" id="search-profile">
                    </form>
                    <div class="profile-list">
                        <ul>
                            <c:forEach items="${requestScope.fpList}" var="fp">
                                <li class="profile-item" onclick="loadProfile(${fp.getProfileId()})" id="${fp.getProfileId()}">
                                    <div class="profile-item-pics ">
                                        <img src="https://www.svgrepo.com/show/497407/profile-circle.svg" width="50px" height="50px" alt="client-img"/> 
                                    </div>
                                    <div class="profile-item-info">
                                        <h3>${fp.getName()}</h3>
                                        <span>${fp.getBirthDate()}</span>
                                    </div>
                                </li>   
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <c:set value="${requestScope.currentfp}" var="current"/>
                <div class="profile-display" id="profile-display">

                </div>
            </div>
        </main>                       
        <jsp:include page="user-footer.jsp"/>
        <button id="back-to-top" title="Back to top">↑</button>
        <jsp:include page="user-script.jsp"/>
        <script>
            $(document).ready(function () {
                // Trigger a click event on the first <li> element
                $(".profile-list li:first").trigger("click");
            });

            function loadProfile(id) {
                $.ajax({
                    url: "/MediCare/load-profile?id=" + id,
                    type: "GET",
                    success: function (data) {
                        var box = document.getElementById("profile-display");
                        box.innerHTML = data;
                        $(".profile-list li").removeClass("profile-item-active");
                        var current = document.getElementById(id);
                        current.className += " profile-item-active";
                    },
                    error: function (jqXHR) {

                    }
                });
            }
        </script>
    </body>
</html>
