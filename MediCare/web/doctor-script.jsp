<%-- 
    Document   : doctor-script
    Created on : Oct 21, 2023, 11:31:27 PM
    Author     : hoang
--%>

<script>
    $(document).ready(function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 50) {
                $('#back-to-top').fadeIn();
            } else {
                $('#back-to-top').fadeOut();
            }
        });
        // scroll body to 0px on click
        $('#back-to-top').click(function () {
            $('body,html').animate({
                scrollTop: 0
            }, 400);
            return false;
        });
    });
</script>

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