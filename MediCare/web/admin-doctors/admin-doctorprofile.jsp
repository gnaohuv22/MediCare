<%-- 
    Document   : admin-doctorprofile
    Created on : Oct 15, 2023, 11:50:30 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../admin-general/admin-head.jsp" />
    </head>
    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
            <jsp:include page="../admin-general/admin-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-7 col-6">
                            <h4 class="page-title">Doctor ${requestScope.doc.getDisplayName()} 's Profile</h4>
                        </div>

                        <div class="col-sm-5 col-6 text-right m-b-30">
                            <a  class="dropdown-item" href="admin-doctor?id=${requestScope.doc.getId()}&action=edit" data-toggle="modal" data-target="#edit_doctor" onclick="setDoctorId(${doc.getId()})"> <i class="fa fa-plus"></i> Chỉnh sửa thông tin cá nhân</a>
                        </div>
                    </div>
                    <div class="card-box profile-header">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="profile-view">
                                    <div class="profile-img-wrap">
                                        <div class="profile-img">
                                            <a href="#"><img class="avatar" src="${requestScope.doc.getProfilePicture()}" alt=""></a>
                                        </div>
                                    </div>
                                    <div class="profile-basic">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <div class="profile-info-left">
                                                    <h3 class="user-name m-t-0 mb-0">${requestScope.doc.getDisplayName()}</h3>
                                                    <small class="text-muted">${requestScope.doc.getARName()}</small>
                                                    <div class="staff-id">ID Bác sĩ : ${requestScope.doc.getId()}</div>

                                                </div>
                                            </div>
                                            <div class="col-md-7">
                                                <ul class="personal-info">
                                                    <li>
                                                        <span class="title">Số điện thoại :</span>
                                                        <span class="text"><a href="tel:${requestScope.doc.getPhone()}">${requestScope.doc.getPhone()}</a></span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Email :</span>
                                                        <span class="text"><a href="mailto:${requestScope.doc.getEmail()}">${requestScope.doc.getEmail()}</a></span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Ngày sinh : </span>
                                                        <span class="text" id="birthDateText">${requestScope.doc.getBirthDate()}</span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Nơi làm việc : </span>
                                                        <span class="text">${requestScope.doc.getWorkplace()}</span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Giới tính</span>
                                                        <span class="text" > <c:if test = "${requestScope.doc2.getGender()==1}">Nam</c:if>
                                                                            <c:if test = "${requestScope.doc2.getGender()==2}">Nữ</c:if>
                                                                            <c:if test = "${requestScope.doc2.getGender()==3}">Không rõ</c:if>
                                                        </span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>                        
                            </div>
                        </div>
                    </div>
                    <div class="profile-tabs">
                        <ul class="nav nav-tabs nav-tabs-bottom">
                            <li class="nav-item"><a class="nav-link active" href="#about-cont" data-toggle="tab">Thông Tin</a></li>
                        </ul>

                        <div class="tab-content">
                            <div class="tab-pane show active" id="about-cont">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="card-box">
                                            <h3 class="card-title">Thông tin</h3>
                                            <div class="experience-box">
                                                <ul class="experience-list">
                                                    <li>
                                                        <div class="experience-user">
                                                            <div class="before-circle"></div>
                                                        </div>
                                                        
                                                        <div class="experience-content">
                                                            <span class="title">Chức vụ : </span>
                                                            <div class="timeline-content">                                  
                                                                <div>${requestScope.doc.getARName()}</div>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="card-box mb-0">
                                            <h3 class="card-title">Chứng chỉ </h3>
                                            <div class="experience-box">

                                                  <c:forEach var="a" items="${listCertOfDoctor}" varStatus="status">
                                                    <ul class="experience-list">
                                                        <li>
                                                            <div class="experience-user">
                                                                <div class="before-circle"></div>
                                                            </div>
                                                            <div class="experience-content">
                                                                <div class="timeline-content">
                                                                    <div type="text" class="name" value=${a.getCertId()}}">${a.getCertName()}</div>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </c:forEach>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="bottom-tab2">
                                Tab content 2
                            </div>
                            <div class="tab-pane" id="bottom-tab3">
                                Tab content 3
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
            <form id="editForm" action="admin-doctor" method="get">
                <input type="hidden" name="id" value="">
                <input type="hidden" name="action" value="edit">
            </form>
            <script>
                function setDoctorId(doctorId) {
                    var editForm = document.getElementById('editForm');
                    var idInput = editForm.querySelector('input[name="id"]');
                    idInput.value = doctorId;
                    editForm.submit();
                }
            </script>
            <script>

                // Lấy phần tử chứa giới tính dựa trên ID "genderText"
                var genderElement = document.getElementById("genderText");

                // Lấy giá trị giới tính
                var genderValue = genderElement.textContent;

                // Chuyển đổi giá trị giới tính
                if (genderValue === "1") {
                    genderElement.textContent = "Male";
                } else if (genderValue === "2") {
                    genderElement.textContent = "Female";
                } else if (genderValue === "3") {
                    genderElement.textContent = "Others";
                }
                // Lấy phần tử chứa ngày tháng dựa trên ID "birthDateText"
                var birthDateElement = document.getElementById("birthDateText");

                // Lấy giá trị ngày tháng
                var birthDateValue = birthDateElement.textContent;

                // Chuyển đổi định dạng từ "YYYY-MM-DD" sang "DD-MM-YYYY"
                var dateParts = birthDateValue.split("-");
                if (dateParts.length === 3) {
                    var newDate = dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
                    birthDateElement.textContent = newDate;
                }

            </script>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="assets/js/jquery-3.2.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/app.js"></script>
    </body>
</html>
