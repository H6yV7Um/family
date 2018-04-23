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
<script src="${ctx}/page_resource/homeBack/account/js/account.js"></script>

<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        帐号管理
    </h1>


    <div class="row">
        <div class="span9" style="">
            <div class="box" style="border-top: 0px;">
                <div class="box-header">
                    <h3 class="box-title">帐号列表</h3>
                    <shiro:hasPermission name="home:account:add">
                        <button style="margin-left: 3em;" type="button" onclick="addAccount()" class="btn btn-sm btn-success">添加</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:account:delete">
                        <button style="margin-left: 1em;" type="button" onclick="deleteAccount()" class="btn btn-sm btn-danger">删除</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="home:account:update">
                        <button style="margin-left: 1em;" type="button" onclick="updateAccount()" class="btn btn-sm btn-info">修改</button>
                    </shiro:hasPermission>
                    <div class="box-tools">

                        <div class="input-group input-group-sm" style="width: 200px">
                            <div class="input-group-btn">
                                <span class="box-title" style="font-size: 15px;">标题 :&nbsp;
                                </span>
                            </div>
                            <input type="text" placeholder="请输入标题" style="margin-bottom:0px;"
                                   name="accountName" id="accountName" />
                            <div class="input-group-btn">
                                &nbsp;&nbsp;<button type="button" onclick="searchAccount()" class="btn btn-sm btn-info">搜索</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="padding: 1%;float: right" id="serverStaticBtns">

                    <button class="btn btn-success" type="button" id="btnType1" vType="1">
                        网站 <span class="badge" style="display: none;background-color: #da4f49"></span>
                    </button>&nbsp;
                    
                    <button class="btn btn-primary" type="button" id="btnType2" vType="2" >
                        服务器 <span class="badge" style="display: none;background-color: #da4f49"></span>
                    </button>&nbsp;

                    <button class="btn btn-info" type="button" id="btnType0" vType="0">
                        其他 <span class="badge" style="display: none;background-color: #da4f49"></span>
                    </button>
                </div>
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover" >
                        <tr>
                            <th>#</th>
                            <th>标题</th>
                            <th>帐号</th>
                            <th>密码</th>
                            <th>url</th>
                            <th>描述</th>
                            <th>操作</th>
                        </tr>
                        <tbody ID="accountTableTbody">
                        </tbody>
                    </table>
                </div>
                <br/>
                <br/>
                <!-- 分页模块  -->
                <div align="center">
                    <ul id="pagination-account" class="pagination-sm"
                        style="margin-top: -10px"></ul>
                </div>
            </div>

        </div>
    </div> <!-- /row -->




    <!--layer modal-->
    <div id="accountServerInfo" style="display: none;">
        <div class="widget-content" style="border: none;">
            <div class="tabbable">
                <div class="tab-content">
                    <div class="tab-pane active">
                        <form id="accountServerForm" class="form-horizontal" />
                            <fieldset>

                                <div class="control-group">
                                    <input type="hidden" name="accountServerBusinessId" id="accountServerBusinessId"/>
                                    <span class="spanAccountServer" style="width: 223px;">标题</span>
                                    <span class="spanAccountServer" style="width: 105px;">帐号</span>
                                    <span class="spanAccountServer" style="width: 105px;">密码</span>
                                    <span class="spanAccountServer" style="width: 105px;">端口</span>
                                    <span class="spanAccountServer" style="width: 223px;">描述</span>
                                    <span class="spanAccountServer" style="width: 55px;">操作</span>

                                </div> <!-- /control-group -->
                                <div class="control-group" id="inputAccountServer">
                                    <input type="text" class="input-large" name="serverTitle" id="serverTitle" />
                                    <input type="text" class="input-small" name="serverAccount" id="serverAccount" />
                                    <input type="text" class="input-small" name="serverPasswd" id="serverPasswd" />
                                    <input type="text" class="input-small" name="serverPort" id="serverPort" />
                                    <input type="text" class="input-large" name="serverDescription" id="serverDescription" />
                                    <button type="button" class="btn btn-default" aria-label="Left Align" onclick="addServer()">
                                        <span class="icon-plus" style="margin-left: 3%" ></span>
                                    </button>
                                    <!--<button type="button" class="btn btn-default" aria-label="Left Align">
                                        <span class="icon-minus" style="margin-left: 3%" ></span>
                                    </button>-->
                                </div> <!-- /control-group -->

                                <div class="control-group">
                                   <table>
                                       <tbody id="serverSettingTbody">
                                            <%--<tr>
                                                <td style="width: 222px;"></td>
                                                <td style="width: 101px;"></td>
                                                <td style="width: 101px;"></td>
                                                <td style="width: 101px;"></td>
                                                <td style="width: 55px;">
                                                    <button type="button" class="btn btn-default" aria-label="Left Align" onclick="addServer()">
                                                        <span class="icon-minus" style="margin-left: 3%" ></span>
                                                    </button>
                                                </td>
                                            </tr>--%>

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
        </div> <!-- /widget-content -->








</div> <!-- /span9 -->
<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
