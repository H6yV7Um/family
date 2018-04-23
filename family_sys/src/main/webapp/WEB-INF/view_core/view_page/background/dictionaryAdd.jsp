<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/common/common_home.jsp"%>
<html>
<head>

<title>增加数据</title>

</head>
<body>
		<!-- Main content -->
		<section class="content">
				<div class="xs">
					<h3>数据信息</h3>
					<div class="tab-content">
						<div class="tab-pane active" id="horizontal-form">
							<form class="form-horizontal" id="dictionaryAddForm" >
								<div class="form-group">
									<label class="col-sm-2 control-label">类型</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input hidden="id" id="id"
												value="${requestScope.dictionaryUpdate.id}" name="id"> 
												<select name="type" value="${requestScope.dictionaryUpdate.type}" id="selector1"
													class="form-control1">
												</select>

											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">名称</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> <input type="text" id="name" name="name"
												value="${requestScope.dictionaryUpdate.name}"
												class="form-control1" placeholder="名称">
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">值</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> <input type="text" id="value" name="value"
												value="${requestScope.dictionaryUpdate.value}"
												class="form-control1" placeholder="值">
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">开始时间</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> <input type="datetime" id="create_date"  readonly
												value="${requestScope.dictionaryUpdate.create_date}"
												class="form-control1" placeholder="开始时间">
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">修改时间</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> <input type="datetime" id="update_date" readonly
												value="${requestScope.dictionaryUpdate.update_date}"
												class="form-control1" placeholder="修改时间">
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">创建人</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> <input type="text" id="create_by" readonly
												value="${requestScope.dictionaryUpdate.create_by}"
												class="form-control1" placeholder="创建人">
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">修改人</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> <input type="text" id="update_by" readonly
												value="${requestScope.dictionaryUpdate.update_by}"
												class="form-control1" placeholder="修改人">
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">标示</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> 
											<select name="flag" class="form-control1">
												<option value="1">启用</option>
												<option value="0">停用</option>
											</select>
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">备注</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> <input type="text" id="remarks" name="remarks"
												value="${requestScope.dictionaryUpdate.remarks}"
												class="form-control1" placeholder="备注">
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>

								
									<div class="row">
										<div class="col-sm-16 col-sm-offset-4">
											<button class="btn-default btn">Submit</button>
											<input type="reset" class="btn-default btn"
												onclick="javascript:$('#dictionarySubmit').focus();" />
										</div>
									</div>
									
							</form>
						</div>
					</div>

				</div>

		</section>
<script src="${ctx}/page_resource/background/dictionary/js/dictionaryAdd.js"></script>
</body>
</html>
