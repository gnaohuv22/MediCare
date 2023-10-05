<%-- 
    Document   : blog-details
    Created on : Sep 20, 2023, 12:34:34 AM
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
                        <div class="col-sm-12">
                            <h4 class="page-title">Blog View</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="blog-view">
                                <article class="blog blog-single-post">
                                    <h3 class="blog-title">Do you know the ABCs of Health Care?</h3>
                                    <div class="blog-info clearfix">
                                        <div class="post-left">
                                            <ul>
                                                <li><a href="#."><i class="fa fa-calendar"></i> <span>December 6, 2017</span></a></li>
                                                <li><a href="#."><i class="fa fa-user-o"></i> <span>By Andrew Dawis</span></a></li>
                                            </ul>
                                        </div>
                                        <div class="post-right"><a href="#."><i class="fa fa-comment-o"></i>1 Comment</a></div>
                                    </div>
                                    <div class="blog-image">
                                        <a href="#."><img alt="" src="../assets/admin/img/blog/blog-01.jpg" class="img-fluid"></a>
                                    </div>
                                    <div class="blog-content">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                        <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?</p>
                                        <blockquote>
                                            <p>Vestibulum id ligula porta felis euismod semper. Sed posuere consectetur est at lobortis. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper.</p>
                                        </blockquote>
                                        <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.</p>
                                    </div>
                                </article>
                                <div class="widget blog-share clearfix">
                                    <h3>Share the post</h3>
                                    <ul class="social-share">
                                        <li><a href="#." title="Facebook"><i class="fa fa-facebook"></i></a></li>
                                        <li><a href="#." title="Twitter"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#." title="Linkedin"><i class="fa fa-linkedin"></i></a></li>
                                        <li><a href="#." title="Google Plus"><i class="fa fa-google-plus"></i></a></li>
                                        <li><a href="#." title="Youtube"><i class="fa fa-youtube"></i></a></li>
                                    </ul>
                                </div>
                                <div class="widget author-widget clearfix">
                                    <h3>About author</h3>
                                    <div class="about-author">
                                        <div class="about-author-img">
                                            <div class="author-img-wrap">
                                                <img class="img-fluid rounded-circle" alt="" src="../assets/admin/img/user.jpg">
                                            </div>
                                        </div>
                                        <div class="author-details">
                                            <span class="blog-author-name">Linda Barrett</span>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget blog-comments clearfix">
                                    <h3>Comments (3)</h3>
                                    <ul class="comments-list">
                                        <li>
                                            <div class="comment">
                                                <div class="comment-author">
                                                    <img class="avatar" alt="" src="../assets/admin/img/user.jpg">
                                                </div>
                                                <div class="comment-block">
                                                    <span class="comment-by">
                                                        <span class="blog-author-name">Diana Bailey</span>
                                                        <span class="float-right">
                                                            <span class="blog-reply"><a href="#."><i class="fa fa-reply"></i> Reply</a></span>
                                                        </span>
                                                    </span>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae, gravida pellentesque urna varius vitae. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae. Sed dui lorem, adipiscing in adipiscing et, interdum nec metus. Mauris ultricies, justo eu convallis placerat, felis enim ornare nisi, vitae mattis nulla ante id dui.</p>
                                                    <span class="blog-date">December 6, 2017</span>
                                                </div>
                                            </div>
                                            <ul class="comments-list reply">
                                                <li>
                                                    <div class="comment">
                                                        <div class="comment-author">
                                                            <img class="avatar" alt="" src="../assets/admin/img/user.jpg">
                                                        </div>
                                                        <div class="comment-block">
                                                            <span class="comment-by">
                                                                <span class="blog-author-name">Henry Daniels</span>
                                                                <span class="float-right">
                                                                    <span class="blog-reply"><a href="#."><i class="fa fa-reply"></i> Reply</a></span>
                                                                </span>
                                                            </span>
                                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae, gravida pellentesque urna varius vitae.</p>
                                                            <span class="blog-date">December 6, 2017</span>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="comment">
                                                        <div class="comment-author">
                                                            <img class="avatar" alt="" src="../assets/admin/img/user.jpg">
                                                        </div>
                                                        <div class="comment-block">
                                                            <span class="comment-by">
                                                                <span class="blog-author-name">Diana Bailey</span>
                                                                <span class="float-right">
                                                                    <span class="blog-reply"> <a href="#."><i class="fa fa-reply"></i> Reply</a></span>
                                                                </span>
                                                            </span>
                                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae, gravida pellentesque urna varius vitae.</p>
                                                            <span class="blog-date">December 7, 2017</span>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div class="comment">
                                                <div class="comment-author">
                                                    <img class="avatar" alt="" src="../assets/admin/img/user.jpg">
                                                </div>
                                                <div class="comment-block">
                                                    <span class="comment-by">
                                                        <span class="blog-author-name">Marie Wells</span>
                                                        <span class="float-right">
                                                            <span class="blog-reply"><a href="#."><i class="fa fa-reply"></i> Reply</a></span>
                                                        </span>
                                                    </span>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                                    <span class="blog-date">December 11, 2017</span>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="comment">
                                                <div class="comment-author">
                                                    <img class="avatar" alt="" src="../assets/admin/img/user.jpg">
                                                </div>
                                                <div class="comment-block">
                                                    <span class="comment-by">
                                                        <span class="blog-author-name">Pamela Curtis</span>
                                                        <span class="float-right">
                                                            <span class="blog-reply"><a href="#."><i class="fa fa-reply"></i> Reply</a></span>
                                                        </span>
                                                    </span>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                                    <span class="blog-date">December 13, 2017</span>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="widget new-comment clearfix">
                                    <h3>Leave Comment</h3>
                                    <form>
                                        <div class="row">
                                            <div class="col-sm-8">
                                                <div class="form-group">
                                                    <label>Name <span class="text-red">*</span></label>
                                                    <input type="text" class="form-control">
                                                </div>
                                                <div class="form-group">
                                                    <label>Your email address <span class="text-red">*</span></label>
                                                    <input type="email" class="form-control">
                                                </div>
                                                <div class="form-group">
                                                    <label>Comments</label>
                                                    <textarea rows="4" class="form-control"></textarea>
                                                </div>
                                                <div class="comment-submit">
                                                    <input type="submit" value="Submit" class="btn">
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <aside class="col-md-4">
                            <div class="widget search-widget">
                                <h5>Blog Search</h5>
                                <form class="search-form">
                                    <div class="input-group">
                                        <input type="text" placeholder="Search..." class="form-control">
                                        <div class="input-group-append">
                                            <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="widget post-widget">
                                <h5>Latest Posts</h5>
                                <ul class="latest-posts">
                                    <li>
                                        <div class="post-thumb">
                                            <a href="blog-details.html">
                                                <img class="img-fluid" src="../assets/admin/img/blog/blog-thumb-01.jpg" alt="">
                                            </a>
                                        </div>
                                        <div class="post-info">
                                            <h4>
                                                <a href="blog-details.html">Lorem ipsum dolor sit amet consectetur</a>
                                            </h4>
                                            <p><i aria-hidden="true" class="fa fa-calendar"></i> December 6, 2017</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="post-thumb">
                                            <a href="blog-details.html">
                                                <img class="img-fluid" src="../assets/admin/img/blog/blog-thumb-02.jpg" alt="">
                                            </a>
                                        </div>
                                        <div class="post-info">
                                            <h4>
                                                <a href="blog-details.html">Lorem ipsum dolor sit amet consectetur</a>
                                            </h4>
                                            <p><i aria-hidden="true" class="fa fa-calendar"></i> December 6, 2017</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="post-thumb">
                                            <a href="blog-details.html">
                                                <img class="img-fluid" src="../assets/admin/img/blog/blog-thumb-03.jpg" alt="">
                                            </a>
                                        </div>
                                        <div class="post-info">
                                            <h4>
                                                <a href="blog-details.html">Lorem ipsum dolor sit amet consectetur</a>
                                            </h4>
                                            <p><i aria-hidden="true" class="fa fa-calendar"></i> December 6, 2017</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="post-thumb">
                                            <a href="blog-details.html">
                                                <img class="img-fluid" src="../assets/admin/img/blog/blog-thumb-04.jpg" alt="">
                                            </a>
                                        </div>
                                        <div class="post-info">
                                            <h4>
                                                <a href="blog-details.html">Lorem ipsum dolor sit amet consectetur</a>
                                            </h4>
                                            <p><i aria-hidden="true" class="fa fa-calendar"></i> December 6, 2017</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="widget category-widget">
                                <h5>Blog Categories</h5>
                                <ul class="categories">
                                    <li><a href="#."><i class="fa fa-long-arrow-right"></i> Lorem ipsum dolor</a></li>
                                    <li><a href="#."><i class="fa fa-long-arrow-right"></i> Lorem ipsum dolor</a></li>
                                    <li><a href="#."><i class="fa fa-long-arrow-right"></i> Lorem ipsum dolor</a></li>
                                    <li><a href="#."><i class="fa fa-long-arrow-right"></i> Lorem ipsum dolor</a></li>
                                    <li><a href="#."><i class="fa fa-long-arrow-right"></i> Lorem ipsum dolor</a></li>
                                    <li><a href="#."><i class="fa fa-long-arrow-right"></i> Lorem ipsum dolor</a></li>
                                </ul>
                            </div>
                            <div class="widget tags-widget">
                                <h5>Tags</h5>
                                <ul class="tags">
                                    <li><a href="#." class="tag">Heart</a></li>
                                    <li><a href="#." class="tag">Cancer</a></li>
                                    <li><a href="#." class="tag">Kids</a></li>
                                    <li><a href="#." class="tag">Family</a></li>
                                    <li><a href="#." class="tag">Medical</a></li>
                                    <li><a href="#." class="tag">Injection</a></li>
                                    <li><a href="#." class="tag">Secure</a></li>
                                    <li><a href="#." class="tag">Insurance</a></li>
                                    <li><a href="#." class="tag">Doctor</a></li>
                                    <li><a href="#." class="tag">Nurse</a></li>
                                </ul>
                            </div>
                        </aside>
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
        <script src="${pageContext.request.contextPath}/assets/admin/js/app.js"></script>
    </body>
</html>
