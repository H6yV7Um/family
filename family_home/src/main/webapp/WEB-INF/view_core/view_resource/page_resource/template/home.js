/**
 * login
 */
function loginUser() {
    var username = $('#home_username').val();
    var password = $('#home_password').val();
    var validate = $('#validateCode').val();
    //shiro ajax login
    var url = family_ns.contextPath + "/adminLogin" ;
    var data = {};
    data.username = username;
    data.password = password;
    data.validateCode = validate;
    family_ns.queryAjax({
        type: "post",
        url: url,
        data: data,
        dataType: "json",
        success: function (data) {
            if(data.success){
                window.location.href = family_ns.contextPath + "/view/home/index";
            }else{
                var message = family_ns.errMessage;
                message.text = data.message;
                //data.result > 1  后台是先放  再++  所以此处 > 1  传递过来是2,后台已经是1
                if(data.result && data.result > 2 ){
                    $("#validateCodeId").removeClass("hide");
                }
                family_ns.operatorAlertError.call(this,message);
            }
        }
    });
}


/**
 * 注销
 */
function signOut() {
    var message = {};

    message.text = "您确认注销退出?";

    family_ns.operateConfirmByAjax(function () {

        window.location.href = family_ns.contextPath+ "/logout";
    }, message);
}

/**
 * active navigation menu
**/
function activeNavigation(){
    var url = window.location.href;
    var model = url.substr(url.lastIndexOf('/')+1);
    $("#navbarHome li").removeClass("active");
    //login
    if(model.indexOf("adminLogin") >= 0 ){
        $("#navbarHome li[homekey='index']").addClass("active");
    }else{
        $("#navbarHome li[homekey='"+model+"']").addClass("active");
    }

};
