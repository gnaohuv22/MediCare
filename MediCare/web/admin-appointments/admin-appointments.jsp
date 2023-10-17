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
                                            <th class="text-right">Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="list" items="${ALL_APPOINTMENT}">
                                        <tr name="display-table-tr">
                                            <td>${list.getId()}</td>
                                            <td>${list.getUser().getName()}</td>
                                            <td>${list.getDoctor().getDisplayName()}</td>
                                            <td>${list.getServiceTag().getNametag()}</td>
                                            <td>${list.getPlannedAt()}</td>
                                            <td>${list.getStatus()}</td>
                                            <!--<td><span class="custom-badge status-green">Active</span></td>-->
                                            <td class="text-right">
                                                <div class="dropdown dropdown-action">
                                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <!--<a class="dropdown-item" href="edit-appointment.jsp"><i class="fa fa-pencil m-r-5"></i> Edit</a>-->
                                                        <!--<a class="dropdown-item" href="${pageContext.request.contextPath}/admin-delete-appointment" data-toggle="modal" data-target="#delete_appointment"><i class="fa fa-trash-o m-r-5"></i> Xóa</a>-->
                                                        <a class="dropdown-item" value="Xoa" onclick="return confirm('Bạn có chắc chắn muốn xóa? (Khi xóa thì dữ liệu này cùng những dữ liệu liên kết sẽ biến mất hoàn toàn và không thể khôi phục lại)');" href="${pageContext.request.contextPath}/admin-list-appointments?delete-appointment=true&id=${list.getId()}"><i class="fa fa-trash-o m-r-5"></i> Xóa</a>
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
                <!-- pagination section -->
<nav aria-label="Pagination">
    <ul class="pagination justify-content-center">
        <c:if test="${currentPage != 1}">
            <li class="page-item">
                <a class="page-link" href="admin-list-appointments?pattern=${pattern}&page=${currentPage - 1}"><<</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${pageCount}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active">
                        <a class="page-link" href="#">${i}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="admin-list-appointments?page=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt pageCount}">
            <li class="page-item">
                <a class="page-link" href="admin-list-appointments?page=${currentPage + 1}">>></a>
            </li>
        </c:if>
    </ul>
</nav>
<!-- pagination section -->
                <%@include file="../admin-general/admin-notifications-box.jsp"%>
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
        <script>
            function confirmDelete(delUrl) {
                if (confirm("Bạn có chắc chắn xóa không ?")) {
                    document.location = delUrl;
                }
            }
        </script>
    </body>
</body>
</html>
