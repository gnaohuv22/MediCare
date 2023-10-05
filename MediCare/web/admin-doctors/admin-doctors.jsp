<%-- 
    Document   : doctors
    Created on : Sep 20, 2023, 12:15:44 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Models.Doctor" %>
<%@page import="Models.AcademicRank" %>
<%@page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../admin-general/admin-head.jsp" />

    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
            <jsp:include page="../admin-general/admin-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-4 col-3">
                            <h4 class="page-title">Doctors</h4>
                            <c:if test="${not empty requestScope.noti}">
                                <p>${requestScope.noti}</p>
                            </c:if>
                        </div>

                        <div class="col-sm-8 col-9 text-right m-b-20">
                            <a href="admin-adddoctor" class="btn btn-primary btn-rounded float-right"><i class="fa fa-plus"></i> Add Doctor</a>
                        </div>
                    </div>
                    <div class="row doctor-grid">
                        <c:forEach var="doc" items="${list}">
                            <div class="col-md-4 col-sm-4  col-lg-3">
                                <div class="profile-widget">
                                    <div class="doctor-img">
                                        <a class="avatar" href="profile.html"><img alt="" src="${doc.getProfilePicture()}"></a>
                                    </div>
                                    <div class="dropdown profile-action">
                                        <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="admin-editdoctor?id=${doc.getId()}"><i class="fa fa-pencil m-r-5"></i> Edit</a>
                                            <a class="dropdown-item" href="admin-deletedoctor" data-toggle="modal" data-target="#delete_doctor" onclick="showDeleteDialog(${doc.getId()})"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
                                        </div>
                                    </div>
                                    <h4 class="doctor-name text-ellipsis"><a href="profile.html">${doc.getDisplayName()}</a></h4>
                                    <div class="doc-prof">${doc.getARName()}</div>
                                    <div class="doc-prof">${doc.getWorkplace()}</div>
                                    <input type="hidden" value="${doc.getId()}" name="id">
                                </div>
                            </div>
                        </c:forEach>
                    </div>
<!--                    <div class="row">
                        <div class="col-sm-12">
                            <div class="see-all">
                                <a class="see-all-btn" href="javascript:void(0);">Load More</a>
                            </div>
                        </div>
                    </div>-->
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
            <form id ="deleteForm" action="admin-deletedoctor" method="post">
                <div id="delete_doctor" class="modal fade delete-modal" role="dialog">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-body text-center">
                                <img src="../assets/img/sent.png" alt="" width="50" height="46">
                                <h3>Are you sure want to delete this Doctor?</h3>
                                <div class="m-t-20">
                                    <a href="admin-deletedoctor" class="btn btn-white" data-dismiss="modal">Close</a>
                                    <input type="submit" class="btn btn-danger" value="Delete"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <jsp:include page="../admin-general/admin-script.jsp"/>
        <script>
            function showDeleteDialog(doctorId) {
                var deleteForm = document.getElementById('deleteForm')
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'id';
                input.value = doctorId;
                deleteForm.appendChild(input)
            }
        </script>
    </body>
</html>
