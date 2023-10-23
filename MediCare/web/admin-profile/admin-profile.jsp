<%-- 
    Document   : profile
    Created on : Sep 20, 2023, 12:47:01 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <jsp:include page="../admin-general/admin-head.jsp"/>
    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
            <jsp:include page="../admin-general/admin-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-7 col-6">
                            <h4 class="page-title">Hồ sơ của tôi</h4>
                            <h3>${MESSAGE}</h3>
                        </div>

                        <div class="col-sm-5 col-6 text-right m-b-30">
                            <a href="${pageContext.request.contextPath}/admin-edit-profile?save=0" class="btn btn-primary btn-rounded"><i class="fa fa-plus"></i> Sửa hồ sơ</a>
                        </div>
                    </div>
                    <div class="card-box profile-header">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="profile-view">
                                    <div class="profile-img-wrap">
                                        <div class="profile-img">
                                            <!--<a href="#"><img class="avatar" src="../assets/admin/img/doctor-03.jpg" alt=""></a>-->
                                        </div>
                                    </div>
                                    <div class="profile-basic">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <div class="profile-info-left">
                                                    <h3 class="user-name m-t-0 mb-0">${EMPLOYEE.getName()}</h3>
                                                    <!--<small class="text-muted">Gynecologist</small>-->
                                                    <div class="staff-id">ID : ${EMPLOYEE.getId()}</div>
                                                    <!--<div class="staff-msg"><a href="chat.html" class="btn btn-primary">Send Message</a></div>-->
                                                </div>
                                            </div>
                                            <div class="col-md-7">
                                                <ul class="personal-info">
                                                    <li>
                                                        <span class="title">Điện thoại:</span>
                                                        <span class="text">${EMPLOYEE.getPhone()}</span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Email:</span>
                                                        <span class="text">${EMPLOYEE.getEmail()}</span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Ngày sinh:</span>
                                                        <span class="text">${EMPLOYEE.getBirthDate()}</span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Địa chỉ:</span>
                                                        <span class="text">${EMPLOYEE.getAddress()}</span>
                                                    </li>
                                                    <li>
                                                        <span class="title">Giới tính:</span>
                                                        <span class="text">
                                                            <c:if test = "${EMPLOYEE.getGender()==0}">Nam</c:if>
                                                            <c:if test = "${EMPLOYEE.getGender()==1}">Nữ</c:if>
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
<!--                    <div class="profile-tabs">
                        <ul class="nav nav-tabs nav-tabs-bottom">
                            <li class="nav-item"><a class="nav-link active" href="#about-cont" data-toggle="tab">About</a></li>
                            <li class="nav-item"><a class="nav-link" href="#bottom-tab2" data-toggle="tab">Profile</a></li>
                            <li class="nav-item"><a class="nav-link" href="#bottom-tab3" data-toggle="tab">Messages</a></li>
                        </ul>

                        <div class="tab-content">
                            <div class="tab-pane show active" id="about-cont">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="card-box">
                                            <h3 class="card-title">Education Informations</h3>
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
                    </div>-->
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.slimscroll.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>
    </body>
</html>
