<%--
  Created by family.
  User: cy
  Date: 16/6/21
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/common/common_home.jsp"%>
<html>
<head>

<title>开始构建</title>
</head>
<body>
	<script src="${ctx}/page_resource/background/code/js/generateCodeAdd.js"></script>
    <script type="text/javascript">
        codeParam.modelId = "${param.modelId}";
        codeParam.urlSelf = "/view/background/role";
    </script>
		<!-- Main content -->
		<section class="content">
				<div class="xs">
					 <h3>开始构建</h3>
                <div class="tab-content">
                    <div class="tab-pane active" id="horizontal-form">
                        <!--onsubmit="return operateConfirm('userAddForm')"-->
                        <form class="form-horizontal"  id="codeConnectForm"  method="post">

                            <div class="form-group">
                                <label class="col-sm-1 control-label">数据库类型</label>
                                <div class="col-sm-3">
                                    <div class="input-group input-icon right">
                                        <span class="input-group-addon">
                                            <i class="fa fa-comment"></i>
                                        </span>
                                        <select value="" name="db" id="db" class="form-control1">
                                            <option selected="selected" value="1">mysql</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block"></p>
                                </div>

                                <label class="col-sm-1 control-label">数据库IP</label>
                                <div class="col-sm-3">
                                    <div class="input-group input-icon right">
                                        <span class="input-group-addon">
                                            <i class="fa fa-comment"></i>
                                        </span>
                                        <input type="text" class="form-control1" id="ip" name="ip" value="127.0.0.1" placeholder="请输入IP">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block"></p>
                                </div>

                            </div>



                            <div class="form-group">
                                <label class="col-sm-1 control-label">数据库端口</label>
                                <div class="col-sm-3">
                                    <div class="input-group input-icon right">
                                        <span class="input-group-addon">
                                            <i class="fa fa-comment"></i>
                                        </span>
                                        <input type="text" class="form-control1" id="port" name="port" value="3306" placeholder="请输入PORT">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block"></p>
                                </div>


                                <label class="col-sm-1 control-label">数据库名称</label>
                                <div class="col-sm-3">
                                    <div class="input-group input-icon right">
                                        <span class="input-group-addon">
                                            <i class="fa fa-comment"></i>
                                        </span>
                                        <input type="text" class="form-control1" value="test_car" id="dbName" name="dbName" placeholder="请输入DB"/>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block"></p>
                                </div>
                            </div>


                            



                            <div class="form-group">
                                <label class="col-sm-1 control-label">数据库帐号</label>
                                <div class="col-sm-3">
                                    <div class="input-group input-icon right">
									<span class="input-group-addon">
										<i class="fa fa-comment"></i>
									</span>
                                        <input type="text" class="form-control1" value="root" id="username" name="username" placeholder="请输入DB帐号">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block"></p>
                                </div>

                                <label class="col-sm-1 control-label">数据库密码</label>
                                <div class="col-sm-3">
                                    <div class="input-group input-icon right">
                                        <span class="input-group-addon">
                                            <i class="fa fa-comment"></i>
                                        </span>
                                        <input type="password" class="form-control1" value="cy" id="password" name="password" placeholder="请输入DB密码">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-16 col-sm-offset-5">
                                    <button class="btn-default btn" >连接</button>
                                </div>
                            </div>
                          
                        </form>
                    </div>
                </div>

                <br/>
                    <div id="generateTable" class="box" style="border-top: 0px;display: none;">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box" style="border-top: 0px;">
                                    <div class="box-header">
                                        <h3 class="box-title">数据表</h3>

                                    </div>
                                    <div class="col-xs-6">
                                        <div class="box-body table-responsive no-padding">
                                            <table class="table table-hover">
                                                <tr>
                                                    <th># <input type="button"></th>
                                                    <th>表名</th>
                                                </tr>
                                                <tbody ID="tableTbodyF">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="col-xs-6">
                                        <div class="box-body table-responsive no-padding">
                                            <table class="table table-hover">
                                                <tr>
                                                    <th>#</th>
                                                    <th>表名</th>
                                                </tr>
                                                <tbody ID="tableTbodyS">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                </div>

                                <br/>


                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <form class="form-horizontal"    method="post">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">包名</label>
                                    <div class="col-sm-3">
                                        <div class="input-group input-icon right">
                                    <span class="input-group-addon">
                                        <i class="fa fa-comment"></i>
                                    </span>
                                            <input type="text" class="form-control1" value="" id="package" name="package" placeholder="请输入包名,用'.'分割">
                                        </div>
                                    </div>
                                    <div class="col-sm-1">
                                        <p class="help-block"></p>
                                    </div>

                                    <label class="col-sm-1 control-label">作者</label>
                                    <div class="col-sm-2">
                                        <div class="input-group input-icon right">
                                    <span class="input-group-addon">
                                        <i class="fa fa-comment"></i>
                                    </span>
                                            <input type="text" class="form-control1" value="" id="author" name="author" placeholder="请输入作者">
                                        </div>
                                    </div>
                                    <div class="col-sm-1">
                                        <p class="help-block"></p>
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" class="btn-default btn" onclick="constructTables()" >构建</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

				</div>

		</section>
</body>
</html>
