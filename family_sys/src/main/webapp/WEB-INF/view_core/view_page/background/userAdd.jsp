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

<title>增加用户</title>
</head>
<body>
		<!-- Main content -->
		<section class="content">
				<div class="xs">
					<h3>增加用户</h3>
					<div class="tab-content">
						<div class="tab-pane active" id="horizontal-form">
							<!--onsubmit="return operateConfirm('userAddForm')"-->
							<form class="form-horizontal" id="userAddForm" method="post">
								<div hidden="true"  class="form-group">
									<label class="col-sm-2 control-label">登录名称</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> 
											<input hidden="true" id="user_id" value="${requestScope.userUpdate.businessId}" name="businessId">
											<%-- <input type="text" id="loginName" class="form-control1" name="loginName" placeholder="登录名称   请输入您的企业邮箱" value="${requestScope.userUpdate.loginName}"> --%>
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">邮箱</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-envelope-o"></i>
											</span> <input id="email" class="form-control1" 
												value="${requestScope.userUpdate.email}" name="email"
												type="text" placeholder="您的企业邮箱，同时也是您的登陆账号">
											<%--<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>--%>
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
											<%--<span class="glyphicon glyphicon-remove-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div><!-- onchange="$('#loginName').val(this.value)" -->
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">姓名</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" name="name"
												placeholder="请输入您的真实姓名" value="${requestScope.userUpdate.name}">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">登陆密码</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> <input type="password" id="password" name="password"
												value="${requestScope.userUpdate.password}"
												class="form-control1" placeholder="Password" placeholder="请输入您的密码">
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">重复密码</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i class="fa fa-key"></i>
											</span> <input type="password" id="confirm_password"
												name="confirm_password" class="form-control1"
												placeholder="重复密码">
											<%--<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">工号</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" id="no" class="form-control1"
												value="${requestScope.userUpdate.no}" name="no"
												placeholder="工号">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">电话</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" name="phone"
												value="${requestScope.userUpdate.phone}"
												placeholder="区号-电话号">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">手机号</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="mobile"
												value="${requestScope.userUpdate.mobile}" name="mobile"
												placeholder="手机号">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								
																<div class="form-group">
									<label class="col-sm-2 control-label">身份证号</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="personId"
												value="${requestScope.userUpdate.personId}" name="personId"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								
								<div class="form-group">
									<label class="col-sm-2 control-label">入职日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="entryDate"
												value="${requestScope.userUpdate.entryDate}" name="entryDate"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">员工组</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> 
											<select value="${requestScope.userUpdate.emplGroup}" name="emplGroup" id="selector_emplGroup" class="form-control1">
											</select>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
																<div class="form-group">
									<label class="col-sm-2 control-label">是否试用</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="trialOrNot"
												value="${requestScope.userUpdate.trialOrNot}" name="trialOrNot"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								
								<div class="form-group">
									<label class="col-sm-2 control-label">试用时长</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="trialLong"
												value="${requestScope.userUpdate.trialLong}" name="trialLong"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">试用到期日</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="trialDate"
												value="${requestScope.userUpdate.trialDate}" name="trialDate"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								
																<div class="form-group">
									<label class="col-sm-2 control-label">工作地点</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="workPosition"
												value="${requestScope.userUpdate.workPosition}" name="workPosition"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								
								
								<div class="form-group">
									<label class="col-sm-2 control-label">户籍地址</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="address"
												value="${requestScope.userUpdate.address}" name="address"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">紧急联系人</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="urgencyPerson"
												value="${requestScope.userUpdate.urgencyPerson}" name="urgencyPerson"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">紧急联系人电话</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="urgencyLine"
												value="${requestScope.userUpdate.urgencyLine}" name="urgencyLine"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">毕业学校/城市</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="college"
												value="${requestScope.userUpdate.college}" name="college"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">专业</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="speciality"
												value="${requestScope.userUpdate.speciality}" name="speciality"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">学历</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span>
											<select value="${requestScope.userUpdate.degree}" name="degree" id="selector_degree" class="form-control1">
											</select>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">学位</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> 
											<select value="${requestScope.userUpdate.academic}" name="academic" id="selector_academic" class="form-control1">
											</select>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">毕业日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="graduateDate"
												value="${requestScope.userUpdate.graduateDate}" name="graduateDate"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">参加工作日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="workDate"
												value="${requestScope.userUpdate.workDate}" name="workDate"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">民族</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span>
											<select value="${requestScope.userUpdate.nation}" name="nation" id="selector_nation" class="form-control1">
											</select>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">籍贯</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="hometown"
												value="${requestScope.userUpdate.hometown}" name="hometown"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">户口性质</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> 
											<select value="${requestScope.userUpdate.personProperty}" name="personProperty" id="selector_personProperty" class="form-control1">
											</select>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
