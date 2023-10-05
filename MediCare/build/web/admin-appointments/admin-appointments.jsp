<%-- 
    Document   : appointments
    Created on : Sep 20, 2023, 12:18:37 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <jsp:include page="../admin-general/admin-head.jsp" />
       <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/admin-display-table.css">
    </head>
    <body>
        <div class="main-wrapper">
            <%@include file="../admin-general/admin-header.jsp"%>
            <%@include file="../admin-general/admin-sidebar.jsp"%>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-4 col-3">
                            <h4 class="page-title">Đơn đặt khám</h4>
                        </div>
<!--                        <div class="col-sm-8 col-9 text-right m-b-20">
                            <a href="add-appointment.jsp" class="btn btn btn-primary btn-rounded float-right"><i class="fa fa-plus"></i> Add Appointment</a>
                        </div>-->
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-striped custom-table">
                                    <thead>
                                        <tr>
                                            <c:forEach var="title" items="${TITLE_APPOINTMENTS}">
                                                ${title.toString()}
                                            </c:forEach>
<!--                                            <th>ID đơn</th>
                                            <th>Tên bệnh nhân</th>
                                            <th>Tên bác sĩ</th>
                                            <th>Tên dịch vụ</th>
                                            <th>Thời gian tạo</th>
                                            <th>Trạng thái</th>
                                            <th class="text-right">Action</th>-->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="list" items="${ALL_APPOINTMENT}">
                                        <tr name="display-table-tr">
                                            <td>${list.getAppointments().getId()}</td>
                                            <td>${list.getUser().getName()}</td>
                                            <td>${list.getDoctor().getDisplayName()}</td>
                                            <td>${list.getServiceTag().getNametag()}</td>
                                            <td>${list.getAppointments().getPlannedAt()}</td>
                                            <td>${list.getAppointments().getStatus()}</td>
                                            <!--<td><span class="custom-badge status-green">Active</span></td>-->
                                            <td class="text-right">
                                                <div class="dropdown dropdown-action">
                                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <!--<a class="dropdown-item" href="edit-appointment.jsp"><i class="fa fa-pencil m-r-5"></i> Edit</a>-->
                                                        <!--<a class="dropdown-item" href="${pageContext.request.contextPath}/admin-delete-appointment" data-toggle="modal" data-target="#delete_appointment"><i class="fa fa-trash-o m-r-5"></i> Xóa</a>-->
                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin-delete-appointment?id=${list.getAppointments().getId()}"><i class="fa fa-trash-o m-r-5"></i> Xóa</a>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="../admin-general/admin-notifications-box.jsp"%>
<!--                <form action="${pageContext.request.contextPath}/DispatchController">
                <div id="delete_appointment" class="modal fade delete-modal" role="dialog">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-body text-center">
                                <img src="../assets/admin/img/sent.png" alt="" width="50" height="46">
                                <h3>Are you sure want to delete this Appointment?</h3>
                                <h3>Bạn có chắc chắn xóa đơn hàng này không</h3>
                                <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal">Close</a>
                                    <input type="hidden" name="id" value="${list.getAppointments().getId()}">
                                    <button type="submit" class="btn btn-danger" name="btAction" value="Delete appointment">Xóa</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </form>-->
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.slimscroll.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>
        <script>
            $(function () {
                $('#datetimepicker3').datetimepicker({
                    format: 'LT'
                });
                $('#datetimepicker4').datetimepicker({
                    format: 'LT'
                });
            });
        </script>
    </body>
</body>
</html>
