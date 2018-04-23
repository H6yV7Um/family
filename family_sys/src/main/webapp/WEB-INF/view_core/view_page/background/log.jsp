<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<head>
    <title>日志管理</title>

</head>
<body>
<script src="${ctx}/page_resource/background/log/js/log.js"></script>



    <!-- Main content -->
    <section class="content">
        <div class="col-md-12 graphs">
            <div class="xs">
                <h3>日志管理</h3>
					<div class="col-sm-12"></div>
					<div class="box" style="border-top: 0px;">
						<div class="row">
							<div class="col-xs-12">
								<div class="box" style="border-top: 0px;">
									<div class="box-header">
										<h3 class="box-title">日志列表</h3>
										<div class="box-tools">
											<div class="input-group" style="width: 400px;">
												<select name="level" class="col-sm-4 input-sm" id="selectorLevel">
													<option value="">请选择</option>
													<option value="FATAL">FATAL</option>
													<option value="ERROR">ERROR</option>
													<option value="WARN">WARN</option>
													<option value="INFO">INFO</option>
													<option value="DEBUG">DEBUG</option>
													<option value="TRACE">TRACE</option>
												</select>
												<input type="text" placeholder="请输入信息" class="col-sm-4 input-sm" 
													style="border-color: #29A233" name="message" id="message" />
												<div class="col-sm-2">
													<button type="button" onclick="searchLog()" class="btn btn-sm btn-info">搜索</button>
												</div>
												
											</div>
										</div>
									</div>
									<div class="box-body table-responsive no-padding">
										<table class="table table-hover">
											<tr>
											    <th>#</th>
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