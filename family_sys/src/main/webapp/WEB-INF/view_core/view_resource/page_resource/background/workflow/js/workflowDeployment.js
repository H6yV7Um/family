family_ns.page.sort = "REV_ desc";

/**
 * constant
 * @type {string}
 */
var modelParam = {};
var tbodyId = "flowDeploymentTbody";
var tbodyCheck = "workflowDeploymentCheck";


/**
 * 加载流程设计流程定义部署表( act_re_procdef )数据(D_xm)
 */
$(function(){
	queryWorkflowDeployment.call(this,1);
});
/**
 * 查询数据库的所有流程定义信息
 * D_xm
 */
function queryWorkflowDeployment(page){
	
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
	     url: family_ns.contextPath + "/workflow/central/queryWorkflowDeployment" ,
	     dataType: 'json',
		 success:function(results){
	    	   
			var rows = results.rows;
			if(!family_ns.clearTableData.call(this,tbodyId,rows))
				return;
			var resStr = "";
			for(var i = 0 ; i < rows.length ; i ++){
				resStr += "<tr>";
				resStr += "<td scope='row' id='"+rows[i].id+"'>"
				+"<input type='radio' name='user'/>&nbsp;&nbsp;"+(i+1)+"</td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].name)+"</td>";
				resStr += "<td>"+stringToBlank.call(this,rows[i].key)+"</td>";

				resStr += "<td>"+stringToBlank.call(this,rows[i].version)+"</td>";
				resStr += "<td><div class='btn-group'>";
				resStr += "<a class='btn dropdown-toggle' data-toggle='dropdown' href='#'> ";
				resStr += "流程配置";
				resStr += "<span class='caret'></span></a>";
				resStr += "<ul class='dropdown-menu'><li>" +
					"<a style='cursor: pointer;' onclick='javascript:window.open(\"" +family_ns.contextPath +"/workFlow/downLoadWorkFlow?definitionId="+rows[i].id+"&type=png\")'>" +"流程图</a>" +
					"</li><li>" +
					"<a style='cursor: pointer;' onclick='javascript:window.open(\"" +family_ns.contextPath +"/workFlow/downLoadWorkFlow?definitionId="+rows[i].id+"&type=bpmn\")'>" +"配置文件</a></li>";
				resStr += "</ul></div></td>  ";
				resStr += "</tr>";
			}
			$("#" + tbodyId).html("");
			$("#" + tbodyId).append(resStr);
			initPagination.call(this,'paginationWorkflowDeployment',results.pages,results.pageNu,"queryWorkflowDeployment");
	            
	       }
	   });
}




