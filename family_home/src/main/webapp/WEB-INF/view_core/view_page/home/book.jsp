<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<head>
	<title>index</title>

</head>
<body>
<link rel="stylesheet" href="${ctx}/common/bower/materialize-css/dist/css/materialize.css">
<!--Let browser know website is optimized for mobile-->
<link rel="stylesheet" href="${ctx}/common/css/home/book.css">
<link rel="stylesheet" href="${ctx}/common/bower/materialize-css/dist/icon/material-icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

<!--about-->
<div class="about" id="about">
	<div class="container center-align">
		<h4 class="amber-text" id="bookTitle">推荐</h4>
	</div>
	<hr>
	<div class="col offset-s12 center-align" style="margin-top: 2em;">
		<a class="chip hoverable" href="#" onclick="refreshClassify('1','移动开发')">
			<img src="${ctx}/common/images/home/tag-1.png" alt="Contact Person">
			移动开发<span id="classify_1"></span>
		</a>
		<a class="chip hoverable" href="#" onclick="refreshClassify('2','架构')">
			<img src="${ctx}/common/images/home/tag-2.png" alt="Contact Person">
			架构<span id="classify_2"></span>
		</a>
		<a class="chip hoverable" href="#" onclick="refreshClassify('3','云计算/大数据')">
			<img src="${ctx}/common/images/home/tag-3.png" alt="Contact Person">
			云计算/大数据<span id="classify_3"></span>
		</a>
		<a class="chip hoverable" href="#" onclick="refreshClassify('4','互联网')">
			<img src="${ctx}/common/images/home/tag-4.png" alt="Contact Person">
			互联网<span id="classify_4"></span>
		</a>
		<a class="chip hoverable" href="#" onclick="refreshClassify('5','运维')">
			<img src="${ctx}/common/images/home/tag-5.png" alt="Contact Person">
			运维<span id="classify_5"></span>
		</a>
		<a class="chip hoverable" href="#" onclick="refreshClassify('6','数据库')">
			<img src="${ctx}/common/images/home/tag-6.png" alt="Contact Person">
			数据库<span id="classify_6"></span>
		</a>
		<a class="chip hoverable" href="#" onclick="refreshClassify('7','前端')">
			<img src="${ctx}/common/images/home/tag-7.png" alt="Contact Person">
			前端<span id="classify_7"></span>
		</a>
		<a class="chip hoverable" href="#" onclick="refreshClassify('8','编程语言')">
			<img src="${ctx}/common/images/home/tag-8.png" alt="Contact Person">
			编程语言<span id="classify_8"></span>
		</a>
		<a class="chip hoverable" href="#" onclick="refreshClassify('9','研发管理')">
			<img src="${ctx}/common/images/home/tag-9.png" alt="Contact Person">
			研发管理<span id="classify_9"></span>
		</a>
		<a class="chip hoverable" href="#"  onclick="refreshClassify('0','综合')">
			<img src="${ctx}/common/images/home/tag-10.png" alt="Contact Person">
			综合<span id="classify_0"></span>
		</a>
	</div>
	<div class="container" style="margin-top: 2em;" id="bookList">

		<!-- Page Content goes here -->


	</div>
	<ul class="pagination center-align" id="pageEffect">
		<!--<li class="waves-effect"><a href="#!"><span aria-hidden="true">←</span>Previous</a></li>
		<li class="waves-effect"><a href="#!">Next<span aria-hidden="true">→</span></a></li>-->
	</ul>
</div>
<script src="${ctx}/common/bower/materialize-css/dist/js/materialize.min.js"></script>
<script src="${ctx}/page_resource/home/js/book.js"></script>
<script type="text/javascript">
</script>

</body>