/** * Created with family. * author: cy * Date: 16/6/8 * Time: 下午4:14 *
description: users */
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/common/common_home.jsp"%>
<head>
<title>流程表单</title>

</head>
<body>
	<!-- layer  form -->
	<link href="${ctx}/common/css/background/layer_form.css" rel='stylesheet' type='text/css' />

	<script src="${ctx}/page_resource/background/workflow/js/workflowForm.js"></script>
	<script type="text/javascript">
		formParam.modelId = "${param.modelId}";
	</script>

		<!-- Main content -->
		<section class="content">
			<div class="col-md-12 graphs">
				<div class="xs">
					<h3>流程表单</h3>
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<div data-toggle="buttons-checkbox" class="btn-group">
								<shiro:hasPermission name="sys:workflowForm:add">
									<button class="btn  btn-sm btn-info" type="button"
											onclick="addForm()">添加</button>
								</shiro:hasPermission>

								<shiro:hasPermission name="sys:workflowForm:delete">
									<button class="btn btn-danger btn-sm " type="button"
											onclick="deleteForm()">删除</button>
								</shiro:hasPermission>

								<shiro:hasPermission name="sys:workflowForm:update">
									<button class="btn  btn-sm btn-info" type="button"
											onclick="updateForm()">修改</button>
								</shiro:hasPermission>

								<shiro:hasPermission name="sys:workflowForm:design">
									<button class="btn  btn-sm btn-success" type="button"
											onclick="designForm()">设计</button>
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
										<h3 class="box-title">流程表单</h3>
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
													<button class="btn btn-default" onclick="selectProcessDefinition(1)">
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
												<th>名称</th>
												<th>code</th>
												<th>数据表</th>
												<th>表单参数个数</th>
											</tr>
											<tbody ID="flowFormTbody">

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
							<ul id="paginationWorkflowDeployment" class="pagination-sm"
								style="margin-top: -10px"></ul>
						</div>
					</div>




				</div>
			</div>
			<br /> <br />
		</section>
	<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>


	<!--form modal-->
	<div id="workflowFormInfo" style="display: none;">
		<div class="widget-content" style="border: none;">
			<br/>
			<div class="tabbable">
				<div class="tab-content">
					<div class="tab-pane active">
						<form class="form-horizontal" id="formAddForm" method="post">
							<div class="form-group row">
								<label class="col-sm-2 control-label">表单名称</label>
								<div class="col-sm-8">
									<div class="input-group input-icon right">
										<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
										</span>
										<input id="modelName" class="form-control1"
											   name="modelName"
											   type="text" placeholder="请输入表单名称">
									</div>
								</div>
								<div class="col-sm-2">
									<p class="help-block"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">表单编号</label>
								<div class="col-sm-8">
									<div class="input-group input-icon right">
										<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
										</span>
										<input id="modelKey" class="form-control1"
											   name="modelKey"
											   type="text" placeholder="请输入表单编号">
									</div>
								</div>
								<div class="col-sm-2">
									<p class="help-block"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">表单描述</label>
								<div class="col-sm-8">
									<div class="input-group input-icon right">
										<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
										</span>
										<input id="des" class="form-control1"
											   name="des"
											   type="text" placeholder="请输入表单描述">
									</div>
								</div>
								<div class="col-sm-2">
									<p class="help-block"></p>
								</div>
							</div>
							<button type="reset" style="display: none;" id="resetWorkflowForm" />
						</form>
					</div>
				</div>
			</div>
		</div> <!-- /widget-content -->

	</div>

	<!--form modal-->
</body>