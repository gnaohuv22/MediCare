<%-- 
    Document   : user-profile
    Created on : Sep 26, 2023, 4:59:54 AM
    Author     : phuon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="user-head.jsp"/>
        <title>Profile</title>
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
                                    <c:when test="${'user-profile' eq item.getHref()}">
                                        <li class="sidebar-active">
                                            <a href="#">${item.getContent()}</a>
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
                                <a href="user-profile?id=${fp.getProfileId()}">
                                    <li class="profile-item <c:if test="${fp.getProfileId() == requestScope.currentfp.profileId}">profile-item-active</c:if>">
                                            <div class="profile-item-pics ">
                                                <img src="https://www.svgrepo.com/show/497407/profile-circle.svg" width="50px" height="50px" alt="client-img"/> 
                                            </div>
                                            <div class="profile-item-info">
                                                <h3>${fp.getName()}</h3>
                                            <span>${fp.getBirthDate()}</span>
                                        </div>
                                    </li>
                                </a>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <c:set value="${requestScope.currentfp}" var="current"/>
                <div class="profile-display">
                    <div class="profile-display-header">
                        <div class="profile-display-pics ">
                            <img src="https://www.svgrepo.com/show/497407/profile-circle.svg" width="50px" height="50px" alt="client-img"/> 
                        </div>
                        <div class="profile-display-info">
                            <h2>${current.name}</h2>
                            <span>Patient Code: ${current.profileId}</span>
                        </div>
                    </div>
                    <div class="profile-display-body">
                        <h3>Thông tin cơ bản</h3>
                        <div class="profile-display-basic">
                            <span>Họ và tên:</span><span>${current.name}</span>
                            <span>Điện thoại:</span><span>${current.phone}</span>
                            <span>Ngày sinh:</span><span>${current.birthDate}</span>
                            <span>Giới tính:</span><span>${current.gender}</span>
                            <span>Địa chỉ:</span><span>${current.address}</span>
                        </div>
                        <h3>Thông tin bổ sung</h3>
                        <div class="profile-display-addition">
                            <span>Mã BHYT:</span><span>${current.medicalId}</span>
                            <span>Số CMND/CCCD:</span><span>${current.identity}</span>
                            <span>Dân tộc:</span><span>${current.ethnic}</span>
                            <span>Email:</span><span>${current.email}</span>
                        </div>
                    </div>
                </div>
            </div>
        </main>                       
        <jsp:include page="user-footer.jsp"/>
        <jsp:include page="user-script.jsp"/>
    </body>
</html>
