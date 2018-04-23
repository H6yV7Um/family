<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<head>
    <title>index</title>

</head>
<body>
<div class="banner">
<!--banner-->
	<ul class="rslides" id="slider3">
		<li class="">
			<img src="${ctx}/common/images/home/banner.jpg" />

		</li>
		<li>
			<img src="${ctx}/common/images/home/banner1.jpg" />

		</li>
		<li>
			<img src="${ctx}/common/images/home/banner2.jpg" />

		</li>
	</ul>
</div>
<!--//banner-->
<!--about-->
<div class="about" id="about">
	<div class="container">
		<h3 class="title">Welcome to <span>yx </span></h3>
		<div class="col-md-4 about-left wow fadeInLeft animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<div class="about-fashion-left">
				<img src="${ctx}/common/images/home/yx1.jpg" alt="" class="img-responsive" />
				<article class="text css3-3">
					<h3><a href="#" class="css3-3">尹雪</a></h3>
				</article>
			</div>
		</div>
		<div class="col-md-8 about-right about-fashion-right wow fadeInRight animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<h4>尹雪's growing course</h4>
			<p>1998~2004清源道小学</p>
			<p>2004~2007民族中学</p>
			<p>2007~2010天津5中</p>
			<h6>2010~2014 天津科技大学 计算机科学与技术</h6>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!--//about-->
<!-- about-bottom -->
<div class="about-bottom about-bottom-fashion">
	<div class="container">
		<h3>New Styles</h3>
		<section class="slider">
			<div class="flexslider">
				<ul class="slides">
					<li>
						<div class="about-bottom-fashion-grids">
							<div class="col-md-3 about-bottom-fashion-grid">
								<div class="hover08 column">
									<div>
										<figure><img src="images/37.jpg" alt=" " class="img-responsive" /></figure>
									</div>
								</div>
							</div>
							<div class="col-md-3 about-bottom-fashion-grid">
								<div class="hover08 column">
									<div>
										<figure><img src="images/38.jpg" alt=" " class="img-responsive" /></figure>
									</div>
								</div>
							</div>
							<div class="col-md-3 about-bottom-fashion-grid">
								<div class="hover08 column">
									<div>
										<figure><img src="images/39.jpg" alt=" " class="img-responsive" /></figure>
									</div>
								</div>
							</div>
							<div class="col-md-3 about-bottom-fashion-grid">
								<div class="hover08 column">
									<div>
										<figure><img src="images/40.jpg" alt=" " class="img-responsive" /></figure>
									</div>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
					</li>
					<li>
						<div class="about-bottom-fashion-grids">
							<div class="col-md-3 about-bottom-fashion-grid">
								<div class="hover08 column">
									<div>
										<figure><img src="images/41.jpg" alt=" " class="img-responsive" /></figure>
									</div>
								</div>
							</div>
							<div class="col-md-3 about-bottom-fashion-grid">
								<div class="hover08 column">
									<div>
										<figure><img src="images/42.jpg" alt=" " class="img-responsive" /></figure>
									</div>
								</div>
							</div>
							<div class="col-md-3 about-bottom-fashion-grid">
								<div class="hover08 column">
									<div>
										<figure><img src="images/37.jpg" alt=" " class="img-responsive" /></figure>
									</div>
								</div>
							</div>
							<div class="col-md-3 about-bottom-fashion-grid">
								<div class="hover08 column">
									<div>
										<figure><img src="images/38.jpg" alt=" " class="img-responsive" /></figure>
									</div>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
					</li>
				</ul>
			</div>
		</section>
		<!--FlexSlider-->
		<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
		<script defer src="js/jquery.flexslider.js"></script>
		<script type="text/javascript">
			$(window).load(function(){
				$('.flexslider').flexslider({
					animation: "slide",
					start: function(slider){
						$('body').removeClass('loading');
					}
				});
			});
		</script>
		<!--End-slider-script-->
	</div>
