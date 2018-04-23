family_ns.page.sort = "REV_ desc";

/**
 * constant
 * @type {string}
 */
var modelParam = {};
var tbodyId = "flowModelTbody";
var tbodyCheck = "workflowModelCheck";

// 模态框
var modelInfo ;

var validator;

/**
 * 加载流程设计模型部署表( act_re_model )数据(D_xm)
 */
$(function(){
	queryWorkflowModel.call(this,1);
	validator = $("#modelAddForm").validate(
		{
			debug : true, //调试模式取消submit的默认提交功能
			//errorClass: "label.error", //默认为错误的样式类为：error
			focusInvalid : true, //当为false时，验证无效时，没有焦点响应
			onkeyup : false,
			submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
				modelSubmit.call(this);
			},
			rules : {
				modelName : {
					required : true
				},

				modelKey : {
					required : true
					/*remote: {
					 url: family_ns.contextPath + "/user/queryLoginNameValidate" ,     //后台处理程序
					 type: "post",               //数据发送方式
					 dataType: "json",           //接受数据格式
					 data: {                     //要传递的数据
					 no: function() {
					 return $("#no").val();
					 },
					 id:function(){
					 return $("#user_id").val();
					 },
					 page:1,
					 rows:10
					 }  //远程地址只能输出 "true" 或 "false"，不能有其他输出。
					 }*/
				}
			},
			messages : {
				modelName : {
					required : "必填"
				},
				modelKey : {
					required : "必填",
					remote : "当前模型key已存在,不能使用,请更换"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent("div").parent("div").next(
					"div").find("p"));
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
 * 查询数据库的所有流程模型数据
 */
function queryWorkflowModel(page){
	var data={};
    family_ns.page.page = page;
    data= family_ns.page ;
	

	var key = $("#keyinput").val();
	data.key = key;
	var name = $("#nameinput").val();

	data.name = name;
	family_ns.queryAjax({
		 type: 'POST',
		 data:data,
	     url: family_ns.contextPath + "/workflow/model/queryWorkflowModel" ,
	     dataType: 'json',
		 success:function(results){
			var rows = results.rows;
			//数据判断
			if(!family_ns.clearTableData.call(this,tbodyId,rows))
				return;
			var resStr = "";
			for(var i = 0 ; i < rows.length ; i ++){
				resStr += "<tr>";
				resStr += "<td scope='row' id='"+rows[i].modelId+"'>"
					+"<input type='radio' name='"  + tbodyCheck+ "'/>&nbsp;&nbsp;"+(i+1)+ "</td>";
				resStr += "<td><a style='cursor:pointer' onclick='javascript:window.open(\"" +family_ns.contextPath +"/explorer/modeler.html?modelId="+rows[i].modelId+"\")'>"+stringToBlank.call(this,rows[i].name) +"<a></td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].key)+"</td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].createTime)+"</td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].lastUpdateTime)+"</td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].version)+"</td>";
				resStr += "<td><a style='cursor:pointer' onclick='javascript:window.open(\"" +family_ns.contextPath +"/workflow/model/export/"+rows[i].modelId+"/png\")'>图片<a></td>";
				resStr += "<td><a style='cursor:pointer' onclick='javascript:window.open(\"" +family_ns.contextPath +"/workflow/model/export/"+rows[i].modelId+"/bpmn\")'>配置文件<a></td>";
				resStr += "</tr>";
			}
			$("#" + tbodyId).html("");
			$("#" + tbodyId).append(resStr);
			initPagination.call(this,'paginationWorkflowModel',results.pages,results.pageNu,"queryWorkflowModel");
	            
	       }
	   });
}

/**
 * 增加模型
 */
function addModel(){
	modelInfo = layer.open({
		type: 1,
		title: '模型信息',
		fix: false,
		//maxmin: true,
		shadeClose: false,
		zIndex:1111,
		// width,height
		area: ['50%', '300px'],
		content: $("#workflowModelInfo"),
		success: function(layero, index){

		},
		end: function(){
			cleanModel.call(this);
		},
		btn: ['确认'],
		yes: function(index, layero){
			$("#modelAddForm").submit();
		}
	});
}
/**
 * 修改model
 */
function updateModel(){

}

/**
 * model submit
 */
function modelSubmit(){
	var url = family_ns.contextPath + "/workflow/model/modelAdd";
	var data = {};
	/**
	 * 判断是否为新增
	 */
	var userId = $('#user_id').val();
	if (userId && "" != userId) {
		url = family_ns.contextPath + "/workflow/model/modelDel";
	}
	//增加or修改操作
	data = family_ns.serializeObject($("#modelAddForm"));

	family_ns.operateConfirmByAjax(function(){
		family_ns.submitAjax({
			type : 'POST',
			url : url,
			data : data,
			dataType : 'json',
			success : function(result) {
				//操作成功
				if(result.success){
					window.open(family_ns.contextPath + "/explorer/modeler.html?modelId="
						+ result.result);
				}else{
					family_ns.operatorAlertError.call(this,family_ns.errMessage);
				}

			}

		});
	},null);
}
//情况表单
function cleanModel(){
	validator.resetForm();
	$("#resetWorkflowModel").click();

}
/**
 * 删除模型
 */
function deleteModel(){
	if(!family_ns.isCommonTableSelectedRow.call(this,tbodyId,tbodyCheck))
		return;
	var businessId= family_ns.getCommonTableSelectedRowBusinessId.call(this,tbodyId,tbodyCheck);
	family_ns.operateConfirmByAjax(function(){
		family_ns.submitAjax({
			type: "post",
			url: family_ns.contextPath + "/workflow/model/modelDel/" + businessId,
			data: "",
			success: function (result) {
				if (result.success) {
					window.location.href = family_ns.contextPath + "/view/background/workflowModel?mf=1&modelId="
						+ modelParam.modelId;
				}
				else
					family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
			}
		});
	});

}

/**
 * 部署模型
 */
function deployModel(){
	if(!family_ns.isCommonTableSelectedRow.call(this,tbodyId,tbodyCheck))
		return;
	var businessId= family_ns.getCommonTableSelectedRowBusinessId.call(this,tbodyId,tbodyCheck);
	family_ns.operateConfirmByAjax(function(){
		family_ns.submitAjax({
			type: "post",
			url: family_ns.contextPath + "/workflow/model/modelDeploy/" + businessId,
			data: "",
			success: function (result) {
				if (result.success) {
					window.location.href = family_ns.contextPath + "/view/background/workflowModel?mf=1&modelId="
						+ modelParam.modelId;
				}
				else
					family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
			}
		});
	});

}




