<%-- 
    Document   : patients
    Created on : Sep 20, 2023, 12:17:41 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../admin-general/admin-head.jsp" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/admin-display-table.css">
    </head>
    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
            <jsp:include page="../admin-general/admin-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-4 col-3">
                            <h4 class="page-title">Người dùng</h4>
                        </div>
                        <div class="col-sm-8 col-9 text-right m-b-20">
                            <!--<a href="admin-get-info-register-user" class="btn btn btn-primary btn-rounded float-right"><i class="fa fa-plus"></i> Thêm người dùng</a>-->
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-border table-striped custom-table datatable mb-0">
                                    <thead>
                                        <tr>
                                            <c:forEach var="title" items="${TITLE_USER}">
                                                ${title.toString()}
                                            </c:forEach>
                                            <!--<th >ID</th>-->
                                            <!--<th >email</th>-->
                                            <!--<th >Mật khẩu</th>-->
<!--                                            <th >Tên</th>
                                            <th >Ngày sinh</th>
                                            <th >Giới tính</th>
                                            <th >Địa chỉ</th>
                                            <th >Tỉnh</th>
                                            <th >Căn cước công dân</th>
                                            <th >medicalId</th>
                                            <th >Dân tộc</th>
                                            <th >Điện thoại</th>
                                            <th >Ảnh</th>
                                            <th >Ngày tạo</th>-->
                                            <!--<th class="text-right">Action</th>-->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="list" items="${ALL_USER}">
                                        <tr name="display-table-tr">
                                            <!--<td><img width="28" height="28" src="../assets/admin/img/user.jpg" class="rounded-circle m-r-5" alt=""> Linda Carpenter</td>-->
                                            <td>${list.getUser().getId()}</td>
                                            <td><img src="${list.getUser().getProfilePicture()}"></td>
                                            <td>${list.getUser().getEmail()}</td>
                                            <!--<td>${list.getUser().getPassword()}</td>-->
                                            <td>${list.getUser().getName()}</td>
                                            <td>${list.getUser().getBirthDate()}</td>
                                            <td>
                                                <c:if test="${list.getUser().getGender()==0}">Nam</c:if>
                                                <c:if test="${list.getUser().getGender()==1}">Nữ</c:if>
                                            </td>
                                            <td>${list.getUser().getAddress()}</td>
                                            <td>${list.getProvince().getName()}</td>
                                            <td>${list.getUser().getIdentity()}</td>
                                            <td>${list.getUser().getMedicalId()}</td>
                                            <td>${list.getUser().getEthnic()}</td>
                                            <td>${list.getUser().getPhone()}</td>
                                            <td>${list.getUser().getCreatedAt()}</td>
<!--                                            <td class="text-right">
                                                <div class="dropdown dropdown-action">
                                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <a class="dropdown-item" href="admin-get-user"><i class="fa fa-pencil m-r-5"></i> Sửa</a>
                                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_patient"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
                                                    </div>
                                                </div>
                                            </td>-->
                                        </tr>
                                        </c:forEach>
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
<!--            <div id="delete_patient" class="modal fade delete-modal" role="dialog">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-body text-center">
                            <img src="../assets/admin/img/sent.png" alt="" width="50" height="46">
                            <h3>Are you sure want to delete this Patient?</h3>
                            <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal">Close</a>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>-->
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.slimscroll.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/dataTables.bootstrap4.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>
    </body>
</html>
