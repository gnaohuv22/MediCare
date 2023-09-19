import React, { Fragment } from 'react'

function Index(){
    return(
        <Fragment>
   <div className="header_section">
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
         <div className="logo"><a href="index.html"><img src="ASSETS/images/logo.png"/></a></div>
         <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
         </button>
         <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav mr-auto">
               <li className="nav-item active">
                  <a className="nav-link" href="index.html">Home</a>
               </li>
               <li className="nav-item">
                  <a className="nav-link" href="health.html">Health</a>
               </li>
               <li className="nav-item">
                  <a className="nav-link" href="medicine.html">Medicine</a>
               </li>
               <li className="nav-item">
                  <a className="nav-link" href="news.html">News</a>
               </li>
               <li className="nav-item">
                  <a className="nav-link" href="client.html">Client</a>
               </li>
               <li className="nav-item">
                  <a className="nav-link" href="contact.html">Contact Us</a>
               </li>
               <li className="nav-item">
                  <a className="nav-link" href="#"><img src="ASSETS/images/search-icon.png"/></a>
               </li>
            </ul>
         </div>
      </nav>
      <div id="main_slider" className="carousel slide" data-ride="carousel">
         <div className="carousel-inner">
            <div className="carousel-item active">
               <div className="banner_section">
                  <div className="container">
                     <div className="row">
                        <div className="col-md-6">
                           <h1 className="banner_taital">Health <br/><span style={{color: "#151515"}}>Care</span></h1>
                           <p className="banner_text">There are many variations of passages of Lorem Ipsum</p>
                           <div className="btn_main">
                              <div className="more_bt"><a href="#">Contact Now</a></div>
                              <div className="contact_bt"><a href="#">Get A Quote</a></div>
                           </div>
                        </div>
                        <div className="col-md-6">
                           <div className="image_1"><img src="ASSETS/images/img-1.png"/></div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div className="carousel-item">
               <div className="banner_section">
                  <div className="container">
                     <div className="row">
                        <div className="col-md-6">
                           <h1 className="banner_taital">Health <br/><span style={{color: "#151515"}}>Care</span></h1>
                           <p className="banner_text">There are many variations of passages of Lorem Ipsum</p>
                           <div className="btn_main">
                              <div className="more_bt"><a href="#">Contact Now</a></div>
                              <div className="contact_bt"><a href="#">Get A Quote</a></div>
                           </div>
                        </div>
                        <div className="col-md-6">
                           <div className="image_1"><img src="ASSETS/images/img-1.png"/></div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div className="carousel-item">
               <div className="banner_section">
                  <div className="container">
                     <div className="row">
                        <div className="col-md-6">
                           <h1 className="banner_taital">Health <br/><span style={{color: "#151515"}}>Care</span></h1>
                           <p className="banner_text">There are many variations of passages of Lorem Ipsum</p>
                           <div className="btn_main">
                              <div className="more_bt"><a href="#">Contact Now</a></div>
                              <div className="contact_bt"><a href="#">Get A Quote</a></div>
                           </div>
                        </div>
                        <div className="col-md-6">
                           <div className="image_1"><img src="ASSETS/images/img-1.png"/></div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <a className="carousel-control-prev" href="#main_slider" role="button" data-slide="prev">
            <i className="fa fa-long-arrow-left" style={{fontSize: "24px", paddingTop: "4px"}}></i>
         </a>
         <a className="carousel-control-next" href="#main_slider" role="button" data-slide="next">
            <i className="fa fa-long-arrow-right" style={{fontSize: "24px", paddingTop: "4px"}}></i>
         </a>
      </div>
   </div>
   {/* <!-- banner section end -->
   <!-- health section start --> */}
   <div className="health_section layout_padding">
      <div className="container">
         <h1 className="health_taital">Best Of Health care for you</h1>
         <p className="health_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis</p>
         <div className="health_section layout_padding">
            <div className="row">
               <div className="col-sm-7">
                  <div className="image_main">
                     <div className="main">
                        <img src="ASSETS/images/img-2.png" alt="Avatar" className="image" style={{width: "100%"}} />
                     </div>
                     <div className="middle">
                        <div className="text"><img src="ASSETS/images/icon-1.png" style={{width: "40px"}} /></div>
                     </div>
                  </div>
               </div>
               <div className="col-sm-5">
                  <div className="image_main_1">
                     <div className="main">
                        <img src="ASSETS/images/img-3.png" alt="Avatar" className="image" style={{width: "100%"}} />
                     </div>
                     <div className="middle">
                        <div className="text"><img src="ASSETS/images/icon-1.png" style={{width: "40px"}}/></div>
                     </div>
                  </div>
               </div>
            </div>
            <div className="getquote_bt_1"><a href="#">Read More <span><img src="ASSETS/images/right-arrow.png"/></span></a></div>
         </div>
      </div>
   </div>
   {/* <!-- health section end -->
   <!-- knowledge section end --> */}
   <div className="knowledge_section layout_padding">
      <div className="container">
         <div className="knowledge_main">
            <div className="left_main">
               <h1 className="knowledge_taital">Knowledge of center</h1>
               <p className="knowledge_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam</p>
            </div>
            <div className="right_main">
               <div className="play_icon"><a href="#"><img src="ASSETS/images/play-icon.png"/></a></div>
            </div>
         </div>
      </div>
   </div>
   {/* <!-- knowledge section end -->
   <!-- news section start --> */}
   <div className="news_section layout_padding">
      <div className="container">
         <h1 className="health_taital">Why choose 24hr home care</h1>
         <p className="health_text">labore et dolore magna aliqua. Ut enim ad minim veniam</p>
         <div className="news_section_2 layout_padding">
            <div className="row">
               <div className="col-lg-4 col-sm-6">
                  <div className="box_main">
                     <div className="icon_1"><img src="ASSETS/images/icon-2.png"/></div>
                     <h4 className="daily_text">Daily care experts</h4>
                  </div>
               </div>
               <div className="col-lg-4 col-sm-6">
                  <div className="box_main active">
                     <div className="icon_1"><img src="ASSETS/images/icon-3.png"/></div>
                     <h4 className="daily_text_1">Available 24/7</h4>
                  </div>
               </div>
               <div className="col-lg-4 col-sm-6">
                  <div className="box_main">
                     <div className="icon_1"><img src="ASSETS/images/icon-4.png"/></div>
                     <h4 className="daily_text_1">Balanced care</h4>
                  </div>
               </div>
            </div>
         </div>
         <div className="getquote_bt"><a href="#">Get A Quote <span><img src="ASSETS/images/right-arrow.png"/></span></a></div>
      </div>
   </div>
   {/* <!-- news section end -->
   <!-- contact section start --> */}
   <div className="contact_section layout_padding">
      <div className="container">
         <h1 className="contact_taital">What we do</h1>
         <div className="news_section_2">
            <div className="row">
               <div className="col-md-6">
                  <div className="icon_main">
                     <div className="icon_7"><img src="ASSETS/images/icon-7.png"/></div>
                     <h4 className="diabetes_text">Diabetes and obesity Counselling </h4>
                  </div>
                  <div className="icon_main">
                     <div className="icon_7"><img src="ASSETS/images/icon-5.png"/></div>
                     <h4 className="diabetes_text">Obstetrics and Gynsecology</h4>
                  </div>
                  <div className="icon_main">
                     <div className="icon_7"><img src="ASSETS/images/icon-6.png"/></div>
                     <h4 className="diabetes_text">Surgical and medical Oncology</h4>
                  </div>
               </div>
               <div className="col-md-6">
                  <div className="contact_box">
                     <h1 className="book_text">Book Appoinment</h1>
                     <input type="text" className="Email_text" placeholder="Name" name="Name"/>
                     <input type="text" className="Email_text" placeholder="Name" name="Name"/>
                     <textarea className="massage-bt" placeholder="Massage" rows="5" id="comment" name="Massage"></textarea>
                     <div className="send_bt"><a href="#">SEND</a></div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   {/* <!-- contact section end -->
   <!-- client section start --> */}
   <div className="client_section layout_padding">
      <div id="my_slider" className="carousel slide" data-ride="carousel">
         <div className="carousel-inner">
            <div className="carousel-item active">
               <div className="container">
                  <h1 className="client_taital">What People Say</h1>
                  <p className="client_text">It is a long established fact that a reader will be distracted </p>
                  <div className="client_section_2">
                     <div className="client_left">
                        <div><img src="ASSETS/images/client-img.png" className="client_img"/></div>
                     </div>
                     <div className="client_right">
                        <h3 className="distracted_text">Distracted by</h3>
                        <p className="lorem_text">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters</p>
                        <div className="quote_icon"><img src="ASSETS/images/quote-icon.png"/></div>
                     </div>
                  </div>
               </div>
            </div>
            <div className="carousel-item">
               <div className="container">
                  <h1 className="client_taital">What People Say</h1>
                  <p className="client_text">It is a long established fact that a reader will be distracted </p>
                  <div className="client_section_2">
                     <div className="client_left">
                        <div><img src="ASSETS/images/client-img.png" className="client_img"/></div>
                     </div>
                     <div className="client_right">
                        <h3 className="distracted_text">Distracted by</h3>
                        <p className="lorem_text">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters</p>
                        <div className="quote_icon"><img src="ASSETS/images/quote-icon.png"/></div>
                     </div>
                  </div>
               </div>
            </div>
            <div className="carousel-item">
               <div className="container">
                  <h1 className="client_taital">What People Say</h1>
                  <p className="client_text">It is a long established fact that a reader will be distracted </p>
                  <div className="client_section_2">
                     <div className="client_left">
                        <div><img src="ASSETS/images/client-img.png" className="client_img"/></div>
                     </div>
                     <div className="client_right">
                        <h3 className="distracted_text">Distracted by</h3>
                        <p className="lorem_text">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters</p>
                        <div className="quote_icon"><img src="ASSETS/images/quote-icon.png"/></div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <a className="carousel-control-prev" href="#my_slider" role="button" data-slide="prev">
            <i className="fa fa-long-arrow-left" style={{fontSize: "24px", paddingTop: "4px"}}></i>
         </a>
         <a className="carousel-control-next" href="#my_slider" role="button" data-slide="next">
            <i className="fa fa-long-arrow-right" style={{fontSize: "24px", paddingTop: "4px"}}></i>
         </a>
      </div>
   </div>
   {/* <!-- client section end -->
   <!-- footer section start --> */}
   <div className="footer_section layout_padding">
      <div className="container">
         <div className="row">
            <div className="col-lg-3 col-sm-6">
               <div className="footer_logo"><a href="index.html"><img src="ASSETS/images/footer-logo.png"/></a></div>
               <h1 className="adderss_text">Contact Us</h1>
               <div className="map_icon"><img src="ASSETS/images/map-icon.png"/><span className="paddlin_left_0">Page when looking at its</span></div>
               <div className="map_icon"><img src="ASSETS/images/call-icon.png"/><span className="paddlin_left_0">+7586656566</span></div>
               <div className="map_icon"><img src="ASSETS/images/mail-icon.png"/><span className="paddlin_left_0">volim@gmail.com</span></div>
            </div>
            <div className="col-lg-3 col-sm-6">
               <h1 className="adderss_text">Doctors</h1>
               <div className="hiphop_text_1">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour,</div>
            </div>
            <div className="col-lg-3 col-sm-6">
               <h1 className="adderss_text">Useful Links</h1>
               <div className="Useful_text">There are many variations of passages of Lorem Ipsum available, but the majority have suffered ,</div>
            </div>
            <div className="col-lg-3 col-sm-6">
               <h1 className="adderss_text">Newsletter</h1>
               <input type="text" className="Enter_text" placeholder="Enter your Emil" name="Enter your Emil"/>
               <div className="subscribe_bt"><a href="#">Subscribe</a></div>
               <div className="social_icon">
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
   <div className="copyright_section">
      <div className="container">
         <p className="copyright_text">2019 All Rights Reserved. Design by <a href="https://html.design">Free html Templates</a></p>
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
   {/* <!-- javascript --> */}
   <script src="ASSETS/js/owl.carousel.js"></script>
   <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
        </Fragment>
    )
}

export default Index;