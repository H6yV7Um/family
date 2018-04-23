/**
 * Created by cy on 16/6/22.
 */
var accountUpdate = {};

$(function(){

    $(".datetimeClass").datetimepicker({
        format : "yyyy-mm-dd hh:ii",
        autoclose : true,
        todayBtn : true,
        language : 'zh-CN',
        pickDate: true,
        pickTime: true,
        hourStep: 1,
        minuteStep: 1,
        inputMask: true
    });

    $("#accountTitle").focus();

    //修改
    if(accountUpdate.businessId){
        initialUpdateInfo.call(this);
    }

    var validate = $("#accountForm").validate(
        {
            debug : true, //调试模式取消submit的默认提交功能
            //errorClass: "label.error", //默认为错误的样式类为：error
            focusInvalid : false, //当为false时，验证无效时，没有焦点响应
            onkeyup : false,
            submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
                saveAccount.call(this);
            },

            rules : {
                title : {
                    required : true
                }
            },
            messages : {
                title : {
                    required : "*必填"
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
function saveAccount(){
    var formData = family_ns.serializeObject($("#accountForm"));
    var url = family_ns.contextPath + "/account/accountAdd";
    if(accountUpdate.businessId)
        url = family_ns.contextPath + "/account/accountUpdate";
    //请选择
    if(formData.type == "-1")
        formData.type = "";
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type:"post",
            url:url,
            data: formData,
            success:function(result){
                if(result.success){
                    window.location.href = family_ns.contextPath + "/view/homeBack/account?mf=1";
                }
                else
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
            }
        })
    });
}

/**
 * 初始化修改信息
 **/
function initialUpdateInfo(){
    //accountUpdate
    $("#typeAccount option[value='" + accountUpdate.type +"']").attr("selected","selected");
    //初始化 textarea
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/account/querySingleAccount/" + accountUpdate.businessId,
        data: "",
        success: function (result) {
            if (result.success) {
                $("#description").text(result.result.description);
            }
        }
    });
}