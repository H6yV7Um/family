<%--
  Created by IntelliJ IDEA.
  User: lcy
  Date: 17/10/4
  Time: 上午6:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>脑图</title>

    <%--<link href="favicon.ico" type="image/x-icon" rel="shortcut icon"> --%>

    <!-- bower:css -->
    <link rel="icon" href="${ctx}/common/ico/family_home.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${ctx}/common/bower/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="${ctx}/common/bower/codemirror/lib/codemirror.css" />
    <link rel="stylesheet" href="${ctx}/common/bower/hotbox/hotbox.css" />
    <link rel="stylesheet" href="${ctx}/common/bower/kityminder-core/dist/kityminder.core.css" />
    <link rel="stylesheet" href="${ctx}/common/bower/color-picker/dist/color-picker.min.css" />
    <!-- endbower -->

    <link rel="stylesheet" href="${ctx}/common/css/kityMinder/kityminder.editor.min.css">

    <style>
        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden;
        }
        h1.editor-title {
            background: #393F4F;
            color: white;
            margin: 0;
            height: 40px;
            font-size: 14px;
            line-height: 40px;
            font-family: 'Hiragino Sans GB', 'Arial', 'Microsoft Yahei';
            font-weight: normal;
            padding: 0 20px;
        }
        div.minder-editor-container {
            position: absolute;
            top: 40px;
            bottom: 0;
            left: 0;
            right: 0;
        }
    </style>
</head>
<body ng-app="kityminderDemo" ng-controller="MainController">

<h1 class="editor-title">

        minder
        <div style="float: right">
            <span id="">title : ${blog.title}</span>
            <shiro:hasPermission name="home:blog:add">
                <input type="button" id="mindBtn" value="提交" onclick="sub()" class="btn btn-default" style="display: none" >
            </shiro:hasPermission>
        </div>

</h1>
<kityminder-editor on-init="initEditor(editor, minder)"></kityminder-editor>
</body>

<!-- bower:js -->
<script src="${ctx}/common/bower/jquery/dist/jquery.js"></script>
<script src="${ctx}/common/bower/bootstrap/dist/js/bootstrap.js"></script>
<script src="${ctx}/common/bower/angular/angular.js"></script>
<script src="${ctx}/common/bower/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="${ctx}/common/bower/codemirror/lib/codemirror.js"></script>
<script src="${ctx}/common/bower/codemirror/mode/xml/xml.js"></script>
<script src="${ctx}/common/bower/codemirror/mode/javascript/javascript.js"></script>
<script src="${ctx}/common/bower/codemirror/mode/css/css.js"></script>
<script src="${ctx}/common/bower/codemirror/mode/htmlmixed/htmlmixed.js"></script>
<script src="${ctx}/common/bower/codemirror/mode/markdown/markdown.js"></script>
<script src="${ctx}/common/bower/codemirror/addon/mode/overlay.js"></script>
<script src="${ctx}/common/bower/codemirror/mode/gfm/gfm.js"></script>
<script src="${ctx}/common/bower/angular-ui-codemirror/ui-codemirror.js"></script>
<script src="${ctx}/common/bower/marked/lib/marked.js"></script>
<script src="${ctx}/common/bower/kity/dist/kity.min.js"></script>
<script src="${ctx}/common/bower/hotbox/hotbox.js"></script>
<script src="${ctx}/common/bower/json-diff/json-diff.js"></script>
<script src="${ctx}/common/bower/kityminder-core/dist/kityminder.core.min.js"></script>
<script src="${ctx}/common/bower/color-picker/dist/color-picker.min.js"></script>
<!-- endbower -->

<script src="${ctx}/common/js/kityMinder/kityminder.editor.min.js"></script>

<!-- Sweet alert -->
<link href="${ctx}/common/ui/sweetalert/css/sweetalert.css" rel="stylesheet">
<script src="${ctx}/common/ui/sweetalert/js/sweetalert.min.js"></script>
<!-- cyUtil -->
<script src="${ctx}/common/js/util/cyUtil.js"></script>
<script>
    family_ns.contextPath = "${ctx}";
</script>

<script src="${ctx}/page_resource/homeBack/blog/js/mind.js"></script>

<script>
    // businessId
    mindParam.businessId = "${blog.businessId}";
    mindParam.owns = "${owns}";

</script>

</html>