/**
 * Created by cy on 16/6/22.
 */
var codeParam = {};

var dbSettings = {};

var tbodyCheck = "codeCheck";

$(function() {
	
	
	$.validator.addMethod('mobile', function(value, element) {

		// /^1\d{10}$/ 来自支付宝的正则
		return this.optional(element) || /^1\d{10}$/.test(value);

	}, '请输入正确的手机号码');
	$.validator.addMethod('personId', function(value, element) {
		return this.optional(element) || /^\d{18}|(\d{17}(\d|x|X))$/.test(value);
	}, '请输入正确的身份证号');

	var validate = $("#codeConnectForm").validate(
			{
				debug : true, //调试模式取消submit的默认提交功能
				//errorClass: "label.error", //默认为错误的样式类为：error
				focusInvalid : false, //当为false时，验证无效时，没有焦点响应
				onkeyup : false,
				submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
					codeConnect.call(this);
				},

				rules : {
					db : {
						required : true
					},
					port : {
						required : true,
						number:true
					},
					ip : {
						required : true
					},
					dbName : {
						required : true
					},
					username : {
						required : true
					},
					password : {
						required : true
					}
				},
				messages : {
					db : {
						required : "必填"
					},
					port : {
						required : "必填",
						number:"请输入数字"
					},
					ip : {
						required : "必填"
					},
					dbName : {
						required : "必填"
					},
					username : {
						required : "必填"
					},
					password : {
						required : "必填"
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
 * 连接 数据库
 */
function codeConnect() {
	//codeConnectForm
	var url = family_ns.contextPath + "/code/connectDb";
	var data = {};
	data = family_ns.serializeObject($("#codeConnectForm"));
    family_ns.operateConfirmByAjax(function(){
    	family_ns.queryAjax({
    		type : 'POST',
    		url : url,
    		data : data,
    		dataType : 'json',
    		success : function(result) {
				var rows = result.result;
				var resStrF = "";
				var resStrS = "";
				$("#tableTbodyF").html("");
				$("#tableTbodyS").html("");
				$("#generateTable").hide();
                //操作成功
                if(result.success){
					dbSettings = family_ns.serializeObject($("#codeConnectForm"));
					for(var i = 0 ; i < rows.length ; i ++){
						if(i % 2 == 0){
							resStrF += "<tr>";
							resStrF += "<td scope='row' >"
								+ "<input type='checkbox' value='"+ rows[i].tableName +"' name='" + tbodyCheck + "'/>&nbsp;&nbsp;"+(i+1)
							"</td>";
							resStrF += "<td>"+stringToBlank.call(this,rows[i].tableName) +"</td>";
							resStrF += "</tr>";
						}else{
							resStrS += "<tr>";
							resStrS += "<td scope='row' >"
								+ "<input type='checkbox' value='"+ rows[i].tableName +"' name='codeCheck'/>&nbsp;&nbsp;"+(i+1)
							"</td>";
							resStrS += "<td>"+stringToBlank.call(this,rows[i].tableName) +"</td>";
							resStrS += "</tr>";
						}
					}
					swal.close();
					$("#tableTbodyF").append(resStrF);
					$("#tableTbodyS").append(resStrS);
					$("#generateTable").show();
                }else{
					var messageAlert = family_ns.getErrMessage(result.message);
                    family_ns.operatorAlertError.call(this,messageAlert);
                }

    		}

    	});
    },null);
}


/**
 * 构建表结构
 */
function constructTables(){
	var package = $("#package").val();
	//反转表结构
	var selectTables = "";
	dbSettings.basePackage = package;
	dbSettings.author = $('#author').val();
	//获取选中的table
	var selectTablesF = $("#tableTbodyF tr td input[name='" + tbodyCheck + "']:checked");
	var selectTablesS = $("#tableTbodyS tr td input[name='" + tbodyCheck + "']:checked");
	//非法
	if(!package || "" == package.trim() || (selectTablesF.length + selectTablesS.length) == 0){
		var message = family_ns.getErrMessage("请选中表,并填写包名!");
		family_ns.operatorAlertError(message);
		return;
	}


	selectTablesF.each(function(){
		selectTables += $(this).val() + ",";
	});
	selectTablesS.each(function(){
		selectTables += $(this).val() + ",";
	});
    dbSettings.tables = selectTables;
	var url = family_ns.contextPath + "/code/constructCode";
	family_ns.operateConfirmByAjax(function(){
		/*family_ns.queryAjax({
			type : 'POST',
			url : url,
			data : dbSettings,
			dataType : 'json',
			success : function(result) {
				//操作成功
				if(result.success){
					swal.close();
				}else{
					var messageAlert = family_ns.getErrMessage(result.message);
					family_ns.operatorAlertError.call(this,messageAlert);
				}

			}

		});*/
		swal.close();
		window.open(url + "?basePackage=" + package + "&tables=" + selectTables
			+ "&ip=" + dbSettings.ip +  "&db=" + dbSettings.db + "&port=" + dbSettings.port
		    + "&dbName=" + dbSettings.dbName + "&password=" + dbSettings.password
		    + "&username=" + dbSettings.username + "&author=" + dbSettings.author);
	},null);
}
