<%-- 
    Document   : breadcrumb
    Created on : Oct 16, 2023, 1:26:32 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <ul class="breadcrumb">
            <c:forEach var="crumb" items="${requestScope.breadcrumbs}">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/${crumb.url}">${crumb.name}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>
