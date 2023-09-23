<%-- 
    Document   : Appointment
    Created on : Sep 10, 2023, 10:42:54 PM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .custom-dialog {
                border: 1px solid #ccc;
                padding: 20px;
                width: 300px;
                text-align: center;
            }
            .button {
                margin: 10px;
            }
        </style>
    </head>
    <body>
        <form action="appointment" method="post">
            Chọn Gói Khám : <select name="plan" required="">
                <option value-="1"  >Standard</option><br/>
                <option value="2">Extend</option><br/>
                <option value="2">Highend</option><br/>
            </select>
            Chọn Ngày khám <input type="date" name="date" required="" ><!--  -->
            <button onclick="showCustomConfirm()" name="submit">Submit</button>
            <!--    <input type="submit" value="Submit" />-->
            <p id="demo"></p>
            <p> ${requestScope.status} </p>

            <script>
                function showCustomConfirm() {
                    var customDialog = document.createElement('div');
                    customDialog.className = 'custom-dialog';

                    var title = document.createElement('h2');
                    title.textContent = 'Custom Confirm Dialog';

                    var confirmButton = document.createElement('button');
                    confirmButton.className = 'button';
                    confirmButton.textContent = 'Confirm';

                    var cancelButton = document.createElement('button');
                    cancelButton.className = 'button';
                    cancelButton.textContent = 'Cancel';

                    customDialog.appendChild(title);
                    customDialog.appendChild(confirmButton);
                    customDialog.appendChild(cancelButton);

                    document.body.appendChild(customDialog);

                    confirmButton.addEventListener('click', function () {
                        document.body.removeChild(customDialog);

                        // Create a form dynamically
                        var form = document.createElement('form');
                        form.method = 'POST';
                        form.action = 'appointment'; // Replace 'servlet-url' with the actual URL of your servlet

                        // Create an input field to hold the value
                        var valueInput = document.createElement('input');
                        valueInput.type = 'hidden';
                        valueInput.name = 'value';
                        valueInput.value = 'confirm';

                        // Append the input field to the form
                        form.appendChild(valueInput);

                        // Append the form to the document and submit it
                        document.body.appendChild(form);
                        form.submit();
                    });

                    cancelButton.addEventListener('click', function () {
                        document.body.removeChild(customDialog);

                        // Create a form dynamically
                        var form = document.createElement('form');
                        form.method = 'POST';
                        form.action = 'appointment'; // Replace 'servlet-url' with the actual URL of your servlet

                        // Create an input field to hold the value
                        var valueInput = document.createElement('input');
                        valueInput.type = 'hidden';
                        valueInput.name = 'value';
                        valueInput.value = 'cancel';

                        // Append the input field to the form
                        form.appendChild(valueInput);

                        // Append the form to the document and submit it
                        document.body.appendChild(form);
                        form.submit();
                    });
                }
                
            </script>
        </form>
    </body>
</html>
