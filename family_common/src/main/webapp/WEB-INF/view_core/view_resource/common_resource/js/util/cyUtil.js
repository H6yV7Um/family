//  命名空间
var family_ns = {};
/*生产*/
//family_ns.webResourceServer = "www.quanjianfamily.com/resourceServer/family/upload";
/*测试*/
family_ns.webResourceServer = "/resourceServer/family/upload";
// context_path
family_ns.contextPath = "";
// 分页
family_ns.page = {};
family_ns.page.page = 1;
family_ns.page.rows = 10;
family_ns.confirm = false;
//报销类型
family_ns.dictiontryType = {};
family_ns.dictiontryType.expenseType = "charge";
//成功信息
family_ns.sucMessage = {};
family_ns.sucMessage.title = "温馨提示";
family_ns.sucMessage.text = "操作成功!";
family_ns.sucMessage.timer = 2000;
family_ns.sucMessage.showConfirmButton = true;
//取消信息
family_ns.canMessage = {};
family_ns.canMessage.title = "温馨提示";
family_ns.canMessage.text = "操作取消!";
family_ns.canMessage.timer = 2000;
//失败信息
family_ns.errMessage = {};
family_ns.errMessage.title = "温馨提示";
family_ns.errMessage.text = "操作失败!";
family_ns.errMessage.timer = 2000;
/**
 * grid tr class
 * @param cloNo
 * @returns {*}
 */
function trClassFormater(cloNo){
    if(0 == cloNo){
        return "active";
    }else if(2 == cloNo){
        return "success";
    }else if(4 == cloNo){
        return "info";
    }else if(6 == cloNo){
        return "warning";
    }else if(8 == cloNo){
        return "danger";
    }else{
        return "";
    }

}
/**
 * 为false的字符串  显示为 ""
 */
function stringToBlank(str){
    if(!str){
        return "";
    }
    else
        return str;
}
/**
 * @author CY
 *
 * @requires jQuery
 *
 * 将form表单元素的值序列化成对象
 *
 * @returns object
 */
