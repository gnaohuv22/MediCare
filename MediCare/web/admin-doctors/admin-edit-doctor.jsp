<%-- 
    Document   : edit-doctor
    Created on : Sep 21, 2023, 1:09:03 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <c:if test="${requestScope.action eq 'edit'}">
                                <h4 class="page-title">Edit Doctor</h4>
                            </c:if>
                            <c:if test="${requestScope.action eq 'add'}">
                                <h4 class="page-title">Add Doctor</h4>
                            </c:if>

                        </div>
                        <c:if test="${not empty requestScope.error}">
                            <p style="color: red">${requestScope.error}</p>
                        </c:if>
                    </div>

                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <form action="admin-doctor" method="post" enctype="multipart/form-data">
                                <input type="hidden" value="${requestScope.action}" name="action">
                                <input type="hidden" name="id" value="${requestScope.doc.getId()}">
                                <div class="row">
                                    <c:if test="${requestScope.action eq 'edit'}">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label>ID <span class="text-danger">*</span></label>                      
                                                    <input name="id" class="form-control" type="text" value="${requestScope.doc.getId()}" required="" readonly="" >
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Display name <span class="text-danger">*</span></label>
                                            <c:if test="${requestScope.action eq 'edit'}">
                                            <input value="${requestScope.doc.getDisplayName()}" name="displayName" class="form-control" type="text" required="" >
                                            </c:if>
                                            <c:if test="${requestScope.action eq 'add'}">
                                                <input name="displayName" class="form-control" type="text" required="" value="${requestScope.displayName}">
                                            </c:if>
                                        </div>
                                        <c:if test="${not empty requestScope.displayNameError}">
                                            <p style="color: red">${requestScope.displayNameError}</p>
                                        </c:if>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Email <span class="text-danger">*</span></label>
                                            <c:if test="${requestScope.action eq 'edit'}">
                                                <input value="${requestScope.doc.getEmail()}" name="email" class="form-control" type="email" required="" >
                                            </c:if>
                                            <c:if test="${requestScope.action eq 'add'}">
                                                <input name="email" class="form-control" type="email" required=""value="${requestScope.email}" >
                                            </c:if>
                                        </div>
                                        <c:if test="${not empty requestScope.EmailError}">
                                            <p style="color: red">${requestScope.EmailError}</p>
                                        </c:if>

                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label> Gender  <span class="text-danger">*</span></label>
                                             <c:if test="${requestScope.action eq 'edit'}">
                                                     <select name="gender"  required="" class="form-control">
                                               
                                                <option  <c:if test="${doc.getGender() == 1}">
                                                        selected
                                                    </c:if>  value="1" >Male</option>
                                                <option  <c:if test="${doc.getGender() == 2}">
                                                        selected
                                                    </c:if>  value="2" >Female </option>  
                                                <option  <c:if test="${doc.getGender() == 3}">
                                                        selected
                                                    </c:if>  value="3" >Others </option>  
                                            </select>
                                                </c:if>
                                            <c:if test="${requestScope.action eq 'add'}">
                                                <select name="gender"  required="" class="form-control">
                                                <option  value="1" <% if ("1".equals(request.getAttribute("gender"))) { %>selected<% } %>>Male</option>
                                                <option  value="2" <% if ("2".equals(request.getAttribute("gender"))) { %>selected<% } %>>Female </option>  
                                                <option  value="3" <% if ("3".equals(request.getAttribute("gender"))) { %>selected<% } %>>Others </option>  
                                            </select>
                                            </c:if>
                                           
                                        </div>

                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label> Birthdate  <span class="text-danger">*</span></label>
                                            <c:if test="${requestScope.action eq 'edit'}">
                                                <input name="birthDate" class="form-control" type="date" required="" value="${requestScope.doc.getBirthDate()}" oninput="mydate1(this)">
                                            </c:if>
                                            <c:if test="${requestScope.action eq 'add'}">
                                                <input name="birthDate" class="form-control" type="date" required="" value="${requestScope.birthDate}" oninput="myDate1(this)">
                                            </c:if>
                                        </div>

                                    </div>

                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Certificates <span class="text-danger">*</span></label> <br>
                                            <c:if test="${requestScope.action eq 'edit'}">
                                                 <div class="row">
                                                <c:forEach var="a" items="${listCert}" varStatus="status">
                                                    <c:set var="isChecked" value="false"/>
                                                    <c:forEach var="doccert" items="${listCertofDoc}">
                                                        <c:if test="${a.getId() eq doccert.getCertId()}">
                                                            <c:set var="isChecked" value="true" />
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${status.index % 2 eq 1 and status.index ne listCert.size() - 1}">
                                                        <div class="col-lg-6">
                                                            <input type="checkbox" name="certificates" value="${a.getId()}" ${isChecked ? 'checked' : ''}>
                                                            <label for="certificates">${a.getName()}</label>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${status.index % 2 eq 0 and status.index ne listCert.size() - 1}">
                                                        <div class="col-lg-6">
                                                            <input type="checkbox" name="certificates" value="${a.getId()}" ${isChecked ? 'checked' : ''}>
                                                            <label for="certificates">${a.getName()}</label>
                                                        </div>
                                                    </c:if>
                                                </c:forEach> 
                                            </div>
                                            </c:if>
                                            <c:if test="${requestScope.action eq 'add'}">
                                                 <div class="row">
                                                <c:forEach var="a" items="${listCert}" varStatus="status">
                                                    <c:set var="isChecked" value="false"/>
                                                    <c:forEach var="doccert" items="${CDList}">
                                                        <c:if test="${a.getId() eq doccert.getCertId()}">
                                                            <c:set var="isChecked" value="true" />
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${status.index % 2 eq 1 and status.index ne listCert.size() - 1}">
                                                        <div class="col-lg-6">
                                                            <input type="checkbox" name="certificates" value="${a.getId()}" ${isChecked ? 'checked' : ''}>
                                                            <label for="certificates">${a.getName()}</label>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${status.index % 2 eq 0 and status.index ne listCert.size() - 1}">
                                                        <div class="col-lg-6">
                                                            <input type="checkbox" name="certificates" value="${a.getId()}" ${isChecked ? 'checked' : ''}>
                                                            <label for="certificates">${a.getName()}</label>
                                                        </div>
                                                    </c:if>
                                                </c:forEach> 
                                            </div>

                                            </c:if>
                                           

                                        </div>
                                        <c:if test="${not empty requestScope.certificateError}">
                                            <p style="color: red">${requestScope.certificateError}</p>
                                        </c:if>
                                    </div>
                                    <div class="col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label>Academic Rank <span class="text-danger">*</span></label>
                                            <c:if test="${requestScope.action eq 'edit'}">
                                                 <select name="academicRank"  required="" class="form-control">
                                                <c:forEach  var="a" items="${listAR}">
                                                    <option value="${a.getId()}" <c:if test="${a.getId() eq requestScope.doc.getARId()}">selected</c:if>> ${a.getName()}</option>
                                                </c:forEach> 
                                            </select>
                                            </c:if>
                                            <c:if test="${requestScope.action eq 'add'}">
                                                                             <select name="academicRank" required="" class="form-control">
                                                <c:forEach var="a" items="${listAR}">
                                                    <option value="${a.getId()}" 
                                                            <c:if test="${a.getId() == requestScope.academicRank}">
                                                                selected
                                                            </c:if>
                                                            >${a.getName()}</option>
                                                </c:forEach> 
                                            </select>

                                            </c:if>
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Salary  <span class="text-danger">*</span></label>
                                            <c:if test="${requestScope.action eq 'edit'}">
                                                 <input value="${requestScope.doc.getSalary()}" name="salary" class="form-control" type="text" required="" oninput="formatSalaryInput(this)" >
                                            </c:if>
                                            <c:if test="${requestScope.action eq 'add'}">
                                                 <input name="salary" class="form-control" type="text" required="" value="${requestScope.salary}" oninput="formatSalaryInput(this)" >
                                            </c:if>
                                        </div>
                                        <c:if test="${not empty requestScope.SalaryError}">
                                            <p style="color: red">${requestScope.SalaryError}</p>
                                        </c:if>
                                    </div>
                                    <div class="col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label>Branch <span class="text-danger">*</span></label>
                                            <c:if test="${requestScope.action eq 'edit'}">
                                                 <select name="branch" class="form-control" required="" >
                                                <c:forEach  var="br" items="${listBranch}">
                                                    <option  value="${br.getId()}" <c:if test="${br.getId() eq requestScope.doc.getBranchId()}">selected</c:if>>${br.getName()}</option>
                                                </c:forEach> 
                                            </select>
                                            </c:if>
                                            <c:if test="${requestScope.action eq 'add'}">
                                                 <select name="branch" class="form-control" required="" >
                                                <c:forEach  var="br" items="${listBranch}">
                                                    <option value="${br.getId()}" 
                                                            <c:if test="${br.getId() == requestScope.branch}">
                                                                selected
                                                            </c:if>
                                                            >${br.getName()}</option>
                                                </c:forEach> 
                                            </select>

                                            </c:if>
                                           
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <label>Status <span class="text-danger">*</span></label>
                                        <c:if test="${requestScope.action eq 'edit'}">
                                            <select name="status" required="" class="form-control">
                                            <option value="1" 
                                                    <c:if test="${requestScope.doc.getStatus() eq String.valueOf(1)}">
                                                        selected
                                                    </c:if>
                                                    >contract</option>
                                            <option value="2" 
                                                    <c:if test="${requestScope.doc.getStatus() eq String.valueOf(2)}">
                                                        selected
                                                    </c:if>
                                                    >Official</option>
                                        </select>
                                        </c:if>
                                        <c:if test="${requestScope.action eq 'add'}">
                                            <select name="status"  required="" class="form-control">
                                                <option <% if ("1".equals(request.getAttribute("status"))) { %>selected<% } %> value="1">Contract</option>
                                                <option <% if ("2".equals(request.getAttribute("status"))) { %>selected<% } %> value="2">Official </option>  
                                            </select>
                                        </c:if>
                                        
                                    </div>


                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Phone </label>
                                            <c:if test="${requestScope.action eq 'edit'}">
                                                <input value="${requestScope.doc.getPhone()}"name="phone" class="form-control" type="text" required="" >
                                            </c:if>
                                                <c:if test="${requestScope.action eq 'add'}">
                                                     <input name="phone" class="form-control" type="text" required="" value="${requestScope.phone}">
                                                </c:if>
                                            
                                        </div>
                                        <c:if test="${not empty requestScope.PhoneError}">
                                            <p style="color: red"> ${requestScope.PhoneError}</p>
                                        </c:if>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Avatar</label>
                                            <div class="profile-upload">
                                                <div class="upload-img">
                                                    <img alt="" src="${pageContext.request.contextPath}/uploads/default-img.jpg"> 
                                                </div>
                                                <div class="upload-input">
                                                    <input name="avatarUpload" type="file" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="m-t-20 text-center">
                                    <input type="submit" value="Update" class="btn btn-primary submit-btn">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin-general/admin-notifications-box.jsp"/>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <jsp:include page="../admin-general/admin-script.jsp"/>
        <script>
            function formatSalaryInput(input) {
                let value = input.value.replace(/\D/g, ''); // Lọc ra chỉ còn số
                value = parseInt(value, 10); // Chuyển giá trị thành số nguyên
                input.value = value.toLocaleString('vi-VN'); // Định dạng và hiển thị giá trị
            }
        </script>
        <script>
            function mydate1() {
                d = new Date(document.getElementById("dt").value);
                dt = d.getDate();
                mn = d.getMonth();
                mn++;
                yy = d.getFullYear();
                document.getElementById("ndt").value = dt + "/" + mn + "/" + yy
                document.getElementById("ndt").hidden = false;
                document.getElementById("dt").hidden = true;
            }
        </script>
    </body>
</html>
