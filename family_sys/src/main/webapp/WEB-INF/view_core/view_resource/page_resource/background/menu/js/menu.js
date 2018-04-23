/**
 * user js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "no desc";
$(function(){
    var validate = $("#menuAddForm").validate({
        debug: true, //调试模式取消submit的默认提交功能
        //errorClass: "label.error", //默认为错误的样式类为：error
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应
        onkeyup: false,
        submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form
            //是否确认提交
            //operateConfirmMenu(form);
            operateConfirmMenu.call(this);
        },

        rules:{
            menuCode:{
                required:true,
                remote: {
                    url: family_ns.contextPath + "/dept/queryMenuCodeValidate" ,     //后台处理程序
                    type: "post",               //数据发送方式
                    dataType: "json",           //接受数据格式
                    data: {                     //要传递的数据
                        loginName: function() {
                            return $("#code").val();
                        }
                    }   //远程地址只能输出 "true" 或 "false"，不能有其他输出。
                }
            },
            name:{
                required:true
            },
            href:{
                maxlength:100
            },
            sort:{
                digits:true
            }
        },
        messages:{
            name:{
                required:"必填"
            },
            menuCode:{
                required:"必填",
                email:"部门编号,必填且必须唯一"
            },
            sort:{
                digits:"必须为整数"
            },
            href:{
                maxlength:"最多输入100个字符"
            }
        },
        errorPlacement: function(error, element) {
            error.appendTo( element.parent("div").parent("div").next("div").find("p") );
        },
        invalidHandler: function(errorMap,errorList){       // 如果表单验证不通过，将会触发这个函数
            for (var p in errorMap){
                $("#"+p).parent("div").parent("div").parent("div").removeClass("has-error");
                $("#"+p).parent("div").parent("div").parent("div").removeClass("has-success");
                $("#"+p).parent("div").parent("div").parent("div").addClass("has-error");
            }
            //正确的  success
            $(":input",$("#menuAddForm")).each(function(){
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

            });
        },
        //invalidHandler  表单提交时  错误验证回调 showErrors  每一次错误验证 回调
        showErrors:function(errorMap,errorList) {   //每一次错误验证
            for (var p in errorMap){
                $("#"+p).parent("div").parent("div").parent("div").removeClass("has-error");
                $("#"+p).parent("div").parent("div").parent("div").removeClass("has-success");
                $("#"+p).parent("div").parent("div").parent("div").addClass("has-error");
            }
            for (var i=0; i < this.successList.length;i++){
                var successName = this.successList[i].name;
                $("#"+ successName).parent("div").parent("div").parent("div").removeClass("has-error");
                $("#"+successName).parent("div").parent("div").parent("div").removeClass("has-success");
                $("#"+successName).parent("div").parent("div").parent("div").addClass("has-success");
            }

            //缺省默认错误
            this.defaultShowErrors();
        }
    });
    initTableGrid.call(this);

});

/**
 * 初始化table
 */

function initTableGrid(){
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/menu/queryMenusForTree",

        data: {},

        dataType: 'json',

        success: function (result) {
            $('#treeviewMenu').treeview({
                enableLinks:false,
                levels:3,
                showTags:true,
                color: "#428bca",
                data: result
            });
        }
    });

}




/**
 * add menu
 */
function addMenu(){

    if(isTreeViewSelected('treeviewMenu')){
        $("#menuModalLabel").html("添加菜单");
        //清空表单
        clearMenuForm.call(this);
        $("input[name='isShow'][value='1']").prop("checked",true);
        $("input[name='target'][value='_blank']").prop("checked",true);
        //缺省 系统
        $("input[name='modelId'][value='20']").prop("checked",true);
        var selectedTreeView = $('#treeviewMenu').treeview('getSelected');
        var parentId = selectedTreeView[0].id;
        $('#menuModal').modal('show');
        $('#upMenu').html("&nbsp;&nbsp;&nbsp;"+selectedTreeView[0].text);
        $('#upMenuId').val(parentId);
        var caseCount = this.calculateMenuCase(0);
        $("#caseMenu").html(caseCount);
        $("#caseCount").val(caseCount);
        $("#menuId").val("");
    }
}
/**
 * 计算菜单等级
 */
function calculateMenuCase(caseCount){
    var selectedNode =  $('#treeviewMenu').treeview('getSelected');
    var parentId = selectedNode[0].id;
    while(parentId){
        caseCount++;
        selectedNode = $('#treeviewMenu').treeview('getParent', selectedNode);
        parentId = selectedNode.id;
    }
    return caseCount;

}
/**
 * update menu
 */
