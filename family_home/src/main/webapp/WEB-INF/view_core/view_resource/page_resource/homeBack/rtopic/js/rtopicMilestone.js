/**
 * movie js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "start_date desc";
var vagueQuery;
var rtopicParam = {};
// tbody
var tbodyId = "rtopicTableTbody";
var tbodyCheck = "rtopicCheck";

/**
 * layer
 */
var blogInfoLayer ;
//是否可以编辑
var isEditAble = true;
var editAbleBusinessId = "";

var rtopicLabelTableName = "rtopicLabelTbody";

$(function(){

    initTableGrid.call(this,1);

});


/**
 * 初始化table
 */

function initTableGrid(page){
    var data={};
    family_ns.page.page = page;
    data= family_ns.page ;
    data.flag = 1;
    data.delFlag = 0;
    data.type = 2;
    if(vagueQuery){
        data.title = $("#rtopicName").val();
    }
    data.topicId = rtopicParam.milestoneId;
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/timesheet/queryTimesheets" ,

        data: data ,

        dataType: 'json',

        success: function(result){
            var rows = result.rows;
            //数据判断
            if(!family_ns.clearTableData.call(this,tbodyId,rows))
                return;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                resStr += "<tr class='unread checked' >";
                resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'>"
                            + "<input type='radio' name='" + tbodyCheck + "' class='checkbox'/></td>";

                resStr += "<td>"+stringToBlank.call(this,rows[i].title)+"</td>";
                resStr += "<td>"+family_ns.formatterStrByLength(rows[i].content,20,true)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].start)
                    + " ~ " +  stringToBlank.call(this,rows[i].end) +"</td>";

                resStr += "<td>"+stringToBlank.call(this,rows[i].createUser)+"</td>";
                resStr += "<td><button class='btn btn-primary' onclick='checkBlog(\"" + rows[i].businessId + "\",\"" + rows[i].blogCount + "\")' type='button'>blog(" + rows[i].blogCount + ")</button></td>";

                resStr += "</tr>";
            }
            $("#rtopicTableTbody").html(resStr);
            if(1 === result.pageNu){
                initPagination.call(this,'pagination-rtopic',result.pages,result.pageNu,"initTableGrid");
            }
        }
    });
}

/**
 * search rtopic
 */
function searchRtopic(){
    vagueQuery = true;
    initTableGrid(1);
}

/**
 * 检查 blog
 * @param businessId
 */
function checkBlog(businessId,blogCount){
    /*   空   */
    if(!blogCount || 0 == blogCount || '0' == blogCount)
        return;
    blogInfoLayer = layer.open({
        type: 1,
        title: '博客信息',
        fix: false,
        //maxmin: true,
        shadeClose: false,
        zIndex:1111,
        area: ['50%', '48%'],
        content: $("#blogInfo"),
        success: function(layero, index){
            initSheetBlog.call(this,businessId);
        },
        end: function(){
            clearSheetBlog.call(this);
        }
    });
}
/**
 * 展示 blog 列表
 */
function initSheetBlog(businessId){
    if(!businessId)
        return;
    var data={};
    data.flag = 1;
    data.delFlag = 0;
    data.sheetId = businessId;
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/blog/querySheetBlogsBySheet",

        data: data,

        dataType: 'json',

        success: function (result) {
            var rows = result.rows;
            //数据判断
            if (!family_ns.clearTableData.call(this, 'blogContent', rows))
                return;
            var resStr = "";
            for (var i = 0; i < rows.length; i++) {
                resStr += "<tr class='unread checked' style='cursor:pointer' ondblclick='browseBlog.call(this,\""+
                    rows[i].businessId
                    +"\",\""+
                    rows[i].type
                    +"\")'>";
                resStr += "<td class='spanRtopicMile' style='width: 208px;'>"+family_ns.formatterStrByLength.call(this,rows[i].title,20,true)+"</td>";
                resStr += "<td class='spanRtopicMile' style='width: 208px;'>"+family_ns.formatterStrByLength.call(this,rows[i].bkeys,20,true)+"</td>";
                resStr += "<td class='spanRtopicMile' style='width: 150px;'>"+stringToBlank.call(this,rows[i].createDate)+"</td>";
                resStr += "</tr>";
            }
            $("#blogContent").html(resStr);
        }
    });
}
/**
 * 清空blog 列表
 */
function clearSheetBlog(){
    $("#blogContent").html('');
}

//浏览博客
function browseBlog(id,type){
    if("0" == type || 0 == type)
        window.open(family_ns.contextPath + "/blog/browseBlog/"+id,'newwindow','');
    else
        window.open(family_ns.contextPath + "/blog/toHg/toMindUpdatePage/"+id,'newwindow','');

}