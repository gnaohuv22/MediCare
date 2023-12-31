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
                       <c:forEach items="${sessionScope.profileSidebar}" var="item">
                            <c:choose>
                                <c:when test="${'user-payment-history' eq item.getHref()}">
                                    <li class="sidebar-active">
                                        <a href="#">${item.getName()}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li> 
                                        <a href="${item.getHref()}">${item.getName()}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
                <div class="search-box">
                    <form action="" method="post" class="search-bar">
                        <input placeholder="Tên bệnh nhân, số điện thoại, email" type="search" name="search-payment" id="search-profile">
                        <input type="hidden" name="method" value="search"/>
                    </form>
                    <div class="profile-list">
                        <ul>
                            <c:forEach items="${requestScope.bhList}" var="bh">
                                <li class="profile-item" onclick="loadProfile(${bh.getId()})" id="${bh.getId()}">
                                    <div class="profile-item-info">
                                        <h3>${bh.getAppointment().getFp().getName()}</h3>
                                        <span>${bh.getCreatedAt()}</span><br>
                                        <span>Tổng: ${bh.getTotalCash()}K</span>
                                    </div>
                                </li>   
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <c:set value="" var="current"/>
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
                    url: "/MediCare/load-html?id=" + id + "&method=payment",
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
