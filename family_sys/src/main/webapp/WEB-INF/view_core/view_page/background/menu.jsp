/**
* Created with family.
* author: cy
* Date: 16/6/8
* Time: 下午4:14
* description: users
*/
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<head>
    <title>菜单管理</title>

</head>
<body>
<script src="${ctx}/common/js/treeView/bootstrap-treeview.js" type="text/javascript"></script>

<script src="${ctx}/page_resource/background/menu/js/menu.js"></script>


<!-- Content Wrapper. Contains page content -->

    <!-- Main content -->
    <section class="content">
        <div class="col-md-12 graphs">
            <div class="xs">
                <h3>菜单管理</h3>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div data-toggle="buttons-checkbox" class="btn-group">
                            <shiro:hasPermission name="sys:menu:add">
                                <button class="btn  btn-sm btn-info" type="button" onclick="addMenu()">添加</button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="sys:menu:delete">
                                <button class="btn btn-danger btn-sm " type="button" onclick="deleteMenu()">删除</button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="sys:menu:update">
                                <button class="btn  btn-sm btn-info" type="button" onclick="updateMenu()">修改</button>
                            </shiro:hasPermission>

                        </div>
                    </div>
                    
                    

                
                <div class="row" style="margin-top: 5%">
                    <div class="col-sm-8" >
                        <div id="treeviewMenu" class=""></div>
                    </div>
                </div>
                <!-- 添加菜单   模态框（Modal） -->
                <div class="modal fade" id="menuModal" tabindex="-1" role="dialog"
                     aria-labelledby="menuModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close"
                                        data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="menuModalLabel">
                                    添加菜单
                                </h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" method="post" id="menuAddForm" >

                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">菜单名称</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-comment"></i>
                                                </span>
                                                <input type="text"  id="name" class="form-control1" name="name" placeholder="菜单名称">
                                                <%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">菜单编码</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-comment"></i>
                                                </span>
                                                <input type="text"  id="menuCode" class="form-control1" name="code" placeholder="菜单编码">
                                                <%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>



                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">菜单权限</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-comment"></i>
                                                </span>
                                                <input type="text"  id="permission" class="form-control1" name="permission" placeholder="菜单权限">
                                                <%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">菜单链接</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-comment"></i>
                                                </span>
                                                <input type="text"  id="href" class="form-control1" name="href" placeholder="菜单链接">
                                                <%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">菜单图标</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-comment"></i>
                                                </span>
                                                <input type="text"  id="icon" class="form-control1" name="icon" placeholder="菜单图标">
                                                <%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">菜单排序</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-comment"></i>
                                                </span>
                                                <input type="text"  id="sort" class="form-control1" name="sort" placeholder="菜单排序">
                                                <%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">是否展示</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">
                                                <div >
                                                    <label><input type="radio" value="1"   name="isShow" checked="checked"> 是</label>
                                                    <label><input type="radio" value="0"   name="isShow"> 否</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">菜单目标</label>
                                        <div class="col-sm-8">
                                            <div class="input-group input-icon right">
                                                <div class="radio block">
                                                    <label><input type="radio" value="_blank" name="target" checked="checked"> _blank</label>
                                                    <label><input type="radio" value="mainFrame" name="target"> mainFrame</label>
                                                    <label><input type="radio" value="_self" name="target"> _self</label>
                                                    <label><input type="radio" value="_parent" name="target"> _parent</label>
                                                    <label><input type="radio" value="_top" name="target"> _top</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">菜单模块</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">
                                                <div class="radio block">
                                                    <label><input type="radio" value="20" name="modelId" checked="checked"> 系统</label>
                                                    <label><input type="radio" value="21" name="modelId"> 主页</label>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">层级</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">

                                                <label class="control-label" id="caseMenu"></label>
                                                <input type="hidden" name="caseCount" id="caseCount"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">上级菜单</label>
                                        <div class="col-sm-7">
                                            <div class="input-group input-icon right">

                                                <label class="control-label" id="upMenu"></label>
                                                <input type="hidden" name="parentId" id="upMenuId">
                                                <input type="hidden" name="businessId" id="menuId">
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <p class="help-block"></p>
                                        </div>
                                    </div>

                                    <button style="display: none;"  type="reset"  id="menuReset">
                                    </button>
                                <div align="center">
                                    <button class="btn btn-primary">
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