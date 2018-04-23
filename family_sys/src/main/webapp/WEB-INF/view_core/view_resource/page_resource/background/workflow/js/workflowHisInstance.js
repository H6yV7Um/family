family_ns.page.sort = "REV_ desc";


var instanceParam = {};
var tbodyId = "flowHisInstanceTbody";
var tbodyCheck = "workflowHisInstanceCheck";


$(function(){
	queryWorkflowHisInstance.call(this,1);
});
/**
 * 查询数据库的所有流程实例信息
 * D_xm
 */
function queryWorkflowHisInstance(page){

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
		url: family_ns.contextPath + "/workflow/central/queryWorkflowHisInstance" ,
		dataType: 'json',
		success:function(results){

			var rows = results.rows;
			if(!family_ns.clearTableData.call(this,tbodyId,rows))
				return;
			var resStr = "";
			for(var i = 0 ; i < rows.length ; i ++){
				resStr += "<tr>";
				resStr += "<td scope='row' id='"+rows[i].id+"'>"
					+"<input type='radio' name='" + tbodyCheck + "'/>&nbsp;&nbsp;"+(i+1)
				"</td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].processInstanceId)+"</td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].processDefinitionKey)+"</td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].processDefinitionId)+"</td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].actId)+"</td>";
				resStr += "<td><div class='btn-toolbar'>" +
					"<div class='btn-group btn-group-xs'>"+
					"<button  type='button' class='btn btn-primary' " +
					"onclick='previewProcess(\""+ rows[i].processDefinitionId +"\",\"" + rows[i].processInstanceId +"\")' title='查看'><span class='glyphicon glyphicon-zoom-out'></span></button>" +
					"</div></div></td>";
				resStr += "</tr>";
			}
			$("#" + tbodyId).html("");
			$("#" + tbodyId).append(resStr);
			$('.am-dropdown').dropdown();
			initPagination.call(this,'paginationWorkflowHisInstance',results.pages,results.pageNu,"queryWorkflowHisInstance");

		}
	});
}
/**
 * 浏览 流程
 * @param pdId
 * @param piId
 */
function previewProcess(pdId,piId){
	window.open(family_ns.contextPath + "/diagram-viewer/index.html?processDefinitionId=" + pdId
		+ "&processInstanceId=" + piId);
}

