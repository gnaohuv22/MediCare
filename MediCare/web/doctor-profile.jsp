<%-- 
    Document   : doctor-profile
    Created on : Sep 24, 2023, 5:31:35 PM
    Author     : phuon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="doctor-head.jsp"/>
        <title>Hồ sơ bác sĩ | MediCare</title>
    </head>
    <body>
        <jsp:include page="doctor-header.jsp"/>

        <div class="container">
            <div class="text-center">
                <h1 class="profile-title">Hồ sơ bác sĩ</h1>
            </div>
            <div class="profile-header">
                <div class="profile-body">
                    <div class="row">
                        <div class="col-md-4">
                            <img src="${d.profilePicture}" style="width: 200px; height: 200px; object-fit: cover" class="img-fluid rounded-circle" alt="Ảnh bác sĩ">
                            <h2>${d.displayName}</h2>
                            <p>Chuyên khoa: ${d.branchName}</p>
                            <p>Học vị: ${d.ARName}</p>
                            <p>Giới tính: ${d.gender}</p>
                            <p>Ngày sinh: ${d.birthDate}</p>
                            <p>Điện thoại: ${d.phone}</p>
                            <p>Email: ${d.email}</p>
                            <p>Nơi làm việc: ${d.workplace}</p>
                        </div>
                        <div class="col-md-8">
                            <h3>Giới thiệu</h3>
                            <p>${d.introduce}</p>
                            <h3>Lịch sử công việc</h3>
                            <p>${d.workHistory}</p>
                            <h3>Chứng chỉ</h3>
                            <p>${d.certificates}</p>
                            <h3>Đánh giá</h3>
                            <p>Số lượng đánh giá: ${numberOfReviews}</p>
                            <p>Đánh giá trung bình: ${overallRating}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <jsp:include page="doctor-footer.jsp"/>
        <jsp:include page="doctor-script.jsp"/>
    </body>
</html>
