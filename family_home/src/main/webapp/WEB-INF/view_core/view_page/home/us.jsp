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
<!-- industrial-banner-bottom -->
<!--
<div class="industrial-banner-bottom">
	<div class="container">
		<div class="col-md-8 industrial-banner-bottom-left">
			<h3>we provide the best service to make successful your dreams</h3>
			<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
				fugiat nulla pariatur.</p>
		</div>
		<div class="col-md-4 industrial-banner-bottom-right">
			<a href="#working-hours" class="scroll"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span> support helpline</a>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>-->
<!-- //industrial-banner-bottom -->
<!--about-->
<div class="about" id="about">
	<div class="container">
		<h3 class="title">Welcome to <span>yxyqcy</span></h3>
		<div class="col-md-4 about-left wow fadeInLeft animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<img src="${ctx}/common/images/home/yxyqcy1.jpg" alt="" class="img-responsive" />
		</div>
		<div class="col-md-8 about-right about-industrial-right wow fadeInRight animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<h4>2016-01-26号</h4>
			<p>Lorem Ipsum was popularised in the 1960s with the release of Letraset sheet content of
				a page when looking at its layout The point of using Lorem Ipsum is that it has a
				more-or-less normal distribution is that it has a more of letters.</p>
			<h5>Some Important <span>里程碑</span></h5>
			<ul>
				<li>第一场电影&nbsp;&nbsp;唐人街探案</li>
				<li>第一次牵手&nbsp;&nbsp;美人鱼</li>
				<li>第一次旅行&nbsp;&nbsp;张家界</li>
			</ul>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!--//about-->
<!-- about-bottom -->
<div class="about-bottom">
	<div class="container">
		<div class="about-bottom-grid about-wedding-bottom-grid about-industrial-bottom-grid">
			<h3>Planning</h3>
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
					<div class='divLeftImage' style='background:url(images/30.jpg) -89px 0px;'></div>
					<div class='divLeftShadow'></div>
				</div>
				<div class='divRight' style='left:0px;background-image:url(images/30.jpg);'></div>
			</div>
		</div>
		<div class="about-bottom-grid1">
			<div class='thumb'>
				<div class='someContent'>
					<div class='content'>This reminds me of a goldfish in a big blue sea. Beautiful color contrasts.   </div>
				</div>
				<div class='divLeft' style='left:0px'>
					<div class='divLeftImage' style='background:url(images/31.jpg) -89px 0px;'></div>
					<div class='divLeftShadow'></div>
				</div>
				<div class='divRight' style='left:0px;background-image:url(images/31.jpg);'></div>
			</div>
		</div>
		<div class="about-bottom-grid1">
			<div class='thumb'>
				<div class='someContent'>
					<div class='content'>This reminds me of a goldfish in a big blue sea. Beautiful color contrasts.   </div>
				</div>
				<div class='divLeft' style='left:0px'>
					<div class='divLeftImage' style='background:url(images/32.jpg) -89px 0px;'></div>
					<div class='divLeftShadow'></div>
				</div>
				<div class='divRight' style='left:0px;background-image:url(images/32.jpg);'></div>
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
					<img class="img-responsive" src="images/img11.jpg" alt="">
					<div class="captn">
						<h4>Adam Laura</h4>
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
					<img class="img-responsive" src="images/34.jpg" alt="">
					<div class="captn">
						<h4>Kerl Paul</h4>
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
<div class="special industrial-special">
	<div class="container">
		<div class="col-md-5 special-grids wow fadeInLeft animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
			<img src="images/35.jpg" alt=" " class="img-responsive" />
		</div>
		<div class="col-md-7 special-grids">
			<h3>progress bars are using for calculating work status</h3>
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
		<div class="clearfix"> </div>
	</div>
</div>
<!--//special-->
<!--services-->
<div class="services services3" id="services">
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
					<span class="glyphicon glyphicon-fire" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>dignissimos ducimus</h4>
					<p>Vero eos et accusamus et iusto odio dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-globe" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>accusamus et iusto</h4>
					<p>Vero eos et accusamus et iusto odio dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
				</div>
				<div class="caption work-captn">
					<h4>quo voluptas nulla</h4>
					<p>odioero eos et accusamus et iusto dignissimos ducimus</p>
				</div>
			</div>
			<div class="col-sm-6 col-md-4 work-row-grids wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
				<div class="work-grids-img">
					<span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span>
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
<div class="working-hours" id="working-hours">
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
<div class="steps">
	<div class="container">
		<h3 class="title">Steps in <span>Planning</span></h3>
		<div class="steps-grids">
			<div class="col-md-6 steps-grid-left">
				<div class="steps-grid-left-grid">
					<div class="steps-grid-left1">
						<h4>quia voluptas sit aspernatur</h4>
						<p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia.</p>
					</div>
					<div class="steps-grid-left2">
						<span class="glyphicon glyphicon-tower" aria-hidden="true"></span>
					</div>
					<div class="clearfix"> </div>
				</div>
				<div class="steps-grid-left-grid">
					<div class="steps-grid-left1">
						<h4>sunt in culpa qui officia</h4>
						<p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia.</p>
					</div>
					<div class="steps-grid-left2">
						<span class="glyphicon glyphicon-oil" aria-hidden="true"></span>
					</div>
					<div class="clearfix"> </div>
				</div>
				<div class="steps-grid-left-grid">
					<div class="steps-grid-left1">
						<h4>occaecat cupidatat non sint</h4>
						<p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia.</p>
					</div>
					<div class="steps-grid-left2">
						<span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
			<div class="col-md-6 steps-grid-left">
				<div class="steps-grid-left-grid">
					<div class="steps-grid-left1">
						<h4>cumque nihil impedit quo minus</h4>
						<p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia.</p>
					</div>
					<div class="steps-grid-left2">
						<span class="glyphicon glyphicon-link" aria-hidden="true"></span>
					</div>
					<div class="clearfix"> </div>
				</div>
				<div class="steps-grid-left-grid">
					<div class="steps-grid-left1">
						<h4>officia deserunt mollitia animi</h4>
						<p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia.</p>
					</div>
					<div class="steps-grid-left2">
						<span class="glyphicon glyphicon-import" aria-hidden="true"></span>
					</div>
					<div class="clearfix"> </div>
				</div>
				<div class="steps-grid-left-grid">
					<div class="steps-grid-left1">
						<h4>voluptates repudiandae sint et</h4>
						<p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia.</p>
					</div>
					<div class="steps-grid-left2">
						<span class="glyphicon glyphicon-compressed" aria-hidden="true"></span>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
</div>
<!--//portfolio-->
<!--testimonials-->
<div class="testimonials">
	<div class="container">
		<h3 class="title">Planning <span>Testimonials</span></h3>
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