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
    <title>电影管理</title>
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
</style>
<script src="${ctx}/page_resource/homeBack/movie/js/movie.js"></script>

<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        电影管理
    </h1>


    <div class="row">
        <div class="span9" style="">
            <div class="box" style="border-top: 0px;">
                <div class="box-header">
                    <h3 class="box-title">电影列表</h3>
                    <shiro:hasPermission name="home:movie:add">
                        <button style="margin-left: 3em;" type="button" onclick="addMovie()" class="btn btn-sm btn-success">添加</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:movie:delete">
                        <button style="margin-left: 1em;" type="button" onclick="deleteMovie()" class="btn btn-sm btn-danger">删除</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:movie:update">
                        <button style="margin-left: 1em;" type="button" onclick="updateMovie()" class="btn btn-sm btn-info">修改</button>
                    </shiro:hasPermission>
                    <div class="box-tools">

                        <div class="input-group input-group-sm" style="width: 200px">
                            <div class="input-group-btn">
                                <span class="box-title" style="font-size: 15px;">电影名称 :&nbsp;
                                </span>
                            </div>
                            <input type="text" placeholder="请输入电影名称" style="margin-bottom:0px;"
                                   name="movieName" id="movieName" />
                            <div class="input-group-btn">
                                &nbsp;&nbsp;<button type="button" onclick="searchMovie()" class="btn btn-sm btn-info">搜索</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover" >
                        <tr>
                            <th>#</th>
                            <th>电影</th>
                            <th>影院</th>
                            <th>时间</th>
                            <th>时长</th>
                            <th>评价</th>
                        </tr>
                        <tbody ID="movieTableTbody">
                        </tbody>
                    </table>
                </div>
                <br/>
                <br/>
                <!-- 分页模块  -->
                <div align="center" >
                    <ul id="pagination-movie" class="pagination-sm"
                        style="margin-top: -10px;"></ul>
                </div>
            </div>

        </div>

    </div> <!-- /row -->

</div> <!-- /span9 -->

<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
