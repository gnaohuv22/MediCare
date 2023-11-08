<%-- 
    Document   : client
    Created on : Sep 16, 2023, 11:28:23 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Đặt lịch hẹn khám cho bệnh nhân | MediCare</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <jsp:include page="user-head.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    </head>

    <body>
        <%@include file="user-header.jsp" %>
        <!-- client section start -->
        
        <!--profile header of a branch start-->
        <div class="branch-profile-header">
            <img src="${pageContext.request.contextPath}/assets/client/images/branch-img.jpg" alt="branch-img" class="branch-image" />
            <p class="branch-name">MediCare - ${requestScope.branch.getName()}</p>
        </div>
        <!--profile header of a branch end-->

        <h2 class="appointment-header-text">Đăng kí khám</h2>
        <div class="booking-container">
            

            <!--Step-1-container - start-->
            <form action="user-book-appointment" method="post">
                <div id="step-1-container">
                    <c:if test="${requestScope.ownerId != null}"> 
                        <!--FOR USER: If logged in, they can choose profile for booking fast: start-->
                        <h2>Chọn khách hàng</h2>
                        <div id="family-profiles-container" class="family-profiles-container">
                            <c:forEach items="${requestScope.profiles}" var="profile">
                                <div class="each-family-profile" data-profile-id="${profile.getProfileId()}" data-ownerID ="${requestScope.ownerId}" onclick="handleProfileClick(this)">
                                    <img src="./assets/client/images/human.png" alt="alt"/>
                                    <span class="relation-each-family-profile">${profile.getRelationship().getRelation()}</span>
                                    <span class="name-each-family-profile">${profile.getName()}</span>
                                </div>
                            </c:forEach>
                            <div id="add-family-profile" class="add-family-profile" data-ownerID ="${requestScope.ownerId}" onclick="openAddProfileForm()">
                                <img src="./assets/client/images/add-icon.png" alt="alt"/>
                            </div>
                        </div>
                        <!--FOR USER: If logged in, they can choose profile for booking fast: end-->
                    </c:if>
                    <h2 class="booking-header">Bước 1/3 - Nhập thông tin người khám</h2>
                    <div class="row booking-info-input">
                        <!-- Cột 1 -->
                        <div class="col-md-6">
                            <div> 
                                <input type="text" name="patientName" id="patientName" placeholder="Họ và tên (*)" required/> 
                                <input type="radio" name="gender" checked value="1"> Nam &nbsp;
                                <input type="radio" name="gender" value="0"> Nữ<br>
                                <p style="color: red;" id="inputNameError" class="error-message-input-step-2"></p>

                                <input type="date" id="birthDate" name="birthDate" placeholder="Ngày sinh (*)" value="" max="2023-10-08" required> (Birth date - Thông tin bắt buộc)<br>
                                <p style="color: red;" id="inputBirthDateError" class="error-message-input-step-2"></p>

                                <input type="tel" id="phone" name="phone" placeholder="Eg: 0123456789 (*)" pattern="[0-9]{10}" required value="" maxlength="10"><br>
                                <p style="color: red;" id="inputPhoneError" class="error-message-input-step-2"></p> 

                                <input type="text" id="email" value="${requestScope.email}" name="email" maxlength="32" placeholder="Vui lòng nhập đúng email để xác nhận thông tin lịch hẹn (*)" required>
                                <p style="color: red;" id="inputEmailError" class="error-message-input-step-2"></p> 
                                <c:if test="${requestScope.ownerId != null}">
                                    <input onclick="getCheckboxValue()" style=" margin:10px;" type="checkbox" id="confirm-edit-profile" name="confirm-edit-profile" value="edittrue"> 
                                    <span onclick="toggleCheckbox()" class="confirm-edit-profile-text">Chỉnh sửa thông tin trong profile như thông tin trên</span>
                                </c:if>

                            </div>
                        </div>

                        <!-- Cột 2 -->
                        <div class="col-md-6">  
                            <div>
                                <textarea id="description" name="description" rows="4" cols="60" placeholder="Vui lòng mô tả rõ triệu chứng của bạn và nhu cầu thăm khám (*)" required></textarea>
                                <p style="color: red;" id="inputSymptomsError" class="error-message-input-step-2"></p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12 mt-3 text-center btn-booking">
                        <div id="step-1-to-2" class="btn btn-primary btn-booking-hover" onclick="handleSubmit(this)">Tiếp tục</div>
                    </div>
                </div>          
                <!--Step-1-container - end-->
                <!--Step-2-container - start-->
                <div id="step-2-container">
                    <h2 class="booking-header">Bước 2/3 - Chọn thông tin khám bệnh</h2>
                    <div class="row">
                        <!-- Cột 1 -->
                        <div class="col-md-6">
                            <div>
                                <select id="branchId" name="branchId" class="booking-select-class" onchange="onchangeBranchId()">
                                    <option value="-1" disabled selected>Chọn cơ sở khám (*)</option>
                                    <c:forEach items="${requestScope.branches}" var="branch">
                                        <option value="${branch.id}">${branch.name}</option>
                                    </c:forEach>
                                </select>
                                <!--<span style="color: red;" id="serviceError" class="error-message-service"></span>-->
                            </div>
                            <div>
                                <select class="booking-select-class" id="serviceId" name="serviceId" onchange="loadDoctors()">
                                    <option value="-1" disabled selected>Chọn chuyên khoa khám (*)</option>
                                    <c:forEach items="${requestScope.services}" var="service">
                                        <option value="${service.id}">${service.nametag}</option>
                                    </c:forEach>
                                </select>
                                <p id="serviceError" class="error-message-input-step-1"></p>
                            </div>
                            <div id="doctor-select-box">
                            </div>
                        </div>

                        <!-- Cột 2 -->
                        <div class="col-md-6">
                            <h2>Thời gian khám</h2>
                            <div>
                                <label for="date-appointment">Ngày khám (*)</label></br>
                                <input style="margin: 10px;" type="date" class="" value="${requestScope.currentDate}" id="booking-calendar" name="booking-calendar" onchange="loadScheduleOfDoctor()">
                            </div>
                            <p id="dateError" class="error-message-input-step-1"></p>
                            <label for="booking-slot">Khung giờ khám (*)</label><br>
                            <div id="slot-select-box">
                            </div>
                            <p id="slotError" class="error-message-input-step-1"></p>
                        </div>
                    </div>

                    <div class="col-md-12 mt-3 text-center btn-booking">
                        <!--<button type="submit" class="btn btn-primary btn-booking-hover">Tiếp tục</button>-->
                        <div id="step-2-to-1" class="btn btn-primary btn-booking-hover" onclick="handleSubmit(this)">Quay lại</div>
                        <div id="step-2-to-3" class="btn btn-primary btn-booking-hover" onclick="handleSubmit(this)">Tiếp tục</div>
                    </div>
                    <!--</form>-->
                </div>
                <!--Step-2-container - end-->



                <!--Step-3-container - start-->
                <div id="step-3-container">

                </div>
            </form>
            <!--Popup - Start-->
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
                    <button type="button" id="close-button" onclick="closeAddProfileForm()">Hủy</button>
                </form>
            </div>
            <!--Popup - End-->
            <!--Popup - Start-->
            <div id="confirm-OTP-Form" class="form-popup">
                <div  class="form-container  text-center">
                    <h3><b>Vui lòng kiểm tra email để lấy mã OTP gồm 6 chữ số!</b></h3>
                    <!-- Your form fields go here -->
                    <label for="name">Mã OTP</label><br/>
                    <input type="text" id="otp" name="otp" placeholder="eg: 123456" required=""><br/>
                    <!--                    <input type="hidden" name="method" id="method" value=""/><br/>-->
                    <button class="button-container" id="submit-otp-button" onclick="onclickSubmitOTP(this)">Xác nhận</button>
                    <!--<button type="button" id="close-button" onclick="closeAddProfileForm()">Close</button>-->
                </div>
            </div>
            <!--Popup - End-->
        </div>
        <!--Step-3-container - end -->



        <button id="back-to-top" title="Back to top">↑</button>
        <%@include file="user-footer.jsp" %>
        <!-- client section end -->

        <!--Create Profile JS - Popup - start-->
        <script>
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

            function toggleCheckbox() {
                var checkbox = document.getElementById("confirm-edit-profile");

                if (checkbox) {
                    checkbox.checked = !checkbox.checked;
                }
                getCheckboxValue();
            }

            // Lấy giá trị của checkbox
            function getCheckboxValue() {
                var checkbox = document.getElementById("confirm-edit-profile");

                if (checkbox) {
                    if (checkbox.checked) {
                        console.log("Checkbox is checked");
                    } else {
                        console.log("Checkbox is not checked");
                    }
                }
            }

            // OTP Submit:
            function onclickSubmitOTP(event) {
                console.log("Event OTP: " + event);
                var otp = document.getElementById("otp").value;
                var step3container = document.getElementById('step-3-container');
                var trueOTP = step3container.getAttribute("data-OTP");

                console.log("Ajax - JS - submit OTP of appointment:");
                var branchId = document.getElementById('branchId').value;
                var serviceId = document.getElementById('serviceId').value;
                var doctorId = document.getElementById('doctorId').value;
                var date = document.getElementById('booking-calendar').value;
                var slotId = document.getElementById('booking-slot').value;

                var patientName = document.getElementById("patientName").value;
                var birthDate = document.getElementById("birthDate").value;
                var genderRadios = document.getElementsByName("gender");
                var gender;
                for (var i = 0; i < genderRadios.length; i++) {
                    if (genderRadios[i].checked) {
                        gender = genderRadios[i].value;
                        break;
                    }
                }
                var phone = document.getElementById("phone").value;
                var email = document.getElementById("email").value;
                var description = document.getElementById("description").value;

                $.ajax({
                    url: "/MediCare/user-book-appointment",
                    data: {
                        branchId: branchId,
                        serviceId: serviceId,
                        doctorId: doctorId,
                        date: date,
                        slotId: slotId,
                        otp: otp,
                        trueOTP: trueOTP,
                        patientName: patientName,
                        birthDate: birthDate,
                        gender: gender,
                        phone: phone,
                        email: email,
                        description: description,
                        action: "submitOTP"
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        console.log("response: " + response);
                        var message = response;
                        console.log("Message from JSON: " + message);
                        if (message === "Đặt lịch khám thành công!") {
                            console.log("message = 'dat lich thanh cong'");

                            setTimeout(function () {
                                window.location.href = "user-home";
                            }, 500);
                        } else {
                            var confirmOTPForm = document.getElementById("confirm-OTP-Form");
                            confirmOTPForm.style.display = 'none';
                            document.getElementById("otp").value = '';
                        }
                        window.alert(message);
                        console.log("success");
                    },
                    error: function (xhr) {
                        console.log("Error: " + xhr);
                    }
                });

            }

        </script>
        <!--Create Profile JS - Popup - end-->

        <!--Profile JS - start-->
        <script>
            function handleProfileClick(element) {
                var profileId = element.getAttribute("data-profile-id");
                var ownerId = element.getAttribute("data-ownerId");
                console.log("Clicked on profile with ID: " + profileId);

                // Loại bỏ lớp active từ tất cả các phần tử có lớp each-family-profile
                var allProfileElements = document.querySelectorAll('.each-family-profile');
                allProfileElements.forEach(function (el) {
                    el.classList.remove('each-family-profile-active');
                });
                // Add active class for this element:
                element.classList.add('each-family-profile-active');

                $.ajax({
                    url: "/MediCare/load-each-family-profile",
                    data: {
                        profileId: profileId,
                        ownerId: ownerId
                    },
                    cache: false,
                    type: "POST",
                    success: function (response) {
                        var profileInfo = document.getElementById("step-1-container");
                        profileInfo.innerHTML = response;
                    },
                    error: function (xhr) {

                    }
                });
            }

            function handleAddProfile(element) {
                console.log("Clicked on profile with ID: " + element);
            }
        </script>
        <!--Profile JS - end-->


        <script>
            var dataLoaded = false;

            // Get current date:
            var today = new Date();

            var dateInput = document.getElementById('booking-calendar');


            // Đặt thuộc tính min của input là ngày hiện tại (để disable các ngày trước ngày hiện tại)
            dateInput.min = today.toISOString().split('T')[0];

            // Danh sách các ngày nghỉ lễ (định dạng là yyyy-mm-dd)
            var holidays = ['2023-09-30', '2023-10-20', '2023-12-25']; // Thêm các ngày nghỉ lễ khác nếu cần

            // Sử dụng sự kiện "input" để kiểm tra ngày mà người dùng chọn
            dateInput.addEventListener('input', function () {
                var selectedDate = dateInput.value;

                // Kiểm tra xem ngày đã chọn có trong danh sách ngày nghỉ lễ không
                if (holidays.includes(selectedDate)) {
                    // Nếu ngày đã chọn là ngày nghỉ lễ, xử lý tùy ý, ví dụ: thông báo lỗi hoặc đặt giá trị trống
                    alert('Ngày này là ngày nghỉ lễ. Vui lòng chọn ngày khác.');
                    dateInput.value = '';
                }
            });

            var birthDateInput = document.getElementById("birthDate");
            birthDateInput.max = today.toISOString().split('T')[0];




            // Get list doctor by serviceId - using ajax:

            function loadDoctors() {
                var serviceId = document.getElementById('serviceId').value;
                var branchId = document.getElementById('branchId').value;
                console.log("serviceId: " + serviceId);
                $.ajax({
                    url: "/MediCare/load-doctors-by-serviceid?branchId=" + branchId + "&serviceid=" + serviceId,
                    data: {
                    },
                    cache: false,
                    type: "GET",
                    success: function (response) {
                        var doctorSelectBox = document.getElementById("doctor-select-box");
                        doctorSelectBox.innerHTML = response;
                    },
                    error: function (xhr) {

                    }
                }).then(function () {
                    var doctorId = document.getElementById('doctorId').value;
                    var date = document.getElementById('booking-calendar').value;
                    console.log("doctorId " + doctorId);
                    var serviceError = document.getElementById("serviceError");
                    serviceError.innerHTML = '';
                    $.ajax({
                        url: "/MediCare/load-schedule-booking?branchId=" + branchId + "&serviceid=" + serviceId + "&doctorId=" + doctorId + "&date=" + date,
                        data: {
                        },
                        cache: false,
                        type: "GET",
                        success: function (response) {
                            var doctorSelectBox = document.getElementById("slot-select-box");
                            doctorSelectBox.innerHTML = response;
                        },
                        error: function (xhr) {
                        }
                    });
                });
                dataLoaded = true;

                //                serviceId = document.getElementById('serviceId').value;
            }

            function loadScheduleOfDoctor() {
                var serviceId = document.getElementById('serviceId').value;
                var doctorId = document.getElementById('doctorId').value;
                var date = document.getElementById('booking-calendar').value;
                var branchId = document.getElementById('branchId').value;

                console.log("doctorId " + doctorId);
                $.ajax({
                    url: "/MediCare/load-schedule-booking?branchId=" + branchId + "&serviceid=" + serviceId + "&doctorId=" + doctorId + "&date=" + date,
                    data: {
                    },
                    cache: false,
                    type: "GET",
                    success: function (response) {
                        var doctorSelectBox = document.getElementById("slot-select-box");
                        doctorSelectBox.innerHTML = response;
                    },
                    error: function (xhr) {

                    }
                });
                dataLoaded = true;

            }
            function loadInfoOfAppointment() {
                console.log("Ajax - JS - load info of appointment:");
                var branchId = document.getElementById('branchId').value;
                var serviceId = document.getElementById('serviceId').value;
                var doctorId = document.getElementById('doctorId').value;
                var date = document.getElementById('booking-calendar').value;
                var slotId = document.getElementById('booking-slot').value;

                var patientName = document.getElementById("patientName").value;
                var birthDate = document.getElementById("birthDate").value;
                var genderRadios = document.getElementsByName("gender");
                var gender;
                for (var i = 0; i < genderRadios.length; i++) {
                    if (genderRadios[i].checked) {
                        gender = genderRadios[i].value;
                        break;
                    }
                }
                var phone = document.getElementById("phone").value;
                var email = document.getElementById("email").value;
                var description = document.getElementById("description").value;

                console.log("doctorId " + doctorId);
                $.ajax({
                    url: "/MediCare/load-info-appointment",
                    data: {
                        branchId: branchId,
                        serviceId: serviceId,
                        doctorId: doctorId,
                        date: date,
                        slotId: slotId,

                        patientName: patientName,
                        birthDate: birthDate,
                        gender: gender,
                        phone: phone,
                        email: email,
                        description: description
                    },
                    cache: false,
                    type: "GET",
                    success: function (response) {
//                        var doctorSelectBox = document.getElementById("slot-select-box");
//                        doctorSelectBox.innerHTML = response;
                        var step3container = document.getElementById("step-3-container");
                        step3container.innerHTML = response;
                        console.log("success");
                    },
                    error: function (xhr) {
                        console.log("Error: " + xhr);
                    }
                });
            }

            function onServiceChange() {
                loadDoctors();
                loadScheduleOfDoctor();
            }

            // Validate for step1-container:
            function validateFormStep1() {
                var branchId = document.getElementById("branchId").value;
                var serviceId = document.getElementById("serviceId").value;
                var serviceError = document.getElementById("serviceError");
                console.log("serviceError");

                console.log("BranchId: " + branchId);
                if (branchId < 0 || serviceId < 0) {
                    console.log("Service Error: ");
                    serviceError.innerHTML = "* Thông tin bắt buộc";
                }

                var serviceId = document.getElementById("serviceId").value;
                console.log("ServiceId " + serviceId);
                var slotId = document.getElementById("booking-slot").value;
                console.log("SlotId " + slotId);

                var dateError = document.getElementById("dateError");
                // Lấy ngày hiện tại
                var today = new Date();
                today.setDate(today.getDate() - 1);
//                var today = new Date(Date.now() + new Date().getTimezoneOffset() * 60000);

                // Lấy giá trị của ngày từ input
                var selectedDate = new Date(dateInput.value);
                // Kiểm tra nếu ngày đã chọn nhỏ hơn ngày hiện tại
                if (selectedDate < today) {
                    dateError.innerHTML = "* Ngày không thể nhỏ hơn ngày hiện tại.";
                    return false;
                } else {
                    dateError.innerHTML = ''; // Xóa thông báo lỗi nếu ngày hợp lệ
                }
                if (dataLoaded) {
                    console.log("dataloaded: TRUE");
                    var slotError = document.getElementById("slotError");
                    if (slotId < 0) {
                        console.log("Slot Error: ");
                        slotError.innerHTML = "* Thông tin bắt buộc";
                    }
                    if (branchId > 0 && serviceId > 0 && slotId > 0) {
                        return true;
                    }
                } else {
                    console.log("dataloaded: False");

                }
                return false;
            }

            // Validate for step2-container:
            function validateFormStep2() {
                var patientName = document.getElementById("patientName").value;
                var name = document.getElementById("patientName");
                var birthDate = document.getElementById("birthDate").value;
                var birthDate2 = document.getElementById("birthDate");
                var phone = document.getElementById("phone").value;
                var phone1 = document.getElementById("phone");
                var description = document.getElementById("description").value;
                var email = document.getElementById("email");

                var inputNameError = document.getElementById("inputNameError");
                var inputBirthDateError = document.getElementById("inputBirthDateError");
                var inputPhoneError = document.getElementById("inputPhoneError");
                var inputSymptomsError = document.getElementById("inputSymptomsError");
                var inputEmailError = document.getElementById("inputEmailError");

                console.log("patient name: " + patientName);
                console.log("Birth date: " + birthDate);
                console.log("Phone: " + phone);
                console.log("Description: " + description);

                if (patientName === '') {
                    console.log("patient name === ''");
                    inputNameError.innerHTML = "* Thông tin bắt buộc";
                } else {
                    if (!/^[a-zA-Z0-9\s-àáảãạăắằẳẵặâấầẩẫậèéẻẽẹêếềểễệđòóỏõọôốồổỗộơớờởỡợùúủũụưứừửữựìíỉĩịỳýỷỹỵ]+$/i.test(name.value.trim())) {
                        name.setCustomValidity("Tên chỉ được chứa chữ cái, số, khoảng trắng và dấu gạch ngang.");
                        name.reportValidity();
                        return false;
                    } else {
                        name.setCustomValidity("");
                    }
                }
                if (birthDate === '') {
                    console.log("birth date === ''");
                    inputBirthDateError.innerHTML = "* Thông tin bắt buộc";
                }

                if (phone === '') {
                    console.log("phone === ''");
                    inputPhoneError.innerHTML = "* Thông tin bắt buộc";
                } else {
                    if (!/^\d{10}$/.test(phone1.value.trim())) {
                        phone1.setCustomValidity("Số điện thoại chỉ được chứa số và phải có đúng 10 chữ số.");
                        phone1.reportValidity();
                        return false;
                    } else {
                        phone1.setCustomValidity("");
                    }
                }
                if (email.value === '') {
                    console.log("email === ''");
                    inputEmailError.innerHTML = "* Thông tin bắt buộc";
                } else {
                    if (!/^([a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z]{2,})+)*$/.test(email.value.trim())) {
                        email.setCustomValidity("Địa chỉ email không hợp lệ, vui lòng nhập lại.");
                        email.reportValidity();
                        return false;
                    } else {
                        email.setCustomValidity("");
                    }
                }

                if (description === '') {
                    console.log("Description === ''");
                    inputSymptomsError.innerHTML = "* Thông tin bắt buộc";
                }
                if (patientName === '' || birthDate === '' || phone === '' || description === '' || email.value === '') {
                    return false;
                }


                return true;
            }


            // Validate before submit:
            function validateForm() {
                var branchId = document.getElementById("branchId").value;
                console.log("BranchId: " + branchId);
                // Kiểm tra xem email có giá trị trước khi kiểm tra password
                if (!email.value.trim()) {
                    email.setCustomValidity("Vui lòng nhập địa chỉ email.");
                    email.reportValidity();
                    return false;
                } else {
                    email.setCustomValidity("");
                }

                if (email.value.trim() && !/^([a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z]{2,})+)*$/.test(email.value.trim())) {
                    email.setCustomValidity("Địa chỉ email không hợp lệ, vui lòng nhập lại.");
                    email.reportValidity();
                    return false;
                } else {
                    email.setCustomValidity("");
                }



                if (password.value.trim().length < 8) {
                    password.setCustomValidity("Mật khẩu phải bao gồm ít nhất 8 kí tự.");
                    password.reportValidity();
                    return false;
                } else {
                    password.setCustomValidity("");
                }

                if (password.value.trim() !== repassword.value.trim()) {
                    repassword.setCustomValidity("Vui lòng nhập lại giống với mật khẩu.");
                    repassword.reportValidity();
                    return false;
                } else {
                    repassword.setCustomValidity("");
                }

                if (!/^[a-zA-Z0-9\s-àáảãạăắằẳẵặâấầẩẫậèéẻẽẹêếềểễệđòóỏõọôốồổỗộơớờởỡợùúủũụưứừửữựìíỉĩịỳýỷỹỵ]+$/i.test(name.value.trim())) {
                    name.setCustomValidity("Tên chỉ được chứa chữ cái, số, khoảng trắng và dấu gạch ngang.");
                    name.reportValidity();
                    return false;
                } else {
                    name.setCustomValidity("");
                }

                if (address.value.trim() !== "") {
                    if (!/^[a-zA-Z0-9\s.,#'-àáảãạăắằẳẵặâấầẩẫậèéẻẽẹêếềểễệđòóỏõọôốồổỗộơớờởỡợùúủũụưứừửữựìíỉĩịỳýỷỹỵ]+$/i.test(address.value.trim())) {
                        address.setCustomValidity("Địa chỉ không hợp lệ.");
                        address.reportValidity();
                        return false;
                    } else {
                        address.setCustomValidity("");
                    }
                } else {
                    address.setCustomValidity(""); // Không cần validate nếu địa chỉ không được điền
                }



                var today = new Date();
                var birthDate = new Date(birthDate.value.trim());
                var age = today.getFullYear() - birthDate.getFullYear();
                var m = today.getMonth() - birthDate.getMonth();
                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                    age--;
                }

                if (age < 12) {
                    birthDate.setCustomValidity("Yêu cầu người dùng ít nhất 12 tuổi!");
                    birthDate.reportValidity();
                    return false;
                } else {
                    birthDate.setCustomValidity("");
                }

                if (!/^\d{10}$/.test(phone.value.trim())) {
                    phone.setCustomValidity("Số điện thoại chỉ được chứa số và phải có đúng 10 chữ số.");
                    phone.reportValidity();
                    return false;
                } else {
                    phone.setCustomValidity("");
                }

                return true;
            }

            function handleSubmit(clickedElement) {
                var elementId = clickedElement.id;
                var step1container = document.getElementById('step-1-container');
                var step2container = document.getElementById('step-2-container');
                var step3container = document.getElementById('step-3-container');
                console.log("Ten cua the duoc click la: " + elementId);
                if (elementId === 'step-2-to-3') {
                    console.log("element = step 2 to 3");
                    if (validateFormStep1()) {
                        console.log("step 1: TRUE");
                        step2container.style.display = 'none';
                        step3container.style.display = 'block';
                    } else {
                        console.log("step 1: FALSE");
                    }
                    loadInfoOfAppointment();
                } else if (elementId === 'step-2-to-1') {
                    console.log("element = step 2 to 1");
                    step2container.style.display = 'none';
                    step1container.style.display = 'block';
                } else if (elementId === 'step-1-to-2') {
                    console.log("element = step 1 to 2");
                    var inputNameError = document.getElementById("inputNameError");
                    var inputBirthDateError = document.getElementById("inputBirthDateError");
                    var inputPhoneError = document.getElementById("inputPhoneError");
                    var inputSymptomsError = document.getElementById("inputSymptomsError");
                    var inputEmailError = document.getElementById("inputEmailError");

                    inputNameError.innerHTML = '';
                    inputBirthDateError.innerHTML = '';
                    inputPhoneError.innerHTML = '';
                    inputSymptomsError.innerHTML = '';
                    inputEmailError.innerHTML = '';
                    if (validateFormStep2()) {
                        console.log("step 2: TRUE");
                        step1container.style.display = 'none';
                        step2container.style.display = 'block';
                    } else {
                        console.log("step 2: FALSE");
                    }

                } else if (elementId === 'step-3-to-2') {
                    console.log("element = step 3 to 2");
                    step3container.style.display = 'none';
                    step2container.style.display = 'block';
                } else if (elementId === 'submit') {
                    var confirmOTPForm = document.getElementById("confirm-OTP-Form");
                    confirmOTPForm.style.display = 'block';
                    // Close the modal if the user clicks outside of it
                    window.onclick = function (event) {
                        if (event.target === confirmOTPForm) {
                            confirmOTPForm.style.display = "none";
                        }
                    };
                    console.log("element = submit");

                    console.log("Ajax - JS - submit to get OTP of appointment:");
                    var branchId = document.getElementById('branchId').value;
                    var serviceId = document.getElementById('serviceId').value;
                    var doctorId = document.getElementById('doctorId').value;
                    var date = document.getElementById('booking-calendar').value;
                    var slotId = document.getElementById('booking-slot').value;

                    var patientName = document.getElementById("patientName").value;
                    var birthDate = document.getElementById("birthDate").value;
                    var genderRadios = document.getElementsByName("gender");
                    var gender;
                    for (var i = 0; i < genderRadios.length; i++) {
                        if (genderRadios[i].checked) {
                            gender = genderRadios[i].value;
                            break;
                        }
                    }
                    var phone = document.getElementById("phone").value;
                    var email = document.getElementById("email").value;
                    var description = document.getElementById("description").value;
                    console.log("requestOPT - JS: ");
                    console.log("slotId: " + slotId);

                    $.ajax({
                        url: "/MediCare/user-book-appointment",
                        data: {
                            branchId: branchId,
                            serviceId: serviceId,
                            doctorId: doctorId,
                            date: date,
                            slotId: slotId,

                            patientName: patientName,
                            birthDate: birthDate,
                            gender: gender,
                            phone: phone,
                            email: email,
                            description: description,
                            action: "requestOTP"
                        },
                        cache: false,
                        type: "POST",
                        success: function (response) {
                            console.log("response: " + response);
                            var message = response;
                            console.log("Message from JSON: " + message);
                            step3container.setAttribute('data-OTP', message);
                            console.log("data-OTP:" + step3container.getAttribute("data-OTP"));
                            console.log("success");
                        },
                        error: function (xhr) {
                            console.log("Error: " + xhr);
                        }
                    });
                }

            }

            function onchangeBranchId() {
                var branchId = document.getElementById("branchId").value;
                var serviceId = document.getElementById("serviceId");

                console.log("BranchId: " + branchId);
                if (serviceId.style.display === 'none') {
                    serviceId.style.display = 'block';
                }
                var serviceError = document.getElementById("serviceError");
                serviceError.innerHTML = '';
            }

            function onchangeSlot() {
                var slotError = document.getElementById("slotError");
                slotError.innerHTML = '';
            }

            window.onload = function () {
                var branchId = document.getElementById("branchId");
                if (branchId.style.display === 'none') {
                    branchId.style.display = 'block';
                }
            };
        </script>
        <%@include file="user-script.jsp" %>

    </body>
</html>
