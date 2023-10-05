<%-- 
    Document   : email-settings
    Created on : Sep 20, 2023, 11:50:45 PM
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
                        <div class="col-lg-8 offset-lg-2">
                            <form>
                                <div class="form-group">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="mailoption" id="phpmail" value="option1">
                                        <label class="form-check-label" for="phpmail">PHP Mail</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="mailoption" id="smtpmail" value="option2">
                                        <label class="form-check-label" for="smtpmail">SMTP</label>
                                    </div>
                                </div>
                                <h4 class="page-title">PHP Email Settings</h4>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Email From Address</label>
                                            <input class="form-control" type="email">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Emails From Name</label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>
                                <h4 class="page-title m-t-30">SMTP Email Settings</h4>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>SMTP HOST</label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>SMTP USER</label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>SMTP PASSWORD</label>
                                            <input class="form-control" type="password">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>SMTP PORT</label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>SMTP Security</label>
                                            <select class="select">
                                                <option>None</option>
                                                <option>SSL</option>
                                                <option>TLS</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>SMTP Authentication Domain</label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 m-t-20 text-center">
                                    <button type="button" class="btn btn-primary submit-btn">Save</button>
                                </div>
                            </form>
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
        <script src="${pageContext.request.contextPath}/assets/admin/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>
    </body>
</html>
