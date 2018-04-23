/** * Created with family. * author: cy * Date: 16/6/8 * Time: 下午4:14 *
description: users */
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/common/common_home.jsp"%>
<head>
<title>流程实例</title>

</head>
<body>
	<script src="${ctx}/page_resource/background/workflow/js/workflowInstance.js"></script>



		<!-- Main content -->
		<section class="content">
			<div class="col-md-12 graphs">
				<div class="xs">
					<h3>流程实例</h3>
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<div data-toggle="buttons-checkbox" class="btn-group">
								<shiro:hasPermission name="sys:user:add">
									<button class="btn  btn-sm btn-info" type="button"
										onclick="addUser()">添加</button>
								</shiro:hasPermission>

								<shiro:hasPermission name="sys:user:delete">
									<button class="btn btn-danger btn-sm " type="button"
										onclick="deleteUser(this)">删除</button>
								</shiro:hasPermission>

							</div>
						</div>
						<br />
		
					</div>
					<div class="col-sm-12"></div>
					<div class="box" style="border-top: 0px">
						<div class="row">
							<div class="col-xs-12">
								<div class="box" style="border-top: 0px">
									<div class="box-header">
										<h3 class="box-title">流程实例</h3>
										<div class="box-tools">
											<div class="input-group input-group-sm" style="width: 150px;">
											

												<div class="input-group-btn">
													<span class="box-title" style="font-size: 15px;">名称 :&nbsp;
													</span>
												</div>
												    <input class="form-control pull-right"  name="table_search"
													placeholder="Search" type="text" id="nameinput"style="width: 150px">
													
											   <div class="input-group-btn">
													<span class="box-title" style="font-size: 15px;">&nbsp;Key :&nbsp;
													</span>
												</div>
												<input class="form-control pull-right"  name="table_search"
													placeholder="Search" type="text" id="keyinput"style="width: 150px">
												
												<div class="input-group-btn">
													<button class="btn btn-default" onclick="queryWorkflowInstance(1)">
														<i class="fa fa-search"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="box-body table-responsive no-padding">
										<table class="table table-hover">
											<tr>
												<th>#</th>
												<th>流程实例ID</th>
												<th>BusinessKey</th>
												<th>流程定义ID</th>
												<th>活动ID</th>
												<th>操作</th>
											</tr>
											<tbody ID="flowInstanceTbody">



											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>


						</table>
						<input type="hidden" name="pageNo" id="pageNo" value="1" />
						<!-- 分页模块  -->
						<div align="center">
							<ul id="paginationWorkflowInstance" class="pagination-sm"
								style="margin-top: -10px"></ul>
						</div>
					</div>



					<!-- 分配角色   模态框（Modal） -->
					<div class="modal fade" id="roleModal" tabindex="-1" role="dialog"
						aria-labelledby="menuModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="roleModalLabel">分配岗位</h4>
								</div>
								<div class="bs-example4" data-example-id="contextual-table">
									<table class="table">
										<thead>
											<tr>
												<th>#</th>
												<th>岗位名称</th>
												<!-- <th>角色信息</th> -->
												<!-- <th>启/停用</th> -->
											</tr>
										</thead>
										<tbody ID="roleTableTbody">

										</tbody>

									</table>
									<input type="hidden" name="pageNo" id="pageRoleNo" value="1" />
									<ul id="pagination-role" class="pagination-sm"
										style="margin-top: -10px"></ul>
								</div>
								<div class="modal-footer">

									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button type="button" class="btn btn-primary"
										onclick="submitUserRole()">提交更改</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
					</div>
					<!-- /.modal -->

				</div>
			</div>
			<br /> <br />
		</section>
	<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>

</body>