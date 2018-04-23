/**
 * Created by cy on 16/6/22.
 */
var accountParam = {};

$(function(){

    /**
     * focus
     */
    $("#accountLastPass").focus();


    var validate = $("#accountForm").validate(
        {
            debug : false, //调试模式取消submit的默认提交功能
            //errorClass: "label.error", //默认为错误的样式类为：error
            focusInvalid : false, //当为false时，验证无效时，没有焦点响应
            onkeyup : false,
            submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
                saveAccountPass.call(this);
            },

            rules : {
                accountLastPass : {
                    required : true,
                    remote: {
                        url: family_ns.contextPath + "/user/queryLastPassWdValidate",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            lastPass: function () {
                                return $("#accountLastPass").val();
                            }
                            //远程地址只能输出 "true" 或 "false"，不能有其他输出。
                        }
                    }
                },
                accountPass : {
                    required : true,
                    rangelength : [ 6, 10 ]
                },
                accountRepeatPass : {
                    required : true,
                    equalTo : "#accountPass"
                }
            },
            messages : {
                accountLastPass : {
                    required : "*必填",
                    remote:"*原始密码错误"
                },
                accountPass : {
                    required : "*必填",
                    rangelength : $.validator
                        .format("密码最小长度:{0}, 最大长度:{1}。")
                },
                accountRepeatPass : {
                    required : "*必填",
                    equalTo : "两次密码输入不一致"
                }
            },
            errorPlacement : function(error, element) {
                error.appendTo(element.next(
                    "span"));
            },
            invalidHandler : function(errorMap, errorList) { // 如果表单验证不通过，将会触发这个函数

            },
            //invalidHandler  表单提交时  错误验证回调 showErrors  每一次错误验证 回调
            showErrors : function(errorMap, errorList) { //每一次错误验证
                //缺省默认错误
                this.defaultShowErrors();

            }
        });
});

/**
 * 保存帐号
 */
function saveAccountPass(){
    var formData = {};
    formData.password = $("#accountPass").val();
    var url = family_ns.contextPath + "/user/updateUserPass";
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type:"post",
            url:url,
            data: formData,
            success:function(result){
                if (result.success)
                    family_ns.successJumpSelf.call(this,accountParam.urlSelf,accountParam.modelId);
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        })
    });
}

