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
    <title>课题管理</title>
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
    .errorSheetFormLabel label{
        display: inline;
    }
</style>
<script src="${ctx}/page_resource/homeBack/rtopic/js/rtopic.js"></script>

<script type="text/javascript">
    rtopicParam.modelId = "${param.modelId}";
    rtopicParam.urlSelf = "/view/homeBack/rtopic";
</script>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        课题管理
    </h1>


    <div class="row">
        <div class="span9" style="">
            <div class="box" style="border-top: 0px;">
                <div class="box-header">
                    <h3 class="box-title">课题列表</h3>
                    <shiro:hasPermission name="home:rtopic:add">
                        <button style="margin-left: 3em;" type="button" onclick="addRtopic()" class="btn btn-sm btn-success">添加</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:rtopic:delete">
                        <button style="margin-left: 1em;" type="button" onclick="deleteRtopic()" class="btn btn-sm btn-danger">删除</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:rtopic:update">
                        <button style="margin-left: 1em;" type="button" onclick="updateRtopic()" class="btn btn-sm btn-info">修改</button>
                    </shiro:hasPermission>


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
                            <th>类型</th>
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
    <div id="rtopicInfo" style="display: none;">
        <div class="widget-content" style="border: none;">
            <div class="tabbable">
                <div class="tab-content">

                    <div style="width: 45%;float: left">
                        <div class="tab-pane active">
                        <form id="rtopicForm" class="form-horizontal" style="padding-left: 5%"/>
                            <fieldset>

                                <div class="control-group">
                                    <label class="control-label" for="workTitle">工作标题</label>
                                    <div class="controls">
                                        <input type="text" class="input-medium" name="title" id="workTitle" />
                                        <span class="errorSheetFormLabel" ></span>
                                        <input type="hidden" class="input-medium" name="businessId" id="rtopicBusinessId"  />
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->
                                <div class="control-group">
                                    <label class="control-label" for="startDate">开始时间</label>
                                    <div class="controls">
                                        <input type="text" name="startDate"   class="datetimeClass input-medium" id="startDate" />
                                        <span class="errorSheetFormLabel" ></span>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                                <div class="control-group">
                                    <label class="control-label" for="endDate">结束时间</label>
                                    <div class="controls">
                                        <input type="text" class="datetimeClass input-medium" name="endDate" id="endDate" />
                                        <span class="errorSheetFormLabel" ></span>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->
                                <div class="control-group">
                                    <label class="control-label" for="workContent">工作内容</label>
                                    <div class="controls">
                                        <textarea class="input-medium" value="" name="description" id="workContent" rows="8"></textarea>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->
                                <div class="control-group">
                                    <label class="control-label" for="type">类型</label>
                                    <div class="controls">
                                        <select name="type" id="type" style="width: 150px;">
                                            <option value="">请选择</option>
                                            <option value="0">前端</option>
                                            <option value="1">后端</option>
                                            <option value="2">运维</option>
                                            <option value="3">管理</option>
                                            <option value="4">策划</option>
                                        </select>

                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->


                            </fieldset>
                            <button type="reset" style="display: none;" id="resetRtopicInfo" />
                        </form>

                    </div>
                    </div>

                    <div style="width: 50%;float: left">
                        <div class="tab-pane active">
                            <form  class="form-horizontal" />
                            <div class="control-group">
                                <label class="control-label"  >课题人</label>
                                <div class="controls">
                                    <span id="topicUsers"></span>
                                </div> <!-- /controls -->
                            </div> <!-- /control-group -->

                            <div class="control-group">
                                <label class="control-label" ></label>
                                <div class="controls">
                                    <div class="box-body table-responsive no-padding">
                                        <table class="table table-hover" >
                                            <tr>
                                                <th>#</th>
                                                <th>人员</th>
                                                <th>工号</th>
                                            </tr>
                                            <tbody ID="userTableTbody">
                                            </tbody>
                                        </table>
                                    </div>
                                    <br/>
                                    <br/>
                                    <!-- 分页模块
                                    <div align="center">
                                        <ul id="pagination-blog" class="pagination-sm"
                                            style="margin-top: -10px"></ul>
                                    </div>
                                    -->
                                </div> <!-- /controls -->
                            </div> <!-- /control-group -->

                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>



<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
