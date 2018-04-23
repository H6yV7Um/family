/**
 * 日志管理查询
 */
family_ns.page.sort = "business_id desc";
$(function() {
	 initTableGrid.call(this,1);
});
function searchLog(){
	initTableGrid.call(this,1);
}

function  initTableGrid(page){
	var data={};
	family_ns.page.page = page;
	data= family_ns.page ;
	data.message=$("#message").val();
	data.level=$("#selectorLevel").val();
	family_ns.queryAjax({
		type: "POST",
		url: family_ns.contextPath + "/log/queryLog",
		data: data ,
		success : function(data) {
			var rows = data.rows;
			if(!rows)
				return;
			var resStr = "";
			for ( var i = 0; i < rows.length; i++) {
                resStr += "<tr>";
                resStr += "<td scope='row' id='"+rows[i].id +"'>"+(i+1)
                	"</td>";
                resStr += "<td>"+rows[i].message+"</td>";
                resStr += "<td>"+rows[i].level+"</td>";
                resStr += "<td>"+rows[i].logDate+"</td>";
                resStr += "<td>"+rows[i].username+"</td>";
                resStr += "</tr>";
            }
            $("#logTableTbody").html("");
            $("#logTableTbody").append(resStr);
            initPagination.call(this,'pagination-log',data.pages,data.pageNu,"initTableGrid");
			
		}
	})
	
}
