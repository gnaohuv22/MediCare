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
                        <div class="col-sm-3">
                            <h4 class="page-title">Doctors</h4>
                            <c:if test="${not empty requestScope.noti}">
                                <p>${requestScope.noti}</p>
                            </c:if>
                        </div>
                        <form id="myForm" action="admin-doctor">
                            <div class="col-sm-5">
                                <div class="form-group">
                                    <label>Doctor Status <span class="text-danger">*</span></label>
                                    <select id="isDelete" name="isDelete" required class="form-control">
                                        <option value="0" <% if ("0".equals(request.getParameter("isDelete"))) { %>selected<% } %>>Active</option>
                                        <option value="1"<% if ("1".equals(request.getParameter("isDelete"))) { %>selected<% } %>>Deleted</option>
                                        <option value=""<% if ("".equals(request.getParameter("isDelete"))) { %>selected<% } %>>All</option>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-6 col-6 text-right m-b-20">
                        <a href="admin-doctor?action=add" class="btn btn-primary btn-rounded float-right"><i class="fa fa-plus"></i> Add Doctor</a>
                    </div>
                </div>
                <div class ="row">
                    <form action="admin-doctor" method="get">
                        <div class="col-sm-6 col-5">
                            Search doctor: <input type="text" name="search">
                            <input type="submit" value="search">
                        </div>

                    </form>

                </div>
                <div class="row doctor-grid">
                    <c:forEach var="doc" items="${listPaging}">
                        <div class="col-md-4 col-sm-4  col-lg-3">
                            <div class="profile-widget">
                                <div class="doctor-img">
                                    <a class="avatar" href="#" onclick="redirectToDoctorProfile(${doc.getId()})">
                                        <img alt="" src="${doc.getProfilePicture()}">
                                    </a>
                                </div>
                                <div class="dropdown profile-action">
                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#edit_doctor" onclick="setDoctorId(${doc.getId()})"><i class="fa fa-pencil m-r-5"></i> Edit</a>
                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_doctor" onclick="showDeleteDialog(${doc.getId()})"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
                                    </div>
                                </div>
                                <h4 class="doctor-name text-ellipsis"><a href="profile.html">${doc.getDisplayName()}</a></h4>
                                <div class="doc-prof">${doc.getARName()}</div>
                                <div class="doc-prof">${doc.getWorkplace()}</div>
                                <input type="hidden" value="${doc.getId()}" name="id">
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-lg-12" style="display: flex ;flex-direction: row; justify-content: center">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="admin-doctor?isDelete=${requestScope.isDelete}&index=${requestScope.previous}">Previous</a></li>
                                <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                                <li class="page-item"><a class="page-link" href="admin-doctor?isDelete=${requestScope.isDelete}&index=${i}">${i}</a></li>
                                </c:forEach>
                            <li class="page-item"><a class="page-link" href="admin-doctor?isDelete=${requestScope.isDelete}&index=${requestScope.after}">Next</a></li>
                        </ul> 
                    </div>
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
        <form id="editForm" action="admin-doctor" method="get">
            <input type="hidden" name="id" value="">
            <input type="hidden" name="action" value="edit">
        </form>
        <form id="doctorProfileForm" action="admin-doctor" method="get">
            <input type="hidden" name="id" value="">
            <input type="hidden" name="action" value="profile">
        </form>
        <form id ="deleteForm" action="admin-doctor" method="post">
            <input type="hidden" name="action" value="delete">
            <div id="delete_doctor" class="modal fade delete-modal" role="dialog">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-body text-center">
                            <img src="../assets/img/sent.png" alt="" width="50" height="46">
                            <h3>Are you sure want to delete this Doctor?</h3>
                            <div class="m-t-20">
                                <a href="admin-doctor" class="btn btn-white" data-dismiss="modal">Close</a>
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
    <script>
        function setDoctorId(doctorId) {
            var editForm = document.getElementById('editForm');
            var idInput = editForm.querySelector('input[name="id"]');
            idInput.value = doctorId;
            editForm.submit();
        }
    </script>
    <script>
        function redirectToDoctorProfile(doctorId) {
            var doctorProfileForm = document.getElementById('doctorProfileForm');
            var idInput = doctorProfileForm.querySelector('input[name="id"]');
            idInput.value = doctorId;
            console.log(doctorId);
            console.log(idInput.value);
            doctorProfileForm.submit();
        }
    </script>
    <script>
        // Lắng nghe sự kiện khi thay đổi giá trị trong thẻ select
        document.getElementById("isDelete").addEventListener("change", function () {
            // Lấy giá trị đã chọn
            var selectedValue = this.value;

            // Tự động gửi biểu mẫu khi có sự thay đổi
            document.getElementById("myForm").submit();
        });
    </script>
</body>
</html>