family_ns.serializeObject = function(form) {
    var o = {};
    $.each(form.serializeArray(), function(index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};
/**
 * from 反序列化
 * @param form
 */
family_ns.serializeForm = function(obj) {
    for(var p in obj){
        // 方法
        if(typeof(obj[p])=="function"){
            // obj[p]();
        }else{
            $("[name='"+p+"']").val(obj[p]);
            // p 为属性名称，obj[p]为对应属性的值

        }
    }
};

/***
 * 确认操作  表单提交
 */
function operateConfirm(formId){
    if(family_ns.confirm){
        family_ns.confirm = false ;
        return true;
    }
    swal({
        title: "您确定要提交吗？",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnConfirm: false
    }, function () {
        family_ns.confirm = true;
        $("#"+formId).submit();
    });
    return false;

}
/**
 * 获取error message
 * @param message
 */
family_ns.getErrMessage = function(mess){
    var message = family_ns.errMessage;
    message.text = mess;
    return message;
};
/**
 * 获取error message
 * @param message
 */
family_ns.getSucMessage = function(mess){
    var message = family_ns.sucMessage;
    message.text = mess;
    return message;
};
/**
 * 根据length截取
 * @param length
 */
family_ns.formatterStrByLength = function(str,length,tip){
    if(!str || str == "" || str.trim() == "")
        return "";
    if(str.length < length)
        return str;
    if(tip)
        return "<span title='" + str+ "' style='cursor: pointer'>" + str.substr(0,length)+"..."+"</span>";
    else
        return str.substr(0,length)+"...";
}

/***
 * 确认操作  ajax提交
 */
family_ns.operateConfirmByAjax = function(formFun,message,cancelFun){
    swal({
        title: "温馨提示",
        text: (message && (undefined != message.text))?message.text:"您确认您的操作?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        closeOnConfirm: false,
        closeOnCancel: false,
        showLoaderOnConfirm: true
    }, function (isConfirm) {
        if (isConfirm) {
            formFun.call(this);
        }else{
            family_ns.operatorAlertError.call(this,family_ns.canMessage)
            if(cancelFun)
                cancelFun.call(this);
        }
    });
    return false;

};
/**
 * 转义html
 * @param str
 * @returns {string}
 */
family_ns.html_encode = function(str)
{
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/&/g, "&gt;");
    s = s.replace(/</g, "&lt;");
    s = s.replace(/>/g, "&gt;");
    s = s.replace(/ /g, "&nbsp;");
    s = s.replace(/\'/g, "&#39;");
    s = s.replace(/\"/g, "&quot;");
    s = s.replace(/\n/g, "<br>");
    return s;
};
/**
 * 反转义html
 * @param str
 * @returns {string}
 */
family_ns.html_decode = function(str)
{
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/&gt;/g, "&");
    s = s.replace(/&lt;/g, "<");
    s = s.replace(/&gt;/g, ">");
    s = s.replace(/&nbsp;/g, " ");
    s = s.replace(/&#39;/g, "\'");
    s = s.replace(/&quot;/g, "\"");
    s = s.replace(/<br>/g, "\n");
    return s;
}

/*
* 是否选中  提示操作*/

function  isTreeViewSelected(treeViewId){
    var selectedTreeView = $('#'+treeViewId).treeview('getSelected');
    //没有选中根节点
    if(!selectedTreeView || 0 == selectedTreeView.length){
        var swalAlert = {"title":"温馨提示","text":"请选中一个节点,进行操作!"};
        family_ns.operatorAlertError.call(this,swalAlert);
        return false;
    }
    return true;
}
/**
 * 判断 table 是否选中行
 */
family_ns.isCommonTableSelectedRow = function(tableId,tdName){
    var account = $("#" + tableId +" td input[name='"+tdName+"']:checked");
    if(0 == account.length){
        var swalAlert = {"title":"温馨提示","text":"请选中一条数据,进行操作!","timer":2000};
        family_ns.operatorAlertError.call(this,swalAlert);
        return false;
    }
    return true;
};
/**
 * get common table Row Id
 * @returns {*}
 */
family_ns.getCommonTableSelectedRowBusinessId = function (tableId,tdName){
    var account = $("#" + tableId +" td input[name='"+tdName+"']:checked");
    // 未选中
    if(!account && 0 == account.length)
        return "";
    // radio
    if(1 == account.length)
        return account.parent().attr("id");
    //check
    var accountList = "";
    account.each(function(){
        accountList += $(this).parent().attr("id") + ",";
    });
    return accountList;
}


/**
 * 操作成功
 */
family_ns.operatorAlertSuccess = function(message){
    var swalObj = {
        title: (message.title)?message.title:family_ns.sucMessage.title,
        text:  (message.text)?message.text:family_ns.sucMessage.text,
        timer: message.timer,
        type: (undefined != message.type)?message.type:"success",
        showConfirmButton: (undefined !=message.showConfirmButton)?
            message.showConfirmButton:true
    };
    swal(swalObj);
};

/**
 * 操作失败
 */
family_ns.operatorAlertError = function(message){
    var swalObj = {
        title: (message && message.title)?message.title:family_ns.errMessage.title,
        text:  (message && message.text)?message.text:family_ns.errMessage.text,
        timer: (message && message.timer)? message.timer : message,
        type: (message && message.type)?message.type:"error",
        showConfirmButton: (message && message.showConfirmButton)?
            message.showConfirmButton:true
    };
    swal(swalObj);
};
/**
 * ajax 无权限
 */
family_ns.noAuthorizcationByAjax = function(result){
    if(!result.success && "noauthorication" == result.result){
        var swalObj = {
            title: "温馨提示",
            text: "对不起,您没有对应的权限!",
            timer: 2000,
            showConfirmButton: false
        };
        family_ns.operatorAlertError.call(this,swalObj);
        return true;
    }
    else if(!result.success && "noauthentication" == result.result){
        var swalObj = {
            title: "温馨提示",
            text: "对不起,您需要进行认证!",
            timer: 2000,
            showConfirmButton: false
        };
        family_ns.operatorAlertError.call(this,swalObj);
        return true;
    }
    return false;
};
/**
 * 展示html message
 * @param message
 */
family_ns.showHtmlMessage = function(message){
    var swalObj = {
        title: message.title,
        text:  message.text,
        html: true
    };
    swal(swalObj);
};

/**
 * 锁住confirm button
 */
family_ns.lockConfirmButton = function () {
    $(".confirm").attr('disabled','true');
};

/**
 * 解锁confirm button
 */
family_ns.unlockConfirmButton = function () {
    $(".confirm").removeAttr('disabled');
};

/**
 * 锁住 screen
 */
family_ns.lockConfirmScreen = function () {
    $("#loadingData").css('display','flex');
};

/**
 * 解锁 screen
 */
family_ns.unlockConfirmScreen = function () {
    $("#loadingData").css('display','none');
};

/**
 * 提交ajax
 */
family_ns.submitAjax = function (param) {
    //加锁
    family_ns.lockConfirmButton.call(this);
    $.ajax({
        type: (param.type) ? param.type:'post',
        url: param.url,
        data: param.data,
        dataType: (param.dataType) ? param.dataType:'json',
        success: function (data) {
            if(family_ns.noAuthorizcationByAjax.call(this,data)){
                return;
            }
            //解锁
            family_ns.unlockConfirmButton.call(this);
            if(param.success)
                param.success.call(this,data);
        },
        error:function(data){
            //解锁
            family_ns.unlockConfirmButton.call(this);
            if(param.error)
            	param.error.call(this,data);
            else{
                data.timer = 2000;
                family_ns.operatorAlertError(data);
            }

        }
    });
};
/**
 * query ajax
 * @param param
 */
family_ns.queryAjax = function (param) {
    //加锁
    family_ns.lockConfirmScreen.call(this);
    $.ajax({
        type: (param.type) ? param.type:'post',
        url: param.url,
        data: param.data,
        dataType: (param.dataType) ? param.dataType:'json',
        success: function (data) {
            //解锁
            family_ns.unlockConfirmScreen.call(this);
            if(family_ns.noAuthorizcationByAjax.call(this,data)){
                return;
            }
            param.success.call(this,data);
        },
        error:function(data){
            //解锁
            family_ns.unlockConfirmScreen.call(this);
            if(param.error)
                param.error.call(this,data);
            else{
                data.timer = 2000;
                family_ns.operatorAlertError(data);
            }
        }
    });
};

/**
 * 跳转本页面  操作成功
 */
family_ns.successJumpSelf = function(url,modelId) {
    window.location.href = family_ns.contextPath + url + "?mf=1&modelId="
        + modelId;
}
/**
 * clear 表格数据
 * @param table
 * @param data
 */
family_ns.clearTableData = function (table,data) {
    $("#" + table).html("");
    if(!data || 0 == data.length){
        return false;
    }else
        return true;

};


/**
 * 根据浏览器参数  获取值
 * @param     parameterName
 * @returns   value
 * @constructor
 */
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}