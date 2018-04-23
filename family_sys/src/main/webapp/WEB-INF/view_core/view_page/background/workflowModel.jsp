/** * Created with family. * author: cy * Date: 16/6/8 * Time: 下午4:14 *
description: users */
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/common/common_home.jsp"%>
<head>
<title>流程模型</title>

</head>
<body>

	<!-- layer  form -->
	<link href="${ctx}/common/css/background/layer_form.css" rel='stylesheet' type='text/css' />

	<script src="${ctx}/page_resource/background/workflow/js/workflowModel.js"></script>
	<script type="text/javascript">
		modelParam.modelId = "${param.modelId}";

	</script>
		<!-- Main content -->
		<section class="content">
			<div class="col-md-12 graphs">
				<div class="xs">
					<h3>流程模型</h3>
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<div data-toggle="buttons-checkbox" class="btn-group">
								<shiro:hasPermission name="sys:workflowModel:add">
									<button class="btn  btn-sm btn-info" type="button"
										onclick="addModel()">添加</button>
								</shiro:hasPermission>

								<shiro:hasPermission name="sys:workflowModel:delete">
									<button class="btn btn-danger btn-sm " type="button"
										onclick="deleteModel()">删除</button>
								</shiro:hasPermission>

								<shiro:hasPermission name="sys:workflowModel:update">
									<button class="btn  btn-sm btn-info" type="button"
											onclick="updateModel()">修改</button>
								</shiro:hasPermission>


								<shiro:hasPermission name="sys:workflowModel:deploy">
									<button class="btn  btn-sm btn-danger" type="button"
										onclick="deployModel()">部署</button>
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
										<h3 class="box-title">流程模型</h3>
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
													<button class="btn btn-default" onclick="queryWorkflowModel(1)">
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
												<th>KEY</th>
												<th>创建时间</th>
												<th>最新修改时间</th>
												<th>版本</th>
												<th>图片</th>
												<th>部署文件</th>
											</tr>
											<tbody ID="flowModelTbody">



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
							<ul id="paginationWorkflowModel" class="pagination-sm"
								style="margin-top: -10px"></ul>
						</div>
					</div>


				</div>
			</div>
			<br /> <br />
		</section>
	<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>

	<!--layer modal-->
	<div id="workflowModelInfo" style="display: none;">
		<div class="widget-content" style="border: none;">
			<br/>
			<div class="tabbable">
				<div class="tab-content">
					<div class="tab-pane active">
						<form class="form-horizontal" id="modelAddForm" method="post">
							<div class="form-group row">
								<label class="col-sm-2 control-label">标题</label>
								<div class="col-sm-8">
									<div class="input-group input-icon right">
										<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
										</span>
										<input id="modelName" class="form-control1"
													   name="modelName"
													   type="text" placeholder="请输入模型标题">
									</div>
								</div>
								<div class="col-sm-2">
									<p class="help-block"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">key</label>
								<div class="col-sm-8">
									<div class="input-group input-icon right">
										<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
										</span>
										<input id="modelKey" class="form-control1"
														    name="modelKey"
														   type="text" placeholder="请输入模型key">
									</div>
								</div>
								<div class="col-sm-2">
									<p class="help-block"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">描述</label>
								<div class="col-sm-8">
									<div class="input-group input-icon right">
										<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
										</span>
										<input id="modelDes" class="form-control1"
											   name="modelDes"
											   type="text" placeholder="请输入模型描述">
									</div>
								</div>
								<div class="col-sm-2">
									<p class="help-block"></p>
								</div>
							</div>
							<button type="reset" style="display: none;" id="resetWorkflowModel" />
						</form>
					</div>
				</div>
			</div>
		</div> <!-- /widget-content -->

	</div>

	<!--float modal-->


</body>