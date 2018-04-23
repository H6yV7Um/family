<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<head>
    <title>index</title>

</head>
<body>
<link href="${ctx}/common/css/home/blog.css" rel="stylesheet">
<script src="${ctx}/page_resource/home/js/blog.js"></script>

<!--about-->
<div class="about" id="about">
	<div class="container"  style="margin-bottom: 20px">
		<h3 class="title">Category <span style="margin-top: .5em" id="classifyName">推荐</span></h3>
		<div class="col-md-8 about-right wow fadeInRight animated" data-wow-delay=".5s"
			 style="visibility: visible; -webkit-animation-delay: .5s;" id="blogs">
		</div>

		<div class="col-md-4 about-left wow fadeInLeft animated" data-wow-delay=".5s"
			 style="visibility: visible; -webkit-animation-delay: .5s;">
			<img src="${ctx}/common/images/home/user7.jpg" alt="" class="img-responsive"/>
			<hr>
			<h5 class="title"><i class="glyphicon glyphicon-list-alt" style="top: 2px;"></i><span
					style="margin-top: .5em;margin-left: .5em;">博文分类</span></h5>

			<div class="row">
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('1','移动开发')" style="border: 1px solid #eee"><span
							class="fa fa-envira span-icon"></span>移动开发<span id="classify_1"></span>
					</button>
				</div>
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('2','架构')" style="border: 1px solid #eee"><span
							class="fa fa-codepen span-icon"></span>架构<span id="classify_2"></span>
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('3','云计算/大数据')" style="border: 1px solid #eee"><span
							class="fa fa-skyatlas span-icon"></span>云计算/大数据<span id="classify_3"></span>
					</button>
				</div>
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('4','互联网')" style="border: 1px solid #eee"><span
							class="fa fa-chrome span-icon"></span>互联网<span id="classify_4"></span>
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('5','运维')" style="border: 1px solid #eee"><span
							class="fa fa-ship span-icon"></span>运维<span id="classify_5"></span>
					</button>
				</div>
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('6','数据库')" style="border: 1px solid #eee"><span
							class="fa fa-server span-icon"></span>数据库<span id="classify_6"></span>
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('7','前端')" style="border: 1px solid #eee"><span
							class="fa fa-magic span-icon"></span>前端<span id="classify_7"></span>
					</button>
				</div>
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('8','编程语言')" style="border: 1px solid #eee"><span
							class="fa fa-gg span-icon"></span>编程语言<span id="classify_8"></span>
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('9','研发管理')" style="border: 1px solid #eee"><span
							class="fa fa-medium span-icon"></span>研发管理<span id="classify_9"></span>
					</button>
				</div>
				<div class="col-md-6">
					<button class="btn btn-default btn-trigger" onclick="refreshClassify('0','综合')" style="border: 1px solid #eee"><span
							class="fa fa-linode span-icon"></span>综合<span id="classify_0"></span>
					</button>
				</div>
			</div>

			<hr>
			<h5 class="title"><i class="glyphicon glyphicon-list-alt" style="top: 2px;"></i><span
					style="margin-top: .5em;margin-left: .5em;">专题订阅</span></h5>
			<div id="rtopicList">

			</div>

		</div>


	</div>


</div>

<script type="text/javascript">
	$(document).ready(function () {
		/*
		 var defaults = {
		 containerID: 'toTop', // fading element id
		 containerHoverID: 'toTopHover', // fading element hover id
		 scrollSpeed: 1200,
		 easingType: 'linear'
		 };
		 */
		if ($(".blog").length < 1) {
			$("#pagi").css("display", "none")
		}


	});


</script>

</body>