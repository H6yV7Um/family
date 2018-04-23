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
    var url = family_ns.contextPath + "/menu/menus/sys" ;
    var data = {};
    if(modelSelection)
        data.selectId = modelSelection;
    family_ns.queryAjax({
        type: "post",
        url: url,
        data: data,
        dataType: "json",
        success: function (data) {
            var result = data.result.backgroundMenuModelList;
            foreachMenu.call(this,result);
            $("#mainMenu").append(resultStr);
        }
    });

}

function foreachMenu(result){
    for(var i = 0 ; i < result.length ; i++){
        resultStr += "<li class='" + result[i].isActive + " treeview'>"

        if(result[i].href)
            resultStr += "<a href='" + family_ns.contextPath  + result[i].href +"?" + modelId + "=" +
                result[i].parentId + "'>";
        else
            resultStr += "<a href='#'>";
        resultStr += "<i class='fa " + result[i].icon + "'></i>";
        resultStr += "<span>" + result[i].name + "</span>";
        /*-------------common------------*/
        resultStr += "<span class='pull-right-container'>";
        resultStr += "<i class='fa fa-angle-left pull-right'></i>";
        resultStr += "</span>";
        resultStr += "</a>";
        /*-------------common-----end-------*/
        if(result[i].backgroundMenuModelList.length > 0){
            var resultChild = result[i].backgroundMenuModelList;
            //for(var j = 0 ; j < resultChild.length ; j++){
                resultStr += "<ul class='treeview-menu'>";
                //resultStr += "<li><a href="${ctx}/view/background/dept"><i class="fa fa-circle-o"></i>部门管理</a>

                //</li>";
                foreachMenu.call(this,resultChild);
                resultStr += "</ul>";
            //}
        }
        resultStr += "</li>";
    }
}

queryBackMenus.call(this);