</div>
<div class="about-bottom about-bottom-fashion1">
	<div class="container">
		<div class="about-bottom-fashion1-grids">
			<h3>sign up for our newsletter</h3>
			<p>Enter your email and subscribe to our newsletter and never miss any updates.</p>
			<form>
				<input type="email" value="Email..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email...';}" required="">
				<input type="submit" value="send">
			</form>
		</div>
	</div>
</div>
<!-- //about-bottom -->
<!--team-->
<div class="team" id="team">
	<div class="container">
		<h3 class="title title1">Meet Our <span>Team</span></h3>
		<div class="team-info">
			<div class="col-md-3 team-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<ul class="social-networks square spin-icon team-icons">
					<li><a href="#" class="icon-linkedin"></a></li>
					<li><a href="#" class="icon-twitter"></a></li>
					<li><a href="#" class="icon-facebook"></a></li>
				</ul>
				<a href="#">
					<img class="img-responsive" src="images/img8.jpg" alt="">
					<div class="captn">
						<h4>Gem Parker</h4>
						<p>Aenean puivinar ac enimet posuere tincidunt velit Utin tincidunt</p>
					</div>
				</a>
			</div>
			<div class="col-md-3 team-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<ul class="social-networks square spin-icon team-icons">
					<li><a href="#" class="icon-linkedin"></a></li>
					<li><a href="#" class="icon-twitter"></a></li>
					<li><a href="#" class="icon-facebook"></a></li>
				</ul>
				<a href="#">
					<img class="img-responsive" src="images/33.jpg" alt="">
					<div class="captn">
						<h4>Kerl Paul</h4>
						<p>Aenean puivinar ac enimet posuere tincidunt velit Utin tincidunt</p>
					</div>
				</a>
			</div>
			<div class="col-md-3 team-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<ul class="social-networks square spin-icon team-icons">
					<li><a href="#" class="icon-linkedin"></a></li>
					<li><a href="#" class="icon-twitter"></a></li>
					<li><a href="#" class="icon-facebook"></a></li>
				</ul>
				<a href="#">
					<img class="img-responsive" src="images/img10.jpg" alt="">
					<div class="captn">
						<h4>James Cam</h4>
						<p>Aenean puivinar ac enimet posuere tincidunt velit Utin tincidunt</p>
					</div>
				</a>
			</div>
			<div class="col-md-3 team-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<ul class="social-networks square spin-icon team-icons">
					<li><a href="#" class="icon-linkedin"></a></li>
					<li><a href="#" class="icon-twitter"></a></li>
					<li><a href="#" class="icon-facebook"></a></li>
				</ul>
				<a href="#">
					<img class="img-responsive" src="images/img11.jpg" alt="">
					<div class="captn">
						<h4>Adam Laura</h4>
						<p>Aenean puivinar ac enimet posuere tincidunt velit Utin tincidunt</p>
					</div>
				</a>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
</div>
<!--//team-->
<!--services-->
<div class="services services4" id="services">
	<div class="container">
		<h3 class="title">Special Offered <span>Services</span></h3>
		<div class="row work-row">
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>labore et dolore magna</h4>
					<p>Vero eos et accusamus et iusto odio dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>dignissimos ducimus</h4>
					<p>Vero eos et accusamus et iusto odio dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-print" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>accusamus et iusto</h4>
					<p>Vero eos et accusamus et iusto odio dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-facetime-video" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>quo voluptas nulla</h4>
					<p>odioero eos et accusamus et iusto dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-picture" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>occaecat cupidatat non</h4>
					<p>odioero eos et accusamus et iusto dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>fugiat nulla pariatur</h4>
					<p>odioero eos et accusamus et iusto dignissimos ducimus</p>
				</div>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
