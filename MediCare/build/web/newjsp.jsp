<%-- Document : newjsp Created on : Oct 18, 2023, 8:31:38 PM Author : DELL --%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>JSP Page</title>
            </head>

            <body>
                <table id="myTable">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Gender</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${people}" var="person">
                            <tr>
                                <td>${person.name}</td>
                                <td>${person.age}</td>
                                <td>${person.gender}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>name</td>
                            <td>age</td>
                            <td>gender</td>
                        </tr>
                    </tbody>
                </table>
                <input type="checkbox" onclick="hideColumn(1)"> Hide Age
            </body>

            <a href="#" onclick="openPopup()">Open Popup</a>
            <div id="popup" style="display: none;">
                <div class="popup-content">
                    <h1>Popup Title</h1>
                    <form action="servlet" method="post">
                        <input type="text" name="name">
                        <input type="submit" value="Submit">
                    </form>
                </div>
            </div>
            <script>
                function hideColumn(index) {
                    var table = document.getElementById("myTable");
                    var rows = table.getElementsByTagName("tr");
                    var cell = rows[0].getElementsByTagName("th");
                    cell[index].style.display="none";
                    var length = rows.length;
                    for (var i = 1; i < length; i++) {
                        var cell = rows[i].getElementsByTagName("td");
                        text = cell[index].style.display="none";;
                    }
                }
                function diappear() {
                    var tag = document.getElementById("1");
                    tag.style.display = "none";
                }
                function loadMore() {
                    document.getElementById("loadMore").style.display = "block";
                }
                function closeLoadMore() {
                    document.getElementById("buttonLoadMore").style.display = "none";
                }
                function openPopup() {
                    document.getElementById("popup").style.display = "block";
                }
                function closePopup() {
                    document.getElementById("popup").style.display = "none";
                }
            </script>
            </body>

            </html>