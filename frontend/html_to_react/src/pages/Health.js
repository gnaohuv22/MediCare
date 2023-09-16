import React, { Fragment } from "react";

function Health(){
    return(
        <Fragment>
            <div class="header_section">
         <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="logo"><a href="index.html"><img src="ASSETS/images/logo.png" /></a></div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
               <ul class="navbar-nav mr-auto">
                  <li class="nav-item">
                     <a class="nav-link" href="index.html">Home</a>
                  </li>
                  <li class="nav-item active">
                     <a class="nav-link" href="health.html">Health</a>
                  </li>
                  <li class="nav-item">
                     <a class="nav-link" href="medicine.html">Medicine</a>
                  </li>
                  <li class="nav-item">
                     <a class="nav-link" href="news.html">News</a>
                  </li>
                  <li class="nav-item">
                     <a class="nav-link" href="client.html">Client</a>
                  </li>
                  <li class="nav-item">
                     <a class="nav-link" href="contact.html">Contact Us</a>
                  </li>
                  <li class="nav-item">
                     <a class="nav-link" href="#"><img src="ASSETS/images/search-icon.png" /></a>
                  </li>
               </ul>
            </div>
         </nav>
      </div>
      {/* <!-- header section end -->
      <!-- health section start --> */}
      <div class="health_section layout_padding">
         <div class="container">
            <h1 class="health_taital">Best Of  Health care for you</h1>
            <p class="health_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis</p>
            <div class="health_section layout_padding">
               <div class="row">
                  <div class="col-sm-7">
                     <div class="image_main">
                        <div class="main">
                           <img src="ASSETS/images/img-2.png" alt="Avatar" class="image" style={{width:"100%"}}/>
                        </div>
                        <div class="middle">
                           <div class="text"><img src="ASSETS/images/icon-1.png" style={{width: "40px"}}/></div>
                        </div>
                     </div>
                  </div>
                  <div class="col-sm-5">
                     <div class="image_main_1">
                        <div class="main">
                        <img src="ASSETS/images/img-3.png" alt="Avatar" class="image" style={{width:"100%"}}/>
                        </div>
                        <div class="middle">
                           <div class="text"><img src="ASSETS/images/icon-1.png" style={{width: "40px"}}/></div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="getquote_bt_1"><a href="#">Read More <span><img src="ASSETS/images/right-arrow.png"/></span></a></div>
            </div>
         </div>
      </div>
      {/* <!-- health section end -->
      <!-- footer section start --> */}
      <div class="footer_section layout_padding">
         <div class="container">
            <div class="row">
               <div class="col-lg-3 col-sm-6">
                  <div class="footer_logo"><a href="index.html"><img src="ASSETS/images/footer-logo.png"/></a></div>
                  <h1 class="adderss_text">Contact Us</h1>
                  <div class="map_icon"><img src="ASSETS/images/map-icon.png"/><span class="paddlin_left_0">Page when looking at its</span></div>
                  <div class="map_icon"><img src="ASSETS/images/call-icon.png"/><span class="paddlin_left_0">+7586656566</span></div>
                  <div class="map_icon"><img src="ASSETS/images/mail-icon.png"/><span class="paddlin_left_0">volim@gmail.com</span></div>
               </div>
               <div class="col-lg-3 col-sm-6">
                  <h1 class="adderss_text">Doctors</h1>
                  <div class="hiphop_text_1">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour,</div>
               </div>
               <div class="col-lg-3 col-sm-6">
                  <h1 class="adderss_text">Useful Links</h1>
                  <div class="Useful_text">There are many variations of passages of Lorem Ipsum available, but the majority have suffered ,</div>
               </div>
               <div class="col-lg-3 col-sm-6">
                  <h1 class="adderss_text">Newsletter</h1>
                  <input type="text" class="Enter_text" placeholder="Enter your Emil" name="Enter your Emil"/>
                  <div class="subscribe_bt"><a href="#">Subscribe</a></div>
                  <div class="social_icon">
                     <ul>
                        <li><a href="#"><img src="ASSETS/images/fb-icon.png"/></a></li>
                        <li><a href="#"><img src="ASSETS/images/twitter-icon.png"/></a></li>
                        <li><a href="#"><img src="ASSETS/images/linkedin-icon.png"/></a></li>
                        <li><a href="#"><img src="ASSETS/images/instagram-icon.png"/></a></li>
                     </ul>
                  </div>
               </div>
            </div>
         </div>
      </div>
      {/* <!-- footer section end -->
      <!-- copyright section start --> */}
      <div class="copyright_section">
         <div class="container">
            <p class="copyright_text">2019 All Rights Reserved. Design by <a href="https://html.design">Free html  Templates</a></p>
         </div>
      </div>
      {/* <!-- copyright section end -->
      <!-- Javascript files--> */}
      <script src="ASSETS/js/jquery.min.js"></script>
      <script src="ASSETS/js/popper.min.js"></script>
      <script src="ASSETS/js/bootstrap.bundle.min.js"></script>
      <script src="ASSETS/js/jquery-3.0.0.min.js"></script>
      <script src="ASSETS/js/plugin.js"></script>
      {/* <!-- sidebar --> */}
      <script src="ASSETS/js/jquery.mCustomScrollbar.concat.min.js"></script>
      <script src="ASSETS/js/custom.js"></script>
      {/* <!-- javascript -->  */}
      <script src="ASSETS/js/owl.carousel.js"></script>
      <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
        </Fragment>
    )
}

export default Health;