/** * Created with family. * author: cy * Date: 16/6/8 * Time: 下午4:14 *
description: users */
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/common/common_home.jsp"%>
<head>
<title>角色管理</title>

</head>
<body>
<!-- treeView -->
<script src="${ctx}/common/js/treeView/bootstrap-treeview.js" type="text/javascript"></script>
<script src="${ctx}/page_resource/background/role/js/role.js"></script>
<script type="text/javascript">
	roleParam.modelId = "${param.modelId}";
	roleParam.urlSelf = "/view/backGround/role";
</script>

		<!-- Main content -->
		<section class="content">
			<div class="col-md-12 graphs">
				<div class="xs">
					 <h3>角色管理</h3>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div data-toggle="buttons-checkbox" class="btn-group">
                            <shiro:hasPermission name="sys:role:add">
                                <button class="btn  btn-sm btn-info" type="button" onclick="addRole()">添加</button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="sys:role:delete">
                                <button class="btn btn-danger btn-sm " type="button" onclick="deleteRole()">删除</button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="sys:role:update">
                                <button class="btn  btn-sm btn-info" type="button" onclick="updateRole()">修改</button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="sys:role:permission">
                                <button class="btn  btn-sm btn-danger" type="button" onclick="permissionRole()">分配权限</button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="sys:role:permission">
                                <button class="btn  btn-sm btn-info" type="button" onclick="permissionDept()">指定岗位</button>
                            </shiro:hasPermission>
                        </div>
					</div>
					<br />
					<div class="col-sm-12"></div>
					<div class="box" style="border-top: 0px;">
						<div class="row">
							<div class="col-xs-12">
								<div class="box" style="border-top: 0px;">
									<div class="box-header">
										<h3 class="box-title">角色列表</h3>
										<div class="box-tools">
											<div class="input-group input-group-sm" style="width: 210px;">
											   <div class="input-group-btn">
													<span class="box-title" style="font-size: 15px;">Key :&nbsp;
													</span>
												</div>
												<input type="text" placeholder="请输入角色名称" class="input-sm form-control" 
												style="border-color: #29A233" name="roleName" id="roleName" />
												<div class="input-group-btn">
                                        		<button type="button" onclick="searchRole()" class="btn btn-sm btn-info">搜索</button>
												</div>
											</div>
										</div>
									</div>
									<div class="box-body table-responsive no-padding">
										<table class="table table-hover">
											<tr>
												<th>#</th>
												<th>角色名称</th>
												<th>角色编码</th>
						                        <th>创建人</th>
						                        <th>创建时间</th>
						                        <th>启/停用</th>
											</tr>
											<tbody ID="roleTableTbody">



											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

						<input type="hidden" name="pageNo" id="pageNo" value="1" />
						<!-- 分页模块  -->
						<div align="center">
							<ul id="pagination-role" class="pagination-sm"
								style="margin-top: -10px"></ul>
						</div>
					</div>



					<!-- 分配角色   模态框（Modal） -->
					<div class="modal fade" id="roleModal" tabindex="-1" role="dialog"
                     aria-labelledby="menuModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close"
                                        data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="menuModalLabel">
                                    分配权限
                                </h4>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-sm-8" >
                                        <div id="treeviewMenu" class=""></div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">

                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <button type="button" class="btn btn-primary" onclick="submitRoleMenu()">
                                    提交更改
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div>
                </div><!-- /.modal -->
					
					 <div class="modal fade" id="deptModal" tabindex="-1" role="dialog"
                     aria-labelledby="deptModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close"
                                        data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="deptModalLabel"><!-- 、角色后面绑部门，添加时，少的用弹框，角色为主 -->
                                   	指定岗位
                                </h4>
                            </div>
                            <div class="modal-body">
                				<div class="row" style="margin-top: 5%">
                				<input type="text" hidden="true"   id="roleId" value=""/>
                    				<div class="col-sm-8" >
                        				<div id="treeviewDept" class=""></div>
                    				</div>
                				</div>
                            </div>
                            <div class="modal-footer">

                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
								<button type="button" class="btn btn-primary" onclick="submitRoleDept()">提交更改</button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>

				</div>
			</div>
			</div>
			<br /> <br />
		</section>
	<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>