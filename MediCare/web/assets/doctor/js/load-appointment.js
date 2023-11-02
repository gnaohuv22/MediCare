/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function loadAppointments(dateString) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var appointments = JSON.parse(this.responseText);
            var scheduleContainer = document.getElementById('appointmentSchedule');
            scheduleContainer.innerHTML = '';

            if (appointments.length === 0) {
                //If there are no appointment.
                var alertElement = document.createElement('div');
                alertElement.className = 'row alert alert-warning';
                alertElement.role = 'alert';
                var parts = dateString.split('-');
                if (parts[2].length < 2)
                    parts[2] = '0' + parts[2];
                if (parts[1].length < 2)
                    parts[1] = '0' + parts[1];
                var formatted = parts.reverse().join('/');

                alertElement.textContent = 'Ngày ' + formatted + ' không có cuộc hẹn với bệnh nhân nào.';
                scheduleContainer.appendChild(alertElement);
            } else {
                appointments.forEach(function (appointment) {
                    var appointmentElement = document.createElement('div');
                    appointmentElement.className = 'card appointment-card';

                    var headerElement = document.createElement('div');
                    headerElement.className = 'card-header';

                    var titleElement = document.createElement('h5');
                    titleElement.className = 'card-title';
                    var plannedAtParts = appointment.plannedAt.split(' ')[0].split('-');
                    if (plannedAtParts[2].length < 2)
                        plannedAtParts[2] = '0' + plannedAtParts[2];
                    if (plannedAtParts[1].length < 2)
                        plannedAtParts[1] = '0' + plannedAtParts[1];
                    var plannedAtFormatted = plannedAtParts.reverse().join('/');
                    titleElement.textContent = 'Thời gian: ' + appointment.plannedAt.split(' ')[1] + ', ' + plannedAtFormatted;

                    var subtitleElement = document.createElement('h6');
                    subtitleElement.className = 'card-subtitle mb-2 text-muted';
                    subtitleElement.textContent = 'Tên bệnh nhân: ' + (appointment.fp.name || "—");
                    headerElement.appendChild(titleElement);
                    headerElement.appendChild(subtitleElement);

                    var bodyElement = document.createElement('div');
                    bodyElement.className = 'card-body';
                    var serviceTextElement = document.createElement('p');
                    serviceTextElement.className = 'card-text';
                    serviceTextElement.textContent = 'Dịch vụ Khám bệnh: ' + (appointment.st.nametag || "—");
                    bodyElement.appendChild(serviceTextElement);

                    var footerElement = document.createElement('div');
                    footerElement.className = 'card-footer text-muted';

                    var statusElement = document.createElement('div');
                    statusElement.className = 'show-status';

                    switch (appointment.status) {
                        case '0':
                            statusElement.textContent = 'Chờ xác nhận';
                            statusElement.className = 'status status-0';
                            break;

                        case '1':
                            statusElement.textContent = 'Đang chờ khám';
                            statusElement.className = 'status status-1';
                            break;

                        case '2':
                            statusElement.textContent = 'Đã khám xong';
                            statusElement.className = 'status status-2';
                            break;

                        case '3':
                            statusElement.textContent = 'Đã huỷ';
                            statusElement.className = 'status status-3';
                            break;

                        default:
                            statusElement.textContent = 'Trạng thái không xác định';
                            statusElement.className = 'status status-default';
                            break;
                    }
                    footerElement.appendChild(statusElement);

                    appointmentElement.appendChild(headerElement);
                    appointmentElement.appendChild(bodyElement);
                    appointmentElement.appendChild(footerElement);

                    //When choose an appointment, display modal windows with details
                    appointmentElement.addEventListener('click', function () {
                        var detailsElement = document.getElementById('appointmentDetails');
                        detailsElement.innerHTML = '';

                        var bodyElement = document.createElement('div');

                        var serviceTextElement = document.createElement('p');
                        serviceTextElement.className = 'card-text';
                        serviceTextElement.textContent = 'Dịch vụ Khám bệnh: ' + (appointment.st.nametag || "—");

                        var patientEmailElement = document.createElement('p');
                        patientEmailElement.className = 'card-text';
                        patientEmailElement.textContent = 'Địa chỉ Email: ' + (appointment.fp.email || "—");

                        var patientNameElement = document.createElement('p');
                        patientNameElement.className = 'card-text';
                        patientNameElement.textContent = 'Tên: ' + (appointment.fp.name || "—");

                        var patientGenderElement = document.createElement('p');
                        patientGenderElement.className = 'card-text';
                        patientGenderElement.textContent = 'Giới tính: ' + (appointment.fp.gender || "—");

                        var patientAddressElement = document.createElement('p');
                        patientAddressElement.className = 'card-text';
                        patientAddressElement.textContent = 'Địa chỉ: ' + (appointment.fp.address || "—");

                        var patientPhoneElement = document.createElement('p');
                        patientPhoneElement.className = 'card-text';
                        patientPhoneElement.textContent = 'SĐT: ' + (appointment.fp.phone || "—");

                        var patientSymptomsElement = document.createElement('p');
                        patientSymptomsElement.className = 'card-text';
                        patientSymptomsElement.textContent = 'Triệu chứng: ' + (appointment.symptoms || "—");

                        bodyElement.appendChild(serviceTextElement);
                        bodyElement.appendChild(patientEmailElement);
                        bodyElement.appendChild(patientNameElement);
                        bodyElement.appendChild(patientGenderElement);
                        bodyElement.appendChild(patientAddressElement);
                        bodyElement.appendChild(patientPhoneElement);
                        bodyElement.appendChild(patientSymptomsElement);

                        var footerElement = document.createElement('div');
                        footerElement.className = 'modal-footer';
                        var insideFooter = document.createElement('div');

                        switch (appointment.status) {
                            case '0':
                                insideFooter.textContent = 'Chờ xác nhận';
                                insideFooter.className = 'status status-0';
                                break;

                            case '1':
                                insideFooter.textContent = 'Đang chờ khám';
                                insideFooter.className = 'status status-1';
                                break;

                            case '2':
                                insideFooter.textContent = 'Đã khám xong';
                                insideFooter.className = 'status status-2';
                                break;

                            case '3':
                                insideFooter.textContent = 'Đã huỷ';
                                insideFooter.className = 'status status-3';
                                break;

                            default:
                                insideFooter.textContent = 'Trạng thái không xác định';
                                insideFooter.className = 'status status-default';
                                break;
                        }
                        footerElement.appendChild(insideFooter);

                        //Change the status of an appointment AJAX
                        var confirmButton = document.createElement('button');
                        confirmButton.className = 'confirm-button';
                        confirmButton.textContent = 'Xác nhận';

                        var completeButton = document.createElement('button');
                        completeButton.className = 'complete-button';
                        completeButton.textContent = 'Hoàn tất Khám bệnh';

                        if (appointment.status === '0') {
                            footerElement.appendChild(confirmButton);
                            footerElement.appendChild(completeButton);
                            completeButton.style.display = 'none';
                        }

                        if (appointment.status === '1') {
                            footerElement.appendChild(completeButton);
                        }

                        var confirmModal = document.getElementById('confirmModal');
                        var confirmYes = document.getElementById('confirmYes');
                        var confirmNo = document.getElementById('confirmNo');

                        confirmButton.addEventListener('click', function () {
                            confirmModal.style.display = 'block';
                            confirmYes.addEventListener('click', function () {
                                //Send AJAX request to Servlet to update the status
                                var xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState === 4 && this.status === 200) {
                                        //When request completed, display notification window
                                        if (this.responseText === 'Trạng thái đã được cập nhật thành công!') {
                                            document.getElementById('notificationMessage').textContent = this.responseText;
                                            document.getElementById('notificationModal').style.display = 'block';
                                            confirmButton.style.display = 'none';
                                            completeButton.style.display = 'block';
                                        } else {
                                            document.getElementById('notificationMessage').textContent = this.responseText;
                                            document.getElementById('notificationModal').style.display = 'block';
                                        }
                                    }
                                };
                                xhttp.open("POST", "getAppointments", true);
                                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                xhttp.send("id=" + appointment.id + "&status=1");

                                // Update class name of status to reflect the new state
                                insideFooter.textContent = 'Đang chờ khám';
                                insideFooter.className = 'status status-1';
                                statusElement.textContent = 'Đang chờ khám';
                                statusElement.className = 'status status-1';
                                confirmModal.style.display = 'none';
                                appointment.status = '1';
                            });

                            confirmNo.addEventListener('click', function () {
                                confirmModal.style.display = 'none';
                            });
                        });

                        completeButton.addEventListener('click', function () {
                            confirmModal.style.display = 'block';
                            confirmYes.addEventListener('click', function () {
                                //Send AJAX request to Servlet to update the status
                                var xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState === 4 && this.status === 200) {
                                        //When request completed, display notification window
                                        if (this.responseText === 'Trạng thái đã được cập nhật thành công!') {
                                            document.getElementById('notificationMessage').textContent = this.responseText;
                                            document.getElementById('notificationModal').style.display = 'block';
                                            completeButton.style.display = 'none';
                                        } else {
                                            document.getElementById('notificationMessage').textContent = this.responseText;
                                            document.getElementById('notificationModal').style.display = 'block';
                                        }
                                    }
                                };
                                xhttp.open("POST", "getAppointments", true);
                                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                xhttp.send("id=" + appointment.id + "&status=2");

                                // Update class name of status to reflect the new state
                                insideFooter.textContent = 'Đã khám xong';
                                insideFooter.className = 'status status-2';
                                statusElement.textContent = 'Đã khám xong';
                                statusElement.className = 'status status-2';
                                confirmModal.style.display = 'none';
                                appointment.status = '2';
                            });
                            confirmNo.addEventListener('click', function () {
                                confirmModal.style.display = 'none';
                            });
                        });

                        detailsElement.appendChild(bodyElement);
                        detailsElement.appendChild(footerElement);

                        document.getElementById('profile-picture').src = appointment.fp.profilePicture;

                        var parts = appointment.plannedAt.split(' ')[0].split('-');
                        if (parts[2].length < 2)
                            parts[2] = '0' + parts[2];
                        if (parts[1].length < 2)
                            parts[1] = '0' + parts[1];
                        var formatted = parts.reverse().join('/');
                        document.getElementById('modalTitle').textContent = 'Chi tiết lịch hẹn - ' + appointment.plannedAt.split(' ')[1] + ' ' + formatted;
                        document.getElementById('appointmentModal').style.display = "block";
                    });

                    scheduleContainer.appendChild(appointmentElement);
                });

            }
        }
    };
    xhttp.open("GET", "getAppointments?day=" + dateString, true);
    xhttp.send();
}