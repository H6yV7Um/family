/**
 * Created by cy on 16/6/22.
 */
$(function() {
	
	initSexSelect('sex', $('#selector_sex'));
	initSexSelect('nation', $('#selector_nation'));
	initSexSelect('academic', $('#selector_academic'));
	initSexSelect('degree', $('#selector_degree'));
	initSexSelect('marryOrNot', $('#selector_marryOrNot'));
	initSexSelect('personProperty', $('#selector_personProperty'));
	initSexSelect('emplGroup', $('#selector_emplGroup'));
	initSexSelect('contractType', $('#selector_contractType'));
	
	if($("#user_id").val()==null||$("#user_id").val()==""){
		$("#loginName").focus();
	}else{
		$("#loginName").attr('disabled','disabled')
	}

	//回填 select
	$('#selector1 option').each(function() {
		$this = $(this).val();
		if ($this == $("#selector1").attr('val')) {
			$(this).attr('selected', true);
		}
	});

	$.validator.addMethod('mobile', function(value, element) {

		// /^1\d{10}$/ 来自支付宝的正则
		return this.optional(element) || /^1\d{10}$/.test(value);

	}, '请输入正确的手机号码');
	$.validator.addMethod('personId', function(value, element) {
		return this.optional(element) || /^\d{18}|(\d{17}(\d|x|X))$/.test(value);
	}, '请输入正确的身份证号');

	var validate = $("#userAddForm").validate(
			{
				debug : true, //调试模式取消submit的默认提交功能
				//errorClass: "label.error", //默认为错误的样式类为：error
				focusInvalid : false, //当为false时，验证无效时，没有焦点响应
				onkeyup : false,
				submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
					userSubmit.call(this);
				},

				rules : {
					email : {
						required : true,
						email : true,
				        /*remote: {
				        	url: family_ns.contextPath + "/user/queryLoginNameValidate" ,     //后台处理程序
				        	type: "post",               //数据发送方式
				        	dataType: "json",           //接受数据格式
				        	data: {                     //要传递的数据
				        		email: function() {
		                            return $("#email").val();
		                        },
		                        id:function(){
		                        	return $("#user_id").val();
		                        },
		                        page:1,
		                        rows:10
		                    }  //远程地址只能输出 "true" 或 "false"，不能有其他输出。
				        }*/
					},
					
					no : {
						required : true,
				        /*remote: {
				        	url: family_ns.contextPath + "/user/queryLoginNameValidate" ,     //后台处理程序
				        	type: "post",               //数据发送方式
				        	dataType: "json",           //接受数据格式
				        	data: {                     //要传递的数据
				        		no: function() {
		                            return $("#no").val();
		                        },
		                        id:function(){
		                        	return $("#user_id").val();
		                        },
		                        page:1,
		                        rows:10
		                    }  //远程地址只能输出 "true" 或 "false"，不能有其他输出。
				        }*/
					},
					
					name : {
						required : true
					},
					password : {
						required : true,
						rangelength : [ 6, 10 ]
					},
					confirm_password : {
						equalTo : "#password"
					},
					mobile : {
						mobile : true
					},
					personId:{
						personId : true
					}
				},
				messages : {
					loginName : {
						required : "必填",
						remote : "当前帐号已存在,不能使用,请更换"
					},
					
					no : {
						required : "必填",
						remote : "当前工号已存在,不能使用,请更换"
					},
					
					name : {
						required : "必填"
					},
					email : {
						required : "必填",
						email : "E-Mail格式不正确或已被注册"
					},
					password : {
						required : "不能为空",
						rangelength : $.validator
								.format("密码最小长度:{0}, 最大长度:{1}。")
					},
					confirm_password : {
						equalTo : "两次密码输入不一致"
					},
					mobile : {
						mobile : "请输入正确的手机号码"
					},
					personId : {
						personId : "请输入正确的身份证号"
					}
				},
				errorPlacement : function(error, element) {
					error.appendTo(element.parent("div").parent("div").next(
							"div").find("p"));
				},
				invalidHandler : function(errorMap, errorList) { // 如果表单验证不通过，将会触发这个函数
				/*            for (var p in errorMap){
				 $("#"+p).parent("div").parent("div").parent("div").removeClass("has-error");
				 $("#"+p).parent("div").parent("div").parent("div").removeClass("has-success");
				 $("#"+p).parent("div").parent("div").parent("div").addClass("has-error");
				 }
				 //正确的  success
				 $(":input",$("#userAddForm")).each(function(){
				 var inputName = this.name;
				 var inputSuccess = true;
				 for (var p in errorMap){
				 if(p == inputName){
				 inputSuccess = false;
				 }
				 }
				 if(inputSuccess){
				 if($(this).parent("div").parent("div").parent("div").hasClass("has-error")){
				 $(this).parent("div").parent("div").parent("div").removeClass("has-error");
				 }
				 $(this).parent("div").parent("div").parent("div").addClass("has-success");
				 }

				 });*/

				},
				//invalidHandler  表单提交时  错误验证回调 showErrors  每一次错误验证 回调
				showErrors : function(errorMap, errorList) { //每一次错误验证
				/*            for (var p in errorMap){
				 $("#"+p).parent("div").parent("div").parent("div").removeClass("has-error");
				 $("#"+p).parent("div").parent("div").parent("div").removeClass("has-success");
				 $("#"+p).parent("div").parent("div").parent("div").addClass("has-error");
				 }
				 for (var i=0; i < this.successList.length;i++){
				 var successName = this.successList[i].name;
				 $("#"+ successName).parent("div").parent("div").parent("div").removeClass("has-error");
				 $("#"+successName).parent("div").parent("div").parent("div").removeClass("has-success");
				 $("#"+successName).parent("div").parent("div").parent("div").addClass("has-success");
				 }*/

				 //缺省默认错误
				 this.defaultShowErrors();

				}
			});
});

