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
    <title>新增故事片段</title>
</head>
<body>
<!--home star-->
<link rel="stylesheet" href="${ctx}/common/css/homeBack/homeStar.css">
<style type="text/css">
    .uploadifive-button {
        float: left;
        margin-right: 10px;
    }
    .queue {
        border: 1px solid #E5E5E5;
        height: 177px;
        overflow: auto;
        margin-bottom: 10px;
        padding: 0 3px 3px;
        width: 300px;
    }

</style>
<script src="${ctx}/common/ui/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/common/ui/uploadifive/uploadifive.css">
<script src="${ctx}/page_resource/homeBack/story/js/storyFragmentAdd.js"></script>
<script type="text/javascript">

    fragmentUpdate.modelId = "${param.modelId}";
    fragmentUpdate.businessId = "${fragmentUpdate.businessId}";
    if(fragmentUpdate.businessId)
        fragmentUpdate.storyId = "${fragmentUpdate.storyId}";
    else
        fragmentUpdate.storyId = "${param.storyId}";
    fragmentUpdate.isYear = "${fragmentUpdate.isYear}";
    fragmentUpdate.classFirst="${fragmentUpdate.classFirst}";
    fragmentUpdate.imageFirst="${fragmentUpdate.imageFirst}";
    fragmentUpdate.imageSecond="${fragmentUpdate.imageSecond}";
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

</style>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        添加fragment
    </h1>


    <div class="row">

        <div class="span9">

            <div class="widget">

                <div class="widget-header">
                    <h3>基本信息</h3>
                </div> <!-- /widget-header -->

                <div class="widget-content">



                    <div class="tabbable">
                        <br />

                        <div class="tab-content">
                            <div class="tab-pane active">
                                <form id="storyFragmentForm" class="form-horizontal" method="post" />
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="fragmentTitle">标题</label>
                                            <div class="controls">
                                                <input type="text" class="input-xlarge" name="title" id="fragmentTitle" value="${fragmentUpdate.title}"/>
                                                <span class="errorSheetFormLabel" ></span>
                                                <input type="hidden"  name="businessId" value="${fragmentUpdate.businessId}" />
                                                <input type="hidden"  name="storyId" value="${fragmentUpdate.storyId}" />
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->

                                        <div class="control-group">
                                            <label class="control-label" for="storyDate">日期</label>
                                            <div class="controls">
                                                <input type="text" name="storyDate"  class="datetimeClass" id="storyDate" value="${fragmentUpdate.formatterStoryDate}" style="width: 270px;">
                                                <span class="errorSheetFormLabel" ></span>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->


                                        <div class="control-group">
                                            <label class="control-label"  >年</label>
                                            <div class="controls">
                                                <input  type="radio" name="isYear" value="1"/> <span style="padding-top: 5px;">是</span>
                                                &nbsp;&nbsp;
                                                <input  type="radio" name="isYear" value="0" checked="checked"/> <span style="padding-top: 5px;">否</span>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->

                                        <div class="control-group">
                                            <label class="control-label" for="classFirst" >class</label>
                                            <div class="controls">
                                                <select id="classFirst" name="classFirst"  class="input-xlarge" >
                                                    <option value="">请选择</option>
                                                    <option value="list_show">list_show(1图)</option>
                                                    <option value="list_show show3">list_show show3(2图)</option>
                                                    <option value="list_show show4">list_show show4(2图)</option>
                                                    <option value="list_show show5">list_show show5(2图)</option>
                                                </select>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->

                                        <div class="control-group">
                                            <label class="control-label" >first</label>
                                            <div class="controls">

                                                <div id="queue_first" class="queue" style="float: left">

                                                </div>
                                                <div  class="queue" style="float: right">
                                                    <img src="" id="image_first_show"/>
                                                </div>
                                                &nbsp;&nbsp;
                                                <input type="button" class="input-xlage" value="clear" onclick="clearPhoto(1)">
                                                <input id="file_upload_first" class="input-xlarge" name="file_upload" type="file" multiple="true">
                                                <!--隐藏域-->
                                                <input id="image_first"name="imageFirst" type="hidden" value="${fragmentUpdate.imageFirst}" >
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->


                                        <div class="control-group">
                                            <label class="control-label" for="contentFirst">first 内容</label>
                                            <div class="controls">
                                                <input type="text" class="input-xlarge" name="contentFirst" id="contentFirst" value="${fragmentUpdate.contentFirst}"/>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->
                                        <div class="control-group">
                                            <label class="control-label" >second</label>
                                            <div class="controls">
                                                <div id="queue_second" class="queue" style="float: left"></div>
                                                <div  class="queue" style="float: right">
                                                    <img src="" id="image_second_show"/>

                                                </div>
                                                &nbsp;&nbsp;
                                                <input type="button" class="input-xlage" value="clear" onclick="clearPhoto(2)">
                                                <input id="file_upload_second" class="input-xlarge" name="file_upload" type="file" multiple="true">

                                                <!--隐藏域-->
                                                <input id="image_second"name="imageSecond" type="hidden" value="${fragmentUpdate.imageSecond}">
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->


                                        <div class="control-group">
                                            <label class="control-label" for="contentSecond">second 内容</label>
                                            <div class="controls">
                                                <input type="text" class="input-xlarge" name="contentSecond" id="contentSecond" value="${fragmentUpdate.contentSecond}"/>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->
                                        <br />


                                        <div class="form-actions">
                                            <button type="submit" class="btn btn-primary" >Save</button>
                                            <!--<button class="btn">Cancel</button>-->
                                        </div> <!-- /form-actions -->
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div> <!-- /widget-content -->

            </div> <!-- /widget -->

        </div> <!-- /span9 -->




</div> <!-- /row -->

</div> <!-- /span9 -->

<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
