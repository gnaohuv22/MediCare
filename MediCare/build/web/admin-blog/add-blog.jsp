<%-- 
    Document   : add-blog
    Created on : Sep 20, 2023, 12:35:29 AM
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
                            <h4 class="page-title">Add Blog</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <form>
                                <div class="form-group">
                                    <label>Blog Name</label>
                                    <input class="form-control" type="text">
                                </div>
                                <div class="form-group">
                                    <label>Blog Images</label>
                                    <div>
                                        <input class="form-control" type="file">
                                        <small class="form-text text-muted">Max. file size: 50 MB. Allowed images: jpg, gif, png. Maximum 10 images only.</small>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-3 col-sm-3 col-4 col-lg-3 col-xl-2">
                                            <div class="product-thumbnail">
                                                <img src="../assets/admin/img/blog/blog-thumb-01.jpg" class="img-thumbnail img-fluid" alt="">
                                                <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-4 col-lg-3 col-xl-2">
                                            <div class="product-thumbnail">
                                                <img src="../assets/admin/img/placeholder-thumb.jpg" class="img-thumbnail img-fluid" alt="">
                                                <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-4 col-lg-3 col-xl-2">
                                            <div class="product-thumbnail">
                                                <img src="../assets/admin/img/placeholder-thumb.jpg" class="img-thumbnail img-fluid" alt="">
                                                <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-4 col-lg-3 col-xl-2">
                                            <div class="product-thumbnail">
                                                <img src="../assets/admin/img/placeholder-thumb.jpg" class="img-thumbnail img-fluid" alt="">
                                                <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-4 col-lg-3 col-xl-2">
                                            <div class="product-thumbnail">
                                                <img src="../assets/admin/img/placeholder-thumb.jpg" class="img-thumbnail img-fluid" alt="">
                                                <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-4 col-lg-3 col-xl-2">
                                            <div class="product-thumbnail">
                                                <img src="../assets/admin/img/placeholder-thumb.jpg" class="img-thumbnail img-fluid" alt="">
                                                <span class="product-remove" title="remove"><i class="fa fa-close"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Blog Category</label>
                                            <select class="select">
                                                <option>Health Care</option>
                                                <option>Child</option>
                                                <option>AHealth Care</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Blog Sub Category</label>
                                            <select class="select">
                                                <option>Health Care</option>
                                                <option>Health Care</option>
                                                <option>Health Care</option>
                                                <option>Health Care</option>
                                                <option>Health Care</option>
                                                <option>Health Care</option>
                                                <option>Health Care</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Blog Description</label>
                                    <textarea cols="30" rows="6" class="form-control"></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Tags <small>(separated with a comma)</small></label>
                                    <input type="text" placeholder="Enter your tags" data-role="tagsinput" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label class="display-block">Blog Status</label>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="blog_active" value="option1" checked>
                                        <label class="form-check-label" for="blog_active">
                                            Active
                                        </label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="blog_inactive" value="option2">
                                        <label class="form-check-label" for="blog_inactive">
                                            Inactive
                                        </label>
                                    </div>
                                </div>
                                <div class="m-t-20 text-center">
                                    <button class="btn btn-primary submit-btn">Publish Blog</button>
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
        <script src="${pageContext.request.contextPath}/assets/admin/js/tagsinput.js"></script>
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>s
    </body>
</html>
