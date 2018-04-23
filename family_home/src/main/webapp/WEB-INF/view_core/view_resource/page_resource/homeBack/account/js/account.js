/**
 * account js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "seq_date desc";

var accountInfo; //sheet layer modal

var accountParam = {};
var vagueQuery;

//是否可以编辑
var isEditAble = true;
var editAbleBusinessId = "";

var serverSettingTbodyName = "serverSettingTbody";

$(function(){

    initTableGrid.call(this,1);
    //统计类型
    initStatisticsCount.call(this);
    //绑定 server type
    initStatisticsTypeBtn.call(this);
});
/**
 * server type 绑定
 */
function initStatisticsTypeBtn() {
    $("#serverStaticBtns button").on("click",function (event) {
        var button = $(this)[0];
        //gold
        if(!$("#"+button.id).hasClass("selectedTypeBtn")){
            $("#serverStaticBtns button").removeClass("selectedTypeBtn");
            $("#"+button.id).addClass("selectedTypeBtn");
            accountParam.type = $("#"+button.id).attr("vType");
            searchAccount.call(this);
        }else{
            $("#"+button.id).removeClass("selectedTypeBtn");
            accountParam.type = "";
            searchAccount.call(this);
        }

    });
}

/**
 * 初始化table
 */

function initTableGrid(page){
    var data={};
    family_ns.page.page = page;
    data= family_ns.page ;
    data.flag = 1;
    data.delFlag = 0;

    data.type = accountParam.type;
    if(vagueQuery){
        data.title = $("#accountName").val();
    }
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/account/queryAccounts" ,

        data: data ,

        dataType: 'json',

        success: function(result){
            $("#accountTableTbody").html("");
            var rows = result.rows;
            if(!rows || 0 == rows.length)
                return;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                resStr += "<tr class='unread checked'>";
                    resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'>"
                            + "<input type='radio' name='accountCheck' class='checkbox'/></td>";

                    resStr += "<td>"+stringToBlank.call(this,rows[i].title)+"</td>";
                    resStr += "<td>"+stringToBlank.call(this,rows[i].account)+"</td>";
                    resStr += "<td>"+stringToBlank.call(this,rows[i].passwd)+"</td>";
                    resStr += "<td>"+stringToBlank.call(this,rows[i].url)+"</td>";
                    resStr += "<td>"+family_ns.formatterStrByLength(stringToBlank.call(this,rows[i].description),20,true)+"</td>";
                    resStr += "<td>";
                    //服务器
                    if(2 == rows[i].type ){
                        resStr += "<button class='btn btn-primary' onclick='serverSetting(\"" + rows[i].businessId + "\")' type='button'>配置服务器</button>";
                    }
                    else{
                        resStr += "";
                    }
                    resStr += "</td>";

                resStr += "</tr>";
            }
            $("#accountTableTbody").append(resStr);
            if(1 === result.pageNu){
                initPagination.call(this,'pagination-account',result.pages,result.pageNu,"initTableGrid");
            }
        },error:function (result) {
            console.info(result);
        }
    });
}

/**
 * add account
 */
function addAccount(){
    window.location.href = family_ns.contextPath + "/view/homeBack/accountAdd";
}
/**
 * update account
 */
function updateAccount(){
    if(!isSelectedRow())
        return;
    var businessId = getSelectedRowBusinessId.call(this);
    window.location.href=family_ns.contextPath+"/account/toHg/toAccountUpdatePage/" + businessId;
}
/***
 * delete account
 */
function deleteAccount(){
    if(!isSelectedRow())
        return;
    var businessId = getSelectedRowBusinessId.call(this);
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/account/accountDelete/" + businessId,
            data: "",
            success: function (result) {
                if (result.success) {
                    window.location.href = family_ns.contextPath + "/view/homeBack/account?mf=1";
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });

}
/**
 * search account
 */
function searchAccount(){
    vagueQuery = true;
    initTableGrid(1);
}

/**
 * 判断是否选中行
 */
function isSelectedRow(){
    var account = $("#accountTableTbody td input[name='accountCheck']:checked");
    if(0 == account.length){
        var swalAlert = {"title":"温馨提示","text":"请选中一条数据,进行操作!","timer":2000};
        family_ns.operatorAlertError.call(this,swalAlert);
        return false;
    }
    return true;
}
/**
 * get select Id
 * @returns {boolean}
 */
function getSelectedRowBusinessId(){
    var account = $("#accountTableTbody td input[name='accountCheck']:checked");
    return account.parent().attr("id");
}
/**
 * 统计
 */
function initStatisticsCount(){
    var param = {};
    param.type = "type";
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/account/statisticsCount",
        data: param,
        success: function (result) {
            if (result.success && result.result.length > 0) {
                for(var k = 0; k < result.result.length ; k ++){
                    $("#btnType" + result.result[k].type + " span").html(result.result[k].count);
                    $("#btnType" + result.result[k].type + " span").show();
                }

            }
        }
    });
}
/**
 * server setting
 */
function serverSetting(businessId){
    var param = {};
    param.serverId = businessId;
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/account/queryAccountServerSettings",
        data: param,
        success: function (result) {
            if (result.success) {
                accountInfo = layer.open({
                    type: 1,
                    title: '服务器信息',
                    fix: false,
                    //maxmin: true,
                    shadeClose: false,
                    zIndex:1111,
                    area: [640, '68%'],
                    content: $("#accountServerInfo"),
                    success: function(layero, index){
                        initServerSetting.call(this,result.result,businessId);
                    },
                    end: function(){
                        clearServerSetting.call(this);
                    }
                });

            }
        }
    });

}
/**
 * add server
 */