<!-- 								<div class="input-group">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							下拉菜单 
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li ><a onclick="$('#asd').val(this.text)">功能</a></li>
							<li><a href="#">另一个功能</a></li>
							<li><a href="#">其他</a></li>
							<li class="divider"></li>
							<li><a href="#">分离的链接</a></li>
						</ul>
					</div>/btn-group
					<input type="text" id="asd"  class="form-control">
				</div>/input-group -->
								
								<div class="form-group">
									<label class="col-sm-2 control-label">出生日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="birthday"
												value="${requestScope.userUpdate.birthday}" name="birthday"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">性别</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span>
											<select value="${requestScope.userUpdate.sex}" name="sex" id="selector_sex" class="form-control1">
											</select>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">婚姻状况</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> 
												<select value="${requestScope.userUpdate.marryOrNot}" name="marryOrNot" id="selector_marryOrNot" class="form-control1">
											</select>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">银行名称</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="bankName"
												value="${requestScope.userUpdate.bankName}" name="bankName"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								
																<div class="form-group">
									<label class="col-sm-2 control-label">银行卡号</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="bankNum"
												value="${requestScope.userUpdate.bankNum}" name="bankNum"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">受款人</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="accountBy"
												value="${requestScope.userUpdate.bankName}" name="accountBy"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">合同类型</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> 
											<select value="${requestScope.userUpdate.contractType}" name="contractType" id="selector_contractType" class="form-control1">
											</select>
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">合同开始日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="contractStart"
												value="${requestScope.userUpdate.contractStart}" name="contractStart"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">合同到期日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="contractEnd"
												value="${requestScope.userUpdate.contractEnd}" name="contractEnd"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">续约合同开始日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="renewContractStart"
												value="${requestScope.userUpdate.renewContractStart}" name="renewContractStart"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">续约合同到期日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="renewContractEnd"
												value="${requestScope.userUpdate.renewContractEnd}" name="renewContractEnd"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">协议类型</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="text" class="form-control1" id="agreementType"
												value="${requestScope.userUpdate.agreementType}" name="agreementType"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">协议生效日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="agreementStart"
												value="${requestScope.userUpdate.agreementStart}" name="agreementStart"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">协议失效日期</label>
									<div class="col-sm-8">
										<div class="input-group input-icon right">
											<span class="input-group-addon"> <i
												class="fa fa-comment"></i>
											</span> <input type="date" class="form-control1" id="agreementEnd"
												value="${requestScope.userUpdate.agreementEnd}" name="agreementEnd"
												placeholder="">
										</div>
									</div>
									<div class="col-sm-2">
										<p class="help-block"></p>
									</div>
								</div>
								

								<div >
									<div class="row">
										<div class="col-sm-16 col-sm-offset-4">
											<button class="btn-default btn">确认提交</button>
											<input type="reset" class="btn-default btn"
												onclick="javascript:$('#loginName').focus();" />
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>




				</div>

		</section>
<script src="${ctx}/page_resource/background/user/js/userAdd.js"></script>	
</body>
</html>