function updateMenu(){

    if(isTreeViewSelected('treeviewMenu')) {
        var selectedTreeView = $('#treeviewMenu').treeview('getSelected');
        var menuId = selectedTreeView[0].id;
        var urlSubmit = family_ns.contextPath + "/menu/queryMenuById/" + menuId;
        family_ns.queryAjax({
            type: "post",
            url: urlSubmit,
            data: {},
            dataType: "json",
            success: function (data) {
                $("#menuModalLabel").html("修改菜单");
                //清空表单
                clearMenuForm.call(this);
                var parent = $('#treeviewMenu').treeview('getParent', selectedTreeView[0]);
                //存在上级部门
                if (parent.id){
                    $('#upMenu').html("&nbsp;&nbsp;&nbsp;" + parent.text);
                    $('#upMenuId').html("&nbsp;&nbsp;&nbsp;" + parent.id);
                }
                else
                    $('#upMenu').html("&nbsp;&nbsp;&nbsp;");

                //radio check  有问题
                //family_ns.serializeForm(data.result);


                var caseCount = calculateMenuCase(0);
                $("#caseMenu").html(caseCount-1);
                $("#caseCount").val(caseCount-1);
                initForm(data.result);
                $('#menuModal').modal('show');
            }

        });
    }
}
/**
 * delete dept
 */
function deleteMenu(){
    var urlSubmit = family_ns.contextPath + "/menu/adminLog/menuDelete";
    var message = {};
    message.text = "您确认你的操作?(删除菜单,相关子菜单也会被删除)";
    if(isTreeViewSelected('treeviewMenu')){
        var selectedTreeView = $('#treeviewMenu').treeview('getSelected');
        if(!selectedTreeView[0].pid){
            family_ns.operatorAlertError.call(this,{'title':'提示','text':'不能删除最高级菜单!'});
        }else{
            var id = selectedTreeView[0].id;
            urlSubmit += "/"+id;
            family_ns.operateConfirmByAjax(function(){

                family_ns.submitAjax({
                    type: "post",
                    url: urlSubmit,
                    data: {

                    },
                    dataType: "json",
                    success: function (data) {
                        //操作成功
                        if(data.success){
                            family_ns.operatorAlertSuccess.call(this,family_ns.sucMessage);
                            initTableGrid.call(this);

                        }else{
                            family_ns.operatorAlertError.call(this,family_ns.errMessage);
                        }
                    }
                });
            },message);
        }


    }
}

/**
 * clear form
 */
function clearMenuForm(){
    $('#menuReset').click();
    $('#upMenuId').val('');//reset Firefox/Chrome 浏览器中 对 隐藏域无效
    $('#menuId').val('');
    $('#menuAddForm :input').parent("div").parent("div").parent("div").removeClass("has-error");
    $('#menuAddForm :input').parent("div").parent("div").parent("div").removeClass("has-success");
}
/**
 * initial form
 */
function initForm(result){

    //修改  赋值form
    $("input[name='isShow']").removeAttr('checked');
    $("input[name='isShow'][value='"+result.isShow+"']").prop("checked",true);

    $("input[name='target']").removeAttr('checked');
    $("input[name='target'][value='"+result.target+"']").prop("checked",true);

    $("input[name='name']").val(result.name);

    $("input[name='code']").val(result.code);

    $("input[name='permission']").val(result.permission);

    $("input[name='href']").val(result.href);

    $("input[name='icon']").val(result.icon);

    $("input[name='sort']").val(result.sort);

    $("input[name='businessId']").val(result.businessId);


    $("input[name='modelId']").removeAttr('checked');
    $("input[name='modelId'][value='"+result.modelId+"']").prop("checked",true);
}

/**
 * 确认提交
 * @param form
 */
function operateConfirmMenu(form){
    var urlSubmit = family_ns.contextPath + "/menu/adminLog/menuAdd";
    if($("#menuId").val()){
        urlSubmit = family_ns.contextPath + "/menu/adminLog/menuUpdate";
    }
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: urlSubmit ,
            data: family_ns.serializeObject($("#menuAddForm")),
            dataType: "json",
            success: function(data){
                //操作成功
                if(data.success){
                    $('#menuModal').modal('hide');
                    initTableGrid.call(this);
                    family_ns.operatorAlertSuccess.call(this,family_ns.sucMessage);

                }else{
                    family_ns.operatorAlertError.call(this,family_ns.errMessage);
                }
            }
        });


    });
}