function addServer(){
    var server = getServerSettingInfo.call(this,"");
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/account/accountServerSettingAdd" ,
            data: server,
            success: function (result) {
                if (result.success) {
                    family_ns.operatorAlertSuccess(family_ns.getSucMessage.call(this,result.message));
                    var row = conversionServerSettingRow.call(this,result.result);
                    $("#"+serverSettingTbodyName).append(row);
                    clearServerSettingInput.call(this);
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });
}
/**
 * minus server
 */
function minusServer(businessId){
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/account/accountServerSettingDelete/" + businessId,
            data: "",
            success: function (result) {
                if (result.success) {
                    family_ns.operatorAlertSuccess(family_ns.getSucMessage.call(this,result.message));
                    $("#"+serverSettingTbodyName+" tr[id='"+businessId+"']").remove();
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });
}
/**
 *  clear tbody Settings
 */
function clearServerSetting(){
    $("#resetAccountServer").click();
    $("#accountServerBusinessId").val("");
    $("#"+serverSettingTbodyName).html("");
}

/**
 * init server Settings
 */
function initServerSetting(result,businessId){
    $("#accountServerBusinessId").val(businessId);
    if(!result || result.length == 0)
        return ;
    for(var k = 0; k < result.length ; k ++){
        var row = conversionServerSettingRow.call(this,result[k]);
        $("#"+serverSettingTbodyName).append(row);
    }
}
/**
 * 转换
 * @param param
 */
function conversionServerSettingRow(param){
    var row = "";
    row += "<tr id='"+ param.businessId +"'>";
    row += "<td style='width: 222px;'>"+ param.title;
    row += "</td>";
    row += "<td style='width: 101px;'>"+ param.account;
    row += "</td>";
    row += "<td style='width: 101px;'>"+ param.passwd;
    row += "</td>";
    row += "<td style='width: 101px;'>"+ param.port;
    row += "</td>";
    row += "<td style='width: 222px;'>"+ param.description;
    row += "</td>";
    row += "<td style='width: 95px;'>" ;
    row += "<button type='button' class='btn btn-default' aria-label='Left Align' style='width: 33px;'  onclick='minusServer(\""+
        param.businessId +"\")'>";
    row += "<span class='icon-minus' style='margin-left: 3%' ></span>";
    row += "</button>&nbsp;&nbsp;";
    row += "<button type='button' class='btn btn-default' aria-label='Left Align' style='width: 33px;' onclick='editServer(\""+
        param.businessId +"\")'>";
    row += "<span class='icon-edit' style='margin-left: 3%' ></span>";
    row += "</button>";
    row += "</td>";

    row += "</tr>"
    return row;
}
/**
 * 获取serverSettingInfo
 * @returns serverSettingInfo
 */
function getServerSettingInfo(temp){
    var server = {};
    server.description = $("#"+temp+"serverDescription").val();
    server.title = $("#"+temp+"serverTitle").val();
    server.account = $("#"+temp+"serverAccount").val();
    server.passwd = $("#"+temp+"serverPasswd").val();
    server.port =   $("#"+temp+"serverPort").val();
    server.serverId = $("#accountServerBusinessId").val();
    return server;
}
/**
 * 编辑server
 * @param businessId
 */
function editServer(businessId){
    //编辑
    if(isEditAble){
        isEditAble = false;
        editAbleBusinessId = businessId;
        //var title = $("#"+serverSettingTbodyName+" tr[id='"+businessId+"'] td:first").html();
        //$("#"+serverSettingTbodyName+" tr[id='"+businessId+"'] td:first").html("<input value='"+title+"'/>");
        var tdIndex = 0;
        $("#"+serverSettingTbodyName+" tr[id='"+businessId+"'] td").each(function(){
            var title = $(this).html();
            var width = $("#inputAccountServer input:eq("+tdIndex+")").css("width");
            var inputId = $("#inputAccountServer input:eq("+tdIndex+")").attr("id");
            $(this).html("<input value='"+title+"' style='width: "+width+"' id='temporary"+inputId+"'/>");
            tdIndex ++;
            if(tdIndex == 5)
                return false;
        });
    }
    //结束编辑
    else{
        if(businessId != editAbleBusinessId){
            family_ns.operatorAlertError(family_ns.getErrMessage("只能同时编辑一个配置"));
            return;
        }
        var server = getServerSettingInfo.call(this,"temporary");
        server.businessId = businessId;
        family_ns.operateConfirmByAjax(function(){
            family_ns.submitAjax({
                type: "post",
                url: family_ns.contextPath + "/account/accountServerSettingUpdate",
                data: server,
                success: function (result) {
                    if (result.success) {
                        family_ns.operatorAlertSuccess(family_ns.getSucMessage.call(this,result.message));
                        var tdIndex = 0;
                        $("#"+serverSettingTbodyName+" tr[id='"+businessId+"'] td input").each(function(){
                            var title = $(this).val();
                            $(this).parent().html(title);
                            tdIndex ++;
                            if(tdIndex == 5)
                                return false;
                        });
                        isEditAble = true;
                    }
                    else {
                        family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                    }
                }
            });
        });
    }

}