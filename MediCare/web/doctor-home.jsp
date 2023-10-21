<%-- 
    Document   : doctor-home
    Created on : Oct 21, 2023, 11:29:57 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ dành cho bác sĩ | MediCare</title>
        <%@include file="doctor-head.jsp" %>
    </head>
    <body>
        <%@include file="doctor-header.jsp" %>

        <!-- Expect: Appointment Schedule in this week -->

        <!-- Expect: Appointment Schedule in this week -->


        <!-- Expect: Recent review -->

        <!-- Expect: Recent review -->


        <!-- Expect: Display news -->
        <div class="news_section layout_padding">
            <div class="container">
                <h1 class="health_taital" style="font-weight: bold">Bài viết nổi bật</h1>
                <p class="health_text">
                    “Đi sâu vào các bài viết sâu sắc từ các chuyên gia chăm sóc sức khỏe hàng đầu, đưa ra những quan điểm mới mẻ về sức khỏe và thể chất.”
                </p>
                <div class="news_section_3 layout_padding">
                    <c:forEach items="${topNews}" var="news">
                        <a class="news-block" href="${pageContext.request.contextPath}/news/${news.getCategory().getHref()}/${news.getSlug()}">
                            <div class="news-cover">
                                <img src="${news.getCoverImage()}" alt="Cover image of ${news.getTitle()}"/>
                            </div>
                            <div class="news-title">
                                ${news.getTitle()}
                            </div>
                            <c:forEach items="${employees}" var="e">
                                <c:if test="${e.getEmail() eq news.getAuthor()}">
                                    <div class="news-author">
                                        Author: ${e.getName()}
                                    </div>
                                </c:if>
                            </c:forEach>

                            <div class="news-create-time">
                                <i class="fas fa-calendar"> </i> <span>${news.getCreatedAt()}</span>
                            </div>
                            <div class="news-view-count">
                                <i class="fas fa-eye"> </i> <span>${news.getViewCount()}</span>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- Expect: Display news -->
        
        <button id="back-to-top" title="Back to top">↑</button>

        <%@include file="doctor-footer.jsp" %>

        <%@include file="doctor-script.jsp" %>
    </body>
</html>
