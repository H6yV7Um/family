<%--
  Created by family.
  User: cy
  Date: 16/6/21
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/common/common_home.jsp"%>
<html>
<head>

<title>增加角色</title>
</head>
<body>
	<script src="${ctx}/page_resource/background/role/js/roleAdd.js"></script>
    <script type="text/javascript">
        roleParam.modelId = "${param.modelId}";
        roleParam.urlSelf = "/view/background/role";
    </script>
		<!-- Main content -->
		<section class="content">
				<div class="xs">
					 <h3>角色信息</h3>
                <div class="tab-content">
                    <div class="tab-pane active" id="horizontal-form">
                        <!--onsubmit="return operateConfirm('userAddForm')"-->
                        <form class="form-horizontal"  id="roleAddForm"  method="post">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">角色名称</label>
                                <div class="col-sm-8">
                                    <div class="input-group input-icon right">
									<span class="input-group-addon">
										<i class="fa fa-comment"></i>
									</span>
									<input hidden="true" id="role_id" value="${requestScope.roleUpdate.businessId}" name="businessId">
                                        <input type="text" class="form-control1" id="name" name="name" value="${requestScope.roleUpdate.name}" placeholder="">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block"></p>
                                </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">角色编码</label>
                                <div class="col-sm-8">
                                    <div class="input-group input-icon right">
									<span class="input-group-addon">
										<i class="fa fa-comment"></i>
									</span>
                                        <input type="text" class="form-control1" value="${requestScope.roleUpdate.code}" id="code" name="code" placeholder="Default Input">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block"></p>
                                </div>
                            </div>

                                <div class="row">
                                    <div class="col-sm-16 col-sm-offset-4">
                                        <button class="btn-default btn" >Submit</button>
                                        <input type="reset" class="btn-default btn" onclick="javascript:$('#code').focus();"/>
                                    </div>
                                </div>
                          
                        </form>
                    </div>
                </div>




				</div>

		</section>
</body>
</html>
