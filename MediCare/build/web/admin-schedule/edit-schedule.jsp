<%-- 
    Document   : edit-schedule
    Created on : Sep 21, 2023, 1:13:08 AM
    Author     : Asus
--%>

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
                        <div class="col-lg-8 offset-lg-2">
                            <h4 class="page-title">Edit Schedule</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <form>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Doctor Name</label>
                                            <select class="select">
                                                <option>Select</option>
                                                <option>Cristina Groves</option>
                                                <option>Marie Wells</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Available Days</label>
                                            <select class="select" multiple>
                                                <option>Select Days</option>
                                                <option selected>Sunday</option>
                                                <option selected>Monday</option>
                                                <option>Tuesday</option>
                                                <option>Wednesday</option>
                                                <option>Thursday</option>
                                                <option>Friday</option>
                                                <option>Saturday</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Start Time</label>
                                            <div class="time-icon">
                                                <input type="text" class="form-control" id="datetimepicker3">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>End Time</label>
                                            <div class="time-icon">
                                                <input type="text" class="form-control" id="datetimepicker4">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Message</label>
                                    <textarea cols="30" rows="4" class="form-control"></textarea>
                                </div>
                                <div class="form-group">
                                    <label class="display-block">Schedule Status</label>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="product_active" value="option1" checked>
                                        <label class="form-check-label" for="product_active">
                                            Active
                                        </label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="product_inactive" value="option2">
                                        <label class="form-check-label" for="product_inactive">
                                            Inactive
                                        </label>
                                    </div>
                                </div>
                                <div class="m-t-20 text-center">
                                    <button class="btn btn-primary submit-btn">Save</button>
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
        <script src="assets/admin/js/app.js"></script>
        <script>
            $(function () {
                $('#datetimepicker3').datetimepicker({
                    format: 'LT'

                });
            });
        </script>
        <script>
            $(function () {
                $('#datetimepicker4').datetimepicker({
                    format: 'LT'

                });
            });
        </script>
    </body>
</html>
