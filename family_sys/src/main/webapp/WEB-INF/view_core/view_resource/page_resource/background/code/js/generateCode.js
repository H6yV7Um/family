
/**
 * user js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "no desc";
//获取要编辑的id  更改的id
var userId = "";
//分配岗位多page
var rolePage = 1;
$(function(){

    initTableGrid.call(this,1);
});

function search() {
	initTableGrid.call(this,1);
}

/**
 * 初始化table
 */

function initTableGrid(page){
	var data={};
    family_ns.page.page = page;
    data= family_ns.page ;
	data.loginName=$("#loginName").val();
    family_ns.queryAjax({
        type: 'POST', 

        url: family_ns.contextPath + "/user/queryUsers" ,

        data: data ,

        dataType: 'json',

        success: function(result){
            var rows = result.rows;
            $("#userTableTbody").html("");
            if(!rows)
                return;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                resStr += "<tr>";
                resStr += "<td scope='row' id='"+rows[i].businessId+"'>"
                        +"<input type='radio' name='userCheck'/>&nbsp;&nbsp;"+(i+1)
                    "</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].loginName) +"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].name)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].no)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].email)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].mobile)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].loginDate)+"</td>"
                resStr += "</tr>";
            }

            $("#userTableTbody").append(resStr);

            initPagination.call(this,'pagination-user',result.pages,result.pageNu,"initTableGrid");
        }
    });
}

/**
 * generate code
 *
 */
function startGenerateCode(){

    window.location.href = family_ns.contextPath + "/view/background/generateCodeAdd";
}
/***
 * delete user
 */
function deleteUser(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'userTableTbody','userCheck'))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,'userTableTbody','userCheck');
    family_ns.operateConfirmByAjax(function () {
        family_ns.submitAjax({
            type: 'POST',

            url: family_ns.contextPath + "/user/adminLog/userDel?id=" + businessId,

            dataType: 'json',

            success: function(result){
                if (result.success) {
                    window.location.href = family_ns.contextPath + "/view/backGround/user?mf=1";
                }
                else
                    family_ns.operatorAlertError();
            }
        });
    });
	

}
/**
 * 分配角色
 */
function distributeRole(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'userTableTbody','userCheck'))
        return;
    var  businessId= family_ns.getCommonTableSelectedRowBusinessId.call(this,'userTableTbody','userCheck');
    userId = businessId;
    initRoleGrid.call(this,1);
    $('#roleModal').modal('show');


}
/**
 * update user
 */
function updateUser(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'userTableTbody','userCheck'))
        return;
    var  businessId= family_ns.getCommonTableSelectedRowBusinessId.call(this,'userTableTbody','userCheck');

    window.location.href=family_ns.contextPath+"/user/toBg/toUserUpdatePage/" + businessId;

}

/**
 * 初始化dept_role grid 根据 userId   选中已选中的角色
 */
function initRoleGrid(page){
    if(page)
        rolePage = page;
    else
        rolePage = 1;
    family_ns.submitAjax({
        type: 'POST',

        url: family_ns.contextPath + "/role/queryRolesByUser/" + userId,

        data: {page:rolePage,rows:10} ,

        dataType: 'json',

        success: function(result){
            var rows = result.rows;
            $("#roleTableTbody").html("");
            if(!rows || 0 == rows.length)
                return;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                var classStr = trClassFormater.call(this,i);
                var flag = "启用";
                resStr += "<tr class='"+classStr+"'>";
                resStr += "<td scope='row' id='"+rows[i].id+"'>";
                //是否已选中
                if("1" == rows[i].checked)
                    resStr += "<input type='checkbox' value='" + rows[i].id + "' checked='checked' name='role'/>";
                else
                    resStr += "<input type='checkbox' value='" + rows[i].id + "' name='role'/>";
                resStr += "&nbsp;&nbsp;"+(i+1) + "</td>";
                //resStr += "<td>"+stringToBlank.call(this,rows[i].dName)+stringToBlank.call(this,rows[i].rCode)+"</td>";
                resStr += "<td>"+rows[i].dname+rows[i].rname+"</td>";
               /* resStr += "<td>"+stringToBlank.call(this,rows[i].dCode)+"</td>";*/
                /*resStr += "<td>"+stringToBlank.call(this,rows[i].rName)+stringToBlank.call(this,rows[i].rCode)+"</td>";*/
               /* resStr += "<td>"+stringToBlank.call(this,rows[i].rCode)+"</td>"*/
/*                //if("0" == rows[i].mobile)
                    flag = "停用";
                resStr += "<td>"+flag+"</td>";*/
                resStr += "</tr>";
            }
            $("#roleTableTbody").append(resStr);
            initPagination.call(this,'pagination-role',result.pages,result.pageNu,"initRoleGrid");
        }
    });
}

/**
 * 提交用户角色
 */
function submitUserRole(){
    var user_roles_Id = "";
    //获取checked menu
    var user_roles = $("input[name='role']:checked").each(function(){
        user_roles_Id += $(this).val() + ",";
    });

    var urlSubmit = family_ns.contextPath + "/user/adminLog/distributeRole";

    family_ns.operateConfirmByAjax.call(this,function(){
        family_ns.submitAjax({
            type: "post",
            url: urlSubmit,
            data: {
                role_users : user_roles_Id,
                userId : userId
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