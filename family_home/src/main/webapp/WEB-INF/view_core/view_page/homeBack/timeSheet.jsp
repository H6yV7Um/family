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
    <title>帐号管理</title>
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
    .spanAccountServer{
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
<script src="${ctx}/page_resource/homeBack/timeSheet/js/timesheet.js"></script>

<script type="text/javascript">
    timeSheetParam.modelId = "${param.modelId}";
    timeSheetParam.urlSelf = "/view/homeBack/timeSheet";
</script>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        工时管理
    </h1>


    <div class="row">
        <div class="span9" style="">
            <div class="box" style="border-top: 0px;">
                <div class="box-header">
                    <h3 class="box-title">工时列表</h3>

                    <shiro:hasPermission name="home:timesheet:assess">
                        <button style="margin-left: 1em;" type="button" onclick="batchAssess()" class="btn btn-sm btn-success">批量审核</button>

                        <script type="text/javascript">
                            // 拥有author
                            timeSheetParam.assessAuthor = true;
                        </script>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="home:timesheet:export">
                        <button style="margin-left: 1em;" type="button" onclick="exportTimesheet()" class="btn btn-sm btn-info">工时导出</button>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="home:timesheet:assess">
                        <button style="margin-left: 1em;" type="button" onclick="calendarTimesheet()" class="btn btn-sm btn-warning">工时日历</button>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="home:timesheet:assess">
                        <div class="box-tools">

                            <div class="input-group input-group-sm" style="width: 200px">
                                <div class="input-group-btn">
                                    <span class="box-title" style="font-size: 15px;">人员 :&nbsp;
                                    </span>
                                </div>
                                <input type="text" placeholder="请输入人员" style="margin-bottom:0px;"
                                       name="createBy" id="createBy" />
                                <div class="input-group-btn">
                                    &nbsp;&nbsp;<button type="button" onclick="searchTimesheet()" class="btn btn-sm btn-info">搜索</button>
                                </div>
                            </div>
                        </div>
                    </shiro:hasPermission>
                </div>

                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover" >
                        <tr>
                            <th>#</th>
                            <th>标题</th>
                            <th>内容</th>
                            <th>人员</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>操作</th>
                        </tr>
                        <tbody ID="timeSheetTbody">
                        </tbody>
                    </table>
                </div>
                <br/>
                <br/>
                <!-- 分页模块  -->
                <div align="center">
                    <ul id="paginationTimeSheet" class="pagination-sm"
                        style="margin-top: -10px"></ul>
                </div>
            </div>

        </div>
    </div> <!-- /row -->








</div> <!-- /span9 -->

    <!--layer modal-->
    <div id="exportInfo" style="display: none;">
        <div class="widget-content" style="border: none;">
            <div class="tabbable">
                <div class="tab-content">
                    <div class="tab-pane active">
                        <form id="sheetForm" class="form-horizontal" />
                        <fieldset>



                            <div class="control-group">
                                <label class="control-label" for="startDate">开始时间</label>
                                <div class="controls">
                                    <input type="text" name="start"   class="dateClass input-xlarge" id="startDate" />
                                    <span class="errorSheetFormLabel" ></span>
                                </div> <!-- /controls -->

                            </div> <!-- /control-group -->

                            <div class="control-group">
                                <label class="control-label" for="endDate">结束时间</label>
                                <div class="controls">
                                    <input type="text" class="dateClass input-xlarge" name="end" id="endDate" />
                                    <span class="errorSheetFormLabel" ></span>
                                </div> <!-- /controls -->
                            </div> <!-- /control-group -->


                            <div class="control-group">
                                <div class="controls">
                                    <button  type="button" onclick="sureExportTs()" class="btn btn-sm btn-info">工时导出</button>
                                </div>
                            </div> <!-- /control-group -->

                        </fieldset>
                            <button type="reset" style="display: none;" id="resetExportSheet" />
                        </form>
                    </div>
                </div>
            </div>
        </div> <!-- /widget-content -->

    </div>

<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
