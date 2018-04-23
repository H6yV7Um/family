/**
 * lcy
 * 待办任务查询
 */
family_ns.page.sort = "id desc";
$(function() {
    initTableGrid.call(this,1);
});


function  initTableGrid(page){
    var data={};
    family_ns.page.page = page;
    data= family_ns.page ;
    $.ajax({
        type: "POST",
        url: family_ns.contextPath + "/log/queryLog",
        data: data ,
        success : function(data) {
            if(family_ns.noAuthorizcationByAjax.call(this,data)){
                return;
            }

            var rows = data.rows;
            if(!rows)
                return;
            var resStr = "";
            for ( var i = 0; i < rows.length; i++) {
                resStr += "<tr>";
                resStr += "<td scope='row' id='"+rows[i].id +"'>";
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
