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
    <title>故事管理</title>
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
    .spanStoryServer{
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
<script src="${ctx}/page_resource/homeBack/story/js/story.js"></script>
<script type="text/javascript">
    storyParam.modelId = "${param.modelId}";
</script>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        与子成说
    </h1>


    <div class="row">
        <div class="span9" style="">
            <div class="box" style="border-top: 0px;">
                <div class="box-header">
                    <h3 class="box-title">故事列表</h3>
                    <shiro:hasPermission name="home:story:add">
                        <button style="margin-left: 3em;" type="button" onclick="addStory()" class="btn btn-sm btn-success">添加</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:story:delete">
                        <button style="margin-left: 1em;" type="button" onclick="deleteStory()" class="btn btn-sm btn-danger">删除</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:story:update">
                        <button style="margin-left: 1em;" type="button" onclick="updateStory()" class="btn btn-sm btn-info">修改</button>
                    </shiro:hasPermission>
                        <button style="margin-left: 1em;" type="button" onclick="browseStory()" class="btn btn-sm btn-default">浏览</button>
                    <div class="box-tools">

                        <div class="input-group input-group-sm" style="width: 200px">
                            <div class="input-group-btn">
                                <span class="box-title" style="font-size: 15px;">标题 :&nbsp;
                                </span>
                            </div>
                            <input type="text" placeholder="请输入标题" style="margin-bottom:0px;"
                                   name="storyName" id="storyName" />
                            <div class="input-group-btn">
                                &nbsp;&nbsp;<button type="button" onclick="searchStory()" class="btn btn-sm btn-info">搜索</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover" >
                        <tr>
                            <th>#</th>
                            <th>标题</th>
                            <th>描述</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        <tbody ID="storyTableTbody">
                        </tbody>
                    </table>
                </div>
                <br/>
                <br/>
                <!-- 分页模块  -->
                <div align="center">
                    <ul id="pagination-story" class="pagination-sm"
                        style="margin-top: -10px"></ul>
                </div>
            </div>

        </div>
    </div> <!-- /row -->




</div> <!-- /widget-content -->








</div> <!-- /span9 -->
<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
