<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/common/common_home.jsp"%>
<head>
<title>数据字典</title>
</head>
<body>
<script src="${ctx}/page_resource/background/dictionary/js/dictionary.js"></script>

		<!-- Main content -->
		<section class="content">
			<div class="col-md-12 graphs">
				<div class="xs">
					 <h3>数据字典</h3>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div data-toggle="buttons-checkbox" class="btn-group">
                            <shiro:hasPermission name="sys:user:add">
                                <button onclick="addDictionary()" class="btn  btn-sm btn-info" type="button" >添加</button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="sys:user:add">
                                <button class="btn btn-danger btn-sm " type="button" onclick="deleteDictionary()">删除</button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="sys:user:add">
                                <button class="btn  btn-sm btn-info" type="button" onclick="updateDictionary()">修改</button>
                            </shiro:hasPermission>

							</div>
							<%--<div data-toggle="buttons-checkbox" class="btn-group">--%>
							<%--<shiro:hasPermission name="oa:welfare:view">--%>
							<%--<button class="btn btn-sm btn-info" type="button" onclick="">修改</button>--%>
							<%--</shiro:hasPermission>--%>

							<%--</div>--%>
							<%--<div data-toggle="buttons-checkbox" class="btn-group">--%>
							<%--<shiro:hasPermission name="oa:welfare:view">--%>
							<%--<button class="btn btn-danger btn-sm btn-info" type="button" onclick="">删除</button>--%>
							<%--</shiro:hasPermission>--%>
							<%--</div>--%>
						</div>
						<br />
		
					</div>
					<div class="col-sm-12"></div>
					<div class="box" style="border-top: 0px;">
						<div class="row">
							<div class="col-xs-12">
								<div class="box" style="border-top: 0px;">
									<div class="box-header">
										<h3 class="box-title">数据列表</h3>
										<div class="box-tools">
											<div class="input-group input-group-sm" style="width: 200px;">
											   <div class="input-group-btn">
													<span class="box-title" style="font-size: 15px;">Key :&nbsp;
													</span>
												</div>
												<input type="text" placeholder="请输入名称" class="input-sm form-control" 
													style="border-color: #29A233" name="name" id="name" />
												<div class="input-group-btn">
													<button type="button" onclick="searchDic()" class="btn btn-sm btn-info">搜索</button>
												</div>
											</div>
										</div>
									</div>
									<div class="box-body table-responsive no-padding">
										<table class="table table-hover">
											<tr>
												<th>#</th>
					                            <th>名称</th>
					                        	<th>值</th>
					                            <th>类型</th>
					                            <th>创建时间</th>
					                            <th>创建人</th>
					                            <th>启/停用</th>
											</tr>
											<tbody ID="dictionaryTableTbody">
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

						<input type="hidden" name="pageNo" id="pageNo" value="1" />
						<!-- 分页模块  -->
						<div align="center">
							<ul id="pagination-dictionary" class="pagination-sm"
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