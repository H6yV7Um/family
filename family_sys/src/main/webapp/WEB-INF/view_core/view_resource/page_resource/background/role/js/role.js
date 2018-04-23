/**
 * user js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "code desc";
//获取要编辑的id  更改权限的id
var roleId = "";
var deptRolePage = 1;
var roleParam = {};

$(function(){

    initTableGrid.call(this,1);

});

function searchRole(){
	initTableGrid.call(this,1);
}


/**
 * 初始化table
 */

function initTableGrid(page){
	var data={};
	family_ns.page.page = page;
	data= family_ns.page ;
	data.name=$("#roleName").val();
	data.flag=$('input[name="roleType"]:checked').val();
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/role/queryRoles" ,

        data: data ,

        dataType: 'json',

        success: function(result){
            var rows = result.rows;
            if(!rows || 0 == rows.length)
                return;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                var flag = "启用";
                resStr += "<tr>";
                resStr += "<td scope='row' id='"+rows[i].businessId+"'>"
                        +"<input type='radio' name='roleCheck'/>&nbsp;&nbsp;"+(i+1)
                    "</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].name)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].code)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].username)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].createDate)+"</td>"
                if("0" == rows[i].flag)
                    flag = "停用";
                resStr += "<td><a href='#' onclick=''>"+flag+"</a></td>";
                resStr += "</tr>";
            }
            $("#roleTableTbody").html("");
            $("#roleTableTbody").append(resStr);
            //if(1 === result.pageNu){
                //initPagination.call(this,result.pages,result.pageNu);
            //}
            initPagination.call(this,'pagination-role',result.pages,result.pageNu,"initTableGrid");
        }
    });
}
/**
 * 初始化 menu tree table
 */

function initTreeMenuGrid(){
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/menu/queryMenusOfRolePermission/"+roleId,

        data: family_ns.page,

        dataType: 'json',

        success: function (result) {
            $('#treeviewMenu').treeview({
                enableLinks:false,
                levels:3,
                showTags:true,
                color: "#428bca",
                showCheckbox:true,
                data: result,
                onNodeChecked: function(event, data) {
                    var parentId = data.id;
                    while(parentId){
                        data = $('#treeviewMenu').treeview('getParent', data);
                        parentId = data.id;
                        if(parentId && data.state && !(data.state.checked))
                            $('#treeviewMenu').treeview('checkNode',data);
                    }
                },
                //取消选中   data is node
                onNodeUnchecked:function (event,data) {
                    var dataNode = data.nodes;
                    for(var i = 0 ; i < dataNode.length ; i ++){
                        if(dataNode[i].state.checked){
                            data = null;
                            break;
                        }
                    }

                }
            });
            $('#roleModal').modal('show');
        }
    });

}
/**
 * check parentMenu
 */
function checkedParentMenu(id){


}
/*
 * 部门树
 */
function initTreeDeptGrid(){
    family_ns.submitAjax({
        type: 'POST',

        url: family_ns.contextPath + "/dept/queryDeptsTreeByRoleId/"+roleId,

        data: family_ns.page,

        dataType: 'json',

        success: function (result) {
            $('#treeviewDept').treeview({
                enableLinks:false,
                levels:3,
                showTags:true,
                color: "#428bca",
                showCheckbox:true,
                data: result
            });
            $('#deptModal').modal('show');
        }
    });

}
/**
 * add user
 */
function addRole(){

    window.location.href = family_ns.contextPath + "/view/background/roleAdd?modelId=" + roleParam.modelId;
}
/***
 * delete user
 */
function deleteRole(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'roleTableTbody','roleCheck'))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,'roleTableTbody','roleCheck');

    var message = {};

    message.text = "您确认您的操作?(删除角色,拥有该角色的人员将失去对应的权限)";

    family_ns.operateConfirmByAjax(function () {

        family_ns.submitAjax({
            type: 'POST',

            url: family_ns.contextPath + "/role/adminLog/roleDelete/" + businessId,

            dataType: 'json',

            success: function(result){
                if (result.success) {
                    family_ns.successJumpSelf.call(this,roleParam.urlSelf,roleParam.modelId);
                }
                else
                    family_ns.operatorAlertError();
            }
        });
    }, message);



}
/**
 * 修改 角色
 */
function updateRole(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'roleTableTbody','roleCheck'))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,'roleTableTbody','roleCheck');
    window.location.href = family_ns.contextPath + "/role/toBg/toRoleUpdatePage/"+ businessId;

}
/**
 * 分配权限
 */
function permissionRole(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'roleTableTbody','roleCheck'))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,'roleTableTbody','roleCheck');
    roleId = businessId;
    initTreeMenuGrid.call(this);

}
/**
 * 绑定部门
 */
function permissionDept(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'roleTableTbody','roleCheck'))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,'roleTableTbody','roleCheck');
    roleId = businessId;
    initTreeDeptGrid.call(this);
    $("#roleId").val(roleId);


}

/**
 * 提交角色部门绑定
 */
function submitRoleDept(){
    var role_depts = $('#treeviewDept').treeview('getChecked');
    var role_depts_Id = "";
    var urlSubmit = family_ns.contextPath + "/role/adminLog/deptPermission";
    for(var i = 0 ; i < role_depts.length; i++)
    	role_depts_Id +=  role_depts[i].id + ","
    	
    //一个角色指定多个部门，存储到dept_role表中	
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: urlSubmit,
            data: {
            	role_depts : role_depts_Id,
                roleId : roleId
            },
            dataType: "json",
            success: function (data) {
                //操作成功
                if(data.success){
                    $('#deptModal').modal('hide');
                    family_ns.operatorAlertSuccess.call(this,family_ns.sucMessage);

                }else{
                    family_ns.operatorAlertError.call(this,family_ns.errMessage);
                }
            }
        });
    });
}


/**
 * 提交角色权限
 */
function submitRoleMenu(){
    //获取checked menu
    var role_menus = $('#treeviewMenu').treeview('getChecked');
    var role_menus_Id = "";
    var urlSubmit = family_ns.contextPath + "/role/adminLog/rolePermission";
    for(var i = 0 ; i < role_menus.length; i++)
        role_menus_Id +=  role_menus[i].id + ","

    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: urlSubmit,
            data: {
                role_menus : role_menus_Id,
                roleId : roleId
            },
            dataType: "json",
            success: function (data) {
                //操作成功
                if(data.success){
                    $('#roleModal').modal('hide');
                    family_ns.operatorAlertSuccess.call(this,family_ns.sucMessage);

                }else{
                    family_ns.operatorAlertError.call(this,family_ns.errMessage);
                }
            }
        });
    });
}
