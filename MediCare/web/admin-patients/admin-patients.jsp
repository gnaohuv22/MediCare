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
                            <h4 class="page-title">Người dùng</h4>
                        </div>
                        <div class="col-sm-8 col-9 text-right m-b-20">
                            <a href="${pageContext.request.contextPath}/admin-list-user?add-user=true" class="btn btn btn-primary btn-rounded float-right"><i class="fa fa-plus"></i> Thêm người dùng</a>
                        </div>
                    </div>
                    <button class="search-button" id="open-search-form" onclick="openForm()">Lọc</button>
                    <button class="search-button" id="close-search-form" style="display: none" onclick="closeForm()">Lọc</button>
                    <form id="search-form" action="${pageContext.request.contextPath}/admin-list-user?" style="display: none">
                        <input type="hidden" name="search-user" value="true">
                        <div class="row filter-row">
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">ID Người dùng</label>
                                    <input type="text" class="form-control floating" name="searchId" id="searchId" value="${searchId}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Email</label>
                                    <input type="text" class="form-control floating" name="searchEmail" id="searchEmail" value="${searchEmail}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Tên người dùng</label>
                                    <input type="text" class="form-control floating" name ="searchName" id ="searchName" ${searchName}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Ngày sinh</label>
                                    <input type="text" class="form-control floating" name ="searchBirthDate" id ="searchBirthDate" ${searchBirthDate}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Giới tính</label>
                                    <input type="radio" name="searchGender" id="searchGender0" value="0" <c:if test="${searchGender eq '0'}">checked="checked"</c:if>/>Nam
                                    <input type="radio" name="searchGender" id="searchGender1" value="1" <c:if test="${searchGender eq '1'}">checked="checked"</c:if>/>Nữ
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Địa chỉ</label>
                                        <input type="text" class="form-control floating" name="searchAddress" id="searchAddress" value="${searchAddress}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus select-focus">
                                    <label class="focus-label">Tỉnh</label>
                                    <select class="select floating" name="searchProvince" id="searchProvince" style="min-width: 100%">
                                        <option value="all">Tất cả</option>
                                        <c:forEach var="list" items="${ALL_PROVINCE}">
                                            <option value="${list.getId()}" <c:if test="${list.getId() eq searchProvince}">selected</c:if>>${list.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Số CCCD</label>
                                    <input type="text" class="form-control floating" name ="searchIdentity" id ="searchIdentity" ${searchIdentity}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Số BHYT</label>
                                    <input type="text" class="form-control floating" name ="searchMedicalId" id ="searchMedicalId" ${searchMedicalId}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Dân tộc</label>
                                    <input type="text" class="form-control floating" name ="searchEthnic" id ="searchEthnic" ${searchEthnic}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Điện thoại</label>
                                    <input type="text" class="form-control floating" name ="searchPhone" id ="searchPhone" ${searchPhone}">
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <button class="btn btn-success btn-block" name="btAction"> Tìm kiếm </button>
                            </div>
                        </div>
                    </form>
                    <P style="color:red">${MESSAGE}</P>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-border table-striped custom-table datatable mb-0">
                                    <thead>
                                        <tr>
                                            <c:forEach var="title" items="${TITLE_USER}">
                                                ${title.toString()}
                                            </c:forEach>
                                            <th class="text-right">Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="list" items="${ALL_USER}">
                                            <tr name="display-table-tr">
                                                <!--<td><img width="28" height="28" src="../assets/admin/img/user.jpg" class="rounded-circle m-r-5" alt=""> Linda Carpenter</td>-->
                                                <td>${list.getId()}</td>
                                                <td><img src="${list.getProfilePicture()}"></td>
                                                <td>${list.getEmail()}</td>
                                                <!--<td>${list.getPassword()}</td>-->
                                                <td>${list.getName()}</td>
                                                <td>${list.getBirthDate()}</td>
                                                <td>
                                                    <c:if test="${list.getGender()==0}">Nam</c:if>
                                                    <c:if test="${list.getGender()==1}">Nữ</c:if>
                                                    </td>
                                                    <td>${list.getAddress()}</td>
                                                <td>${list.getProvince().getName()}</td>
                                                <td>${list.getIdentity()}</td>
                                                <td>${list.getMedicalId()}</td>
                                                <td>${list.getEthnic()}</td>
                                                <td>${list.getPhone()}</td>
                                                <c:if test="${view_detail}">
                                                    <td>${list.getCreatedAt()}</td>
                                                    <td>${list.getCreateBy()}</td>
                                                    <td>${list.getModifyAt()}</td>
                                                    <td>${list.getModifyBy()}</td>
                                                </c:if>
                                                <td class="text-right">
                                                    <div class="dropdown dropdown-action">
                                                        <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                        <div class="dropdown-menu dropdown-menu-right">
                                                            <a class="dropdown-item" href="admin-list-user?edit-user=true&id=${list.getId()}"><i class="fa fa-pencil m-r-5"></i> Sửa</a>
                                                            <c:if test="${!view_detail}">
                                                                <a class="dropdown-item" href="" id="link">Xem danh sách chi tiết</a>
                                                            </c:if>
                                                            <c:if test="${view_detail}">
                                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin-list-user?<c:if test="${IS_SEARCH==1}">search-user=true</c:if><c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchEmail=${searchEmail}&searchName=${searchName}&searchBirthDate=${searchBirthDate}&searchGender=${searchGender}&searchAddress=${searchAddress}&searchProvince=${searchProvince}&searchIdentity=${searchIdentity}&searchMedicalId=${searchMedicalId}&searchEthnic=${searchEthnic}&searchPhone=${searchPhone}</c:if>">Ẩn danh sách chi tiết</a>
                                                            </c:if>
                                                            <!--<a class="dropdown-item" onclick="return confirm('Bạn có chắc chắn muốn xóa? (Khi xóa thì dữ liệu này cùng những dữ liệu liên kết sẽ biến mất hoàn toàn và không thể khôi phục lại)');" href="#" data-toggle="modal" data-target="#delete_patient"><i class="fa fa-trash-o m-r-5"></i> Delete</a>-->
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
                                <a class="page-link" href="admin-list-user?<c:if test="${view_detail}">view-detail=true&</c:if><c:if test="${IS_SEARCH==1}">search-user=true&</c:if>page=${currentPage - 1}<c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchEmail=${searchEmail}&searchName=${searchName}&searchBirthDate=${searchBirthDate}&searchGender=${searchGender}&searchAddress=${searchAddress}&searchProvince=${searchProvince}&searchIdentity=${searchIdentity}&searchMedicalId=${searchMedicalId}&searchEthnic=${searchEthnic}&searchPhone=${searchPhone}</c:if>"><<</a>
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
                                        <a class="page-link" href="admin-list-user?<c:if test="${view_detail}">view-detail=true&</c:if><c:if test="${IS_SEARCH==1}">search-user=true&</c:if>page=${i}<c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchEmail=${searchEmail}&searchName=${searchName}&searchBirthDate=${searchBirthDate}&searchGender=${searchGender}&searchAddress=${searchAddress}&searchProvince=${searchProvince}&searchIdentity=${searchIdentity}&searchMedicalId=${searchMedicalId}&searchEthnic=${searchEthnic}&searchPhone=${searchPhone}</c:if>">${i}</a>
                                        </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${currentPage lt pageCount}">
                            <li class="page-item">
                                <a class="page-link" href="admin-list-user?<c:if test="${view_detail}">view-detail=true&</c:if><c:if test="${IS_SEARCH==1}">search-user=true&</c:if>page=${currentPage + 1}<c:if test="${IS_SEARCH==1}">&searchId=${searchId}&searchEmail=${searchEmail}&searchName=${searchName}&searchBirthDate=${searchBirthDate}&searchGender=${searchGender}&searchAddress=${searchAddress}&searchProvince=${searchProvince}&searchIdentity=${searchIdentity}&searchMedicalId=${searchMedicalId}&searchEthnic=${searchEthnic}&searchPhone=${searchPhone}</c:if>">>></a>
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
            document.getElementById("link").addEventListener("click", function (e) {
                e.preventDefault();

                viewDetail();
            });
        </script>
        <script>

            function viewDetail() {
                var searchId = document.getElementById("searchId").value;
                var searchEmail = document.getElementById("searchEmail").value;
                var searchName = document.getElementById("searchName").value;
                var searchBirthDate = document.getElementById("searchBirthDate").value;
                var searchGender;
                if (document.getElementById('searchGender0').checked) {
                    searchGender = document.getElementById('searchGender0').value;
                } else {
                    if (document.getElementById('searchGender1').checked) {
                        searchGender = document.getElementById('searchGender1').value;
                    } else {
                        searchGender = '';
                    }
                }
                var searchAddress = document.getElementById("searchAddress").value;
                var province = document.getElementById("searchProvince");
                var searchProvince = province.options[province.selectedIndex].value;
                var searchIdentity = document.getElementById("searchIdentity").value;
                var searchMedicalId = document.getElementById("searchMedicalId").value;
                var searchEthnic = document.getElementById("searchEthnic").value;
                var searchPhone = document.getElementById("searchPhone").value;
                window.location.href = "admin-list-user?view-detail=true&search-user=true&searchId=" + searchId + "&searchEmail=" + searchEmail + "&searchName=" + searchName + "&searchBirthDate=" + searchBirthDate + "&searchGender=" + searchGender + "&searchAddress=" + searchAddress + "&searchProvince=" + searchProvince + "&searchIdentity=" + searchIdentity + "&searchMedicalId=" + searchMedicalId + "&searchEthnic=" + searchEthnic + "&searchPhone=" + searchPhone;
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
