<%-- 
    Document   : add-patient.jsp
    Created on : Sep 21, 2023, 1:02:09 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../admin-general/admin-head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/admin/css/admin-employee.css">

        <script src="${pageContext.request.contextPath}/assets/admin/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <div class="main-wrapper">
            <jsp:include page="../admin-general/admin-header.jsp"/>
            <jsp:include page="../admin-general/admin-sidebar.jsp"/>
            <div class="page-wrapper">
                <div class="content">
                    <c:set var="news" value="${EDIT_NEWS}"></c:set>
                    <c:if test="${edit==1}">
                        <c:set var="error" value="${REGISTER_ERROR}"></c:set>
                            <div class="row">
                                <div class="col-lg-8 offset-lg-2">
                                    <form action="${pageContext.request.contextPath}/admin-list-news" method="POST"> 
                                    <input type="hidden" name="edit-news" value="true">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label>ID</label>
                                                <input class="form-control" type="hidden" name="id" value="${news.id}">
                                                <p>${news.id}</p>
                                            </div>
                                            <p class="error-notice">${error.getIdError()}</p>
                                        </div>
                                        <input class="form-control" type="hidden" name="author" value="${news.author}">
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label>Tiêu đề </label>
                                                <input class="form-control" type="text" name="title" value="${news.title}">
                                            </div>
                                            <p class="error-notice">${error.getTitleError()}</p>
                                        </div>

                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label>Nội dung </label>
                                                <textarea class="form-control" id="content" name="content" value="${news.content}" rows="10">${news.content}</textarea>
                                            </div>
                                            <p class="error-notice">${error.getContentError()}</p>
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label>Thẻ</label>
                                                <select class="select" name="newsCategoryId">
                                                    <c:forEach var="list" items="${ALL_NEWSCATEGORY}">
                                                        <option value="${list.getId()}" <c:if test="${list.getId() eq news.getNewsCategory().getId()}">selected</c:if>>${list.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label>Ảnh</label>
                                                <input class="form-control" type="text" name="coverImage" value="${news.coverImage}" >
                                            </div>
                                            <p class="error-notice">${error.getCoverImageError()}</p>
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label>Subtitle </label>
                                                <input class="form-control" type="text" name="subtitle" value="${news.subtitle}" >
                                            </div>
                                            <p class="error-notice">${error.getSubtitleError()}</p>
                                        </div>
                                    </div>
                                    <div class="m-t-20 text-center">
                                        <button class="btn btn-primary submit-btn" value="Edit News" name="btAction">Sửa bài</button>
                                    </div>
                                    <div class="m-t-20 text-center">
                                        <h3 style="color: red">${MESSAGE}</h3>
                                        <p class="error-notice">${error.getAuthorError()}</p>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${edit==0}">
                    <h1>${news.getTitle()}</h1>
                    <h6>${news.getAuthor()}</h6>
                    <h6>Ngày tạo: ${news.getCreatedAt()}</h6>
                    <h6>Lần chỉnh sửa cuối: ${news.getLastModified()}</h6>
                    <h4>Thể loại: ${news.getNewsCategory().getName()}</h4>
                    <img src="${news.getCoverImage()}">
                    <br>
                    ${news.getContent()}
                </c:if>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script>
            // Replace the <textarea id="content"> with a CKEditor                
            // // instance, using default configuration.                
            CKEDITOR.replace('content');
        </script>    
        <script src="assets/admin/js/jquery-3.2.1.min.js"></script>
        <script src="assets/admin/js/popper.min.js"></script>
        <script src="assets/admin/js/bootstrap.min.js"></script>
        <script src="assets/admin/js/jquery.slimscroll.js"></script>
        <script src="assets/admin/js/select2.min.js"></script>
        <script src="assets/admin/js/moment.min.js"></script>
        <script src="assets/admin/js/bootstrap-datetimepicker.min.js"></script>
        <!--<script src="assets/admin/js/app.js"></script>-->
    </body>
</html>
