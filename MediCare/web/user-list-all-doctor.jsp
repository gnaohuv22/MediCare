<%-- 
    Document   : news
    Created on : Sep 16, 2023, 11:24:55 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Danh sách bác sĩ | MediCare</title>
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
            <p class="branch-name">MediCare - List of doctors nationwide</p>
        </div>
        <!--profile header of a branch end-->

        <!--list all doctors start-->
        <div class="doctors-container">
            <c:forEach items="${requestScope.doctors}" var="doctor">
                <div class="doctor-card">
                    <img src="${pageContext.request.contextPath}/assets/client/images/doctor-img.png" width="100" height="100" alt="doctor-image"/>
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
                            <button class="book-appointment"> <a class="book-appointment-url" href="user-doctor-detail?doctorId=${doctor.getId()}">Xem hồ sơ >></a></button>
                        </div>
                    </div>
                </div>
            </c:forEach> 
        </div>
        <nav aria-label="Pagination">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/user-list-all-doctor?page=${currentPage - 1}"><<</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${pageCount}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active">
                                <a class="page-link" href="${pageContext.request.contextPath}/#">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/user-list-all-doctor?page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt pageCount}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/user-list-all-doctor?page=${currentPage + 1}">>></a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <!--list all doctors end-->
        <%@include file="user-footer.jsp" %>
        <button id="back-to-top" title="Back to top">↑</button>
        <!-- Javascript files-->
        <%@include file="user-script.jsp" %>
    </body>
</html>
