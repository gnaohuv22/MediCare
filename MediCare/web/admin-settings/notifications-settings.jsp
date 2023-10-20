<%-- 
    Document   : notifications-settings
    Created on : Sep 21, 2023, 12:04:21 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../admin-general/admin-head.jsp"/>
    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
             <jsp:include page="adminSetting-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <h4 class="page-title">Notifications Settings</h4>
                            <div>
                                <ul class="list-group notification-list">
                                    <li class="list-group-item">
                                        Employee
                                        <div class="material-switch float-right">
                                            <input id="staff_module" type="checkbox" checked="checked">
                                            <label for="staff_module" class="badge-primary"></label>
                                        </div>
                                    </li>
                                    <li class="list-group-item">
                                        Holidays
                                        <div class="material-switch float-right">
                                            <input id="holidays_module" type="checkbox">
                                            <label for="holidays_module" class="badge-primary"></label>
                                        </div>
                                    </li>
                                    <li class="list-group-item">
                                        Leave Request
                                        <div class="material-switch float-right">
                                            <input id="leave_module" type="checkbox">
                                            <label for="leave_module" class="badge-primary"></label>
                                        </div>
                                    </li>
                                    <li class="list-group-item">
                                        Events
                                        <div class="material-switch float-right">
                                            <input id="events_module" type="checkbox">
                                            <label for="events_module" class="badge-primary"></label>
                                        </div>
                                    </li>
                                    <li class="list-group-item">
                                        Chat
                                        <div class="material-switch float-right">
                                            <input id="chat_module" type="checkbox">
                                            <label for="chat_module" class="badge-primary"></label>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
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
