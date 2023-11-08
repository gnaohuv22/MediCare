<%-- 
    Document   : roles-permissions
    Created on : Sep 20, 2023, 11:49:49 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <jsp:include page="../admin-general/admin-head.jsp" />

    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
             <jsp:include page="adminSetting-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-8">
                            <h4 class="page-title">Roles & Permissions</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4 col-md-4 col-lg-4 col-xl-3">
                            <a href="add-role.html" class="btn btn-primary btn-block"><i class="fa fa-plus"></i> Add Roles</a>
                            <div class="roles-menu">
                                <ul>
                                    <li class="active">
                                        <a href="javascript:void(0);">Administrator</a>
                                        <span class="role-action">
                                            <a href="edit-role.html">
                                                <span class="action-circle large">
                                                    <i class="material-icons">edit</i>
                                                </span>
                                            </a>
                                            <a href="#" data-toggle="modal" data-target="#delete_role">
                                                <span class="action-circle large delete-btn">
                                                    <i class="material-icons">delete</i>
                                                </span>
                                            </a>
                                        </span>
                                    </li>
                                    <li><a href="#">Doctor</a></li>
                                    <li><a href="#">Nurse</a></li>
                                    <li><a href="#">Laboratorist</a></li>
                                    <li><a href="#">Pharmacist</a></li>
                                    <li><a href="#">Accountant</a></li>
                                    <li><a href="#">Receptionist</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-8 col-md-8 col-lg-8 col-xl-9">
                            <h6 class="card-title m-b-20">Module Access</h6>
                            <div class="m-b-30">
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        Employee
                                        <div class="material-switch float-right">
                                            <input id="staff_module" type="checkbox">
                                            <label for="staff_module" class="badge-success"></label>
                                        </div>
                                    </li>
                                    <li class="list-group-item">
                                        Holidays
                                        <div class="material-switch float-right">
                                            <input id="holidays_module" type="checkbox">
                                            <label for="holidays_module" class="badge-success"></label>
                                        </div>
                                    </li>
                                    <li class="list-group-item">
                                        Leave Request
                                        <div class="material-switch float-right">
                                            <input id="leave_module" type="checkbox">
                                            <label for="leave_module" class="badge-success"></label>
                                        </div>
                                    </li>
                                    <li class="list-group-item">
                                        Events
                                        <div class="material-switch float-right">
                                            <input id="events_module" type="checkbox">
                                            <label for="events_module" class="badge-success"></label>
                                        </div>
                                    </li>
                                    <li class="list-group-item">
                                        Chat
                                        <div class="material-switch float-right">
                                            <input id="chat_module" type="checkbox">
                                            <label for="chat_module" class="badge-success"></label>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped custom-table">
                                    <thead>
                                        <tr>
                                            <th>Module Permission</th>
                                            <th class="text-center">Read</th>
                                            <th class="text-center">Write</th>
                                            <th class="text-center">Create</th>
                                            <th class="text-center">Delete</th>
                                            <th class="text-center">Import</th>
                                            <th class="text-center">Export</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Employee</td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Holidays</td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Leave Request</td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Events</td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                            <td class="text-center">
                                                <input type="checkbox" checked="">
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
            <div id="delete_role" class="modal fade delete-modal" role="dialog">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-body text-center">
                            <img src="../assets/admin/img/sent.png" alt="" width="50" height="46">
                            <h3>Are you sure want to delete this Role?</h3>
                            <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal">Close</a>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
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
