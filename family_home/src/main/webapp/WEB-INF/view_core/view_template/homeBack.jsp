<%--
  Created by family
  User: cy
  Date: 17/01/05
  Time: 下午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title><sitemesh:write property='title' /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />


    <link rel="icon" href="${ctx}/common/ico/family_home.ico" type="image/x-icon"/>

    <link href="${ctx}/common/ui/bootstrap/css/bootstrap_2.0.2.min.css" rel="stylesheet" />
    <link href="${ctx}/common/ui/bootstrap/css/pagination.css" rel="stylesheet" />
    <link href="${ctx}/common/ui/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />

    <!-- Theme style -->
    <link rel="stylesheet" href="${ctx}/common/css/homeBack/AdminLTE.min.css">

    <!--<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet" />-->
    <link href="${ctx}/common/css/font-awesome/font-awesome.css" rel="stylesheet" />
    <link href="${ctx}/common/css/font-awesome/font-awesome_homeBack.css" rel="stylesheet" />
    <link href="${ctx}/common/css/homeBack/adminia.css" rel="stylesheet" />
    <link href="${ctx}/common/css/homeBack/adminia-responsive.css" rel="stylesheet" />

    <link href="${ctx}/common/css/homeBack/pages/dashboard.css" rel="stylesheet" />

    <link href="${ctx}/common/css/homeBack/add.css" rel="stylesheet" />

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

    <!-- jQuery 2.2.3 -->
    <script src="${ctx}/common/js/jQuery/jquery-2.2.3.min.js"></script>
    <!--validator-->
    <script src="${ctx}/common/js/jQuery/jquery.validate.min.js"></script>
    <script src="${ctx}/common/js/jQuery/messages_zh.min.js"></script>
    <!-- Bootstrap 3.3.6 -->
    <script src="${ctx}/common/ui/bootstrap/js/bootstrap.min.js"></script>
    <!-- Sweet alert -->
    <link href="${ctx}/common/ui/sweetalert/css/sweetalert.css" rel="stylesheet">
    <script src="${ctx}/common/ui/sweetalert/js/sweetalert.min.js"></script>
    <!--layer-->
    <script src="${ctx}/common/ui/layer/layer.js"></script>
    <!-- cyUtil -->
    <script src="${ctx}/common/js/util/cyUtil.js"></script>
    <script>
        family_ns.contextPath = "${ctx}";
    </script>
    <!--loading css-->
    <link href="${ctx}/common/css/util/loading.css" rel="stylesheet">


    <link href="${ctx}/common/ui/bootstrap-datetimepicker-master/build/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
    <script src="${ctx}/common/ui/bootstrap-datetimepicker-master/build/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctx}/common/ui/bootstrap-datetimepicker-master/build/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${ctx}/common/ui/bootstrap-datetimepicker-master/build/js/moment.min.js"></script>


    <link href="${ctx}/common/ui/bootstrap-switch-master/dist/css/bootstrap2/bootstrap-switch.min.css" rel="stylesheet">
    <script src="${ctx}/common/ui/bootstrap-switch-master/dist/js/bootstrap-switch.min.js"></script>

</head>
<body>
<!--loading-->
<div class="loading" id="loadingData">
    <div class="loading-i">
        <img src="${ctx}/common/images/loading/loading.gif" style="height: 30px;" />&nbsp;<span>页面加载中</span>
    </div>
</div>


<div class="navbar navbar-fixed-top" id="homeBackNavbar">

    <div class="navbar-inner addInner">

        <div class="container">

            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>


            <a class="brand" href="${ctx}/view/homeBack/index">personal center</a>
            <div class="nav-collapse">

                <ul class="nav pull-right">
                    <li>
                        <a href="#"><span class="badge badge-warning">7</span></a>
                    </li>

                    <li class="divider-vertical"></li>

                    <li class="dropdown">

                        <a data-toggle="dropdown" class="dropdown-toggle " href="#">
                            <shiro:principal property="loginName"/> <b class="caret"></b>
                        </a>

                        <ul class="dropdown-menu">
                            <li>
                                <a href="${ctx}/view/homeBack/accountInfo"><i class="icon-user"></i>设置</a>
                            </li>
                            <shiro:hasPermission name="home:timesheet:view">
                                <li>
                                    <a href="${ctx}/view/homeBack/myTimeSheet"><i class="icon-user"></i>工时</a>
                                </li>
                            </shiro:hasPermission>
                            <li>
                                <a href="${ctx}/view/home/index"><i class="icon-lock"></i>首页</a>
                            </li>
                            <li class="divider"></li>

                            <li>
                                <a href="#" onclick="signOut()"><i class="icon-off"></i>退出</a>
                            </li>
                        </ul>
                    </li>
                </ul>

            </div> <!-- /nav-collapse -->

        </div> <!-- /container -->

    </div> <!-- /navbar-inner -->

</div> <!-- /navbar -->


<div id="content">

    <div class="container">
        <div class="row">
            <div class="span3" id="homeBackLeftMenu">

                <div class="account-container">

                    <div class="account-avatar">
                        <!--<img src="./img/headshot.png" alt="" class="thumbnail" />-->
                    </div> <!-- /account-avatar -->

                    <div class="account-details">

                        <span class="account-name">
                             <shiro:principal property="name"/>
                        </span>

                        <span class="account-role">
                            <shiro:principal property="loginName"/>
                        </span>

                        <span class="account-actions">
							<a href="javascript:;">Profile</a> |
							<a href="javascript:;">Edit Settings</a>
						</span>

                    </div> <!-- /account-details -->

                </div> <!-- /account-container -->

                <hr />

                <ul id="main-nav" class="nav nav-tabs nav-stacked">
                    <%--<li class="active">
                        <a href="${ctx}/view/homeBack/index">
                            <i class="icon-home"></i>
                            博客管理
                        </a>
                    </li>
                    <li >
                        <a href="${ctx}/view/homeBack/index">
                            <i class="icon-home"></i>
                            电影管理
                        </a>
                    </li>--%>
                </ul>

                <hr />
                <!--
                <div class="sidebar-extra">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.</p>
                </div>
                -->
                <!-- .sidebar-extra -->

                <br />

            </div> <!-- /span3 -->




            <sitemesh:write property='body' />


        </div>
    </div>
</div>



<jsp:include page="/WEB-INF/common/copyright_homeBack_footer.jsp" />

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="${ctx}/common/js/jQuery/jquery-1.7.2.min.js"></script>--%>
<script src="${ctx}/common/js/homeBack/excanvas.min.js"></script>
<script src="${ctx}/common/js/jQuery/jquery.flot.js"></script>
<script src="${ctx}/common/js/jQuery/jquery.flot.pie.js"></script>
<script src="${ctx}/common/js/jQuery/jquery.flot.orderBars.js"></script>
<script src="${ctx}/common/js/jQuery/jquery.flot.resize.js"></script>


<%--<script src="${ctx}/common/ui/bootstrap/js/bootstrap_2.0.2.js"></script>--%>

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
<!--防止未加载菜单-->
<script src="${ctx}/page_resource/template/homeBack.js"></script>
</body>
</html>
