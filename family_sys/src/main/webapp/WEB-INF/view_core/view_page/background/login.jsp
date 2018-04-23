<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %><%--
  Created by family.
  User: cy
  Date: 16/5/27
  Time: 下午1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/common/ui/bootstrap/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <!-- Custom CSS -->
    <link href="${ctx}/common/css/background/styleBackground.css" rel='stylesheet' type='text/css' />
    <link href="${ctx}/common/css/font-awesome/font-awesome.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="${ctx}/common/js/jQuery/jquery.min.js"></script>
    <!----webfonts--->
    <%--<link href='http://fonts.useso.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>--%>
    <!---//webfonts--->
    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/common/ui/bootstrap/js/bootstrap.min.js"></script>
    <script type="application/javascript">
        // 如果在框架中，则跳转刷新上级页面
        if(self.frameElement && self.frameElement.tagName=="IFRAME"){
            parent.location.reload();
        }
    </script>
</head>
<body id="login">
<div class="login-logo">
    <img src="${ctx}/common/images/logo.png" alt=""/>
</div>
<h2 class="form-heading">yxyqcy</h2>
<div class="app-cam">
    <form  id="loginForm"   action="${ctx}/adminLogin" method="post">
        <%String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);%>
        <div id="messageBox" class="alert alert-danger <%=error==null ? "hide" : ""%>"><button data-dismiss="alert" class="close">×</button>
            <label id="loginError" class="error"><%=error==null ? "" : "org.yxyqcy.family.sys.security.CaptchaException".equals(error) ? "验证码错误, 请重试." : "用户或密码错误, 请重试." %></label>
        </div>
        <input type="text" name="username"  class="text" value="admin">
        <input type="password" name="password"  value="admin" >
        <div class="submit"><input type="submit"  value="Login"></div>
        <div class="login-social-link">

            <%--<a href="index.html" class="facebook">--%>
                <%--Facebook--%>
            <%--</a>--%>
            <%--<a href="index.html" class="twitter">--%>
                <%--Twitter--%>
            <%--</a>--%>
                <c:if test="${isValidateCodeLogin}">
                <div class="validateCode">
                    <label for="validateCode">验证码：</label>
                    <tags:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
                </div>
                </c:if>
                <%-- <input type="checkbox" id="rememberMe" name="rememberMe"> <span style="color:#08c;">记住我</span>--%>
        </div>
        <%--<ul class="new">--%>
            <%--<li class="new_left"><p><a href="#">Forgot Password ?</a></p></li>--%>
            <%--<li class="new_right"><p class="sign">New here ?<a href="register.html"> Sign Up</a></p></li>--%>
            <%--<div class="clearfix"></div>--%>
        <%--</ul>--%>
    </form>
</div>

<div class="copy_layout login">
    <p>Copyright &copy; 2016.   yxyqcy</p>
</div>
</body>
</html>

