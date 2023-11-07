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
        <title>Quản lý tin tức</title>
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
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <c:if test="${add_news}">
                                <h4 class="page-title">Viết tin</h4>
                            </c:if>
                            <c:if test="${edit_news}">
                                <h4 class="page-title">Sửa tin</h4>
                            </c:if>
                        </div>
                    </div>

                    <c:set var="error" value="${REGISTER_ERROR}"></c:set>
                        <div class="row">
                            <div class="col-lg-8 offset-lg-2">
                                <form action="${pageContext.request.contextPath}/admin-list-news" method="POST"> 
                                <c:if test="${add_news}">
                                    <input type="hidden" name="add-news" value="true">
                                    <c:set var="news" value="${ADD_NEWS}"/>
                                    <c:set var="error" value="${REGISTER_ERROR}"></c:set>
                                </c:if>
                                <c:if test="${edit_news}">
                                    <input type="hidden" name="edit-news" value="true">
                                    
                                    <c:set var="news" value="${EDIT_NEWS}"/>
                                    <c:set var="error" value="${EDIT_ERROR}"></c:set>
                                    <input type="hidden" name="id" value="${news.id}">
                                </c:if>
                                <input type="hidden" name="add-news" value="true">
                                <div class="row">
                                    
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
                                                    <option value="${list.getId()}" <c:if test="${list.getId() eq news.category.id}">selected</c:if>>${list.getName()}</option>
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
                                    <c:if test="${add_news}">
                                    <button class="btn btn-primary submit-btn" >Đăng kí</button>
                                    </c:if>
                                    
                                    <c:if test="${edit_news}">
                                        <button class="btn btn-primary submit-btn">Lưu</button>
                                    </c:if>
                                   
                                </div>
                                <div class="m-t-20 text-center">
                                    <h3 style="color: red">${MESSAGE}</h3>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>                        
        <script>
            // Replace the <textarea id="content"> with a CKEditor                
            // // instance, using default configuration.                
            CKEDITOR.replace('content');
        </script>  
        <!--        <script>
                ClassicEditor
                .create( document.querySelector( '#content' ) )
                .catch( error => {
                    console.error( error );
                } );
                </script>-->
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
