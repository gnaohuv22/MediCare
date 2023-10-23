<%-- 
    Document   : adminSetting-sidebar
    Created on : Sep 21, 2023, 12:49:53 AM
    Author     : Asus
--%>

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
                    <div class="sidebar-menu">
                        <ul>
                            <li>
                                <a href="../screen/home.jsp"><i class="fa fa-home back-icon"></i> <span>Back to Home</span></a>
                            </li>
                            <li class="menu-title">Settings</li>
                            <li>
                                <a href="settings.jsp"><i class="fa fa-building"></i> <span>Company Settings</span></a>
                            </li>
                            <li>
                                <a href="localization.jsp"><i class="fa fa-clock-o"></i> <span>Localization</span></a>
                            </li>
                            <li>
                                <a href="theme-settings.jsp"><i class="fa fa-picture-o"></i> <span>Theme Settings</span></a>
                            </li>
                            <li>
                                <a href="roles-permissions.jsp"><i class="fa fa-key"></i> <span>Roles & Permissions</span></a>
                            </li>
                            <li class="active">
                                <a href="email-settings.jsp"><i class="fa fa-envelope-o"></i> <span>Email Settings</span></a>
                            </li>
                            <li>
                                <a href="invoice-settings.jsp"><i class="fa fa-pencil-square-o"></i> <span>Invoice Settings</span></a>
                            </li>
                            <li>
                                <a href="salary-settings.jsp"><i class="fa fa-money"></i> <span>Salary Settings</span></a>
                            </li>
                            <li>
                                <a href="notifications-settings.jsp"><i class="fa fa-globe"></i> <span>Notifications</span></a>
                            </li>
                            <li>
                                <a href="change-password.jsp"><i class="fa fa-lock"></i> <span>Change Password</span></a>
                            </li>
                            <li>
                                <a href="leave-type.jsp"><i class="fa fa-cogs"></i> <span>Leave Type</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
    </body>
</html>
