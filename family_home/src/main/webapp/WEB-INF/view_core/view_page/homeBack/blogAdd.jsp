<%--
  Created by IntelliJ IDEA.
  User: lcy
  Date: 17/1/3
  Time: 下午4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<html>
<head>
    <title>新增博客</title>
</head>
<body>
<!--md-->

<link rel="stylesheet" href="${ctx}/common/ui/mdeditor/css/editormd.min.css" />
<script src="${ctx}/common/ui/mdeditor/editormd.js"></script>

<!--home star-->
<link rel="stylesheet" href="${ctx}/common/css/homeBack/homeStar.css">

<script src="${ctx}/page_resource/homeBack/blog/js/blogAdd.js"></script>
<script type="text/javascript">
    blogUpdate.businessId = "${blogUpdate.businessId}";
    blogUpdate.blogLabelId = "${blogUpdate.blogLabelId}";
    //md 内容
    blogUpdate.modelId = "${param.modelId}";
    blogUpdate.classify = "${blogUpdate.classify}";
</script>
<style>
    .errorSheetFormLabel{
        color: red;
    }
    /*覆盖jquery validation error css*/
    .errorSheetFormLabel label{
        display:inherit;
        color: red;
    }
    .editormd-toolbar{
        z-index: 1500;
    }
</style>
<div >

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        添加博客
    </h1>




    <div class="">

        <div class="widget">

            <div class="widget-header">
                <h3>基本信息</h3>
            </div> <!-- /widget-header -->

            <div class="widget-content">
                <div class="tabbable">
                    <br />

                    <div class="tab-content">
                        <div class="tab-pane active">
                            <form id="accountForm" class="form-horizontal" method="post" />
                                <fieldset>
                                    <div class="control-group">
                                        <label class="control-label" for="blogTitle">标题</label>
                                        <div class="controls">
                                            <input type="text" class="input-xxlarge" name="title" id="blogTitle" value="${blogUpdate.title}"/>
                                            <span class="errorSheetFormLabel" ></span>
                                            <input type="hidden"  name="businessId" value="${blogUpdate.businessId}" />
                                            <input type="hidden"  name="type" value="0" />
                                        </div> <!-- /controls -->
                                    </div> <!-- /control-group -->

                                    <div class="control-group">
                                        <label class="control-label" for="blogBkeys">关键字</label>
                                        <div class="controls">
                                            <input type="text" class="input-xxlarge" name="bkeys" id="blogBkeys" value="${blogUpdate.bkeys}"/>
                                            <span class="errorSheetFormLabel" >&nbsp;&nbsp;&nbsp;空格间隔</span>

                                        </div> <!-- /controls -->
                                    </div> <!-- /control-group -->


                                    <div class="control-group">
                                        <label class="control-label" for="classifyLables" >分类</label>
                                        <div class="controls">
                                            <select name="classify" id="classifyLables" class="input-xxlarge" >
                                                <option value="0">综合</option>
                                                <option value="1">移动开发</option>
                                                <option value="2">架构</option>
                                                <option value="3">云计算/大数据</option>
                                                <option value="4">互联网</option>
                                                <option value="5">运维</option>
                                                <option value="6">数据库</option>
                                                <option value="7">前端</option>
                                                <option value="8">编程语言</option>
                                                <option value="9">研发管理</option>

                                            </select>
                                        </div> <!-- /controls -->
                                    </div> <!-- /control-group -->

                                    <div class="control-group">
                                        <label class="control-label" for="blogLabels" >标签</label>
                                        <div class="controls">
                                            <select name="type" id="blogLabels" class="input-xxlarge" >
                                                <option value="-1">请选择</option>
                                            </select>
                                        </div> <!-- /controls -->
                                    </div> <!-- /control-group -->



                                    <div class="control-group">
                                        <label class="control-label"  >内容</label>
                                    </div> <!-- /control-group -->

                                    <div style="">
                                        <div id="test-editormd" style="width: 100%">
                                            <textarea id="bcontent" name="bcontent" style="display:none;">${blogUpdate.bdescmd}</textarea>
                                        </div>
                                    </div>
                                    <br />


                                    <div class="form-actions" align="center">
                                        <button type="button" class="btn btn-primary" onclick="saveBlog()" >Save</button>
                                        <!--<button class="btn">Cancel</button>-->
                                    </div> <!-- /form-actions -->
                                </fieldset>
                                <button type="reset" style="display: none;" id="resetMinder" />
                            </form>
                        </div>
                    </div>
                </div>
            </div> <!-- /widget-content -->

        </div> <!-- /widget -->

    </div> <!-- /span9 -->




</div>

<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
