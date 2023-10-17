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
                    <form action="${pageContext.request.contextPath}/admin-list-employee?">
                        <input type="hidden" name="search-employee" value="true">
                    <div class="row filter-row">
                        <div class="col-sm-6 col-md-3">
                            <div class="form-group form-focus">
                                <label class="focus-label">ID nhân viên</label>
                                <input type="text" class="form-control floating" name="searchId" value="${searchId}">
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-3">
                            <div class="form-group form-focus">
                                <label class="focus-label">Tên nhân viên</label>
                                <input type="text" class="form-control floating" name ="searchName" ${searchName}">
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-3">
                            <div class="form-group form-focus select-focus">
                                <label class="focus-label">Quyền</label>
                                <select class="select floating" name="searchRole">
                                    <option value="Select Role">Chọn quyền</option>
                                    <c:forEach var="list" items="${ALL_EMPLOYEEROLE}">
                                        <option value="${list.getId()}" <c:if test="${list.getId() eq searchRole}">selected</c:if>>${list.getRole()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-3">
                            <button class="btn btn-success btn-block" name="btAction" value="Search Employee"> Tìm kiếm </button>
                        </div>
                    </div>
                    </form>
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
                <td>${list.getId()}</td>
                <td>${list.getEmail()}</td>
                <!--<td>${list.getPassword()}</td>-->
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
                <td>${list.getEmployeeRole().getRole()}</td>
                <td>${list.getCreateAt()}</td>
                <td class="text-right">
                    <div class="dropdown dropdown-action">
                        <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin-list-employee?edit-employee=true&id=${list.getId()}"><i class="fa fa-pencil m-r-5"></i> Sửa</a>
			<!--<a class="dropdown-item" onclick="return confirm('Bạn có chắc chắn muốn xóa? (Khi xóa thì dữ liệu này cùng những dữ liệu liên kết sẽ biến mất hoàn toàn và không thể khôi phục lại)');" href="${pageContext.request.contextPath}/admin-delete-employee-controller?id=${list.getId()}" ><i class="fa fa-trash-o m-r-5"></i> Xóa</a>-->
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
                <c:if test="${IS_SEARCH==1}">
                    <a class="page-link" href="admin-list-employee?search-employee=true&page=${currentPage - 1}&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}"><<</a>
                </c:if>
                <c:if test="${IS_SEARCH==0}">
                    <a class="page-link" href="admin-list-employee?page=${currentPage - 1}"><<</a>
                </c:if>    
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
                        <c:if test="${IS_SEARCH==1}">
                        <a class="page-link" href="admin-list-employee?search-employee=true&page=${i}&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}">${i}</a>
                        </c:if>
                        <c:if test="${IS_SEARCH==0}">
                            <a class="page-link" href="admin-list-employee?page=${i}">${i}</a>
                        </c:if>    
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt pageCount}">
            <li class="page-item">
                <c:if test="${IS_SEARCH==1}">
                    <a class="page-link" href="admin-list-employee?search-employee=true&page=${currentPage + 1}&searchId=${searchId}&searchName=${searchName}&searchRole=${searchRole}">>></a>
                </c:if>
                <c:if test="${IS_SEARCH==0}">
                    <a class="page-link" href="admin-list-employee?page=${currentPage + 1}">>></a>
                </c:if>   
            </li>
        </c:if>
    </ul>
</nav>
<!-- pagination section -->
                <div><a href="admin-export-employee-list">Lấy file excel</a></div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
<!--            <div id="delete_employee" class="modal fade delete-modal" role="dialog">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-body text-center">
                            <img src="../assets/admin/img/sent.png" alt="" width="50" height="46">
                            <h3>Are you sure want to delete this Employee?</h3>
                            <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal">Close</a>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>-->
        </div>
<!--        <div>
            <a href="${pageContext.request.contextPath}/admin-export-excel-controller" class="btn btn-success btn-block"> Xuất file Excel (thử nghiệm) </a>
            <input type="text" id="linkExcel" name="linkExcel" value=""/>
            <button onclick="setDomain()">Gán giá trị</button>
        </div>-->
        
        <script>//
//            function setDomain(){
//                let person = prompt("Mời bạn nhập tên của mình", "Harry Potter");
//                let linkExcel = person;
//                document.getElementById('linkExcel').setAttribute('value', linkExcel);
//            }
//        </script>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
<!--        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/dataTables.bootstrap4.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.slimscroll.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>-->
    </body>
</html>
