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
    <title>博客管理</title>
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
<script src="${ctx}/page_resource/homeBack/blog/js/blog.js"></script>

<script type="text/javascript">
    blogParam.modelId = "${param.modelId}";
    blogParam.urlSelf = "/view/homeBack/blog";
</script>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        博客管理
    </h1>


    <div class="row">
        <div class="span9" style="">
            <div class="box" style="border-top: 0px;">
                <div class="box-header">
                    <h3 class="box-title">博客列表</h3>
                    <shiro:hasPermission name="home:blog:add">
                        <button style="margin-left: 3em;" type="button" onclick="addBlog()" class="btn btn-sm btn-success">添加md</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:blog:add">
                        <button style="margin-left: 1em;" type="button" onclick="addMinder()" class="btn btn-sm btn-success">添加脑图</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:blog:delete">
                        <button style="margin-left: 1em;" type="button" onclick="deleteBlog()" class="btn btn-sm btn-danger">删除</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:blog:update">
                        <button style="margin-left: 1em;" type="button" onclick="updateBlog()" class="btn btn-sm btn-info">修改</button>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="home:blog:manage">
                        <script type="text/javascript">
                            // 拥有author
                            blogParam.manageAuthor = true;
                        </script>
                        &nbsp;&nbsp;
                        <input type="radio" value="0" name="blogOwn">&nbsp;&nbsp;my <input value="1" type="radio" name="blogOwn" checked> &nbsp;&nbsp;all
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
                                &nbsp;&nbsp;<button type="button" onclick="searchBlog()" class="btn btn-sm btn-info">搜索</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="padding: 1%" id="labelStaticBtns">





                </div>
                <!--标签-->
                <div style="padding: 1%;float: right">
                    <button class="btn btn-info" type="button"  onclick="blogLabelMana()" >
                        标签管理
                    </button>
                </div>
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover" >
                        <tr>
                            <th>#</th>
                            <th>标题</th>
                            <th>关键词</th>
                            <th>创建时间</th>
                            <th>分类</th>
                            <th>类型</th>
                            <!--<th>阅读次数</th>-->
                            <th style="width: 250px;">操作</th>

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
            </div>

        </div>
    </div> <!-- /row -->




    <!--layer modal-->
    <div id="blogLabelInfo" style="display: none;">
        <div class="widget-content" style="border: none;">
            <div class="tabbable">
                <div class="tab-content">
                    <div class="tab-pane active">
                        <form id="" class="form-horizontal" style="padding-left: 5%"/>
                            <fieldset>

                                <div class="control-group" >
                                    <span class="spanAccountServer" style="width: 223px;">标题</span>
                                    <span class="spanAccountServer" style="width: 85px;">操作</span>
                                    <span class="spanAccountServer" style="width: 115px;">博客数</span>
                                </div> <!-- /control-group -->
                                <div class="control-group" >
                                    <input type="text" class="input-large" name="title" id="labelTitle" />

                                    <button type="button" class="btn btn-default" aria-label="Left Align" onclick="addLabel()">
                                        <span class="icon-plus" style="margin-left: 3%" ></span>
                                    </button>
                                    <!--<button type="button" class="btn btn-default" aria-label="Left Align">
                                        <span class="icon-minus" style="margin-left: 3%" ></span>
                                    </button>-->
                                </div> <!-- /control-group -->

                                <div class="control-group">
                                   <table>
                                       <tbody id="blogLabelTbody">

                                       </tbody>
                                   </table>


                                    <!--<button type="button" class="btn btn-default" aria-label="Left Align">
                                        <span class="icon-minus" style="margin-left: 3%" ></span>
                                    </button>-->
                                </div> <!-- /control-group -->
                            </fieldset>
                            <button type="reset" style="display: none;" id="resetAccountServer" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="mindInfo" style="display: none;">
        <div class="widget-content" style="border: none;">
            <div class="tabbable">
                <div class="tab-content">
                    <div class="tab-pane active">
                        <form id="mindForm" class="form-horizontal" style=""/>
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label" for="blogTitle">标题</label>
                                    <div class="controls">
                                        <input type="text" class="input-large" name="title" id="blogTitle" value="${blogUpdate.title}"/>
                                        <span class="errorSheetFormLabel" ></span>
                                        <input type="hidden"  name="businessId" id="minderId"/>
                                        <input type="hidden"  name="type" value="1" />
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                                <div class="control-group">
                                    <label class="control-label" for="blogBkeys">关键字</label>
                                    <div class="controls">
                                        <input type="text" class="input-large" name="bkeys" id="blogBkeys" value="${blogUpdate.bkeys}"/>
                                        <span class="errorSheetFormLabel" >&nbsp;&nbsp;&nbsp;空格间隔</span>

                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                                <div class="control-group">
                                    <label class="control-label" for="classifyLables" >分类</label>
                                    <div class="controls">
                                        <select name="classify" id="classifyLables" class="input-large" >
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
                                        <select name="blogLabelId" id="blogLabels" class="input-large" >
                                            <option value="-1">请选择</option>
                                        </select>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                            </fieldset>
                            <button type="reset" style="display: none;" id="resetMinder" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
