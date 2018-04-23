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
    <title>课题里程碑管理</title>
</head>
<body>
<style type="text/css">
    .input-group{
        position: relative;
        display: table;
        border-collapse: separate;
        margin-bottom: 1em;
    }
    .input-group-btn{
        width: 1%;
        white-space: nowrap;
        vertical-align: middle;
        display: table-cell;
    }
    .table th, .table td{
        line-height: 25px;
    }
    .spanRtopicMile{
        display:-moz-inline-box;

        display:inline-block;

        margin-left: 0px;
    }
    .selectedTypeBtn{
        color: gold;
    }
    .selectedTypeBtn:hover{
        color: gold;
    }
</style>
<script src="${ctx}/page_resource/homeBack/rtopic/js/rtopicMilestone.js"></script>

<script type="text/javascript">
    rtopicParam.modelId = "${param.modelId}";
    rtopicParam.milestoneId = "${param.milestoneId}";
    rtopicParam.urlSelf = "/view/homeBack/rtopicMilestone";
</script>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        课题里程碑管理
    </h1>


    <div class="row">
        <div class="span9" style="">
            <div class="box" style="border-top: 0px;">
                <div class="box-header">
                    <h3 class="box-title">课题里程碑列表</h3>
                    <div class="box-tools">
                        <div class="input-group input-group-sm" style="width: 200px">
                            <div class="input-group-btn">
                                <span class="box-title" style="font-size: 15px;">标题 :&nbsp;
                                </span>
                            </div>
                            <input type="text" placeholder="请输入标题" style="margin-bottom:0px;"
                                   name="blogName" id="blogName" />
                            <div class="input-group-btn">
                                &nbsp;&nbsp;<button type="button" onclick="searchRtopic()" class="btn btn-sm btn-info">搜索</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover" >
                        <tr>
                            <th>#</th>
                            <th>标题</th>
                            <th>内容</th>
                            <th>开始时间~结束时间</th>
                            <th>课题人</th>
                            <th>操作</th>
                        </tr>
                        <tbody ID="rtopicTableTbody">
                        </tbody>
                    </table>
                </div>
                <br/>
                <br/>
                <!-- 分页模块  -->
                <div align="center">
                    <ul id="pagination-rtopic" class="pagination-sm"
                        style="margin-top: -10px"></ul>
                </div>
            </div>

        </div>
    </div> <!-- /row -->




    <!--layer modal-->
    <div id="blogInfo" style="display: none;">
        <div class="widget-content" style="border: none;">
            <div class="tabbable">
                <div class="tab-content">
                    <div class="tab-pane active">
                        <form  class="form-horizontal" />
                        <fieldset>

                            <div class="control-group">
                                <span class="spanRtopicMile" style="width: 210px;">标题</span>
                                <span class="spanRtopicMile" style="width: 210px;">关键字</span>
                                <span class="spanRtopicMile" style="width: 160px;">时间</span>
                            </div> <!-- /control-group -->


                            <div class="control-group">
                                <table>
                                    <tbody id="blogContent">
                                    </tbody>
                                </table>

                            </div> <!-- /control-group -->
                        </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div> <!-- /widget-content -->



<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
