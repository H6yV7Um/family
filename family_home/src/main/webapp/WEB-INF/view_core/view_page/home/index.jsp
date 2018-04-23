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
		<h3 class="title">Welcome to <span>Snapshot</span></h3>
		<div class="col-md-4 about-left wow fadeInLeft animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<img src="images/img7.jpg" alt="" class="img-responsive" />
		</div>
		<div class="col-md-8 about-right wow fadeInRight animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<h4>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin established fact that a reader will be distracted Lorem Ipsum when looking at its layout.</h4>
			<p>Lorem Ipsum was popularised in the 1960s with the release of Letraset sheet content of
				a page when looking at its layout The point of using Lorem Ipsum is that it has a
				more-or-less normal distribution is that it has a more of letters, as opposed to using
				'Content here, content here', making it look like readable English. Many desktop
				publishing packages and web page editors now.</p>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!--//about-->
<!-- about-bottom -->
<div class="about-bottom">
	<div class="container">
		<div class="about-bottom-grid">
			<h3>Photographer</h3>
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
					<div class='divLeftImage' style='background:url(images/1.jpg) -89px 0px;'></div>
					<div class='divLeftShadow'></div>
				</div>
				<div class='divRight' style='left:0px;background-image:url(images/1.jpg);'></div>
			</div>
		</div>
		<div class="about-bottom-grid1">
			<div class='thumb'>
				<div class='someContent'>
					<div class='content'>This reminds me of a goldfish in a big blue sea. Beautiful color contrasts.   </div>
				</div>
				<div class='divLeft' style='left:0px'>
					<div class='divLeftImage' style='background:url(images/2.jpg) -89px 0px;'></div>
					<div class='divLeftShadow'></div>
				</div>
				<div class='divRight' style='left:0px;background-image:url(images/2.jpg);'></div>
			</div>
		</div>
		<div class="about-bottom-grid1">
			<div class='thumb'>
				<div class='someContent'>
					<div class='content'>This reminds me of a goldfish in a big blue sea. Beautiful color contrasts.   </div>
				</div>
				<div class='divLeft' style='left:0px'>
					<div class='divLeftImage' style='background:url(images/3.jpg) -89px 0px;'></div>
					<div class='divLeftShadow'></div>
				</div>
				<div class='divRight' style='left:0px;background-image:url(images/3.jpg);'></div>
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
					<img class="img-responsive" src="images/img8.jpg" alt="" />
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
					<img class="img-responsive" src="images/img9.jpg" alt=""/>
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
					<img class="img-responsive" src="images/img10.jpg" alt=""  />
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
					<img class="img-responsive" src="images/img11.jpg" alt="" />
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
<!--special-->
<div class="special">
	<div class="container">
		<div class="col-md-5 special-grids wow fadeInLeft animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<h3>Our Specialization</h3>
			<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident.</p>
			<h6>Our facilities</h6>
			<div class="progress">
				<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
					60%
				</div>
			</div>
			<h6>Experienced staff</h6>
			<div class="progress">
				<div class="progress-bar" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%;">
					80%
				</div>
			</div>
			<h6>Free consultations</h6>
			<div class="progress">
				<div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width:90%;">
					90%
				</div>
			</div>
			<h6>Advanced lab tests</h6>
			<div class="progress">
				<div class="progress-bar" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%;">
					75%
				</div>
			</div>
		</div>
		<div class="col-md-7 special-grids wow fadeInRight animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<h3>What We do</h3>
			<div class="embed-responsive embed-responsive-16by9">
				<iframe src="https://player.vimeo.com/video/25503274" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
			</div>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!--//special-->
<!--services-->
<div class="services" id="services">
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
		<h3 class="title">Snapshot <span>Photo Gallery</span></h3>
		<div class="portfolio-grids">
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/img2.jpg" data-lightbox="example-set" data-title="">
					<img src="images/img2.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/img3.jpg" data-lightbox="example-set" data-title="">
					<img src="images/img3.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/img4.jpg" data-lightbox="example-set" data-title="">
					<img src="images/img4.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/img5.jpg" data-lightbox="example-set" data-title="">
					<img src="images/img5.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/img6.jpg" data-lightbox="example-set" data-title="">
					<img src="images/img6.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/img7.jpg" data-lightbox="example-set" data-title="">
					<img src="images/img7.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/img4.jpg" data-lightbox="example-set" data-title="">
					<img src="images/img4.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/img1.jpg" data-lightbox="example-set" data-title="">
					<img src="images/img1.jpg" class="img-responsive" alt=""/>
					<div class="mask">
						<p>Duis aute irure dolor in reprehenderit in voluptate velit cillum dolore fugiat.</p>
					</div>
				</a>
			</div>
			<div class="col-md-4 port-grids view view-fourth">
				<a class="example-image-link" href="images/img5.jpg" data-lightbox="example-set" data-title="">
					<img src="images/img5.jpg" class="img-responsive" alt=""/>
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
		<h3 class="title">Snapshot <span>Testimonials</span></h3>
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