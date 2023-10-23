<%-- 
    Document   : holidays
    Created on : Sep 20, 2023, 12:29:55 AM
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
                        <div class="col-sm-5 col-5">
                            <h4 class="page-title">Holidays 2018</h4>
                        </div>
                        <div class="col-sm-7 col-7 text-right m-b-30">
                            <a href="add-holiday.jsp" class="btn btn-primary btn-rounded"><i class="fa fa-plus"></i> Add Holiday</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-striped custom-table mb-0">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Title </th>
                                            <th>Holiday Date</th>
                                            <th>Day</th>
                                            <th class="text-right">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="holiday-completed">
                                            <td>1</td>
                                            <td>New Year</td>
                                            <td>1 Jan 2018</td>
                                            <td>Monday</td>
                                            <td></td>
                                        </tr>
                                        <tr class="holiday-completed">
                                            <td>2</td>
                                            <td>Good Friday</td>
                                            <td>13 Apr 2018</td>
                                            <td>Friday</td>
                                            <td></td>
                                        </tr>
                                        <tr class="holiday-completed">
                                            <td>3</td>
                                            <td>May Day</td>
                                            <td>1 May 2018</td>
                                            <td>Tuesday</td>
                                            <td class="text-center">
                                            </td>
                                        </tr>
                                        <tr class="holiday-completed">
                                            <td>4</td>
                                            <td>Memorial Day</td>
                                            <td>28 May 2018</td>
                                            <td>Monday</td>
                                            <td class="text-center">
                                            </td>
                                        </tr>
                                        <tr class="holiday-completed">
                                            <td>5</td>
                                            <td>Ramzon</td>
                                            <td>26 Jun 2018</td>
                                            <td>Monday</td>
                                            <td></td>
                                        </tr>
                                        <tr class="holiday-upcoming">
                                            <td>6</td>
                                            <td>Bakrid</td>
                                            <td>23 Aug 2018</td>
                                            <td>Wednesday</td>
                                            <td class="text-right">
                                                <div class="dropdown dropdown-action">
                                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <a class="dropdown-item" href="edit-holiday.jsp"><i class="fa fa-pencil m-r-5"></i> Edit</a>
                                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_holiday"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr class="holiday-upcoming">
                                            <td>7</td>
                                            <td>Deepavali</td>
                                            <td>18 Oct 2018</td>
                                            <td>Wednesday</td>
                                            <td class="text-right">
                                                <div class="dropdown dropdown-action">
                                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <a class="dropdown-item" href="edit-holiday.jsp"><i class="fa fa-pencil m-r-5"></i> Edit</a>
                                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_holiday"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr class="holiday-upcoming">
                                            <td>8</td>
                                            <td>Christmas</td>
                                            <td>25 Dec 2018</td>
                                            <td>Tuesday</td>
                                            <td class="text-right">
                                                <div class="dropdown dropdown-action">
                                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <a class="dropdown-item" href="edit-holiday.jsp"><i class="fa fa-pencil m-r-5"></i> Edit</a>
                                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_holiday"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
                                                    </div>
                                                </div>
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
            <div id="delete_holiday" class="modal fade delete-modal" role="dialog">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-body text-center">
                            <img src="../assets/admin/img/sent.png" alt="" width="50" height="46">
                            <h3>Are you sure want to delete this Holiday?</h3>
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
        <script src="${pageContext.request.contextPath}/assets/admin/js/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>
    </body>
</html>
