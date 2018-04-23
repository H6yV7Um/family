/**
 * Created by cy on 16/6/22.
 */

var roleParam = {};

$(function(){

    $("#loginName").focus();

    $.validator.addMethod('mobile', function( value, element ){

        // /^1\d{10}$/ 来自支付宝的正则
        return this.optional( element ) || /^1\d{10}$/.test( value );

    }, '请输入正确的手机号码');

    var validate = $("#roleAddForm").validate({
        debug: true, //调试模式取消submit的默认提交功能
        //errorClass: "label.error", //默认为错误的样式类为：error
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应
        onkeyup: false,
        submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form
           // return operateConfirm('roleAddForm');
        	roleSubmit.call(this)
        },

        rules:{
/*        	name:{
                required:true,
                remote: {
                    url: family_ns.contextPath + "/role/queryRoleValidate" ,     //后台处理程序
                    type: "post",               //数据发送方式
                    dataType: "json",           //接受数据格式
                    data:{                     //要传递的数据
                        loginName: function() {
                            return $("#name").val();
                        },
                        page:1,
                        rows:10
                    }     //远程地址只能输出 "true" 或 "false"，不能有其他输出。
                }
            },*/
            email:{
                required:true,
                email:true
            },
            password:{
                required:true,
                rangelength:[6,10]
            },
            confirm_password:{
                equalTo:"#password"
            },
            mobile:{
                mobile:true
            }
        },
        messages:{
            loginName:{
                required:"必填",
                remote:"当前帐号已存在,不能使用,请更换"
            },
            name:{
                required:"必填"
            },
            email:{
                required:"必填",
                email:"E-Mail格式不正确"
            },
            password:{
                required: "不能为空",
                rangelength: $.validator.format("密码最小长度:{0}, 最大长度:{1}。")
            },
            confirm_password:{
                equalTo:"两次密码输入不一致"
            },
            mobile:{
                mobile:"请输入正确的手机号码"
            }
        },
        errorPlacement: function(error, element) {
            error.appendTo( element.parent("div").parent("div").next("div").find("p") );
        },
        invalidHandler: function(errorMap,errorList){       // 如果表单验证不通过，将会触发这个函数
            /*for (var p in errorMap){
                $("#"+p).parent("div").parent("div").parent("div").removeClass("has-error");
                $("#"+p).parent("div").parent("div").parent("div").removeClass("has-success");
                $("#"+p).parent("div").parent("div").parent("div").addClass("has-error");
            }
            //正确的  success
            $(":input",$("#roleAddForm")).each(function(){
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
        showErrors:function(errorMap,errorList) {   //每一次错误验证
           /* for (var p in errorMap){
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
/**
 * 提交表单
 */
function roleSubmit(){
	var url = family_ns.contextPath + "/role/adminLog/roleAdd";
	var data = {};
	data = family_ns.serializeObject($("#roleAddForm"));
	var roleId = $('#role_id').val();
	if (roleId && "" != roleId) {
		url = family_ns.contextPath + "/role/adminLog/roleUpdate";
	}
	//增加or修改操作
	data = family_ns.serializeObject($("#roleAddForm"));

    family_ns.operateConfirmByAjax(function () {

        family_ns.submitAjax({
            type : 'POST',
            url : url,
            data : data,
            dataType : 'json',
            success : function(result) {
                if (result.success) {
                    family_ns.successJumpSelf.call(this,roleParam.urlSelf,roleParam.modelId);
                }
                else
                    family_ns.operatorAlertError();


            }
        });
    });
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