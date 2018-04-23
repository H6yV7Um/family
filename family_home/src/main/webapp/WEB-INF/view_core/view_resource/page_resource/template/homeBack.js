var modelId = "modelId";

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

var resultStr = "";
/**
 * 查询菜单
 */
function queryBackMenus(){
    var modelSelection = GetQueryString.call(this,modelId);
    var url = family_ns.contextPath + "/menu/menus/homeBack" ;
    var data = {};
    if(modelSelection)
        data.selectId = modelSelection;
    family_ns.queryAjax({
        type: "post",
        url: url,
        data: data,
        dataType: "json",
        success: function (data) {
            var result = data.result.homeMenuModelList;
            foreachMenu.call(this,result);
            $("#main-nav").append(resultStr);
        }
    });

}

function foreachMenu(result){

    for(var i = 0 ; i < result.length ; i++){
        resultStr += "<li class='" + result[i].isActive + "'>"

        if(result[i].href)
            resultStr += "<a href='" + family_ns.contextPath  + result[i].href +"?" + modelId + "=" +
                result[i].id + "'>";
        else
            resultStr += "<a href='#'>";
        resultStr += "<i class='icon-home'></i>";
        resultStr += "<span>" + result[i].name + "</span>";


        resultStr += "</li>";
    }
}

queryBackMenus.call(this);