</div>
<!--//services-->
<!--portfolio-->
<div class="portfolio" id="gallery">
	<div class="container">
		<h3 class="title">Styling Salon <span>Photo Gallery</span></h3>
		<div class="portfolio-grids">
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/43.jpg" data-lightbox="example-set" data-title="">
					<img src="images/43.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/44.jpg" data-lightbox="example-set" data-title="">
					<img src="images/44.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/45.jpg" data-lightbox="example-set" data-title="">
					<img src="images/45.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/46.jpg" data-lightbox="example-set" data-title="">
					<img src="images/46.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/47.jpg" data-lightbox="example-set" data-title="">
					<img src="images/47.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/48.jpg" data-lightbox="example-set" data-title="">
					<img src="images/48.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/45.jpg" data-lightbox="example-set" data-title="">
					<img src="images/45.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/44.jpg" data-lightbox="example-set" data-title="">
					<img src="images/44.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/43.jpg" data-lightbox="example-set" data-title="">
					<img src="images/43.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="clearfix"> </div>
			<script src="${ctx}/common/js/home/lightbox-plus-jquery.min.js"> </script>
		</div>
	</div>
</div>
<!--//portfolio-->
<!--testimonials-->
<div class="testimonials">
	<div class="container">
		<h3 class="title">Styling Salon <span>Testimonials</span></h3>
		<div class="sap_tabs">
			<div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
				<ul class="resp-tabs-list wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
					<li class="resp-tab-item"><span><img src="images/img12.jpg" alt=""/></span></li>
					<li class="resp-tab-item"><span><img src="images/img13.jpg" alt=""/></span></li>
					<li class="resp-tab-item"><span><img src="images/img14.jpg" alt=""/></span></li>
				</ul>
				<div class="clearfix"> </div>
				<div class="resp-tabs-container wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
					<div class="tab-1 resp-tab-content">
						<div class="view1 view-first">
							<h5>FILAN FISTEKU</h5>
							<p>Donec libero dui, scelerisque ac augue id, tristique ullamcorper elit. Nam ultrices, lacus vitae adipiscing aliquet, metus ipsum imperdiet libero, vitae dignissim sapien diam ac nibh convallis.</p>
						</div>
					</div>
					<div class="tab-1 resp-tab-content">
						<div class="view1 view-first">
							<h5>ULLAMCORPER FILAN </h5>
							<p>Scelerisque ac augue id Donec libero dui, , tristique ullamcorper elit. Nam ultrices, lacus vitae adipiscing aliquet, metus ipsum imperdiet libero, vitae dignissim sapien diam ac nibh convallis.</p>
						</div>
					</div>
					<div class="tab-1 resp-tab-content">
						<div class="view1 view-first">
							<h5>SCELERISQUE AUGUE</h5>
							<p>Nam ultrices lacus vitae adipiscing aliquet, metus ipsum imperdiet libero, vitae dignissim sapientristique Donec libero dui, scelerisque ac augue id,  ullamcorper elit,diam ac nibh convallis.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/common/js/home/easyResponsiveTabs.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#horizontalTab').easyResponsiveTabs({
				type: 'default', //Types: default, vertical, accordion
				width: 'auto', //auto or any width like 600px
				fit: true   // 100% fit in a container
			});
		});
	</script>
</div>
<!--//testimonials-->
<!--contact-->
<div id="contact" class="contact-form pt-section">
	<div class="container">
		<h3 class="title">Find Us <span>On Map</span></h3>
		<div class="map wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12105.351653659702!2d-73.9331822244853!3d40.6665229343601!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c24fa5d33f083b%3A0xc80b8f06e177fe62!2sNew+York%2C+NY%2C+USA!5e0!3m2!1sen!2sin!4v1433740873957"></iframe>
		</div>
		<div class="col-md-7 contact-right wow fadeInLeft animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<form>
				<input type="text" value="Name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Name';}" required="">
				<input type="email" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}" required="">
				<input type="text" value="Telephone" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Telephone';}" required="">
				<textarea onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Message...';}" required="">Message...</textarea>
				<input type="submit" value="Submit" >
			</form>
		</div>
		<div class="col-md-5 contact-left wow fadeInRight animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<p>"Lorem Ipsum"is the common name dummy text often used in the design, printing, and type setting industriescommon name dummy text often used in the design, printing, and type setting industries. "</p>
			<ul>
				<li><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>756 global Place, New York.</li>

				<li><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span><a href="mailto:example@mail.com">mail@example.com</a></li>
				<li><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span></span>+123 2222 222</li>
			</ul>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!--//contact-->
</body>