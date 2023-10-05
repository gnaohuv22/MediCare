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
                            <h4 class="page-title">Edit Doctor</h4>
                        </div>
                        <c:if test="${not empty requestScope.error}">
                            <p style="color: red">${requestScope.error}</p>
                        </c:if>
                    </div>

                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <form action="admin-editdoctor" method="post">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>ID <span class="text-danger">*</span></label>
                                            <input name="id" class="form-control" type="text" value="${requestScope.doc.getId()}" required="" readonly="" >
                                        </div>
                                        <c:if test="${not empty requestScope.IdError}">
                                            <p style="color: red">${requestScope.IdError}</p>
                                        </c:if>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Display name <span class="text-danger">*</span></label>
                                            <input value="${requestScope.doc.getDisplayName()}" name="displayName" class="form-control" type="text" required="" >
                                        </div>
                                        <c:if test="${not empty requestScope.displayNameError}">
                                            <p style="color: red">${requestScope.displayNameError}</p>
                                        </c:if>
                                    </div>
                                    <!-- comment 
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Last Name</label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Username <span class="text-danger">*</span></label>
                                            <input class="form-control" type="text">
                                        </div>
                                    </div>
                                    -->
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Email <span class="text-danger">*</span></label>
                                            <input value="${requestScope.doc.getEmail()}" name="email" class="form-control" type="email" required="" >
                                        </div>
                                        <c:if test="${not empty requestScope.EmailError}">
                                            <p style="color: red">${requestScope.EmailError}</p>
                                        </c:if>

                                    </div>


                                    <div class="col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label>Academic Rank <span class="text-danger">*</span></label>
                                            <select name="academicRank"  required="" class="form-control">
                                                <c:forEach  var="a" items="${listAR}">
                                                    <option value="${a.getId()}" <c:if test="${a.getId() eq requestScope.doc.getARId()}">selected</c:if>> ${a.getName()}</option>
                                                </c:forEach> 
                                            </select>
                                        </div>
                                    </div>
                                    <!--                                    <div class="col-sm-6 col-md-6 col-lg-6">
                                                                            <div class="form-group">
                                                                                <label>Certificates <span class="text-danger">*</span></label> <br>
                                    <c:forEach var="cert" items="${listCert}">
                                        <c:set var="isChecked" value="false"/>
                                        <c:forEach var="doccert" items="${listCertofDoc}">
                                            <c:if test="${cert.getId() eq doccert.getCertId()}">
                                                <c:set var="isChecked" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <input type="checkbox" name="certificates" value="${cert.getId()}" ${isChecked ? 'checked' : ''}>
                                        <label for="certificates">${cert.getName()}</label><br>
                                    </c:forEach> 
                                </div>
                            </div>-->
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Certificates <span class="text-danger">*</span></label> <br>
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

                                        </div>
                                        <c:if test="${not empty requestScope.certificateError}">
                                            <p style="color: red">${requestScope.certificateError}</p>
                                        </c:if>
                                    </div>
<!--                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>CV ID  <span class="text-danger">*</span></label>
                                            <input value="${requestScope.doc.getCVId()}" name="certID" class="form-control" type="text" readonly="" >
                                        </div>
                              </div>-->
                                    

                                    <!-- comment
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Date of Birth</label>
                                            <div class="cal-icon">
                                                <input type="text" class="form-control datetimepicker">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <div class="form-group gender-select">
                                            <label class="gen-label">Gender:</label>
                                            <div class="form-check-inline">
                                                <label class="form-check-label">
                                                    <input type="radio" name="gender" class="form-check-input">Male
                                                </label>
                                            </div>
                                            <div class="form-check-inline">
                                                <label class="form-check-label">
                                                    <input type="radio" name="gender" class="form-check-input">Female
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 
                                   <div class="col-sm-12">
                                       <div class="row">
                                           <div class="col-sm-12">
                                               <div class="form-group">
                                                   <label>Address</label>
                                                   <input type="text" class="form-control ">
                                               </div>
                                           </div>
                                    -->
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Salary  <span class="text-danger">*</span></label>
                                            <input value="${requestScope.doc.getSalary()}" name="salary" class="form-control" type="text" required="" >
                                        </div>
                                        <c:if test="${not empty requestScope.SalaryError}">
                                            <p style="color: red">${requestScope.SalaryError}</p>
                                        </c:if>
                                    </div>
                                    <div class="col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label>Branch <span class="text-danger">*</span></label>
                                            <select name="branch" class="form-control" required="" >
                                                <c:forEach  var="br" items="${listBranch}">
                                                    <option  value="${br.getId()}" <c:if test="${br.getId() eq requestScope.doc.getBranchId()}">selected</c:if>>${br.getName()}</option>
                                                </c:forEach> 
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <label>Status <span class="text-danger">*</span></label>
                                        <select name="status"  required="" class="form-control">
                                            <option value="1">contract</option>
                                            <option value="2">Official </option>  
                                        </select>
                                    </div>
                                    <!--                                           <div class="col-sm-6 col-md-6 col-lg-3">
                                                                                   <div class="form-group">
                                                                                       <label>City</label>
                                                                                       <input type="text" class="form-control">
                                                                                   </div>
                                                                               </div>
                                                                               <div class="col-sm-6 col-md-6 col-lg-3">
                                                                                   <div class="form-group">
                                                                                       <label>State/Province</label>
                                                                                       <select class="form-control">
                                                                                           <option>California</option>
                                                                                           <option>Alaska</option>
                                                                                           <option>Alabama</option>
                                                                                       </select>
                                                                                   </div>
                                                                               </div>
                                                                               <div class="col-sm-6 col-md-6 col-lg-3">
                                                                                   <div class="form-group">
                                                                                       <label>Postal Code</label>
                                                                                       <input type="text" class="form-control">
                                                                                   </div>
                                                                               </div>
                                                                           </div>
                                                                       </div>-->

                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Phone </label>
                                            <input value="${requestScope.doc.getPhone()}"name="phone" class="form-control" type="text" required="" >
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
                                                    <img alt="" src="${pageContext.request.contextPath}assets/img/user.jpg">
                                                </div>
                                                <div class="upload-input">
                                                    <input name="avatarUpload" type="file" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- comment 
                                <div class="form-group">
                                    <label>Short Biography</label>
                                    <textarea class="form-control" rows="3" cols="30"></textarea>
                                </div>
                                
                                <div class="form-group">
                                    <label class="display-block">Status</label>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="doctor_active" value="option1" checked>
                                        <label class="form-check-label" for="doctor_active">
                                            Active
                                        </label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="doctor_inactive" value="option2">
                                        <label class="form-check-label" for="doctor_inactive">
                                            Inactive
                                        </label>
                                    </div>
                                </div>
                                -->
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
    </body>
</html>
