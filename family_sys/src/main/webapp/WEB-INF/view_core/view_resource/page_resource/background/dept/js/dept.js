/**
 * user js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "DEPT_CODE desc";  //cloumn 部门编号
var deptPage = 1;
$(function(){
    var validate = $("#deptAddForm").validate({
        debug: true, //调试模式取消submit的默认提交功能
        //errorClass: "label.error", //默认为错误的样式类为：error
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应
        onkeyup: false,
        submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form
           //是否确认提交
        	deptSubmit.call(this)
        },

        rules:{
            deptCode:{
                required:true,
                remote: {
                    url: family_ns.contextPath + "/dept/queryDeptCodeValidate" ,     //后台处理程序
                    type: "post",               //数据发送方式
                    dataType: "json",           //接受数据格式
                    data: {                     //要传递的数据
                        loginName: function() {
                            return $("#deptCode").val();
                        }
                    }   //远程地址只能输出 "true" 或 "false"，不能有其他输出。
                }
            },
            name:{
                required:true
            }
        },
        messages:{
            name:{
                required:"必填"
            },
            deptCode:{
                required:"必填",
                email:"部门编号,必填且必须唯一"
            }
        },
        errorPlacement: function(error, element) {
            error.appendTo( element.parent("div").parent("div").next("div").find("p") );
        },
        invalidHandler: function(errorMap,errorList){       // 如果表单验证不通过，将会触发这个函数
/*            for (var p in errorMap){
                $("#"+p).parent("div").parent("div").parent("div").removeClass("has-error");
                $("#"+p).parent("div").parent("div").parent("div").removeClass("has-success");
                $("#"+p).parent("div").parent("div").parent("div").addClass("has-error");
            }
            //正确的  success
            $(":input",$("#deptAddForm")).each(function(){
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

            });*/
        },
        //invalidHandler  表单提交时  错误验证回调 showErrors  每一次错误验证 回调
        showErrors:function(errorMap,errorList) {   //每一次错误验证
            //缺省默认错误
            this.defaultShowErrors();
        }
    });
    initTableGrid();

});

/**
 * 初始化table
 */

function initTableGrid(){
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/dept/queryDeptsForTree",

        data: family_ns.page,

        dataType: 'json',

        success: function (result) {
            $('#treeviewDept').treeview({
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
 * 新增/修改部门
 */
function deptSubmit(){
    if(isTreeViewSelected('treeviewDept')){
        var parentId = $('#upDeptId').val();
        var deptName = $("#deptName").val();
        var deptCode = $("#deptCode").val();
    	var deptId = $('#deptId').val();
    	var url = family_ns.contextPath + "/dept/adminLog/deptAdd";
    	if (deptId && "" != deptId) {
    		url = family_ns.contextPath + "/dept/adminLog/deptUpdate?businessId="+deptId;
    		parentId=null;
    	}
        clearDeptForm.call(this);
        
    	/**
    	 * 判断是否为新增
    	 */
    	//增加or修改操作
        
        family_ns.submitAjax({
    		type : 'POST',
    		url : url,
    		data : {
    			name:deptName,
    			deptCode:deptCode,
    			parentId:parentId
    		},
    		dataType : 'json',
            success: function(data){
                //操作成功
                if(data.success){
                    initTableGrid.call(this);
                    $('#deptModal').modal('hide');
                    family_ns.operatorAlertSuccess.call(this,family_ns.sucMessage);

                }else{
                    family_ns.operatorAlertError.call(this,family_ns.errMessage);
                }
            }
    	});
        
    }
}

/*function queryRolesSelect(deptId){
	var url = family_ns.contextPath + "/dept/queryRolesSelect";
	$.ajax({
		type : 'POST',
		url : url,
		dataType : 'json',
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				$("#deptSelect").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
			}
		},
		error : function(e) {
		}
	});
}*/

/**
 * add dept
 */
function addDept(){

    if(isTreeViewSelected('treeviewDept')){
        $("#deptModalLabel").html("添加部门");
        var selectedTreeView = $('#treeviewDept').treeview('getSelected');
        var parentId = selectedTreeView[0].id;
        clearDeptForm.call(this);
        $('#deptModal').modal('show');
        $('#upDept').html("&nbsp;&nbsp;&nbsp;"+selectedTreeView[0].text);
        $('#upDeptId').val(parentId);
    }
}
/**
 * update dept
 */
function updateDept(){
    if(isTreeViewSelected('treeviewDept')){
        $("#deptModalLabel").html("修改部门");
        var selectedTreeView = $('#treeviewDept').treeview('getSelected');
        var parentId = selectedTreeView[0].id;
        clearDeptForm.call(this);
        $('#deptModal').modal('show');
        var parent = $('#treeviewDept').treeview('getParent',selectedTreeView[0]);
        //存在上级部门
        if(parent.id)
            $('#upDept').html("&nbsp;&nbsp;&nbsp;"+parent.text);
        else
            $('#upDept').html("&nbsp;&nbsp;&nbsp;");
        $('#upDeptId').val(selectedTreeView[0].pid);
        $('#deptId').val(selectedTreeView[0].id);
        $('#deptName').val(selectedTreeView[0].text);
        $('#deptCode').val(selectedTreeView[0].tags[0]);
    }
}
/**
 * delete dept
 */
function deleteDept(){
    var urlSubmit = family_ns.contextPath + "/dept/adminLog/deptDelete";
    var message = {};
    message.text = "您确认你的操作?(删除部门,相关子部门也会被删除,所属人员部门置空)";
    if(isTreeViewSelected('treeviewDept')){
        var selectedTreeView = $('#treeviewDept').treeview('getSelected');
        if(!selectedTreeView[0].pid){
            family_ns.operatorAlertError.call(this,{'title':'提示','text':'不能删除最高部门!'});
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
                            initTableGrid.call(this);
                            family_ns.operatorAlertSuccess.call(this,family_ns.sucMessage);

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
 * 清空 form
 */
function clearDeptForm(){
    $('#deptReset').click();
    $('#upDeptId').val('');//reset Firefox/Chrome 浏览器中 对 隐藏域无效
    $('#deptId').val('');
    $('#deptAddForm :input').parent("div").parent("div").parent("div").removeClass("has-error");
    $('#deptAddForm :input').parent("div").parent("div").parent("div").removeClass("has-success");
}

