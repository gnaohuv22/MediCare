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
       <style>
            .search-button {
                background-color: white;
                border: 2px solid #009ce7;
                color: #009ce7;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
            <jsp:include page="../admin-general/admin-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-4 col-3">
                            <h4 class="page-title">Đánh giá</h4>
                        </div>
                    </div>
                    <button class="search-button" id="open-search-form" onclick="openForm()">Lọc</button>
                    <button class="search-button" id="close-search-form" style="display: none" onclick="closeForm()">Lọc</button>
                    <form id="search-form" action="${pageContext.request.contextPath}/admin-list-review" style="display: none">
                        <input type="hidden" name="search-review" value="true">
                        <div class="row filter-row">
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">ID đánh giá</label>
                                    <input type="text" class="form-control floating" placeholder="ID đánh giá" name="searchId" id="searchId" value="${searchId}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus select-focus">
                                    <label class="focus-label">Người dùng</label>
                                    <select class="select floating" name="searchUser" id="searchUser" style="min-width: 100%">
                                        <option value="">Tất cả</option>
                                        <c:forEach var="list" items="${ALL_USER}">
                                            <option value="${list.getId()}" <c:if test="${list.getId() eq searchUser}">selected</c:if>>${list.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus select-focus">
                                    <label class="focus-label">Bác sĩ</label>
                                    <select class="select floating" name="searchDoctor" id="searchDoctor" style="min-width: 100%">
                                        <option value="">Tất cả</option>
                                        <c:forEach var="list" items="${ALL_DOCTOR}">
                                            <option value="${list.getId()}" <c:if test="${list.getId() eq searchDoctor}">selected</c:if>>${list.getDisplayName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus select-focus">
                                    <label class="focus-label">Sắp xếp</label>
                                    <select class="select floating" name="searchOrderBy" id="searchOrderBy" style="min-width: 100%">
                                        <option value="desc" <c:if test="${searchDoctor eq 'desc'}">selected</c:if>>Cao -> thấp</option>
                                        <option value="" <c:if test="${searchDoctor eq ''}">selected</c:if>>Thấp -> cao</option>
                                    </select>
                                </div>
                            </div>
                                <div class="col-sm-6 col-md-3">
                                    <button class="btn btn-success btn-block"> Tìm kiếm </button>
                                </div>
                            </div>
                        </form>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-border table-striped custom-table datatable mb-0">
                                    <thead>
                                        <tr>
                                            <c:forEach var="title" items="${TITLE_REVIEWS}">
                                                ${title.toString()}
                                            </c:forEach>
                                            <th class="text-right">Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="list" items="${ALL_REVIEW}">
                                        <tr>
                                            <!--<td><img width="28" height="28" src="../assets/admin/img/user.jpg" class="rounded-circle m-r-5" alt=""> Linda Carpenter</td>-->
                                            <td>${list.getId()}</td>
                                            <td>${list.getUser().getName()}</td>
                                            <td>${list.getDoctor().getDisplayName()}</td>
                                            <td>${list.getAppointment().getId()}</td>
                                            <td>${list.getRating()}</td>
                                            <td>${list.getReviewContent()}</td>
                                            <td>${list.getCreatedAt()}</td>
                                            <td class="text-right">
                                                <div class="dropdown dropdown-action">
                                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                    <div class="dropdown-menu dropdown-menu-right">
                                                        <!--<a class="dropdown-item" href="${pageContext.request.contextPath}/admin-delete-review?id=${list.getId()}" data-toggle="modal" data-target="#delete_patient"><i class="fa fa-trash-o m-r-5"></i> Xóa</a>-->
                                                        <a class="dropdown-item" onclick="return confirm('Bạn có chắc chắn muốn xóa? (Khi xóa thì dữ liệu này cùng những dữ liệu liên kết sẽ biến mất hoàn toàn và không thể khôi phục lại)');" href="${pageContext.request.contextPath}/admin-delete-review?id=${list.getId()}"><i class="fa fa-trash-o m-r-5"></i> Xóa</a>
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
                <a class="page-link" href="admin-list-review?page=${currentPage - 1}<c:if test="${search_review==1}">&search-review=true&searchId=${searchId}&searchUser=${searchUser}&searchDoctor=${searchDoctor}&searchOrderBy=${searchOrderBy}</c:if>"><<</a>
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
                        <a class="page-link" href="admin-list-review?page=${i}<c:if test="${search_review==1}">&search-review=true&searchId=${searchId}&searchUser=${searchUser}&searchDoctor=${searchDoctor}&searchOrderBy=${searchOrderBy}</c:if>">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt pageCount}">
            <li class="page-item">
                <a class="page-link" href="admin-list-review?page=${currentPage + 1}<c:if test="${search_review==1}">&search-review=true&searchId=${searchId}&searchUser=${searchUser}&searchDoctor=${searchDoctor}&searchOrderBy=${searchOrderBy}</c:if>">>></a>
            </li>
        </c:if>
    </ul>
</nav>
<!-- pagination section -->
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script>
            function openForm() {
                var openButton = document.getElementById("open-search-form");
                var closeButton = document.getElementById("close-search-form");
                var form = document.getElementById("search-form");
                openButton.style.display = "none";
                closeButton.style.display = "block";
                form.style.display = "block";
            }
            function closeForm() {
                var openButton = document.getElementById("open-search-form");
                var closeButton = document.getElementById("close-search-form");
                var form = document.getElementById("search-form");
                openButton.style.display = "block";
                closeButton.style.display = "none";
                form.style.display = "none";
            }
        </script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.slimscroll.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/dataTables.bootstrap4.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap-datetimepicker.min.js"></script>
        <!--<script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>-->
    </body>
</html>
