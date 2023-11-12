<%-- 
    Document   : user-profile
    Created on : Sep 26, 2023, 4:59:54 AM
    Author     : phuon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="user-head.jsp"/>
        <title>Danh sách hồ sơ | MediCare</title>
    </head>
    <body>
        <jsp:include page="user-header.jsp"/>
        <main>
            <div class="main-wrapper profile-wrapper">
                <div class="sidebar">
                    <ul>
                        <c:forEach items="${sessionScope.profileSidebar}" var="item">
                            <c:choose>
                                <c:when test="${'user-profile' eq item.getHref()}">
                                    <li class="sidebar-active">
                                        <a href="#">${item.getName()}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li> 
                                        <a href="${item.getHref()}">${item.getName()}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
                <div class="search-box">
                    <form action="" method="post" class="search-bar">
                        <input placeholder="Tìm nhanh hồ sơ" type="search" name="search-profile" id="search-profile">
                        <input type="hidden" name="method" value="search"/>
                    </form>
                    <div class="profile-list">
                        <ul>
                            <c:forEach items="${requestScope.fpList}" var="fp">
                                <li class="profile-item" onclick="loadProfile(${fp.getProfileId()})" id="${fp.getProfileId()}">
                                    <div class="profile-item-pics ">
                                        <c:choose>
                                            <c:when test="${fp.getProfilePicture() != null && fn:length(fp.getProfilePicture()) > 0}">
                                                <img class="profile-pics" src="${fp.getProfilePicture()}" alt="client-img"/>
                                            </c:when>
                                            <c:otherwise>
                                                <img class="profile-pics" src="https://www.svgrepo.com/show/497407/profile-circle.svg" alt="client-img"/> 
                                            </c:otherwise>
                                        </c:choose>
                                        <span class="profile-item-pics-relation">${fp.getRelationship().getRelation()}</span>
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
                <div class="profile-display" id="profile-display">

                </div>
            </div>
            <div id="profileForm" class="form-popup">
                <form action="user-profile" class="form-container" method="post" enctype="multipart/form-data">
                    <h3 id="form-title"></h3>
                    <div class="form-group">
                        <label for="name">Họ và Tên:</label>
                        <input type="text" id="name" name="name" placeholder="Nguyễn Văn A" required>
                    </div>

                    <div class="form-group">
                        <label for="phone">Số điện thoại:</label>
                        <input type="tel" id="phone" name="phone" placeholder="0987654321" pattern="[0]{1}[0-9]{9,10}" required>
                    </div>

                    <div class="form-group">
                        <label for="birthDate">Ngày sinh:</label>
                        <input type="date" id="birthDate" name="birthDate" required>
                    </div>

                    <div class="form-group">
                        <label>Giới tính:</label>
                        <input type="radio" name="gender" id="male" value="Male" required>
                        <label for="male">Nam</label>
                        <input type="radio" name="gender" id="female" value="Female" required>
                        <label for="female">Nữ</label>
                    </div>

                    <div class="form-group">
                        <label for="address">Địa chỉ:</label>
                        <input type="text" id="address" name="address" placeholder="Ex: 123 Nguyễn Chí Thanh" required>
                    </div>

                    <div class="form-group">
                        <label for="medicalId">Mã BHYT:</label>
                        <input type="text" id="medicalId" name="medicalId" placeholder="Mã BHYT">
                    </div>

                    <div class="form-group">
                        <label for="identity">Số CMND/CCCD:</label>
                        <input type="text" id="identity" name="identity" placeholder="Số CMND/CCCD">
                    </div>

                    <div class="form-group">
                        <label for="ethnic">Dân tộc:</label>
                        <input type="text" id="ethnic" name="ethnic" placeholder="Kinh">
                    </div>

                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" id="email" name="email" placeholder="example@gmail.com">
                    </div>

                    <div class="form-group">
                        <label for="relation">Quan hệ:</label>
                        <select id="relation" name="relation" required>
                            <c:forEach items="${requestScope.rList}" var="r">
                                <c:if test="${r.getId() != 0}">
                                    <option value="${r.getId()}">${r.getRelation()}</option>
                                </c:if>
                            </c:forEach>
                        </select><br/>
                    </div>
                    <div class="form-group">
                        <label for="photo">Ảnh đại diện:</label>
                        <input type="file" id="photo" name="photo" accept=".jpg, .png">
                    </div>

                    <input type="hidden" name="method" id="method" value="">
                    <input type="hidden" name="profileId" id="id" value="">

                    <div class="form-buttons">
                        <button type="button" id="close-button" onclick="closeProfileForm()">Đóng</button>
                        <button class="button-container" id="submit-button" type="submit">Submit</button>

                    </div>
                </form>
            </div>
            <div id="deleteForm" class="form-popup">
                <form action="user-profile" class="form-container" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <p>Bạn có chắc chắn muốn xóa hồ sơ?</p>
                    </div>
                    <input type="hidden" name="profileId" id="profileId" value="">
                    <input type="hidden" name="method" id="delete-method" value="delete">
                    <div class="form-buttons">
                        <button type="button" id="delete-close-button" onclick="closeDeleteProfileForm()">Đóng</button>
                        <button class="button-container" id="delete-submit-button" type="submit">Chắc chắn</button>
                    </div>
                </form>
            </div>

        </main>                       
        <jsp:include page="user-footer.jsp"/>
        <button id="back-to-top" title="Back to top">↑</button>
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
                        document.getElementById("profileId").value = id;
                    },
                    error: function (jqXHR) {

                    }
                });
            }

            function openAddProfileForm() {
                var modal = document.getElementById("profileForm");
                modal.style.display = "block";
                document.getElementById("method").value = "add";
                document.getElementById("submit-button").innerHTML = "Thêm hồ sơ mới";
                document.getElementById("form-title").innerHTML = "Thêm hồ sơ mới";
                // Close the modal if the user clicks outside of it
                var selectElement = document.getElementById("relation");
                selectElement.disabled = false;
                $('select').niceSelect('destroy');
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";

                        document.getElementById("method").value = "add";
                    }
                };
            }

            function closeProfileForm() {
                var modal = document.getElementById("profileForm");
                modal.style.display = "none";
                document.getElementById("method").value = "";
                document.getElementById("submit-button").innerHTML = "";
            }

            function closeDeleteProfileForm() {
                var modal = document.getElementById("deleteForm");
                modal.style.display = "none";
            }

            function openEditProfileForm() {
                var modal = document.getElementById("profileForm");
                modal.style.display = "block";
                document.getElementById("method").value = "edit";
                document.getElementById("submit-button").innerHTML = "Cập nhật";
                document.getElementById("form-title").innerHTML = "Thay đổi thông tin";
                $('select').niceSelect('destroy');

                document.getElementById("name").value = document.getElementById("display-name").textContent;
                document.getElementById("phone").value = document.getElementById("display-phone").textContent;
                document.getElementById("birthDate").value = document.getElementById("display-dob").textContent.split("/").reverse().join("-");
                if (document.getElementById("display-dob").textContent === "Male") {
                    document.getElementById("male").checked = true;
                } else {
                    document.getElementById("female").checked = true;
                }
                document.getElementById("address").value = document.getElementById("display-address").textContent === '--' ? '' : document.getElementById("display-address").textContent;
                document.getElementById("medicalId").value = document.getElementById("display-medical-id").textContent === '--' ? '' : document.getElementById("display-medical-id").textContent;
                document.getElementById("identity").value = document.getElementById("display-identity").textContent === '--' ? '' : document.getElementById("display-identity").textContent;
                document.getElementById("ethnic").value = document.getElementById("display-ethnic").textContent === '--' ? '' : document.getElementById("display-ethnic").textContent;
                document.getElementById("email").value = document.getElementById("display-email").textContent === '--' ? '' : document.getElementById("display-email").textContent;
                document.getElementById("id").value = document.getElementById("display-id").textContent;
                // Replace 'myValueToSelect' with the value you want to select.
                var valueToSelect = document.getElementById("display-relation").value;
                if (valueToSelect === '0') {
                    var selectElement = document.getElementById('relation');
                    selectElement.disabled = true;
                } else {
                    var selectElement = document.getElementById("relation");
                    selectElement.disabled = false;
                    for (var i = 0; i < selectElement.options.length; i++) {
                        var option = selectElement.options[i];

                        if (option.value === valueToSelect) {
                            option.selected = true;
                            break;
                        }
                    }
                }





                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                        document.getElementById("method").value = "edit";
                    }
                };
            }

            function openDeleteProfileForm() {
                var modal = document.getElementById("deleteForm");
                modal.style.display = "block";
                document.getElementById("method").value = "delete";
                document.getElementById("form-title").innerHTML = "Xóa hồ sơ";
                // Close the modal if the user clicks outside of it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                        document.getElementById("method").value = "delete";
                    }
                };
            }

        </script>
    </body>
</html>
