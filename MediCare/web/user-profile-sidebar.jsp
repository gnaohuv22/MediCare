<%-- 
    Document   : user-profile-sidebar
    Created on : Oct 10, 2023, 12:01:58 AM
    Author     : phuon
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="sidebar">
        <ul>
            <c:set var="req" value="${pageContext.request}" />
            <c:set var="path" value="${req.requestURI}" />
            <c:set var="truePath" value="${fn:substring(path, 1, fn:length(path))}"/>
            <c:forEach items="${sessionScope.subMenu}" var="item">
                <c:if test="${item.getCategoryId() eq 9}">
                    <c:choose>
                        <c:when test="${truePath eq item.getHref()}">
                            <li class="sidebar-active">
                                <a href="${item.getHref()}">${item.getContent()}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li> 
                                <a href="${item.getHref()}">${item.getContent()}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</html>
