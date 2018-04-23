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
    <title>书籍管理</title>
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
    .queue {
        border: 1px solid #E5E5E5;
        height: 80px;
        overflow: auto;
        margin-bottom: 10px;
        padding: 0 3px 3px;
    }
    .errorSheetFormLabel{
        color: red;
    }
    /*覆盖jquery validation error css*/
    .errorSheetFormLabel label{
        display:inherit;
        color: red;
    }
</style>
<script src="${ctx}/common/ui/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/common/ui/uploadifive/uploadifive.css">
<script src="${ctx}/page_resource/homeBack/book/js/book.js"></script>

<script type="text/javascript">
    bookParam.modelId = "${param.modelId}";
    bookParam.urlSelf = "/view/homeBack/book";
</script>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        书籍管理
    </h1>


    <div class="row">
        <div class="span9" style="">
            <div class="box" style="border-top: 0px;">
                <div class="box-header">
                    <h3 class="box-title">书籍列表</h3>
                    <shiro:hasPermission name="home:book:add">
                        <button style="margin-left: 3em;" type="button" onclick="addBook()" class="btn btn-sm btn-success">添加</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:book:delete">
                        <button style="margin-left: 1em;" type="button" onclick="deleteBook()" class="btn btn-sm btn-danger">删除</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:book:update">
                        <button style="margin-left: 1em;" type="button" onclick="updateBook()" class="btn btn-sm btn-info">修改</button>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="home:book:manage">
                        <script type="text/javascript">
                            // 拥有author
                            bookParam.manageAuthor = true;
                        </script>
                        &nbsp;&nbsp;
                        <input type="radio" value="0" name="bookOwn">&nbsp;&nbsp;my <input value="1" type="radio" name="bookOwn" checked> &nbsp;&nbsp;all
                    </shiro:hasPermission>

                    <div class="box-tools">

                        <div class="input-group input-group-sm" style="width: 200px">
                            <div class="input-group-btn">
                                <span class="box-title" style="font-size: 15px;">标题 :&nbsp;
                                </span>
                            </div>
                            <input type="text" placeholder="请输入标题" style="margin-bottom:0px;"
                                   name="bookName" id="bookName" />
                            <div class="input-group-btn">
                                &nbsp;&nbsp;<button type="button" onclick="searchBook()" class="btn btn-sm btn-info">搜索</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover" >
                        <tr>
                            <th>#</th>
                            <th>书名</th>
                            <th>编号</th>
                            <th>创建时间</th>
                            <th>分类</th>
                            <th>等级</th>
                            <th style="width: 250px;">操作</th>

                        </tr>
                        <tbody ID="bookTableTbody">
                        </tbody>
                    </table>
                </div>
                <br/>
                <br/>
                <!-- 分页模块  -->
                <div align="center">
                    <ul id="pagination-book" class="pagination-sm"
                        style="margin-top: -10px"></ul>
                </div>
            </div>

        </div>
    </div> <!-- /row -->




    <!--layer modal-->
    <div id="bookInfo" style="display: none;">
        <div class="widget-content" style="border: none;">
            <div class="tabbable">
                <div class="tab-content">
                    <div class="tab-pane active">
                        <form id="bookForm" class="form-horizontal" style=""/>
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label" for="bookTitle">书名</label>
                                    <div class="controls">
                                        <input type="text" class="input-large" name="name" id="bookTitle" value="${bookUpdate.title}"/>
                                        <span class="errorSheetFormLabel" ></span>
                                        <input type="hidden"  name="businessId" id="bookId"/>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->

                                <div class="control-group">
                                    <label class="control-label" for="bookCode">编号</label>
                                    <div class="controls">
                                        <input type="text" class="input-large" name="code" id="bookCode" value="${bookUpdate.bkeys}"/>
                                        <span class="errorSheetFormLabel" >&nbsp;&nbsp;&nbsp;</span>

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
                                    <label class="control-label" for="bookLabels" >等级</label>
                                    <div class="controls">
                                        <select name="bookCase" id="bookLabels" class="input-large" >
                                            <option value="0">初级</option>
                                            <option value="1">中级</option>
                                            <option value="2">高级</option>
                                            <option value="3">资深级</option>
                                            <option value="4">专家级</option>
                                        </select>
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->


                                <div class="control-group">
                                    <label class="control-label" for="bookLabels" >书籍</label>
                                    <div class="controls">

                                        <div id="queue_first" class="queue input-large" >

                                        </div>
                                        <div>
                                            <input id="file_upload_first" class="input-large" name="file_upload" type="file" >
                                        </div>
                                        <!--隐藏域-->
                                        <input id="bookUrl" name="bookUrl" type="hidden">
                                    </div> <!-- /controls -->
                                </div> <!-- /control-group -->
                            </fieldset>
                            <button type="reset" style="display: none;" id="resetBook" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
