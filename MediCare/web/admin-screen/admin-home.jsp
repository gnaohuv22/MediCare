<%-- 
    Document   : home
    Created on : Sep 20, 2023, 12:10:27 AM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.Doctor" %>
<%@page import="Models.AcademicRank" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../admin-general/admin-head.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang nhân viên</title>
    </head>
        
    <body>
            <%@include file="../admin-general/admin-header.jsp"%>
            <%@include file="../admin-general/admin-sidebar.jsp"%>
        <c:if test="${empty EMPLOYEE}">
            Bạn phải là nhân viên để truy cập vào trang này
        </c:if>
        <c:if test="${not empty EMPLOYEE}">
        <div class="main-wrapper">
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                            <div class="dash-widget">
                                <span class="dash-widget-bg1"><i class="fa fa-stethoscope" aria-hidden="true"></i></span>
                                <div class="dash-widget-info text-right">
                                    <h3>${DOCTOR_NUMBER}</h3>
                                    <span class="widget-title1">Bác sĩ <i class="fa fa-check" aria-hidden="true"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                            <div class="dash-widget">
                                <span class="dash-widget-bg2"><i class="fa fa-user-o"></i></span>
                                <div class="dash-widget-info text-right">
                                    <h3>${USER_NUMBER}</h3>
                                    <span class="widget-title2">Người dùng <i class="fa fa-check" aria-hidden="true"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                            <div class="dash-widget">
                                <span class="dash-widget-bg3"><i class="fa fa-user-md" aria-hidden="true"></i></span>
                                <div class="dash-widget-info text-right">
                                    <h3>${EMPLOYEE_NUMBER}</h3>
                                    <span class="widget-title3">Nhân viên <i class="fa fa-check" aria-hidden="true"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                            <div class="dash-widget">
                                <span class="dash-widget-bg4"><i class="fa fa-heartbeat" aria-hidden="true"></i></span>
                                <div class="dash-widget-info text-right">
                                    <h3>618</h3>
                                    <span class="widget-title4">Pending <i class="fa fa-check" aria-hidden="true"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
<!--                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-6 col-xl-6">
                            <div class="card">
                                <div class="card-body">
                                    <div class="chart-title">
                                        <h4>Patient Total</h4>
                                        <span class="float-right"><i class="fa fa-caret-up" aria-hidden="true"></i> 15% Higher than Last Month</span>
                                    </div>	
                                    <canvas id="linegraph"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-6 col-xl-6">
                            <div class="card">
                                <div class="card-body">
                                    <div class="chart-title">
                                        <h4>Patients In</h4>
                                        <div class="float-right">
                                            <ul class="chat-user-total">
                                                <li><i class="fa fa-circle current-users" aria-hidden="true"></i>ICU</li>
                                                <li><i class="fa fa-circle old-users" aria-hidden="true"></i> OPD</li>
                                            </ul>
                                        </div>
                                    </div>	
                                    <canvas id="bargraph"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>-->
                    <div class="row">
