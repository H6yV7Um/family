<%--
  Created by IntelliJ IDEA.
  User: lcy
  Date: 17/2/18
  Time: 上午10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>博客详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>markdownToc</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/common/ui/markdownToc/github2-rightpart.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/common/ui/markdownToc/github1-contents.css">
    <link rel="stylesheet" href="${ctx}/common/ui/markdownToc/zTreeStyle.css" type="text/css">
    <link rel="icon" href="${ctx}/common/ico/family_home.ico" type="image/x-icon"/>
    <style>
        .ztree li a.curSelectedNode {
            padding-top: 0px;
            background-color: #FFE6B0;
            color: black;
            height: 16px;
            border: 1px #FFB951 solid;
            opacity: 0.8;
        }
        .ztree{
            overflow: auto;
            height:100%;
            min-height: 200px;
            top: 0px;
        }
        .markdown-body pre code, .markdown-body pre tt{
            font-size: 14px;
            padding: 3px;
            border-radius: 3px;
        }

    </style>

</head>
<body style="">
<div>
    <div style="width:30%;">
        <ul id="tree" class="ztree" style="width: 260px; overflow: auto; position: fixed; z-index: 2147483647; border: 0px none; left: 0px; bottom: 0px;">
            <!-- 目录内容在网页另存为之后将插入到此处 -->
        </ul>
    </div>
    <div id="readme" style="width:70%;margin-left:25%;">
        <article class="markdown-body">
            <%--<iframe src="${ctx}/1.html" style="width: 100%;height: 100%" id="blogInfoTemp" >
            </iframe>--%>
            ${blog.bdescHtml}
        </article>
    </div>
    </div>
    <script src="${ctx}/common/ui/markdownToc/jquery-1.10.2.min.js"></script>
    <script src="${ctx}/common/ui/markdownToc/jquery.ztree.all-3.5.min.js"></script>
    <script src="${ctx}/common/ui/markdownToc/jquery.ztree_toc.min.js"></script>
    <!-- cyUtil -->
    <script src="${ctx}/common/js/util/cyUtil.js"></script>
    <script>
        family_ns.contextPath = "${ctx}";
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#tree').ztree_toc({
                is_auto_number:false,
                documment_selector:'.markdown-body',
                is_expand_all: true
            });
            //$("#blogInfoTemp").attr("src",family_ns.webResourceServer + "${blog_url}");

        });
    </script>
</body>
</html>
