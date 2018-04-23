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
    <title>用户管理</title>

</head>
<body>
<script src="${ctx}/page_resource/background/code/js/generateCode.js"></script>



    <!-- Main content -->
    <section class="content">
        <div class="col-md-12 graphs">
            <div class="xs">
                <h3>代码构建</h3>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div data-toggle="buttons-checkbox" class="btn-group">
                            <shiro:hasPermission name="sys:user:add">
                                <button class="btn  btn-sm btn-info" type="button" onclick="startGenerateCode()">开始构建</button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="sys:user:delete">
                                <button class="btn btn-danger btn-sm " type="button" onclick="deleteGenerateCode(this)">删除构建</button>
                            </shiro:hasPermission>
                        </div>

                    </div>
                    <br/>
                    <div class="ibox-content">

						<br/>
					<div class="col-sm-12"></div>
					<div class="box" style="border-top: 0px;">
						<div class="row">
							<div class="col-xs-12">
								<div class="box" style="border-top: 0px;">
									<div class="box-header">
										<h3 class="box-title">用户列表</h3>
										<div class="box-tools">
											<div class="input-group input-group-sm" style="width: 200px;">
											   <div class="input-group-btn">
													<span class="box-title" style="font-size: 15px;">Key :&nbsp;
													</span>
												</div>
												<input type="text" placeholder="请输入登录名" class="input-sm form-control" 
													style="border-color: #29A233" name="loginName" id="loginName" />
												<div class="input-group-btn">
													<button type="button" onclick="search()" class="btn btn-sm btn-info">搜索</button>
												</div>
											</div>
										</div>
									</div>
									<div class="box-body table-responsive no-padding">
										<table class="table table-hover">
											<tr>
												<th>#</th>
					                            <th>登录名</th>
					                            <th>姓名</th>
					                            <th>工号</th>
					                            <th>邮箱</th>
					                            <th>手机</th>
					                            <th>最后登陆时间</th>
											</tr>
											<tbody ID="userTableTbody">



											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

						<input type="hidden" name="pageNo" id="pageNo" value="1" />
						<!-- 分页模块  -->
						<div align="center">
							<ul id="pagination-user" class="pagination-sm"
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
			</div>
		</div>
	</section>

	<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>