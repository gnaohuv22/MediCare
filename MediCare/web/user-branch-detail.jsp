<%-- 
    Document   : health
    Created on : Sep 16, 2023, 11:19:22 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Health Page</title>

        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Health</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <jsp:include page="user-head.jsp"/>
    </head>

    <body>
        <%@include file="user-header.jsp" %>
        <!--profile header of a branch start-->
        <div class="branch-profile-header">
            <img src="${pageContext.request.contextPath}/assets/client/images/branch-img.jpg" alt="branch-img" class="branch-image" />
            <p class="branch-name">MediCare - ${requestScope.branch.getName()}</p>
        </div>
        <!--profile header of a branch end-->

        <!--branch information start-->
        <div class="branch-info">
            <div class="intro-header" onclick="toggleInfo()">
                <h3>Introduction</h3>
                <span class="toggle-icon">▼</span>
            </div>
            <div class="intro-content"  id="info-content">
                <!-- Information of branch here-->
                <p>${requestScope.branch.description}</p>
            </div>
        </div>

        <!--branch information end-->

        <!-- list doctors in a branch start -->
        <div class="doctors-container">
            <c:forEach items="${requestScope.doctorsOfBranch}" var="doctor">

                <div class="doctor-card">
                    <img src="${pageContext.request.contextPath}/assets/client/images/doctor-img.png" width="30%" height="height" alt="doctor-image"/>
                    <div class="doctor-info">
                        <h2 class="doctor-name">${doctor.getDisplayName()}</h2>
                        <p class="academic-degree">
                            <c:if test="${doctor.getARName()!=null}">
                                ${doctor.getARName()}
                                <c:if test="${doctor.getCertificates()!=null}">
                                    , ${doctor.getCertificates()}
                                </c:if>
                            </c:if>
                            <c:if test="${doctor.getARName()==null}">
                                <c:if test="${doctor.getCertificates()!=null}">
                                    ${doctor.getCertificates()}
                                </c:if>
                            </c:if>
                        </p>
                        <p class="department">Khoa: ${doctor.getDepartmentName()}</p>
                        <p class="branch">${doctor.getBranchName()}</p>
                        <div class="button-container">
                            <button class="book-appointment"> <a class="book-appointment-url" href="doctor-detail?doctorId=${doctor.getId()}">Đặt khám</a></button>
                        </div>
                    </div>
                </div>
            </c:forEach> 
        </div>
        <!-- list doctors in a branch end-->

        <%@include file="user-footer.jsp" %>
        <button id="back-to-top" title="Back to top">↑</button>

        <script>
            function toggleInfo() {
                var infoContent = document.getElementById("info-content");
                var toggleIcon = document.querySelector(".toggle-icon");

                if (infoContent.style.display === "none" || infoContent.style.display === "") {
                    infoContent.style.display = "block";
                    toggleIcon.innerHTML = "▲"; // Thay đổi biểu tượng khi mở
                } else {
                    infoContent.style.display = "none";
                    toggleIcon.innerHTML = "▼"; // Thay đổi biểu tượng khi đóng
                }
            }

        </script>
        <%@include file="user-script.jsp" %>
    </body>
</html>
