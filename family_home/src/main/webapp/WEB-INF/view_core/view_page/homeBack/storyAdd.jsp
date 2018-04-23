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
    <title>新增故事</title>
</head>
<body>

<script src="${ctx}/page_resource/homeBack/story/js/storyAdd.js"></script>
<script type="text/javascript">
    storyUpdate.businessId = "${storyUpdate.businessId}";
    storyUpdate.modelId = "${param.modelId}";
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
        添加故事
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
                                <form id="storyForm" class="form-horizontal" method="post" />
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="storyTitle">标题</label>
                                            <div class="controls">
                                                <input type="text" class="input-xlarge" name="title" id="storyTitle" value="${storyUpdate.title}"/>
                                                <span class="errorSheetFormLabel" ></span>
                                                <input type="hidden"  name="businessId" value="${storyUpdate.businessId}" />
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->



                                        <div class="control-group">
                                            <label class="control-label" for="description">描述</label>
                                            <div class="controls">
                                                <textarea class="input-xlarge"  name="description" id="description" rows="4"></textarea>
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
