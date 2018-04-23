/**
* Created with family.
* author: cy
* Date: 16/6/8
* Time: 下午4:14
* description: depts
*/
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<head>
    <title>部门管理</title>

</head>
<body>
<script src="${ctx}/common/js/treeView/bootstrap-treeview.js" type="text/javascript"></script>
<script src="${ctx}/page_resource/background/dept/js/dept.js"></script>


    <!-- Main content -->
    <section class="content">
        <div class="col-md-12 graphs">
            <div class="xs">
                <h3>部门管理</h3>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div data-toggle="buttons-checkbox" class="btn-group">
                            <shiro:hasPermission name="sys:dept:add">
                                <button class="btn  btn-sm btn-info" type="button" onclick="addDept()">添加</button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="sys:dept:delete">
                                <button class="btn btn-danger btn-sm " type="button" onclick="deleteDept()">删除</button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="sys:dept:update">
                                <button class="btn  btn-sm btn-info" type="button" onclick="updateDept()">修改</button>
                            </shiro:hasPermission>
                        </div>
                    </div>
                    <br/>
                    
                    
<%--
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-8 m-b-xs">
                                <div data-toggle="buttons" class="btn-group">
                                    <label class="btn btn-sm btn-default active"><input type="radio" name="userType" checked value="1"> 正常</label>

                                    <label class="btn btn-sm btn-default"><input type="radio" name="userType" value="2"> 禁用</label>
                                    <label class="btn btn-sm btn-default"><input type="radio" name="userType" value="2"> 全部</label>
                                </div>
                                <div data-toggle="buttons" class="btn-group">
                                    <label class="btn btn-sm btn-default"><input type="radio" name="userType" value="3"> 全部</label>
                                </div>
                            </div>
                        </div>
                    </div>
--%>

                
                <div class="row" style="margin-top: 5%">
                    <div class="col-sm-8" >
                        <div id="treeviewDept" class=""></div>
                    </div>
                </div>
                <!-- 添加部门   模态框（Modal） -->
                <div class="modal fade" id="deptModal" tabindex="-1" role="dialog"
                     aria-labelledby="deptModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close"
                                        data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="deptModalLabel">
                                    添加部门
                                </h4>
                            </div>
                            
                            <div class="modal-body">
                                <form class="form-horizontal" method="post"  id="deptAddForm" >

                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">部门名称</label>
                                        <div class="col-sm-6">
                                            <div class="input-group input-icon right">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-comment"></i>
                                                </span>
                                                <input type="text"  id="deptName" class="form-control1" name="" placeholder="部门名称">
                                                <%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">部门编码</label>
                                        <div class="col-sm-6">
                                            <div class="input-group input-icon right">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-comment"></i>
                                                </span>
                                                <input type="text"  id="deptCode" class="form-control1" name="deptCode" placeholder="部门编码">
                                                <%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">上级部门</label>
                                        <div class="col-sm-6">
                                            <div class="input-group input-icon right">

                                                <label class="control-label" id="upDept"></label>
                                                <input type="hidden" name="parentId" id="upDeptId">
                                                <input type="hidden" name="id" id="deptId">
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>
                                    <button type="reset" style="display: none;" id="deptReset">
                                    </button>
                                    <div align="center">
                                        <button  class="btn btn-primary" type="button" onclick="deptSubmit()">
                                                 提交更改
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <%--<div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                            </div>--%>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                    </div>
				</div>
            </div>
        </div>
        <br/>
        <br/>
    </section>

<%@ include file="/WEB-INF/common/pagination_footer.jsp" %>
</body>