<!--                        <div class="col-12 col-md-6 col-lg-8 col-xl-8">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title d-inline-block">Upcoming Appointments</h4> <a href="admin-appointments.jsp" class="btn btn-primary float-right">View all</a>
                                </div>
                                <div class="card-body p-0">
                                    <div class="table-responsive">
                                        <table class="table mb-0">
                                            <thead class="d-none">
                                                <tr>
                                                    <th>Patient Name</th>
                                                    <th>Doctor Name</th>
                                                    <th>Timing</th>
                                                    <th class="text-right">Status</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td style="min-width: 200px;">
                                                        <a class="avatar" href="profile/admin-profile.jsp ">B</a>
                                                        <h2><a href="profile/admin-profile.jsp ">Bernardo Galaviz <span>New York, USA</span></a></h2>
                                                    </td>                 
                                                    <td>
                                                        <h5 class="time-title p-0">Appointment With</h5>
                                                        <p>Dr. Cristina Groves</p>
                                                    </td>
                                                    <td>
                                                        <h5 class="time-title p-0">Timing</h5>
                                                        <p>7.00 PM</p>
                                                    </td>
                                                    <td class="text-right">
                                                        <a href="admin-appointments.jsp" class="btn btn-outline-primary take-btn">Take up</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="min-width: 200px;">
                                                        <a class="avatar" href="profile/admin-profile.jsp ">B</a>
                                                        <h2><a href="profile/admin-profile.jsp ">Bernardo Galaviz <span>New York, USA</span></a></h2>
                                                    </td>                 
                                                    <td>
                                                        <h5 class="time-title p-0">Appointment With</h5>
                                                        <p>Dr. Cristina Groves</p>
                                                    </td>
                                                    <td>
                                                        <h5 class="time-title p-0">Timing</h5>
                                                        <p>7.00 PM</p>
                                                    </td>
                                                    <td class="text-right">
                                                        <a href="admin-appointments.jsp" class="btn btn-outline-primary take-btn">Take up</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="min-width: 200px;">
                                                        <a class="avatar" href="profile/admin-profile.jsp ">B</a>
                                                        <h2><a href="profile/admin-profile.jsp ">Bernardo Galaviz <span>New York, USA</span></a></h2>
                                                    </td>                 
                                                    <td>
                                                        <h5 class="time-title p-0">Appointment With</h5>
                                                        <p>Dr. Cristina Groves</p>
                                                    </td>
                                                    <td>
                                                        <h5 class="time-title p-0">Timing</h5>
                                                        <p>7.00 PM</p>
                                                    </td>
                                                    <td class="text-right">
                                                        <a href="admin-appointments.jsp" class="btn btn-outline-primary take-btn">Take up</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="min-width: 200px;">
                                                        <a class="avatar" href="profile/admin-profile.jsp ">B</a>
                                                        <h2><a href="profile/admin-profile.jsp ">Bernardo Galaviz <span>New York, USA</span></a></h2>
                                                    </td>                 
                                                    <td>
                                                        <h5 class="time-title p-0">Appointment With</h5>
                                                        <p>Dr. Cristina Groves</p>
                                                    </td>
                                                    <td>
                                                        <h5 class="time-title p-0">Timing</h5>
                                                        <p>7.00 PM</p>
                                                    </td>
                                                    <td class="text-right">
                                                        <a href="admin-appointments.jsp" class="btn btn-outline-primary take-btn">Take up</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="min-width: 200px;">
                                                        <a class="avatar" href="profile/admin-profile.jsp ">B</a>
                                                        <h2><a href="profile/admin-profile.jsp ">Bernardo Galaviz <span>New York, USA</span></a></h2>
                                                    </td>                 
                                                    <td>
                                                        <h5 class="time-title p-0">Appointment With</h5>
                                                        <p>Dr. Cristina Groves</p>
                                                    </td>
                                                    <td>
                                                        <h5 class="time-title p-0">Timing</h5>
                                                        <p>7.00 PM</p>
                                                    </td>
                                                    <td class="text-right">
                                                        <a href="appointments.jsp" class="btn btn-outline-primary take-btn">Take up</a>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>-->
                        <div class="col-12 col-md-6 col-lg-4 col-xl-4">
                            <div class="card member-panel">
                                <div class="card-header bg-white">
                                    <h4 class="card-title mb-0">Doctors</h4>
                                </div>
                                <div class="card-body">
                                    <ul class="contact-list">
                                        <%
                                    ArrayList<Doctor> Doctorlist = (ArrayList<Doctor>)request.getAttribute("Doctorlist");
                                    for (Doctor doc : Doctorlist){
                                 %>  
                                        <li>
                                            <div class="contact-cont">
                                                <div class="float-left user-img m-r-10">
                                                    <a href="profile " title="<%=doc.getDisplayName()%>"><img src="<%=doc.getProfilePicture()%>" alt="" class="w-40 rounded-circle"><span class="status online"></span></a>
                                                </div>
                                                <div class="contact-info">
                                                    <span class="contact-name text-ellipsis"><%=doc.getDisplayName()%></span>
                                                    <span class="contact-date"><%=doc.getARName()%></span>
                                                </div>
                                            </div>
                                        </li>
                                        <%}%>
                                    </ul>
                                </div>
                                <div class="card-footer text-center bg-white">
                                    <a href="admin-doctor" class="text-muted">View all Doctors</a>
                                </div>
                            </div>
                        </div>
                    </div>
