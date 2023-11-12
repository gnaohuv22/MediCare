<%-- 
    Document   : user-appointment
    Created on : Sep 26, 2023, 4:59:54 AM
    Author     : phuon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="user-head.jsp"/>
        <title>Danh sách lịch khám | MediCare</title>
    </head>
    <body>
        <jsp:include page="user-header.jsp"/>
        <main>
            <div class="main-wrapper profile-wrapper">
                <div class="sidebar">
                    <ul>
                        <c:forEach items="${sessionScope.profileSidebar}" var="item">
                            <c:choose>
                                <c:when test="${'user-appointment' eq item.getHref()}">
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
                        <input placeholder="Tên bệnh nhân, số điện thoại" type="search" name="search-appointment" id="search-appointment">
                        <input type="hidden" name="method" value="search"/>
                    </form>
                    <div class="profile-list">
                        <ul>
                            <c:forEach items="${requestScope.aList}" var="a">
                                <li class="profile-item" onclick="loadAppointment(${a.getId()})" id="${a.getId()}">
                                    <div class="profile-item-info">
                                        <h3>${a.getDoctor().getDisplayName()}</h3><span class="profile-item-pics-relation">${a.getStatus()}</span><br>
                                        <span>${a.getPlannedAt()}</span><br>
                                        <span>${a.getFp().getName()}</span>

                                    </div>
                                </li>   
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="profile-display" id="profile-display">

                </div>
                <div id="deleteForm" class="form-popup">
                    <form action="user-appointment" class="form-container" method="post">
                        <div class="form-group">
                            <p>Bạn có chắc chắn muốn hủy cuộc hẹn này?</p>
                        </div>
                        <input type="hidden" name="appointmentId" id="appointmentId" value="">
                        <input type="hidden" name="method" id="method" value="cancel">
                        <div class="form-buttons">
                            <button type="button" id="delete-close-button" onclick="closeCancelAppointmentForm()">Đóng</button>
                            <button class="button-container" id="delete-submit-button" type="submit">Chắc chắn</button>
                        </div>
                    </form>
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

            function loadAppointment(id) {
                $.ajax({
                    url: "/MediCare/load-html?id=" + id + "&method=appointment",
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

            function openCancelAppointmentForm() {
                var modal = document.getElementById("deleteForm");
                modal.style.display = "block";
                document.getElementById("method").value = "cancel";
                document.getElementById("appointmentId").value = document.getElementById("appointment-id").textContent;
                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                        document.getElementById("method").value = "cancel";
                    }
                };
            }
            function closeCancelAppointmentForm() {
                var modal = document.getElementById("deleteForm");
                modal.style.display = "none";
            }

// Get the span element
            var span = document.getElementById('profile-item-pics-relation');

        </script>
    </body>
</html>
