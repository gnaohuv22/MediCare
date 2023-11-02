<%-- 
    Document   : user-script
    Created on : Sep 22, 2023, 12:06:21 PM
    Author     : phuon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Javascript files-->
<script src="${pageContext.request.contextPath}/assets/doctor/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/doctor/js/bootstrap.bundle.min.js"></script>
<!-- Add jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/doctor/js/plugin.js"></script>
<!-- sidebar -->
<script src="${pageContext.request.contextPath}/assets/doctor/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/doctor/js/custom.js"></script>
<!-- javascript -->
<script src="${pageContext.request.contextPath}/assets/doctor/js/owl.carousel.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.js"></script>
<script>
    $(document).ready(function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 50) {
                $('#back-to-top').fadeIn().addClass('show');
            } else {
                $('#back-to-top').fadeOut().removeClass('show');
            }
        });
        // scroll body to 0px on click
        $('#back-to-top').click(function () {
            $('body,html').animate({
                scrollTop: 0
            }, 200);
            return false;
        });
    });
</script>