<!--                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-8 col-xl-8">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title d-inline-block">New Patients </h4> <a href="patients.jsp" class="btn btn-primary float-right">View all</a>
                                </div>
                                <div class="card-block">
                                    <div class="table-responsive">
                                        <table class="table mb-0 new-patient-table">
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <img width="28" height="28" class="rounded-circle" src="../assets/admin/img/user.jpg" alt=""> 
                                                        <h2>John Doe</h2>
                                                    </td>
                                                    <td>Johndoe21@gmail.com</td>
                                                    <td>+1-202-555-0125</td>
                                                    <td><button class="btn btn-primary btn-primary-one float-right">Fever</button></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <img width="28" height="28" class="rounded-circle" src="../assets/admin/img/user.jpg" alt=""> 
                                                        <h2>Richard</h2>
                                                    </td>
                                                    <td>Richard123@yahoo.com</td>
                                                    <td>202-555-0127</td>
                                                    <td><button class="btn btn-primary btn-primary-two float-right">Cancer</button></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <img width="28" height="28" class="rounded-circle" src="../assets/admin/img/user.jpg" alt=""> 
                                                        <h2>Villiam</h2>
                                                    </td>
                                                    <td>Richard123@yahoo.com</td>
                                                    <td>+1-202-555-0106</td>
                                                    <td><button class="btn btn-primary btn-primary-three float-right">Eye</button></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <img width="28" height="28" class="rounded-circle" src="../assets/admin/img/user.jpg" alt=""> 
                                                        <h2>Martin</h2>
                                                    </td>
                                                    <td>Richard123@yahoo.com</td>
                                                    <td>776-2323 89562015</td>
                                                    <td><button class="btn btn-primary btn-primary-four float-right">Fever</button></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-4 col-xl-4">
                            <div class="hospital-barchart">
                                <h4 class="card-title d-inline-block">Hospital Management</h4>
                            </div>
                            <div class="bar-chart">
                                <div class="legend">
                                    <div class="item">
                                        <h4>Level1</h4>
                                    </div>

                                    <div class="item">
                                        <h4>Level2</h4>
                                    </div>
                                    <div class="item text-right">
                                        <h4>Level3</h4>
                                    </div>
                                    <div class="item text-right">
                                        <h4>Level4</h4>
                                    </div>
                                </div>
                                <div class="chart clearfix">
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">16%</span>
                                            <div class="item-progress" data-percent="16">
                                                <span class="title">OPD Patient</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">71%</span>
                                            <div class="item-progress" data-percent="71">
                                                <span class="title">New Patient</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">82%</span>
                                            <div class="item-progress" data-percent="82">
                                                <span class="title">Laboratory Test</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">67%</span>
                                            <div class="item-progress" data-percent="67">
                                                <span class="title">Treatment</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">30%</span>									
                                            <div class="item-progress" data-percent="30">
                                                <span class="title">Discharge</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>-->
                </div>
                <%@include file="../admin-general/admin-notifications-box.jsp"%>
            </div>
        </div>
<!--        <div class="sidebar-overlay" data-reff=""></div>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/dataTables.bootstrap4.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.slimscroll.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>-->
       <jsp:include page="../admin-general/admin-script.jsp"/>
        </c:if>
    </body>
</html>
