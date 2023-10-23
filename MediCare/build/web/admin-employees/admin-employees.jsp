<%-- 
    Document   : employees
    Created on : Sep 20, 2023, 12:28:41 AM
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
                            <h4 class="page-title">Nhân Viên</h4>
                        </div>
                        <div class="col-sm-8 col-9 text-right m-b-20">
                            <a href="${pageContext.request.contextPath}/admin-list-employee?add-employee=true" class="btn btn-primary float-right btn-rounded"><i class="fa fa-plus"></i> Thêm nhân viên</a>
                        </div>
                    </div>
                    <button class="search-button" id="open-search-form" onclick="openForm()">Lọc</button>
                    <button class="search-button" id="close-search-form" style="display: none" onclick="closeForm()">Lọc</button>
                    <form id="search-form" action="${pageContext.request.contextPath}/admin-list-employee?" style="display: none">
                        <input type="hidden" name="search-employee" value="true">
                        <div class="row filter-row">
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">ID nhân viên</label>
                                    <input type="text" class="form-control floating" name="searchId" id="searchId" value="${searchId}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Tên nhân viên</label>
                                    <input type="text" class="form-control floating" name ="searchName" id ="searchName" value="${searchName}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus select-focus">
                                    <label class="focus-label">Quyền</label>
                                    <select class="select floating" name="searchRole" id="searchRole" style="min-width: 100%">
                                        <option value="Select Role">Chọn quyền</option>
                                        <c:forEach var="list" items="${ALL_EMPLOYEEROLE}">
                                            <option value="${list.getId()}" <c:if test="${list.getId() eq searchRole}">selected</c:if>>${list.getRole()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus select-focus" >
                                    <label class="focus-label">Chi nhánh</label>
                                    <select class="select floating" id="searchBranch" name="searchBranch" style="min-width: 100%">
                                        <c:forEach var="list" items="${ALL_BRANCH}">
                                            <option value="${list.getId()}" <c:if test="${list.getId() eq searchBranch}">selected</c:if> >${list.getName()}</option>
                                        </c:forEach>
                                        <option value="" <c:if test="${searchBranch eq ''}">selected</c:if>>Tất cả</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Nhân viên không còn làm</label>
                                        <input type="checkbox" name="isDelete" id="isDelete" value="true" <c:if test="${isDelete eq '1'}">checked="checked"</c:if> />
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <button class="btn btn-success btn-block"> Tìm kiếm </button>
                                </div>
                            </div>
                        </form>
                                    <P style="color:red">${MESSAGE}</P>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-responsive">
                                    <table class="table table-striped custom-table">
                                        <thead>
                                            <tr>
                                            <c:forEach var="title" items="${TITLE_EMPLOYEE}">
                                                ${title.toString()}
                                            </c:forEach>
                                            <th class="text-right">Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <c:forEach var="list" items="${ALL_EMPLOYEE}">
                                            <tr name="display-table-tr">
                                                <td >${list.getId()}</td>
                                                <td>${list.getEmail()}</td>
                                                <td>${list.getBranch().getName()}</td>
                                                <td>${list.getName()}</td>
                                                <td>${list.getBirthDate()}</td>
                                                <td>
                                                    <c:if test="${list.getGender()==0}">Nam</c:if>
                                                    <c:if test="${list.getGender()==1}">Nữ</c:if>
                                                    </td>
                                                    <td>${list.getAddress()}</td>
                                                <td>${list.getWorkplace()}</td>
                                                <td>${list.getProvince().getName()}</td>
                                                <td>${list.getPhone()}</td>
                                                <td>${list.getEthnic()}</td>
                                                <c:if test="${view_detail}">
                                                    <td>${list.getEmployeeRole().getRole()}</td>
                                                    <td>${list.getCreateAt()}</td>
                                                    <td>${list.getCreateBy()}</td>
                                                    <td>${list.getModifyAt()}</td>
                                                    <td>${list.getModifyBy()}</td>
                                                </c:if>
                                                <td class="text-right">
                                                    <div class="dropdown dropdown-action">
                                                        <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                        <div class="dropdown-menu dropdown-menu-right">
                                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin-list-employee?edit-employee=true&id=${list.getId()}"><i class="fa fa-pencil m-r-5"></i> Sửa</a>
                                                            <c:if test="${!view_detail}">
                                                                <a class="dropdown-item" href="" id="link">Xem danh sách chi tiết</a>
                                                            </c:if>
                                                            <c:if test="${view_detail}">
                                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin-list-employee?<c:if test="${IS_SEARCH==1}">search-employee=true&</c:if>searchBranch=${searchBranch}<c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}</c:if>&isDelete=${isDelete}">Ẩn danh sách chi tiết</a>
                                                            </c:if>
                                                            <c:if test="${list.isDelete eq '0'}">
                                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin-list-employee?delete-employee=true&id=${list.getId()}&<c:if test="${view_detail}">view-detail=true&</c:if><c:if test="${IS_SEARCH==1}">search-employee=true&</c:if>searchBranch=${searchBranch}<c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}</c:if>&isDelete=${isDelete}" ><i class="fa fa-trash-o m-r-5"></i> Xóa</a>
                                                            </c:if>
                                                            <c:if test="${list.isDelete eq '1'}">
                                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin-list-employee?restore-employee=true&id=${list.getId()}&<c:if test="${view_detail}">view-detail=true&</c:if><c:if test="${IS_SEARCH==1}">search-employee=true&</c:if>searchBranch=${searchBranch}<c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}</c:if>&isDelete=${isDelete}" >Khôi phục</a>
                                                            </c:if>
                                                            
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                        </c:forEach>


                                        </tr>
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
                                <a class="page-link" href="admin-list-employee?<c:if test="${view_detail}">view-detail=true&</c:if><c:if test="${IS_SEARCH==1}">search-employee=true&</c:if>page=${currentPage - 1}&searchBranch=${searchBranch}<c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}</c:if>&isDelete=${isDelete}"><<</a>

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
                                        <a class="page-link" href="admin-list-employee?<c:if test="${view_detail}">view-detail=true&</c:if><c:if test="${IS_SEARCH==1}">search-employee=true&</c:if>page=${i}&searchBranch=${searchBranch}<c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}</c:if>&isDelete=${isDelete}">${i}</a>

                                        </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${currentPage lt pageCount}">
                            <li class="page-item">
                                <a class="page-link" href="admin-list-employee?<c:if test="${view_detail}">view-detail=true&</c:if><c:if test="${IS_SEARCH==1}">search-employee=true&</c:if>page=${currentPage + 1}&searchBranch=${searchBranch}<c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}</c:if>&isDelete=${isDelete}">>></a> 

                                </li>
                        </c:if>
                    </ul>
                </nav>
                <!-- pagination section -->
                <div><a href="admin-export-employee-list">Lấy file excel</a></div>
                <div><a href="admin-import-employee-list" id="linkExcel" onclick="getNameFile()">Truyền file excel</a></div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
        </div>
        <script>
            document.getElementById("link").addEventListener("click", function (e) {
                e.preventDefault();

                viewDetail();
            });
        </script>
        <script>
            function viewDetail() {
                var searchId = document.getElementById("searchId").value;
                var searchName = document.getElementById("searchName").value;
                var role = document.getElementById("searchRole");
                var searchRole = role.options[role.selectedIndex].value;
                var branch = document.getElementById("searchBranch");
                var searchBranch = branch.options[branch.selectedIndex].value;
                var isDelete = document.getElementById("isDelete").checked;
                window.location.href = "admin-list-employee?view-detail=true&search-employee=true&searchId=" + searchId + "&searchName=" + searchName + "&searchRole=" + searchRole + "&searchBranch=" + searchBranch + "&isDelete=" + isDelete;
            }
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
            function getNameFile(){
                let person = prompt("Mời bạn nhập tên", "Harry Potter");
                let linkExcel = person;
                document.getElementById('linkExcel').setAttribute('value', linkExcel);
            }
        </script>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
    </body>
</html>
