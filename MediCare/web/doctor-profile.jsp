<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}assets/fonts/Inter/inter.css">
        <style>
            @import url('https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700');
            html {
                scroll-behavior: smooth;
            }

            body {
                color: #666666;
                font-size: 14px;
                font-family: 'Inter', sans-serif;
                line-height: 1.80857;
                font-weight: normal;
                overflow-x: hidden;
            }
            .doctor-profile p {
                font-size: 16px;
            }

            .profile-title {
                padding-bottom: 5%;
                color: #2C6975;
                font-weight: bolder;
                font-size: 28px;
            }

            .profile-picture {
                width: 200px;
                height: 200px;
                object-fit: cover;
                border-radius: 50%;
                margin: 20px;
                border: 10px solid #2C6975;
            }

            .profile-name {
                font-weight: bold;
                color: #2C6975;
            }

            .profile-information {
                color: #2C6975;
            }

            .more-information h3,
            .more-information p {
                color: #2C6975;
            }

            .more-information h3 {
                font-weight: bold;
            }

            .profile-specific h3,
            .profile-specific p {
                color: #2C6975;
            }

            .profile-specific h3 {
                font-weight: bold;
            }
        </style>
        <jsp:include page="doctor-head.jsp"/>
    </head>
    <body>
        <div class="container">
            <div class="doctor-profile">
                <div class="text-center">
                    <h1 class="profile-title">Hồ sơ bác sĩ</h1>
                </div>
                <div class="profile-header">
                    <div class="profile-body">
                        <div class="row">
                            <div class="col-md-4">
                                <img class="profile-picture" src="${d.profilePicture}" class="img-fluid rounded-circle" alt="Ảnh bác sĩ">
                                <h2 class="profile-name">${d.displayName}</h2>
                                <div class="profile-information">
                                    <p>Làm việc tại: ${d.branchName}</p>
                                    <p>Học vị: ${d.ARName}</p>
                                    <c:if test="${d.gender eq '1'}">
                                        <p>Giới tính: Nam</p>
                                    </c:if>
                                    <c:if test="${d.gender eq '0'}">
                                        <p>Giới tính: Nữ</p>
                                    </c:if>
                                    <p>Ngày sinh: ${d.birthDate}</p>
                                    <p>Điện thoại: ${d.phone}</p>
                                    <p>Email: ${d.email}</p>
                                    <p>Nơi làm việc: ${d.workplace}</p>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="more-information">
                                    <h3>Giới thiệu</h3>
                                    <p>${d.introduce}</p>

                                    <h3>Lịch sử công việc</h3>
                                    <p>${d.workHistory}</p>

                                    <h3>Chứng chỉ</h3>
                                    <p>${d.certificates}</p>

                                    <h3>Đánh giá</h3>
                                    <p>Số lượng đánh giá: ${numberOfReviews}</p>
                                    <p>Đánh giá trung bình: ${overallRating} / 5</p>
                                    <div class="profile-rating">
                                        <p>
                                            <c:set var="rating" value="${overallRating}"></c:set>
                                            <c:forEach begin="1" end="${rating}">
                                                <i class="fa-solid fa-star"></i>
                                            </c:forEach>
                                            <c:set var="intRating" value="${Math.floor(rating)}"></c:set>
                                            <c:choose>
                                                <c:when test="${rating gt intRating}">
                                                    <i class="fa-regular fa-star-half-stroke"></i>
                                                    <c:forEach begin="1" end="${5 - intRating - 1}">
                                                        <i class="fa-regular fa-star"></i>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach begin="1" end="${5 - intRating}">
                                                        <i class="fa-regular fa-star"></i>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                </div>
                                <div class="profile-specific">
                                    <h3>Dịch vụ cung cấp</h3>
                                    <ul>
                                        <c:forEach items="${services}" var="service">
                                            <li>${service.nametag}</li>
                                            </c:forEach>
                                    </ul>

                                    <h3>Kinh nghiệm</h3>
                                    <ul>
                                        <c:forEach items="${experiences}" var="experience">
                                            <li>${experiences.description}</li>
                                            </c:forEach>
                                    </ul>

                                    <h3>Giải thưởng</h3>
                                    <ul>
                                        <c:forEach items="${awards}" var="award">
                                            <li>${award.description}</li>
                                            </c:forEach>
                                    </ul>

                                    <h3>Học vấn</h3>
                                    <ul>
                                        <c:forEach items="${education}" var="edu">
                                            <li>${edu.description}</li>
                                            </c:forEach>
                                    </ul>

                                    <h3>Công trình nghiên cứu</h3>
                                    <ul>
                                        <c:forEach items="${papers}" var="paper">
                                            <li>${paper.description}</li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="doctor-script.jsp"/>
</html>
