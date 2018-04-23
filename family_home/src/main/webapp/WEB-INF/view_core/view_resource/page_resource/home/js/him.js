/**
 * movie js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "movie_date desc";
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
    data.rows = 20;
    data.flag = 1;
    data.delFlag = 0;
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/movie/queryMoviesAnon" ,

        data: data ,

        dataType: 'json',

        success: function(result){
            var rows = result.rows;
            if(!rows || 0 == rows.length)
                return;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                resStr += "<li><i>" + (i+1) + ".</i>";
                resStr += "<span>"
                resStr += stringToBlank.call(this,rows[i].name);
                resStr += "&nbsp;&nbsp;&nbsp;"+rows[i].movieDate;
                resStr += "</span>"

                resStr += "&nbsp;&nbsp;"+ strFormatter.call(this,rows[i].star);
                resStr += "</li>";
            }
            $("#movieListHomeUl").html("");
            $("#movieListHomeUl").append(resStr);
        },error:function (result) {
            console.info(result);
        }
    });
}

/**
 * 转换星星
 * @param star
 */
function strFormatter(star){
    var starStr = "";
    if(!star || 0 === star)
        return starStr;

    for(var i = 0 ; i < star-0.5 ; i++){
        starStr += "<i class='fa fa-star icon-state-warning'></i>";
    }
    if((star+"").split(".").length > 1)
        starStr += "<i class='fa fa-star-half-o icon-state-warning'></i>";
    return starStr;
}

