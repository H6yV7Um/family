<%--
  Created by family
  User: cy
  Date: 16/12/26
  Time: 下午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:write property='title' /></title>

    <link rel="icon" href="${ctx}/common/ico/family_home.ico" type="image/x-icon"/>
    <link href="${ctx}/common/ui/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" media="all">

    <link href="${ctx}/common/css/home/style.css" type="text/css" rel="stylesheet" media="all">
    <link rel="stylesheet" href="${ctx}/common/css/home/lightbox.css">
    <!-- Custom Theme files -->

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Collective Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //Custom Theme files -->
    <!-- js -->
    <script src="${ctx}/common/js/jQuery/jquery-1.11.1.min.js"></script>
    <!-- //js -->
    <!--animate-->
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${ctx}/common/css/font-awesome/font-awesome_home.css">

    <link href="${ctx}/common/css/home/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="${ctx}/common/js/home/wow.min.js"></script>

    <!-- Sweet alert -->
    <link href="${ctx}/common/ui/sweetalert/css/sweetalert.css" rel="stylesheet">
    <script src="${ctx}/common/ui/sweetalert/js/sweetalert.min.js"></script>
    <!-- cyUtil -->
    <script src="${ctx}/common/js/util/cyUtil.js"></script>
    <script>
        family_ns.contextPath = "${ctx}";
    </script>
    <script src="${ctx}/page_resource/template/home.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- image-hover -->
    <script type="text/javascript" src="${ctx}/common/js/home/mootools-yui-compressed.js"></script>
    <!-- //image-hover -->
    <!-- <link href='http://fonts.useso.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'> -->

</head>
<body>
<!-- 判断是否为执行结果 返回页面 -->
<script>
    $(function() {
        function pushState(){
            /**
             * 修改浏览器参数 mf  避免刷新 提示操作成功
             */
            var forwardUrl = "?mf=2";
            var address = window.location.href;
            address = address.substr(address.indexOf("?")+1);
            var arr = address.split("&"); //各个参数放到数组里
            for(var i=0;i < arr.length;i++){
                var num = arr[i].indexOf("=");
                if(num>0){
                    if("mf" != arr[i].substring(0,num)){
                        forwardUrl += "&" + arr[i].substring(0,num) + "="+arr[i].substr(num+1);
                    }
                }
            }
            window.history.pushState('forward', null, forwardUrl);
        }

        //成功
        if("1" == "${messageFlag}"){

            family_ns.operatorAlertSuccess(family_ns.sucMessage);
            /**
             * 修改浏览器参数 mf  避免刷新 提示操作成功
             */
            pushState.call(this);
        }
        //失败
        else if("0" == "${messageFlag}"){

            family_ns.operatorAlertError(family_ns.errMessage);
            pushState.call(this);
        }
    });

</script>
<!--header-->
<div class="header">
    <div class="container">
        <div class="header-info navbar-left wow fadeInLeft animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
            <p><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>Information Service : yxyqcy</p>
        </div>

        <form class="navbar-form navbar-right wow fadeInRight animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;">
            <shiro:notAuthenticated>
                <button type="button"   class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">Login</button>
            </shiro:notAuthenticated>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
                <button type="submit" class="btn btn-default" aria-label="Left Align">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                </button>
            </div>
        </form>

        <shiro:authenticated>
            <div class="nav-collapse">
                <ul class="nav pull-right">
                    <li class="divider-vertical"></li>
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle " href="#">
                            <shiro:principal property="loginName"/> <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${ctx}/view/homeBack/index"><i class="icon-user"></i>个人中心</a>
                            </li>
                            <li>
                                <a href="#" onclick="signOut()"><i class="icon-off"></i> Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div> <!-- /nav-collapse -->
        </shiro:authenticated>




    </div>
</div>
<!--//header-->
<!--navigation-->
<div class="top-nav">
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header navbar-left">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <h1><a class="navbar-brand wow bounceIn animated" data-wow-delay=".5s" style="visibility: visible; -webkit-animation-delay: .5s;" href="index.html">yxyqcy</a></h1>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-left" id="navbarHome">
                    <li class="hvr-bounce-to-bottom" homeKey="index"><a href="${ctx}/view/home/index">home</a></li>
                    <li class="hvr-bounce-to-bottom" homeKey="blog"><a href="${ctx}/view/home/blog">blog</a></li>
                    <li class="hvr-bounce-to-bottom" homeKey="book"><a href="${ctx}/view/home/book">book</a></li>
                </ul>
                <div class="clearfix"> </div>
            </div>
        </div>
    </nav>
</div>
<!--navigation-->

<sitemesh:write property='body' />

<!--modal-->
<!-- 登录框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Login</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <label class="col-sm-2 control-label">帐号</label>
                        <div class="col-sm-8">
                            <div  style="width: 90%">
                                <input type="text" class="form-control1" style="width: 90%" id="home_username"  name="username" value=""  placeholder="请输入帐号...">
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block"></p>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-8">
                            <div style="width: 90%">
                                <input type="password" class="form-control1" style="width: 90%" id="home_password"  name="password" value="" placeholder="请输入密码..." onkeydown="enterLogin()">
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <p class="help-block"></p>
                        </div>
                    </div>
                </div>
                <%Boolean login = (Boolean) session.getAttribute("isValidateCodeLogin");%>
                <div class="validateCode <%=login==null ? "hide" : ""%>" id="validateCodeId">
                    <div class="row">
                        <label class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-8">
                            <div style="width: 90%">
                                <tags:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="loginUser()">Login</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!---->
<%@ include file="/WEB-INF/common/copyright_home_footer.jsp" %>

<!-- banner-text Slider starts Here -->
<script src="${ctx}/common/js/home/responsiveslides.min.js"></script>
<script>
    // You can also use "$(window).load(function() {"
    $(function () {
        // Slideshow 3
        $("#slider3").responsiveSlides({
            auto: true,
            pager:true,
            nav:true,
            timeout:5000,
            speed: 2500,
            namespace: "callbacks",
            before: function () {
                $('.events').append("<li>before event fired.</li>");
            },
            after: function () {
                $('.events').append("<li>after event fired.</li>");
            }
        });
    });
    /**
     * enterLogin
     */
    function enterLogin(){
        if (event.keyCode == 13)
        {

            loginUser.call(this);
        }
    }

    $('#myModal').on('shown.bs.modal', function () {
        $("#home_username").focus();
    })
</script>
<!--//End-slider-script -->

<!-- start-smoth-scrolling-->
<script type="text/javascript" src="${ctx}/common/js/home/move-top.js"></script>
<script type="text/javascript" src="${ctx}/common/js/home/easing.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
    });
</script>
<!--//end-smoth-scrolling-->
<!--smooth-scrolling-of-move-up-->
<script type="text/javascript">
    $(document).ready(function() {
        /*
         var defaults = {
         containerID: 'toTop', // fading element id
         containerHoverID: 'toTopHover', // fading element hover id
         scrollSpeed: 1200,
         easingType: 'linear'
         };
         */

        $().UItoTop({ easingType: 'easeOutQuart' });

    });
</script>

<!--//smooth-scrolling-of-move-up-->

<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script src="${ctx}/common/ui/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    activeNavigation.call(this);
</script>
</body>
</html>
