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
		<h3 class="title">Welcome to <span>Chakra</span></h3>
		<div class="col-md-8 about-right wow fadeInRight animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<h4>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin established fact that a reader will be distracted Lorem Ipsum when looking at its layout.</h4>
			<p>Lorem Ipsum was popularised in the 1960s with the release of Letraset sheet content of
				a page when looking at its layout The point of using Lorem Ipsum is that it has a
				more-or-less normal distribution is that it has a more of letters, as opposed to using
				'Content here, content here', making it look like readable English. Many desktop
				publishing packages and web page editors now.</p>
			<div class="about-yoga-grids">
				<div class="col-md-4 about-yoga-grid-left">
					<div class="about-yoga-grid-left1">
						<span></span>
					</div>
					<h5>commodi consequatur</h5>
				</div>
				<div class="col-md-4 about-yoga-grid-left">
					<div class="about-yoga-grid-left2">
						<span></span>
					</div>
					<h5>dolorem ipsum quia</h5>
				</div>
				<div class="col-md-4 about-yoga-grid-left">
					<div class="about-yoga-grid-left3">
						<span></span>
					</div>
					<h5>aut odit aut fugit</h5>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<div class="col-md-4 about-left wow fadeInLeft animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<img src="images/18.jpg" alt="" class="img-responsive" />
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!--//about-->
<!-- about-bottom -->
<div class="about-bottom">
	<div class="container">
		<div class="about-bottom-grid">
			<h3>Yoga</h3>
			<p>Many desktop
				publishing packages and web page editors now.</p>
			<ul class="social-networks square spin-icon">
				<li><a href="#" class="icon-linkedin"></a></li>
				<li><a href="#" class="icon-twitter"></a></li>
				<li><a href="#" class="icon-facebook"></a></li>
			</ul>
		</div>
		<div class="about-bottom-grid1">
			<div class='thumb'>
				<div class='someContent'>
					<div class='content'>This reminds me of a goldfish in a big blue sea. Beautiful color contrasts.   </div>
				</div>
				<div class='divLeft' style='left:0px'>
					<div class='divLeftImage' style='background:url(images/19.jpg) -89px 0px;'></div>
					<div class='divLeftShadow'></div>
				</div>
				<div class='divRight' style='left:0px;background-image:url(images/19.jpg);'></div>
			</div>
		</div>
		<div class="about-bottom-grid1">
			<div class='thumb'>
				<div class='someContent'>
					<div class='content'>This reminds me of a goldfish in a big blue sea. Beautiful color contrasts.   </div>
				</div>
				<div class='divLeft' style='left:0px'>
					<div class='divLeftImage' style='background:url(images/21.jpg) -89px 0px;'></div>
					<div class='divLeftShadow'></div>
				</div>
				<div class='divRight' style='left:0px;background-image:url(images/21.jpg);'></div>
			</div>
		</div>
		<div class="about-bottom-grid1">
			<div class='thumb'>
				<div class='someContent'>
					<div class='content'>This reminds me of a goldfish in a big blue sea. Beautiful color contrasts.   </div>
				</div>
				<div class='divLeft' style='left:0px'>
					<div class='divLeftImage' style='background:url(images/20.jpg) -89px 0px;'></div>
					<div class='divLeftShadow'></div>
				</div>
				<div class='divRight' style='left:0px;background-image:url(images/20.jpg);'></div>
			</div>
		</div>
		<div class="clearfix"> </div>
		<script type="text/javascript">
			window.addEvent('domready',function(){
				$$('div.thumb').each(function(div){

					div.getElement('div.someContent').set('tween', {duration:'700'});
					div.getElement('div.divLeft').set('tween', {duration: '450'});
					div.getElement('div.divRight').set('tween', {duration: '450'});

					div.addEvent('mouseenter',function(e){
						this.getElement('div.divLeft').tween('left','-70px')
						this.getElement('div.divRight').tween('left','80px')
						this.getElement('div.someContent').tween("background-position", "40px 0px");
					})
					div.addEvent('mouseleave',function(e){
						this.getElement('div.divLeft').tween('left','0px')
						this.getElement('div.divRight').tween('left','0px')
						this.getElement('div.someContent').tween("background-position", "0px -167px");

					})
				})
			})
		</script>

	</div>
</div>
<!-- //about-bottom -->
<!--services-->
<div class="services services2" id="services">
	<div class="container">
		<h3 class="title">Special Offered <span>Services</span></h3>
		<div class="row work-row">
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>labore et dolore magna</h4>
					<p>Vero eos et accusamus et iusto odio dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-leaf" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>dignissimos ducimus</h4>
					<p>Vero eos et accusamus et iusto odio dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>accusamus et iusto</h4>
					<p>Vero eos et accusamus et iusto odio dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-tree-deciduous" aria-hidden="true"></span>
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
<!-- working-hours -->
<div class="working-hours">
	<div class="container">
		<div class="col-md-4 working-hours-grid">
			<h3><span class="glyphicon glyphicon-time" aria-hidden="true"></span>Working Hours</h3>
			<ul>
				<li><span>Monday - Friday:</span> 10:00 AM to 7:00 PM</li>
				<li><span>Saturday:</span> 10:00 AM to 1:00 PM</li>
				<li><span>Sunday:</span> 9:00 AM to 12:00 PM</li>
			</ul>
		</div>
		<div class="col-md-4 working-hours-grid">
			<h3><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>get in touch with us</h3>
			<ul>
				<li><span>Address:</span> 123k 4th avenue, New York City, USA.</li>
				<li><span>Phone Number :</span> +0000 929 120</li>
				<li><span>Email:</span> <a href="mailto:info@example.com">info@example.com</a></li>
			</ul>
		</div>
		<div class="col-md-4 working-hours-grid">
			<h3><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>Newsletter</h3>
			<p>Enter your email and subscribe to our newsletter and never miss any updates.</p>
			<form>
				<input type="email" value="Email..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email...';}" required="">
				<input type="submit" value="send">
			</form>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!-- //working-hours -->
<!--portfolio-->
<div class="portfolio" id="gallery">
	<div class="container">
		<h3 class="title">Chakra <span>Photo Gallery</span></h3>
		<div class="portfolio-grids">
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/22.jpg" data-lightbox="example-set" data-title="">
					<img src="images/22.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/23.jpg" data-lightbox="example-set" data-title="">
					<img src="images/23.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/24.jpg" data-lightbox="example-set" data-title="">
					<img src="images/24.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/25.jpg" data-lightbox="example-set" data-title="">
					<img src="images/25.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/26.jpg" data-lightbox="example-set" data-title="">
					<img src="images/26.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/27.jpg" data-lightbox="example-set" data-title="">
					<img src="images/27.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/24.jpg" data-lightbox="example-set" data-title="">
					<img src="images/24.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/23.jpg" data-lightbox="example-set" data-title="">
					<img src="images/23.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/22.jpg" data-lightbox="example-set" data-title="">
					<img src="images/22.jpg" class="img-responsive" alt=""/>
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
		<h3 class="title">Chakra <span>Testimonials</span></h3>
		<div class="sap_tabs">
			<div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
				<ul class="resp-tabs-list wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
					<li class="resp-tab-item"><span><img src="images/16.jpg" alt=""/></span></li>
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