function initSexSelect(type,selectId){
	
	$.ajax({
		type : 'POST',
		url : family_ns.contextPath + "/dictionary/adminLog/selectDictionaryByType",
		data : {'type':type},
		dataType : 'json',
		success : function(data) {
			var list = data.result;
			for(var i=0;i<list.length;i++){
				selectId.append('<option value="'+list[i].value+'">'+list[i].name+'</option>');
				
			}
		}
	});
}

function userSubmit() {
	var url = family_ns.contextPath + "/user/adminLog/userAdd";
	var data = {};
	/**
	 * 判断是否为新增
	 */
	var userId = $('#user_id').val();
	if (userId && "" != userId) {
		url = family_ns.contextPath + "/user/adminLog/userUpdate";
	}
	//增加or修改操作
	data = family_ns.serializeObject($("#userAddForm"));
	//data.description=CKEDITOR.instances.description.getData();
	//data.remarks=CKEDITOR.instances.remarks.getData();

	var message='';
    family_ns.operateConfirmByAjax(function(){
    	family_ns.submitAjax({
    		type : 'POST',
    		url : url,
    		data : data,
    		dataType : 'json',
    		success : function(result) {
                //操作成功
                if(result.success){
        			window.location.href=family_ns.contextPath +"/view/background/user"
                    family_ns.operatorAlertSuccess.call(this,family_ns.sucMessage);

                }else{
                    family_ns.operatorAlertError.call(this,family_ns.errMessage);
                }

    		},

    	});
    },message);
}

//角色select
function initList(url, select, value, text) {
	$.ajax({
		url : url,
		success : function(data) {
			var $select = $('#' + select);
			var name = $select.attr('name');
			$select.append($('<option value="-1">请选择</option>'))
			var result = data.result;
			for (var i = 0; i < result.length; i++) {
				var option = $('<option></option>');
				option.attr('value', result[i][value]);
				option.text(result[i][text]);
				if (obj[$select.attr('name')] == result[i][value]) {
					option.attr('selected', '');
				}
				$select.append(option);
			}
			$select.selected();
		}
	});
}