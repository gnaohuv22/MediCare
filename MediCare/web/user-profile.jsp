<%-- 
    Document   : user-profile
    Created on : Sep 26, 2023, 4:59:54 AM
    Author     : phuon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="user-head.jsp"/>
        <title>Profile</title>
    </head>
    <body>
        <jsp:include page="user-header.jsp"/>
        <main>
            <div class="main-wrapper profile-wrapper">
                <div class="sidebar">
                    <ul>
                        <c:forEach items="${sessionScope.subMenu}" var="item">
                            <c:if test="${item.getCategoryId() eq 9}">
                                <c:choose>
                                    <c:when test="${'user-profile' eq item.getHref()}">
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
                <div class="search-box">
                    <form action="" method="post" class="search-bar">
                        <input placeholder="Tìm nhanh hồ sơ" type="search" name="search-profile" id="search-profile">
                        <input type="hidden" name="method" name="search"/>
                    </form>
                    <div class="profile-list">
                        <ul>
                            <c:forEach items="${requestScope.fpList}" var="fp">
                                <li class="profile-item" onclick="loadProfile(${fp.getProfileId()})" id="${fp.getProfileId()}">
                                    <div class="profile-item-pics ">
                                        <img src="https://www.svgrepo.com/show/497407/profile-circle.svg" width="50px" height="50px" alt="client-img"/> 
                                        <span class="profile-item-pics-relation">Relation</span>
                                    </div>
                                    <div class="profile-item-info">
                                        <h3>${fp.getName()}</h3>
                                        <span>${fp.getBirthDate()}</span>
                                    </div>
                                </li>   
                            </c:forEach>
                        </ul>
                        <button id="addProfileButton" onclick="openAddProfileForm()">Add Profile</button>

                    </div>
                </div>
                <c:set value="${requestScope.currentfp}" var="current"/>
                <div class="profile-display" id="profile-display">

                </div>
            </div>
            <div id="profileForm" class="form-popup">
                <form action="user-profile" class="form-container" method="post">
                    <h3><b>Thêm hồ sơ</b></h3>
                    <!-- Your form fields go here -->
                    <label for="name">Họ và Tên:</label><br/>
                    <input type="text" id="name" name="name" placeholder="Nguyễn Văn A" required=""><br/>

                    <label for="phone">Số điện thoại:</label><br/>
                    <input type="tel" id="phone" name="phone"  placeholder="0xxxxxxxx" pattern="[0]{1}[0-9]{9,10}" required=""><br/>

                    <label for="birthDate">Ngày sinh:</label><br/>
                    <input type="date" id="birthDate" name="birthDate" placeholder="01/01/1990" required=""><br/>
                    <label>Giới tính:</label><br/>
                    <input type="radio" name="gender" id="male" value="Male" required="">
                    <label for="male">Nam</label>

                    <input type="radio" name="gender" id="female" value="Female" required="">
                    <label for="female">Nữ</label><br/>

                    <label for="medicalId">Mã BHYT:</label><br/>
                    <input type="text" id="medicalId" name="medicalId" placeholder="Mã BHYT:"><br/>
                    <label for="identity">Số CMND/CCCD:</label><br/>
                    <input type="text" id="identity" name="identity" placeholder="Số CMND/CCCD:"><br/>
                    <label for="ethnic">Dân tộc:</label><br/>
                    <input type="text" id="ethnic" name="ethnic" placeholder="Kinh"><br/>
                    <label for="email">Email:</label><br/>
                    <input type="text" id="email" name="email" placeholder="example@gmail.com"><br/>
                    <!-- Add more input fields as needed -->
                    <input type="hidden" name="method" id="method" value=""/><br/>
                    <button class="button-container" id="submit-button" type="submit"></button>
                    <button type="button" id="close-button" onclick="closeAddProfileForm()">Close</button>
                </form>
            </div>

        </main>                       
        <jsp:include page="user-footer.jsp"/>
        <jsp:include page="user-script.jsp"/>
        <script>
            $(document).ready(function () {
                // Trigger a click event on the first <li> element
                $(".profile-list li:first").trigger("click");
            });

            function loadProfile(id) {
                $.ajax({
                    url: "/MediCare/load-html?id=" + id + "&method=profile",
                    type: "GET",
                    success: function (data) {
                        var box = document.getElementById("profile-display");
                        box.innerHTML = data;
                        $(".profile-list li").removeClass("profile-item-active");
                        var current = document.getElementById(id);
                        current.className += " profile-item-active";
                    },
                    error: function (jqXHR) {

                    }
                });
            }
            function openAddProfileForm() {
                var modal = document.getElementById("profileForm");
                modal.style.display = "block";
                document.getElementById("method").value = "add";
                document.getElementById("submit-button").innerHTML = "Add";

                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                        document.getElementById("method").value = "add";
                    }
                };
            }

            function closeAddProfileForm() {
                var modal = document.getElementById("profileForm");
                modal.style.display = "none";
                document.getElementById("method").value = "";
                document.getElementById("submit-button").innerHTML = "";
            }


        </script>
    </body>
</html>
