<%-- 
    Document   : admin-sidebar
    Created on : Sep 20, 2023, 11:22:13 PM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="sidebar" id="sidebar">
            <div class="sidebar-inner slimscroll">
                <div id="sidebar-menu" class="sidebar-menu">
                    <ul>
                        <li class="menu-title">Chính</li>
                        <c:forEach var="menu" items="${ADMIN_SIDEBAR_MENU}">
                        <li>
                            <a href="${pageContext.request.contextPath}${menu.getLink()}"><i class="${menu.getIcon()}"></i> <span>${menu.getName()}</span></a>
                        </li>
                        </c:forEach>
                        <!-- comment <li>
                            <a href="departments.jsp"><i class="fa fa-hospital-o"></i> <span>Departments</span></a>
                        </li>
                        -->
<!--                        <li class="submenu">
                            <a href="#"><i class="fa fa-user"></i> <span> Nhân viên </span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="${pageContext.request.contextPath}/admin-employees/admin-employees.jsp">Danh sách nhân viên</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin-employees/admin-leaves.jsp">Leaves</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin-employees/admin-holidays.jsp">Holidays</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin-employees/admin-attendance.jsp">Attendance</a></li>
                            </ul>-->

                            <!-- comment
                           <li class="submenu">
                               <a href="#"><i class="fa fa-money"></i> <span> Accounts </span> <span class="menu-arrow"></span></a>
                               <ul style="display: none;">
                                   <li><a href="invoices.jsp">Invoices</a></li>
                                   <li><a href="payments.jsp">Payments</a></li>
                                   <li><a href="expenses.jsp">Expenses</a></li>
                                   <li><a href="taxes.jsp">Taxes</a></li>
                                   <li><a href="provident-fund.jsp">Provident Fund</a></li>
                               </ul>
                           </li>
                           <li class="submenu">
                               <a href="#"><i class="fa fa-book"></i> <span> Payroll </span> <span class="menu-arrow"></span></a>
                               <ul style="display: none;">
                                   <li><a href="salary.html"> Employee Salary </a></li>
                                   <li><a href="salary-view.html"> Payslip </a></li>
                               </ul>
                           </li>
                           <li>
                               <a href="chat.html"><i class="fa fa-comments"></i> <span>Chat</span> <span class="badge badge-pill bg-primary float-right">5</span></a>
                           </li>
                           <li class="submenu">
                               <a href="#"><i class="fa fa-video-camera camera"></i> <span> Calls</span> <span class="menu-arrow"></span></a>
                               <ul style="display: none;">
                                   <li><a href="voice-call.html">Voice Call</a></li>
                                   <li><a href="video-call.html">Video Call</a></li>
                                   <li><a href="incoming-call.html">Incoming Call</a></li>
                               </ul>
                           </li>
                           <li class="submenu">
                               <a href="#"><i class="fa fa-envelope"></i> <span> Email</span> <span class="menu-arrow"></span></a>
                               <ul style="display: none;">
                                   <li><a href="compose.html">Compose Mail</a></li>
                                   <li><a href="inbox.html">Inbox</a></li>
                                   <li><a href="mail-view.html">Mail View</a></li>
                               </ul>
                           </li>
                            -->
<!--                        <li class="submenu">
                            <a href="#"><i class="fa fa-commenting-o"></i> <span> Blog</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="${pageContext.request.contextPath}/blog/blog.jsp">Blog</a></li>
                                <li><a href="${pageContext.request.contextPath}/blog/blog-details.jsp">Blog View</a></li>
                                <li><a class="active" href="${pageContext.request.contextPath}/blog/add-blog.jsp">Add Blog</a></li>
                                <li><a href="${pageContext.request.contextPath}/blog/edit-blog.jsp">Edit Blog</a></li>
                            </ul>
                        </li>-->
                        <!-- comment 
                        <li>
                            <a href="../assets.html"><i class="fa fa-cube"></i> <span>../assets</span></a>
                        </li>
                        <li>
                            <a href="activities.html"><i class="fa fa-bell-o"></i> <span>Activities</span></a>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-flag-o"></i> <span> Reports </span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="expense-reports.html"> Expense Report </a></li>
                                <li><a href="invoice-reports.html"> Invoice Report </a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="settings.html"><i class="fa fa-cog"></i> <span>Settings</span></a>
                        </li>
                        <li class="menu-title">UI Elements</li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-laptop"></i> <span> Components</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="uikit.html">UI Kit</a></li>
                                <li><a href="typography.html">Typography</a></li>
                                <li><a href="tabs.html">Tabs</a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-edit"></i> <span> Forms</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="form-basic-inputs.html">Basic Inputs</a></li>
                                <li><a href="form-input-groups.html">Input Groups</a></li>
                                <li><a href="form-horizontal.html">Horizontal Form</a></li>
                                <li><a href="form-vertical.html">Vertical Form</a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-table"></i> <span> Tables</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="tables-basic.html">Basic Tables</a></li>
                                <li><a href="tables-datatables.html">Data Table</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="calendar.html"><i class="fa fa-calendar"></i> <span>Calendar</span></a>
                        </li>
                        <li class="menu-title">Extras</li>
                        <li class="submenu">
                            <a href="#"><i class="fa fa-columns"></i> <span>Pages</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li><a href="login.html"> Login </a></li>
                                <li><a href="register.html"> Register </a></li>
                                <li><a href="forgot-password.html"> Forgot Password </a></li>
                                <li><a href="change-password2.html"> Change Password </a></li>
                                <li><a href="lock-screen.html"> Lock Screen </a></li>
                                <li><a href="profile.html"> Profile </a></li>
                                <li><a href="gallery.html"> Gallery </a></li>
                                <li><a href="error-404.html">404 Error </a></li>
                                <li><a href="error-500.html">500 Error </a></li>
                                <li><a href="blank-page.html"> Blank Page </a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="javascript:void(0);"><i class="fa fa-share-alt"></i> <span>Multi Level</span> <span class="menu-arrow"></span></a>
                            <ul style="display: none;">
                                <li class="submenu">
                                    <a href="javascript:void(0);"><span>Level 1</span> <span class="menu-arrow"></span></a>
                                    <ul style="display: none;">
                                        <li><a href="javascript:void(0);"><span>Level 2</span></a></li>
                                        <li class="submenu">
                                            <a href="javascript:void(0);"> <span> Level 2</span> <span class="menu-arrow"></span></a>
                                            <ul style="display: none;">
                                                <li><a href="javascript:void(0);">Level 3</a></li>
                                                <li><a href="javascript:void(0);">Level 3</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="javascript:void(0);"><span>Level 2</span></a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="javascript:void(0);"><span>Level 1</span></a>
                                </li>
                            </ul>
                        </li>
                        -->
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
