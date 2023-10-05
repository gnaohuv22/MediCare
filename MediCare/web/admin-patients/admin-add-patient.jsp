<%-- 
    Document   : add-patient.jsp
    Created on : Sep 21, 2023, 1:02:09 AM
    Author     : Asus
--%>

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
                        <div class="col-lg-8 offset-lg-2">
                            <h4 class="page-title">Add Patient</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <form>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>First Name <span class="text-danger">*</span></label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Last Name</label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Username <span class="text-danger">*</span></label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Email <span class="text-danger">*</span></label>
                                            <input class="form-control" type="email">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input class="form-control" type="password">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Confirm Password</label>
                                            <input class="form-control" type="password">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Date of Birth</label>
                                            <div class="cal-icon">
                                                <input type="text" class="form-control datetimepicker">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group gender-select">
                                            <label class="gen-label">Gender:</label>
                                            <div class="form-check-inline">
                                                <label class="form-check-label">
                                                    <input type="radio" name="gender" class="form-check-input">Male
                                                </label>
                                            </div>
                                            <div class="form-check-inline">
                                                <label class="form-check-label">
                                                    <input type="radio" name="gender" class="form-check-input">Female
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <label>Address</label>
                                                    <input type="text" class="form-control ">
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6 col-lg-3">
                                                <div class="form-group">
                                                    <label>Country</label>
                                                    <select class="form-control select">
                                                        <option>USA</option>
                                                        <option>United Kingdom</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6 col-lg-3">
                                                <div class="form-group">
                                                    <label>City</label>
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6 col-lg-3">
                                                <div class="form-group">
                                                    <label>State/Province</label>
                                                    <select class="form-control select">
                                                        <option>California</option>
                                                        <option>Alaska</option>
                                                        <option>Alabama</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-6 col-lg-3">
                                                <div class="form-group">
                                                    <label>Postal Code</label>
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Phone </label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Avatar</label>
                                            <div class="profile-upload">
                                                <div class="upload-img">
                                                    <img alt="" src="assets/admin/img/user.jpg">
                                                </div>
                                                <div class="upload-input">
                                                    <input type="file" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="display-block">Status</label>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="patient_active" value="option1" checked>
                                        <label class="form-check-label" for="patient_active">
                                            Active
                                        </label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="patient_inactive" value="option2">
                                        <label class="form-check-label" for="patient_inactive">
                                            Inactive
                                        </label>
                                    </div>
                                </div>
                                <div class="m-t-20 text-center">
                                    <button class="btn btn-primary submit-btn">Create Patient</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="assets/admin/js/popper.min.js"></script>
        <script src="assets/admin/js/bootstrap.min.js"></script>
        <script src="assets/admin/js/jquery.slimscroll.js"></script>
        <script src="assets/admin/js/select2.min.js"></script>
        <script src="assets/admin/js/moment.min.js"></script>
        <script src="assets/admin/js/bootstrap-datetimepicker.min.js"></script>
        <script src="assets/admin/js/app.js"></script>
    </body>
</html>
