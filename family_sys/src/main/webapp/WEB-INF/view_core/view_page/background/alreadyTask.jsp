<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<head>
    <title>已办任务</title>

</head>
<body>
<script src="${ctx}/page_resource/background/task/js/alreadyTask.js"></script>



    <!-- Main content -->
    <section class="content">
        <div class="col-md-12 graphs">
            <div class="xs">
                <h3>已办任务</h3>
					<div class="col-sm-12"></div>
					<div class="box" style="border-top: 0px;">
						<div class="row">
							<div class="col-xs-12">
								<div class="box" style="border-top: 0px;">
									<div class="box-header">
										<h3 class="box-title">日志列表</h3>
										<div class="box-tools">
											<div class="input-group input-group-sm" style="width: 150px;">
											   <div class="input-group-btn">
													<span class="box-title" style="font-size: 15px;">Key :&nbsp;
													</span>
												</div>
												<input class="form-control pull-right"  name="table_search"
													placeholder="key" type="text">
												<div class="input-group-btn">
													<button class="btn btn-default" type="submit">
														<i class="fa fa-search"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
									<div class="box-body table-responsive no-padding">
										<table class="table table-hover">
											<tr>
											    <th></th>
					                            <th>信息</th>
					                            <th>级别</th>
					                            <th>创建时间</th>
					                            <th>用户名</th>
											</tr>
											<tbody ID="logTableTbody">
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

						<input type="hidden" name="pageNo" id="pageNo" value="1" />
						<!-- 分页模块  -->
						<div align="center">
							<ul id="pagination-log" class="pagination-sm"
								style="margin-top: -10px"></ul>
						</div>
					</div>

				</div>
				</div>
			</div>
	</section>

	<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>