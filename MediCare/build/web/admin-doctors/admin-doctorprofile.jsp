<%-- 
    Document   : admin-doctorprofile
    Created on : Oct 15, 2023, 11:50:30 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <a  class="dropdown-item" href="admin-doctor?action=edit" data-toggle="modal" data-target="#edit_doctor" onclick="setDoctorId(${doc.getId()})"> <i class="fa fa-plus"></i> Edit Profile</a>
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
                                                    <div class="staff-id">Doctor ID : ${requestScope.doc.getId()}</div>

                                                </div>
                                            </div>
                                            <div class="col-md-7">
                                                <ul class="personal-info">
                                                    <li>
                                                        <span class="title">Phone:</span>
                                                        <span class="text"><a href="tel:${requestScope.doc.getPhone()}">${requestScope.doc.getPhone()}</a></span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Email:</span>
                                                        <span class="text"><a href="mailto:${requestScope.doc.getEmail()}">${requestScope.doc.getEmail()}</a></span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Birthday:</span>
                                                        <span class="text" id="birthDateText">${requestScope.doc.getBirthDate()}</span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Work place : </span>
                                                        <span class="text">${requestScope.doc.getWorkplace()}</span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Gender:</span>
                                                        <span class="text" id="genderText">${requestScope.doc.getGender()}</span>
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
                            <li class="nav-item"><a class="nav-link active" href="#about-cont" data-toggle="tab">About</a></li>
                        </ul>

                        <div class="tab-content">
                            <div class="tab-pane show active" id="about-cont">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="card-box">
                                            <h3 class="card-title">Workplace</h3>
                                            <div class="experience-box">
                                                <ul class="experience-list">
                                                    <li>
                                                        <div class="experience-user">
                                                            <div class="before-circle"></div>
                                                        </div>
                                                        <div class="experience-content">
                                                            <div class="timeline-content">
                                                                <a href="#/" class="name">International College of Medical Science (UG)</a>
                                                                <div>MBBS</div>
                                                                <span class="time">2001 - 2003</span>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="experience-user">
                                                            <div class="before-circle"></div>
                                                        </div>
                                                        <div class="experience-content">
                                                            <div class="timeline-content">
                                                                <a href="#/" class="name">International College of Medical Science (PG)</a>
                                                                <div>MD - Obstetrics & Gynaecology</div>
                                                                <span class="time">1997 - 2001</span>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="card-box mb-0">
                                            <h3 class="card-title">Experience</h3>
                                            <div class="experience-box">
                                                <ul class="experience-list">
                                                    <li>
                                                        <div class="experience-user">
                                                            <div class="before-circle"></div>
                                                        </div>
                                                        <div class="experience-content">
                                                            <div class="timeline-content">
                                                                <a href="#/" class="name">Consultant Gynecologist</a>
                                                                <span class="time">Jan 2014 - Present (4 years 8 months)</span>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="experience-user">
                                                            <div class="before-circle"></div>
                                                        </div>
                                                        <div class="experience-content">
                                                            <div class="timeline-content">
                                                                <a href="#/" class="name">Consultant Gynecologist</a>
                                                                <span class="time">Jan 2009 - Present (6 years 1 month)</span>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="experience-user">
                                                            <div class="before-circle"></div>
                                                        </div>
                                                        <div class="experience-content">
                                                            <div class="timeline-content">
                                                                <a href="#/" class="name">Consultant Gynecologist</a>
                                                                <span class="time">Jan 2004 - Present (5 years 2 months)</span>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
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
