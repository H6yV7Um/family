<%--
  Created by IntelliJ IDEA.
  User: lcy
  Date: 17/2/6
  Time: 上午9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<html>
<head>
    <title>我的工时</title>
</head>
<body>
<!-- fullcalendar -->
<link href="${ctx}/common/ui/fullCalendar/css/fullcalendar.min.css" rel="stylesheet">
<!--media='print' 输出到打印机-->
<link href="${ctx}/common/ui/fullCalendar/css/fullcalendar.print.css" rel="stylesheet" media='print'>
<!--cyLayer-->
<link href="${ctx}/common/ui/layer/cyLayer.css" rel="stylesheet">
<style>
    .errorSheetFormLabel{
        color: red;
    }
    .sheetFloat{
        position: absolute;
        background-color: #80c385;
        z-index: 1111;
        width: 150px;
        padding: 1%;
        font-size:13px;
        font-weight: normal;
        line-height:18px;
        display: none;
    }

    .form-horizontal .control-label{
        width:120px;
    }
</style>
<!--<script src="${ctx}/common/js/moment/moment.js"></script>-->
<script src="${ctx}/common/ui/fullCalendar/js/fullcalendar.min.js"></script>
<script src="${ctx}/common/ui/fullCalendar/js/lang/zh-cn.js"></script>
<script src="${ctx}/page_resource/homeBack/timeSheet/js/myTimeSheet.js"></script>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        我的工时
    </h1>


    <div class="row">
        <div class="span9" style="background-color: white">
            <div id='calendar'></div>
        </div>
    </div>
</div>
<!--layer modal-->
<div id="sheetInfo" style="display: none;">
    <div class="widget-content" style="border: none;">
        <div class="tabbable">
            <div class="tab-content">
                    <div style="width: 40%;float: left">
                        <div class="tab-pane active">
                            <form id="sheetForm" class="form-horizontal" />
                            <fieldset>

                                <div class="control-group">
                                    <label class="control-label" for="workTitle">工作标题</label>
                                    <div class="controls">
                                        <input type="text" class="input-medium" name="title" id="workTitle" />
                                        <span class="errorSheetFormLabel" ></span>
                                        <input type="hidden" class="input-medium" name="businessId" id="workBusinessId"  />
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                                <div class="control-group">
                                    <label class="control-label" for="startDate">开始时间</label>
                                    <div class="controls">
                                        <input type="text" name="start"   class="datetimeClass input-medium" id="startDate" />
                                        <span class="errorSheetFormLabel" ></span>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                                <div class="control-group">
                                    <label class="control-label" for="endDate">结束时间</label>
                                    <div class="controls">
                                        <input type="text" class="datetimeClass input-medium" name="end" id="endDate" />
                                        <span class="errorSheetFormLabel" ></span>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->


                                <div class="control-group">
                                    <label class="control-label" for="workContent">工作内容</label>
                                    <div class="controls">
                                        <textarea class="input-medium" value="" name="content" id="workContent" rows="8"></textarea>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->


                                <div class="control-group">
                                    <label class="control-label" for="isOneday">全天任务</label>
                                    <div class="controls">
                                        <input type="radio" value="false" checked="checked" name="allDay"><span style="padding-top: 5px;">&nbsp;否</span>
                                        <input type="radio" value="true" name="allDay" id="isOneday"><span style="padding-top: 5px;">&nbsp;是</span>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->
                                <div class="control-group">
                                    <label class="control-label" for="type">类型</label>
                                    <div class="controls">
                                        <input type="radio" value="2" checked="checked" name="type" id="type"><span style="padding-top: 5px;">&nbsp;课题任务</span>
                                        <input type="radio" value="0" name="type"><span style="padding-top: 5px;">&nbsp;个人日程</span>
                                        <input type="radio" value="1" name="type" ><span style="padding-top: 5px;">&nbsp;timesheet</span>

                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                            </fieldset>
                                <button type="reset" style="display: none;" id="resetMySheet" />
                            </form>
                        </div>


                    </div>
                    <div style="width: 50%;float: left">
                        <div class="tab-pane active">
                            <form id="" class="form-horizontal" />
                                <div class="control-group">
                                    <label class="control-label" for="rtopics" >课题</label>
                                    <div class="controls">
                                        <select name="rtopics" id="rtopics" style="width: 200px;">
                                            <option value="-1">请选择</option>

                                        </select>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                                <div class="control-group">
                                    <label class="control-label" for="workTitle">博客</label>
                                    <div class="controls">
                                        <div class="box-body table-responsive no-padding">
                                            <table class="table table-hover" >
                                                <tr>
                                                    <th>#</th>
                                                    <th>标题</th>
                                                    <th>关键词</th>
                                                    <th>创建时间</th>
                                                </tr>
                                                <tbody ID="blogTableTbody">
                                                </tbody>
                                            </table>
                                        </div>
                                        <br/>
                                        <br/>
                                        <!-- 分页模块  -->
                                        <div align="center">
                                            <ul id="pagination-blog" class="pagination-sm"
                                                style="margin-top: -10px"></ul>
                                        </div>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                            </form>
                        </div>
                    </div>
            </div>
        </div>
    </div> <!-- /widget-content -->

</div>

<!--float modal-->

<div id="sheetInfoFloat" class="sheetFloat">
    <div id="sheetInfoFloatContent"  class="control-group" >
    </div>
</div>
<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
