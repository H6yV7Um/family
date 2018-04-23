<%--
  Created by IntelliJ IDEA.
  User: lcy
  Date: 17/1/3
  Time: 下午4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>个人中心</title>
</head>
<body>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<script src="${ctx}/common/ui/echart3/echarts.js"></script>
<script src="${ctx}/common/ui/echart3/theme/vintage.js"></script>
<script src="${ctx}/page_resource/homeBack/index/js/index.js"></script>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-home"></i>
        Dashboard
    </h1>

    <div class="stat-container">

        <div class="stat-holder">
            <div class="stat">
                <span>564</span>
                Completed Sales
            </div> <!-- /stat -->
        </div> <!-- /stat-holder -->

        <div class="stat-holder">
            <div class="stat">
                <span>423</span>
                Pending Sales
            </div> <!-- /stat -->
        </div> <!-- /stat-holder -->

        <div class="stat-holder">
            <div class="stat">
                <span>96</span>
                Returned Sales
            </div> <!-- /stat -->
        </div> <!-- /stat-holder -->

        <div class="stat-holder">
            <div class="stat">
                <span>2</span>
                Chargebacks
            </div> <!-- /stat -->
        </div> <!-- /stat-holder -->

    </div> <!-- /stat-container -->

    <div class="widget">

        <div class="widget-header">
            <i class="icon-signal"></i>
            <h3>Area Chart</h3>
        </div> <!-- /widget-header -->

        <div class="widget-content">
            <div id="bar-chart" class="chart-holder"></div> <!-- /bar-chart -->
            <hr/>
            <div id="line-chart" class="chart-holder"></div> <!-- /bar-chart -->

            <!--<div class="form-actions">
                <button type="button" class="btn btn-primary" onclick="exportPhoto()">Save</button>
            </div>--> <!-- /form-actions -->
        </div> <!-- /widget-content -->

    </div> <!-- /widget -->










</div> <!-- /span9 -->

</body>
</html>
