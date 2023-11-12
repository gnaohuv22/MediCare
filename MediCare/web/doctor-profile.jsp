<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .doctor-profile p {
                font-size: 16px;
            }
            
            .profile-title1 {
                text-align: center;
            }

            .profile-title {
                padding-bottom: 30px;
                color: #2C6975;
                font-weight: 900;
                font-size: 28px;
            }

            .profile-picture {
                width: 200px;
                height: 200px;
                object-fit: cover;
                border-radius: 50%;
                margin-bottom: 20px;
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
            
            .fa-star,
            .fa-star-half-stroke {
                color: #ffbd1c;
            }

        </style>
        
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.css">
    </head>
    <body>
        <div class="container">
            <div class="doctor-profile">
                <div class="profile-title1">
                    <h1 class="profile-title">Hồ sơ bác sĩ</h1>
                </div>
                <div class="profile-header">
                    <div class="profile-body">
                        <div class="row">
                            <div class="col-md-4">
                                <img class="profile-picture" src="${d.profilePicture}" class="img-fluid rounded-circle" alt="Ảnh bác sĩ">
                                <h2 class="profile-name">${d.displayName}</h2>
                                <div class="profile-information">
                                    <p><i class="fa-solid fa-hospital"></i> Làm việc tại: ${d.branchName}</p>
                                    <p><i class="fa-solid fa-graduation-cap"></i> Học vị: ${d.ARName}</p>
                                    <c:if test="${d.gender eq '1'}">
                                        <p><i class="fa-solid fa-mars"></i> Giới tính: Nam</p>
                                    </c:if>
                                    <c:if test="${d.gender eq '0'}">
                                        <p><i class="fa-solid fa-venus"></i> Giới tính: Nữ</p>
                                    </c:if>
                                    <p><i class="fa-solid fa-cake-candles"></i> Ngày sinh: ${d.birthDate}</p>
                                    <p><i class="fa-solid fa-phone"></i> Điện thoại: ${d.phone}</p>
                                    <p><i class="fa-solid fa-envelope"></i> Email: ${d.email}</p>
                                    <p><i class="fa-solid fa-location-dot"></i> Nơi làm việc: ${d.workplace}</p>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="more-information">
                                    <h3><i class="fa-solid fa-circle-info"></i> Giới thiệu</h3>
                                    <p>${d.introduce}</p>

                                    <h3><i class="fa-solid fa-briefcase-medical"></i> Lịch sử công việc</h3>
                                    <p>${d.workHistory}</p>

                                    <h3><i class="fa-solid fa-certificate"></i> Chứng chỉ</h3>
                                    <p>${d.certificates}</p>

                                    <h3><i class="fa-solid fa-magnifying-glass"></i> Đánh giá</h3>
                                    <p>Số lượng đánh giá: ${sessionScope.numberOfRatings}</p>
                                    <p>Đánh giá trung bình: ${sessionScope.overallRating} / 5</p>
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
</html>
